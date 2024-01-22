package com.library.modules.sys.service;


import com.library.core.entity.ProcessResult;
import com.library.modules.sys.model.SysUser;

import java.util.List;

public interface UserService {
    List<SysUser> getAll(SysUser sysUser, String keyword);

    List<SysUser> getVipList();

    List<SysUser> getByDeptId(Integer deptId);

    List<Integer> queryUserIdByKeyword(String keyword);

    SysUser getById(Integer id);

    ProcessResult deleteById(Integer id);

    ProcessResult save(SysUser sysUser);

    SysUser getUser(SysUser sysUser);

//    ProcessResult showVerify(String email);

    ProcessResult saveUser(SysUser vo);

    ProcessResult saveOrUpdate(SysUser sysUser);

    ProcessResult batchDelete(Integer[] list);

    SysUser findByUserName(String username);

    ProcessResult saveAvatar(SysUser sysUser);

    List<SysUser> selectUserListByRoleId(Integer roleId);

    List<SysUser> getUserListByRoleId(Integer roleId, String keyword);
}