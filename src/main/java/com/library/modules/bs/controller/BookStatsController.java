
package com.library.modules.bs.controller;

import com.alibaba.fastjson.JSONObject;
import com.library.core.entity.ProcessResult;
import com.library.modules.bs.model.po.Stats;
import com.library.modules.bs.model.StatsReq;
import com.library.modules.bs.service.BookStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author: QQ:553039957
 * @Date: 2023/9/27 10:01
 * @Description:
 * 1. gitcode主页： https://gitcode.net/tbb414 （推荐）
 * 2. github主页：https://github.com/doudoutangs
 */
@RestController
@RequestMapping("/bs/bookStats")
public class BookStatsController {

    @Autowired
    private BookStatsService bookStatsService;
    static String pathPrefix = "/modules/bs/bookStats/";

    @GetMapping("/operateStats")
    public ModelAndView operateStats() {
        ModelAndView modelAndView = new ModelAndView(pathPrefix + "operateStats");
        return modelAndView;
    }
    @GetMapping("/bookStats")
    public ModelAndView bookStats() {
        ModelAndView modelAndView = new ModelAndView(pathPrefix + "bookStats");
        return modelAndView;
    }
    @GetMapping("/libraryStats")
    public ModelAndView libraryStats() {
        ModelAndView modelAndView = new ModelAndView(pathPrefix + "libraryStats");
        return modelAndView;
    }
    @GetMapping("/yearStats")
    public ProcessResult yearStats() {
        List<String> data = bookStatsService.yearStats();
        return new ProcessResult(data);
    }

    @PostMapping("/operateStats/{operateType}")
    public ProcessResult operateStats(@PathVariable Integer operateType,@RequestBody List<String> yearList) {
        JSONObject data = bookStatsService.operateStats(operateType,yearList);
        return new ProcessResult(data);
    }

    @GetMapping("/bookCategory")
    public ProcessResult bookCategory(StatsReq req) {
        List<Stats> list = bookStatsService.bookCategory(req);
        return new ProcessResult(list);
    }

    @PostMapping("/hotBookStats")
    public ProcessResult hotBookStats() {
        List<List<String>> list = bookStatsService.hotBookStats();

        return new ProcessResult(list);
    }

    @GetMapping("/upDownStats")
    public ProcessResult upDownStats() {
        List<List<String>> list = bookStatsService.upDownStats();
        return new ProcessResult(list);
    }
}
