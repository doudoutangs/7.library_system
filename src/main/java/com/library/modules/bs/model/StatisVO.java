package com.library.modules.bs.model;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * 统计结果
 * Created by sugar on 2022/4/11.
 */
@Data
public class StatisVO {
    private String name;
    private Long total;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
