package com.moerlong.carloan.modular.loan.entity.vo;

import java.math.BigDecimal;

public class ApplyBasicInfoVo {

    private Long applyId;

    private Long channelId;

    /** 产品类型 0--抵押 1--质押 */
    private Integer productType;
    /** 申请额度 单位(元) */
    private BigDecimal applyAmount;
    /** 申请期限  12/24/36 */
    private Integer applyPeriod;
    /** 还款方式 0--等额本息 1--先息后本 */
    private Integer repaymentType;
    /** 还款用途 1--教育支出 2--医疗 4--生意周转 8--装修 16--其他（可多选） */
    private Integer loanUsage;
    /** 还款用途其他情况说明 */
    private String loanUsageOther;
    /** 0--未婚 1--离异 2--丧偶 3--已婚不知晓 4--已婚知晓不能签字 5--已婚知晓可签字 */
    private Integer partnerKnow;
    /**是否代收 0-是 1-否 */
    private Integer isCollection;
    /** 服务费方式 0-按百分比 1-按金额 */
    private Integer serviceItem;
    /** 金额 单位(元)*/
    private BigDecimal percent;
    /** '百分比(%) */
    private BigDecimal moneyAmount;
    /** 服务费(元) */
    private BigDecimal serviceCharge;
    /** 手续费分期 0-分期 1-全部 */
    private Integer feeInstallment;
    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public BigDecimal getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(BigDecimal applyAmount) {
        this.applyAmount = applyAmount;
    }

    public Integer getApplyPeriod() {
        return applyPeriod;
    }

    public void setApplyPeriod(Integer applyPeriod) {
        this.applyPeriod = applyPeriod;
    }

    public Integer getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(Integer repaymentType) {
        this.repaymentType = repaymentType;
    }

    public Integer getLoanUsage() {
        return loanUsage;
    }

    public void setLoanUsage(Integer loanUsage) {
        this.loanUsage = loanUsage;
    }

    public String getLoanUsageOther() {
        return loanUsageOther;
    }

    public void setLoanUsageOther(String loanUsageOther) {
        this.loanUsageOther = loanUsageOther;
    }

    public Integer getPartnerKnow() {
        return partnerKnow;
    }

    public void setPartnerKnow(Integer partnerKnow) {
        this.partnerKnow = partnerKnow;
    }

    public Integer getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(Integer isCollection) {
        this.isCollection = isCollection;
    }

    public Integer getServiceItem() {
        return serviceItem;
    }

    public void setServiceItem(Integer serviceItem) {
        this.serviceItem = serviceItem;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public BigDecimal getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(BigDecimal serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Integer getFeeInstallment() {
        return feeInstallment;
    }

    public void setFeeInstallment(Integer feeInstallment) {
        this.feeInstallment = feeInstallment;
    }
}
