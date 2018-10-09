package com.moerlong.carloan.modular.paybackMgr.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class RepaymentCalculatorVO {

    /**
     * 借款金额
     */
    private BigDecimal loanAmount;

    /**
     * 放款金额
     */
    private BigDecimal distributeAmount;

    /**
     * 年化费率
     */
    private BigDecimal yearRate;

    /**
     * 手续费率
     */
    private BigDecimal preServiceRate;

    /**
     * 投资人年化费率
     */
    private BigDecimal incomeRate;

    /**
     * 一次性还款违约金费率
     */
    private BigDecimal onceRepaymentRate;

    /**
     * 前期费用
     */
    private BigDecimal preFee;

    /**
     * 借款期数
     */
    private int loanPeriod;

    /**
     * 放款时间
     */
    private Date distributeTime;

    /**
     * 计息开始时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date interestBeginTime;

    /**
     * 计息结束时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date interestEndTime;

    /**
     * 借款总天数
     */
    private int loanDaysNum;

    /**
     * 应还总金额
     */
    private BigDecimal predictAmount;

    /**
     * 应还本金
     */
    private BigDecimal predictCapital;

    /**
     * 应还利息
     */
    private BigDecimal predictInterest;

    /**
     * 应还服务费
     */
    private BigDecimal predictServiceCharge;

    /**
     * 应还手续费
     */
    private BigDecimal predictCharge;

    /**
     * 月还款--租赁合同       给还款计算器用
     */
    private BigDecimal monthLease;

    /**
     * 月还款--服务合同       给还款计算器用
     */
    private BigDecimal monthService;


    /**
     * 租赁合同总计
     */
    private BigDecimal monthLeaseTotal;

    /**
     * 服务合同总计
     */
    private BigDecimal monthServiceTotal;

    /**
     * 租赁本金总计
     */
    private BigDecimal leaseCapitalTotal;

    /**
     * 租赁利息总计
     */
    private BigDecimal leaseInterestTotal;


    /**
     * 还款计划期数
     */
    private List<SinglePeriodRepaymentPlanVO> plans;

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public BigDecimal getDistributeAmount() {
        return distributeAmount;
    }

    public void setDistributeAmount(BigDecimal distributeAmount) {
        this.distributeAmount = distributeAmount;
    }

    public BigDecimal getYearRate() {
        return yearRate;
    }

    public void setYearRate(BigDecimal yearRate) {
        this.yearRate = yearRate;
    }

    public BigDecimal getPreServiceRate() {
        return preServiceRate;
    }

    public void setPreServiceRate(BigDecimal preServiceRate) {
        this.preServiceRate = preServiceRate;
    }

    public BigDecimal getIncomeRate() {
        return incomeRate;
    }

    public void setIncomeRate(BigDecimal incomeRate) {
        this.incomeRate = incomeRate;
    }

    public BigDecimal getPreFee() {
        return preFee;
    }

    public void setPreFee(BigDecimal preFee) {
        this.preFee = preFee;
    }


    public int getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(int loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    public Date getDistributeTime() {
        return distributeTime;
    }

    public void setDistributeTime(Date distributeTime) {
        this.distributeTime = distributeTime;
    }

    public Date getInterestBeginTime() {
        return interestBeginTime;
    }

    public void setInterestBeginTime(Date interestBeginTime) {
        this.interestBeginTime = interestBeginTime;
    }

    public Date getInterestEndTime() {
        return interestEndTime;
    }

    public void setInterestEndTime(Date interestEndTime) {
        this.interestEndTime = interestEndTime;
    }

    public int getLoanDaysNum() {
        return loanDaysNum;
    }

    public void setLoanDaysNum(int loanDaysNum) {
        this.loanDaysNum = loanDaysNum;
    }

    public BigDecimal getPredictAmount() {
        return predictAmount;
    }

    public void setPredictAmount(BigDecimal predictAmount) {
        this.predictAmount = predictAmount;
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

    public List<SinglePeriodRepaymentPlanVO> getPlans() {
        return plans;
    }

    public void setPlans(List<SinglePeriodRepaymentPlanVO> plans) {
        this.plans = plans;
    }

    public BigDecimal getMonthLease() {
        return monthLease;
    }

    public void setMonthLease(BigDecimal monthLease) {
        this.monthLease = monthLease;
    }

    public BigDecimal getMonthService() {
        return monthService;
    }

    public void setMonthService(BigDecimal monthService) {
        this.monthService = monthService;
    }

    public BigDecimal getMonthLeaseTotal() {
        return monthLeaseTotal;
    }

    public void setMonthLeaseTotal(BigDecimal monthLeaseTotal) {
        this.monthLeaseTotal = monthLeaseTotal;
    }

    public BigDecimal getMonthServiceTotal() {
        return monthServiceTotal;
    }

    public void setMonthServiceTotal(BigDecimal monthServiceTotal) {
        this.monthServiceTotal = monthServiceTotal;
    }

    public BigDecimal getLeaseCapitalTotal() {
        return leaseCapitalTotal;
    }

    public void setLeaseCapitalTotal(BigDecimal leaseCapitalTotal) {
        this.leaseCapitalTotal = leaseCapitalTotal;
    }

    public BigDecimal getLeaseInterestTotal() {
        return leaseInterestTotal;
    }

    public void setLeaseInterestTotal(BigDecimal leaseInterestTotal) {
        this.leaseInterestTotal = leaseInterestTotal;
    }

    public BigDecimal getOnceRepaymentRate() {
        return onceRepaymentRate;
    }

    public void setOnceRepaymentRate(BigDecimal onceRepaymentRate) {
        this.onceRepaymentRate = onceRepaymentRate;
    }
}
