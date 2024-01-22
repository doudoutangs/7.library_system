//package com.library.core.mapper;
//
//import org.apache.ibatis.annotations.UpdateProvider;
//import tk.mybatis.mapper.annotation.RegisterMapper;
//
//import java.util.List;
//
//@RegisterMapper
//public interface UpdateListAllMapper<T> {
//
//    /**
//     * 根据主键批量更新实体中所有属性值，支持联合主键
//     *
//     * @param updateList 参数
//     * @return int
//     */
//    @UpdateProvider(type = UpdateListProvider.class,method = "dynamicSQL")
//    int updateListAll(List<T> updateList);
//}
