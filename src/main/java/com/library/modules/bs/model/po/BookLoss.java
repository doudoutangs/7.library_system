package com.library.modules.bs.model.po;

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
@Table(name = "b_book_loss")
public class BookLoss {
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

    /**
     * 图书id
     */
    @Column(name = "book_id")
    private Integer bookId;

    /**
     * 借出时间
     */
    @Column(name = "lend_time")
    private String lendTime;

    /**
     * 损坏原因
     */
    @Column(name = "loss_reason")
    private String lossReason;

    /**
     * 损坏程度
     */
    @Column(name = "loss_degree")
    private Integer lossDegree;

    /**
     * 赔偿金额
     */
    @Column(name = "compensate_amount")
    private String compensateAmount;

    /**
     * 定损操作人
     */
    @Column(name = "loss_operate_id")
    private Integer lossOperateId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

}