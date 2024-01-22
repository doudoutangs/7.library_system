package com.library.modules.bs.service;

import com.library.core.entity.ProcessResult;
import com.library.modules.bs.model.po.Book;
import com.library.modules.bs.model.BookDTO;

import java.util.List;

/**
 *
 **/
public interface BookService {

    List<Book> getAllWithPage(BookDTO param);

    List<Book> getAll();

    ProcessResult saveOrUpdate(Book param);

    Book getById(Integer id);

    ProcessResult deleteById(Integer id);

    ProcessResult batchDelete(Integer[] list);

}
