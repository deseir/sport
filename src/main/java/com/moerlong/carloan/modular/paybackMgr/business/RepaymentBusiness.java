package com.moerlong.carloan.modular.paybackMgr.business;

public interface RepaymentBusiness {

    /**
     * 自动扣款
     */
    public void autoDeductMoneyForRepayment();


    /**
     * 更新还款计划表状态
     */
    public void refreshRepaymentPlanStatus();

    /**
     * 查询支付中状态
     */
    public void refreshAutoDeductMoneyResult();

    /**
     * 财务统计
     */
    public void statisFinance();

    /**
     * 财务核账
     */
    public void checkFinance();

    /**
     * 客户还款短信提前提醒
     */
    public void autoRemainCustomerForRepayment();

    /**
     * 计算逾期违约金
     */
    public void calcOverdue();
}
