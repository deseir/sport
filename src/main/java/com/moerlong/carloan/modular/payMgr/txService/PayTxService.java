package com.moerlong.carloan.modular.payMgr.txService;

import com.moerlong.carloan.common.vo.ErrorCode;

public interface PayTxService {


    ErrorCode refreshPayStatus(Long payId, String payCode, String payMsg);
}
