package com.library.modules.sys.mapper;


import com.library.core.mapper.MyMapper;
import com.library.modules.sys.model.SysMenuRole;

import java.util.List;

public interface SysMenuRoleMapper extends MyMapper<SysMenuRole> {
    int deleteByRoleId(Integer roleId);

    Integer batchDelete(List<Integer> list);

}