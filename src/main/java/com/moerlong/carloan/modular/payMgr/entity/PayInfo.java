package com.moerlong.carloan.modular.payMgr.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class PayInfo {

    private Long id;

    private Long custId;
    
    private Long deptId;

    private Long applyId;

    private String contractNo;

    private String custName;

    private String custMobile;

    private String custIdNo;

    private BigDecimal totalAmount;

    private Integer periodNum;

    private BigDecimal notPayAmount;

    private BigDecimal payedAmount;

    private Integer payStatus;

    private String payStatusDesc;

    private BigDecimal receptionAmount;

    private Integer isReplaceCost;

    private Integer isPerCharge;

    private String bankName;

    private String bankCardNo;

    private BigDecimal approveAmount;


    /**
     * 前台部门
     */
    private String receptionDepart;

    /**
     * 前台经理
     */
    private String receptionManager;

    /**
     * 业务来源
     */
    private String transSource;

    private Long firstBussId;

    private Long secondBussId;

    private Long bussManagerId;

    private Long firstFinanceId;

    private Long firstFinanceManagerId;

    private Long secondFinanceId;

    private Long secondFinanceManagerId;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String remark;

    private Integer isDeleted;

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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getNotPayAmount() {
        return notPayAmount;
    }

    public void setNotPayAmount(BigDecimal notPayAmount) {
        this.notPayAmount = notPayAmount;
    }

    public BigDecimal getPayedAmount() {
        return payedAmount;
    }

    public void setPayedAmount(BigDecimal payedAmount) {
        this.payedAmount = payedAmount;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayStatusDesc() {
        return payStatusDesc;
    }

    public void setPayStatusDesc(String payStatusDesc) {
        this.payStatusDesc = payStatusDesc;
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

    public BigDecimal getApproveAmount() {
        return approveAmount;
    }

    public void setApproveAmount(BigDecimal approveAmount) {
        this.approveAmount = approveAmount;
    }

    public String getReceptionDepart() {
        return receptionDepart;
    }

    public void setReceptionDepart(String receptionDepart) {
        this.receptionDepart = receptionDepart;
    }

    public String getReceptionManager() {
        return receptionManager;
    }

    public void setReceptionManager(String receptionManager) {
        this.receptionManager = receptionManager;
    }

    public String getTransSource() {
        return transSource;
    }

    public void setTransSource(String transSource) {
        this.transSource = transSource;
    }

    public Long getFirstBussId() {
        return firstBussId;
    }

    public void setFirstBussId(Long firstBussId) {
        this.firstBussId = firstBussId;
    }

    public Long getSecondBussId() {
        return secondBussId;
    }

    public void setSecondBussId(Long secondBussId) {
        this.secondBussId = secondBussId;
    }

    public Long getBussManagerId() {
        return bussManagerId;
    }

    public void setBussManagerId(Long bussManagerId) {
        this.bussManagerId = bussManagerId;
    }

    public Long getFirstFinanceId() {
        return firstFinanceId;
    }

    public void setFirstFinanceId(Long firstFinanceId) {
        this.firstFinanceId = firstFinanceId;
    }

    public Long getFirstFinanceManagerId() {
        return firstFinanceManagerId;
    }

    public void setFirstFinanceManagerId(Long firstFinanceManagerId) {
        this.firstFinanceManagerId = firstFinanceManagerId;
    }

    public Long getSecondFinanceId() {
        return secondFinanceId;
    }

    public void setSecondFinanceId(Long secondFinanceId) {
        this.secondFinanceId = secondFinanceId;
    }

    public Long getSecondFinanceManagerId() {
        return secondFinanceManagerId;
    }

    public void setSecondFinanceManagerId(Long secondFinanceManagerId) {
        this.secondFinanceManagerId = secondFinanceManagerId;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getPeriodNum() {
        return periodNum;
    }

    public void setPeriodNum(Integer periodNum) {
        this.periodNum = periodNum;
    }

    public BigDecimal getReceptionAmount() {
        return receptionAmount;
    }

    public void setReceptionAmount(BigDecimal receptionAmount) {
        this.receptionAmount = receptionAmount;
    }

    public Integer getIsReplaceCost() {
        return isReplaceCost;
    }

    public void setIsReplaceCost(Integer isReplaceCost) {
        this.isReplaceCost = isReplaceCost;
    }

    public Integer getIsPerCharge() {
        return isPerCharge;
    }

    public void setIsPerCharge(Integer isPerCharge) {
        this.isPerCharge = isPerCharge;
    }

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
}
