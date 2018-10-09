package com.moerlong.carloan.modular.paybackMgr.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class SinglePeriodRepaymentPlanVO {

    /**
     * 期数
     */
    private int periodNum;

    /**
     * 当期开始时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date periodBeginTime;

    /**
     * 当期结束时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date periodEndTime;

    /**
     * 当期天数
     */
    private int interestDays;

    /**
     * 当期期初余额
     */
    private BigDecimal beginAmount;

    /**
     * 当期期末余额
     */
    private BigDecimal endAmount;

    /**
     * 月还款金额
     */
    private BigDecimal payAmount;

    /**
     * 当期应还总额
     */
    private BigDecimal predictTotalAmount;

    /**
     * 当期应还本金
     */
    private BigDecimal predictCapital;

    /**
     * 当期应还利息
     */
    private BigDecimal predictInterest;

    /**
     * 当期应还手续费
     */
    private BigDecimal predictCharge;

    /**
     * 当期应还服务费
     */
    private BigDecimal predictServiceCharge;

    /**
     * 租赁期初余额
     */
    private BigDecimal beginAmountLease;

    /**
     * 租赁期末余额
     */
    private BigDecimal endAmountLease;

    /**
     * 当期租赁本金   当期租赁本金+ 当期租赁利息 = 租赁合同金额
     */
    private BigDecimal capitalLease;

    /**
     * 当期租赁利息
     */
    private BigDecimal interestLease;

    /**
     * 服务合同金额
     *             服务合同金额+租赁合同金额 = 月还款
     */
    private BigDecimal platformFee;

    /**
     * 租赁合同金额
     */
    private BigDecimal leaseFee;

    public int getPeriodNum() {
        return periodNum;
    }

    public void setPeriodNum(int periodNum) {
        this.periodNum = periodNum;
    }

    public Date getPeriodBeginTime() {
        return periodBeginTime;
    }

    public void setPeriodBeginTime(Date periodBeginTime) {
        this.periodBeginTime = periodBeginTime;
    }

    public Date getPeriodEndTime() {
        return periodEndTime;
    }

    public void setPeriodEndTime(Date periodEndTime) {
        this.periodEndTime = periodEndTime;
    }

    public int getInterestDays() {
        return interestDays;
    }

    public void setInterestDays(int interestDays) {
        this.interestDays = interestDays;
    }

    public BigDecimal getBeginAmount() {
        return beginAmount;
    }

    public void setBeginAmount(BigDecimal beginAmount) {
        this.beginAmount = beginAmount;
    }

    public BigDecimal getEndAmount() {
        return endAmount;
    }

    public void setEndAmount(BigDecimal endAmount) {
        this.endAmount = endAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getPredictTotalAmount() {
        return predictTotalAmount;
    }

    public void setPredictTotalAmount(BigDecimal predictTotalAmount) {
        this.predictTotalAmount = predictTotalAmount;
    }

    public BigDecimal getPredictCapital() {
        return predictCapital;
    }

    public void setPredictCapital(BigDecimal predictCapital) {
        this.predictCapital = predictCapital;
    }

    public BigDecimal getPredictInterest() {
        return predictInterest;
    }

    public void setPredictInterest(BigDecimal predictInterest) {
        this.predictInterest = predictInterest;
    }

    public BigDecimal getPredictServiceCharge() {
        return predictServiceCharge;
    }

    public void setPredictServiceCharge(BigDecimal predictServiceCharge) {
        this.predictServiceCharge = predictServiceCharge;
    }

    public BigDecimal getPredictCharge() {
        return predictCharge;
    }

    public void setPredictCharge(BigDecimal predictCharge) {
        this.predictCharge = predictCharge;
    }

    public BigDecimal getCapitalLease() {
        return capitalLease;
    }

    public void setCapitalLease(BigDecimal capitalLease) {
        this.capitalLease = capitalLease;
    }

    public BigDecimal getInterestLease() {
        return interestLease;
    }

    public void setInterestLease(BigDecimal interestLease) {
        this.interestLease = interestLease;
    }

    public BigDecimal getPlatformFee() {
        return platformFee;
    }

    public void setPlatformFee(BigDecimal platformFee) {
        this.platformFee = platformFee;
    }

    public BigDecimal getBeginAmountLease() {
        return beginAmountLease;
    }

    public void setBeginAmountLease(BigDecimal beginAmountLease) {
        this.beginAmountLease = beginAmountLease;
    }

    public BigDecimal getEndAmountLease() {
        return endAmountLease;
    }

    public void setEndAmountLease(BigDecimal endAmountLease) {
        this.endAmountLease = endAmountLease;
    }

    public BigDecimal getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(BigDecimal leaseFee) {
        this.leaseFee = leaseFee;
    }

    @Override
    public String toString() {
        return "SinglePeriodRepaymentPlanVO{" +
                "periodNum=" + periodNum +
                ", periodBeginTime=" + periodBeginTime +
                ", periodEndTime=" + periodEndTime +
                ", interestDays=" + interestDays +
                ", beginAmount=" + beginAmount +
                ", endAmount=" + endAmount +
                ", payAmount=" + payAmount +
                ", predictTotalAmount=" + predictTotalAmount +
                ", predictCapital=" + predictCapital +
                ", predictInterest=" + predictInterest +
                ", predictCharge=" + predictCharge +
                ", predictServiceCharge=" + predictServiceCharge +
                ", beginAmountLease=" + beginAmountLease +
                ", endAmountLease=" + endAmountLease +
                ", capitalLease=" + capitalLease +
                ", interestLease=" + interestLease +
                ", platformFee=" + platformFee +
                ", leaseFee=" + leaseFee +
                '}';
    }
}
