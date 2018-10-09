package com.moerlong.carloan.modular.payMgr.business.impl;

import com.alibaba.fastjson.JSON;
import com.moerlong.carloan.modular.payMgr.business.PayBussiness;
import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.modular.payMgr.entity.PayDetailInfo;
import com.moerlong.carloan.modular.payMgr.service.PayDetailInfoService;
import com.moerlong.carloan.modular.payMgr.txService.PayTxService;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentPayInfo;
import com.moerlong.carloan.modular.paybackMgr.entity.enums.PayStatus;
import com.moerlong.carloan.modular.paybackMgr.service.PayCenterService;
import com.moerlong.carloan.modular.paybackMgr.txservice.vo.PayCenterPayResultVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PayBusinessImpl implements PayBussiness{

    private static Logger LOG = LoggerFactory.getLogger(PayBusinessImpl.class);

    @Resource
    private PayDetailInfoService payDetailInfoService;

    @Resource
    private PayCenterService payCenterService;

    @Resource
    private PayTxService payTxService;

    @Override
    public void refreshAutoPayMoneyResult() {
        try {
            List<PayDetailInfo> pays = payDetailInfoService.selectByStatus(PayStatus.PAYING.getValue());

            if(!(pays != null && pays.size() > 0)){
                LOG.info("[===>>>无处理中的代付单！！！======]");
                return ;
            }

            for (PayDetailInfo payInfo : pays) {

                ResultVO<PayCenterPayResultVO> centerResultVO;

                try {
                    centerResultVO = payCenterService.queryPayMoneyStatus(payInfo.getSerialNo());
                } catch (Exception e) {
                    LOG.error("[===>>>查询代付支付结果调用支付中心异常,batchNo:{}]======,excp:{}", payInfo.getSerialNo(), e);
                    continue;
                }

                if (centerResultVO == null || centerResultVO.getData() == null) {
                    LOG.error("[===>>>放款单：{},的请求支付中心查询代付支付结果时返回信息centerResultVO为空======]", payInfo.getId());
                    continue;
                }

                if (StringUtils.isBlank(centerResultVO.getData().getResultCode())) {
                    LOG.error("[===>>>放款单：{}.batchNo={}的请求支付中心查询代付支付结果时返回的错误码为空resultCode={},不处理该笔订单======]",
                            payInfo.getId(), payInfo.getSerialNo(), JSON.toJSONString(centerResultVO));
                    continue;
                }

                try{
                    ErrorCode code = payTxService.refreshPayStatus(payInfo.getId(),
                            centerResultVO.getData().getResultCode(), centerResultVO.getData().getMessage());
                    LOG.info("[===>>>放款单：{},根据查询代扣支付结果更新支付信息处理结果：{}，代扣查询结果：{}======]", payInfo.getId(), code,
                            JSON.toJSONString(centerResultVO));

                }catch (Exception e) {
                    LOG.error("[===>>>放款单：{}.处理代扣支付结果更新支付状态信息处理异常。centerResultVO={}======],excp:{}",
                            payInfo.getId(), JSON.toJSONString(centerResultVO), e);
                    continue;
                }
            }
        }catch(Exception e){
            LOG.error("[===>>>获取所有代扣处理中的订单，并更新放款信息发生异常======],excp={}", e);
        }
    }
}
