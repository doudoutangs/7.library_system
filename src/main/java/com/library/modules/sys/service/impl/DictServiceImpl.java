package com.library.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.library.core.entity.ProcessResult;
import com.library.modules.constant.CommonConstant;
import com.library.modules.sys.mapper.SysDictMapper;
import com.library.modules.sys.model.SysDict;
import com.library.modules.sys.service.DictService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: QQ:553039957
 * @Date: 2023/9/28 11:31
 * @Description: 1. gitcode主页： https://gitcode.net/tbb414 （推荐）
 * 2. github主页：https://github.com/doudoutangs
 */
@Service
public class DictServiceImpl implements DictService {

    @Autowired
    SysDictMapper dictMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProcessResult saveOrUpdate(SysDict dict) {
        if (dict.getId() != null) {//update
            if (dict.getParentId().equals(dict.getId())) {
                return new ProcessResult(ProcessResult.ERROR, "不能设置自己为字典类型");
            }
            return update(dict);
        } else {
            //insert
            return save(dict);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProcessResult deleteById(Integer id) {
        SysDict sysDict = dictMapper.selectByPrimaryKey(id);
        String dictLevel = sysDict.getDictLevel();
        if (!dictLevel.equals(sysDict.getDictValue())) {
            //刪除沒有下级的字典
            int result = dictMapper.deleteByPrimaryKey(id);
            if (result == CommonConstant.OPERATE_SUCCESS) {
                return new ProcessResult();
            } else {
                return new ProcessResult(ProcessResult.ERROR, "操作失败");
            }
        } else {
            //1.删除有子节点的字典
            Example param = new Example(SysDict.class);
            param.createCriteria().andEqualTo("dictLevel", dictLevel);
            List<SysDict> dicts = dictMapper.selectByExample(param);
            List<Integer> idList = dicts.stream().map(SysDict::getId).collect(Collectors.toList());
            int result = dictMapper.batchDelete(idList);
            if (result == idList.size()) {
                return new ProcessResult();
            }
            return new ProcessResult(ProcessResult.ERROR, "操作失败");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProcessResult batchDelete(Integer[] list) {
        Integer result = dictMapper.batchDelete(Arrays.asList(list));
        if (result == list.length) {
            return new ProcessResult();
        } else {
            return new ProcessResult(ProcessResult.ERROR, "操作失败");
        }
    }

    @Override
    public List<SysDict> getAllWithPage(SysDict dict) {
        PageHelper.startPage(dict.getPage(), dict.getLimit());
        Example example = new Example(SysDict.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(dict.getDictName())) {
            criteria.andLike("dictName", "%" + dict.getDictName() + "%");
        }
        if (StringUtils.isNotBlank(dict.getDictLevel())) {
            criteria.andLike("dictLevel", "%" + dict.getDictLevel() + "%");
        }
        Integer parentId = dict.getParentId();
        if (null != parentId) {
            criteria.andEqualTo("parentId", parentId);
        }
        example.orderBy("dictSort").asc();
        return dictMapper.selectByExample(example);
    }

    @Override
    public List<SysDict> getAllDict() {
        Example example = new Example(SysDict.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", 0);
        return dictMapper.selectByExample(example);
    }

    @Override
    public List<SysDict> listRootType() {
        Example example = new Example(SysDict.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId", -1);
        return dictMapper.selectByExample(example);
    }

    @Override
    public List<SysDict> getDictByDictValue(String dictValue) {
        return dictMapper.getDictByDictValue(dictValue);
    }

    private ProcessResult update(SysDict dict) {
        int result = dictMapper.updateByPrimaryKeySelective(dict);
        if (result == CommonConstant.OPERATE_SUCCESS) {
            return new ProcessResult();
        } else {
            return new ProcessResult(ProcessResult.ERROR, "操作失败");
        }
    }

    private ProcessResult save(SysDict dict) {
        dict.setCreateTime(new Date());
        Integer parentId = dict.getParentId();
        String dictValue = dict.getDictValue();

        if (parentId == -1) {
            dict.setDictLevel(dictValue);
        } else {
            SysDict d = new SysDict();
            d.setId(parentId);
            SysDict sysDict = dictMapper.selectOne(d);
            dict.setDictLevel(sysDict.getDictLevel());
        }
        //保证根节点dictValue的唯一性
        SysDict d = new SysDict();
        d.setParentId(parentId);
        d.setDictValue(dictValue);
        SysDict sysDict = dictMapper.selectOne(d);
        if (sysDict != null) {
            return new ProcessResult(ProcessResult.ERROR, "字典类型为:" + dictValue + "节点已经存在");
        }
        int result = dictMapper.insert(dict);
        if (result == CommonConstant.OPERATE_SUCCESS) {
            return new ProcessResult();
        } else {
            return new ProcessResult(ProcessResult.ERROR, "操作失败");
        }
    }


    @Override
    public SysDict getById(Integer id) {
        return dictMapper.selectByPrimaryKey(id);
    }


}
