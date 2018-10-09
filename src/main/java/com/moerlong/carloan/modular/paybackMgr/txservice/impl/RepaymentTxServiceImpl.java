package com.moerlong.carloan.modular.paybackMgr.txservice.impl;

import com.moerlong.carloan.modular.loan.entity.vo.SendMsgVo;
import com.moerlong.carloan.modular.paybackMgr.service.*;
import com.moerlong.carloan.modular.paybackMgr.txservice.RepaymentTxService;
import com.moerlong.carloan.modular.paybackMgr.txservice.vo.PayCenterPayResultVO;
import com.moerlong.carloan.common.constant.PayCenterCode;
import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.modular.payMgr.service.SMSService;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentInfo;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentPayInfo;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentPlanInfo;
import com.moerlong.carloan.modular.paybackMgr.entity.enums.PayStatus;
import com.moerlong.carloan.modular.paybackMgr.entity.enums.RepaymentApproveStatus;
import com.moerlong.carloan.modular.paybackMgr.entity.enums.RepaymentType;
import com.moerlong.carloan.modular.paybackMgr.entity.enums.RepaymentStatus;
import com.moerlong.carloan.util.ParamConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
public class RepaymentTxServiceImpl implements RepaymentTxService{

    private static Logger LOG = LoggerFactory.getLogger(RepaymentTxServiceImpl.class);

    @Resource
    private RepaymentInfoService repaymentInfoService;

    @Resource
    private RepaymentPlanInfoService repaymentPlanInfoService;

    @Resource
    private RepaymentPayInfoService repaymentPayInfoService;

    @Resource
    private PayCenterService payCenterService;

    @Resource
    private IOrderSequenceService orderSequenceService;

    @Resource
    private SMSService smsService;

