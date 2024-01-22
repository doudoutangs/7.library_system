package com.library.modules.sys.mapper;


import com.library.core.mapper.MyMapper;
import com.library.modules.sys.model.SysUserRole;

import java.util.List;

public interface SysUserRoleMapper extends MyMapper<SysUserRole> {
    Integer batchDelete(List<Integer> list);

}