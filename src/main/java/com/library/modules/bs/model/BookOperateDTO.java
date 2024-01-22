package com.library.modules.bs.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.library.core.entity.BaseEntity;
import lombok.Data;

@Data
public class BookOperateDTO extends BaseEntity {


    /**
     * 图书id
     */
    private Integer[] bookIds;
    /**
     * 会员id
     */
    private Integer  vipId;
    /**
     * 操作类型:0-借；1-还
     */
    private Integer operateType;
    /**
     * 操作数量
     */
    private Integer operateCount;
    /**
     * 操作人
     */
    private Integer operateId;

    /**
     * 操作年份
     */
    private Integer operateYear;

    /**
     * 操作年份
     */
    private Integer operateMonth;
    /**
     * 操作年月
     */
    private String operateDate;
    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.WriteNullStringAsEmpty);
    }
}