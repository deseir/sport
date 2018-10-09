package com.moerlong.carloan.modular.paybackMgr.txservice.vo;

import java.io.Serializable;

public class PayCenterPayResultVO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String SUCC_CODE = "0000";

	private Integer status;

	private String resultCode;

	private String message;

	private String payPlatform;

	private String remark;

	public boolean isSuccess() {
		if (resultCode.equals(SUCC_CODE)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the resultCode
	 */
	public String getResultCode() {
		return resultCode;
	}

	/**
	 * @param resultCode
	 *            the resultCode to set
	 */
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the payPlatform
	 */
	public String getPayPlatform() {
		return payPlatform;
	}

	/**
	 * @param payPlatform
	 *            the payPlatform to set
	 */
	public void setPayPlatform(String payPlatform) {
		this.payPlatform = payPlatform;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
