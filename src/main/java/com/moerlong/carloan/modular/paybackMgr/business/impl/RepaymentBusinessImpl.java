package com.moerlong.carloan.modular.paybackMgr.business.impl;

import com.alibaba.fastjson.JSON;
import com.moerlong.carloan.modular.loan.entity.vo.SendMsgVo;
import com.moerlong.carloan.modular.paybackMgr.business.RepaymentBusiness;
import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.modular.loan.dao.ProcessNodeDao;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.entity.ProcessNode;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import com.moerlong.carloan.modular.payMgr.entity.PayInfo;
import com.moerlong.carloan.modular.payMgr.entity.enums.PayApproveStatus;
import com.moerlong.carloan.modular.payMgr.service.PayDetailInfoService;
import com.moerlong.carloan.modular.payMgr.service.PayInfoService;
import com.moerlong.carloan.modular.payMgr.service.SMSService;
import com.moerlong.carloan.modular.paybackMgr.entity.*;
import com.moerlong.carloan.modular.paybackMgr.entity.enums.*;
import com.moerlong.carloan.modular.paybackMgr.service.*;
import com.moerlong.carloan.modular.paybackMgr.txservice.RepaymentTxService;
import com.moerlong.carloan.modular.paybackMgr.txservice.vo.PayCenterPayResultVO;
import com.moerlong.carloan.util.ParamConstants;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RepaymentBusinessImpl implements RepaymentBusiness{

    private static Logger LOG = LoggerFactory.getLogger(RepaymentBusinessImpl.class);

    private static final int remainRepaymentDay1 = 5;       //提前五天通知
    private static final int remainRepaymentDay2 = 1;       //提前1天通知
    private static final String appName = "氢诺车抵贷";      //appName

    @Resource
    private RepaymentPlanInfoService repaymentPlanInfoService;
    @Resource
    private RepaymentTxService repaymentTxService;
    @Resource
    private RepaymentPayInfoService repaymentPayInfoService;
    @Resource
    private PayCenterService payCenterService;
    @Resource
    private PayDetailInfoService payDetailInfoService;
    @Resource
    private PayStatisInfoService payStatisInfoService;
    @Resource
    private RepaymentInfoService repaymentInfoService;
    @Resource
    private PayInfoService payInfoService;
    @Resource
    private SMSService smsService;
    @Resource
    private OnceEarlyRepaymentRecordService onceEarlyRepaymentRecordService;
    @Resource
    private RepaymentChangeRecordService repaymentChangeRecordService;
    
    @Resource
    private ApplyInfoService applyInfoService;
    
    @Resource
    private ProcessNodeDao processNodeDao;


    @Override
    public void autoDeductMoneyForRepayment() {

        try{
            // 获取最后还款日 和逾期还款单 并且无支付中状态的还款 的还款计划列表
            List<RepaymentPlanInfo> plans = new ArrayList<RepaymentPlanInfo>();

            // 获取逾期还款计划
            List<RepaymentPlanInfo> overduePlans = repaymentPlanInfoService.selectOverduePlan();
            if (overduePlans != null && overduePlans.size() > 0) {
                plans.addAll(overduePlans);
            }

            // 获取最后还款日还款计划
            List<RepaymentPlanInfo> expirePlans = repaymentPlanInfoService.selectTodayPlan();
            if (expirePlans != null && expirePlans.size() > 0) {
                plans.addAll(expirePlans);
            }

            for (RepaymentPlanInfo planInfo : plans) {
                //进行划扣处理
                ResultVO<Long> autoDeduct;
                try {
                    autoDeduct = repaymentTxService.autoDeductMoneyForRepayment(planInfo);
                    if (autoDeduct != null) {
                    	//查看是否为最后一期还款
                    	RepaymentInfo repaymentInfo =repaymentInfoService.selectById(planInfo.getRepaymentId());
                    	if(repaymentInfo.getLoanPeriod()==planInfo.getPeriodNum()) {//说明是最后一期
                    		//将状态改成还款完成
                    		repaymentInfo.setCurStatus(50);
                    		repaymentInfo.setCurStatusDesc("已还完");
                    		repaymentInfoService.update(repaymentInfo);
                    		
                    		//将流程状态改成已还款
                    		ApplyInfo applyInfo =applyInfoService.selectById(repaymentInfo.getApplyId());
                    		ApplyInfo applyInfoTemp = new ApplyInfo();
                            //ProcessNode node = processNodeDao.selectByName("还款完成");
                            applyInfoTemp.setId(applyInfo.getId());
                            applyInfoTemp.setStatus(10000);
                            applyInfoTemp.setStatusDesc("还款完成");
                            applyInfoTemp.setUpdateTime(new Date());
                            applyInfoService.updateWithOutNull(applyInfoTemp);
                    	}
                        LOG.info("[===>>>计划ID：{},处理代扣结果：{}]", planInfo.getId(), JSON.toJSONString(autoDeduct));
                    } else {
                        LOG.info("[===>>>计划ID：{},处理代扣结果为空,不继续处理：{}]", planInfo.getId(), null);
                        continue;
                    }
                } catch (Exception e) {
                    LOG.error("[===>>>计划ID：{},处理代扣发生异常===],excp:{}", planInfo.getId(), e);
                    continue;
                }

                //处理还款分配
                try{
                    if (autoDeduct != null && autoDeduct.isSuccess() && autoDeduct.getData() != null) {
                        ResultVO<Object> result = repaymentTxService.allotPayUpdatePlanInfo(autoDeduct.getData(), false);
                        LOG.info("[===>>>计划ID：{},代扣完成，处理还款分配结果：{}===]", planInfo.getId(),
                                JSON.toJSONString(result));
                    }
                }catch (Exception e) {
                    LOG.error("[===>>>计划ID：{},代扣完成，处理还款分配发生异常===],excp:{}", planInfo.getId(), e);
                    continue;
                }

            }

        }catch (Exception e){
            LOG.error("===[获取所有需代扣还款还款计划列表,并更新还款信息发生异常,excp={}]===", e);
        }

    }

    @Override
    public void refreshRepaymentPlanStatus() {

        try {
            // 查询开始计息还款计划变为还款中
            List<RepaymentPlanInfo> repaymenting = repaymentPlanInfoService.selectPlanToRepaymenting();
            Date now = new Date();
            if (repaymenting != null && repaymenting.size() > 0) {
                for (RepaymentPlanInfo planInfo : repaymenting) {
                    LOG.info("[===>>>计划ID：{},已到计息日，开始更新状态为还款中======]", planInfo.getId());
                    try {
                        RepaymentPlanInfo info = new RepaymentPlanInfo();
                        info.setId(planInfo.getId());
                        info.setUpdateTime(now);
                        info.setPeriodStatus(RepaymentStatus.REPAYMENTING.getValue());
                        repaymentPlanInfoService.update(info);
                    } catch (Exception e) {
                        LOG.error("[===>>>计划ID：{},更新为还款中状态发生异常======],excp:{}", planInfo.getId(), e);
                    }
                }
            }

            // 查询发生逾期还款计划变为逾期
            List<RepaymentPlanInfo> overdues = repaymentPlanInfoService.selectPlanToOverdue();
            if (overdues != null && overdues.size() > 0) {
                for (RepaymentPlanInfo planInfo : overdues) {
                    LOG.info("[===>>>计划ID：{},发生逾期，开始更新计划状态为逾期======]", planInfo.getId());
                    try {
                        RepaymentPlanInfo info = new RepaymentPlanInfo();
                        info.setId(planInfo.getId());
                        info.setUpdateTime(now);
                        info.setPeriodStatus(RepaymentStatus.REPAYMENT_OVERDUE.getValue());
                        repaymentPlanInfoService.update(info);

                        RepaymentInfo temp = repaymentInfoService.selectById(planInfo.getRepaymentId());
                        RepaymentInfo repaymentInfo = new RepaymentInfo();
                        repaymentInfo.setId(planInfo.getRepaymentId());
                        repaymentInfo.setOverdueTimes(temp.getOverdueTimes()+1);
                        repaymentInfoService.update(repaymentInfo);
                    } catch (Exception e) {
                        LOG.error("[===>>>计划ID：{},发生逾期，开始更新计划状态为逾期======],excp:{}", planInfo.getId(), e);
                    }
                }
            }

        }catch (Exception e){
            LOG.error("[===>>>获取需变更状态还款计划,并刷新状态发生异常======],excp={}", e);
        }
    }

    @Override
    public void refreshAutoDeductMoneyResult() {


        try {
            List<RepaymentPayInfo> pays = repaymentPayInfoService.selectPaysByStatus(PayStatus.PAYING.getValue());

            if(!(pays != null && pays.size() > 0)){
                LOG.info("[===>>>无处理中的代付单！！！======]");
                return ;
            }

            for (RepaymentPayInfo payInfo : pays) {

                ResultVO<PayCenterPayResultVO> centerResultVO;

                try {
                    if(payInfo.getPayType() == RepaymentType.PAY_TYPE_RECEPTION_CHARGE_FEE.getValue()){
                        centerResultVO = payCenterService.queryMrlDeductMoneyStatus(payInfo.getSerialNo());
                    }else {
                        centerResultVO = payCenterService.queryDeductMoneyStatus(payInfo.getSerialNo());
                    }
                } catch (Exception e) {
                    LOG.error("[===>>>查询代扣支付结果调用支付中心异常,batchNo:{}]======,excp:{}", payInfo.getSerialNo(), e);
                    continue;
                }

                if (!(centerResultVO != null && centerResultVO.getData() != null)) {
                    LOG.error("[===>>>支付单：{},的请求支付中心查询代扣支付结果时返回信息centerResultVO为空======]", payInfo.getId());
                    continue;
                }

                if (StringUtils.isBlank(centerResultVO.getData().getResultCode())) {
                    LOG.error("[===>>>支付单：{}.batchNo={}的请求支付中心查询代扣支付结果时返回的错误码为空resultCode={},不处理该笔订单======]",
                            payInfo.getId(), payInfo.getSerialNo(), JSON.toJSONString(centerResultVO));
                    continue;
                }

                try{
                    Date now = new Date();
                    ErrorCode code = repaymentTxService.refreshPayStatus(payInfo.getId(),
                            centerResultVO.getData().getResultCode(), centerResultVO.getData().getMessage());
                    LOG.info("[===>>>支付单：{},根据查询代扣支付结果更新支付信息处理结果：{}，代扣查询结果：{}======]", payInfo.getId(), code,
                            JSON.toJSONString(centerResultVO));

                    if(code == null){
                        continue;
                    }

                    boolean ret;
                    List<SendMsgVo> mobileList = new ArrayList<>();
                    Map<String, Object> param = new HashMap<String, Object>();
                    RepaymentInfo repaymentInfo = repaymentInfoService.selectById(payInfo.getRepaymentId());

                    List<SendMsgVo> m1 = ParamConstants.getValue(ParamConstants.ROLE_NQ);
                    List<SendMsgVo> m2 = ParamConstants.getValue(ParamConstants.ROLE_FINA);
                    List<SendMsgVo> m3 = ParamConstants.getValue(ParamConstants.ROLE_FINA_MA);
                    List<SendMsgVo> m4 = ParamConstants.getValue(ParamConstants.ROLE_NQ);
                    List<SendMsgVo> m5 = ParamConstants.getValue(ParamConstants.ROLE_BUSS_MA);
                    List<SendMsgVo> m6 = ParamConstants.getValue(ParamConstants.ROLE_NQ);

                    LOG.info("----> mobile ROLE_FIND_CD={}", m6);

                    if(code == ErrorCode.REPAYMENT_PAY_DOING){
                        //这里对处理中的定时发送短信通知
                        if(payInfo.getPayingNum() + 1 == 12 || payInfo.getPayingNum() + 1 == 24 || payInfo.getPayingNum() + 1 == 36){
                            mobileList.addAll(m1);
                            mobileList.addAll(m2);
                            mobileList.addAll(m3);
                            param.put("name", repaymentInfo.getCustName());
                            param.put("hour", (payInfo.getPayingNum()+1)*5/60);
                            ret = smsService.sendMsgSMS(mobileList, SMSService.REPAY_PAYING_TIP, param);
                            if (ret == false) {
                                LOG.error("===>>>支付单：{}, 扣款结果，发送短信通知失败", payInfo.getId());
                            } else {
                                LOG.info("===>>>支付单：{}, 扣款结果，发送短信通知成功", payInfo.getId());
                            }
                        }
                        continue;
                    }
                    //然后开始判断是扣除计划还是扣除前期费用

                    if(payInfo.getPayType() == RepaymentType.PAY_TYPE_AUTO_PLAN.getValue()){
                        //对于计划扣款
                        if (code == ErrorCode.SUCCESS) {
                            LOG.info("[===>>>支付单：{},代扣成功SUCCESS开始执行还款分配，代扣查询结果：{}======]", payInfo.getId(),
                                    JSON.toJSONString(centerResultVO));

                            mobileList = new ArrayList<>();
                            param = new HashMap<String, Object>();
                            param.put("name", repaymentInfo.getCustName());
                            param.put("period", payInfo.getRepaymentPeriodNum());
                            param.put("money", payInfo.getAmount());
                            param.put("result", "成功");
                            //添加手机列表
                            mobileList.addAll(m1);
                            mobileList.addAll(m2);
                            mobileList.addAll(m3);
                            mobileList.addAll(m5);
                            ret = smsService.sendMsgSMS(mobileList, SMSService.COST_RESULT, param);
                            if (ret == false) {
                                LOG.error("===>>>申请单：{}, 计划扣款，发送短信通知失败", repaymentInfo.getPayId());
                            } else {
                                LOG.info("===>>>申请单：{}, 计划扣款，发送短信通知成功", repaymentInfo.getPayId());
                            }

                            //这里对每期计划进行处理
                            try {
                                ResultVO<Object> result = repaymentTxService
                                        .allotPayUpdatePlanInfo(payInfo.getId(), false);
                                LOG.info("[===>>>支付单：{},代扣成功SUCCESS,还款分配处理结果：{}======]", payInfo.getId(),
                                        JSON.toJSONString(result));

                            } catch (Exception e) {
                                LOG.error("[===>>>支付单：{},代扣成功SUCCESS执行还款分配异常，代扣查询结果：{}======],excp:{}",
                                        payInfo.getId(), JSON.toJSONString(centerResultVO), e);
                                continue;
                            }
                        }
                        else {
                            LOG.info("[===>>>支付单：{},代扣失败,处理结果：{}======]", payInfo.getId(), code);
                            mobileList = new ArrayList<>();
                            param = new HashMap<String, Object>();
                            param.put("name", repaymentInfo.getCustName());
                            param.put("period", payInfo.getRepaymentPeriodNum());
                            param.put("money", payInfo.getAmount());
                            param.put("result", "失败");
                            //添加手机列表
                            mobileList.addAll(m1);
                            mobileList.addAll(m2);
                            mobileList.addAll(m3);
                            mobileList.addAll(m4);
                            mobileList.addAll(m5);
                            ret = smsService.sendMsgSMS(mobileList, SMSService.COST_RESULT, param);
                            if (ret == false) {
                                LOG.error("===>>>申请单：{}, 计划扣款，发送短信通知失败", repaymentInfo.getPayId());
                            } else {
                                LOG.info("===>>>申请单：{}, 计划扣款，发送短信通知成功", repaymentInfo.getPayId());
                            }
                        }
                    }
                    else if(payInfo.getPayType() == RepaymentType.PAY_TYPE_PRE_FEE.getValue()){
                        //这里对前期费用进行处理
                        PayInfo info = new PayInfo();
                        info.setId(repaymentInfo.getPayId());

                        if (code == ErrorCode.SUCCESS) {
                            info.setPayStatus(PayApproveStatus.COST_SUCCESS.getValue());
                            info.setPayStatusDesc(PayApproveStatus.COST_SUCCESS.getDesc());
                        }else if(code == ErrorCode.REPAYMENT_PAY_FAIL){
                            info.setPayStatus(PayApproveStatus.COST_FAIL.getValue());
                            info.setPayStatusDesc(PayApproveStatus.COST_FAIL.getDesc());
                        }
                        info.setUpdateTime(now);
                        payInfoService.update(info);

                        mobileList = new ArrayList<>();
                        param = new HashMap<String, Object>();
                        param.put("name", repaymentInfo.getCustName());
                        if (code == ErrorCode.SUCCESS) {
                            param.put("result", "成功");
                            //添加手机列表
                            mobileList.addAll(m1);
                            mobileList.addAll(m2);
                            mobileList.addAll(m3);
                            mobileList.addAll(m5);
                        } else{
                            param.put("result", "失败");
                            mobileList.addAll(m1);
                            mobileList.addAll(m2);
                            mobileList.addAll(m3);
                            mobileList.addAll(m4);
                            mobileList.addAll(m5);
                        }

                        ret = smsService.sendMsgSMS(mobileList, SMSService.REPAY_RESULT, param);
                        if (ret == false) {
                            LOG.error("===>>>申请单：{}, 收取前期费用，发送短信通知失败", repaymentInfo.getPayId());
                        } else {
                            LOG.info("===>>>申请单：{}, 收取前期费用，发送短信通知成功", repaymentInfo.getPayId());
                        }

                        //如果扣取前期费用成功，则调用扣取前台手续费
                        if(code == ErrorCode.SUCCESS){
                            payInfoService.repaymentReceptionFee(repaymentInfo.getPayId());
                        }
                    }
                    else if(payInfo.getPayType() == RepaymentType.PAY_TYPE_RECEPTION_CHARGE_FEE.getValue()){
                        //这里对前台费用进行处理
                        PayInfo info = new PayInfo();
                        info.setId(repaymentInfo.getPayId());

                        if (code == ErrorCode.SUCCESS) {
                            info.setPayStatus(PayApproveStatus.COST_CHARGE_SUCCESS.getValue());
                            info.setPayStatusDesc(PayApproveStatus.COST_CHARGE_SUCCESS.getDesc());
                        }else if(code == ErrorCode.REPAYMENT_PAY_FAIL){
                            info.setPayStatus(PayApproveStatus.COST_CHARGE_FAIL.getValue());
                            info.setPayStatusDesc(PayApproveStatus.COST_CHARGE_FAIL.getDesc());
                        }
                        info.setUpdateTime(now);
                        payInfoService.update(info);

                        mobileList = new ArrayList<>();
                        param = new HashMap<String, Object>();
                        param.put("name", repaymentInfo.getCustName());
                        if (code == ErrorCode.SUCCESS) {
                            param.put("result", "成功");
                            //添加手机列表
                            mobileList.addAll(m1);
                            mobileList.addAll(m2);
                            mobileList.addAll(m3);
                            mobileList.addAll(m5);
                            mobileList.addAll(m6);
                        } else{
                            param.put("result", "失败");
                            mobileList.addAll(m1);
                            mobileList.addAll(m2);
                            mobileList.addAll(m3);
                            mobileList.addAll(m4);
                            mobileList.addAll(m5);
                            mobileList.addAll(m6);
                        }

                        LOG.info("---->收取前台手续费手机列表={}", mobileList);
                        ret = smsService.sendMsgSMS(mobileList, SMSService.REPAY_RECEPTION_RESULT, param);
                        if (ret == false) {
                            LOG.error("===>>>申请单：{}, 收取前台手续费，发送短信通知失败", repaymentInfo.getPayId());
                        } else {
                            LOG.info("===>>>申请单：{}, 收取前台手续费，发送短信通知成功", repaymentInfo.getPayId());
                        }
                    }
                    else{
                        LOG.error("===>>>申请单：{}，无法识别的支付类型{}", repaymentInfo.getPayId(), payInfo.getPayType());
                    }
                }catch (Exception e) {
                    LOG.error("[===>>>支付单：{}.处理代扣支付结果更新支付状态信息处理异常。centerResultVO={}======],excp:{}",
                            payInfo.getId(), JSON.toJSONString(centerResultVO), e);
                    continue;
                }
            }
        }catch(Exception e){
            LOG.error("[===>>>获取所有代扣处理中的订单，并更新还款信息发生异常======],excp={}", e);
        }
    }

    @Override
    public void statisFinance() {

        try{
        	List<Map<String ,Object>> repaymentInfo = repaymentPayInfoService.getPaysCountLastDaySuccess();
        	List<Map<String ,Object>> payInfo = payDetailInfoService.getPaysCountLastDaySuccess();
            List<Map<String ,Object>> repaymentPreFeeInfo = repaymentPayInfoService.getPreFeePaysCountLastDaySuccess();
            //统计累计放款总额
            List<Map<String, Object>> totalPay = payDetailInfoService.countAllPayMoney();
            //统计累计收款总计
            List<Map<String, Object>> totalRepayment = repaymentPayInfoService.countAllRepaymentMoney();
            
            //统计提前还款总计
            List<Map<String ,Object>> onceEarlyRepaymentLeaseCapital = repaymentPayInfoService.countAllOnceEarlyRepayment(RepaymentType.PAY_TYPE_ONCE_EARLY_CAPITAL.getValue());
            List<Map<String ,Object>> onceEarlyRepaymentServiceCapital = repaymentPayInfoService.countAllOnceEarlyRepayment(RepaymentType.PAY_TYPE_ONCE_EARLY_INTEREST.getValue());
            List<Map<String ,Object>> onceEarlyRepaymentCharge = repaymentPayInfoService.countAllOnceEarlyRepayment(RepaymentType.PAY_TYPE_ONCE_EARLY_CHARGE.getValue());
            List<Map<String ,Object>> onceEarlyRepaymentBreach = repaymentPayInfoService.countAllOnceEarlyRepayment(RepaymentType.PAY_TYPE_ONCE_EARLY_BREACH.getValue());
            
            //统计系统上线之前前4笔的手续费统计
            List<Map<String, Object>> totalChargeBefore = repaymentInfoService.countChargeBefore();
            
            //统计收取前期费用总计
            List<Map<String, Object>> totalPreFee = repaymentPayInfoService.countAllRepaymentPreFee();
            
            //统计未还款金额总计
            List<Map<String, Object>> totalNotRepayment = repaymentPlanInfoService.countAllNotRepaymentMoney();
            
            //统计当日放款收款情况
        	for(int i=2;i<5;i++) {//按照部门来统计2,3,4,5
        		String yest="";
        		BigDecimal curRepayment=new BigDecimal("0.00");//当日还款总计
        		BigDecimal curPay=new BigDecimal("0.00");//当日放款总计
        		BigDecimal curPreFee=new BigDecimal("0.00");//当日前期费用总计
        		BigDecimal totalPayMoney =new BigDecimal("0.00");//累计放款总额
        		BigDecimal totalAmountMoneyCheck = new BigDecimal("0.00");
                BigDecimal totalAmountMoney = new BigDecimal("0.00");
                BigDecimal totalCapitalMoney =new BigDecimal("0.00");
                BigDecimal totalInterestMoney = new BigDecimal("0.00");
                BigDecimal totalServiceChargeMoney = new BigDecimal("0.00");
                BigDecimal totalChargeMoney = new BigDecimal("0.00");
                BigDecimal totalPenaltyMoney = new BigDecimal("0.00");
                BigDecimal totalChargeBeforeMoney = new BigDecimal("0.00");
                BigDecimal totalPreFeeMoney = new BigDecimal("0.00");
                BigDecimal totalNotAmountMoney = new BigDecimal("0.00");
                BigDecimal totalNotCapitalMoney = new BigDecimal("0.00");
                BigDecimal totalNotInterestMoney = new BigDecimal("0.00");
                BigDecimal totalNotServiceChargeMoney = new BigDecimal("0.00");
                BigDecimal totalNotChargeMoney = new BigDecimal("0.00");
                BigDecimal totalNotPenaltyMoney = new BigDecimal("0.00");
                BigDecimal totalBreach=new BigDecimal("0.00");
        		for (Iterator iterator = repaymentInfo.iterator(); iterator.hasNext();) {
					Map<String, Object> map = (Map<String, Object>) iterator.next();
					//根据部门id来获取数据
					if(map.get("deptId").toString().equals(String.valueOf(i))) {
						curRepayment=new BigDecimal(map.get("money").toString());
						yest=map.get("curDate").toString();
					}
				}
        		
        		for (Iterator iterator = payInfo.iterator(); iterator.hasNext();) {
					Map<String, Object> map = (Map<String, Object>) iterator.next();
					//根据部门id来获取数据
					if(map.get("deptId").toString().equals(String.valueOf(i))) {
						curPay=new BigDecimal(map.get("money").toString());
					}
				}
        		
        		for (Iterator iterator = repaymentPreFeeInfo.iterator(); iterator.hasNext();) {
					Map<String, Object> map = (Map<String, Object>) iterator.next();
					//根据部门id来获取数据
					if(map.get("deptId").toString().equals(String.valueOf(i))) {
						curPreFee=new BigDecimal(map.get("money").toString());
					}
				}
        		
        		for (Iterator iterator = totalPay.iterator(); iterator.hasNext();) {
					Map<String, Object> map = (Map<String, Object>) iterator.next();
					//根据部门id来获取数据
					if(map.get("deptId").toString().equals(String.valueOf(i))) {
						totalPayMoney=new BigDecimal(map.get("money").toString());
					}
				}
        		
        		for (Iterator iterator = totalRepayment.iterator(); iterator.hasNext();) {
					Map<String, Object> map = (Map<String, Object>) iterator.next();
					//根据部门id来获取数据
					if(map.get("deptId").toString().equals(String.valueOf(i))) {
						totalAmountMoneyCheck = new BigDecimal(map.get("amountCheck").toString());
			            totalAmountMoney = new BigDecimal(map.get("amount").toString());
			            totalCapitalMoney = new BigDecimal(map.get("capital").toString());
			            totalInterestMoney = new BigDecimal(map.get("interest").toString());
			            totalServiceChargeMoney = new BigDecimal(map.get("serviceCharge").toString());
			            totalChargeMoney = new BigDecimal(map.get("charge").toString());
			            totalPenaltyMoney = new BigDecimal(map.get("penalty").toString());
					}
				}
        		
        		for (Iterator iterator = onceEarlyRepaymentLeaseCapital.iterator(); iterator.hasNext();) {
					Map<String, Object> map = (Map<String, Object>) iterator.next();
					//根据部门id来获取数据
					if(map.get("deptId").toString().equals(String.valueOf(i))) {
						totalCapitalMoney = totalCapitalMoney.add(new BigDecimal(map.get("preFee").toString()));
						totalAmountMoney = totalAmountMoney.add(new BigDecimal(map.get("preFee").toString()));
					}
				}
        		
        		for (Iterator iterator = onceEarlyRepaymentServiceCapital.iterator(); iterator.hasNext();) {
					Map<String, Object> map = (Map<String, Object>) iterator.next();
					//根据部门id来获取数据
					if(map.get("deptId").toString().equals(String.valueOf(i))) {
						totalCapitalMoney = totalCapitalMoney.add(new BigDecimal(map.get("preFee").toString()));
						totalAmountMoney = totalAmountMoney.add(new BigDecimal(map.get("preFee").toString()));
					}
				}
        		
        		for (Iterator iterator = onceEarlyRepaymentCharge.iterator(); iterator.hasNext();) {
					Map<String, Object> map = (Map<String, Object>) iterator.next();
					//根据部门id来获取数据
					if(map.get("deptId").toString().equals(String.valueOf(i))) {
						totalChargeMoney = totalChargeMoney.add(new BigDecimal(map.get("preFee").toString()));
						totalAmountMoney = totalAmountMoney.add(new BigDecimal(map.get("preFee").toString()));
					}
				}
        		
        		for (Iterator iterator = onceEarlyRepaymentBreach.iterator(); iterator.hasNext();) {
					Map<String, Object> map = (Map<String, Object>) iterator.next();
					//根据部门id来获取数据
					if(map.get("deptId").toString().equals(String.valueOf(i))) {
						totalAmountMoney = totalAmountMoney.add(new BigDecimal(map.get("preFee").toString()));
						totalBreach=new BigDecimal(map.get("preFee").toString());
					}
				}
        		
        		for (Iterator iterator = totalChargeBefore.iterator(); iterator.hasNext();) {
					Map<String, Object> map = (Map<String, Object>) iterator.next();
					//根据部门id来获取数据
					if(map.get("deptId").toString().equals(String.valueOf(i))) {
						totalChargeMoney = totalChargeMoney.add(new BigDecimal(map.get("charge").toString()));
			            totalAmountMoney = totalAmountMoney.add(new BigDecimal(map.get("charge").toString()));
					}
				}
        		
        		for (Iterator iterator = totalPreFee.iterator(); iterator.hasNext();) {
					Map<String, Object> map = (Map<String, Object>) iterator.next();
					//根据部门id来获取数据
					if(map.get("deptId").toString().equals(String.valueOf(i))) {
						totalPreFeeMoney = new BigDecimal(map.get("preFee").toString());
					}
				}
        		
        		for (Iterator iterator = totalNotRepayment.iterator(); iterator.hasNext();) {
					Map<String, Object> map = (Map<String, Object>) iterator.next();
					//根据部门id来获取数据
					if(map.get("deptId").toString().equals(String.valueOf(i))) {
						totalNotAmountMoney = new BigDecimal(map.get("amount").toString());
			            totalNotCapitalMoney = new BigDecimal(map.get("capital").toString());
			            totalNotInterestMoney = new BigDecimal(map.get("interest").toString());
			            totalNotServiceChargeMoney = new BigDecimal(map.get("serviceCharge").toString());
			            totalNotChargeMoney = new BigDecimal(map.get("charge").toString());
			            totalNotPenaltyMoney = new BigDecimal(map.get("penalty").toString());
					}
				}
        		
        		Calendar cal=Calendar.getInstance();
        		cal.add(Calendar.DATE, -1);
        		Date dt=cal.getTime();
        		Date now = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                
                

                PayStatisInfo ps = new PayStatisInfo();
                ps.setDeptId(Long.valueOf(i));
                ps.setCurRepayment(curRepayment);
                ps.setCurPay(curPay);
                ps.setCurPreFee(curPreFee);
                ps.setStatisDate(sdf.parse(sdf.format(dt)));
                ps.setPayTotalAmount(totalPayMoney);
                ps.setRepaymentTotalAmount(totalAmountMoney);
                ps.setTotalCapital(totalCapitalMoney);
                ps.setTotalInterest(totalInterestMoney);
                ps.setTotalServiceCharge(totalServiceChargeMoney);
                ps.setTotalCharge(totalChargeMoney);
                ps.setTotalPenalty(totalPenaltyMoney);
                ps.setTotalBreach(totalBreach);
                ps.setTotalPreFee(totalPreFeeMoney);
                ps.setNotRepayAmount(totalNotAmountMoney);
                ps.setNotRepayCapital(totalNotCapitalMoney);
                ps.setNotRepayInterest(totalNotInterestMoney);
                ps.setNotRepayServiceCharge(totalNotServiceChargeMoney);
                ps.setNotRepayCharge(totalNotChargeMoney);
                ps.setNotRepayPenalty(totalNotPenaltyMoney);

                ps.setCreateTime(now);
                ps.setUpdateTime(now);
                ps.setIsDeleted(0);
                LOG.info("统计结果{}", ps.toString());


                payStatisInfoService.save(ps);
        	}
        	
            /*List<Map<String ,Object>> repaymentInfo = repaymentPayInfoService.getPaysCountLastDaySuccess();
            List<Map<String ,Object>> payInfo = payDetailInfoService.getPaysCountLastDaySuccess();
            List<Map<String ,Object>> repaymentPreFeeInfo = repaymentPayInfoService.getPreFeePaysCountLastDaySuccess();*/

            //统计累计放款总额
            /*List<Map<String, Object>> totalPay = payDetailInfoService.countAllPayMoney();
            BigDecimal totalPayMoney = new BigDecimal(totalPay.get("money").toString());*/

            //统计累计收款总计
           /* List<Map<String, Object>> totalRepayment = repaymentPayInfoService.countAllRepaymentMoney();
            BigDecimal totalAmountMoneyCheck = new BigDecimal(totalRepayment.get("amountCheck").toString());
            BigDecimal totalAmountMoney = new BigDecimal(totalRepayment.get("amount").toString());
            BigDecimal totalCapitalMoney = new BigDecimal(totalRepayment.get("capital").toString());
            BigDecimal totalInterestMoney = new BigDecimal(totalRepayment.get("interest").toString());
            BigDecimal totalServiceChargeMoney = new BigDecimal(totalRepayment.get("serviceCharge").toString());
            BigDecimal totalChargeMoney = new BigDecimal(totalRepayment.get("charge").toString());
            BigDecimal totalPenaltyMoney = new BigDecimal(totalRepayment.get("penalty").toString());*/

            /*//统计提前还款总计

            List<Map<String ,Object>> onceEarlyRepaymentLeaseCapital = repaymentPayInfoService.countAllOnceEarlyRepayment(RepaymentType.PAY_TYPE_ONCE_EARLY_CAPITAL.getValue());
            List<Map<String ,Object>> onceEarlyRepaymentServiceCapital = repaymentPayInfoService.countAllOnceEarlyRepayment(RepaymentType.PAY_TYPE_ONCE_EARLY_INTEREST.getValue());
            List<Map<String ,Object>> onceEarlyRepaymentCharge = repaymentPayInfoService.countAllOnceEarlyRepayment(RepaymentType.PAY_TYPE_ONCE_EARLY_CHARGE.getValue());
            List<Map<String ,Object>> onceEarlyRepaymentBreach = repaymentPayInfoService.countAllOnceEarlyRepayment(RepaymentType.PAY_TYPE_ONCE_EARLY_BREACH.getValue());*/

            /*totalCapitalMoney = totalCapitalMoney.add(onceEarlyRepaymentLeaseCapital).add(onceEarlyRepaymentServiceCapital);
            totalChargeMoney = totalChargeMoney.add(onceEarlyRepaymentCharge);

            LOG.info("==>总还款check{} 总还款{} 总本金{} 总利息{} 总服务费{} 总手续费{} 总罚息{} 加和{}", totalAmountMoneyCheck, totalAmountMoney,
                    totalCapitalMoney, totalInterestMoney, totalServiceChargeMoney, totalChargeMoney, totalPenaltyMoney,
                    totalCapitalMoney.add(totalInterestMoney).add(totalServiceChargeMoney).add(totalChargeMoney).add(totalPayMoney));

            totalAmountMoney = totalAmountMoney.
                    add(onceEarlyRepaymentLeaseCapital).
                    add(onceEarlyRepaymentServiceCapital).
                    add(onceEarlyRepaymentCharge).
                    add(onceEarlyRepaymentBreach);*/

            //统计系统上线之前前4笔的手续费统计
           /* List<Map<String, Object>> totalChargeBefore = repaymentInfoService.countChargeBefore();
            BigDecimal totalChargeBeforeMoney = new BigDecimal(totalChargeBefore.get("charge").toString());
            LOG.info("==>前4笔手续费{}", totalChargeBeforeMoney);

            totalChargeMoney = totalChargeMoney.add(totalChargeBeforeMoney);
            totalAmountMoney = totalAmountMoney.add(totalChargeBeforeMoney);*/

            //统计收取前期费用总计
            /*List<Map<String, Object>> totalPreFee = repaymentPayInfoService.countAllRepaymentPreFee();
            BigDecimal totalPreFeeMoney = new BigDecimal(totalPreFee.get("preFee").toString());*/

            //统计未还款金额总计
           /* Map<String, Object> totalNotRepayment = repaymentPlanInfoService.countAllNotRepaymentMoney();
            BigDecimal totalNotAmountMoney = new BigDecimal(totalNotRepayment.get("amount").toString());
            BigDecimal totalNotCapitalMoney = new BigDecimal(totalNotRepayment.get("capital").toString());
            BigDecimal totalNotInterestMoney = new BigDecimal(totalNotRepayment.get("interest").toString());
            BigDecimal totalNotServiceChargeMoney = new BigDecimal(totalNotRepayment.get("serviceCharge").toString());
            BigDecimal totalNotChargeMoney = new BigDecimal(totalNotRepayment.get("charge").toString());
            BigDecimal totalNotPenaltyMoney = new BigDecimal(totalNotRepayment.get("penalty").toString());*/

            /*Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            
            

            PayStatisInfo ps = new PayStatisInfo();
            ps.setCurRepayment(new BigDecimal(repaymentInfo.get("money").toString()));
            ps.setCurPay(new BigDecimal(payInfo.get("money").toString()));
            ps.setCurPreFee(new BigDecimal(repaymentPreFeeInfo.get("money").toString()));
            ps.setStatisDate(sdf.parse((String) repaymentInfo.get("curDate")));
            ps.setPayTotalAmount(totalPayMoney);
            ps.setRepaymentTotalAmount(totalAmountMoney);
            ps.setTotalCapital(totalCapitalMoney);
            ps.setTotalInterest(totalInterestMoney);
            ps.setTotalServiceCharge(totalServiceChargeMoney);
            ps.setTotalCharge(totalChargeMoney);
            ps.setTotalPenalty(totalPenaltyMoney);
            ps.setTotalBreach(onceEarlyRepaymentBreach);
            ps.setTotalPreFee(totalPreFeeMoney);
            ps.setNotRepayAmount(totalNotAmountMoney);
            ps.setNotRepayCapital(totalNotCapitalMoney);
            ps.setNotRepayInterest(totalNotInterestMoney);
            ps.setNotRepayServiceCharge(totalNotServiceChargeMoney);
            ps.setNotRepayCharge(totalNotChargeMoney);
            ps.setNotRepayPenalty(totalNotPenaltyMoney);

            ps.setCreateTime(now);
            ps.setUpdateTime(now);
            ps.setIsDeleted(0);
            LOG.info("统计结果{}", ps.toString());


            payStatisInfoService.save(ps);*/
        }catch(Exception e){
            LOG.error("统计出现错误 e={}", e);
        }
    }

    @Override
    public void checkFinance() {
        try{

            //1、核对每一个放款总表和放款详情表是否一致
            List<PayInfo> payInfoList = payInfoService.listByCondition(null, null, null, null, null);
            for(PayInfo payInfo : payInfoList){
                BigDecimal payMoney = payDetailInfoService.countPayMoneyByPayId2(payInfo.getId());
                if(payInfo.getPayedAmount().compareTo(payMoney) != 0){
                    LOG.error("payId{} 客户{} 已放款金额{}与记录统计金额{} 不一致...", payInfo.getId(), payInfo.getCustName(), payInfo.getPayedAmount(), payMoney);
                }
            }

            //2、核对每一个还款总表跟还款计划表和还款记录表是否一致

            List<RepaymentInfo> repaymentInfoList = repaymentInfoService.listByCondition(null, null, null, null, null);
            for(RepaymentInfo info : repaymentInfoList){
                Map<String, Object> map = repaymentPlanInfoService.countRepaymentMoneyByRepaymentId(info.getId());
                BigDecimal actualAmount = new BigDecimal(map.get("amount").toString());
                BigDecimal actualCapital = new BigDecimal(map.get("capital").toString());
                BigDecimal actualInterest = new BigDecimal(map.get("interest").toString());
                BigDecimal actualServiceCharge = new BigDecimal(map.get("serviceCharge").toString());
                BigDecimal actualCharge = new BigDecimal(map.get("charge").toString());
                BigDecimal actualPenalty = new BigDecimal(map.get("penalty").toString());

                if(info.getVersion() == 0){
                    //历史问题，版本为0的表示之前都已经全部收取完手续费
                    actualCharge = info.getActualCharge();
                    actualAmount = actualAmount.add(info.getActualCharge());
                }

                //获取提前还款记录
                /*List<OnceEarlyRepaymentRecord> recordList = onceEarlyRepaymentRecordService.selectByRepaymentId(info.getId());
                for (OnceEarlyRepaymentRecord record : recordList) {
                    if(record.getCurStatus() == RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FIN_BUSS_APPROVE.getValue()){
                        actualCapital = actualCapital.add(record.getOnceRepaymentCapital());
                        actualCharge = actualCharge.add(record.getOnceRepaymentCharge());

                        actualAmount = actualAmount.add(record.getOnceRepaymentCapital()).add(record.getOnceRepaymentCharge());
                        break;
                    }
                }*/
                OnceEarlyRepaymentRecord recordList = onceEarlyRepaymentRecordService.selectByRepaymentId(info.getId());
                if(recordList.getCurStatus() == RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FIN_BUSS_APPROVE.getValue()){
                    actualCapital = actualCapital.add(recordList.getOnceRepaymentCapital());
                    actualCharge = actualCharge.add(recordList.getOnceRepaymentCharge());

                    actualAmount = actualAmount.add(recordList.getOnceRepaymentCapital()).add(recordList.getOnceRepaymentCharge());
                }

                if(actualAmount.compareTo(info.getActualAmount()) != 0 || actualCapital.compareTo(info.getActualCapital()) != 0 ||
                        actualInterest.compareTo(info.getActualInterest())!=0 || actualServiceCharge.compareTo(info.getActualServiceCharge())!=0 ||
                        actualCharge.compareTo(info.getActualCharge()) != 0 || actualPenalty.compareTo(info.getActualPenalty()) != 0){
                    LOG.error("repaymentId[{}] 客户[{}] 已还总额[{}] 已还本金[{}] 已还利息[{}] 已还服务费[{}] 已还手续费[{}] 已还罚金[{}] 与记录统计金额的已还总额[{}] 已还本金[{}] 已还利息[{}] 已还服务费[{}] 已还手续费[{}] 已还罚金[{}] 不一致...",
                            info.getId(), info.getCustName(), info.getActualAmount(), info.getActualCapital(), info.getActualInterest(), info.getActualServiceCharge(), info.getActualCharge(),
                            info.getActualPenalty(), actualAmount, actualCapital, actualInterest, actualServiceCharge, actualCharge, actualPenalty);
                }

                BigDecimal amount = repaymentPayInfoService.countRepaymentMoneyByRepaymentId2(info.getId());

                if(info.getVersion() == 0){
                    amount = amount.add(info.getActualCharge());
                }

                if(amount.compareTo(info.getActualAmount()) != 0){
                    LOG.error("repaymentId[{}] 客户[{}] 已还总额[{}] 记录已还总额[{}] 不一致...", info.getId(), info.getCustName(), info.getActualAmount(), amount);
                }
            }
        }catch(Exception e){
            LOG.error("财务核账出现错误 e={}", e);
        }
    }

    @Override
    public void autoRemainCustomerForRepayment() {

        try{
            boolean ret;
            Map<String, Object> param = new HashMap<String, Object>();
            List<SendMsgVo> mobileList = new ArrayList<>();
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");

            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(now);
            rightNow.add(Calendar.DAY_OF_YEAR,remainRepaymentDay1);//日期加5天
            Date day1 = rightNow.getTime();
            String checkDate1 = sdf.format(day1);

            rightNow = Calendar.getInstance();
            rightNow.setTime(now);
            rightNow.add(Calendar.DAY_OF_YEAR,remainRepaymentDay2);//日期加1天
            Date day2 = rightNow.getTime();
            String checkDate2 = sdf.format(day2);

            List<SendMsgVo> m1 = ParamConstants.getValue(ParamConstants.ROLE_NQ);



            //1 获取提前五天的客户还款列表
            List<RepaymentPlanInfo> totalList = new ArrayList<>();
            List<RepaymentPlanInfo> planInfoList = repaymentPlanInfoService.selectCurDayList(checkDate1, 1);
            totalList.addAll(planInfoList);

            List<RepaymentPlanInfo> planInfoList1 = repaymentPlanInfoService.selectCurDayList(checkDate2, 1);
            totalList.addAll(planInfoList1);
            List<RepaymentPlanInfo> planInfoList2 = repaymentPlanInfoService.selectOverduePlan();
            totalList.addAll(planInfoList2);

            for (RepaymentPlanInfo planInfo : totalList) {
                RepaymentInfo info = repaymentInfoService.selectById(planInfo.getRepaymentId());
                LOG.info("planInfo id={}, repaymentId={} custName={} amount={} period={} endTime={}",
                        planInfo.getId(), info.getId(), info.getCustName(), planInfo.getPredictAmount(), planInfo.getPeriodNum(), sdf.format(planInfo.getPeriodEndTime()));

                param = new HashMap<String, Object>();
                mobileList = new ArrayList<>();
                param.put("name", info.getCustName());
                param.put("appName", appName);
                param.put("date", sdf2.format(planInfo.getPeriodEndTime()));
                param.put("money", planInfo.getPredictAmount());
                param.put("bankCardNo", info.getBankCardNo().substring(info.getBankCardNo().length()-4));
                //TODO
                mobileList.add(new SendMsgVo(null,null,null,null,info.getCustMobile()));
                mobileList.addAll(m1);

                LOG.info("param = {} mobilelist={}", param, mobileList);
                ret = smsService.sendMsgSMS(mobileList, SMSService.REMAIN_REPAYMENT, param);
                if (ret == false) {
                    LOG.error("===>>>到期提醒 repaymentPlanId={} repaymentId={} custName={},发送短信失败", planInfo.getId(), info.getId(), info.getCustName());
                } else {
                    LOG.error("===>>>到期提醒 repaymentPlanId={} repaymentId={} custName={},发送短信成功", planInfo.getId(), info.getId(), info.getCustName());
                }
            }

        }catch(Exception e){
            LOG.error("客户还款提醒短信 出现错误e={} ", e);
        }
    }

    /**
     * 计算逾期违约金
     */
    @Override
    public void calcOverdue() {
        try{
            long time = 1000 * 60 * 60 * 24;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date now = sdf.parse(sdf.format(new Date()));
            //列出所有逾期的计划
            List<RepaymentPlanInfo> planList = repaymentPlanInfoService.selectOverduePlan();
            Set<Long> set = new HashSet<>();

            for (RepaymentPlanInfo planInfo : planList) {
                Date endDate = sdf.parse(sdf.format(planInfo.getPeriodEndTime()));
                long days = (now.getTime() - endDate.getTime())/time;

                //计算逾期金额
                BigDecimal predictAmount = planInfo.getPredictCapital()
                        .add(planInfo.getPredictInterest())
                        .add(planInfo.getPredictServiceCharge())
                        .add(planInfo.getPredictCharge());

                //更新还款计划表
                BigDecimal penalty = predictAmount.multiply(new BigDecimal(0.005)).multiply(new BigDecimal(days)).setScale(2, BigDecimal.ROUND_HALF_UP);

                LOG.info("repaymentPlanInfo id={} repaymentId={} overdue days={} penalty={}", planInfo.getId(),
                        planInfo.getRepaymentId(), days, penalty);

                RepaymentPlanInfo temp = new RepaymentPlanInfo();
                temp.setId(planInfo.getId());
                temp.setPredictPenalty(penalty);
                temp.setPredictAmount(predictAmount.add(penalty));
                temp.setOverdueDays(Integer.valueOf((int)days));
                temp.setOverduePenalty(penalty);
                temp.setUpdateTime(new Date());
                repaymentPlanInfoService.update(temp);

                //加入变更记录
                RepaymentChangeRecord record = new RepaymentChangeRecord();
                record.setRepaymentId(planInfo.getRepaymentId());
                record.setRepaymentPlanId(planInfo.getId());
                record.setChangeType(RepaymentChangeType.CHANGE_TYPE_OVERDUE.getValue());
                record.setChangeDesc(RepaymentChangeType.CHANGE_TYPE_OVERDUE.getDesc());
                record.setOldOverdueDays(planInfo.getOverdueDays());
                record.setOldOverduePenalty(planInfo.getOverduePenalty());
                record.setNewOverdueDays(Integer.valueOf((int)days));
                record.setNewOverduePenalty(penalty);
                record.setCreateTime(now);
                record.setUpdateTime(now);
                record.setIsDeleted(0);
                repaymentChangeRecordService.save(record);

                set.add(planInfo.getRepaymentId());
            }

            //更新涉及到的还款总表信息
            for (Long repaymentId : set) {
                RepaymentInfo info = repaymentInfoService.selectById(repaymentId);
                BigDecimal sumPenalty = repaymentPlanInfoService.sumPenaltyByRepaymentId(repaymentId);
                RepaymentInfo temp = new RepaymentInfo();
                temp.setId(repaymentId);
                temp.setPredictPenalty(sumPenalty);
                temp.setPredictAmount(info.getPredictCharge()
                                .add(info.getPredictCapital())
                                .add(info.getPredictInterest())
                                .add(info.getPredictServiceCharge())
                                .add(sumPenalty));
                repaymentInfoService.update(temp);
            }


        }catch(Exception e){
            LOG.error("计算逾期违约金 出现错误e={} ", e);
        }
    }


}
