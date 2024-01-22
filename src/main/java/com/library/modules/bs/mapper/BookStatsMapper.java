package com.library.modules.bs.mapper;

import com.library.core.mapper.MyMapper;
import com.library.modules.bs.model.po.Stats;
import com.library.modules.bs.model.StatsReq;

import java.util.List;

public interface BookStatsMapper extends MyMapper<Stats> {
    List<String> yearStats();

    List<Stats> operateStats(StatsReq req);

    List<Stats> bookCategory(StatsReq req);

    List<Stats> hotBookStats();

    List<Stats> monthUpStats();
    List<Stats> monthDownStats();


}