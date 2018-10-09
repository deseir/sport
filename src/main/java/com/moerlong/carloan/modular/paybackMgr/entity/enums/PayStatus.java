package com.moerlong.carloan.modular.paybackMgr.entity.enums;

public enum PayStatus {

	CREATE(1, "创建"),
	SMS_FAIL(2, "发送验证码失败"),
	SMS_SEND(3, "已发送验证码"),
	PAY_FAIL(4, "失败"),
	PAYING(5, "支付处理中"),
	PAY_SUCCESS(6, "支付成功"),
	REPAYMENTING(7, "还款处理中"),
	REPAYMENT_FAIL(8, "还款失败"),
	REPAYMENT_SUCCESS(9, "还款成功");

	// 成员变量
	private int value;
	private String desc;

	private PayStatus(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	/**
	 * Returns the value of the value header field.
	 * 
	 * @return the field value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Sets the value of the value field of this ServiceType to the specified
	 * value.
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * Returns the value of the desc header field.
	 * 
	 * @return the field desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Sets the value of the desc field of this ServiceType to the specified
	 * value.
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static PayStatus getEnumsByValue(int value) {
		PayStatus[] arrays = values();
		for (PayStatus type : arrays) {
			if (type.getValue() == value) {
				return type;
			}
		}
		return null;
	}
}
