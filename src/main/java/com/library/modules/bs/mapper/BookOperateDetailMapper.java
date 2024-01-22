package com.library.modules.bs.mapper;

import com.library.core.mapper.MyMapper;
import com.library.modules.bs.model.po.BookOperateDetail;

import java.util.List;

public interface BookOperateDetailMapper extends MyMapper<BookOperateDetail> {
    List<BookOperateDetail> getByOperateNo(Integer operateNo);

    List<BookOperateDetail> getByBookIdList(List<Integer> bookIdList);
}