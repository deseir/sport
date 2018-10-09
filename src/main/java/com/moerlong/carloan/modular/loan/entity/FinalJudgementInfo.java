package com.moerlong.carloan.modular.loan.entity;
import java.math.*;
import java.util.*;
public class FinalJudgementInfo {
    /** 主键 */
    private Long id;
    /** 客户id */
    private Long custId;
    /** 借款id */
    private Long applyId;
    /** 终审意见 0--通过 1--拒绝 2--补充资料 */
    private Integer judgementResult;
    /** 拒绝原因 */
    private String rejectionReason;
    /** 通过的金额 */
    private BigDecimal loanAmount;
    /** 通过的期数 */
    private Integer loanPeriod;
    /** 审核时间 */
    private Date auditTime;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 是否删除 0--否 1--是 */
    private Integer isDeleted;
    /** 备注，说明 */
    private String remark;
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id=id;
    }
    public Long getCustId() {
        return this.custId;
    }
    public void setCustId(Long custId) {
        this.custId=custId;
    }
    public Long getApplyId() {
        return this.applyId;
    }
    public void setApplyId(Long applyId) {
        this.applyId=applyId;
    }
    public Integer getJudgementResult() {
        return this.judgementResult;
    }
    public void setJudgementResult(Integer judgementResult) {
        this.judgementResult=judgementResult;
    }
    public String getRejectionReason() {
        return this.rejectionReason;
    }
    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason=rejectionReason;
    }
    public BigDecimal getLoanAmount() {
        return this.loanAmount;
    }
    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount=loanAmount;
    }
    public Integer getLoanPeriod() {
        return this.loanPeriod;
    }
    public void setLoanPeriod(Integer loanPeriod) {
        this.loanPeriod=loanPeriod;
    }
    public Date getAuditTime() {
        return this.auditTime;
    }
    public void setAuditTime(Date auditTime) {
        this.auditTime=auditTime;
    }
    public Date getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime=createTime;
    }
    public Date getUpdateTime() {
        return this.updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime=updateTime;
    }
    public Integer getIsDeleted() {
        return this.isDeleted;
    }
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted=isDeleted;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark=remark;
    }
}

