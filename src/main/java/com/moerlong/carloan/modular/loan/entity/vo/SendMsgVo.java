package com.moerlong.carloan.modular.loan.entity.vo;

import com.moerlong.carloan.common.persistence.model.User;

public class SendMsgVo {
    private String operName;
    private Long applyCode;
    private String custName;
    private String account;
    private String mobile;

    public SendMsgVo() {
    }

    public SendMsgVo(String operName, Long applyCode, String custName, String account, String mobile) {
        this.operName = operName;
        this.applyCode = applyCode;
        this.custName = custName;
        this.account = account;
        this.mobile = mobile;
    }

    public SendMsgVo(User user) {
        this.operName = user.getName();
        this.account = user.getAccount();
        this.mobile = user.getPhone();
    }



    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }


    public Long getApplyCode() {
        return applyCode;
    }

    public void setApplyCode(Long applyCode) {
        this.applyCode = applyCode;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
