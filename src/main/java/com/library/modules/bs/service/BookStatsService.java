package com.library.modules.bs.service;

import com.alibaba.fastjson.JSONObject;
import com.library.modules.bs.model.po.Stats;
import com.library.modules.bs.model.StatsReq;

import java.util.List;

public interface BookStatsService {
    List<String> yearStats();
    JSONObject operateStats(Integer operateType,List<String> yearList);

    List<Stats> bookCategory(StatsReq req);

    List<List<String>> hotBookStats();


    List<List<String>> upDownStats();


}
