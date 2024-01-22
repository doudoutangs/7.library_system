
package com.library.modules.bs.controller;

import com.github.pagehelper.PageInfo;
import com.library.core.annotation.SysLog;
import com.library.core.entity.ProcessResult;
import com.library.core.result.PageResult;
import com.library.modules.bs.model.po.Notice;
import com.library.modules.bs.service.NoticeService;
import com.library.modules.sys.model.SysUser;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/bs/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    static String pathPrefix = "/modules/bs/notice/";

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView(pathPrefix + "list");
        return modelAndView;
    }

    @GetMapping("/form")
    public ModelAndView form() {
        ModelAndView modelAndView = new ModelAndView(pathPrefix + "form");

        return modelAndView;
    }

    @GetMapping("")
    public PageResult<Notice> getAll(Notice param) {
        List<Notice> list = noticeService.getAllWithPage(param);
        return new PageResult(new PageInfo<>(list)).setCode(0);
    }


    @SysLog("保存公告")
    @PostMapping(value = "/saveOrUpdate")
    @RequiresPermissions(value = {"bs:notice:edit", "bs:notice:add"}, logical = Logical.OR)
    public ProcessResult saveOrUpdate(Notice param, HttpServletRequest request) {
        try {
            SysUser sysUser = (SysUser) request.getSession().getAttribute("user");

            param.setUserId(sysUser.getId());
            return noticeService.saveOrUpdate(param);
        } catch (Exception e) {
            return new ProcessResult(ProcessResult.ERROR, e.getMessage());
        }
    }

    @GetMapping(value = "/view/{id}")
    public ProcessResult view(@PathVariable Integer id) {
        Notice param = noticeService.getById(id);
        ProcessResult processResult = new ProcessResult();
        processResult.setData(param);
        return processResult;
    }

    @SysLog("删除公告")
    @DeleteMapping(value = "/delete/{id}")
    @RequiresPermissions("bs:notice:delete")
    public ProcessResult delete(@PathVariable Integer id) {
        try {
            return noticeService.deleteById(id);
        } catch (Exception e) {
            return new ProcessResult(ProcessResult.ERROR, e.getMessage());
        }
    }

    @SysLog("批量删除")
    @DeleteMapping(value = "/batchDelete")
    @RequiresPermissions("bs:notice:batchDel")
    public ProcessResult batchDelete(@RequestParam("ids[]") Integer[] ids) {
        try {
            return noticeService.batchDelete(ids);
        } catch (Exception e) {
            return new ProcessResult(ProcessResult.ERROR, e.getMessage());
        }
    }
}
