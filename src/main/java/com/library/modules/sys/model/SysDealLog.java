package com.library.modules.sys.model;


import com.library.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data
@Table(name = "sys_deal_log")
public class SysDealLog extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户操作
     */
    private String operation;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 执行时长(毫秒)
     */
    private Long time;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

}