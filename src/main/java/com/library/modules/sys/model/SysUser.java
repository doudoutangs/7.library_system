package com.library.modules.sys.model;


import com.library.core.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "sys_user")
public class SysUser extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    @Column(name = "real_name")
    private String realName;

    /**
     * 0启用，1停用
     */
    private Integer status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    private String email;

    private String phone;

    private Integer sex;

    private String avatar;

    private String city;//城市编码

    @Column(name = "city_name")
    private String cityName;//城市名称

    private String birth;

    private String address;

    private String isSysUser;//是否系统用户 0 是 1 否 系统用户不可删除

    private Integer deptId; //部门id
    private Double amount; //账户余额
    @Column(name = "card_type")
    private String cardType; //图书证类型
    @Transient
    private String cardTypeName; //图书证类型
    @Transient
    private Integer ismaster;//0 是管理员  1不是管理员

    @Transient
   /* @ManyToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = { @JoinColumn(name = "roleId") })*/
    private Set<SysRole> roles;

    @Transient
    private Integer[] roleIds;
    @Transient
    private Integer roleId;

    @Transient
    private String verify;  //注册时，校验的验证码


}