package com.library.modules.bs.service;

import com.library.core.entity.ProcessResult;
import com.library.modules.bs.model.po.BookOperate;
import com.library.modules.bs.model.BookOperateDTO;
import com.library.modules.bs.model.po.BookOperateDetail;

import java.util.List;

public interface BookOperateService {

    List<BookOperate> getAllWithPage(BookOperateDTO param);

    List<BookOperate> getAll();

    ProcessResult saveOrUpdate(BookOperate param);
    ProcessResult batchAdd(BookOperateDTO param);


    List<BookOperateDetail> getById(Integer id);

    ProcessResult deleteById(String id);


}
