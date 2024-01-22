package com.library.modules.bs.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

/**
 * Created by sugar on 2021/12/7.
 */
@Data
public class StatsReq {
    String year;
    /**
     * 操作类型:0-借；1-还
     */
    private Integer operateType;
    @Override
    public String toString()
    {
        return JSON.toJSONString(this, SerializerFeature.WriteNullStringAsEmpty);
    }
}
