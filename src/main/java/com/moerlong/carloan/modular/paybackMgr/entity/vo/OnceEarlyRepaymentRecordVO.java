package com.moerlong.carloan.modular.paybackMgr.entity.vo;

import com.moerlong.carloan.modular.paybackMgr.entity.OnceEarlyRepaymentRecord;

public class OnceEarlyRepaymentRecordVO extends OnceEarlyRepaymentRecord{

    private String contractNo;

    private String custName;

    private String custMobile;

    private String custIdNo;

    /**
     * 当前还款状态 1--未开始  2--还款中  3--部分还款  10--申请提前还款 20--业务经理审批 30--财务确认 40--财务经理审核  50--已还完
     */
    private Integer curStatus;

    /**
     * 当前状态描述
     */
    private String curStatusDesc;

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustMobile() {
        return custMobile;
    }

    public void setCustMobile(String custMobile) {
        this.custMobile = custMobile;
    }

    public String getCustIdNo() {
        return custIdNo;
    }

    public void setCustIdNo(String custIdNo) {
        this.custIdNo = custIdNo;
    }

    public Integer getCurStatus() {
        return curStatus;
    }

    public void setCurStatus(Integer curStatus) {
        this.curStatus = curStatus;
    }

    public String getCurStatusDesc() {
        return curStatusDesc;
    }

    public void setCurStatusDesc(String curStatusDesc) {
        this.curStatusDesc = curStatusDesc;
    }
}
