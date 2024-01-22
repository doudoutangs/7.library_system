package com.library.modules.bs.model;

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
@Table(name = "b_book_warn")
public class BookWarnVO {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 图书操作序号
     */
    @Column(name = "book_operate_no")
    private Integer bookOperateNo;
    /**
     * 会员id
     */
    @Column(name = "vip_id")
    private Integer vipId;
    @Column(name = "vip_name")
    private String vipName;
    @Column(name = "vip_phone")
    private String vipPhone;

    /**
     * 图书id
     */
    @Column(name = "book_id")
    private Integer bookId;
    @Column(name = "book_name")
    private String bookName;
    /**
     * 借出时间
     */
    @Column(name = "lend_time")
    private String lendTime;

    /**
     * 应归还时间1
     */
    @Column(name = "revert_time")
    private String revertTime;

    /**
     * 应归还时间2
     */
    @Column(name = "revert_time2")
    private String revertTime2;

    /**
     * 实际归还时间
     */
    @Column(name = "real_revert_time")
    private String realRevertTime;

    /**
     * 逾期天数
     */
    @Column(name = "over_day")
    private Integer overDay;

    /**
     * 违约金
     */
    @Column(name = "damages_amount")
    private String damagesAmount;

    /**
     * 状态：0-借出；1-归还；2-损坏
     */
    private Integer status;

    /**
     * 借书操作人
     */
    @Column(name = "lend_operate_id")
    private Integer lendOperateId;
    @Column(name = "lend_operate_name")
    private String lendOperateName;
    /**
     * 还书操作人
     */
    @Column(name = "revert_operate_id")
    private Integer revertOperateId;
    @Column(name = "revert_operate_name")
    private String revertOperateName;
    /**
     * 延期操作人
     */
    @Column(name = "extend_operate_id")
    private Integer extendOperateId;
    @Column(name = "extend_operate_name")
    private String extendOperateName;
    /**
     * 定损操作人
     */
    @Column(name = "loss_operate_id")
    private Integer lossOperateId;
    @Column(name = "loss_operate_name")
    private String lossOperateName;

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