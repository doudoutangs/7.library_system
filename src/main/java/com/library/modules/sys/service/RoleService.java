package com.library.modules.sys.service;


import com.library.core.entity.ProcessResult;
import com.library.modules.sys.model.SysRole;
import com.library.modules.sys.vo.SysRoleSelectVo;

import java.util.List;

public interface RoleService {

    List<Integer> findByUserId(Integer id);

    List<SysRole> getAll(SysRole sysRole, String keyword);

    ProcessResult batchDelete(Integer[] ids);

    ProcessResult saveOrUpdate(SysRole sysUser);

    SysRole getById(Integer id);

    ProcessResult deleteById(Integer id);

    List<SysRoleSelectVo> getAllRoles();
}