package com.moerlong.carloan.modular.paybackMgr.entity.enums;

public enum RepaymentStatus {

	NO_BEGIN(1, "未开始还款"),

	REPAYMENTING(2, "还款中"),

	REPAYMENT_PART(3, "部分还款"),

	REPAYMENT_OVERDUE(4, "已逾期"),

	REPAYMENT_DONE(5, "已还完");

	// 成员变量
	private int value;
	private String desc;

	private RepaymentStatus(int value, String desc) {
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

	public static RepaymentStatus getEnumsByValue(int value) {
		RepaymentStatus[] arrays = values();
		for (RepaymentStatus type : arrays) {
			if (type.getValue() == value) {
				return type;
			}
		}
		return null;
	}
}
