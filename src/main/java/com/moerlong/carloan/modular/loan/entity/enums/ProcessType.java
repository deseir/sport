package com.moerlong.carloan.modular.loan.entity.enums;

public enum ProcessType {

	SUCCESS(0, "后续成功"),
	FAIL(1, "后续失败"),
	BACK(2, "后续驳回"),
	PRE(3, "前置"),
	HANDING(4, "处理中"),
	SYNC(5, "同步节点");


	// 成员变量
	private int value;
	private String desc;

	private ProcessType(int value, String desc) {
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

	public static ProcessType getEnumsByValue(int value) {
		ProcessType[] arrays = values();
		for (ProcessType type : arrays) {
			if (type.getValue() == value) {
				return type;
			}
		}
		return null;
	}
}
