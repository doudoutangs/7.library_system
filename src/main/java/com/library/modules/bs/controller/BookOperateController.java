
package com.library.modules.bs.controller;

import com.github.pagehelper.PageInfo;
import com.library.core.annotation.SysLog;
import com.library.core.entity.ProcessResult;
import com.library.core.result.PageResult;
import com.library.modules.bs.model.po.BookOperate;
import com.library.modules.bs.model.BookOperateDTO;
import com.library.modules.bs.model.po.BookOperateDetail;
import com.library.modules.bs.service.BookOperateService;
import com.library.modules.constant.BookConstant;
import com.library.modules.constant.CommonConstant;
import com.library.modules.constant.DictConstant;
import com.library.modules.sys.model.SysRole;
import com.library.modules.sys.model.SysUser;
import com.library.modules.sys.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * 借还记录管理
 */
@RestController
@RequestMapping("/bs/bookOperate")
public class BookOperateController {

    @Autowired
    private BookOperateService bookOperateService;

    @Autowired
    private UserService userService;
    @Value("${borrow.maxCount}")
    private String borrowMaxCount;
    static String pathPrefix = "/modules/bs/bookOperate/";

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView(pathPrefix + "list");
        modelAndView.addObject("vipList", userService.getVipList());

        return modelAndView;
    }

    @GetMapping("/singleList")
    public ModelAndView singleList() {
        ModelAndView modelAndView = new ModelAndView(pathPrefix + "singleList");
        return modelAndView;
    }

    @GetMapping("/form/{operateNo}")
    public ModelAndView form(@PathVariable Integer operateNo) {
        ModelAndView modelAndView = new ModelAndView(pathPrefix + "detailList");
        modelAndView.addObject("operateNo", operateNo);
        return modelAndView;
    }

    @GetMapping(value = "/detail/{id}")
    public PageResult<List<BookOperateDetail>> detail(@PathVariable Integer id) {
        List<BookOperateDetail> list = bookOperateService.getById(id);
        return new PageResult(new PageInfo<>(list)).setCode(0);
    }

    @GetMapping("")
    public PageResult<BookOperate> getAll(BookOperateDTO param, HttpServletRequest request) {
        SysUser sysUser = (SysUser) request.getSession().getAttribute("user");
        Set<SysRole> roles = sysUser.getRoles();
        for (SysRole role : roles) {
            if (role.getId() == CommonConstant.USER_ROLE_VIP) {
                param.setVipId(sysUser.getId());
            }
        }
        List<BookOperate> list = bookOperateService.getAllWithPage(param);
        return new PageResult(new PageInfo<>(list)).setCode(0);
    }


    @SysLog("保存借还记录")
    @PostMapping(value = "/{operateType}/batchAdd")
    @RequiresPermissions(value = {"bs:bookOperate:batchAdd"})
    public ProcessResult batchAdd(@PathVariable Integer operateType,
                                  @RequestParam("bookIds[]") Integer[] bookIds,
                                  @RequestParam("vipId") Integer vipId,
                                  HttpServletRequest request
    ) {
        try {
            SysUser sysUser = (SysUser) request.getSession().getAttribute("user");
            int maxCount = Integer.parseInt(borrowMaxCount);
            int length = bookIds.length;
            if (BookConstant.OPERATE_TYPE_LEND.equals(operateType)) {
                SysUser vip = userService.getById(vipId);
                String cardTypeName = vip.getCardType();
                if (cardTypeName.endsWith(DictConstant.CARD_TYPE_ONLY_READ)) {
                    return new ProcessResult(ProcessResult.ERROR, "当前借书人的图书证类型不可借阅图书，请升级图书证后再来借阅");
                } else {
                    if (length > maxCount) {
                        return new ProcessResult(ProcessResult.ERROR, "个人最多一次借" + maxCount + "本书");
                    }
                }
            }
            BookOperateDTO param = new BookOperateDTO();
            param.setOperateType(operateType);
            param.setBookIds(bookIds);
            param.setVipId(vipId);
            param.setOperateId(sysUser.getId());
            param.setOperateCount(length);
            return bookOperateService.batchAdd(param);
        } catch (Exception e) {
            e.printStackTrace();
            return new ProcessResult(ProcessResult.ERROR, e.getMessage());
        }
    }
}
