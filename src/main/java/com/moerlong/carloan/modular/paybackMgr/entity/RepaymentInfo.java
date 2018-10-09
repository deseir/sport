package com.moerlong.carloan.modular.paybackMgr.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class RepaymentInfo {

    /**
     * 主键id
     */
    private Long id;
    
    private Long deptId;

    /**
     * 客户ID
     */
    private Long custId;

    /**
     * 借款申请ID
     */
    private Long applyId;

    /**
     * 合同编号
     */
    private String contractNo;

    /**
     * 放款ID
     */
    private Long  payId;

    /**
     * 客户姓名
     */
    private String custName;

    /**
     * 客户手机号
     */
    private String custMobile;

    /**
     * 客户身份证号
     */
    private String custIdNo;

    /**
     * 客户开户行
     */
    private String bankName;

    /**
     * 客户银行卡号
     */
    private String bankCardNo;

    /**
     * 借款金额
     */
    private BigDecimal loanAmount;

    /**
     * 借款期限
     */
    private Integer loanPeriod;

    /**
     * 年化利率
     */
    private BigDecimal yearRate;

    /**
     * 月利率
     */
    private BigDecimal monthRate;

    /**
     * 手续费率
     */
    private BigDecimal preServiceRate;

    /**
     * 一次性还款违约金费率
     */
    private BigDecimal onceRepaymentRate;

    /**
     * 前期手续费
     */
    private BigDecimal preFee;

    /**
     * 其他费用
     */
    private BigDecimal otherFee;

    /**
     * 一次性提前还款违约金
     */
    private BigDecimal breachAmount;

    /**
     * 前台服务费
     */
    private BigDecimal receptionAmount;

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
    private Integer loanDaysNum;

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
     * 应还罚息
     */
    private BigDecimal predictPenalty;

    /**
     * 实还总金额
     */
    private BigDecimal actualAmount;

    /**
     * 实还本金
     */
    private BigDecimal actualCapital;

    /**
     * 实还利息
     */
    private BigDecimal actualInterest;

    /**
     * 实还服务费
     */
    private BigDecimal actualServiceCharge;

    /**
     * 实还手续费
     */
    private BigDecimal actualCharge;

    /**
     * 实还罚息
     */
    private BigDecimal actualPenalty;


    /**
     * 当前还款状态 1--未开始  2--还款中  3--部分还款  10--申请提前还款 20--业务经理审批 30--财务确认 40--财务经理审核  50--已还完
     */
    private Integer curStatus;

    /**
     * 当前状态描述
     */
    private String curStatusDesc;

    /**
     * 是否是提前还款  0--否 1--是
     */
    private Integer isOnceEarlyRepayment;

    /**
     * 逾期次数
     */
    private Integer overdueTimes;

    /**
     * 当前还款期号
     */
    private Integer currentPeriodNum;

    /**
     * 版本
     */
    private Integer version;

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

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustMobile() {
        return custMobile;
    }

    public void setCustMobile(String custMobile) {
        this.custMobile = custMobile;
    }

    public String getCustIdNo() {
        return custIdNo;
    }

    public void setCustIdNo(String custIdNo) {
        this.custIdNo = custIdNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Integer getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(Integer loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    public BigDecimal getYearRate() {
        return yearRate;
    }

    public void setYearRate(BigDecimal yearRate) {
        this.yearRate = yearRate;
    }

    public BigDecimal getMonthRate() {
        return monthRate;
    }

    public void setMonthRate(BigDecimal monthRate) {
        this.monthRate = monthRate;
    }

    public BigDecimal getPreServiceRate() {
        return preServiceRate;
    }

    public void setPreServiceRate(BigDecimal preServiceRate) {
        this.preServiceRate = preServiceRate;
    }

    public BigDecimal getPreFee() {
        return preFee;
    }

    public void setPreFee(BigDecimal preFee) {
        this.preFee = preFee;
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

    public BigDecimal getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(BigDecimal otherFee) {
        this.otherFee = otherFee;
    }

    public BigDecimal getReceptionAmount() {
        return receptionAmount;
    }

    public void setReceptionAmount(BigDecimal receptionAmount) {
        this.receptionAmount = receptionAmount;
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

    public Integer getLoanDaysNum() {
        return loanDaysNum;
    }

    public void setLoanDaysNum(Integer loanDaysNum) {
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

    public Integer getCurStatus() {
        return curStatus;
    }

    public void setCurStatus(Integer curStatus) {
        this.curStatus = curStatus;
    }

    public Integer getOverdueTimes() {
        return overdueTimes;
    }

    public void setOverdueTimes(Integer overdueTimes) {
        this.overdueTimes = overdueTimes;
    }

    public Integer getCurrentPeriodNum() {
        return currentPeriodNum;
    }

    public void setCurrentPeriodNum(Integer currentPeriodNum) {
        this.currentPeriodNum = currentPeriodNum;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
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

    public BigDecimal getBreachAmount() {
        return breachAmount;
    }

    public void setBreachAmount(BigDecimal breachAmount) {
        this.breachAmount = breachAmount;
    }

    public Integer getIsOnceEarlyRepayment() {
        return isOnceEarlyRepayment;
    }

    public void setIsOnceEarlyRepayment(Integer isOnceEarlyRepayment) {
        this.isOnceEarlyRepayment = isOnceEarlyRepayment;
    }
    public String getCurStatusDesc() {
        return curStatusDesc;
    }

    public void setCurStatusDesc(String curStatusDesc) {
        this.curStatusDesc = curStatusDesc;
    }

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
}
