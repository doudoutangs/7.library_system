package com.library.modules.sys.mapper;

import com.library.core.mapper.MyMapper;
import com.library.modules.sys.model.SysDeptUser;
import org.apache.ibatis.annotations.Param;

public interface SysDeptUserMapper extends MyMapper<SysDeptUser> {
    void clearMasterByDept(@Param("userId") Integer userId, @Param("deptId") Integer deptId);

    void setMasterUserByDept(@Param("userId") Integer userId, @Param("deptId") Integer deptId);
}