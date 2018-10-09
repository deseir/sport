package com.moerlong.carloan.modular.paybackMgr.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class RepaymentPayReportInfoVO{

    /** 主键  */
    private Long payId;

    /** 客户id  */
    private Long custId;

    /**  还款id */
    private Long repaymentId;

    /**  还款计划id */
    private Long repaymentPlanId;

    /** 流水号  */
    private String serialNo;

    /**  支付金额 */
    private BigDecimal amount;

    /** 支付时间  */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    /** 支付银行  */
    private String payBank;

    /** 支付银行卡  */
    private String payCard;

    /**
     * 类型 0-自动还款 1--手动代扣 2--对公转账 3--扣取前期费用
     */
    private Integer payType;

    /**
     * 类型描述
     */
    private String payTypeDesc;


    /** 支付状态  */
    private Integer payStatus;

    /**
     * 期数
     */
    private Integer periodNum;

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


    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
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

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayBank() {
        return payBank;
    }

    public void setPayBank(String payBank) {
        this.payBank = payBank;
    }

    public String getPayCard() {
        return payCard;
    }

    public void setPayCard(String payCard) {
        this.payCard = payCard;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getPeriodNum() {
        return periodNum;
    }

    public void setPeriodNum(Integer periodNum) {
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

    public BigDecimal getActualCharge() {
        return actualCharge;
    }

    public void setActualCharge(BigDecimal actualCharge) {
        this.actualCharge = actualCharge;
    }

    public BigDecimal getActualPenalty() {
        return actualPenalty;
    }

    public void setActualPenalty(BigDecimal actualPenalty) {
        this.actualPenalty = actualPenalty;
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

    public String getPayTypeDesc() {
        return payTypeDesc;
    }

    public void setPayTypeDesc(String payTypeDesc) {
        this.payTypeDesc = payTypeDesc;
    }
}
