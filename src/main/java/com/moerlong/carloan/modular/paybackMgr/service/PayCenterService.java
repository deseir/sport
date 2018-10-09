package com.moerlong.carloan.modular.paybackMgr.service;

import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.modular.paybackMgr.txservice.vo.PayCenterPayResultVO;

public interface PayCenterService {

    public ResultVO<PayCenterPayResultVO> deductMoney(String batchNo, Long uid, String bankCardNo, String userName,
                                                      String bindingMobile, String idNo, String openProvince, String openCity, String bankBranch, String idType,
                                                      String amount, String bankName);

    ResultVO<PayCenterPayResultVO> queryDeductMoneyStatus(String batchNo);

    ResultVO<PayCenterPayResultVO> payMoney(String batchNo, Long uid, String bankCardNo, String userName,
                                            String bindingMobile, String idNo, String openProvince, String openCity, String bankBranch, String idType,
                                            String amount, String bankName);

    ResultVO<PayCenterPayResultVO> queryPayMoneyStatus(String batchNo);

    public ResultVO<PayCenterPayResultVO> deductMrlMoney(String batchNo, Long uid, String bankCardNo, String userName,
                                                         String bindingMobile, String idNo, String openProvince, String openCity, String bankBranch, String idType, String amount, String bankName);

    public ResultVO<PayCenterPayResultVO> queryMrlDeductMoneyStatus(String batchNo);
}
