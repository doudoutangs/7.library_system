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
@Table(name = "b_book")
public class Book extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 图书编号
     */
    @Column(name = "book_no")
    private String bookNo;

    /**
     * 书名
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    @Column(name = "release_date")
    private String releaseDate;

    /**
     * 出版社
     */
    private String press;
    @Transient
    private String pressName;

    /**
     * category
     */
    private String category;
    @Transient
    private String categoryName;

    /**
     * 位置
     */
    private String position;
    @Transient
    private String positionName;
    private String introduction;
    private Double price;
    private Integer total;
    /**
     * 状态:0-上架；1-下架
     */
    private Integer state;
    @Column(name = "up_date")
    private Date upDate;
    @Column(name = "down_date")
    private Date downDate;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;
    @Override
    public String toString()
    {
        return JSON.toJSONString(this, SerializerFeature.WriteNullStringAsEmpty);
    }
}