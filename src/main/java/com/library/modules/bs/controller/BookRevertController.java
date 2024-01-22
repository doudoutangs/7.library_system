
package com.library.modules.bs.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageInfo;
import com.library.core.annotation.SysLog;
import com.library.core.entity.ProcessResult;
import com.library.core.result.PageResult;
import com.library.modules.bs.model.BookOperateDTO;
import com.library.modules.bs.model.po.BookWarn;
import com.library.modules.bs.model.BookWarnDTO;
import com.library.modules.bs.model.BookWarnVO;
import com.library.modules.bs.service.BookOperateService;
import com.library.modules.bs.service.BookWarnService;
import com.library.modules.constant.BookConstant;
import com.library.modules.constant.CommonConstant;
import com.library.modules.constant.DictConstant;
import com.library.modules.sys.model.SysRole;
import com.library.modules.sys.model.SysUser;
import com.library.modules.sys.service.DictService;
import com.library.modules.sys.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 图书借出，归还，延期归还
 */
@Slf4j
@RestController
@RequestMapping("/bs/bookRevert")
public class BookRevertController {
    @Value("${borrow.maxDay}")
    private Integer borrowMaxDay;
    @Autowired
    private BookWarnService bookWarnService;
    @Autowired
    private BookOperateService bookOperateService;
    @Autowired
    private DictService paramService;
    @Autowired
    private UserService userService;
    static String pathPrefix = "/modules/bs/bookRevert/";

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView(pathPrefix + "list");
        modelAndView.addObject("pressList", paramService.getDictByDictValue(DictConstant.PRESS));
        modelAndView.addObject("bookAddressList", paramService.getDictByDictValue(DictConstant.BOOK_ADDRESS));
        return modelAndView;
    }

    @GetMapping("/form")
    public ModelAndView form() {
        ModelAndView modelAndView = new ModelAndView(pathPrefix + "form");
        return modelAndView;
    }

    @GetMapping("")
    public PageResult<BookWarnVO> getAll(BookWarnDTO param, HttpServletRequest request) {
        SysUser sysUser = (SysUser) request.getSession().getAttribute("user");
        Set<SysRole> roles = sysUser.getRoles();
        for (SysRole role : roles) {
            if (role.getId() == CommonConstant.USER_ROLE_VIP) {
                param.setVipId(sysUser.getId());
            }
        }
        String phone = param.getPhone();
        if (StringUtils.isNotEmpty(phone)) {
            List<Integer> vipList = userService.queryUserIdByKeyword(phone);
            if (CollectionUtils.isNotEmpty(vipList)) {
                param.setVipIdList(vipList);
            } else {
                return new PageResult(new PageInfo<>(CollUtil.newArrayList())).setCode(0);
            }
        }
        List<BookWarnVO> list = bookWarnService.getAllWithPage(param);
        return new PageResult(new PageInfo<>(list)).setCode(0);
    }

    @SysLog("新增还书记录")
    @PostMapping(value = "/revert")
    @RequiresPermissions(value = {"bs:bookRevert:revert"})
    public ProcessResult batchAdd(
            @RequestParam("bookWarnIds[]") Integer[] bookWarnIds,
            HttpServletRequest request
    ) {
        try {
            log.info("新增还书记录-入参：{}",bookWarnIds.toString());
            SysUser sysUser = (SysUser) request.getSession().getAttribute("user");
            List<BookWarnVO> bookWarnList = bookWarnService.queryByBookWarnIdList(Arrays.asList(bookWarnIds));
            for (BookWarnVO warnVO : bookWarnList) {
                if(!BookConstant.OPERATE_TYPE_LEND.equals(warnVO.getStatus())){
                    return new ProcessResult(ProcessResult.ERROR, "《"+warnVO.getBookName()+"》不是借出状态，请重新选择");
                }
            }
            Integer[] bookIds = bookWarnList.stream().map(BookWarnVO::getBookId).toArray(Integer[]::new);
            List<Integer> vipIdList = bookWarnList.stream().collect(Collectors.groupingBy(BookWarnVO::getVipId)).keySet().stream().collect(Collectors.toList());
            if (vipIdList.size() > 1) {
                return new ProcessResult(ProcessResult.ERROR, "请选择同一个人的借书记录");
            }
            BookOperateDTO param = new BookOperateDTO();
            param.setOperateType(BookConstant.OPERATE_TYPE_REVERT);
            param.setBookIds(bookIds);
            param.setVipId(vipIdList.get(0));
            param.setOperateId(sysUser.getId());
            param.setOperateCount(bookWarnIds.length);
            return bookOperateService.batchAdd(param);
        } catch (Exception e) {
            e.printStackTrace();
            return new ProcessResult(ProcessResult.ERROR, e.getMessage());
        }
    }

    @SysLog("新增延期归还记录")
    @PostMapping(value = "/delayRevert")
    @RequiresPermissions(value = {"bs:bookRevert:delayRevert"})
    public ProcessResult delayRevert(
            @RequestParam("bookWarnIds[]") Integer[] bookWarnIds,
            HttpServletRequest request
    ) {
        try {
            log.info("新增延期归还记录-入参：{}",bookWarnIds.toString());
            SysUser sysUser = (SysUser) request.getSession().getAttribute("user");
            List<BookWarnVO> bookWarnList = bookWarnService.queryByBookWarnIdList(Arrays.asList(bookWarnIds));
            List<Integer> vipIdList = bookWarnList.stream().collect(Collectors.groupingBy(BookWarnVO::getVipId)).keySet().stream().collect(Collectors.toList());
            if (vipIdList.size() > 1) {
                return new ProcessResult(ProcessResult.ERROR, "请选择同一个人的借书记录");
            }
            List<BookWarn> updateList = CollUtil.newArrayList();
            for (BookWarnVO warnVO : bookWarnList) {
                if(!BookConstant.OPERATE_TYPE_LEND.equals(warnVO.getStatus())){
                    return new ProcessResult(ProcessResult.ERROR, "《"+warnVO.getBookName()+"》不是借出状态，请重新选择");
                }
                if(StringUtils.isNotEmpty(warnVO.getRevertTime2())){
                    return new ProcessResult(ProcessResult.ERROR, "《"+warnVO.getBookName()+"》已延期过，一次不能再延期");
                }
                DateTime dateTime = DateUtil.offsetDay(new Date(), borrowMaxDay);

                warnVO.setRevertTime2(dateTime.toString());
                warnVO.setExtendOperateId(sysUser.getId());
                BookWarn warn = new BookWarn();
                BeanUtil.copyProperties(warnVO,warn);
                updateList.add(warn);
            }
            return bookWarnService.batchUpdate(updateList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ProcessResult(ProcessResult.ERROR, e.getMessage());
        }
    }
    @SysLog("新增定损记录")
    @PostMapping(value = "/loss")
    @RequiresPermissions(value = {"bs:bookRevert:loss"})
    public ProcessResult loss(
            @RequestParam("bookWarnIds[]") Integer[] bookWarnIds,
            HttpServletRequest request
    ) {
        try {
            log.info("新增定损记录-入参：{}",bookWarnIds.toString());
            SysUser sysUser = (SysUser) request.getSession().getAttribute("user");
            List<BookWarnVO> bookWarnList = bookWarnService.queryByBookWarnIdList(Arrays.asList(bookWarnIds));
            List<Integer> vipIdList = bookWarnList.stream().collect(Collectors.groupingBy(BookWarnVO::getVipId)).keySet().stream().collect(Collectors.toList());
            if (vipIdList.size() > 1) {
                return new ProcessResult(ProcessResult.ERROR, "请选择同一个人的借书记录");
            }
            List<BookWarn> updateList = CollUtil.newArrayList();
            for (BookWarnVO warnVO : bookWarnList) {
                if(!BookConstant.OPERATE_TYPE_LEND.equals(warnVO.getStatus())){
                    return new ProcessResult(ProcessResult.ERROR, "《"+warnVO.getBookName()+"》不是借出状态，请重新选择");
                }
                if(StringUtils.isNotEmpty(warnVO.getRevertTime2())){
                    return new ProcessResult(ProcessResult.ERROR, "《"+warnVO.getBookName()+"》已延期过，一次不能再延期");
                }
                DateTime dateTime = DateUtil.offsetDay(new Date(), borrowMaxDay);

                warnVO.setRevertTime2(dateTime.toString());
                warnVO.setExtendOperateId(sysUser.getId());
                BookWarn warn = new BookWarn();
                BeanUtil.copyProperties(warnVO,warn);
                updateList.add(warn);
            }
            return bookWarnService.batchUpdate(updateList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ProcessResult(ProcessResult.ERROR, e.getMessage());
        }
    }
}