    @Override
    @Transactional
    public ResultVO<Long> autoDeductMoneyForRepayment(RepaymentPlanInfo planInfo) {

        LOG.info("===> 对计划id {} 进行扣款处理...", planInfo.getId());

        int payFlag = 0;            //1--成功 2--失败 3--处理中
        String payFailMsg = "";

        RepaymentInfo repaymentInfo = repaymentInfoService.selectInfoByPrimaryKeyForUpdate(planInfo.getRepaymentId());
        if (repaymentInfo == null) {
            LOG.error("===>[autoDeductMoneyForRepayment] not find repaylment id={}", planInfo.getRepaymentId());
            return ResultVO.build(ErrorCode.REPAYMENT_INFO_NOT_EXIST);
        }

        RepaymentPlanInfo plan = repaymentPlanInfoService.selectById(planInfo.getId());
        if (plan.getPeriodStatus().intValue() == RepaymentStatus.REPAYMENT_DONE.getValue()) {
            return ResultVO.build(ErrorCode.REPAYMENT_PAYING_EXIST);
        }

        planInfo = plan;

        // 判断是否有支付中的单子
        List<RepaymentPayInfo> payings = repaymentPayInfoService.selectRepaymentPayingsRecord(planInfo.getRepaymentId());
        if (payings != null && payings.size() > 0) {
            LOG.error("===> 发现有处于支付中的还款计划 repaymentId={}", planInfo.getRepaymentId());
            return ResultVO.build(ErrorCode.REPAYMENT_PAYING_EXIST);
        }

        BigDecimal deductMoney = planInfo.getPredictAmount().subtract(planInfo.getActualAmount()).setScale(2, BigDecimal.ROUND_UP);
        if (deductMoney.compareTo(BigDecimal.ZERO) <= 0) {
            LOG.error("[===>>>计划ID：{}，自动划扣plan还款计划实还金额不小于应还金额！]", planInfo.getId());
            return ResultVO.build(ErrorCode.REPAYMENT_PLAN_AMOUNT_ERROR);
        }

        // 创建自动划扣支付单
        String sequenceNo = orderSequenceService.getTradeSequence();;
        Date now = new Date();

        RepaymentPayInfo repaymentPayInfo = new RepaymentPayInfo();
        repaymentPayInfo.setDeptId(planInfo.getDeptId());
        repaymentPayInfo.setRepaymentId(planInfo.getRepaymentId());
        repaymentPayInfo.setRepaymentPlanId(planInfo.getId());
        repaymentPayInfo.setPayType(RepaymentType.PAY_TYPE_AUTO_PLAN.getValue());
        repaymentPayInfo.setPayTypeDesc(RepaymentType.PAY_TYPE_AUTO_PLAN.getDesc());
        repaymentPayInfo.setPayingNum(0);
        repaymentPayInfo.setRepaymentPeriodNum(planInfo.getPeriodNum());
        repaymentPayInfo.setSerialNo(sequenceNo);
        repaymentPayInfo.setAmount(deductMoney);
        repaymentPayInfo.setPayStatus(PayStatus.PAYING.getValue());
        repaymentPayInfo.setPayTime(now);
        repaymentPayInfo.setPayBank(repaymentInfo.getBankName());
        repaymentPayInfo.setPayCard(repaymentInfo.getBankCardNo());
        repaymentPayInfo.setBindingMobile(repaymentInfo.getCustMobile());
        repaymentPayInfo.setCreateTime(now);
        repaymentPayInfo.setUpdateTime(now);
        repaymentPayInfo.setIsDeleted(0);
        repaymentPayInfoService.save(repaymentPayInfo);

        LOG.info("===> 创建 还款支付记录 payId={}", repaymentPayInfo.getId());

        ResultVO<PayCenterPayResultVO> payResultVO = null;
        try{
            payResultVO = payCenterService.deductMoney(sequenceNo, null, repaymentInfo.getBankCardNo(),
                    repaymentInfo.getCustName(), repaymentInfo.getCustMobile(), repaymentInfo.getCustIdNo(), null, null,
                    null, "id", deductMoney.toString(), repaymentInfo.getBankName());
        }catch(Exception e){
            LOG.error("[===>>>计划ID：{}，调用支付中心划扣接口异常===],excp:{}", planInfo.getId(), e);
        }

        if (!(payResultVO != null && payResultVO.isSuccess())) {
            //失败
            RepaymentPayInfo pay = new RepaymentPayInfo();
            pay.setId(repaymentPayInfo.getId());
            pay.setPayStatus(PayStatus.PAY_FAIL.getValue());
            pay.setUpdateTime(now);
            repaymentPayInfoService.update(pay);
            payFlag = 2;
            payFailMsg = "调用支付接口失败";
        }

        if (payResultVO.getData().getResultCode().equals(PayCenterCode.SUCCESS)) {
            // 代扣成功，更新支付状态
            RepaymentPayInfo pay = new RepaymentPayInfo();
            pay.setId(repaymentPayInfo.getId());
            pay.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
            pay.setPayChannel(payResultVO.getData().getPayPlatform());
            pay.setPayCode(payResultVO.getData().getResultCode());
            pay.setPayMsg(payResultVO.getData().getMessage());
            pay.setUpdateTime(now);
            payFlag = 1;
            repaymentPayInfoService.update(pay);
        } else if (payResultVO.getData().getResultCode().equals(PayCenterCode.DOING)) {
            // 代扣处理中，更新支付状态为支付中

            RepaymentPayInfo pay = new RepaymentPayInfo();
            pay.setId(repaymentPayInfo.getId());
            pay.setPayStatus(PayStatus.PAYING.getValue());
            pay.setPayChannel(payResultVO.getData().getPayPlatform());
            pay.setPayCode(payResultVO.getData().getResultCode());
            pay.setPayMsg(payResultVO.getData().getMessage());
            pay.setUpdateTime(now);
            repaymentPayInfoService.update(pay);
            payFlag = 3;
        } else {
            RepaymentPayInfo pay = new RepaymentPayInfo();
            pay.setId(repaymentPayInfo.getId());
            pay.setPayStatus(PayStatus.PAY_FAIL.getValue());
            pay.setPayChannel(payResultVO.getData().getPayPlatform());
            pay.setPayCode(payResultVO.getData().getResultCode());
            pay.setPayMsg(payResultVO.getData().getMessage());
            pay.setUpdateTime(now);
            repaymentPayInfoService.update(pay);
            payFlag = 2;
            payFailMsg = pay.getPayMsg();
        }


        boolean ret;
        String smsCode = SMSService.COST_RESULT;
        List<SendMsgVo> mobileList = new ArrayList<>();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("name", repaymentInfo.getCustName());
        param.put("period", planInfo.getPeriodNum());
        param.put("money", deductMoney);

        List<SendMsgVo> m1 = ParamConstants.getValue(ParamConstants.ROLE_NQ);
        List<SendMsgVo> m2 = ParamConstants.getValue(ParamConstants.ROLE_FINA);
        List<SendMsgVo> m3 = ParamConstants.getValue(ParamConstants.ROLE_FINA_MA);
        List<SendMsgVo> m4 = ParamConstants.getValue(ParamConstants.ROLE_NQ);
        List<SendMsgVo> m5 = ParamConstants.getValue(ParamConstants.ROLE_BUSS_MA);
        if(payFlag == 1){
            param.put("result", "成功");
            mobileList.addAll(m1);
            mobileList.addAll(m2);
            mobileList.addAll(m3);
        }else if(payFlag == 2){
            param.put("result", "失败");
            mobileList.addAll(m1);
            mobileList.addAll(m2);
            mobileList.addAll(m3);
            mobileList.addAll(m4);
        }else{
            param.put("result", "处理中");
            mobileList.addAll(m1);
            mobileList.addAll(m2);
            mobileList.addAll(m3);
        }
        try {
            ret = smsService.sendMsgSMS(mobileList, smsCode, param);
            if (ret == false) {
                LOG.error("===>>>计划ID：{}, 扣款结果，发送短信通知失败", planInfo.getId());
            } else {
                LOG.info("===>>>计划ID：{}, 扣款结果，发送短信通知成功", planInfo.getId());
            }
        }catch (Exception e){
            LOG.error("===>>>计划ID：{}, 扣款结果，发送短信通知异常 {}", planInfo.getId(), e);
        }

        if(payFlag == 1){
            return ResultVO.build(ErrorCode.SUCCESS, repaymentPayInfo.getId());
        }else if(payFlag == 2){
            return ResultVO.build(ErrorCode.REPAYMENT_PAY_FAIL, repaymentPayInfo.getId());
        }else{
            return ResultVO.build(ErrorCode.REPAYMENT_PAYING_EXIST, repaymentPayInfo.getId());
        }

    }

