package com.moerlong.carloan.modular.cust.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class CustAddHistoryInfoVo {

    /**
     * 客户ID
     */
    private Long custId;

    /**
     * 借款ID
     */
    private Long applyId;

    /**
     * 客户名称
     */
    private String name;

    /**
     * 客户身份证号
     */
    private String certId;

    /**
     * 与借款人的关系
     */
    private String relationship;

    /**
     * 借款日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date loanDate;

    /**
     * 借款金额
     */
    private BigDecimal loanAmount;

    /**
     * 借款期限
     */
    private Integer loanPreiod;

    /**
     * 借款状态
     */
    private Integer status;

    /**
     * 借款状态描述
     */
    private String loanStatus;

    /**
     * 逾期次数
     */
    private Integer overdueNum;

    /**
     * 剩余本金
     */
    private BigDecimal leftCapital;
    /** 是否删除 0-未删除 ；1-已删除；2-已拒绝 */
    private Integer isDeleted;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }



    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Integer getLoanPreiod() {
        return loanPreiod;
    }

    public void setLoanPreiod(Integer loanPreiod) {
        this.loanPreiod = loanPreiod;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Integer getOverdueNum() {
        return overdueNum;
    }

    public void setOverdueNum(Integer overdueNum) {
        this.overdueNum = overdueNum;
    }

    public BigDecimal getLeftCapital() {
        return leftCapital;
    }

    public void setLeftCapital(BigDecimal leftCapital) {
        this.leftCapital = leftCapital;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
