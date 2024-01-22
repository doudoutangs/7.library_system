package com.library.modules.bs.service;

import com.library.core.entity.ProcessResult;
import com.library.modules.bs.model.po.MessageBoard;

import java.util.List;

public interface MessageBoardService {

    List<MessageBoard> getAllWithPage(MessageBoard param);

    List<MessageBoard> getAll();

    ProcessResult saveOrUpdate(MessageBoard param);

    MessageBoard getById(Integer id);

    ProcessResult deleteById(Integer id);

    ProcessResult batchDelete(Integer[] list);

}
