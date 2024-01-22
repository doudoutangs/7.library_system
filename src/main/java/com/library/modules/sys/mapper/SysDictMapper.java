package com.library.modules.sys.mapper;

import com.library.core.mapper.MyMapper;
import com.library.modules.sys.model.SysDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDictMapper extends MyMapper<SysDict> {
    List<SysDict> getDictByDictValue(String dictValue);

    SysDict getDictByValueAndLevel(@Param("dictValue") String dictValue, @Param("dictLevel") String dictLevel);

    Integer batchDelete(List<Integer> list);

}