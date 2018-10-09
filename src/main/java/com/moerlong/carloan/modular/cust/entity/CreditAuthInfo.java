package com.moerlong.carloan.modular.cust.entity;
import java.math.*;
import java.util.*;
public class CreditAuthInfo {
    /** 主键id自增 */
    private Long id;
    /** 借款申请id */
    private Long applyId;
    /** 用户id */
    private Long custId;
    /** 类型 0--主借人 1--共借人 */
    private Integer type;
    /** 同盾贷前审核评分 */
    private Integer tdScore;
    /** 同盾贷前审核评分附件地址 */
    private String tdScoreAttachUrl;
    /** 同盾贷前审核风险情况附件地址 */
    private String tdRiskAttachUrl;
    /** 百融贷前审核规则集分数 */
    private Integer brRuleScore;
    /** 百融代签审核信用评分分数 */
    private Integer brCreditScore;
    /** 百融贷前审核报告附件地址 */
    private String brAttachUrl;
    /** 有无裁判文书（裁判文书网） 0--无 1--有 */
    private Integer hasJudgement;
    /** 裁判文书网附件 */
    private String judgementAttachUrl;
    /** 创建时间 */
    private Date createTime;
    /** 修改时间 */
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
    public Long getApplyId() {
        return this.applyId;
    }
    public void setApplyId(Long applyId) {
        this.applyId=applyId;
    }
    public Long getCustId() {
        return this.custId;
    }
    public void setCustId(Long custId) {
        this.custId=custId;
    }
    public Integer getType() {
        return this.type;
    }
    public void setType(Integer type) {
        this.type=type;
    }
    public Integer getTdScore() {
        return this.tdScore;
    }
    public void setTdScore(Integer tdScore) {
        this.tdScore=tdScore;
    }
    public String getTdScoreAttachUrl() {
        return this.tdScoreAttachUrl;
    }
    public void setTdScoreAttachUrl(String tdScoreAttachUrl) {
        this.tdScoreAttachUrl=tdScoreAttachUrl;
    }
    public String getTdRiskAttachUrl() {
        return this.tdRiskAttachUrl;
    }
    public void setTdRiskAttachUrl(String tdRiskAttachUrl) {
        this.tdRiskAttachUrl=tdRiskAttachUrl;
    }
    public Integer getBrRuleScore() {
        return this.brRuleScore;
    }
    public void setBrRuleScore(Integer brRuleScore) {
        this.brRuleScore=brRuleScore;
    }
    public Integer getBrCreditScore() {
        return this.brCreditScore;
    }
    public void setBrCreditScore(Integer brCreditScore) {
        this.brCreditScore=brCreditScore;
    }
    public String getBrAttachUrl() {
        return this.brAttachUrl;
    }
    public void setBrAttachUrl(String brAttachUrl) {
        this.brAttachUrl=brAttachUrl;
    }
    public Integer getHasJudgement() {
        return this.hasJudgement;
    }
    public void setHasJudgement(Integer hasJudgement) {
        this.hasJudgement=hasJudgement;
    }
    public String getJudgementAttachUrl() {
        return this.judgementAttachUrl;
    }
    public void setJudgementAttachUrl(String judgementAttachUrl) {
        this.judgementAttachUrl=judgementAttachUrl;
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

