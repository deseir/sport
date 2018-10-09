package com.moerlong.carloan.modular.loan.entity.vo;
/**
 * 贷款详情信息类
 * @author moerlong
 *
 */

import java.math.BigDecimal;

public class AppInforVO {
	//订单编号
	private Integer applyId;
	//贷款金额
	private BigDecimal applyAmount;
	//贷款类型
	private Integer productType;
	//贷款期限
	private Integer applyPeriod;
	//客户姓名
	private String custName;
	//客户性别
	private Integer sex;
	//身份证号码
	private String custIdNo;
	//汽车牌照
	private String carNum;
	/**
	 * @return the applyId
	 */
	public Integer getApplyId() {
		return applyId;
	}
	/**
	 * @param applyId the applyId to set
	 */
	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}
	/**
	 * @return the applyAmount
	 */
	public BigDecimal getApplyAmount() {
		return applyAmount;
	}
	/**
	 * @param applyAmount the applyAmount to set
	 */
	public void setApplyAmount(BigDecimal applyAmount) {
		this.applyAmount = applyAmount;
	}
	/**
	 * @return the productType
	 */
	public Integer getProductType() {
		return productType;
	}
	/**
	 * @param productType the productType to set
	 */
	public void setProductType(Integer productType) {
		this.productType = productType;
	}
	/**
	 * @return the applyPeriod
	 */
	public Integer getApplyPeriod() {
		return applyPeriod;
	}
	/**
	 * @param applyPeriod the applyPeriod to set
	 */
	public void setApplyPeriod(Integer applyPeriod) {
		this.applyPeriod = applyPeriod;
	}
	/**
	 * @return the custName
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * @param custName the custName to set
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	/**
	 * @return the sex
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**
	 * @return the custIdNo
	 */
	public String getCustIdNo() {
		return custIdNo;
	}
	/**
	 * @param custIdNo the custIdNo to set
	 */
	public void setCustIdNo(String custIdNo) {
		this.custIdNo = custIdNo;
	}
	/**
	 * @return the carNum
	 */
	public String getCarNum() {
		return carNum;
	}
	/**
	 * @param carNum the carNum to set
	 */
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	
	
}
