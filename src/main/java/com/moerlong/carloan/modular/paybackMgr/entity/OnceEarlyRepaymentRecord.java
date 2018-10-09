package com.moerlong.carloan.modular.paybackMgr.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class OnceEarlyRepaymentRecord {

    /**
     * 主键
     */
    private Long id;

    /**
     * 还款总表id
     */
    private Long repaymentId;

    /**
     * 一次性提前还款剩余本金
     */
    private BigDecimal onceRepaymentCapital;

    /**
     * 一次性提前还款剩余手续费
     */
    private BigDecimal onceRepaymentCharge;

    /**
     * 一次性提前还款罚金
     */
    private BigDecimal onceRepaymentBreach;

    /**
     * 当期期数
     */
    private Integer curPeriodNum;

    /**
     * 当期还款金额
     */
    private BigDecimal curPeriodAmount;

    /**
     * 一次性提前还款总额
     */
    private BigDecimal onceRepaymentTotal;

    /**
     * 约定提前还款时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date appointDate;

    /**
     * 当前还款状态
     */
    private Integer curStatus;

    /**
     * 当前状态描述
     */
    private String curStatusDesc;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
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

    public BigDecimal getOnceRepaymentTotal() {
        return onceRepaymentTotal;
    }

    public void setOnceRepaymentTotal(BigDecimal onceRepaymentTotal) {
        this.onceRepaymentTotal = onceRepaymentTotal;
    }

    public Date getAppointDate() {
        return appointDate;
    }

    public void setAppointDate(Date appointDate) {
        this.appointDate = appointDate;
    }

    public Integer getCurStatus() {
        return curStatus;
    }

    public void setCurStatus(Integer curStatus) {
        this.curStatus = curStatus;
    }

    public String getCurStatusDesc() {
        return curStatusDesc;
    }

    public void setCurStatusDesc(String curStatusDesc) {
        this.curStatusDesc = curStatusDesc;
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
