package com.moerlong.carloan.modular.paybackMgr.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *  小虾借款宝付代扣
 * @author suxiaobo
 *
 */
public class CostOrder {
	
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


	  /**支付金额**/

	  private BigDecimal amount;


	  /**手机号**/

	  private String mobile;
	  
	  /**身份证**/

	  private String idNo;
	  
	  /**支付状态 0000代表成功**/

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

	  /**交易时间**/
	  private String tradeDate;

	  private String sn;
	  
	public String getSn() {
		return sn;
	}


	public void setSn(String sn) {
		this.sn = sn;
	}


	public String getTradeDate() {
		return tradeDate;
	}


	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}


	public Long getEcoOrderId() {
		return ecoOrderId;
	}


	public void setEcoOrderId(Long ecoOrderId) {
		this.ecoOrderId = ecoOrderId;
	}


	public String getUserUuid() {
		return userUuid;
	}


	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}


	public String getBatchNo() {
		return batchNo;
	}


	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}


	public String getAccNo() {
		return accNo;
	}


	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}


	public String getAccName() {
		return accName;
	}


	public void setAccName(String accName) {
		this.accName = accName;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getIdNo() {
		return idNo;
	}


	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}


	public String getPayState() {
		return payState;
	}


	public void setPayState(String payState) {
		this.payState = payState;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public String getCny() {
		return cny;
	}


	public void setCny(String cny) {
		this.cny = cny;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getTransDesc() {
		return transDesc;
	}


	public void setTransDesc(String transDesc) {
		this.transDesc = transDesc;
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


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	public String getChannelId() {
		return channelId;
	}


	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}


	public String getThirdReturnCode() {
		return thirdReturnCode;
	}


	public void setThirdReturnCode(String thirdReturnCode) {
		this.thirdReturnCode = thirdReturnCode;
	}


	public Integer getPayType() {
		return payType;
	}


	public void setPayType(Integer payType) {
		this.payType = payType;
	}


	public String getItemId() {
		return itemId;
	}


	public void setItemId(String itemId) {
		this.itemId = itemId;
	}


	public String getMerOrderNo() {
		return merOrderNo;
	}


	public void setMerOrderNo(String merOrderNo) {
		this.merOrderNo = merOrderNo;
	}

	  
}
