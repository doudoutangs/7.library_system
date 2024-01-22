package com.library.modules.bs.mapper;

import com.library.core.mapper.MyMapper;
import com.library.modules.bs.model.po.MessageBoard;

import java.util.List;

public interface MessageBoardMapper extends MyMapper<MessageBoard> {
    Integer batchDelete(List<Integer> list);

}