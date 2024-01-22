package com.library.modules.bs.service.impl;

import com.github.pagehelper.PageHelper;
import com.library.core.entity.ProcessResult;
import com.library.modules.bs.mapper.BookWarnMapper;
import com.library.modules.bs.model.po.BookWarn;
import com.library.modules.bs.model.BookWarnDTO;
import com.library.modules.bs.model.BookWarnVO;
import com.library.modules.bs.service.BookWarnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sugar on 2021/11/25.
 */
@Service
public class BookWarnServiceImpl implements BookWarnService {

    @Autowired
    BookWarnMapper bookWarnMapper;

    @Override
    public ProcessResult batchAdd(List<BookWarn> param) {
        int addResult = bookWarnMapper.insertList(param);
        return new ProcessResult(addResult);
    }

    @Override
    public ProcessResult batchUpdate(List<BookWarn> param) {
        int updateResult = bookWarnMapper.updateList(param);
        return new ProcessResult(updateResult);
    }

    @Override
    public List<BookWarnVO> getAllWithPage(BookWarnDTO param) {
        PageHelper.startPage(param.getPage(), param.getLimit());

        List<BookWarnVO> list = bookWarnMapper.queryPage(param);
        return list;
    }

    @Override
    public BookWarnVO getById(Integer id) {
        return bookWarnMapper.getById(id);
    }

    @Override
    public List<BookWarnVO> queryByBookWarnIdList(List<Integer> bookWarnIdList) {
        return bookWarnMapper.queryByBookWarnIdList(bookWarnIdList);
    }
}
