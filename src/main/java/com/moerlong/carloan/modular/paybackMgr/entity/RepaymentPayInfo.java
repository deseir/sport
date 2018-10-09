package com.moerlong.carloan.modular.paybackMgr.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/***
 * 还款记录表
 */
public class RepaymentPayInfo {

    /** 主键  */
    private Long id;
    
    private Long deptId;

    /** 客户id  */
    private Long custId;

    /**  还款id */
    private Long repaymentId;

    /**  还款计划id */
    private Long repaymentPlanId;

    /**
     * 类型 0-自动还款 1--手动代扣 2--对公转账 3--扣取前期费用
     */
    private Integer payType;

    /**
     * 类型描述
     */
    private String payTypeDesc;

    /**  还款期数 */
    private Integer repaymentPeriodNum;

    /** 流水号  */
    private String serialNo;

    /**  支付金额 */
    private BigDecimal amount;

    /**
     * 绑定手机号
     */
    private String bindingMobile;

    /** 支付状态  */
    private Integer payStatus;

    private Integer payingNum;

    /** 支付中心code  */
    private String payCode;

    /** 支付消息  */
    private String payMsg;

    /** 支付时间  */
    private Date payTime;

    /** 支付渠道  */
    private String payChannel;

    /** 支付银行  */
    private String payBank;

    /** 支付银行卡  */
    private String payCard;

    /** 创建时间  */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新时间  */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 是否删除 0--否 1--是  */
    private Integer isDeleted;

    /**  备注，说明 */
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

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getRepaymentPeriodNum() {
        return repaymentPeriodNum;
    }

    public void setRepaymentPeriodNum(Integer repaymentPeriodNum) {
        this.repaymentPeriodNum = repaymentPeriodNum;
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

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getPayingNum() {
        return payingNum;
    }

    public void setPayingNum(Integer payingNum) {
        this.payingNum = payingNum;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayMsg() {
        return payMsg;
    }

    public void setPayMsg(String payMsg) {
        this.payMsg = payMsg;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBindingMobile() {
        return bindingMobile;
    }

    public void setBindingMobile(String bindingMobile) {
        this.bindingMobile = bindingMobile;
    }

    public String getPayTypeDesc() {
        return payTypeDesc;
    }

    public void setPayTypeDesc(String payTypeDesc) {
        this.payTypeDesc = payTypeDesc;
    }

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
}
