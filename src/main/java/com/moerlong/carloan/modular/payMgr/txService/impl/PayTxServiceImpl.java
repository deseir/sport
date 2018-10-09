package com.moerlong.carloan.modular.payMgr.txService.impl;

import com.moerlong.carloan.modular.loan.entity.vo.SendMsgVo;
import com.moerlong.carloan.modular.payMgr.txService.PayTxService;
import com.moerlong.carloan.common.constant.PayCenterCode;
import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.modular.payMgr.entity.PayDetailInfo;
import com.moerlong.carloan.modular.payMgr.entity.PayInfo;
import com.moerlong.carloan.modular.payMgr.entity.enums.PayApproveStatus;
import com.moerlong.carloan.modular.payMgr.service.PayDetailInfoService;
import com.moerlong.carloan.modular.payMgr.service.PayInfoService;
import com.moerlong.carloan.modular.payMgr.service.SMSService;
import com.moerlong.carloan.modular.paybackMgr.entity.enums.PayStatus;
import com.moerlong.carloan.modular.paybackMgr.service.IOrderSequenceService;
import com.moerlong.carloan.modular.paybackMgr.service.PayCenterService;
import com.moerlong.carloan.modular.paybackMgr.service.RepaymentInfoService;
import com.moerlong.carloan.util.ParamConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
public class PayTxServiceImpl implements PayTxService{

    private static Logger LOG = LoggerFactory.getLogger(PayTxServiceImpl.class);

    @Resource
    private PayInfoService payInfoService;

    @Resource
    private PayDetailInfoService payDetailInfoService;

    @Resource
    private IOrderSequenceService orderSequenceService;

    @Resource
    private PayCenterService payCenterService;

    @Resource
    private RepaymentInfoService repaymentInfoService;

    @Resource
    private SMSService smsService;


