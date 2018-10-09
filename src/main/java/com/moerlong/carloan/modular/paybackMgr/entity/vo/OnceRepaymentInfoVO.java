package com.moerlong.carloan.modular.paybackMgr.entity.vo;

import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentPlanInfo;

import java.math.BigDecimal;

public class OnceRepaymentInfoVO {

    /**
     * 一次性还款违约金费率
     */
    private BigDecimal onceRepaymentRate;

    /**
     * 一次性提前还款本金
     */
    private BigDecimal onceRepaymentCapital;

    /**
     * 一次性提前还款手续费
     */
    private BigDecimal onceRepaymentCharge;

    /**
     * 一次性提前还款违约金
     */
    private BigDecimal onceRepaymentBreach;

    /**
     * 一次性提前还款总额
     */
    private BigDecimal onceRepaymentTotal;

    private RepaymentPlanInfo plan;


    public BigDecimal getOnceRepaymentRate() {
        return onceRepaymentRate;
    }

    public void setOnceRepaymentRate(BigDecimal onceRepaymentRate) {
        this.onceRepaymentRate = onceRepaymentRate;
    }

    public BigDecimal getOnceRepaymentCapital() {
        return onceRepaymentCapital;
    }

    public void setOnceRepaymentCapital(BigDecimal onceRepaymentCapital) {
        this.onceRepaymentCapital = onceRepaymentCapital;
    }

    public BigDecimal getOnceRepaymentCharge() {
        return onceRepaymentCharge;
    }

    public void setOnceRepaymentCharge(BigDecimal onceRepaymentCharge) {
        this.onceRepaymentCharge = onceRepaymentCharge;
    }

    public BigDecimal getOnceRepaymentBreach() {
        return onceRepaymentBreach;
    }

    public void setOnceRepaymentBreach(BigDecimal onceRepaymentBreach) {
        this.onceRepaymentBreach = onceRepaymentBreach;
    }

    public BigDecimal getOnceRepaymentTotal() {
        return onceRepaymentTotal;
    }

    public void setOnceRepaymentTotal(BigDecimal onceRepaymentTotal) {
        this.onceRepaymentTotal = onceRepaymentTotal;
    }

    public RepaymentPlanInfo getPlan() {
        return plan;
    }

    public void setPlan(RepaymentPlanInfo plan) {
        this.plan = plan;
    }
}
