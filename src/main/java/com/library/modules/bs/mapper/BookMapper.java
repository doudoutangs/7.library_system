package com.library.modules.bs.mapper;

import com.library.core.mapper.MyMapper;
import com.library.modules.bs.model.po.Book;
import com.library.modules.bs.model.BookDTO;

import java.util.List;

public interface BookMapper extends MyMapper<Book> {
    Integer batchDelete(List<Integer> list);

    List<Book> getByIdList(List<Integer> idList);

    void batchUpdate(List<Book> list);

    List<Book> query(BookDTO param);

}