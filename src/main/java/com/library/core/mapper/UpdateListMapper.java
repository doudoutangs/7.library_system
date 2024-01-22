package com.library.core.mapper;

import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

@RegisterMapper
public interface UpdateListMapper<T> {

    /** 根据主键批量更新实体中不是null的属性值，支持联合主键
     *
     * @param updateList 参数
     * @return int
     */
    @UpdateProvider(type = UpdateListProvider.class,method = "dynamicSQL")
    int updateList(List<T> updateList);
}