    /**
     * 分配还款计划金额
     * @param payId
     * @param earlyPayFlag  是否是提前还款 -- false--否  true--是
     * @return
     */
    @Override
    @Transactional
    public ResultVO<Object> allotPayUpdatePlanInfo(Long payId, boolean earlyPayFlag) {

        LOG.info("[>>>>>>txService][allotPayUpdatePlanInfo({})]", payId);
        RepaymentPayInfo payInfo = repaymentPayInfoService.selectInfoByPrimaryKeyForUpdate(payId);

        if (payInfo == null) {
            LOG.error(">>>>>>txService][allotPayUpdatePlanInfo payId={} {}", payId, ErrorCode.REPAYMENT_PAY_INFO_NOT_EXIST.getMsg());
            return ResultVO.build(ErrorCode.REPAYMENT_PAY_INFO_NOT_EXIST);
        }

        RepaymentInfo repaymentInfo = repaymentInfoService.selectInfoByPrimaryKeyForUpdate(payInfo.getRepaymentId());
        RepaymentPlanInfo repaymentPlanInfo = repaymentPlanInfoService.selectById(payInfo.getRepaymentPlanId());

        if (repaymentInfo == null || repaymentPlanInfo == null) {
            return ResultVO.build(ErrorCode.REPAYMENT_INFO_NOT_EXIST);
        }

        //目前该函数只支持自动还款  不支持手动 或 一次性还款

        // 还款金额
        BigDecimal payAmount = payInfo.getAmount();


        BigDecimal leftAmount = repaymentPlanInfo.getPredictAmount().subtract(repaymentPlanInfo.getActualAmount());

        if(payAmount.compareTo(leftAmount) != 0){
            LOG.error(">>>>>>txService][allotPayUpdatePlanInfo payId={} payAmount={} leftAmount={}",
                    payId, payAmount, leftAmount);
            return ResultVO.build(ErrorCode.REPAYMENT_PAY_ALLOT_ERROR);
        }

        BigDecimal amount = repaymentPlanInfo.getPredictCapital().subtract(repaymentPlanInfo.getActualCapital());
        amount = amount.add(repaymentPlanInfo.getPredictInterest().subtract(repaymentPlanInfo.getActualInterest()));
        amount = amount.add(repaymentPlanInfo.getPredictServiceCharge().subtract(repaymentPlanInfo.getActualServiceCharge()));
        amount = amount.add(repaymentPlanInfo.getPredictCharge().subtract(repaymentPlanInfo.getActualCharge()));
        amount = amount.add(repaymentPlanInfo.getPredictPenalty().subtract(repaymentPlanInfo.getActualPenalty()));

        if(payAmount.compareTo(amount) != 0){
            LOG.error(">>>>>>txService][allotPayUpdatePlanInfo payId={} payAmount={} amount={}",
                    payId, payAmount, amount);
            return ResultVO.build(ErrorCode.REPAYMENT_PAY_ALLOT_ERROR);
        }

        Date now = new Date();
        RepaymentPlanInfo planInfo = new RepaymentPlanInfo();
        planInfo.setId(repaymentPlanInfo.getId());
        planInfo.setActualPenalty(repaymentPlanInfo.getPredictPenalty());
        planInfo.setActualServiceCharge(repaymentPlanInfo.getPredictServiceCharge());
        planInfo.setActualInterest(repaymentPlanInfo.getPredictInterest());
        planInfo.setActualCapital(repaymentPlanInfo.getPredictCapital());
        planInfo.setActualCharge(repaymentPlanInfo.getPredictCharge());
        planInfo.setActualAmount(repaymentPlanInfo.getPredictAmount());
        planInfo.setPeriodStatus(RepaymentStatus.REPAYMENT_DONE.getValue());
        planInfo.setUpdateTime(now);
        repaymentPlanInfoService.update(planInfo);

        // 更新分配状态
        RepaymentPayInfo pay = new RepaymentPayInfo();
        pay.setId(payId);
        pay.setPayStatus(PayStatus.REPAYMENT_SUCCESS.getValue());
        pay.setUpdateTime(now);
        repaymentPayInfoService.update(pay);

        //更新还款总表
        RepaymentInfo info = new RepaymentInfo();
        info.setId(repaymentInfo.getId());
        info.setActualPenalty(repaymentInfo.getActualPenalty().add(repaymentPlanInfo.getPredictPenalty().subtract(repaymentPlanInfo.getActualPenalty())).setScale(2, BigDecimal.ROUND_UP));
        info.setActualAmount(repaymentInfo.getActualAmount().add(repaymentPlanInfo.getPredictAmount().subtract(repaymentPlanInfo.getActualAmount())).setScale(2, BigDecimal.ROUND_UP));
        info.setActualInterest(repaymentInfo.getActualInterest().add(repaymentPlanInfo.getPredictInterest().subtract(repaymentPlanInfo.getActualInterest())).setScale(2, BigDecimal.ROUND_UP));
        info.setActualCapital(repaymentInfo.getActualCapital().add(repaymentPlanInfo.getPredictCapital().subtract(repaymentPlanInfo.getActualCapital())).setScale(2, BigDecimal.ROUND_UP));
        info.setActualServiceCharge(repaymentInfo.getActualServiceCharge().add(repaymentPlanInfo.getPredictServiceCharge().subtract(repaymentPlanInfo.getActualServiceCharge())).setScale(2, BigDecimal.ROUND_UP));
        info.setActualCharge(repaymentInfo.getActualCharge().add(repaymentPlanInfo.getPredictCharge().subtract(repaymentPlanInfo.getActualCharge())).setScale(2, BigDecimal.ROUND_UP));
        info.setCurrentPeriodNum(repaymentPlanInfo.getPeriodNum());
        if(earlyPayFlag == false) {
            info.setCurStatus(RepaymentApproveStatus.REPAYMENT_PART.getValue());
            info.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_PART.getDesc());
        }

