package com.moerlong.carloan.modular.paybackMgr.entity.enums;

public enum RepaymentChangeType {

    CHANGE_TYPE_OVERDUE(0, "逾期变更"),
    CHANGE_TYPE_EARLY_PAY(1, "提前还款变更"),
    ;

    // 成员变量
    private int value;
    private String desc;

    private RepaymentChangeType(int value, String desc) {
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

    public static RepaymentChangeType getEnumsByValue(int value) {
        RepaymentChangeType[] arrays = values();
        for (RepaymentChangeType type : arrays) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
}
