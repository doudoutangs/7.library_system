package com.library.modules.bs.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.library.core.entity.ProcessResult;
import com.library.core.util.CalcTool;
import com.library.modules.bs.mapper.UserAccountRecordMapper;
import com.library.modules.bs.model.BookWarnVO;
import com.library.modules.bs.model.UserAccountRecordDTO;
import com.library.modules.bs.model.UserAccountRecordVO;
import com.library.modules.bs.model.po.UserAccountRecord;
import com.library.modules.bs.service.UserAccountService;
import com.library.modules.constant.AccountConstant;
import com.library.modules.constant.CommonConstant;
import com.library.modules.sys.mapper.SysUserMapper;
import com.library.modules.sys.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * Created by sugar on 2021/11/25.
 */
@Slf4j
@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    UserAccountRecordMapper accountRecordMapper;
    @Autowired
    SysUserMapper sysUserMapper;
    @Override
    public List<UserAccountRecordVO> getAllWithPage(UserAccountRecordDTO param) {
        PageHelper.startPage(param.getPage(), param.getLimit());
        List<UserAccountRecordVO> list = accountRecordMapper.queryPage(param);
        return list;
    }

    @Override
    public List<UserAccountRecord> getAll() {
        return accountRecordMapper.selectAll();
    }

    @Override
    public int add(UserAccountRecord param) {
        return accountRecordMapper.insert(param);
    }

    @Override
    public ProcessResult recharge(UserAccountRecord param) {
        log.info("充值，入参：{}", JSONObject.toJSONString(param));
        Integer vipId = param.getVipId();

        int addRecord = add(param);
        log.info("增加充值记录，结果：{}", addRecord);
        SysUser vipUser = sysUserMapper.getById(vipId);
        Double lastAmount = vipUser.getAmount() + param.getAmount();
        SysUser user = SysUser.builder()
                .id(vipId)
                .amount(lastAmount)
                .updateTime(new Date())
                .build();
        log.info("更新，余额，入参：{}", JSONObject.toJSONString(user));
        int updateAccountResult = sysUserMapper.update(user);
        log.info("更新，余额，结果：{}",updateAccountResult);
        return new ProcessResult();
    }

    @Override
    public UserAccountRecord getById(Integer id) {
        return accountRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public ProcessResult deleteById(Integer id) {
        Integer result = 0;

        result = accountRecordMapper.deleteByPrimaryKey(id);
        if (result == CommonConstant.OPERATE_SUCCESS) {
            return new ProcessResult();
        } else {
            return new ProcessResult(ProcessResult.ERROR, "操作失败");
        }
    }

}
