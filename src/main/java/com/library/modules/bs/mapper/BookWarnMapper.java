package com.library.modules.bs.mapper;

import com.library.core.mapper.MyMapper;
import com.library.modules.bs.model.po.BookWarn;
import com.library.modules.bs.model.BookWarnDTO;
import com.library.modules.bs.model.BookWarnVO;

import java.util.List;

public interface BookWarnMapper extends MyMapper<BookWarn> {
    List<BookWarnVO> queryPage(BookWarnDTO param);

    List<BookWarnVO> queryByBookWarnIdList(List<Integer> bookWarnIdList);

    BookWarnVO getById(Integer id);

}