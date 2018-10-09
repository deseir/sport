package com.moerlong.carloan.modular.paybackMgr.entity;

import java.util.Date;

/**
 * 提前还款审批记录表
 */
public class RepaymentApporveRecord {
    /**
     * 主键
     */
    private Long id;

    /**
     * 还款总表id
     */
    private Long repaymentId;

    /**
     * 操作员id
     */
    private Long operatorId;

    /**
     * 操作员姓名
     */
    private String operatorName;

    /**
     * 操作时间
     */
    private Date operatorTime;

    /**
     * 审批结果 0--未通过 1--通过
     */
    private Integer operatorResult;

    /**
     * 审批意见
     */
    private String operatorTip;

    /**
     * 前置状态
     */
    private Integer preStatus;

    /**
     * 前置状态描述
     */
    private String preStatusDesc;

    /**
     * 操作后状态
     */
    private Integer afterStatus;

    /**
     * 操作后状态描述
     */
    private String afterStatusDesc;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除 0--否 1--是
     */
    private Integer isDeleted;

    /**
     * 备注，说明
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRepaymentId() {
        return repaymentId;
    }

    public void setRepaymentId(Long repaymentId) {
        this.repaymentId = repaymentId;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public Integer getOperatorResult() {
        return operatorResult;
    }

    public void setOperatorResult(Integer operatorResult) {
        this.operatorResult = operatorResult;
    }

    public String getOperatorTip() {
        return operatorTip;
    }

    public void setOperatorTip(String operatorTip) {
        this.operatorTip = operatorTip;
    }

    public Integer getPreStatus() {
        return preStatus;
    }

    public void setPreStatus(Integer preStatus) {
        this.preStatus = preStatus;
    }

    public String getPreStatusDesc() {
        return preStatusDesc;
    }

    public void setPreStatusDesc(String preStatusDesc) {
        this.preStatusDesc = preStatusDesc;
    }

    public Integer getAfterStatus() {
        return afterStatus;
    }

    public void setAfterStatus(Integer afterStatus) {
        this.afterStatus = afterStatus;
    }

    public String getAfterStatusDesc() {
        return afterStatusDesc;
    }

    public void setAfterStatusDesc(String afterStatusDesc) {
        this.afterStatusDesc = afterStatusDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
