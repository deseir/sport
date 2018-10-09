package com.moerlong.carloan.modular.payMgr.entity.enums;

public enum PayApproveStatus {

	CREATE(0, "创建"),
	
	NQZG_APPROVE(5,"内勤主管审批成功"),
	
	NQZG_APPROVE_FAIL(6,"内勤主管审批失败"),

	BUSS_MANAGER_APPROVE(10, "业务经理审批成功"),

	BUSS_MANAGER_APPROVE_FAIL(11, "业务经理审批失败"),

	FIRST_FINANCE_APPROVE(20, "财务会计提交成功"),

	FIRST_FINANCE_APPROVE_FAIL(21, "财务会计提交失败"),

	FIRST_FINANCE_MANAGER_APPROVE(30, "财务经理首次审批成功"),

	FIRST_FINANCE_MANAGER_APPROVE_FAIL(31, "财务经理首次审批失败"),

	FIRST_PAY_SUCCESS(40, "首次放款成功"),

	FIRST_PAY_FAIL(41, "首次放款失败"),

	FIRST_PAY_PAYING(42, "首次放款支付中"),

	COST_SUCCESS(50, "扣取前期费用成功"),

	COST_FAIL(51, "扣取前期费用失败"),

	COST_PAYING(52, "扣取前期费用支付中"),

	COST_ONCE_CHARGE_SUCCESS(53, "扣取一次性手续费成功"),

	COST_ONCE_CHANGE_FAIL(58, "扣取一次性手续费失败"),

	COST_ONCE_CHANGE_PAYING(59, "扣取一次性手续费支付中"),

	COST_CHARGE_SUCCESS(54, "扣取前台手续费成功"),

	COST_CHARGE_FAIL(55, "扣取前台手续费失败"),

	COST_CHARGE_PAYING(56, "扣取前台手续费支付中"),

	CONFIRM_FEE(57, "前台财务确认"),

	SECOND_FINANCE_APPROVE(60, "财务会计二次提交成功"),

	SECOND_FINANCE_APPROVE_FAIL(61, "财务会计二次提交失败"),

	SECOND_FINANCE_MANAGER_APPROVE(70, "财务经理二次审批成功"),

	SECOND_FINANCE_MANAGER_APPROVE_FAIL(71, "财务经理二次审批失败"),

	SECOND_PAY_SUCCESS(80, "二次放款成功"),

	SECOND_PAY_FAIL(81, "二次放款失败"),

	SECOND_PAY_PAYING(82, "二次放款支付中"),

	PAY_SUCCESS(90, "放款完成");

	// 成员变量
	private int value;
	private String desc;

	private PayApproveStatus(int value, String desc) {
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

	public static PayApproveStatus getEnumsByValue(int value) {
		PayApproveStatus[] arrays = values();
		for (PayApproveStatus type : arrays) {
			if (type.getValue() == value) {
				return type;
			}
		}
		return null;
	}
}
