package com.moerlong.carloan.modular.paybackMgr.entity.vo;

import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentPayInfo;

import java.math.BigDecimal;

public class RepaymentPayInfoVO extends RepaymentPayInfo {

    private String custName;

    private BigDecimal loanAmount;

    private String receptionDepart ;

    private String receptionManager ;

    private String transSource;


    public String getReceptionDepart() {
        return receptionDepart;
    }

    public void setReceptionDepart(String receptionDepart) {
        this.receptionDepart = receptionDepart;
    }

    public String getReceptionManager() {
        return receptionManager;
    }

    public void setReceptionManager(String receptionManager) {
        this.receptionManager = receptionManager;
    }

    public String getTransSource() {
        return transSource;
    }

    public void setTransSource(String transSource) {
        this.transSource = transSource;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }
}
