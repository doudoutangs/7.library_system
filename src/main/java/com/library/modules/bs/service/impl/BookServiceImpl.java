package com.library.modules.bs.service.impl;

import com.github.pagehelper.PageHelper;
import com.library.core.entity.ProcessResult;
import com.library.modules.bs.mapper.BookMapper;
import com.library.modules.bs.mapper.BookOperateDetailMapper;
import com.library.modules.bs.model.po.Book;
import com.library.modules.bs.model.BookDTO;
import com.library.modules.bs.model.po.BookOperateDetail;
import com.library.modules.bs.service.BookService;
import com.library.modules.constant.BookConstant;
import com.library.modules.constant.CommonConstant;
import com.library.modules.sys.mapper.SysDictMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by sugar on 2021/11/25.
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;
    @Autowired
    SysDictMapper dictMapper;
    @Autowired
    BookOperateDetailMapper bookOperateDetailMapper;

    @Override
    public List<Book> getAllWithPage(BookDTO param) {
        PageHelper.startPage(param.getPage(), param.getLimit());
        String bookNos = param.getBookNos();
        if (StringUtils.isNotEmpty(bookNos)){
            param.setBookNoList(Arrays.asList(bookNos.split(",")));
        }
        List<Book> list = bookMapper.query(param);
        return list;
    }

    @Override
    public List<Book> getAll() {
        return bookMapper.selectAll();
    }

    @Override
    public ProcessResult saveOrUpdate(Book param) {
        Integer result = 0;
        if (param.getId() != null) {//update
            if(param.getState().equals(BookConstant.BOOK_STATE_DOWN)){
                param.setDownDate(new Date());
            }
            result = bookMapper.updateByPrimaryKeySelective(param);
        } else {//insert
            String bookNo = getBookNo();
            param.setBookNo(bookNo);
            param.setCreateTime(new Date());
            param.setUpDate(new Date());
            result = bookMapper.insert(param);
        }
        if (result == CommonConstant.OPERATE_SUCCESS) {
            return new ProcessResult();
        } else {
            return new ProcessResult(ProcessResult.ERROR, "操作失败");
        }

    }

    private String getBookNo() {
        while (true) {
            String bookNo = RandomStringUtils.randomNumeric(6);
            Book u = new Book();
            u.setBookNo(bookNo);
            Book book = bookMapper.selectOne(u);
            if (null == book) {
                return bookNo;
            }
        }
    }

    @Override
    public Book getById(Integer id) {
        return bookMapper.selectByPrimaryKey(id);
    }

    @Override
    public ProcessResult deleteById(Integer id) {
        Integer result = 0;
        List<BookOperateDetail> bookOperateDetailList = bookOperateDetailMapper.getByBookIdList(Arrays.asList(id));
        if(CollectionUtils.isNotEmpty(bookOperateDetailList)){
            return new ProcessResult(ProcessResult.ERROR, id+",该图书已有借阅记录，无法删除");
        }
        result = bookMapper.deleteByPrimaryKey(id);
        if (result == CommonConstant.OPERATE_SUCCESS) {
            return new ProcessResult();
        } else {
            return new ProcessResult(ProcessResult.ERROR, "操作失败");
        }
    }

    @Override
    public ProcessResult batchDelete(Integer[] list) {
        List<BookOperateDetail> bookOperateDetailList = bookOperateDetailMapper.getByBookIdList(Arrays.asList(list));
        if(CollectionUtils.isNotEmpty(bookOperateDetailList)){
            return new ProcessResult(ProcessResult.ERROR, "有图书已有借阅记录，无法删除");
        }
        Integer result = bookMapper.batchDelete(Arrays.asList(list));
        if (result == list.length) {
            return new ProcessResult();
        } else {
            return new ProcessResult(ProcessResult.ERROR, "操作失败");
        }
    }

}
