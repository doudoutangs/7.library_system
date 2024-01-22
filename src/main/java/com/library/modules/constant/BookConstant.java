package com.library.modules.constant;

/**
 * Created by sugar on 2021/12/3.
 */
public interface BookConstant {
    /**
     * 操作类型:0-借；1-还;2-损坏
     */
    Integer OPERATE_TYPE_LEND = 0;
    Integer OPERATE_TYPE_REVERT = 1;
    Integer OPERATE_TYPE_LOSS = 2;

    /**
     * 状态:0-上架；1-下架
     */
    Integer BOOK_STATE_UP = 0;
    Integer BOOK_STATE_DOWN = 1;
}
