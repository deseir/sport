package com.moerlong.carloan.modular.cust.entity;
import java.math.*;
import java.util.*;
public class CustInterviewInfo {
    /** 主键 */
    private Long id;
    /** 客户id */
    private Long custId;
    /** 借款id */
    private Long applyId;
    /** 面审意见综述 */
    private String overview;
    /** 面审意见 0--拒绝 1--通过 */
    private Integer interviewResult;
    /** 拒绝原因 */
    private String rejectionReason;
    /** 通过的金额 */
    private BigDecimal loanAmount;
    /** 通过的期数 */
    private Integer loanPeriod;
    /** 现场证据附件地址1 */
    private String sceneEvidenceUrl1;
    /** 现场证据附件地址2 */
    private String sceneEvidenceUrl2;
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
    public String getOverview() {
        return this.overview;
    }
    public void setOverview(String overview) {
        this.overview=overview;
    }
    public Integer getInterviewResult() {
        return this.interviewResult;
    }
    public void setInterviewResult(Integer interviewResult) {
        this.interviewResult=interviewResult;
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
    public String getSceneEvidenceUrl1() {
        return this.sceneEvidenceUrl1;
    }
    public void setSceneEvidenceUrl1(String sceneEvidenceUrl1) {
        this.sceneEvidenceUrl1=sceneEvidenceUrl1;
    }
    public String getSceneEvidenceUrl2() {
        return this.sceneEvidenceUrl2;
    }
    public void setSceneEvidenceUrl2(String sceneEvidenceUrl2) {
        this.sceneEvidenceUrl2=sceneEvidenceUrl2;
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

