package com.moerlong.carloan.modular.paybackMgr.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class RepaymentPlanTodayInfoVO {


    private Long id;

    private Long repaymentId;

    private String contractNo;

    private String custName;

    private String bankName;

    private String bankNo;

    private String mobile;

    private String idCard;

    private Integer periodNum;

    private Integer periodStatus;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date periodBeginTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date periodEndTime;

    private BigDecimal predictAmount;


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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
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

    public BigDecimal getPredictAmount() {
        return predictAmount;
    }

    public void setPredictAmount(BigDecimal predictAmount) {
        this.predictAmount = predictAmount;
    }
}
