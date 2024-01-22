package com.library.modules.bs.model.po;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by sugar on 2021/12/7.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stats {
    String year;
    String name;
    String value;
    @Override
    public String toString()
    {
        return JSON.toJSONString(this, SerializerFeature.WriteNullStringAsEmpty);
    }
}
