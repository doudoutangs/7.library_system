package com.library.modules.sys.controller;

import com.library.modules.bs.model.po.Notice;
import com.library.modules.bs.service.BookService;
import com.library.modules.bs.service.NoticeService;
import com.library.modules.sys.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping
@Controller
public class InitController {
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private BookService bookService;

    @GetMapping("/toIndex")
    public ModelAndView toIndex(ModelMap map, HttpServletRequest request) {
        SysUser vo = (SysUser) request.getSession().getAttribute("user");
        if (vo != null) {
            map.put("user", vo);
        }
        return new ModelAndView("index");
    }

    @GetMapping("/toHomeView")
    public ModelAndView home(ModelMap map, HttpServletRequest request) {
        List<Notice> noticeList = noticeService.getAll();
//        List<Book> bookList = bookService.getAll();
        map.put("noticeList", noticeList);
//        map.put("bookList", bookList);
        return new ModelAndView("home");
    }
    @GetMapping("/toRegisterView")
    public ModelAndView toRegisterView(ModelMap map, HttpServletRequest request) {
        List<Notice> noticeList = noticeService.getAll();
//        List<Book> bookList = bookService.getAll();
        map.put("noticeList", noticeList);
//        map.put("bookList", bookList);
        return new ModelAndView("register");
    }
    @GetMapping("/toLoginView")
    public ModelAndView toLoginView(ModelMap map, HttpServletRequest request) {
        List<Notice> noticeList = noticeService.getAll();
//        List<Book> bookList = bookService.getAll();
        map.put("noticeList", noticeList);
//        map.put("bookList", bookList);
        return new ModelAndView("login");
    }
    @GetMapping("/showRegister")
    public String showRegister() {
        return "register";
    }
    @GetMapping("/modifyPassword")
    public ModelAndView modifyPassword(ModelMap map, HttpServletRequest request) {
        SysUser vo = (SysUser) request.getSession().getAttribute("user");
        if (vo != null) {
            map.put("user", vo);
        }
        return new ModelAndView("/modules/sys/set/password");
    }

    @GetMapping("/toError")
    public String error() {
        return "error";
    }



    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/avatar")
    public String avatar() {
        return "avatar";
    }

    @RequestMapping({"/toLogin"})
    public String home() {
        return "login";
    }

    @RequestMapping({"/", ""})
    public String toHome() {
        return "toHomeView";
    }
}
