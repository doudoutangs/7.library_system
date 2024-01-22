package com.library.modules.bs.service;

import com.library.core.entity.ProcessResult;
import com.library.modules.bs.model.po.Notice;

import java.util.List;

public interface NoticeService {

    List<Notice> getAllWithPage(Notice param);

    List<Notice> getAll();

    ProcessResult saveOrUpdate(Notice param);

    Notice getById(Integer id);

    ProcessResult deleteById(Integer id);

    ProcessResult batchDelete(Integer[] list);

}
