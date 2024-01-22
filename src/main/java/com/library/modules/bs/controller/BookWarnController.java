
package com.library.modules.bs.controller;

import com.github.pagehelper.PageInfo;
import com.library.core.annotation.SysLog;
import com.library.core.entity.ProcessResult;
import com.library.core.result.PageResult;
import com.library.modules.bs.model.*;
import com.library.modules.bs.service.BookService;
import com.library.modules.bs.service.BookWarnService;
import com.library.modules.constant.CommonConstant;
import com.library.modules.constant.DictConstant;
import com.library.modules.sys.model.SysRole;
import com.library.modules.sys.model.SysUser;
import com.library.modules.sys.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * 图书到期提醒
 */
@RestController
@RequestMapping("/bs/bookWarn")
public class BookWarnController {

    @Autowired
    private BookWarnService bookWarnService;

    @Autowired
    private DictService paramService;

    static String pathPrefix = "/modules/bs/bookWarn/";

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView(pathPrefix + "list");
        modelAndView.addObject("pressList", paramService.getDictByDictValue(DictConstant.PRESS));
        modelAndView.addObject("bookAddressList", paramService.getDictByDictValue(DictConstant.BOOK_ADDRESS));
        return modelAndView;
    }

    @GetMapping("/form/{id}")
    public ModelAndView form(@PathVariable Integer id,ModelMap map) {
        ModelAndView modelAndView = new ModelAndView(pathPrefix + "form");
        BookWarnVO param = bookWarnService.getById(id);
        map.put("bookWarn", param);
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
        List<BookWarnVO> list = bookWarnService.getAllWithPage(param);
        return new PageResult(new PageInfo<>(list)).setCode(0);
    }
    @GetMapping(value = "/view/{id}")
    public ProcessResult view(@PathVariable Integer id) {
        BookWarnVO param = bookWarnService.getById(id);
        ProcessResult processResult = new ProcessResult();
        processResult.setData(param);
        return processResult;
    }
}
