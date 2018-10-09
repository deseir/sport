package com.moerlong.carloan.modular.payMgr.entity.vo;

import com.moerlong.carloan.modular.payMgr.entity.PayDetailInfo;

public class PayDetailInfoVO extends PayDetailInfo{

    /**
     * 客户姓名
     */
    private String custName;

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }
}
