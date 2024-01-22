
package com.library.modules.bs.controller;

import com.github.pagehelper.PageInfo;
import com.library.core.annotation.SysLog;
import com.library.core.entity.ProcessResult;
import com.library.core.result.PageResult;
import com.library.modules.bs.model.po.MessageBoard;
import com.library.modules.bs.service.BookOperateService;
import com.library.modules.bs.service.MessageBoardService;
import com.library.modules.constant.CommonConstant;
import com.library.modules.sys.model.SysRole;
import com.library.modules.sys.model.SysUser;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/bs/messageBoard")
public class MessageBoardController {

    @Autowired
    private MessageBoardService messageBoardService;

    @Autowired
    private BookOperateService majorService;
    static String pathPrefix = "/modules/bs/messageBoard/";

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView(pathPrefix + "list");
        return modelAndView;
    }

    @GetMapping("/form")
    public ModelAndView form() {
        ModelAndView modelAndView = new ModelAndView(pathPrefix + "form");
        modelAndView.addObject("majorList", majorService.getAll());
        return modelAndView;
    }

    @GetMapping("")
    public PageResult<MessageBoard> getAll(MessageBoard param, HttpServletRequest request) {
        SysUser sysUser = (SysUser) request.getSession().getAttribute("user");
        List<SysRole> roleList = sysUser.getRoles().stream().collect(Collectors.toList());
        SysRole role = roleList.get(0);
        if (role.getId() == CommonConstant.USER_ROLE_VIP) {
            param.setUserId(sysUser.getId());
        }
        List<MessageBoard> list = messageBoardService.getAllWithPage(param);
        return new PageResult(new PageInfo<>(list)).setCode(0);
    }


    @SysLog("保存留言")
    @PostMapping(value = "/saveOrUpdate")
    @RequiresPermissions(value = {"bs:messageBoard:edit", "bs:messageBoard:add"}, logical = Logical.OR)
    public ProcessResult saveOrUpdate(MessageBoard param, HttpServletRequest request) {
        try {
            SysUser sysUser = (SysUser) request.getSession().getAttribute("user");

            param.setUserId(sysUser.getId());
            return messageBoardService.saveOrUpdate(param);
        } catch (Exception e) {
            return new ProcessResult(ProcessResult.ERROR, e.getMessage());
        }
    }

    @GetMapping(value = "/view/{id}")
    public ProcessResult view(@PathVariable Integer id) {
        MessageBoard param = messageBoardService.getById(id);
        ProcessResult processResult = new ProcessResult();
        processResult.setData(param);
        return processResult;
    }

    @SysLog("删除留言")
    @DeleteMapping(value = "/delete/{id}")
    @RequiresPermissions("bs:messageBoard:delete")
    public ProcessResult delete(@PathVariable Integer id, HttpServletRequest request) {
        try {
            SysUser sysUser = (SysUser) request.getSession().getAttribute("user");
            List<SysRole> roleList = sysUser.getRoles().stream().collect(Collectors.toList());
            SysRole role = roleList.get(0);
            if (role.getId() == CommonConstant.USER_ROLE_VIP) {
                MessageBoard message = messageBoardService.getById(id);
                if (null == message) {
                    return new ProcessResult(ProcessResult.ERROR, "要删除的留言不存在");
                } else {
                    if (!sysUser.getId().equals(message.getUserId())) {
                        return new ProcessResult(ProcessResult.ERROR, "普通用户不能删除非自己留言");
                    }
                }

            }
            return messageBoardService.deleteById(id);
        } catch (Exception e) {
            return new ProcessResult(ProcessResult.ERROR, e.getMessage());
        }
    }

    @SysLog("批量删除")
    @DeleteMapping(value = "/batchDelete")
    @RequiresPermissions("bs:messageBoard:batchDel")
    public ProcessResult batchDelete(@RequestParam("ids[]") Integer[] ids) {
        try {
            return messageBoardService.batchDelete(ids);
        } catch (Exception e) {
            return new ProcessResult(ProcessResult.ERROR, e.getMessage());
        }
    }
}
