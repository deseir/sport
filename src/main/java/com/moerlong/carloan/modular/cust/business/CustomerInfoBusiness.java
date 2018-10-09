package com.moerlong.carloan.modular.cust.business;

import java.math.BigDecimal;

public interface CustomerInfoBusiness {

    /**
     * 新增客户查询
     * @param name
     * @param idcard
     * @return
     */
    public Object searchCustHistory(String name, String idcard);

    /**
     * 新增客户
     * @param operatorId
     * @param operatorName
     * @param name
     * @param idcard
     * @return
     */
    public Object custAddInfo(Long operatorId, String operatorName, String name, String idcard,BigDecimal leftAmount);

}
