package com.moerlong.carloan.modular.payMgr.entity;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 
 * 
 * 
 **/
public class PayOrder {


  /****/

  private Long ecoOrderId;


  /**用户id**/

  private String userUuid;


  /**审核批次号**/

  private String batchNo;


  /**卡号**/

  private String accNo;


  /**卡用户名**/

  private String accName;


  /**代付金额**/

  private BigDecimal amount;


  /**手机号**/

  private String mobile;


  /**开户省**/

  private String openProvince;


  /**开户市**/

  private String openCity;


  /**支行名称**/

  private String bankBranch;


  /****/

  private String idType;


  /**身份证**/

  private String idNo;


  /**代付状态**/

  private String payState;


  /**银行名**/

  private String bankName;


  /**货币**/

  private String cny;


  /**备注**/

  private String remark;


  /**返回说明**/

  private String transDesc;


  /****/

  private Date createTime;


  /****/

  private Date updateTime;


  /****/

  private String version;


  /** 支付渠道商编号**/

  private String channelId;


  /**第三方返回code码**/

  private String thirdReturnCode;


  /**(0认证支付/1代扣支付)**/

  private Integer payType;


  /**第三方外部码**/

  private String itemId;


  /**商户订单号**/

  private String merOrderNo;




  public void setEcoOrderId(Long ecoOrderId) {
    this.ecoOrderId = ecoOrderId;
  }


  public Long getEcoOrderId() {
    return this.ecoOrderId;
  }


  public void setUserUuid(String userUuid) {
    this.userUuid = userUuid;
  }


  public String getUserUuid() {
    return this.userUuid;
  }


  public void setBatchNo(String batchNo) {
    this.batchNo = batchNo;
  }


  public String getBatchNo() {
    return this.batchNo;
  }


  public void setAccNo(String accNo) {
    this.accNo = accNo;
  }


  public String getAccNo() {
    return this.accNo;
  }


  public void setAccName(String accName) {
    this.accName = accName;
  }


  public String getAccName() {
    return this.accName;
  }


  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }


  public BigDecimal getAmount() {
    return this.amount;
  }


  public void setMobile(String mobile) {
    this.mobile = mobile;
  }


  public String getMobile() {
    return this.mobile;
  }


  public void setOpenProvince(String openProvince) {
    this.openProvince = openProvince;
  }


  public String getOpenProvince() {
    return this.openProvince;
  }


  public void setOpenCity(String openCity) {
    this.openCity = openCity;
  }


  public String getOpenCity() {
    return this.openCity;
  }


  public void setBankBranch(String bankBranch) {
    this.bankBranch = bankBranch;
  }


  public String getBankBranch() {
    return this.bankBranch;
  }


  public void setIdType(String idType) {
    this.idType = idType;
  }


  public String getIdType() {
    return this.idType;
  }


  public void setIdNo(String idNo) {
    this.idNo = idNo;
  }


  public String getIdNo() {
    return this.idNo;
  }


  public void setPayState(String payState) {
    this.payState = payState;
  }


  public String getPayState() {
    return this.payState;
  }


  public void setBankName(String bankName) {
    this.bankName = bankName;
  }


  public String getBankName() {
    return this.bankName;
  }


  public void setCny(String cny) {
    this.cny = cny;
  }


  public String getCny() {
    return this.cny;
  }


  public void setRemark(String remark) {
    this.remark = remark;
  }


  public String getRemark() {
    return this.remark;
  }


  public void setTransDesc(String transDesc) {
    this.transDesc = transDesc;
  }


  public String getTransDesc() {
    return this.transDesc;
  }


  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }


  public Date getCreateTime() {
    return this.createTime;
  }


  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }


  public Date getUpdateTime() {
    return this.updateTime;
  }


  public void setVersion(String version) {
    this.version = version;
  }


  public String getVersion() {
    return this.version;
  }


  public void setChannelId(String channelId) {
    this.channelId = channelId;
  }


  public String getChannelId() {
    return this.channelId;
  }


  public void setThirdReturnCode(String thirdReturnCode) {
    this.thirdReturnCode = thirdReturnCode;
  }


  public String getThirdReturnCode() {
    return this.thirdReturnCode;
  }


  public void setPayType(Integer payType) {
    this.payType = payType;
  }


  public Integer getPayType() {
    return this.payType;
  }


  public void setItemId(String itemId) {
    this.itemId = itemId;
  }


  public String getItemId() {
    return this.itemId;
  }


  public void setMerOrderNo(String merOrderNo) {
    this.merOrderNo = merOrderNo;
  }


  public String getMerOrderNo() {
    return this.merOrderNo;
  }


@Override
public String toString() {
	return "PayOrder [ecoOrderId=" + ecoOrderId + ", userUuid="
			+ userUuid + ", batchNo=" + batchNo + ", accNo=" + accNo
			+ ", accName=" + accName + ", amount=" + amount + ", mobile="
			+ mobile + ", openProvince=" + openProvince + ", openCity="
			+ openCity + ", bankBranch=" + bankBranch + ", idType=" + idType
			+ ", idNo=" + idNo + ", payState=" + payState + ", bankName="
			+ bankName + ", cny=" + cny + ", remark=" + remark + ", transDesc="
			+ transDesc + ", createTime=" + createTime + ", updateTime="
			+ updateTime + ", version=" + version + ", channelId=" + channelId
			+ ", thirdReturnCode=" + thirdReturnCode + ", payType=" + payType
			+ ", itemId=" + itemId + ", merOrderNo=" + merOrderNo + "]";
}

}
