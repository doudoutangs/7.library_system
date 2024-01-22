package com.library.modules.sys.mapper;


import com.library.core.mapper.MyMapper;
import com.library.modules.sys.model.SysRole;

import java.util.List;

public interface SysRoleMapper extends MyMapper<SysRole> {
    List<SysRole> findByUserId(Integer userId);
    Integer batchDelete(List<Integer> list);

}