package com.library.modules.constant;

/**
 * Created by sugar on 2021/12/6.
 */
public interface CommonConstant {
    int OPERATE_SUCCESS = 1;
    //用户状态:0启用，1停用
    int USER_STATUS_ON = 0;
    int USER_STATUS_OFF = 1;

    //是否系统用户 0 是 1 否 系统用户不可删除
    String USER_ADMIN_Y = "1";
    String USER_ADMIN_N = "0";
    //普通会员
    int USER_ROLE_VIP = 2;

}
