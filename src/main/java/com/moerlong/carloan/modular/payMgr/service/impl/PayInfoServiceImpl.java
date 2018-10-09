package com.moerlong.carloan.modular.payMgr.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import com.google.gson.Gson;
import com.moerlong.carloan.modular.loan.entity.vo.SendMsgVo;
import com.moerlong.carloan.modular.payMgr.service.PayApproveRecordService;
import com.moerlong.carloan.modular.payMgr.service.PayDetailInfoService;
import com.moerlong.carloan.modular.payMgr.service.PayInfoService;
import com.moerlong.carloan.modular.payMgr.service.SMSService;
import com.moerlong.carloan.modular.payMgr.txService.PayTxService;
import com.moerlong.carloan.modular.paybackMgr.entity.enums.PayStatus;
import com.moerlong.carloan.modular.paybackMgr.entity.enums.RepaymentType;
import com.moerlong.carloan.modular.paybackMgr.service.IOrderSequenceService;
import com.moerlong.carloan.modular.paybackMgr.service.PayCenterService;
import com.moerlong.carloan.modular.paybackMgr.service.RepaymentInfoService;
import com.moerlong.carloan.modular.paybackMgr.service.RepaymentPayInfoService;
import com.moerlong.carloan.modular.paybackMgr.txservice.vo.PayCenterPayResultVO;
import com.moerlong.carloan.common.constant.PayCenterCode;
import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.modular.payMgr.dao.PayInfoDao;
import com.moerlong.carloan.modular.payMgr.entity.PayApproveRecord;
import com.moerlong.carloan.modular.payMgr.entity.PayDetailInfo;
import com.moerlong.carloan.modular.payMgr.entity.PayInfo;
import com.moerlong.carloan.modular.payMgr.entity.enums.PayApproveStatus;
import com.moerlong.carloan.modular.payMgr.entity.enums.PayType;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentInfo;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentPayInfo;
import com.moerlong.carloan.util.ParamConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class PayInfoServiceImpl implements PayInfoService{

	private static Logger LOG = LoggerFactory.getLogger(PayInfoServiceImpl.class);

	@Autowired
	PayInfoDao mapper;

	@Resource
	private PayTxService payTxService;

	@Resource
	private PayDetailInfoService payDetailInfoService;

	@Resource
	private PayApproveRecordService payApproveRecordService;

	@Resource
	private IOrderSequenceService orderSequenceService;

	@Resource
	private SMSService smsService;

	@Resource
	private PayCenterService payCenterService;

	@Resource
	private RepaymentInfoService repaymentInfoService;

	@Resource
	private RepaymentPayInfoService repaymentPayInfoService;

	@Value("${system.selectBank.url}")
	private String selectBankUrl;

	@Transactional
	public void saveOrUpdate(PayInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional
	public void save(PayInfo entity) {
		mapper.save(entity);
	}
	
	@Transactional
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Transactional
	public void update(PayInfo entity) {
		mapper.update(entity);
	}
	
	public PayInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public PageInfo<PayInfo> selectPage(int pageSize,int pageNum, String orderCondition ) {
		PageHelper.startPage(pageNum, pageSize);
		List<PayInfo> pageList = mapper.selectPage( orderCondition);
		PageInfo<PayInfo> pageInfo = new PageInfo<PayInfo>(pageList);
		return pageInfo;
	}

	/**
	 * 业务经理审批
	 * @param payId
	 * @param operatorId
	 * @param operatorName
	 * @return
	 */
	@Override
	public ResultVO<Object> bussManagerApprove(Long payId, Integer operatorResult, String operatorTip, Long operatorId, String operatorName) {

		PayInfo temp = this.selectById(payId);

		if(temp.getPayStatus() != PayApproveStatus.CREATE.getValue()){
			LOG.error("业务经理审批 请求状态不正确 payId={},status={}", payId, temp.getPayStatus());
			return ResultVO.build(ErrorCode.PAY_PROCESS_ERROR);
		}

		Date now = new Date();
		PayApproveRecord entity = new PayApproveRecord();
		entity.setIsDeleted(0);
		entity.setPayId(payId);
		entity.setOperatorResult(operatorResult);
		entity.setOperatorTip(operatorTip);
		entity.setOperatorId(operatorId);
		entity.setOperatorName(operatorName);
		entity.setOperatorTime(now);
		entity.setCreateTime(now);
		entity.setUpdateTime(now);
		try {
			//1 添加审批记录
			payApproveRecordService.save(entity);

			//2 更新payInfo表状态
			PayInfo payInfo = new PayInfo();
			payInfo.setId(payId);
			if(operatorResult == 1){
				payInfo.setPayStatus(PayApproveStatus.BUSS_MANAGER_APPROVE.getValue());
				payInfo.setPayStatusDesc(PayApproveStatus.BUSS_MANAGER_APPROVE.getDesc());
			}else{
				payInfo.setPayStatus(PayApproveStatus.BUSS_MANAGER_APPROVE_FAIL.getValue());
				payInfo.setPayStatusDesc(PayApproveStatus.BUSS_MANAGER_APPROVE_FAIL.getDesc());
			}
			payInfo.setBussManagerId(operatorId);
			payInfo.setUpdateTime(now);
			this.update(payInfo);

			//3 成功则短信通知财务  失败则短信通知业务员
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("name", temp.getCustName());
			List<SendMsgVo> mobileList = new ArrayList<>();


			boolean ret;
			String smsCode;
			if(operatorResult == 1){
				//成功则通知财务
				mobileList = ParamConstants.getValue(ParamConstants.ROLE_FINA);
				smsCode = SMSService.APPLY_SUCCESS;
			}else{
				//失败则通知业务员
				mobileList = ParamConstants.getValue(ParamConstants.ROLE_NQ);
				smsCode = SMSService.APPLY_FAIL;

			}
			try{
				ret = smsService.sendMsgSMS(mobileList, smsCode, param);
				if(ret == false){
					LOG.error("===>>>申请单：{}, 业务经理审核，发送短信通知失败", payId);
				}else{
					LOG.info("===>>>申请单：{}, 业务经理审核，发送短信通知成功", payId);
				}
			}catch(Exception e){
				LOG.error("===>>>申请单：{}, 业务经理审核，发送短信通知异常 {}", payId, e);
			}

			return ResultVO.build(ErrorCode.SUCCESS);
		} catch (Throwable e) {
			LOG.error("业务经理审批 流程发生错误 e={}", e);
			return ResultVO.build(ErrorCode.PAY_PROCESS_ERROR);
		}
	}

	/**
	 * 财务审批
	 * @param payId
	 * @param amount
	 * @param operatorResult
	 * @param operatorTip
	 * @param operatorId
	 * @param operatorName
	 * @return
	 */
	@Override
	@Transactional
	public ResultVO<Object> financeApprove(Long payId, String amount, Integer operatorResult, String operatorTip, Long operatorId, String operatorName) {
		try {
			int numFlag = 0;		//0--表示第一次放款  1--表示第二次放款
			Date now = new Date();
			//1 判断借款状态
			PayInfo temp = mapper.selectById(payId);
			if (temp == null) {
				LOG.error("can't find payId={}", payId);
				return ResultVO.build(ErrorCode.PAY_INFO_NOT_EXIST);
			}

			BigDecimal payMoney = new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP);


			if(temp.getPayedAmount().compareTo(BigDecimal.ZERO) > 0){
				numFlag = 1;
			}

			if(numFlag == 0){
				//TODO 这里暂定1500块
				if(payMoney.compareTo(new BigDecimal(1500)) < 0 || payMoney.compareTo(temp.getTotalAmount()) >= 0){
					LOG.error("首次放款金额不正确 amount={}", amount);
					return ResultVO.build(ErrorCode.PAY_MONEY_ERROR);
				}
			}else{
				if (payMoney.compareTo(temp.getNotPayAmount()) != 0) {
					LOG.error("二次放款输入金额不等于未放款金额 payId={} 输入金额={} 未放款金额={}", payId, payMoney, temp.getNotPayAmount());
					return ResultVO.build(ErrorCode.PAY_MONEY_ERROR);
				}
			}

			if ((temp.getPayStatus() != PayApproveStatus.BUSS_MANAGER_APPROVE.getValue()) &&
					(temp.getPayStatus() != PayApproveStatus.CONFIRM_FEE.getValue()) &&
					(temp.getPayStatus() != PayApproveStatus.FIRST_PAY_FAIL.getValue()) &&
					(temp.getPayStatus() != PayApproveStatus.SECOND_PAY_FAIL.getValue())) {
				LOG.error("放款状态不正确 payId={} status={}", payId, temp.getPayStatus());
				return ResultVO.build(ErrorCode.PAY_PROCESS_ERROR);
			}

			//查询放款详情表中是否存在支付中的信息
			PayDetailInfo paying = payDetailInfoService.selectByPayIdAndStatus(payId, PayStatus.PAYING.getValue());
			if (paying != null) {
				LOG.error("存在放款中的订单 payId={}", payId);
				return ResultVO.build(ErrorCode.REPAYMENT_PAYING_EXIST);
			}

			//2 更改放款总表状态
			PayInfo payInfo = new PayInfo();
			payInfo.setId(payId);
			if (operatorResult == 1) {
				if(numFlag == 0){
					payInfo.setPayStatus(PayApproveStatus.FIRST_FINANCE_APPROVE.getValue());
					payInfo.setPayStatusDesc(PayApproveStatus.FIRST_FINANCE_APPROVE.getDesc());
				}else{
					payInfo.setPayStatus(PayApproveStatus.SECOND_FINANCE_APPROVE.getValue());
					payInfo.setPayStatusDesc(PayApproveStatus.SECOND_FINANCE_APPROVE.getDesc());
				}
				payInfo.setApproveAmount(payMoney);
			} else {
				if(numFlag == 0){
					payInfo.setPayStatus(PayApproveStatus.FIRST_FINANCE_APPROVE_FAIL.getValue());
					payInfo.setPayStatusDesc(PayApproveStatus.FIRST_FINANCE_APPROVE_FAIL.getDesc());
				}else{
					payInfo.setPayStatus(PayApproveStatus.SECOND_FINANCE_APPROVE_FAIL.getValue());
					payInfo.setPayStatusDesc(PayApproveStatus.SECOND_FINANCE_APPROVE_FAIL.getDesc());
				}
				payInfo.setApproveAmount(new BigDecimal(0));
			}
			if(numFlag == 0) {
				payInfo.setFirstFinanceId(operatorId);
			}else{
				payInfo.setSecondFinanceId(operatorId);
			}
			payInfo.setUpdateTime(now);
			this.update(payInfo);

			//2 添加一条审批记录
			PayApproveRecord record = new PayApproveRecord();
			record.setIsDeleted(0);
			record.setPayId(payId);
			record.setOperatorResult(operatorResult);
			record.setOperatorTip(operatorTip);
			record.setOperatorId(operatorId);
			record.setOperatorName(operatorName);
			record.setOperatorTime(now);
			record.setCreateTime(now);
			record.setUpdateTime(now);
			payApproveRecordService.save(record);

			//4 发送短信通知
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("name", temp.getCustName());
			List<SendMsgVo> mobileList = new ArrayList<>();

			boolean ret;
			String smsCode ;
			if (operatorResult == 1) {
				//成功则通知财务主管
				mobileList = ParamConstants.getValue(ParamConstants.ROLE_FINA_MA);
				if(numFlag == 0){
					param.put("time", "一");
				}else{
					param.put("time", "二");
				}
				smsCode = SMSService.FINANCE_AUDIT_SUCCESS;
			} else {
				//失败则通知业务员
				mobileList = ParamConstants.getValue(ParamConstants.ROLE_NQ);
				if(numFlag == 0){
					param.put("time", "一");
				}else{
					param.put("time", "二");
				}
				smsCode = SMSService.FINANCE_AUDIT_FAIL;
			}
			try {
				ret = smsService.sendMsgSMS(mobileList, smsCode, param);
				if (ret == false) {
					LOG.error("===>>>申请单：{}, 财务审核，发送短信通知失败", payId);
				} else {
					LOG.info("===>>>申请单：{}, 财务审核，发送短信通知成功", payId);
				}
			}catch (Exception e){
				LOG.error("===>>>申请单：{}, 财务审核，发送短信通知异常 {}", payId, e);
			}

			return ResultVO.build(ErrorCode.SUCCESS);
		}catch(Exception e){
			LOG.error("财务审批流程发生错误 e={}", e);
			return ResultVO.build(ErrorCode.PAY_PROCESS_FINANCE_APPROVE_ERROR);
		}
	}

	@Override
	public ResultVO<Object> getPreFeeByPayId(Long payId) {
		PayInfo temp = mapper.selectById(payId);
		if (temp == null) {
			LOG.error("can't find payId={}", payId);
			return ResultVO.build(ErrorCode.PAY_INFO_NOT_EXIST);
		}

		RepaymentInfo repaymentInfo = repaymentInfoService.selectByPayId(payId);
		if(repaymentInfo == null){
			LOG.error("没有对应payId={} 的还款总表信息!", payId);
			return ResultVO.build(ErrorCode.REPAYMENT_INFO_NOT_EXIST);
		}

		return ResultVO.build(ErrorCode.SUCCESS, (Object)repaymentInfo.getPreFee());
	}

	/**
	 * 前期费用失败，财务补签
	 * @param payId
	 * @return
	 */
	@Override
	@Transactional
	public ResultVO<Object> preRepayFailManual(Long payId, Integer payType, Long operatorId, String operatorName) {

		if(payId == null || payType == null){
			LOG.error("param null payId={} payType={}", payId, payType);
			return ResultVO.build(ErrorCode.PARAM_EMPTY);
		}

		if(payType != 1 && payType != 2){
			LOG.error("param payType error payType={}", payType);
			return ResultVO.build(ErrorCode.PARAM_ERROR);
		}

		PayInfo temp = mapper.selectById(payId);
		if (temp == null) {
			LOG.error("can't find payId={}", payId);
			return ResultVO.build(ErrorCode.PAY_INFO_NOT_EXIST);
		}

		//1、 判断状态

		if(temp.getPayStatus() != PayApproveStatus.COST_FAIL.getValue()){
			LOG.error("请求状态不正确 preRepayFailManual payId={},status={}", payId, temp.getPayStatus());
			return ResultVO.build(ErrorCode.PAY_PROCESS_ERROR);
		}

		RepaymentInfo repaymentInfo = repaymentInfoService.selectByPayId(payId);
		if(repaymentInfo == null){
			LOG.error("没有对应payId={} 的还款总表信息!", payId);
			return ResultVO.build(ErrorCode.REPAYMENT_INFO_NOT_EXIST);
		}

		//2 进行补签
		Date now = new Date();
		RepaymentPayInfo repaymentPayInfo = new RepaymentPayInfo();
		repaymentPayInfo.setRepaymentId(repaymentInfo.getId());
		if(payType == 1){
			//手动代扣
			repaymentPayInfo.setPayType(RepaymentType.PAY_TYPE_PRE_FEE_MANUAL.getValue());
			repaymentPayInfo.setPayTypeDesc(RepaymentType.PAY_TYPE_PRE_FEE_MANUAL.getDesc());
		}else{
			//对公转账
			repaymentPayInfo.setPayType(RepaymentType.PAY_TYPE_PRE_FEE_PUBLIC.getValue());
			repaymentPayInfo.setPayTypeDesc(RepaymentType.PAY_TYPE_PRE_FEE_PUBLIC.getDesc());
		}
		repaymentPayInfo.setAmount(repaymentInfo.getPreFee());
		repaymentPayInfo.setRepaymentPeriodNum(0);
		repaymentPayInfo.setBindingMobile(temp.getCustMobile());
		repaymentPayInfo.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
		repaymentPayInfo.setPayingNum(0);
		repaymentPayInfo.setPayCode("0000");
		repaymentPayInfo.setPayMsg("成功");
		repaymentPayInfo.setPayTime(now);
		repaymentPayInfo.setPayChannel("BF002");
		repaymentPayInfo.setPayBank(temp.getBankName());
		repaymentPayInfo.setPayCard(temp.getBankCardNo());
		repaymentPayInfo.setCreateTime(now);
		repaymentPayInfo.setUpdateTime(now);
		repaymentPayInfo.setIsDeleted(0);
		repaymentPayInfo.setRemark("手工补录");
		repaymentPayInfoService.save(repaymentPayInfo);

		//3 添加审批记录
		PayApproveRecord entity = new PayApproveRecord();
		entity.setIsDeleted(0);
		entity.setPayId(payId);
		entity.setOperatorResult(1);
		entity.setOperatorTip("手工补录前期费用");
		entity.setOperatorId(operatorId);
		entity.setOperatorName(operatorName);
		entity.setOperatorTime(now);
		entity.setCreateTime(now);
		entity.setUpdateTime(now);
		payApproveRecordService.save(entity);

		//4 更新状态
		PayInfo payInfo = new PayInfo();
		payInfo.setId(payId);
		payInfo.setPayStatus(PayApproveStatus.COST_SUCCESS.getValue());
		payInfo.setPayStatusDesc(PayApproveStatus.COST_SUCCESS.getDesc());
		payInfo.setUpdateTime(now);
		mapper.update(payInfo);

		//5 收取一次性手续费
		ResultVO<Object> r = repaymentCharge(payId);
		if(!r.isSuccess()){
			return r;
		}

		//6 进行前台服务费扣款
		r = repaymentReceptionFee(payId);
		return r;
	}

	/**
	 * 财务经理审批
	 * @param payId
	 * @param operatorResult
	 * @param operatorTip
	 * @param operatorId
	 * @param operatorName
	 * @return
	 */
	@Override
	@Transactional
	public ResultVO<Object> financeManagerApprove(Long payId, Integer operatorResult, String operatorTip, Long operatorId, String operatorName) {

		try{
			int numFlag = 0;		//0--表示第一次放款  1--表示第二次放款
			int payFlag = 0;		//1--成功 2--失败 3--处理中
			int isFinished = 0;		//0--放款未完成 1--放款完成

			Date now = new Date();
			//LOG.info("===[financeManagerApprove] start--->payId={}", payId);
			//1 检查流程
			PayInfo temp = this.selectById(payId);
			if((temp.getPayStatus() != PayApproveStatus.FIRST_FINANCE_APPROVE.getValue()) &&
					(temp.getPayStatus() != PayApproveStatus.SECOND_FINANCE_APPROVE.getValue())){
				LOG.error("请求状态不正确 payId={},status={}", payId, temp.getPayStatus());
				return ResultVO.build(ErrorCode.PAY_PROCESS_ERROR);
			}

			//查询放款详情表中是否存在支付中的信息
			//LOG.info("===[selectByPayIdAndStatus] --->payId={}", payId);
			PayDetailInfo paying = payDetailInfoService.selectByPayIdAndStatus(payId, PayStatus.PAYING.getValue());
			if(paying != null){
				LOG.error("存在放款中的订单 payId={}", payId);
				return ResultVO.build(ErrorCode.REPAYMENT_PAYING_EXIST);
			}

			BigDecimal payMoney = temp.getApproveAmount();
			if(payMoney.compareTo(temp.getNotPayAmount()) > 0){
				LOG.error("输入的金额 超过未放款金额 payId={} 输入金额={} 未放款金额={}", payId, payMoney, temp.getNotPayAmount());
				return ResultVO.build(ErrorCode.PAY_MONEY_ERROR);
			}

			if(temp.getPayedAmount().compareTo(BigDecimal.ZERO) > 0){
				numFlag = 1;
			}

			//LOG.info("===[payApproveRecordService save] --->payId={}", payId);
			//2 生成审批记录
			PayApproveRecord entity = new PayApproveRecord();
			entity.setIsDeleted(0);
			entity.setPayId(payId);
			entity.setOperatorResult(operatorResult);
			entity.setOperatorTip(operatorTip);
			entity.setOperatorId(operatorId);
			entity.setOperatorName(operatorName);
			entity.setOperatorTime(now);
			entity.setCreateTime(now);
			entity.setUpdateTime(now);
			payApproveRecordService.save(entity);

			//LOG.info("===[payInfo update] --->payId={}", payId);
			//3 修改总表状态
			PayInfo payInfo = new PayInfo();
			payInfo.setId(payId);

			if(operatorResult == 1){
				//放款审批通过
				if(numFlag == 0){
					payInfo.setPayStatus(PayApproveStatus.FIRST_FINANCE_MANAGER_APPROVE.getValue());
					payInfo.setPayStatusDesc(PayApproveStatus.FIRST_FINANCE_MANAGER_APPROVE.getDesc());
				}else{
					payInfo.setPayStatus(PayApproveStatus.SECOND_FINANCE_MANAGER_APPROVE.getValue());
					payInfo.setPayStatusDesc(PayApproveStatus.SECOND_FINANCE_MANAGER_APPROVE.getDesc());
				}
			}else{
				//放款审批不通过
				if(numFlag == 0) {
					payInfo.setPayStatus(PayApproveStatus.FIRST_FINANCE_MANAGER_APPROVE_FAIL.getValue());
					payInfo.setPayStatusDesc(PayApproveStatus.FIRST_FINANCE_MANAGER_APPROVE_FAIL.getDesc());
				}else{
					payInfo.setPayStatus(PayApproveStatus.SECOND_FINANCE_MANAGER_APPROVE_FAIL.getValue());
					payInfo.setPayStatusDesc(PayApproveStatus.SECOND_FINANCE_MANAGER_APPROVE_FAIL.getDesc());
				}
			}
			if(numFlag == 0){
				payInfo.setFirstFinanceManagerId(operatorId);
			}else{
				payInfo.setSecondFinanceManagerId(operatorId);
			}
			payInfo.setUpdateTime(new Date());
			this.update(payInfo);

			//4 短信通知
			//LOG.info("===[sms send] --->payId={}", payId);
			boolean ret;
			String smsCode;
			List<SendMsgVo> mobileList = new ArrayList<>();
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("name", temp.getCustName());

			if(numFlag == 0){
				param.put("time", "一");
			}else{
				param.put("time", "二");
			}

			if (operatorResult == 1) {
				// 成功
				List<SendMsgVo> mobileList1 = ParamConstants.getValue(ParamConstants.ROLE_NQ);
				List<SendMsgVo> mobileList2 = ParamConstants.getValue(ParamConstants.ROLE_FINA);
				mobileList.addAll(mobileList1);
				mobileList.addAll(mobileList2);
				smsCode = SMSService.FINANCE_MANAGER_AUDIT_SUCCESS;
			} else {
				//失败则通知业务员
				mobileList = ParamConstants.getValue(ParamConstants.ROLE_NQ);
				smsCode = SMSService.FINANCE_MANAGER_AUDIT_FAIL;
			}
			try {
				ret = smsService.sendMsgSMS(mobileList, smsCode, param);
				if (ret == false) {
					LOG.error("===>>>申请单：{}, 财务主管审核，发送短信通知失败", payId);
				} else {
					LOG.info("===>>>申请单：{}, 财务主管审核，发送短信通知成功", payId);
				}
			}catch (Exception e){
				LOG.error("===>>>申请单：{}, 财务主管审核，发送短信通知异常 {}", payId, e);
			}

			if(operatorResult == 0){
				//如果审批失败 则直接返回
				return ResultVO.build(ErrorCode.SUCCESS);
			}

			// 5 进行放款
			// 5.1 生成放款详情记录
			now = new Date();
			String sequenceNo = orderSequenceService.getTradeSequence();
			PayDetailInfo payDetailInfo = new PayDetailInfo();
			payDetailInfo.setPayId(payId);
			payDetailInfo.setPayNum(numFlag == 0 ? 1 : 2);
			payDetailInfo.setCustMobile(temp.getCustMobile());
			payDetailInfo.setBankName(temp.getBankName());
			payDetailInfo.setBankCardNo(temp.getBankCardNo());
			payDetailInfo.setPayStatus(PayStatus.CREATE.getValue());
			payDetailInfo.setPayType(PayType.PAY_TYPE_SYS_PAY.getValue());
			payDetailInfo.setPayingNum(0);
			payDetailInfo.setSerialNo(sequenceNo);
			payDetailInfo.setOperatorId(operatorId);
			payDetailInfo.setOperatorDate(now);
			payDetailInfo.setCurPayAmount(payMoney);
			payDetailInfo.setCreateTime(now);
			payDetailInfo.setUpdateTime(now);
			payDetailInfo.setIsDeleted(0);
			payDetailInfoService.save(payDetailInfo);

			// 5.2 调用payCenter 进行放款
			ResultVO<PayCenterPayResultVO> payResultVO = null;
			try{
				payResultVO = payCenterService.payMoney(sequenceNo, null, temp.getBankCardNo(),
						temp.getCustName(), temp.getCustMobile(), temp.getCustIdNo(), null, null,
						null, "id", payMoney.toString(), temp.getBankName());
			}catch(Exception e){
				LOG.error("[===>>>放款ID:{} 放款详情ID:{}，调用支付中心代付接口异常===],excp:{}", temp.getId(), payDetailInfo.getId(), e);
			}

			// 5.3 更新放款详情表
			now = new Date();
			PayDetailInfo info = new PayDetailInfo();
			info.setId(payDetailInfo.getId());
			info.setUpdateTime(now);

			if (payResultVO == null || payResultVO.getData() == null || !payResultVO.isSuccess()) {
				//失败
				info.setPayStatus(PayStatus.PAY_FAIL.getValue());
				payFlag = 2;
			}

			if (payResultVO.getData().getResultCode().equals(PayCenterCode.SUCCESS)) {
				// 代扣成功，更新支付状态
				info.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
				info.setPayChannel(payResultVO.getData().getPayPlatform());
				info.setPayCode(payResultVO.getData().getResultCode());
				info.setPayMsg(payResultVO.getData().getMessage());
				payFlag = 1;
			} else if (payResultVO.getData().getResultCode().equals(PayCenterCode.DOING)) {
				// 代扣处理中，更新支付状态为支付中
				info.setPayStatus(PayStatus.PAYING.getValue());
				info.setPayChannel(payResultVO.getData().getPayPlatform());
				info.setPayCode(payResultVO.getData().getResultCode());
				info.setPayMsg(payResultVO.getData().getMessage());
				payFlag = 3;
			} else {
				info.setPayStatus(PayStatus.PAY_FAIL.getValue());
				info.setPayChannel(payResultVO.getData().getPayPlatform());
				info.setPayCode(payResultVO.getData().getResultCode());
				info.setPayMsg(payResultVO.getData().getMessage());
				payFlag = 2;
			}

			payDetailInfoService.update(info);

			//6 更新总放款表状态
			payInfo = new PayInfo();
			payInfo.setId(payId);
			if(payFlag == 1){
				payInfo.setNotPayAmount(temp.getNotPayAmount().subtract(payMoney));
				payInfo.setPayedAmount(temp.getPayedAmount().add(payMoney));
				if(numFlag == 0){
					payInfo.setPayStatus(PayApproveStatus.FIRST_PAY_SUCCESS.getValue());
					payInfo.setPayStatusDesc(PayApproveStatus.FIRST_PAY_SUCCESS.getDesc());
				}
				else{
					payInfo.setPayStatus(PayApproveStatus.SECOND_PAY_SUCCESS.getValue());
					payInfo.setPayStatusDesc(PayApproveStatus.SECOND_PAY_SUCCESS.getDesc());
				}
				if(payInfo.getNotPayAmount().compareTo(BigDecimal.ZERO) == 0){
					//放款完成
					payInfo.setPayStatus(PayApproveStatus.PAY_SUCCESS.getValue());
					payInfo.setPayStatusDesc(PayApproveStatus.PAY_SUCCESS.getDesc());
					isFinished = 1;		//表示放款完成
				}
			}else if(payFlag == 2){
				if(numFlag == 0){
					payInfo.setPayStatus(PayApproveStatus.FIRST_PAY_FAIL.getValue());
					payInfo.setPayStatusDesc(PayApproveStatus.FIRST_PAY_FAIL.getDesc());
				}
				else{
					payInfo.setPayStatus(PayApproveStatus.SECOND_PAY_FAIL.getValue());
					payInfo.setPayStatusDesc(PayApproveStatus.SECOND_PAY_FAIL.getDesc());
				}
			}else{
				if(numFlag == 0){
					payInfo.setPayStatus(PayApproveStatus.FIRST_PAY_PAYING.getValue());
					payInfo.setPayStatusDesc(PayApproveStatus.FIRST_PAY_PAYING.getDesc());
				}
				else{
					payInfo.setPayStatus(PayApproveStatus.SECOND_PAY_PAYING.getValue());
					payInfo.setPayStatusDesc(PayApproveStatus.SECOND_PAY_PAYING.getDesc());
				}
			}
			payInfo.setUpdateTime(now);
			this.update(payInfo);


			//7 短信通知
			mobileList = new ArrayList<>();
			param = new HashMap<String, Object>();
			param.put("name", temp.getCustName());
			if(numFlag == 0){
				param.put("time", "一");
			}else{
				param.put("time", "二");
			}
			if (payFlag == 1) {
				param.put("result", "成功");
				//添加手机列表
				List<SendMsgVo> m1 = ParamConstants.getValue(ParamConstants.ROLE_NQ);
				List<SendMsgVo> m2 = ParamConstants.getValue(ParamConstants.ROLE_FINA);
				List<SendMsgVo> m3 = ParamConstants.getValue(ParamConstants.ROLE_FINA_MA);
				mobileList.addAll(m1);
				mobileList.addAll(m2);
				mobileList.addAll(m3);
			} else if(payFlag == 2){
				param.put("result", "失败");
				List<SendMsgVo> m1 = ParamConstants.getValue(ParamConstants.ROLE_NQ);
				List<SendMsgVo> m2 = ParamConstants.getValue(ParamConstants.ROLE_FINA);
				List<SendMsgVo> m3 = ParamConstants.getValue(ParamConstants.ROLE_FINA_MA);
				List<SendMsgVo> m4 = ParamConstants.getValue(ParamConstants.ROLE_NQ);
				mobileList.addAll(m1);
				mobileList.addAll(m2);
				mobileList.addAll(m3);
				mobileList.addAll(m4);
			}
			else{
				param.put("result", "正在处理");
				List<SendMsgVo> m1 = ParamConstants.getValue(ParamConstants.ROLE_NQ);
				List<SendMsgVo> m2 = ParamConstants.getValue(ParamConstants.ROLE_FINA);
				List<SendMsgVo> m3 = ParamConstants.getValue(ParamConstants.ROLE_FINA_MA);
				mobileList.addAll(m1);
				mobileList.addAll(m2);
				mobileList.addAll(m3);
			}
			try{
				ret = smsService.sendMsgSMS(mobileList, SMSService.PAY_RESULT, param);
				if (ret == false) {
					LOG.error("===>>>申请单：{}, 放款结果，发送短信通知失败", payId);
				} else {
					LOG.info("===>>>申请单：{}, 放款结果，发送短信通知成功", payId);
				}
			}catch(Exception e){
				LOG.error("===>>>申请单：{}, 放款结果，发送短信通知异常 {}", payId, e);
			}

			if(payFlag != 1){
				//对于放款不是成功的 在这里返回
				return ResultVO.build(ErrorCode.SUCCESS);
			}

			//8 如果不是首次放款则在这里返回
			if(numFlag != 0) {
				return ResultVO.build(ErrorCode.SUCCESS);
			}

			//生成还款计划 扣除前期费用
			ResultVO<Object> r = paySuccessOpt(payId);
			return r;


		}catch (Exception e){
			LOG.error("财务经理审批流程发生错误 e={}", e);
			return ResultVO.build(ErrorCode.PAY_PROCESS_FINANCE_MANAGER_APPROVE_ERROR);
		}
	}

	/**
	 * 前台财务确认收款
	 * @param payId
	 * @param operatorId
	 * @param operatorName
	 * @return
	 */
	@Override
	@Transactional
	public ResultVO<Object> ReceptionFinanceApprove(Long payId, Long operatorId, String operatorName) {
		PayInfo temp = mapper.selectById(payId);
		if(temp.getPayStatus() != PayApproveStatus.COST_CHARGE_SUCCESS.getValue() &&
				temp.getPayStatus() != PayApproveStatus.COST_CHARGE_FAIL.getValue()){
			LOG.error("请求状态不正确 ReceptionFinanceApprove payId={},status={}", payId, temp.getPayStatus());
			return ResultVO.build(ErrorCode.PAY_PROCESS_ERROR);
		}

		Date now = new Date();

		//如果是扣除前台服务费失败，则手动创建扣款记录
		if(temp.getPayStatus() == PayApproveStatus.COST_CHARGE_FAIL.getValue()){
			RepaymentInfo repaymentInfo = repaymentInfoService.selectByPayId(payId);
			if(repaymentInfo == null){
				LOG.error("没有对应payId={} 的还款总表信息!", payId);
				return ResultVO.build(ErrorCode.REPAYMENT_INFO_NOT_EXIST);
			}

			RepaymentPayInfo repaymentPayInfo = new RepaymentPayInfo();
			repaymentPayInfo.setRepaymentId(repaymentInfo.getId());
			repaymentPayInfo.setPayType(RepaymentType.PAY_TYPE_RECEPTION_CHARGE_FEE_MANUAL.getValue());
			repaymentPayInfo.setPayTypeDesc(RepaymentType.PAY_TYPE_RECEPTION_CHARGE_FEE_MANUAL.getDesc());
			repaymentPayInfo.setAmount(temp.getReceptionAmount());
			repaymentPayInfo.setRepaymentPeriodNum(0);
			repaymentPayInfo.setBindingMobile(temp.getCustMobile());
			repaymentPayInfo.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
			repaymentPayInfo.setPayingNum(0);
			repaymentPayInfo.setPayCode("0000");
			repaymentPayInfo.setPayMsg("成功");
			repaymentPayInfo.setPayTime(now);
			repaymentPayInfo.setPayChannel("BF002");
			repaymentPayInfo.setPayBank(temp.getBankName());
			repaymentPayInfo.setPayCard(temp.getBankCardNo());
			repaymentPayInfo.setCreateTime(now);
			repaymentPayInfo.setUpdateTime(now);
			repaymentPayInfo.setIsDeleted(0);
			repaymentPayInfo.setRemark("手工补录");
			repaymentPayInfoService.save(repaymentPayInfo);
		}

		//
		PayApproveRecord entity = new PayApproveRecord();
		entity.setIsDeleted(0);
		entity.setPayId(payId);
		entity.setOperatorResult(1);
		entity.setOperatorTip("手续费收取成功");
		entity.setOperatorId(operatorId);
		entity.setOperatorName(operatorName);
		entity.setOperatorTime(now);
		entity.setCreateTime(now);
		entity.setUpdateTime(now);
		try {
			//1 添加审批记录
			payApproveRecordService.save(entity);

			//2 更新payInfo表状态
			PayInfo payInfo = new PayInfo();
			payInfo.setId(payId);
			payInfo.setPayStatus(PayApproveStatus.CONFIRM_FEE.getValue());
			payInfo.setPayStatusDesc(PayApproveStatus.CONFIRM_FEE.getDesc());
			payInfo.setSecondBussId(operatorId);
			payInfo.setUpdateTime(now);
			this.update(payInfo);

			//3 成功则短信通知财务
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("name", temp.getCustName());
			List<SendMsgVo> mobileList = new ArrayList<>();

			boolean ret;
			String smsCode;
			//成功则通知财务
			mobileList = ParamConstants.getValue(ParamConstants.ROLE_FINA);
			smsCode = SMSService.BUSS_CHAGE_COM;
			try{
				ret = smsService.sendMsgSMS(mobileList, smsCode, param);
				if(ret == false){
					LOG.error("===>>>申请单：{}, 前台财务确认收取手续费，发送短信通知失败", payId);
				}else{
					LOG.info("===>>>申请单：{}, 前台财务确认收取手续费，发送短信通知成功", payId);
				}
			}catch(Exception e){
				LOG.error("===>>>申请单：{}, 前台财务确认收取手续费，发送短信通知异常 {}", payId, e);
			}

			return ResultVO.build(ErrorCode.SUCCESS);
		} catch (Throwable e) {
			LOG.error("前台财务服务费确认流程发生错误 e={}", e);
			return ResultVO.build(ErrorCode.PAY_PROCESS_ERROR);
		}
	}




	/**
	 * 收取前台手续费
	 * @param payId
	 * @return
	 */
	@Override
	@Transactional
	public ResultVO<Object> repaymentReceptionFee(Long payId){
		Date now = new Date();
		PayInfo temp = this.selectById(payId);

		if(temp.getIsReplaceCost() == 0){
			//这里如果是不进行代收前台手续费，则只更新状态即可返回
			PayInfo payInfo = new PayInfo();
			payInfo.setId(payId);
			payInfo.setPayStatus(PayApproveStatus.CONFIRM_FEE.getValue());
			payInfo.setPayStatusDesc(PayApproveStatus.CONFIRM_FEE.getDesc());
			payInfo.setUpdateTime(now);
			this.update(payInfo);
			return ResultVO.build(ErrorCode.SUCCESS);
		}

		ResultVO<Map<String,String>> vo = repaymentPreFee(payId, RepaymentType.PAY_TYPE_RECEPTION_CHARGE_FEE.getValue());
		if(vo.getStatus() != ErrorCode.SUCCESS.getErrCode()){
			LOG.error("前台手续费扣款失败");
			return ResultVO.build(vo.getStatus(), vo.getMsg());
		}

		int repayFlag = Integer.parseInt(vo.getData().get("ret"));
		LOG.info("前台手续费扣款结果为 repayFlag={}", repayFlag);


		PayInfo payInfo = new PayInfo();
		payInfo.setId(payId);
		if(repayFlag == 1){
			//成功
			payInfo.setPayStatus(PayApproveStatus.COST_CHARGE_SUCCESS.getValue());
			payInfo.setPayStatusDesc(PayApproveStatus.COST_CHARGE_SUCCESS.getDesc());
		}else if(repayFlag == 2){
			//失败
			payInfo.setPayStatus(PayApproveStatus.COST_CHARGE_FAIL.getValue());
			payInfo.setPayStatusDesc(PayApproveStatus.COST_CHARGE_FAIL.getDesc());
		}else{
			//处理中
			payInfo.setPayStatus(PayApproveStatus.COST_CHARGE_PAYING.getValue());
			payInfo.setPayStatusDesc(PayApproveStatus.COST_CHARGE_PAYING.getDesc());
		}
		payInfo.setUpdateTime(now);
		this.update(payInfo);

		//10 短信通知
		boolean ret;
		ArrayList<SendMsgVo> mobileList = new ArrayList<>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", temp.getCustName());
		List<SendMsgVo> m1 = ParamConstants.getValue(ParamConstants.ROLE_NQ);
		List<SendMsgVo> m2 = ParamConstants.getValue(ParamConstants.ROLE_FINA);
		List<SendMsgVo> m3 = ParamConstants.getValue(ParamConstants.ROLE_FINA_MA);
		List<SendMsgVo> m4 = ParamConstants.getValue(ParamConstants.ROLE_NQ);
		List<SendMsgVo> m5 = ParamConstants.getValue(ParamConstants.ROLE_NQ);
		if (repayFlag == 1) {
			param.put("result", "成功");
			mobileList.addAll(m1);
			mobileList.addAll(m2);
			mobileList.addAll(m3);
			mobileList.addAll(m5);

		} else if(repayFlag == 2){
			param.put("result", "失败");
			mobileList.addAll(m1);
			mobileList.addAll(m2);
			mobileList.addAll(m3);
			mobileList.addAll(m4);
			mobileList.addAll(m5);
		}
		else{
			param.put("result", "正在处理");
			mobileList.addAll(m1);
			mobileList.addAll(m2);
			mobileList.addAll(m3);
			mobileList.addAll(m5);
		}
		try {
			ret = smsService.sendMsgSMS(mobileList, SMSService.REPAY_RECEPTION_RESULT, param);
			if (ret == false) {
				LOG.error("===>>>申请单：{}, 收取前台手续费，发送短信通知失败", payId);
			} else {
				LOG.info("===>>>申请单：{}, 收取前台手续费，发送短信通知成功", payId);
			}
		}catch(Exception e){
			LOG.error("===>>>申请单：{}, 收取前台手续费，发送短信通知异常 {}", payId, e);
		}
		return ResultVO.build(ErrorCode.SUCCESS);
	}

	/**
	 * 收取一次性手续费
	 * @param payId
	 * @return
	 */
	@Override
	@Transactional
	public ResultVO<Object> repaymentCharge(Long payId){
		Date now = new Date();
		PayInfo temp = this.selectById(payId);

		if(temp.getIsPerCharge() == 0){
			//这里如果是分期手续费的话，则直接返回
			return ResultVO.build(ErrorCode.SUCCESS);
		}

		ResultVO<Map<String,String>> vo = repaymentPreFee(payId, RepaymentType.PAY_TYPE_ONCE_CHARGE.getValue());
		if(vo.getStatus() != ErrorCode.SUCCESS.getErrCode()){
			LOG.error("一次性手续费扣款失败");
			return ResultVO.build(vo.getStatus(), vo.getMsg());
		}

		int repayFlag = Integer.parseInt(vo.getData().get("ret"));
		LOG.info("一次性手续费扣款结果为 repayFlag={}", repayFlag);


		PayInfo payInfo = new PayInfo();
		payInfo.setId(payId);
		if(repayFlag == 1){
			//成功
			payInfo.setPayStatus(PayApproveStatus.COST_ONCE_CHARGE_SUCCESS.getValue());
			payInfo.setPayStatusDesc(PayApproveStatus.COST_ONCE_CHARGE_SUCCESS.getDesc());
		}else if(repayFlag == 2){
			//失败
			payInfo.setPayStatus(PayApproveStatus.COST_ONCE_CHANGE_FAIL.getValue());
			payInfo.setPayStatusDesc(PayApproveStatus.COST_ONCE_CHANGE_FAIL.getDesc());
		}else{
			//处理中
			payInfo.setPayStatus(PayApproveStatus.COST_ONCE_CHANGE_PAYING.getValue());
			payInfo.setPayStatusDesc(PayApproveStatus.COST_ONCE_CHANGE_PAYING.getDesc());
		}
		payInfo.setUpdateTime(now);
		this.update(payInfo);

		//10 短信通知
		boolean ret;
		ArrayList<SendMsgVo> mobileList = new ArrayList<>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", temp.getCustName());
		List<SendMsgVo> m1 = ParamConstants.getValue(ParamConstants.ROLE_NQ);
		List<SendMsgVo> m2 = ParamConstants.getValue(ParamConstants.ROLE_FINA);
		List<SendMsgVo> m3 = ParamConstants.getValue(ParamConstants.ROLE_FINA_MA);
		List<SendMsgVo> m4 = ParamConstants.getValue(ParamConstants.ROLE_NQ);
		List<SendMsgVo> m5 = ParamConstants.getValue(ParamConstants.ROLE_NQ);
		if (repayFlag == 1) {
			param.put("result", "成功");
			mobileList.addAll(m1);
			mobileList.addAll(m2);
			mobileList.addAll(m3);
			mobileList.addAll(m5);

		} else if(repayFlag == 2){
			param.put("result", "失败");
			mobileList.addAll(m1);
			mobileList.addAll(m2);
			mobileList.addAll(m3);
			mobileList.addAll(m4);
			mobileList.addAll(m5);
		}
		else{
			param.put("result", "正在处理");
			mobileList.addAll(m1);
			mobileList.addAll(m2);
			mobileList.addAll(m3);
			mobileList.addAll(m5);
		}
		try {
			ret = smsService.sendMsgSMS(mobileList, SMSService.REPAY_ONCE_CHARGE_RESULT, param);
			if (ret == false) {
				LOG.error("===>>>申请单：{}, 收取一次性手续费，发送短信通知失败", payId);
			} else {
				LOG.info("===>>>申请单：{}, 收取一次性手续费，发送短信通知成功", payId);
			}
		}catch(Exception e){
			LOG.error("===>>>申请单：{}, 收取一次性手续费，发送短信通知异常 {}", payId, e);
		}
		return ResultVO.build(ErrorCode.SUCCESS);
	}

	@Override
	@Transactional
	public ResultVO<Object> paySuccessOpt(Long payId){
		//8 生成还款计划表

		PayInfo temp = this.selectById(payId);

		Date now = new Date();

		RepaymentInfo repaymentInfo = repaymentInfoService.selectByPayId(payId);
		if(repaymentInfo != null){
			LOG.warn("放款ID {}  已经生成还款计划表...", payId);
		}else{
			LOG.info("对放款ID {} 生成还款计划...", payId);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			repaymentInfoService.export(payId, temp.getContractNo(),temp.getIsPerCharge(), temp.getCustName(), temp.getCustIdNo(), temp.getCustMobile(),
					temp.getBankName(), temp.getBankCardNo(), temp.getTotalAmount().toString(), temp.getReceptionAmount().toString(), temp.getPeriodNum(),
					sdf.format(now), "19900101", 2);
		}

		//9 进行前期费用扣款
		ResultVO<Map<String,String>> vo = repaymentPreFee(payId, RepaymentType.PAY_TYPE_PRE_FEE.getValue());
		if(vo.getStatus() != ErrorCode.SUCCESS.getErrCode()){
			LOG.error("前期扣款失败");
			return ResultVO.build(vo.getStatus(), vo.getMsg());
		}

		int repayFlag = Integer.parseInt(vo.getData().get("ret"));
		LOG.info("前期扣款结果为 repayFlag={}", repayFlag);


		now = new Date();
		PayInfo payInfo = new PayInfo();
		payInfo.setId(payId);
		if(repayFlag == 1){
			//成功
			payInfo.setPayStatus(PayApproveStatus.COST_SUCCESS.getValue());
			payInfo.setPayStatusDesc(PayApproveStatus.COST_SUCCESS.getDesc());
		}else if(repayFlag == 2){
			//失败
			payInfo.setPayStatus(PayApproveStatus.COST_FAIL.getValue());
			payInfo.setPayStatusDesc(PayApproveStatus.COST_FAIL.getDesc());
		}else{
			//处理中
			payInfo.setPayStatus(PayApproveStatus.COST_PAYING.getValue());
			payInfo.setPayStatusDesc(PayApproveStatus.COST_PAYING.getDesc());
		}
		payInfo.setUpdateTime(now);
		this.update(payInfo);

		//10 短信通知
		boolean ret;
		List<SendMsgVo> mobileList = new ArrayList<>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", temp.getCustName());
		List<SendMsgVo> m1 = ParamConstants.getValue(ParamConstants.ROLE_NQ);
		List<SendMsgVo> m2 = ParamConstants.getValue(ParamConstants.ROLE_FINA);
		List<SendMsgVo> m3 = ParamConstants.getValue(ParamConstants.ROLE_FINA_MA);
		List<SendMsgVo> m4 = ParamConstants.getValue(ParamConstants.ROLE_NQ);
		List<SendMsgVo> m5 = ParamConstants.getValue(ParamConstants.ROLE_NQ);
		if (repayFlag == 1) {
			param.put("result", "成功");
			mobileList.addAll(m1);
			mobileList.addAll(m2);
			mobileList.addAll(m3);
			if(temp.getIsReplaceCost() == 0){
				mobileList.addAll(m5);
			}

		} else if(repayFlag == 2){
			param.put("result", "失败");
			mobileList.addAll(m1);
			mobileList.addAll(m2);
			mobileList.addAll(m3);
			mobileList.addAll(m4);
			if(temp.getIsReplaceCost() == 0){
				mobileList.addAll(m5);
			}
		}
		else{
			param.put("result", "正在处理");
			mobileList.addAll(m1);
			mobileList.addAll(m2);
			mobileList.addAll(m3);
			if(temp.getIsReplaceCost() == 0){
				mobileList.addAll(m5);
			}
		}
		try {
			ret = smsService.sendMsgSMS(mobileList, SMSService.REPAY_RESULT, param);
			if (ret == false) {
				LOG.error("===>>>申请单：{}, 收取前期费用，发送短信通知失败", payId);
			} else {
				LOG.info("===>>>申请单：{}, 收取前期费用，发送短信通知成功", payId);
			}
		}catch(Exception e){
			LOG.error("===>>>申请单：{}, 收取前期费用，发送短信通知异常 {}", payId, e);
		}

		if(repayFlag != 1){
			//没有扣取成功的话 就返回
			return ResultVO.build(ErrorCode.SUCCESS);
		}

		//扣取一次性手续费 如果有的话
		ResultVO<Object> r = repaymentCharge(payId);
		if(!r.isSuccess()){
			return r;
		}

		//这里表示前期费用扣取成功  继续进行前台手续费的扣除
		r = repaymentReceptionFee(payId);
		return r;
	}

	/**
	 * 进行前期费用扣款
	 * @param payId
	 * @return
	 */
	@Override
	public ResultVO<Map<String,String>> repaymentPreFee(Long payId, Integer payType) {

		Map<String,String> ret = new HashMap<>();
		RepaymentInfo repaymentInfo = repaymentInfoService.selectByPayId(payId);
		if(repaymentInfo == null){
			LOG.error("未找到还款总表记录 payId={}", payId);
			return ResultVO.build(ErrorCode.REPAYMENT_INFO_NOT_EXIST);
		}

		// 创建自动划扣支付单
		String sequenceNo = orderSequenceService.getTradeSequence();;
		Date now = new Date();

		RepaymentPayInfo repaymentPayInfo = new RepaymentPayInfo();
		repaymentPayInfo.setRepaymentId(repaymentInfo.getId());
		repaymentPayInfo.setRepaymentPlanId(null);
		repaymentPayInfo.setPayType(payType);	//标志成扣取前期费用
		repaymentPayInfo.setPayTypeDesc(RepaymentType.getEnumsByValue(payType).getDesc());
		repaymentPayInfo.setRepaymentPeriodNum(0);
		repaymentPayInfo.setSerialNo(sequenceNo);
		if(payType == RepaymentType.PAY_TYPE_RECEPTION_CHARGE_FEE.getValue()){
			repaymentPayInfo.setAmount(repaymentInfo.getReceptionAmount());
		}else if(payType == RepaymentType.PAY_TYPE_ONCE_CHARGE.getValue()){
			repaymentPayInfo.setAmount(repaymentInfo.getPredictCharge());
		}else{
			repaymentPayInfo.setAmount(repaymentInfo.getPreFee());
		}
		repaymentPayInfo.setPayStatus(PayStatus.PAYING.getValue());
		repaymentPayInfo.setPayingNum(0);
		repaymentPayInfo.setPayTime(now);
		repaymentPayInfo.setPayBank(repaymentInfo.getBankName());
		repaymentPayInfo.setPayCard(repaymentInfo.getBankCardNo());
		repaymentPayInfo.setBindingMobile(repaymentInfo.getCustMobile());
		repaymentPayInfo.setCreateTime(now);
		repaymentPayInfo.setUpdateTime(now);
		repaymentPayInfo.setIsDeleted(0);
		repaymentPayInfoService.save(repaymentPayInfo);

		ResultVO<PayCenterPayResultVO> payResultVO = null;
		try{
			if(payType == RepaymentType.PAY_TYPE_RECEPTION_CHARGE_FEE.getValue()) {
				payResultVO = payCenterService.deductMrlMoney(sequenceNo, null, repaymentInfo.getBankCardNo(),
						repaymentInfo.getCustName(), repaymentInfo.getCustMobile(), repaymentInfo.getCustIdNo(), null, null,
						null, "id", repaymentPayInfo.getAmount().toString(), repaymentInfo.getBankName());
			}else{
				payResultVO = payCenterService.deductMoney(sequenceNo, null, repaymentInfo.getBankCardNo(),
						repaymentInfo.getCustName(), repaymentInfo.getCustMobile(), repaymentInfo.getCustIdNo(), null, null,
						null, "id", repaymentPayInfo.getAmount().toString(), repaymentInfo.getBankName());
			}
		}catch(Exception e){
			LOG.error("[===>>>放款ID：{} 还款ID:{} 费用扣除，调用支付中心划扣接口异常===],excp:{}", payId, repaymentInfo.getId(), e);
			RepaymentPayInfo pay = new RepaymentPayInfo();
			pay.setId(repaymentPayInfo.getId());
			pay.setPayStatus(PayStatus.PAY_FAIL.getValue());
			pay.setUpdateTime(now);
			repaymentPayInfoService.update(pay);
			ret.put("ret", "2");
			ret.put("msg", "调用支付中心异常");
			return ResultVO.build(ErrorCode.SUCCESS, ret);
		}

		RepaymentPayInfo pay = new RepaymentPayInfo();
		pay.setId(repaymentPayInfo.getId());

		if (!(payResultVO != null && payResultVO.isSuccess())) {
			//失败
			pay.setPayStatus(PayStatus.PAY_FAIL.getValue());
			pay.setUpdateTime(now);
			repaymentPayInfoService.update(pay);
			ret.put("ret", "2");
			ret.put("msg", "调用支付中心异常");
			return ResultVO.build(ErrorCode.SUCCESS, ret);
		}

		if (payResultVO.getData().getResultCode().equals(PayCenterCode.SUCCESS)) {
			// 代扣成功，更新支付状态
			pay.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
			pay.setPayChannel(payResultVO.getData().getPayPlatform());
			pay.setPayCode(payResultVO.getData().getResultCode());
			pay.setPayMsg(payResultVO.getData().getMessage());
			pay.setUpdateTime(now);
			repaymentPayInfoService.update(pay);
			ret.put("ret", "1");
			ret.put("msg", "成功");
			return ResultVO.build(ErrorCode.SUCCESS, ret);
		} else if (payResultVO.getData().getResultCode().equals(PayCenterCode.DOING)) {
			// 代扣处理中，更新支付状态为支付中
			pay.setPayStatus(PayStatus.PAYING.getValue());
			pay.setPayChannel(payResultVO.getData().getPayPlatform());
			pay.setPayCode(payResultVO.getData().getResultCode());
			pay.setPayMsg(payResultVO.getData().getMessage());
			pay.setPayingNum(1);
			pay.setUpdateTime(now);
			repaymentPayInfoService.update(pay);
			ret.put("ret", "3");
			ret.put("msg", "处理中");
			return ResultVO.build(ErrorCode.SUCCESS, ret);
		} else {
			pay.setPayStatus(PayStatus.PAY_FAIL.getValue());
			pay.setPayChannel(payResultVO.getData().getPayPlatform());
			pay.setPayCode(payResultVO.getData().getResultCode());
			pay.setPayMsg(payResultVO.getData().getMessage());
			pay.setUpdateTime(now);
			repaymentPayInfoService.update(pay);
			ret.put("ret", "2");
			ret.put("msg", pay.getPayMsg());
			return ResultVO.build(ErrorCode.SUCCESS, ret);
		}
	}


	/**
	 * 根据条件查询放款总表
	 * @return
	 */
	public List<PayInfo> listByCondition(String custName, String custMobile, String beginTime, String endTime, Integer payStatus){

		return mapper.listByCondition(custName,custMobile,beginTime,endTime,payStatus);

	}


	/**
	 * 根据卡号查询出银行名称
	 * @return
	 */
	public Map<String,Object> selectBankName(String bankCardNo){
		RestTemplate restTemplate = new RestTemplate();
		Map<String,Object> res=new HashMap<String,Object>();
		String resp = "";
		try{

			resp = restTemplate.postForObject(selectBankUrl+"?accNo="+bankCardNo, null, String.class);
		}catch(Throwable e){
			LOG.error("======调用获取银行列表接口异常====== {}", e);
			return null;
		}
		if(StringUtils.isEmpty(resp)) {
			LOG.error("======调用获取银行列表接口失败====== {}", "无返回数据");
			return null;
		}
		Gson gs = new  Gson();
		Map<String,Object> map = gs.fromJson(resp, Map.class);
		String respStatus = (String)map.get("resultCode");


		if(!"0000".equals(respStatus)) {
			LOG.error("======调用获取银行列表接口失败====== {}", resp);

			res.put("resultCode",(String)map.get("resultCode"));
			res.put("message",(String)map.get("message"));
			return res;
		}else{
			res.put("resultCode",(String)map.get("resultCode"));
			res.put("message",(String)map.get("message"));
			res.put("data",(Map<String,String>)map.get("data"));
			return  res;
		}


	}

	@Override
	public PayInfo selectByApplyId(Long id) {
		// TODO Auto-generated method stub
		return mapper.selectByApplyId(id);
	}
}

