package com.moerlong.carloan.modular.cust.entity;
import java.math.*;
import java.util.*;
public class CreditCardDetail {
    /** 主键 */
    private Long id;
    /** 客户id */
    private Long custId;
    /** 借款id */
    private Long applyId;
    /** 类型 0--主借人 1--共借人 */
    private Integer type;
    /** 信用卡发卡机构 */
    private String cardOrg;
    /** 授信额度 */
    private BigDecimal cardAmount;
    /** 共享授信额度 */
    private BigDecimal cardShareAmount;
    /** 类型 0--抵押担保 1--信用免担保 2--组合认证 */
    private Integer cardType;
    private String cardTypeName;
    /** 账户状态 0--正常 1--冻结 2--止付 3--呆帐 */
    private Integer accountStatus;
    private String accountStatusName;
    /** 已用额度 */
    private BigDecimal usedAmount;
    /** 近6个月平均使用额度 */
    private BigDecimal avgUsedAmount;
    /** 最大使用额度 */
    private BigDecimal maxUsedAmount;
    /** 当前逾期期数 */
    private Integer curOverdueNum;
    /** 当前逾期金额 */
    private BigDecimal curOverdueAmount;
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
    public Integer getType() {
        return this.type;
    }
    public void setType(Integer type) {
        this.type=type;
    }
    public String getCardOrg() {
        return this.cardOrg;
    }
    public void setCardOrg(String cardOrg) {
        this.cardOrg=cardOrg;
    }
    public BigDecimal getCardAmount() {
        return this.cardAmount;
    }
    public void setCardAmount(BigDecimal cardAmount) {
        this.cardAmount=cardAmount;
    }
    public BigDecimal getCardShareAmount() {
        return this.cardShareAmount;
    }
    public void setCardShareAmount(BigDecimal cardShareAmount) {
        this.cardShareAmount=cardShareAmount;
    }
    public Integer getCardType() {
        return this.cardType;
    }
    public void setCardType(Integer cardType) {
        this.cardType=cardType;
    }
    public Integer getAccountStatus() {
        return this.accountStatus;
    }
    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus=accountStatus;
    }
    public BigDecimal getUsedAmount() {
        return this.usedAmount;
    }
    public void setUsedAmount(BigDecimal usedAmount) {
        this.usedAmount=usedAmount;
    }
    public BigDecimal getAvgUsedAmount() {
        return this.avgUsedAmount;
    }
    public void setAvgUsedAmount(BigDecimal avgUsedAmount) {
        this.avgUsedAmount=avgUsedAmount;
    }
    public BigDecimal getMaxUsedAmount() {
        return this.maxUsedAmount;
    }
    public void setMaxUsedAmount(BigDecimal maxUsedAmount) {
        this.maxUsedAmount=maxUsedAmount;
    }
    public Integer getCurOverdueNum() {
        return this.curOverdueNum;
    }
    public void setCurOverdueNum(Integer curOverdueNum) {
        this.curOverdueNum=curOverdueNum;
    }
    public BigDecimal getCurOverdueAmount() {
        return this.curOverdueAmount;
    }
    public void setCurOverdueAmount(BigDecimal curOverdueAmount) {
        this.curOverdueAmount=curOverdueAmount;
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

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }

    public String getAccountStatusName() {
        return accountStatusName;
    }

    public void setAccountStatusName(String accountStatusName) {
        this.accountStatusName = accountStatusName;
    }
}