        if (info.getActualAmount().compareTo(repaymentInfo.getPredictAmount()) == 0) {
            info.setCurStatus(RepaymentApproveStatus.REPAYMENT_DONE.getValue());
            info.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_DONE.getDesc());
            //TODO 这里更新借款单状态为  已还清状态
        }
        info.setUpdateTime(now);
        repaymentInfoService.update(info);

        return ResultVO.build(ErrorCode.SUCCESS);
    }

    /**
     *
     * @param repaymentId
     * @param period
     * @param payType 1--手动代扣  2--对公转账
     * @return
     */
    @Override
    public ResultVO<Object> manualDeductMoney(Long repaymentId, Integer period, Integer payType) {

        RepaymentInfo repaymentInfo = repaymentInfoService.selectInfoByPrimaryKeyForUpdate(repaymentId);
        if (repaymentInfo == null) {
            LOG.error("===>[padPlan] not find repaylment id={}", repaymentId);
            return ResultVO.build(ErrorCode.REPAYMENT_INFO_NOT_EXIST);
        }

        RepaymentPlanInfo plan = repaymentPlanInfoService.selectByPeriod(repaymentId, period);
        if(plan == null){
            LOG.error("===>[padPlan] not find repaylment plan repaymentId={} period={}", repaymentId, period);
            return ResultVO.build(ErrorCode.REPAYMENT_PLAN_INFO_NOT_EXIST);
        }
        if (plan.getPeriodStatus().intValue() == RepaymentStatus.REPAYMENT_DONE.getValue()) {
            return ResultVO.build(ErrorCode.REPAYMENT_PLAN_INFO_ALREADY_FINISH);
        }

        RepaymentPlanInfo planInfo = plan;

        // 判断是否有支付中的单子
        List<RepaymentPayInfo> payings = repaymentPayInfoService.selectRepaymentPayingsRecord(planInfo.getRepaymentId());
        if (payings != null && payings.size() > 0) {
            LOG.error("===> 发现有处于支付中的还款计划 repaymentId={}", planInfo.getRepaymentId());
            return ResultVO.build(ErrorCode.REPAYMENT_PAYING_EXIST);
        }

        BigDecimal deductMoney = planInfo.getPredictAmount().subtract(planInfo.getActualAmount()).setScale(2, BigDecimal.ROUND_UP);
        if (deductMoney.compareTo(BigDecimal.ZERO) <= 0) {
            LOG.error("[===>>>计划ID：{}，自动划扣plan还款计划实还金额不小于应还金额！]", planInfo.getId());
            return ResultVO.build(ErrorCode.REPAYMENT_PLAN_AMOUNT_ERROR);
        }

        // 创建自动划扣支付单
        Date now = new Date();

        RepaymentPayInfo repaymentPayInfo = new RepaymentPayInfo();
        repaymentPayInfo.setRepaymentId(planInfo.getRepaymentId());
        repaymentPayInfo.setRepaymentPlanId(planInfo.getId());
        if(payType == 1){
            //手动代扣
            repaymentPayInfo.setPayType(RepaymentType.PAY_TYPE_MANUAL_DEDUCT_PLAN.getValue());
            repaymentPayInfo.setPayTypeDesc(RepaymentType.PAY_TYPE_MANUAL_DEDUCT_PLAN.getDesc());
        }else{
            //对公转账
            repaymentPayInfo.setPayType(RepaymentType.PAY_TYPE_MANUAL_PUBLIC_PLAN.getValue());
            repaymentPayInfo.setPayTypeDesc(RepaymentType.PAY_TYPE_MANUAL_PUBLIC_PLAN.getDesc());
        }
        repaymentPayInfo.setPayingNum(0);
        repaymentPayInfo.setRepaymentPeriodNum(planInfo.getPeriodNum());
        repaymentPayInfo.setAmount(deductMoney);
        repaymentPayInfo.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
        repaymentPayInfo.setPayTime(now);
        repaymentPayInfo.setPayBank(repaymentInfo.getBankName());
        repaymentPayInfo.setPayCard(repaymentInfo.getBankCardNo());
        repaymentPayInfo.setBindingMobile(repaymentInfo.getCustMobile());
        repaymentPayInfo.setCreateTime(now);
        repaymentPayInfo.setUpdateTime(now);
        repaymentPayInfo.setIsDeleted(0);
        repaymentPayInfoService.save(repaymentPayInfo);

        LOG.info("===> 创建 还款支付记录 payId={}", repaymentPayInfo.getId());
        return ResultVO.build(ErrorCode.SUCCESS, (Object)repaymentPayInfo.getId());
    }

    @Override
    @Transactional
    public ErrorCode refreshPayStatus(Long payId, String payCode, String payMsg) {
        // 判断支付单
        RepaymentPayInfo payInfo = repaymentPayInfoService.selectInfoByPrimaryKeyForUpdate(payId);
        if(payInfo == null){
            LOG.info("[===>>>不存在支付单={}======]", payId);
            return ErrorCode.REPAYMENT_PAY_INFO_NOT_EXIST;
        }

        RepaymentInfo repaymentInfo = repaymentInfoService.selectById(payInfo.getRepaymentId());

        RepaymentPayInfo pay = new RepaymentPayInfo();
        pay.setId(payId);
        Date now = new Date();
        pay.setUpdateTime(now);
        pay.setPayCode(payCode);
        pay.setPayMsg(payMsg);

        if (payCode.equals(PayCenterCode.DOING)) {
            LOG.info("[===>>>支付单={},支付处理中，无需处理状态，支付状态={}======]", payId, payCode + payMsg);
            pay.setPayingNum(payInfo.getPayingNum()+1);
            repaymentPayInfoService.update(pay);
            return ErrorCode.REPAYMENT_PAY_DOING;
        } else if (payCode.equals(PayCenterCode.SUCCESS)) {
            LOG.info("[===>>>支付单={},支付成功，支付响应信息={}======]", payId, payCode + payMsg);
            pay.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
            repaymentPayInfoService.update(pay);
            return ErrorCode.SUCCESS;
        } else {
            LOG.info("[===>>>支付单={},支付失败,支付响应信息={}======]", payId, payCode + payMsg);
            pay.setPayStatus(PayStatus.PAY_FAIL.getValue());
            repaymentPayInfoService.update(pay);
            return ErrorCode.REPAYMENT_PAY_FAIL;
        }
    }
}
