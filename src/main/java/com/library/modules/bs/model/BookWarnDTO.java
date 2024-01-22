package com.library.modules.bs.model;

import com.library.core.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookWarnDTO extends BaseEntity {
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
    private List<Integer> vipIdList;
    /**
     * 用户手机号
     */
    private String phone;
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

    /**
     * 还书操作人
     */
    @Column(name = "revert_operate_id")
    private Integer revertOperateId;

    /**
     * 延期操作人
     */
    @Column(name = "extend_operate_id")
    private Integer extendOperateId;

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