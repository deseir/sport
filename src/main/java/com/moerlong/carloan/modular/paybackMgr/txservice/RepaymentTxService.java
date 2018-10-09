package com.moerlong.carloan.modular.paybackMgr.txservice;

import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentPlanInfo;

public interface RepaymentTxService {

    /**
     * 自动扣款
     * @param planInfo
     * @return
     */
    public ResultVO<Long> autoDeductMoneyForRepayment(RepaymentPlanInfo planInfo);


    /**
     * 分配金额--还款计划表
     * @param payId
     * @return
     */
    public ResultVO<Object> allotPayUpdatePlanInfo(Long payId, boolean earlyPayFlag);


    /**
     * 手工还款
     * @param repaymentId
     * @param period
     * @param payType
     * @return
     */
    public ResultVO<Object> manualDeductMoney(Long repaymentId, Integer period, Integer payType);

    /**
     * 更新支付状态
     *
     * @param payCode
     * @param payMsg
     * @return
     */
    ErrorCode refreshPayStatus(Long payId, String payCode, String payMsg);

}
