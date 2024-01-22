package com.library.modules.bs.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.library.modules.bs.mapper.BookStatsMapper;
import com.library.modules.bs.model.po.Stats;
import com.library.modules.bs.model.StatsReq;
import com.library.modules.bs.service.BookStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: QQ:553039957
 * @Date: 2023/9/26 19:44
 * @Description: 1. gitcode主页： https://gitcode.net/tbb414 （推荐）
 * 2. github主页：https://github.com/doudoutangs
 */
@Service
public class BookStatsServiceImpl implements BookStatsService {

    @Autowired
    BookStatsMapper bookStatsMapper;

    @Override
    public List<String> yearStats() {
        return bookStatsMapper.yearStats();
    }

    @Override
    public JSONObject operateStats(Integer operateType, List<String> yearList) {
        List<List<String>> result = new ArrayList<List<String>>();
        List<String> product = new ArrayList<>();
        product.add("product");
        product.addAll(yearList);
        result.add(product);

        StatsReq param = new StatsReq();
        List<Stats> newList = new ArrayList<>();
        for (String year : yearList) {
            param.setYear(year);
            param.setOperateType(operateType);
            List<Stats> stats = bookStatsMapper.operateStats(param);
            newList.addAll(stats);
        }
        Map<String, List<Stats>> collect = newList.stream().collect(Collectors.groupingBy(Stats::getName,LinkedHashMap::new,Collectors.toList()));
        Set<String> keySet = collect.keySet();
        Iterator<String> iterator = keySet.iterator();

        while (iterator.hasNext()) {
            String cityName = iterator.next();
            List<String> cityYearStatisList = collect.get(cityName).stream().map(Stats::getValue).collect(Collectors.toList());
            List<String> t = new ArrayList<String>();
            t.add(cityName);
            t.addAll(cityYearStatisList);
            result.add(t);
        }
        JSONObject data = new JSONObject();
        data.put("statis", result);
        return data;
    }

    @Override
    public List<Stats> bookCategory(StatsReq req) {
        return bookStatsMapper.bookCategory(req);
    }

    @Override
    public List<List<String>> hotBookStats() {
        List<Stats> list = bookStatsMapper.hotBookStats();
        List<List<String>> result = CollUtil.newArrayList();
        List<String> firstItem = CollUtil.newArrayList();

        firstItem.add("score");
        firstItem.add("hot");
        firstItem.add("product");
        result.add(firstItem);

        for (Stats stats : list) {
            List<String> item = CollUtil.newArrayList();
            item.add("20");
            item.add(stats.getValue());
            item.add(stats.getName());
            result.add(item);
        }
        return result;
    }


    @Override
    public List<List<String>> upDownStats() {
//                          ['product', '2020', '2021', '2022', '2023'],
//                        ['Matcha Latte', 41.1, 30.4, 65.1, 53.3],
//                        ['Milk Tea', 86.5, 92.1, 85.7, 83.1],
        List<Stats> upStats = bookStatsMapper.monthUpStats();
        List<Stats> downStats = bookStatsMapper.monthDownStats();

        Set<String> upDates = upStats.stream().map(Stats::getName).collect(Collectors.toSet());
        Set<String> downDates = downStats.stream().map(Stats::getName).collect(Collectors.toSet());
        List<String> dateList = CollUtil.newArrayList();
        dateList.addAll(upDates);
        dateList.addAll(downDates);

        dateList = dateList.stream().distinct().sorted().collect(Collectors.toList());


        for (String date : dateList) {
            if (!upDates.contains(date)) {
                upStats.add(Stats.builder().name(date).value("0").build());
            }
            if (CollectionUtil.isEmpty(downStats)) {
                downStats.add(Stats.builder().name(date).value("0").build());
            } else {
                if (!downDates.contains(date)) {
                    downStats.add(Stats.builder().name(date).value("0").build());
                }

            }
        }



        List<String> line1 = CollUtil.newArrayList();
        line1.add("product");
        line1.addAll(dateList);
        List<List<String>> result = CollUtil.newArrayList();

        List<String> line2 = CollUtil.newArrayList();
        line2.add("上架数量");
        line2.addAll(upStats.stream().sorted(Comparator.comparing(Stats::getName)).map(Stats::getValue).collect(Collectors.toList()));

        List<String> line3 = CollUtil.newArrayList();
        line3.add("下架数量");
        line3.addAll(downStats.stream().sorted(Comparator.comparing(Stats::getName)).map(Stats::getValue).collect(Collectors.toList()));

        result.add(line1);
        result.add(line2);
        result.add(line3);
        return result;
    }

}