    @Override
    public ErrorCode refreshPayStatus(Long payId, String payCode, String payMsg) {
        int numFlag = 0;		//0--表示第一次放款  1--表示第二次放款
        int payFlag = 0;		//1--成功 2--失败 3--处理中
        int isFinished = 0;		//0--放款未完成 1--放款完成

        PayDetailInfo payDetailInfo = payDetailInfoService.selectById(payId);
        if(payDetailInfo == null){
            LOG.info("[===>>>不存在放款详情单={}======]", payId);
            return ErrorCode.PAY_DETAIL_INFO_NOT_EXIST;
        }

        PayInfo payInfo = payInfoService.selectById(payDetailInfo.getPayId());
        if(payInfo.getPayedAmount().compareTo(BigDecimal.ZERO) > 0){
            numFlag = 1;
        }

        PayDetailInfo info = new PayDetailInfo();
        info.setId(payId);
        Date now = new Date();
        info.setUpdateTime(now);
        info.setPayCode(payCode);
        info.setPayMsg(payMsg);

        boolean ret;
        List<SendMsgVo> mobileList = new ArrayList<>();
        Map<String, Object> param = new HashMap<String, Object>();

        List<SendMsgVo> m1 = ParamConstants.getValue(ParamConstants.ROLE_NQ);
        List<SendMsgVo> m2 = ParamConstants.getValue(ParamConstants.ROLE_FINA);
        List<SendMsgVo> m3 = ParamConstants.getValue(ParamConstants.ROLE_FINA_MA);
        List<SendMsgVo> m4 = ParamConstants.getValue(ParamConstants.ROLE_NQ);

        PayInfo temp = new PayInfo();
        temp.setId(payDetailInfo.getPayId());

        if (payCode.equals(PayCenterCode.DOING)) {
            LOG.info("[===>>>放款单={},支付处理中，无需处理状态，支付状态={}======]", payId, payCode + payMsg);
            info.setPayingNum(payDetailInfo.getPayingNum()+1);
            payDetailInfoService.update(info);
            if(info.getPayingNum() == 12 || info.getPayingNum() == 24 || info.getPayingNum() == 36){
                //短信通知
                mobileList.addAll(m1);
                mobileList.addAll(m2);
                mobileList.addAll(m3);
                param.put("name", payInfo.getCustName());
                param.put("hour", (info.getPayingNum())*5/60);
                ret = smsService.sendMsgSMS(mobileList, SMSService.PAY_PAYING_TIP, param);
                if (ret == false) {
                    LOG.error("===>>>放款单：{}, 放款结果，发送短信通知失败", payId);
                } else {
                    LOG.info("===>>>放款单：{}, 放款结果，发送短信通知成功", payId);
                }
            }
            return ErrorCode.REPAYMENT_PAY_DOING;
        } else if (payCode.equals(PayCenterCode.SUCCESS)) {
            LOG.info("[===>>>放款单={},支付成功，支付响应信息={}======]", payId, payCode + payMsg);
            info.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
            payDetailInfoService.update(info);

            temp.setNotPayAmount(payInfo.getNotPayAmount().subtract(payDetailInfo.getCurPayAmount()));
            temp.setPayedAmount(payInfo.getPayedAmount().add(payDetailInfo.getCurPayAmount()));
            if(payDetailInfo.getPayNum() == 1){
                temp.setPayStatus(PayApproveStatus.FIRST_PAY_SUCCESS.getValue());
                temp.setPayStatusDesc(PayApproveStatus.FIRST_PAY_SUCCESS.getDesc());
            }else{
                temp.setPayStatus(PayApproveStatus.SECOND_PAY_SUCCESS.getValue());
                temp.setPayStatusDesc(PayApproveStatus.SECOND_PAY_SUCCESS.getDesc());
            }
            if(temp.getNotPayAmount().compareTo(BigDecimal.ZERO) == 0){
                //放款完成
                temp.setPayStatus(PayApproveStatus.PAY_SUCCESS.getValue());
                temp.setPayStatusDesc(PayApproveStatus.PAY_SUCCESS.getDesc());
                isFinished = 1;		//表示放款完成
            }

            temp.setUpdateTime(now);
            payInfoService.update(temp);
            payFlag = 1;

        } else {
            LOG.info("[===>>>放款单={},支付失败,支付响应信息={}======]", payId, payCode + payMsg);
            info.setPayStatus(PayStatus.PAY_FAIL.getValue());
            payDetailInfoService.update(info);

            if(payDetailInfo.getPayNum() == 1){
                temp.setPayStatus(PayApproveStatus.FIRST_PAY_FAIL.getValue());
                temp.setPayStatusDesc(PayApproveStatus.FIRST_PAY_FAIL.getDesc());
            }else{
                temp.setPayStatus(PayApproveStatus.SECOND_PAY_FAIL.getValue());
                temp.setPayStatusDesc(PayApproveStatus.SECOND_PAY_FAIL.getDesc());
            }

            temp.setUpdateTime(now);
            payInfoService.update(temp);

            payFlag = 2;
        }

        mobileList = new ArrayList<>();
        param = new HashMap<String, Object>();
        param.put("name", payInfo.getCustName());
        if(payDetailInfo.getPayNum() == 1){
            param.put("time", "一");
        }else{
            param.put("time", "二");
        }
        if (payFlag == 1) {
            param.put("result", "成功");
            mobileList.addAll(m1);
            mobileList.addAll(m2);
            mobileList.addAll(m3);

        } else if(payFlag == 2){
            param.put("result", "失败");
            mobileList.addAll(m1);
            mobileList.addAll(m2);
            mobileList.addAll(m3);
            mobileList.addAll(m4);
        }

        ret = smsService.sendMsgSMS(mobileList, SMSService.PAY_RESULT, param);

        if (ret == false) {
            LOG.error("===>>>放款单：{}, 放款结果，发送短信通知失败", payId);
        } else {
            LOG.info("===>>>放款单：{}, 放款结果，发送短信通知成功", payId);
        }


        if(payFlag == 1){
            if(numFlag == 0){
                //生成还款计划 扣除前期费用
                payInfoService.paySuccessOpt(payInfo.getId());
            }
            return ErrorCode.SUCCESS;
        }
        else{
            return ErrorCode.REPAYMENT_PAY_FAIL;
        }
    }
}
