package com.moerlong.carloan.modular.paybackMgr.entity.enums;

public enum RepaymentType {

    PAY_TYPE_AUTO_PLAN(0, "自动还款"),
    PAY_TYPE_MANUAL_DEDUCT_PLAN(1, "手动代扣"),
    PAY_TYPE_MANUAL_PUBLIC_PLAN(2, "对公转账"),
    PAY_TYPE_MANUAL_BALANCE(4, "手工冲账"),
    PAY_TYPE_PRE_FEE(3, "扣取前期费用-系统自动"),
    PAY_TYPE_PRE_FEE_MANUAL(6, "扣取前期费用-手工代扣"),
    PAY_TYPE_PRE_FEE_PUBLIC(7, "扣取前期费用-对公转账"),
    PAY_TYPE_RECEPTION_CHARGE_FEE(5, "扣取前台服务费"),
    PAY_TYPE_RECEPTION_CHARGE_FEE_MANUAL(8, "扣取前台服务费-手工代扣"),
    PAY_TYPE_RECEPTION_CHARGE_FEE_PUBLIC(9, "扣取前台服务费-对公转账"),

    PAY_TYPE_ONCE_EARLY_CAPITAL(10, "提前还款剩余租赁本金-对公转账"),
    PAY_TYPE_ONCE_EARLY_INTEREST(11, "提前还款剩余天津费用-对公转账"),
    PAY_TYPE_ONCE_EARLY_CHARGE(12, "提前还款剩余手续费-对公转账"),
    PAY_TYPE_ONCE_EARLY_BREACH(13, "提前还款违约金-对公转账"),

    PAY_TYPE_ONCE_CHARGE(14, "一次性手续费"),
    PAY_TYPE_ONCE_CHARGE_MAN(15, "一次性手续费-手动代扣"),
    PAY_TYPE_ONCE_CHARGE_PUBLIC(16, "一次性手续费-对公转账")

    ;

    // 成员变量
    private int value;
    private String desc;

    private RepaymentType(int value, String desc) {
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

    public static RepaymentType getEnumsByValue(int value) {
        RepaymentType[] arrays = values();
        for (RepaymentType type : arrays) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
}
