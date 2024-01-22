package com.library.modules.bs.mapper;

import com.library.core.mapper.MyMapper;
import com.library.modules.bs.model.BookWarnDTO;
import com.library.modules.bs.model.BookWarnVO;
import com.library.modules.bs.model.UserAccountRecordDTO;
import com.library.modules.bs.model.UserAccountRecordVO;
import com.library.modules.bs.model.po.UserAccountRecord;

import java.util.List;

public interface UserAccountRecordMapper extends MyMapper<UserAccountRecord> {
    List<UserAccountRecordVO> queryPage(UserAccountRecordDTO param);

}