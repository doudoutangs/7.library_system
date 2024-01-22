package com.library.modules.bs.model;

import com.library.core.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAccountRecordVO {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 会员id
     */
    @Column(name = "vip_id")
    private Integer vipId;
    private String vipName;

    /**
     * 变动类型：0-增加，1-扣减
     */
    @Column(name = "operate_type")
    private Integer operateType;
    /**
     * 金额
     */
    @Column(name = "amount")
    private Double amount;
    /**
     * 变动原因
     */
    @Column(name = "change_reason")
    private String changeReason;

    /**
     * 操作人
     */
    @Column(name = "operate_id")
    private Integer operateId;
    private String operateName;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

}