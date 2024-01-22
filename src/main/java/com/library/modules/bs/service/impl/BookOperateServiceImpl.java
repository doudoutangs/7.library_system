package com.library.modules.bs.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.library.core.entity.ProcessResult;
import com.library.core.util.CalcTool;
import com.library.core.util.DateTool;
import com.library.modules.bs.mapper.BookMapper;
import com.library.modules.bs.mapper.BookOperateDetailMapper;
import com.library.modules.bs.mapper.BookOperateMapper;
import com.library.modules.bs.mapper.BookWarnMapper;
import com.library.modules.bs.model.*;
import com.library.modules.bs.model.po.Book;
import com.library.modules.bs.model.po.BookOperate;
import com.library.modules.bs.model.po.BookOperateDetail;
import com.library.modules.bs.model.po.BookWarn;
import com.library.modules.bs.service.BookOperateService;
import com.library.modules.constant.BookConstant;
import com.library.modules.constant.CommonConstant;
import com.library.modules.sys.mapper.SysDictMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by sugar on 2021/11/25.
 */
@Service
public class BookOperateServiceImpl implements BookOperateService {

    @Autowired
    BookOperateMapper operateMapper;
    @Autowired
    SysDictMapper dictMapper;
    @Autowired
    BookMapper bookMapper;
    @Autowired
    BookOperateDetailMapper bookOperateDetailMapper;
    @Autowired
    BookWarnMapper bookWarnMapper;
    @Value("${borrow.maxDay}")
    private Integer borrowMaxDay;
    @Value("${borrow.damagesDay}")
    private String damagesDay;

    @Override
    public List<BookOperate> getAllWithPage(BookOperateDTO param) {
        PageHelper.startPage(param.getPage(), param.getLimit());
        String operateDate = param.getOperateDate();
        if (StringUtils.isNotEmpty(operateDate)) {
            String[] date = operateDate.split("-");
            param.setOperateYear(Integer.parseInt(date[0]));
            param.setOperateMonth(Integer.parseInt(date[1]));
        }
        List<BookOperate> list = operateMapper.query(param);
        return list;
    }

