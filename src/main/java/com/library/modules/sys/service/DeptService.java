package com.library.modules.sys.service;

import com.library.core.entity.ProcessResult;
import com.library.modules.sys.model.SysDept;
import com.library.modules.sys.vo.SysDeptSelectVo;
import com.library.modules.sys.vo.SysDeptVo;

import java.util.List;

public interface DeptService {

    List<SysDept> getAll();

    List<SysDeptVo> treeList();

    List<SysDept> getChildDeptList(Integer id);

    List<SysDeptSelectVo> treeSelectList();

    SysDept getDeptById(Integer id);

    ProcessResult saveOrUpdate(SysDept sysDept) throws Exception;

    ProcessResult deleteById(Integer id);

}