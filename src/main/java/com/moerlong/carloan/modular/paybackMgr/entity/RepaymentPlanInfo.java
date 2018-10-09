package com.moerlong.carloan.modular.paybackMgr.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class RepaymentPlanInfo {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 客户id
     */
    private Long custId;
    
    private Long deptId;

    /**
     * 借款id
     */
    private Long applyId;

    /**
     * 还款id
     */
    private Long repaymentId;

    /**
     * 期数
     */
    private Integer periodNum;

    /**
     * 当期状态 1--未开始  2--还款中  3--部分还款  4--已逾期  5--已还完
     */
    private Integer periodStatus;

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
     * 计息天数
     */
    private Integer interestDays;

    /**
     * 期初余额
     */
    private BigDecimal beginAmount;

    /**
     * 期末余额
     */
    private BigDecimal endAmount;

    /**
     * 月还款
     */
    private BigDecimal payAmount;

    /**
     * 当期应还总额
     */
    private BigDecimal predictAmount;

    /**
     * 当期应还利息
     */
    private BigDecimal predictInterest;

    /**
     * 当期应还北京
     */
    private BigDecimal predictCapital;

    /**
     * 当期应还服务费
     */
    private BigDecimal predictServiceCharge;

    /**
     * 当期应还手续费
     */
    private BigDecimal predictCharge;

    /**
     * 当期应还罚息
     */
    private BigDecimal predictPenalty;

    /**
     * 当期实还总额
     */
    private BigDecimal actualAmount;

    /**
     * 当期实还本金
     */
    private BigDecimal actualCapital;

    /**
     * 当期实还利息
     */
    private BigDecimal actualInterest;

    /**
     * 当期实还服务费
     */
    private BigDecimal actualServiceCharge;

    /**
     * 当期实还手续费
     */
    private BigDecimal actualCharge;

    /**
     * 当期实还罚息
     */
    private BigDecimal actualPenalty;


    /**
     * 租赁期初余额
     */
    private BigDecimal beginAmountLease;

    /**
     * 租赁期末余额
     */
    private BigDecimal endAmountLease;

    /**
     * 租赁本金
     */
    private BigDecimal capitalLease;

    /**
     * 租赁利息
     */
    private BigDecimal interestLease;

    /**
     * 租赁合同总计
     */
    private BigDecimal leaseTotal;

    /**
     * 服务合同总计
     */
    private BigDecimal serviceTotal;


    /**
     * 是否逾期 0-否 1-是
     */
    private Integer isOverdue;

    /**
     * 是否被锁定 0-否 1-是
     */
    private Integer isLock;

    /**
     * 逾期天数
     */
    private Integer overdueDays;

    /**
     * 逾期罚息
     */
    private BigDecimal overduePenalty;



    private Integer isDeleted;



    private Date createTime;

    private Date updateTime;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Long getRepaymentId() {
        return repaymentId;
    }

    public void setRepaymentId(Long repaymentId) {
        this.repaymentId = repaymentId;
    }

    public Integer getPeriodNum() {
        return periodNum;
    }

    public void setPeriodNum(Integer periodNum) {
        this.periodNum = periodNum;
    }

    public Integer getPeriodStatus() {
        return periodStatus;
    }

    public void setPeriodStatus(Integer periodStatus) {
        this.periodStatus = periodStatus;
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

    public Integer getInterestDays() {
        return interestDays;
    }

    public void setInterestDays(Integer interestDays) {
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

    public BigDecimal getPredictAmount() {
        return predictAmount;
    }

    public void setPredictAmount(BigDecimal predictAmount) {
        this.predictAmount = predictAmount;
    }

    public BigDecimal getPredictInterest() {
        return predictInterest;
    }

    public void setPredictInterest(BigDecimal predictInterest) {
        this.predictInterest = predictInterest;
    }

    public BigDecimal getPredictCapital() {
        return predictCapital;
    }

    public void setPredictCapital(BigDecimal predictCapital) {
        this.predictCapital = predictCapital;
    }

    public BigDecimal getPredictServiceCharge() {
        return predictServiceCharge;
    }

    public void setPredictServiceCharge(BigDecimal predictServiceCharge) {
        this.predictServiceCharge = predictServiceCharge;
    }

    public BigDecimal getPredictPenalty() {
        return predictPenalty;
    }

    public void setPredictPenalty(BigDecimal predictPenalty) {
        this.predictPenalty = predictPenalty;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public BigDecimal getActualCapital() {
        return actualCapital;
    }

    public void setActualCapital(BigDecimal actualCapital) {
        this.actualCapital = actualCapital;
    }

    public BigDecimal getActualInterest() {
        return actualInterest;
    }

    public void setActualInterest(BigDecimal actualInterest) {
        this.actualInterest = actualInterest;
    }

    public BigDecimal getActualServiceCharge() {
        return actualServiceCharge;
    }

    public void setActualServiceCharge(BigDecimal actualServiceCharge) {
        this.actualServiceCharge = actualServiceCharge;
    }

    public BigDecimal getActualPenalty() {
        return actualPenalty;
    }

    public void setActualPenalty(BigDecimal actualPenalty) {
        this.actualPenalty = actualPenalty;
    }

    public Integer getIsOverdue() {
        return isOverdue;
    }

    public void setIsOverdue(Integer isOverdue) {
        this.isOverdue = isOverdue;
    }

    public Integer getOverdueDays() {
        return overdueDays;
    }

    public void setOverdueDays(Integer overdueDays) {
        this.overdueDays = overdueDays;
    }

    public BigDecimal getOverduePenalty() {
        return overduePenalty;
    }

    public void setOverduePenalty(BigDecimal overduePenalty) {
        this.overduePenalty = overduePenalty;
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

    public BigDecimal getPredictCharge() {
        return predictCharge;
    }

    public void setPredictCharge(BigDecimal predictCharge) {
        this.predictCharge = predictCharge;
    }

    public BigDecimal getActualCharge() {
        return actualCharge;
    }

    public void setActualCharge(BigDecimal actualCharge) {
        this.actualCharge = actualCharge;
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

    public BigDecimal getLeaseTotal() {
        return leaseTotal;
    }

    public void setLeaseTotal(BigDecimal leaseTotal) {
        this.leaseTotal = leaseTotal;
    }

    public BigDecimal getServiceTotal() {
        return serviceTotal;
    }

    public void setServiceTotal(BigDecimal serviceTotal) {
        this.serviceTotal = serviceTotal;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
}
