package com.library.modules.bs.service;

import com.library.core.entity.ProcessResult;
import com.library.modules.bs.model.UserAccountRecordDTO;
import com.library.modules.bs.model.UserAccountRecordVO;
import com.library.modules.bs.model.po.UserAccountRecord;

import java.util.List;

public interface UserAccountService {

    List<UserAccountRecordVO> getAllWithPage(UserAccountRecordDTO param);

    List<UserAccountRecord> getAll();

    int add(UserAccountRecord param);

    ProcessResult recharge(UserAccountRecord param);

    UserAccountRecord getById(Integer id);

    ProcessResult deleteById(Integer id);


}
