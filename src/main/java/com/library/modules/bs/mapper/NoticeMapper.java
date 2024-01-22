package com.library.modules.bs.mapper;

import com.library.core.mapper.MyMapper;
import com.library.modules.bs.model.po.Notice;

import java.util.List;

public interface NoticeMapper extends MyMapper<Notice> {
    Integer batchDelete(List<Integer> list);
}