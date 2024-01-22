package com.library.modules.bs.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.library.core.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO extends BaseEntity {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 图书编号
     */
    private String bookNo;
    /**
     * 图书编号集合，每个图书之间用英文逗号隔开
     */
    private String bookNos;
    private List<String> bookNoList;
    /**
     * 书名
     */
    private String name;
    /**
     * 状态:0-上架；1-下架
     */
    private Integer state;
    /**
     * 作者
     */
    private String author;


    
    private String nameOrAuthor;

    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.WriteNullStringAsEmpty);
    }
}