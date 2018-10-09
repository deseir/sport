package com.moerlong.carloan.modular.loan.entity.enums;

public enum MainStatus {

	CREATE(1000, "新增客户"),
	INTERVIEW(2000, "面审"),
	INTERVIEW_BUSS(3000, "面审主管审批"),
	INTERVIEW_REFUSE(3500, "面审拒绝"),
	AUTO_RISK(4000, "机器风控"),
	FINAL_INTERVIEW(5000, "总部终审"),
	FINAL_INTERVIEW_REFUSE(5100, "总部终审拒绝"),
	FINAL_INTERVIEW_REJECT(5200, "总部终审驳回"),
	SIGN_CONTRACT(6000, "签订合同"),
	MORTGAGE_SUCCESS(7000, "抵押登记"),
	DATA_STORE(8000,"资料存留"),
	PAY_SUCCESS(9000, "放款完成"),
	REPAY_SUCCESS(10000, "还款完成"),
	DETENTION_SUCCESS(11000, "解押登记"),
	DATA_KEEP(12000, "资料移交"),
	FINISH(13000, "债务完结");


	// 成员变量
	private int value;
	private String desc;

	private MainStatus(int value, String desc) {
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

	public static MainStatus getEnumsByValue(int value) {
		MainStatus[] arrays = values();
		for (MainStatus type : arrays) {
			if (type.getValue() == value) {
				return type;
			}
		}
		return null;
	}
}
