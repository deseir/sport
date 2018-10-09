package com.moerlong.carloan.modular.paybackMgr.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class PayStatisInfo {

    /**
     * 主键id
     */
    private Long id;
    
    private Long deptId;

    /**
     * 统计日期 YYYYMMDD
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date statisDate;

    /**
     * 累计放款总计
     */
    private BigDecimal payTotalAmount;

    /**
     * 累计还款总计
     */
    private BigDecimal repaymentTotalAmount;

    /**
     * 累计还款总本金
     */
    private BigDecimal totalCapital;

    /**
     * 累计还款总利息
     */
    private BigDecimal totalInterest;

    /**
     * 累计还款总服务费
     */
    private BigDecimal totalServiceCharge;

    /**
     * 累计还款总手续费
     */
    private BigDecimal totalCharge;

    /**
     * 累计还款总罚息
     */
    private BigDecimal totalPenalty;

    /**
     * 累计总违约金
     */
    private BigDecimal totalBreach;

    /**
     * 累计还款总前期费用
     */
    private BigDecimal totalPreFee;

    /**
     * 未还总金额
     */
    private BigDecimal notRepayAmount;
    /**
     * 未还总本金
     */
    private BigDecimal notRepayCapital;

    /**
     * 未还总利息
     */
    private BigDecimal notRepayInterest;

    /**
     * 未还总服务费
     */
    private BigDecimal notRepayServiceCharge;

    /**
     * 未还总手续费
     */
    private BigDecimal notRepayCharge;

    /**
     * 未还总罚息
     */
    private BigDecimal notRepayPenalty;

    /**
     * 当日放款总计
     */
    private BigDecimal curPay;

    /**
     * 当日还款总计
     */
    private BigDecimal curRepayment;

    /**
     * 当日前期费用总计
     */
    private BigDecimal curPreFee;


    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Integer isDeleted;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStatisDate() {
        return statisDate;
    }

    public void setStatisDate(Date statisDate) {
        this.statisDate = statisDate;
    }

    public BigDecimal getPayTotalAmount() {
        return payTotalAmount;
    }

    public void setPayTotalAmount(BigDecimal payTotalAmount) {
        this.payTotalAmount = payTotalAmount;
    }

    public BigDecimal getRepaymentTotalAmount() {
        return repaymentTotalAmount;
    }

    public void setRepaymentTotalAmount(BigDecimal repaymentTotalAmount) {
        this.repaymentTotalAmount = repaymentTotalAmount;
    }

    public BigDecimal getTotalCapital() {
        return totalCapital;
    }

    public void setTotalCapital(BigDecimal totalCapital) {
        this.totalCapital = totalCapital;
    }

    public BigDecimal getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(BigDecimal totalInterest) {
        this.totalInterest = totalInterest;
    }

    public BigDecimal getTotalServiceCharge() {
        return totalServiceCharge;
    }

    public void setTotalServiceCharge(BigDecimal totalServiceCharge) {
        this.totalServiceCharge = totalServiceCharge;
    }

    public BigDecimal getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(BigDecimal totalCharge) {
        this.totalCharge = totalCharge;
    }

    public BigDecimal getTotalPenalty() {
        return totalPenalty;
    }

    public void setTotalPenalty(BigDecimal totalPenalty) {
        this.totalPenalty = totalPenalty;
    }

    public BigDecimal getTotalBreach() {
        return totalBreach;
    }

    public void setTotalBreach(BigDecimal totalBreach) {
        this.totalBreach = totalBreach;
    }

    public BigDecimal getTotalPreFee() {
        return totalPreFee;
    }

    public void setTotalPreFee(BigDecimal totalPreFee) {
        this.totalPreFee = totalPreFee;
    }

    public BigDecimal getNotRepayCapital() {
        return notRepayCapital;
    }

    public BigDecimal getNotRepayAmount() {
        return notRepayAmount;
    }

    public void setNotRepayAmount(BigDecimal notRepayAmount) {
        this.notRepayAmount = notRepayAmount;
    }

    public void setNotRepayCapital(BigDecimal notRepayCapital) {
        this.notRepayCapital = notRepayCapital;
    }

    public BigDecimal getNotRepayInterest() {
        return notRepayInterest;
    }

    public void setNotRepayInterest(BigDecimal notRepayInterest) {
        this.notRepayInterest = notRepayInterest;
    }

    public BigDecimal getNotRepayServiceCharge() {
        return notRepayServiceCharge;
    }

    public void setNotRepayServiceCharge(BigDecimal notRepayServiceCharge) {
        this.notRepayServiceCharge = notRepayServiceCharge;
    }

    public BigDecimal getNotRepayCharge() {
        return notRepayCharge;
    }

    public void setNotRepayCharge(BigDecimal notRepayCharge) {
        this.notRepayCharge = notRepayCharge;
    }

    public BigDecimal getNotRepayPenalty() {
        return notRepayPenalty;
    }

    public void setNotRepayPenalty(BigDecimal notRepayPenalty) {
        this.notRepayPenalty = notRepayPenalty;
    }

    public BigDecimal getCurPay() {
        return curPay;
    }

    public void setCurPay(BigDecimal curPay) {
        this.curPay = curPay;
    }

    public BigDecimal getCurRepayment() {
        return curRepayment;
    }

    public void setCurRepayment(BigDecimal curRepayment) {
        this.curRepayment = curRepayment;
    }

    public BigDecimal getCurPreFee() {
        return curPreFee;
    }

    public void setCurPreFee(BigDecimal curPreFee) {
        this.curPreFee = curPreFee;
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
    
    public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	@Override
    public String toString() {
        return "PayStatisInfo{" +
                "id=" + id +
                ", statisDate=" + statisDate +
                ", payTotalAmount=" + payTotalAmount +
                ", repaymentTotalAmount=" + repaymentTotalAmount +
                ", totalCapital=" + totalCapital +
                ", totalInterest=" + totalInterest +
                ", totalServiceCharge=" + totalServiceCharge +
                ", totalCharge=" + totalCharge +
                ", totalPenalty=" + totalPenalty +
                ", totalBreach=" + totalBreach +
                ", totalPreFee=" + totalPreFee +
                ", notRepayAmount=" + notRepayAmount +
                ", notRepayCapital=" + notRepayCapital +
                ", notRepayInterest=" + notRepayInterest +
                ", notRepayServiceCharge=" + notRepayServiceCharge +
                ", notRepayCharge=" + notRepayCharge +
                ", notRepayPenalty=" + notRepayPenalty +
                ", curPay=" + curPay +
                ", curRepayment=" + curRepayment +
                ", curPreFee=" + curPreFee +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                ", remark='" + remark + '\'' +
                '}';
    }
}
