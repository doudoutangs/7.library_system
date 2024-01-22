package com.library.modules.sys.mapper;

import com.library.core.mapper.MyMapper;
import com.library.modules.sys.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysUserMapper extends MyMapper<SysUser> {
    List<SysUser> getIsNotOwner(@Param("roleId") Integer roleId, @Param("keyword") String keyword);

    List<SysUser> getIsOwner(@Param("roleId") Integer roleId, @Param("keyword") String keyword);

    SysUser getMasterUserByDept(Integer deptId);

    List<SysUser> isUnAllotUserList(Integer deptId);

    List<SysUser> isAllotUserList(Integer deptId);

    List<SysUser> selectUserByDeptId(Map<String, Object> map);

    List<Integer> queryUserIdByKeyword(String keyword);

    List<SysUser> getVipList();

    SysUser getById(Integer id);

    List<SysUser> selectUserByRoleId(Integer roleId);

    List<SysUser> getUserListByRoleId(@Param("roleId") Integer roleId, @Param("keyword") String keyword);

    Integer batchDelete(List<Integer> list);

    SysUser getUserByUserName(String userName);

    int update(SysUser param);

}