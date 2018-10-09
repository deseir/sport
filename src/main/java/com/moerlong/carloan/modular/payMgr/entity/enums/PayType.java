package com.moerlong.carloan.modular.payMgr.entity.enums;

public enum PayType {

    PAY_TYPE_SYS_PAY(0, "系统放款"),
    PAY_TYPE_MANUAL_PAY(1, "手动代付"),
    PAY_TYPE_MANUAL_BALANCE(2, "手工冲账");

    // 成员变量
    private int value;
    private String desc;

    private PayType(int value, String desc) {
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

    public static PayType getEnumsByValue(int value) {
        PayType[] arrays = values();
        for (PayType type : arrays) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
}
