
package com.library.modules.bs.controller;

import com.github.pagehelper.PageInfo;
import com.library.core.annotation.SysLog;
import com.library.core.entity.ProcessResult;
import com.library.core.result.PageResult;
import com.library.modules.bs.model.po.Book;
import com.library.modules.bs.model.BookDTO;
import com.library.modules.bs.service.BookOperateService;
import com.library.modules.bs.service.BookService;
import com.library.modules.constant.DictConstant;
import com.library.modules.sys.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 图书管理
 */
@RestController
@RequestMapping("/bs/book")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    BookOperateService majorService;

    @Autowired
    private DictService paramService;

    static String pathPrefix = "/modules/bs/book/";

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView(pathPrefix + "list");
        modelAndView.addObject("pressList", paramService.getDictByDictValue(DictConstant.PRESS));
        modelAndView.addObject("bookAddressList", paramService.getDictByDictValue(DictConstant.BOOK_ADDRESS));
        return modelAndView;
    }
    @GetMapping("/queryList")
    public ModelAndView queryList() {
        ModelAndView modelAndView = new ModelAndView(pathPrefix + "queryList");
        modelAndView.addObject("pressList", paramService.getDictByDictValue(DictConstant.PRESS));
        return modelAndView;
    }
    @GetMapping("/operate/vipList")
    public ModelAndView operateVipList() {
        ModelAndView modelAndView = new ModelAndView(pathPrefix + "vipList");
        return modelAndView;
    }
    @GetMapping("/form")
    public ModelAndView form() {
        ModelAndView modelAndView = new ModelAndView(pathPrefix + "form");
        modelAndView.addObject("pressList", paramService.getDictByDictValue(DictConstant.PRESS));
        modelAndView.addObject("bookAddressList", paramService.getDictByDictValue(DictConstant.BOOK_ADDRESS));
        modelAndView.addObject("categoryList", paramService.getDictByDictValue(DictConstant.BOOK_CATEGORY));
        return modelAndView;
    }

    @GetMapping("")
    public PageResult<Book> getAll(BookDTO param) {
        List<Book> list = bookService.getAllWithPage(param);
        return new PageResult(new PageInfo<>(list)).setCode(0);
    }


    @SysLog("保存图书")
    @PostMapping(value = "/saveOrUpdate")
    public ProcessResult saveOrUpdate(Book param, HttpServletRequest request) {
        try {
            ProcessResult result = bookService.saveOrUpdate(param);
            return result;
        } catch (Exception e) {
            return new ProcessResult(ProcessResult.ERROR, e.getMessage());
        }
    }

    @GetMapping(value = "/view/{id}")
    public ProcessResult view(@PathVariable Integer id) {
        Book param = bookService.getById(id);
        ProcessResult processResult = new ProcessResult();
        processResult.setData(param);
        return processResult;
    }

    @SysLog("删除图书")
    @DeleteMapping(value = "/delete/{id}")
    public ProcessResult delete(@PathVariable Integer id) {
        try {
            return bookService.deleteById(id);
        } catch (Exception e) {
            return new ProcessResult(ProcessResult.ERROR, e.getMessage());
        }
    }


    @SysLog("批量删除")
    @DeleteMapping(value = "/batchDelete")
    public ProcessResult batchDelete(@RequestParam("ids[]") Integer[] ids) {
        try {
            return bookService.batchDelete(ids);
        } catch (Exception e) {
            return new ProcessResult(ProcessResult.ERROR, e.getMessage());
        }
    }

}