    @Override
    public List<BookOperate> getAll() {
        return operateMapper.selectAll();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProcessResult saveOrUpdate(BookOperate param) {
        Integer result = 0;
        if (null != param.getId()) {//update
            result = operateMapper.updateByPrimaryKeySelective(param);
        } else {//insert
            param.setCreateTime(new Date());
//            param.setId(IDUtil.randomUnion(param.getUniversitiesId()));
            result = operateMapper.insert(param);
        }
        if (result == CommonConstant.OPERATE_SUCCESS) {
            return new ProcessResult();
        } else {
            return new ProcessResult(ProcessResult.ERROR, "操作失败");
        }
    }

    /**
     * @param param
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProcessResult batchAdd(BookOperateDTO param) {
        Integer[] bookIds = param.getBookIds();
        if (bookIds.length == 0) {
            return new ProcessResult(ProcessResult.ERROR, "请选择图书后再借阅");
        }
        List<Book> bookList = bookMapper.getByIdList(Arrays.asList(bookIds));
        if (BookConstant.OPERATE_TYPE_LEND.equals(param.getOperateType())) {
            //1.1借书
            for (Book book : bookList) {
                int total = book.getTotal();
                Integer state = book.getState();
                if (BookConstant.BOOK_STATE_DOWN.equals(state)) {
                    String msg = "《" + book.getName() + "》" + ",该图书已下架";
                    return new ProcessResult(ProcessResult.ERROR, msg);
                }
                if (total < 1) {
                    String msg = "《" + book.getName() + "》" + ",该图书数量不足,无法借阅";
                    return new ProcessResult(ProcessResult.ERROR, msg);
                }
            }
            //1.1更新图书数量,减1
            bookList.stream().forEach(b -> {
                b.setTotal(new AtomicInteger(b.getTotal()).decrementAndGet());
                b.setUpdateTime(new Date());
            });
            bookMapper.batchUpdate(bookList);
        } else {
            //1.2还书
            // 更新图书数量，加1
            bookList.stream().forEach(b -> {
                b.setTotal(new AtomicInteger(b.getTotal()).incrementAndGet());
                b.setUpdateTime(new Date());
            });
            bookMapper.batchUpdate(bookList);
        }
        //2.新增借还书记录及详情
        BookOperate bookOperate = addOperate(param, bookIds, bookList);
        //3.添加书的操作详情
        addDetail(param, bookList, bookOperate);

        ProcessResult processResult = new ProcessResult();
        return processResult;
    }

    /**
     * 新增本次借书记录
     *
     * @param param
     * @param bookIds
     * @param bookList
     * @return
     */
    private BookOperate addOperate(BookOperateDTO param, Integer[] bookIds, List<Book> bookList) {
        List<String> bookNameList = bookList.stream().map(Book::getName).collect(Collectors.toList());
        BookOperate bookOperate = new BookOperate();
        bookOperate.setVipId(param.getVipId());
        bookOperate.setOperateId(param.getOperateId());
        bookOperate.setOperateType(param.getOperateType());
        bookOperate.setOperateCount(bookIds.length);
        bookOperate.setCreateTime(new Date());
        int year = DateTool.getNowYearMonth()[0];
        int month = DateTool.getNowYearMonth()[1];
        bookOperate.setOperateYear(year);
        bookOperate.setOperateMonth(month);
        bookOperate.setBookName(bookNameList.toString());
        operateMapper.insert(bookOperate);
        return bookOperate;
    }

    /**
     * 添加图书操作详情
     *
     * @param param
     * @param bookList
     * @param bookOperate
     */
    private void addDetail(BookOperateDTO param, List<Book> bookList, BookOperate bookOperate) {
        List<BookOperateDetail> detailList = CollUtil.newArrayList();
        List<BookWarn> warnList = CollUtil.newArrayList();
        Integer operateType = bookOperate.getOperateType();
        for (Book book : bookList) {
            BookOperateDetail d = new BookOperateDetail();
            BeanUtil.copyProperties(bookOperate, d, "id");
            Integer bookId = book.getId();
            d.setBookId(bookId);
            Integer bookOperateNo = bookOperate.getId();
            d.setOperateNo(bookOperateNo);
            d.setOperateId(param.getOperateId());
            detailList.add(d);

            //过期提醒
            BookWarn warn = null;
            if (BookConstant.OPERATE_TYPE_LEND.equals(operateType)) {
                warn = getLendWarnList(d, bookId, bookOperateNo);
            } else {
                warn = getRevertWarnList(d, bookId);
            }
            warnList.add(warn);

        }
        if (BookConstant.OPERATE_TYPE_LEND.equals(operateType)) {
            bookWarnMapper.insertList(warnList);
        } else {
            bookWarnMapper.updateList(warnList);
        }
        bookOperateDetailMapper.insertList(detailList);
    }

    /**
     * 借书记录
     *
     * @param d
     * @param bookId
     * @param bookOperateId
     * @return
     */
    private BookWarn getLendWarnList(BookOperateDetail d, Integer bookId, Integer bookOperateId) {
        BookWarn warn = BookWarn.builder()
                .bookId(bookId)
                .vipId(d.getVipId())
                .bookOperateNo(bookOperateId)
                .build();
        String now = DateUtil.now();
        //借书
        DateTime dateTime = DateUtil.offsetDay(new Date(), borrowMaxDay);
        warn.setLendTime(now);
        warn.setRevertTime(dateTime.toString());
        warn.setStatus(BookConstant.OPERATE_TYPE_LEND);
        warn.setLendOperateId(d.getOperateId());
        warn.setCreateTime(new Date());
        return warn;
    }

    /**
     * 还书记录
     *
     * @param d
     * @param bookId
     * @return
     */
    private BookWarn getRevertWarnList(BookOperateDetail d, Integer bookId) {
        BookWarn warn = BookWarn.builder()
                .bookId(bookId)
                .vipId(d.getVipId())
                .build();
        String now = DateUtil.now();

        BookWarn bookWarn = bookWarnMapper.selectOne(warn);
        String revertTime2 = bookWarn.getRevertTime2();
        String revertTime1 = bookWarn.getRevertTime();
        Integer overDay = 0;
        DateTime date2 = DateTime.now();
        DateTime date1 = null;
        if (StringUtils.isNotEmpty(revertTime2)) {
            date1 = DateUtil.parse(revertTime2, DatePattern.NORM_DATE_PATTERN);
        } else {
            date1 = DateUtil.parse(revertTime1, DatePattern.NORM_DATE_PATTERN);
        }
        long between = DateUtil.between(date2, date1, DateUnit.DAY, false);
        overDay = Integer.valueOf(between + "");
        //还书
        if (overDay < 0) {
            //判断是否超期,并计算违约金
            bookWarn.setOverDay(Math.abs(overDay));
            Double damagesAmount = CalcTool.multiplyCalc(Math.abs(overDay), Double.valueOf(damagesDay));
            bookWarn.setDamagesAmount(damagesAmount.toString());
        }
        bookWarn.setStatus(BookConstant.OPERATE_TYPE_REVERT);
        bookWarn.setRealRevertTime(now);
        bookWarn.setRevertOperateId(d.getOperateId());
        bookWarn.setUpdateTime(new Date());
        return bookWarn;
    }

    @Override
    public List<BookOperateDetail> getById(Integer id) {
        return bookOperateDetailMapper.getByOperateNo(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProcessResult deleteById(String id) {
        int result = operateMapper.deleteByPrimaryKey(id);
        if (result == CommonConstant.OPERATE_SUCCESS) {
            return new ProcessResult();
        } else {
            return new ProcessResult(ProcessResult.ERROR, "操作失败");
        }
    }

//    public static void main(String[] args) {
//        String revertTime2 = "2023-11-28 16:45:32";
//        DateTime date1 = DateUtil.parse(revertTime2, DatePattern.NORM_DATE_PATTERN);
//        DateTime date2 = DateTime.now();
//        long between = DateUtil.between(date2, date1, DateUnit.DAY, false);
//        int overDay = Integer.valueOf(between + "");
//        System.out.println("日期1："+date1+"--日期1："+date2+"，相差:"+between);
//        System.out.println(overDay);
//        System.out.println(Math.abs(overDay));
//    }
}
