package com.library.modules.bs.model.po;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.library.core.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "b_book_operate")
public class BookOperate extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 会员id
     */
    @Column(name = "vip_id")
    private Integer vipId;
    @Transient
    private String vipName;
    /**
     * 操作人id
     */
    @Column(name = "operate_id")
    private Integer operateId;
    @Transient
    private String operateName;
    /**
     * 操作类型:0-借；1-还
     */
    @Column(name = "operate_type")
    private Integer operateType;

    @Column(name = "book_name")
    private String bookName;
    /**
     * 操作书的数量
     */
    @Column(name = "operate_count")
    private Integer operateCount;

    /**
     * 操作年份
     */
    @Column(name = "operate_year")
    private Integer operateYear;

    /**
     * 操作月份
     */
    @Column(name = "operate_month")
    private Integer operateMonth;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;


    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.WriteNullStringAsEmpty);
    }
}