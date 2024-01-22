package com.library.modules.bs.mapper;

import com.library.core.mapper.MyMapper;
import com.library.modules.bs.model.po.BookOperate;
import com.library.modules.bs.model.BookOperateDTO;

import java.util.List;

public interface BookOperateMapper extends MyMapper<BookOperate> {

    List<BookOperate> query(BookOperateDTO param);
}