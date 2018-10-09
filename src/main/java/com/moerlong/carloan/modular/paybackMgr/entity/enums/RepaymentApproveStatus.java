package com.moerlong.carloan.modular.paybackMgr.entity.enums;

public enum RepaymentApproveStatus {

	NO_BEGIN(1, "未开始还款"),

	REPAYMENTING(2, "还款中"),

	REPAYMENT_PART(3, "部分还款"),

	REPAYMENT_ONCE_EARLY_CREATE(10, "申请提前还款"),

	REPAYMENT_ONCE_EARLY_BUSS_APPROVE(20, "业务经理审批"),

	REPAYMENT_ONCE_EARLY_BUSS_APPROVE_FAIL(21, "业务经理审批失败"),

	REPAYMENT_ONCE_EARLY_FINANCE(30, "财务确认"),

	REPAYMENT_ONCE_EARLY_FINANCE_FAIL(31, "财务审批失败"),

	REPAYMENT_ONCE_EARLY_FIN_BUSS_APPROVE(40, "财务经理审核"),

	REPAYMENT_ONCE_EARLY_FIN_BUSS_APPROVE_FAIL(41, "财务经理审核失败"),

	REPAYMENT_DONE(5, "已还完");

	// 成员变量
	private int value;
	private String desc;

	private RepaymentApproveStatus(int value, String desc) {
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

	public static RepaymentApproveStatus getEnumsByValue(int value) {
		RepaymentApproveStatus[] arrays = values();
		for (RepaymentApproveStatus type : arrays) {
			if (type.getValue() == value) {
				return type;
			}
		}
		return null;
	}
}
