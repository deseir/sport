package com.moerlong.carloan.modular.paybackMgr.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class RepaymentChangeRecord {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 还款ID
     */
    private Long repaymentId;

    /**
     * 还款计划ID
     */
    private Long repaymentPlanId;

    /**
     * 变更类型 0--逾期  1--一次性提前还款
     */
    private Integer changeType;

    /**
     * 变更描述
     */
    private String changeDesc;

    /**
     * 原逾期天数
     */
    private Integer oldOverdueDays;

    /**
     * 原逾期金额
     */
    private BigDecimal oldOverduePenalty;

    /**
     * 新逾期天数
     */
    private Integer newOverdueDays;

    /**
     * 新逾期金额
     */
    private BigDecimal newOverduePenalty;

    /**
     * 一次性提前还款本金
     */
    private BigDecimal onceRepaymentCapital;

    /**
     * 一次性提前还款手续费
     */
    private BigDecimal onceRepaymentCharge;

    /**
     * 一次性提前还款违约金
     */
    private BigDecimal onceRepaymentBreach;

    /**
     * 一次性提前还款总额
     */
    private BigDecimal onceRepaymentTotal;

    /**
     * 当期期数
     */
    private Integer curPeriodNum;

    /**
     * 当期还款金额
     */
    private BigDecimal curPeriodAmount;

    /**
     * 原总金额
     */
    private BigDecimal oldAmount;

    /**
     * 原总利息
     */
    private BigDecimal oldInterest;

    /**
     * 原总服务费
     */
    private BigDecimal oldServiceCharge;

    /**
     * 原租赁本金总计
     */
    private BigDecimal oldLeaseCapitalTotal;

    /**
     * 原租赁利息总计
     */
    private BigDecimal oldLeaseInterestTotal;

    /**
     * 原租赁合同总计
     */
    private BigDecimal oldMonthServiceTotal;

    /**
     * 原服务合同总计
     */
    private BigDecimal oldMonthLeaseTotal;

    /**
     * 现总金额
     */
    private BigDecimal newAmount;

    /**
     * 现总利息
     */
    private BigDecimal newInterest;

    /**
     * 现总服务费
     */
    private BigDecimal newServiceCharge;

    /**
     * 现租赁本金总计
     */
    private BigDecimal newLeaseCapitalTotal;

    /**
     * 现租赁利息总计
     */
    private BigDecimal newLeaseInterestTotal;

    /**
     * 现租赁合同总计
     */
    private BigDecimal newMonthServiceTotal;

    /**
     * 现服务合同总计
     */
    private BigDecimal newMonthLeaseTotal;


    private Integer isDeleted;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

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

    public Long getRepaymentPlanId() {
        return repaymentPlanId;
    }

    public void setRepaymentPlanId(Long repaymentPlanId) {
        this.repaymentPlanId = repaymentPlanId;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public String getChangeDesc() {
        return changeDesc;
    }

    public void setChangeDesc(String changeDesc) {
        this.changeDesc = changeDesc;
    }

    public Integer getOldOverdueDays() {
        return oldOverdueDays;
    }

    public void setOldOverdueDays(Integer oldOverdueDays) {
        this.oldOverdueDays = oldOverdueDays;
    }

    public BigDecimal getOldOverduePenalty() {
        return oldOverduePenalty;
    }

    public void setOldOverduePenalty(BigDecimal oldOverduePenalty) {
        this.oldOverduePenalty = oldOverduePenalty;
    }

    public Integer getNewOverdueDays() {
        return newOverdueDays;
    }

    public void setNewOverdueDays(Integer newOverdueDays) {
        this.newOverdueDays = newOverdueDays;
    }

    public BigDecimal getNewOverduePenalty() {
        return newOverduePenalty;
    }

    public void setNewOverduePenalty(BigDecimal newOverduePenalty) {
        this.newOverduePenalty = newOverduePenalty;
    }

    public BigDecimal getOnceRepaymentCapital() {
        return onceRepaymentCapital;
    }

    public void setOnceRepaymentCapital(BigDecimal onceRepaymentCapital) {
        this.onceRepaymentCapital = onceRepaymentCapital;
    }

    public BigDecimal getOnceRepaymentCharge() {
        return onceRepaymentCharge;
    }

    public void setOnceRepaymentCharge(BigDecimal onceRepaymentCharge) {
        this.onceRepaymentCharge = onceRepaymentCharge;
    }

    public BigDecimal getOnceRepaymentBreach() {
        return onceRepaymentBreach;
    }

    public void setOnceRepaymentBreach(BigDecimal onceRepaymentBreach) {
        this.onceRepaymentBreach = onceRepaymentBreach;
    }

    public BigDecimal getOnceRepaymentTotal() {
        return onceRepaymentTotal;
    }

    public void setOnceRepaymentTotal(BigDecimal onceRepaymentTotal) {
        this.onceRepaymentTotal = onceRepaymentTotal;
    }

    public BigDecimal getOldAmount() {
        return oldAmount;
    }

    public void setOldAmount(BigDecimal oldAmount) {
        this.oldAmount = oldAmount;
    }

    public BigDecimal getOldInterest() {
        return oldInterest;
    }

    public void setOldInterest(BigDecimal oldInterest) {
        this.oldInterest = oldInterest;
    }

    public BigDecimal getOldServiceCharge() {
        return oldServiceCharge;
    }

    public void setOldServiceCharge(BigDecimal oldServiceCharge) {
        this.oldServiceCharge = oldServiceCharge;
    }

    public BigDecimal getNewAmount() {
        return newAmount;
    }

    public void setNewAmount(BigDecimal newAmount) {
        this.newAmount = newAmount;
    }

    public BigDecimal getNewInterest() {
        return newInterest;
    }

    public void setNewInterest(BigDecimal newInterest) {
        this.newInterest = newInterest;
    }

    public BigDecimal getNewServiceCharge() {
        return newServiceCharge;
    }

    public void setNewServiceCharge(BigDecimal newServiceCharge) {
        this.newServiceCharge = newServiceCharge;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCurPeriodNum() {
        return curPeriodNum;
    }

    public void setCurPeriodNum(Integer curPeriodNum) {
        this.curPeriodNum = curPeriodNum;
    }

    public BigDecimal getCurPeriodAmount() {
        return curPeriodAmount;
    }

    public void setCurPeriodAmount(BigDecimal curPeriodAmount) {
        this.curPeriodAmount = curPeriodAmount;
    }

    public BigDecimal getOldLeaseCapitalTotal() {
        return oldLeaseCapitalTotal;
    }

    public void setOldLeaseCapitalTotal(BigDecimal oldLeaseCapitalTotal) {
        this.oldLeaseCapitalTotal = oldLeaseCapitalTotal;
    }

    public BigDecimal getOldLeaseInterestTotal() {
        return oldLeaseInterestTotal;
    }

    public void setOldLeaseInterestTotal(BigDecimal oldLeaseInterestTotal) {
        this.oldLeaseInterestTotal = oldLeaseInterestTotal;
    }

    public BigDecimal getOldMonthServiceTotal() {
        return oldMonthServiceTotal;
    }

    public void setOldMonthServiceTotal(BigDecimal oldMonthServiceTotal) {
        this.oldMonthServiceTotal = oldMonthServiceTotal;
    }

    public BigDecimal getOldMonthLeaseTotal() {
        return oldMonthLeaseTotal;
    }

    public void setOldMonthLeaseTotal(BigDecimal oldMonthLeaseTotal) {
        this.oldMonthLeaseTotal = oldMonthLeaseTotal;
    }

    public BigDecimal getNewLeaseCapitalTotal() {
        return newLeaseCapitalTotal;
    }

    public void setNewLeaseCapitalTotal(BigDecimal newLeaseCapitalTotal) {
        this.newLeaseCapitalTotal = newLeaseCapitalTotal;
    }

    public BigDecimal getNewLeaseInterestTotal() {
        return newLeaseInterestTotal;
    }

    public void setNewLeaseInterestTotal(BigDecimal newLeaseInterestTotal) {
        this.newLeaseInterestTotal = newLeaseInterestTotal;
    }

    public BigDecimal getNewMonthServiceTotal() {
        return newMonthServiceTotal;
    }

    public void setNewMonthServiceTotal(BigDecimal newMonthServiceTotal) {
        this.newMonthServiceTotal = newMonthServiceTotal;
    }

    public BigDecimal getNewMonthLeaseTotal() {
        return newMonthLeaseTotal;
    }

    public void setNewMonthLeaseTotal(BigDecimal newMonthLeaseTotal) {
        this.newMonthLeaseTotal = newMonthLeaseTotal;
    }
}
