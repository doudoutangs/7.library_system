package com.library.modules.bs.service;

import com.library.core.entity.ProcessResult;
import com.library.modules.bs.model.po.BookWarn;
import com.library.modules.bs.model.BookWarnDTO;
import com.library.modules.bs.model.BookWarnVO;

import java.util.List;

public interface BookWarnService {

    ProcessResult batchAdd(List<BookWarn> param);

    ProcessResult batchUpdate(List<BookWarn> param);

    List<BookWarnVO> getAllWithPage(BookWarnDTO param);

    BookWarnVO getById(Integer id);

    List<BookWarnVO> queryByBookWarnIdList(List<Integer> bookWarnIdList);
}
