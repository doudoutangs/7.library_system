
package com.library.modules.bs.controller;

import com.github.pagehelper.PageInfo;
import com.library.core.annotation.SysLog;
import com.library.core.entity.ProcessResult;
import com.library.core.result.PageResult;
import com.library.modules.bs.model.UserAccountRecordDTO;
import com.library.modules.bs.model.UserAccountRecordVO;
import com.library.modules.bs.model.po.Book;
import com.library.modules.bs.model.BookDTO;
import com.library.modules.bs.model.po.UserAccountRecord;
import com.library.modules.bs.service.BookOperateService;
import com.library.modules.bs.service.UserAccountService;
import com.library.modules.constant.AccountConstant;
import com.library.modules.constant.CommonConstant;
import com.library.modules.constant.DictConstant;
import com.library.modules.sys.model.SysRole;
import com.library.modules.sys.model.SysUser;
import com.library.modules.sys.service.DictService;
import com.library.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * 用户账户金额管理
 */
@RestController
@RequestMapping("/bs/userAccount")
public class UserAccountController {

    @Autowired
    private UserAccountService accountService;
    @Autowired
    private UserService userService;
    @Autowired
    private DictService paramService;

    static String pathPrefix = "/modules/bs/userAccount/";

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView(pathPrefix + "list");
        return modelAndView;
    }

    @GetMapping("/form")
    public ModelAndView form() {
        ModelAndView modelAndView = new ModelAndView(pathPrefix + "form");
        modelAndView.addObject("vipList", userService.getVipList());
        modelAndView.addObject("accountChange", paramService.getDictByDictValue(DictConstant.USER_ACCOUNT_CHANGE));
        return modelAndView;
    }

    @GetMapping("")
    public PageResult<UserAccountRecordVO> getAll(UserAccountRecordDTO param, HttpServletRequest request) {
        SysUser sysUser = (SysUser) request.getSession().getAttribute("user");
        Set<SysRole> roles = sysUser.getRoles();
        for (SysRole role : roles) {
            if (role.getId() == CommonConstant.USER_ROLE_VIP) {
                param.setVipId(sysUser.getId());
            }
        }
        List<UserAccountRecordVO> list = accountService.getAllWithPage(param);
        return new PageResult(new PageInfo<>(list)).setCode(0);
    }


    @SysLog("充值")
    @PostMapping(value = "/recharge")
    public ProcessResult recharge(UserAccountRecord param, HttpServletRequest request) {
        try {
            SysUser sysUser = (SysUser) request.getSession().getAttribute("user");
            Set<SysRole> roles = sysUser.getRoles();
            for (SysRole role : roles) {
                if (role.getId() == CommonConstant.USER_ROLE_VIP) {
                    param.setOperateId(sysUser.getId());
                }
            }
            param.setOperateType(AccountConstant.ACCOUNT_OPERATE_TYPE_ADD);
            ProcessResult result = accountService.recharge(param);
            return result;
        } catch (Exception e) {
            return new ProcessResult(ProcessResult.ERROR, e.getMessage());
        }
    }

    @GetMapping(value = "/view/{id}")
    public ProcessResult view(@PathVariable Integer id) {
        UserAccountRecord param = accountService.getById(id);
        ProcessResult processResult = new ProcessResult();
        processResult.setData(param);
        return processResult;
    }
}
