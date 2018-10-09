package com.moerlong.carloan.modular.paybackMgr.service.impl;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.moerlong.carloan.modular.paybackMgr.dao.RepaymentInfoDao;
import com.moerlong.carloan.modular.paybackMgr.dao.RepaymentPlanInfoDao;
import com.moerlong.carloan.modular.paybackMgr.service.*;
import com.moerlong.carloan.modular.paybackMgr.txservice.RepaymentTxService;
import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import com.moerlong.carloan.modular.payMgr.entity.PayInfo;
import com.moerlong.carloan.modular.payMgr.service.PayInfoService;
import com.moerlong.carloan.modular.paybackMgr.entity.*;
import com.moerlong.carloan.modular.paybackMgr.entity.enums.*;
import com.moerlong.carloan.modular.paybackMgr.entity.vo.OnceRepaymentInfoVO;
import com.moerlong.carloan.modular.paybackMgr.entity.vo.RepaymentCalculatorVO;
import com.moerlong.carloan.modular.paybackMgr.entity.vo.SinglePeriodRepaymentPlanVO;
import com.moerlong.carloan.util.DateUtil;
import com.moerlong.carloan.util.FinanceUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import javax.xml.transform.Result;

@Service
public class RepaymentInfoServiceImpl implements RepaymentInfoService{
	private static final Logger LOG = LoggerFactory.getLogger(RepaymentInfoServiceImpl.class);

	private static final String yearRateStr = "0.18";
	private static final String serviceFee = "100";
	private static final String inComingRateStr = "0.12";
	private static final String preServiceChargeRateStr = "0.03";
	private static final String preFee = "1500";
	private static final String onceRepaymentRate = "0.05";

	private static final int ONCE_EARLY_REPAYMENT_DATE = 1;	//允许提前还款天数

	@Autowired
	RepaymentInfoDao mapper;

	@Autowired
	RepaymentPlanInfoDao repaymentPlanInfoDao;
	
	@Autowired
	RepaymentInfoService repaymentInfoService;

	@Autowired
	RepaymentTxService repaymentTxService;

	@Autowired
	RepaymentApproveRecordService repaymentApproveRecordService;

	@Autowired
	OnceEarlyRepaymentRecordService onceEarlyRepaymentRecordService;

	@Autowired
	RepaymentPayInfoService repaymentPayInfoService;

	@Autowired
	RepaymentChangeRecordService repaymentChangeRecordService;

	@Autowired
	PayInfoService payInfoService;
	
	@Resource
    private ApplyInfoService applyInfoService;

	@Transactional
	public void saveOrUpdate(RepaymentInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional
	public void save(RepaymentInfo entity) {
		entity.setIsDeleted(0);
		entity.setCreateTime(new Date());
		mapper.save(entity);
	}
	
	@Transactional
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Transactional
	public void update(RepaymentInfo entity) {
		entity.setUpdateTime(new Date());
		mapper.update(entity);
	}
	
	public RepaymentInfo selectById(Long id) {
		return mapper.selectById(id);
	}

	@Override
	public RepaymentInfo selectByIdNumber(String idNumber) {
		return mapper.selectByIdNumber(idNumber);
	}

	@Override
	public RepaymentInfo selectByPayId(Long payId) {
		return mapper.selectByPayId(payId);
	}

	public PageInfo<RepaymentInfo> selectPage(int pageSize,int pageNum, String orderCondition ) {
		PageHelper.startPage(pageNum, pageSize);
		List<RepaymentInfo> pageList = mapper.selectPage( orderCondition);
		PageInfo<RepaymentInfo> pageInfo = new PageInfo<RepaymentInfo>(pageList);
		return pageInfo;
	}
	
	public Map<String,Object> selectByExampleWithPage(RepaymentInfo repaymentInfo, Integer pageSize, Integer offset) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		RepaymentInfoExample example=new RepaymentInfoExample();
		RepaymentInfoExample.Criteria cri=example.createCriteria();
		if(!StringUtils.isEmpty(repaymentInfo.getCustName())) {
			cri.andCustNameEqualTo(repaymentInfo.getCustName());
		}
		if(!StringUtils.isEmpty(repaymentInfo.getContractNo())) {
			cri.andContractNoEqualTo(repaymentInfo.getContractNo());
		}
		if(repaymentInfo.getCurStatus()!=null) {
			cri.andCurStatusEqualTo(repaymentInfo.getCurStatus());
		}
		if(repaymentInfo.getOverdueTimes()!=null) {
			cri.andOverdueTimesGreaterThan(0);
		}
		List<RepaymentInfo> rinfos=mapper.selectByExampleWithPage(example, Integer.valueOf(offset), Integer.valueOf(pageSize));
		map.put("total",mapper.countByExample(example));
		map.put("rows", rinfos);
		return map;

	}


	public static RepaymentCalculatorVO calculatorRepaymentSub(String amount, Integer period, String beginDate, Integer version, String yearRate, Integer isPerCharge){
		int days = 0;
		int beforeDays = 0;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		BigDecimal loanAmount = new BigDecimal(amount);		//借款金额

		BigDecimal monthAmount = new BigDecimal(0);		//月还款金额
		BigDecimal beginAmount = new BigDecimal(amount);	//期初余额
		BigDecimal endAmount = new BigDecimal(0);		//期末余额

		BigDecimal monthLease = new BigDecimal(0);		//月还款租赁合同
		BigDecimal monthService = new BigDecimal(0);	//月还款服务合同

		BigDecimal chargeAmountPer = new BigDecimal(0);	//每期手续费
		BigDecimal chargeTemp = new BigDecimal(0);	//总手续费
		BigDecimal chargeSub = new BigDecimal(0);	//手续费差值

		BigDecimal CapitalTotal = new BigDecimal(0);
		BigDecimal interestTotalAmount = new BigDecimal(0);		//利息总收益
		BigDecimal serviceTotalAmount = new BigDecimal(0);		//服务费总收益
		BigDecimal repaymentTotalAmount = new BigDecimal(0); 	//应还总金额
		BigDecimal chargeTotalAmount = new BigDecimal(0);		//总手续费

		Date distributeTime = null;

		RepaymentCalculatorVO vo = new RepaymentCalculatorVO();

		try {

			distributeTime = sdf.parse(beginDate);

			DateTime next = new DateTime(distributeTime);
			DateTime amountTime = new DateTime(distributeTime);

			vo.setYearRate(new BigDecimal(yearRateStr));
			vo.setPreServiceRate(new BigDecimal(preServiceChargeRateStr));
			vo.setIncomeRate(new BigDecimal(inComingRateStr));
			vo.setPreFee(new BigDecimal(preFee));
			vo.setOnceRepaymentRate(new BigDecimal(onceRepaymentRate));
			chargeTemp = new BigDecimal(preServiceChargeRateStr).multiply(loanAmount);
			if(isPerCharge == 1){
				chargeAmountPer = new BigDecimal(0);
			}else{
				chargeAmountPer = chargeTemp.divide(new BigDecimal(period), 10, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
				chargeSub = chargeTemp.subtract(chargeAmountPer.multiply(new BigDecimal(period)));
			}

			vo.setLoanAmount(loanAmount);
			vo.setLoanPeriod(period);
			vo.setDistributeTime(distributeTime);

			vo.setInterestBeginTime(DateUtil.str2Date(amountTime.toString(DateUtil.YMD), DateUtil.YMD));

			monthAmount = FinanceUtils.PMT(new BigDecimal(yearRate).divide(new BigDecimal(12), 10, BigDecimal.ROUND_HALF_UP), period, loanAmount);

			monthLease = FinanceUtils.PMT(new BigDecimal(inComingRateStr).divide(new BigDecimal(12), 10, BigDecimal.ROUND_HALF_UP), period, loanAmount);
			monthService = monthAmount.subtract(monthLease);

			monthLease = monthLease.setScale(2, BigDecimal.ROUND_HALF_UP);
			monthAmount = monthAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
			monthService = monthService.setScale(2, BigDecimal.ROUND_HALF_UP);
			vo.setMonthLease(monthLease);
			vo.setMonthService(monthService);

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<SinglePeriodRepaymentPlanVO> plans = new ArrayList<SinglePeriodRepaymentPlanVO>();
			// 计算每期计息天数，计息开始结束时间
			for (int i = 0; i < period; i++) {
				SinglePeriodRepaymentPlanVO plan = new SinglePeriodRepaymentPlanVO();
				plan.setPeriodBeginTime(DateUtil.str2Date(next.toString(DateUtil.YMD), DateUtil.YMD));
				next = amountTime.plusMonths(i + 1);

				days = DateUtil.daysBetween(amountTime.toDate(), next.toDate());

				plan.setInterestDays(days - beforeDays);
				beforeDays = days;
				plan.setPeriodNum(i + 1);
				DateTime endTime = new DateTime(DateUtil.str2Date(next.toString(DateUtil.YMD), DateUtil.YMD));
				plan.setPeriodEndTime(endTime.toDate());

				plan.setPayAmount(monthAmount);
				plan.setBeginAmount(beginAmount);


				//每期的月还利息 = 当期期初余额*年化利率/12
				BigDecimal interest = beginAmount.multiply(new BigDecimal(yearRate)).divide(new BigDecimal(12), 8, BigDecimal.ROUND_HALF_UP);
				interest = interest.setScale(2, BigDecimal.ROUND_HALF_UP);
				plan.setPredictInterest(interest);
				//每期的服务费 目前固定100元
				plan.setPredictServiceCharge(new BigDecimal(serviceFee));

				if(isPerCharge == 1){
					plan.setPredictCharge(chargeAmountPer);
				}else{
					if(i == 0){
						//第一期调整一下手续费
						plan.setPredictCharge(chargeAmountPer.add(chargeSub));
					}else{
						plan.setPredictCharge(chargeAmountPer);
					}
				}

				//每期的月还本金 = 当期月还款 - 当期月还利息
				BigDecimal capital = monthAmount.subtract(interest);
				plan.setPredictCapital(capital);
				//每期的期末余额 = 当期期初余额 - 当期月还本金
				endAmount = beginAmount.subtract(capital);
				plan.setEndAmount(endAmount);
				//每期的月还款合计 = 当期月还款本金 + 当期月还款利息 + 服务费 + 每期手续费
				BigDecimal total = capital.add(interest).add(new BigDecimal(serviceFee)).add(plan.getPredictCharge());
				plan.setPredictTotalAmount(total);

				beginAmount = endAmount;
				plans.add(plan);


				CapitalTotal = CapitalTotal.add(capital);
				interestTotalAmount = interestTotalAmount.add(interest);
				serviceTotalAmount = serviceTotalAmount.add(new BigDecimal(serviceFee));
				chargeTotalAmount = chargeTotalAmount.add(plan.getPredictCharge());
				repaymentTotalAmount = repaymentTotalAmount.add(total);
			}

			if(isPerCharge == 1){
				chargeTotalAmount = chargeTemp.setScale(2, BigDecimal.ROUND_HALF_UP);
				repaymentTotalAmount = repaymentTotalAmount.add(chargeTotalAmount);
			}

			DateTime endTime = new DateTime(DateUtil.str2Date(next.toString(DateUtil.YMD), DateUtil.YMD));
			vo.setInterestEndTime(endTime.toDate());
			vo.setPlans(plans);

			// 借款总天数
			int loanDays = 0;

			loanDays = DateUtil.daysBetween(amountTime.toDate(), next.toDate());

			vo.setLoanDaysNum(loanDays);

			//对于总本金不对的 进行调整
			if(CapitalTotal.compareTo(loanAmount) != 0){
				BigDecimal temp = CapitalTotal.subtract(loanAmount);
				SinglePeriodRepaymentPlanVO pVO = plans.get(0);

				pVO.setPredictCapital(pVO.getPredictCapital().subtract(temp));		//只对第一期的还款本金进行调整
				pVO.setEndAmount(pVO.getBeginAmount().subtract(pVO.getPredictCapital()));
				pVO.setPredictTotalAmount(pVO.getPredictInterest().add(pVO.getPredictCapital()).
						add(pVO.getPredictServiceCharge()).add(pVO.getPredictCharge()));
				CapitalTotal = pVO.getPredictCapital();
				temp = pVO.getEndAmount();
				repaymentTotalAmount = pVO.getPredictTotalAmount();
				/*LOG.info("{}期 begin {} end {} day {} 期初{} 月还款{} 月还本金{} 月还利息{} 月还服务费{} 手续费{} 期末余额{} 月还款总额{}",
						1, df.format(pVO.getPeriodBeginTime()), df.format(pVO.getPeriodEndTime()), pVO.getInterestDays(),
						pVO.getBeginAmount(), pVO.getPayAmount(), pVO.getPredictCapital(), pVO.getPredictInterest(), pVO.getPredictServiceCharge(),
						pVO.getPredictCharge(), pVO.getEndAmount(), pVO.getPredictTotalAmount());*/

				for(int i = 1; i < period; i++){
					SinglePeriodRepaymentPlanVO s = plans.get(i);
					s.setBeginAmount(temp);
					s.setEndAmount(s.getBeginAmount().subtract(s.getPredictCapital()));
					temp = s.getEndAmount();
					CapitalTotal = CapitalTotal.add(s.getPredictCapital());
					repaymentTotalAmount = repaymentTotalAmount.add(s.getPredictTotalAmount());
					/*LOG.info("{}期 begin {} end {} day {} 期初{} 月还款{} 月还本金{} 月还利息{} 月还服务费{} 手续费{} 期末余额{} 月还款总额{}",
							i+1, df.format(s.getPeriodBeginTime()), df.format(s.getPeriodEndTime()), s.getInterestDays(),
							s.getBeginAmount(), s.getPayAmount(), s.getPredictCapital(), s.getPredictInterest(), s.getPredictServiceCharge(),
							pVO.getPredictCharge(), s.getEndAmount(), s.getPredictTotalAmount());*/
				}
			}

			// 应还本金
			vo.setPredictCapital(CapitalTotal);

			// 放款金额
			vo.setDistributeAmount(loanAmount);

			// 服务费总收益
			vo.setPredictServiceCharge(serviceTotalAmount);

			// 利息总收益
			vo.setPredictInterest(interestTotalAmount);

			//总手续费
			vo.setPredictCharge(chargeTotalAmount);

			if(isPerCharge == 1){
				repaymentTotalAmount = repaymentTotalAmount.add(chargeTotalAmount);
			}
			// 应还总金额
			vo.setPredictAmount(repaymentTotalAmount);

			/*LOG.info("总应还本金 {},总服务费{} 总利息{} 总手续费{} 总应还金额{}", vo.getPredictCapital(), vo.getPredictServiceCharge(),
					vo.getPredictInterest(), vo.getPredictCharge(), vo.getPredictAmount());*/



		} catch (Exception e) {
			LOG.error("===>>>计算还款计划 发生错误 e={}", e);
			e.printStackTrace();
			return null;
		}

		return vo;
	}


	/**
	 *
	 * @param amount
	 * @param period
	 * @param beginDate
	 * @param version	0--表示之前业务的前4单，前4单已经事先收取手续费 计算算法是最后补齐
	 *                  1--表示之前业务的 40多单，这些单子手续费分散在各其手续费中， 计算算法是最后补齐
	 *                  2--表示系统放款流程上线后，由系统录入请款单信息 计算算法是第一期补齐  从2开始 调用本方法
	 *                  3--表示系统全流程上线后的数据
	 * @param isPerCharge
	 *
	 * @return
	 */
	public static RepaymentCalculatorVO calculatorRepayment(String amount, Integer period, String beginDate, Integer version, Integer isPerCharge){

		RepaymentCalculatorVO vo = calculatorRepaymentSub(amount, period, beginDate, version, yearRateStr, isPerCharge);
		RepaymentCalculatorVO temp = calculatorRepaymentSub(amount, period, beginDate, version, inComingRateStr, isPerCharge);

		BigDecimal monthServiceTotal = new BigDecimal(0);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		for(int i=0;i< period;i++){
			SinglePeriodRepaymentPlanVO s = vo.getPlans().get(i);
			SinglePeriodRepaymentPlanVO a = temp.getPlans().get(i);
			s.setCapitalLease(a.getPredictCapital());
			s.setInterestLease(a.getPredictInterest());
			s.setBeginAmountLease(a.getBeginAmount());
			s.setEndAmountLease(a.getEndAmount());
			s.setLeaseFee(s.getCapitalLease().add(s.getInterestLease()));
			s.setPlatformFee(s.getPredictCapital().add(s.getPredictInterest()).subtract(s.getLeaseFee()));
			monthServiceTotal = monthServiceTotal.add(s.getPlatformFee());

			/*LOG.info("{}期 begin {} end {} day {} 期初{} 月还款{} 月还本金{} 月还利息{} 月还服务费{} 月还手续费{} 期末余额{} 月还款总额{} 租赁期初{} 租赁期末{} 租赁本金{} 租赁利息{} 租赁合同{} 服务合同{}",
					i+1, df.format(s.getPeriodBeginTime()), df.format(s.getPeriodEndTime()), s.getInterestDays(),
					s.getBeginAmount(), s.getPayAmount(), s.getPredictCapital(), s.getPredictInterest(),
					s.getPredictServiceCharge(), s.getPredictCharge(),
					s.getEndAmount(), s.getPredictTotalAmount(),
					s.getBeginAmountLease(), s.getEndAmountLease(), s.getCapitalLease(), s.getInterestLease(), s.getLeaseFee(), s.getPlatformFee());
			*/
		}
		vo.setLeaseCapitalTotal(temp.getPredictCapital());
		vo.setLeaseInterestTotal(temp.getPredictInterest());
		vo.setMonthServiceTotal(monthServiceTotal);
		vo.setMonthLeaseTotal(vo.getLeaseCapitalTotal().add(vo.getLeaseInterestTotal()));

		/*LOG.info("总应还本金 {},总服务费{} 总利息{} 总手续费{} 总应还金额{} 租赁合同总金额{} 服务合同总金额{} 租赁总本金{} 租赁总利息{}", vo.getPredictCapital(), vo.getPredictServiceCharge(),
				vo.getPredictInterest(), vo.getPredictCharge(), vo.getPredictAmount(), vo.getMonthLeaseTotal(), vo.getMonthServiceTotal(),
				vo.getLeaseCapitalTotal(), vo.getLeaseInterestTotal());*/

		return vo;
	}

	/**
	 *
	 * @param amount
	 * @param period
	 * @param beginDate
	 * @param version	0--表示之前业务的前4单，前4单已经事先收取手续费 计算算法是最后补齐
	 *                  1--表示之前业务的 40多单，这些单子手续费分散在各其手续费中， 计算算法是最后补齐
	 *                  2--表示系统放款流程上线后，由系统录入请款单信息 计算算法是第一期补齐  从2开始 计算方法不在调用该方法 调用 calculatorRepayment
	 *                  3--表示系统全流程上线后的数据
	 * @return
	 */
	public static RepaymentCalculatorVO calculatorRepaymentBefore(String amount, Integer period, String beginDate, Integer version){


		int days = 0;
		int beforeDays = 0;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		BigDecimal loanAmount = new BigDecimal(amount);		//借款金额

		BigDecimal monthAmount = new BigDecimal(0);		//月还款金额
		BigDecimal beginAmount = new BigDecimal(amount);	//期初余额
		BigDecimal endAmount = new BigDecimal(0);		//期末余额

		BigDecimal beginAmountLease = new BigDecimal(amount);	//期初余额（租赁）
		BigDecimal endAmountLease = new BigDecimal(0);		//期末余额（租赁）

		BigDecimal monthLease = new BigDecimal(0);		//月还款租赁合同
		BigDecimal monthService = new BigDecimal(0);	//月还款服务合同

		BigDecimal chargeAmountPer = new BigDecimal(0);	//手续费

		BigDecimal CapitalTotal = new BigDecimal(0);
		BigDecimal interestTotalAmount = new BigDecimal(0);		//利息总收益
		BigDecimal serviceTotalAmount = new BigDecimal(0);		//服务费总收益
		BigDecimal repaymentTotalAmount = new BigDecimal(0); 	//应还总金额
		BigDecimal chargeTotalAmount = new BigDecimal(0);		//总手续费

		BigDecimal leaseCapitalTotal = new BigDecimal(0);		//租赁本金总计
		BigDecimal leaseInterestTotal = new BigDecimal(0);		//租赁利息总计
		BigDecimal monthLeaseTotal = new BigDecimal(0);			//租赁合同 总计
		BigDecimal monthServiceTotal = new BigDecimal(0);		//服务合同 总计

		Date distributeTime = null;

		RepaymentCalculatorVO vo = new RepaymentCalculatorVO();

		try {

			distributeTime = sdf.parse(beginDate);

			DateTime next = new DateTime(distributeTime);
			DateTime amountTime = new DateTime(distributeTime);

			vo.setYearRate(new BigDecimal(yearRateStr));
			vo.setPreServiceRate(new BigDecimal(preServiceChargeRateStr));
			vo.setIncomeRate(new BigDecimal(inComingRateStr));
			vo.setPreFee(new BigDecimal(preFee));
			vo.setOnceRepaymentRate(new BigDecimal(onceRepaymentRate));
			chargeAmountPer = new BigDecimal(preServiceChargeRateStr).multiply(loanAmount).
					divide(new BigDecimal(period), 10, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);

			vo.setLoanAmount(loanAmount);
			vo.setLoanPeriod(period);
			vo.setDistributeTime(distributeTime);

			vo.setInterestBeginTime(DateUtil.str2Date(amountTime.toString(DateUtil.YMD), DateUtil.YMD));

			monthAmount = FinanceUtils.PMT(new BigDecimal(yearRateStr).divide(new BigDecimal(12), 10, BigDecimal.ROUND_HALF_UP), period, loanAmount);

			monthLease = FinanceUtils.PMT(new BigDecimal(inComingRateStr).divide(new BigDecimal(12), 10, BigDecimal.ROUND_HALF_UP), period, loanAmount);

			monthService = monthAmount.subtract(monthLease);

			monthAmount = monthAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
			monthLease = monthLease.setScale(2, BigDecimal.ROUND_HALF_UP);
			monthService = monthService.setScale(2, BigDecimal.ROUND_HALF_UP);

			vo.setMonthLease(monthLease);
			vo.setMonthService(monthService);

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<SinglePeriodRepaymentPlanVO> plans = new ArrayList<SinglePeriodRepaymentPlanVO>();
			// 计算每期计息天数，计息开始结束时间
			for (int i = 0; i < period; i++) {
				SinglePeriodRepaymentPlanVO plan = new SinglePeriodRepaymentPlanVO();
				plan.setPeriodBeginTime(DateUtil.str2Date(next.toString(DateUtil.YMD), DateUtil.YMD));
				next = amountTime.plusMonths(i + 1);

				days = DateUtil.daysBetween(amountTime.toDate(), next.toDate());

				plan.setInterestDays(days - beforeDays);
				beforeDays = days;
				plan.setPeriodNum(i + 1);
				DateTime endTime = new DateTime(DateUtil.str2Date(next.toString(DateUtil.YMD), DateUtil.YMD));
				plan.setPeriodEndTime(endTime.toDate());

				plan.setPayAmount(monthAmount);
				plan.setBeginAmount(beginAmount);


				BigDecimal capital;
				BigDecimal interest;

				BigDecimal capitalLease;
				BigDecimal interestLease;

				if(i == period - 1){
					if(version == 0){
						//最后一期 如果版本是0 则最后一期的本金是期初本金，利息=月还款-本金
						capital = beginAmount;
						interest = monthAmount.subtract(capital);

						capitalLease = beginAmountLease;
						interestLease = monthLease.subtract(capitalLease);
					}else{
						//最后一期 如果版本是1 则最后一期的本金 是期初本金，利息跟之前一样计算出来的 （这样月还款跟之前的就不一样了）
						interest = beginAmount.multiply(new BigDecimal(yearRateStr)).divide(new BigDecimal(12), 8, BigDecimal.ROUND_HALF_UP);
						interest = interest.setScale(2, BigDecimal.ROUND_HALF_UP);
						capital = beginAmount;

						interestLease = beginAmountLease.multiply(new BigDecimal(inComingRateStr)).divide(new BigDecimal(12), 8, BigDecimal.ROUND_HALF_UP);
						interestLease = interestLease.setScale(2, BigDecimal.ROUND_HALF_UP);
						capitalLease = beginAmountLease;
					}

				}else{
					//每期的月还利息 = 当期期初余额*年化利率/12
					interest = beginAmount.multiply(new BigDecimal(yearRateStr)).divide(new BigDecimal(12), 8, BigDecimal.ROUND_HALF_UP);
					interest = interest.setScale(2, BigDecimal.ROUND_HALF_UP);
					capital = monthAmount.subtract(interest);

					interestLease = beginAmountLease.multiply(new BigDecimal(inComingRateStr)).divide(new BigDecimal(12), 8, BigDecimal.ROUND_HALF_UP);
					interestLease = interestLease.setScale(2, BigDecimal.ROUND_HALF_UP);
					capitalLease = monthLease.subtract(interestLease);
				}

				if(version == 0){
					plan.setPredictCharge(new BigDecimal(0));
				}else{
					plan.setPredictCharge(chargeAmountPer);
				}

				plan.setPredictInterest(interest);

				plan.setInterestLease(interestLease);
				plan.setCapitalLease(capitalLease);
				plan.setBeginAmountLease(beginAmountLease);

				//每期的服务费 目前固定100元
				plan.setPredictServiceCharge(new BigDecimal(serviceFee));
				//每期的月还本金 = 当期月还款 - 当期月还利息
				plan.setPredictCapital(capital);
				//每期的期末余额 = 当期期初余额 - 当期月还本金
				endAmount = beginAmount.subtract(capital);
				plan.setEndAmount(endAmount);

				endAmountLease = beginAmountLease.subtract(capitalLease);
				plan.setEndAmountLease(endAmountLease);

				//每期的月还款合计 = 当期月还款本金 + 当期月还款利息 + 服务费 + 每月手续费
				BigDecimal total = capital.add(interest).add(new BigDecimal(serviceFee)).add(plan.getPredictCharge());
				plan.setPredictTotalAmount(total);

				beginAmount = endAmount;

				beginAmountLease = endAmountLease;
				plan.setLeaseFee(capitalLease.add(interestLease));
				plan.setPlatformFee(capital.add(interest).subtract(capitalLease).subtract(interestLease));

				plans.add(plan);

				/*LOG.info("{}期 begin {} end {} day {} 期初{} 月还款{} 月还本金{} 月还利息{} 月还服务费{} 月还手续费{} 期末余额{} 月还款总额{} 租赁期初{} 租赁期末{} 租赁本金{} 租赁利息{} 租赁合同{} 服务合同{}",
						i+1, df.format(plan.getPeriodBeginTime()), df.format(plan.getPeriodEndTime()), plan.getInterestDays(),
						plan.getBeginAmount(), plan.getPayAmount(), plan.getPredictCapital(), plan.getPredictInterest(),
						plan.getPredictServiceCharge(), plan.getPredictCharge(),
						plan.getEndAmount(), plan.getPredictTotalAmount(),
						plan.getBeginAmountLease(), plan.getEndAmountLease(), plan.getCapitalLease(), plan.getInterestLease(), plan.getLeaseFee(), plan.getPlatformFee());
				*/
				CapitalTotal = CapitalTotal.add(capital);
				interestTotalAmount = interestTotalAmount.add(interest);
				serviceTotalAmount = serviceTotalAmount.add(new BigDecimal(serviceFee));
				chargeTotalAmount = chargeTotalAmount.add(plan.getPredictCharge());
				repaymentTotalAmount = repaymentTotalAmount.add(total);

				leaseCapitalTotal = leaseCapitalTotal.add(capitalLease);
				leaseInterestTotal = leaseInterestTotal.add(interestLease);
				monthServiceTotal = monthServiceTotal.add(plan.getPlatformFee());
			}

			DateTime endTime = new DateTime(DateUtil.str2Date(next.toString(DateUtil.YMD), DateUtil.YMD));
			vo.setInterestEndTime(endTime.toDate());
			vo.setPlans(plans);

			vo.setMonthLeaseTotal(leaseCapitalTotal.add(leaseInterestTotal));
			vo.setMonthServiceTotal(monthServiceTotal);
			vo.setLeaseCapitalTotal(leaseCapitalTotal);
			vo.setLeaseInterestTotal(leaseInterestTotal);

			// 借款总天数
			int loanDays = 0;

			loanDays = DateUtil.daysBetween(amountTime.toDate(), next.toDate());

			vo.setLoanDaysNum(loanDays);

			// 应还本金
			vo.setPredictCapital(CapitalTotal);

			// 放款金额
			vo.setDistributeAmount(loanAmount);

			// 服务费总收益
			vo.setPredictServiceCharge(serviceTotalAmount);

			//总手续费
			if(version == 0){
				vo.setPredictCharge(new BigDecimal(preServiceChargeRateStr).multiply(loanAmount).setScale(2, BigDecimal.ROUND_HALF_UP));
			}else{
				vo.setPredictCharge(chargeTotalAmount);
			}


			// 利息总收益
			vo.setPredictInterest(interestTotalAmount);

			// 应还总金额
			if(version == 0) {
				vo.setPredictAmount(repaymentTotalAmount.add(new BigDecimal(preServiceChargeRateStr).multiply(loanAmount).setScale(2, BigDecimal.ROUND_HALF_UP)));
			}else{
				vo.setPredictAmount(repaymentTotalAmount);
			}

			/*LOG.info("总应还本金 {},总服务费{} 总利息{} 总手续费{} 总应还金额{} 租赁合同总金额{} 服务合同总金额{} 租赁总本金{} 租赁总利息{}", vo.getPredictCapital(), vo.getPredictServiceCharge(),
					vo.getPredictInterest(), vo.getPredictCharge(), vo.getPredictAmount(), vo.getMonthLeaseTotal(), vo.getMonthServiceTotal(),
					vo.getLeaseCapitalTotal(), vo.getLeaseInterestTotal());*/



		} catch (Exception e) {
			LOG.error("===>>>计算还款计划 发生错误 e={}", e);
			e.printStackTrace();
			return null;
		}

		return vo;
	}


	private void writeCVSFile(String name, Integer period, RepaymentCalculatorVO vo, Integer version) throws IOException {
		String path = "/Users/admin/work/temp/cvs/";
		String fileName = path + name + ".csv";
		File file = new File(fileName);

		FileOutputStream fos  = null;
		PrintWriter pw = null;
		try {
			fos = new FileOutputStream(file);
			pw = new PrintWriter(fos);

			StringBuffer buffer = new StringBuffer();
			for(int i = 0; i < period; i++){
				SinglePeriodRepaymentPlanVO plan = vo.getPlans().get(i);
				if(version == 0) {
					buffer.append(i + 1).append(",").
							append(plan.getBeginAmount()).append(",").
							append(plan.getPayAmount()).append(",").
							append(plan.getPredictCapital()).append(",").
							append(plan.getPredictInterest()).append(",").
							append(plan.getPredictServiceCharge()).append(",").
							append(plan.getEndAmount()).append(",").
							append(plan.getPredictTotalAmount()).append(System.getProperty("line.separator"));
				}else{
					buffer.append(i + 1).append(",").
							append(plan.getBeginAmount()).append(",").
							append(plan.getPayAmount()).append(",").
							append(plan.getPredictCapital()).append(",").
							append(plan.getPredictInterest()).append(",").
							append(plan.getPredictInterest().add(plan.getPredictCharge())).append(",").
							append(plan.getPredictServiceCharge()).append(",").
							append(plan.getEndAmount()).append(",").
							append(plan.getPredictTotalAmount()).append(System.getProperty("line.separator"));
				}
			}

			if(version == 0) {
				buffer.append("0,0,0,").append(vo.getPredictCapital()).append(",").
						append(vo.getPredictInterest()).append(",").
						append(vo.getPredictServiceCharge()).append(",").append("0,").
						append(vo.getPredictAmount()).append(System.getProperty("line.separator"));
			}else{
				buffer.append("0,0,0,").append(vo.getPredictCapital()).append(",").
						append(vo.getPredictInterest()).append(",").
						append(vo.getPredictInterest().add(vo.getPredictCharge())).append(",").
						append(vo.getPredictServiceCharge()).append(",").append("0,").
						append(vo.getPredictAmount()).append(System.getProperty("line.separator"));
			}



			pw.write(buffer.toString().toCharArray());
			pw.flush();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			//不要忘记关闭
			if (pw != null) {
				pw.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
	}

	@Override
	public void export(Long payId, String contractNo, Integer isPerCharge, String name, String idNo, String mobile, String bankName, String bankCardNo,
					   String amount, String receptionAmount, Integer period, String beginDate, String deadlineDate, Integer version) {


		RepaymentCalculatorVO vo;
		
		PayInfo payInfo=payInfoService.selectById(payId);
		
		if(version == 0 || version == 1){
			//这里是导入的 调用的算法是 最后一期进行调整的
			vo = calculatorRepaymentBefore(amount, period, beginDate, version);
		}else{
			//这里是生成的 调用算法是 第一期进行调整
			vo = calculatorRepayment(amount, period, beginDate, version, isPerCharge);
		}

		if(vo == null){
			LOG.error("===>[export] name={} idNo={} 失败", name, idNo);
			return ;
		}

		//这里是用来测试与财务生成的excel文件是否相同的
		try {
			writeCVSFile(name, period, vo, version);
		} catch (IOException e) {
			e.printStackTrace();
		}


		Date date = new Date();

		BigDecimal interestTotalAmount = new BigDecimal(0);		//利息总收益
		BigDecimal serviceTotalAmount = new BigDecimal(0);		//服务费总收益
		BigDecimal repaymentTotalAmount = new BigDecimal(0); 	//总金额
		BigDecimal capitalTotalAmount = new BigDecimal(0); 		//总本金
		BigDecimal chargeTotalAmount = new BigDecimal(0);		//总手续费

		RepaymentInfo info = new RepaymentInfo();
		info.setApplyId(payInfo.getApplyId());
		info.setDeptId(payInfo.getDeptId());
		info.setCustId(payInfo.getCustId());
		info.setPayId(payId);
		info.setContractNo(contractNo);
		info.setCustName(name);
		info.setCustMobile(mobile);
		info.setCustIdNo(idNo);
		info.setBankName(bankName);
		info.setBankCardNo(bankCardNo);
		info.setLoanAmount(vo.getLoanAmount());
		info.setLoanPeriod(vo.getLoanPeriod());
		info.setYearRate(vo.getYearRate());
		info.setPreServiceRate(vo.getPreServiceRate());
		info.setPreFee(vo.getPreFee());
		info.setOtherFee(new BigDecimal(0));
		info.setOnceRepaymentRate(vo.getOnceRepaymentRate());
		info.setBreachAmount(new BigDecimal(0));

		info.setMonthLeaseTotal(vo.getMonthLeaseTotal());
		info.setMonthServiceTotal(vo.getMonthServiceTotal());
		info.setLeaseCapitalTotal(vo.getLeaseCapitalTotal());
		info.setLeaseInterestTotal(vo.getLeaseInterestTotal());

		info.setReceptionAmount(new BigDecimal(receptionAmount));
		info.setInterestBeginTime(vo.getInterestBeginTime());
		info.setInterestEndTime(vo.getInterestEndTime());
		info.setLoanDaysNum(vo.getLoanDaysNum());
		info.setPredictAmount(vo.getPredictAmount());
		info.setPredictCapital(vo.getPredictCapital());
		info.setPredictInterest(vo.getPredictInterest());
		info.setPredictServiceCharge(vo.getPredictServiceCharge());
		info.setPredictCharge(vo.getPredictCharge());
		info.setPredictPenalty(new BigDecimal(0));
		info.setActualAmount(new BigDecimal(0));
		info.setActualCapital(new BigDecimal(0));
		info.setActualInterest(new BigDecimal(0));
		info.setActualServiceCharge(new BigDecimal(0));
		if(version == 0){
			//如果版本为0 表示事先已经一次性扣除了所有手续费
			info.setActualCharge(vo.getPredictCharge());
			repaymentTotalAmount = repaymentTotalAmount.add(vo.getPredictCharge());
		}else{
			info.setActualCharge(new BigDecimal(0));
		}
		info.setActualPenalty(new BigDecimal(0));
		info.setCurStatus(RepaymentApproveStatus.NO_BEGIN.getValue());
		info.setCurStatusDesc(RepaymentApproveStatus.NO_BEGIN.getDesc());
		info.setOverdueTimes(0);
		info.setCurrentPeriodNum(0);
		info.setIsOnceEarlyRepayment(0);
		info.setVersion(version);
		info.setCreateTime(date);
		info.setUpdateTime(date);
		info.setIsDeleted(0);

		save(info);

		//根据时间节点来计算已经完成的还款计划
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date deadline;
		try {
			deadline = sdf.parse(deadlineDate);
		} catch (ParseException e) {
			LOG.error("===>[export] parse deadlineDate error, deadlineDate={}", deadlineDate);
			e.printStackTrace();
			return ;
		}

		int curPeriod = 0;

		List<SinglePeriodRepaymentPlanVO> plans = vo.getPlans();
		for (int i = 0; i < plans.size(); i++) {
			SinglePeriodRepaymentPlanVO single = plans.get(i);

			RepaymentPlanInfo rpi = new RepaymentPlanInfo();
			rpi.setDeptId(info.getDeptId());
			rpi.setRepaymentId(info.getId());
			rpi.setPeriodNum(single.getPeriodNum());
			rpi.setApplyId(info.getApplyId());rpi.setCustId(info.getCustId());
			rpi.setPeriodBeginTime(single.getPeriodBeginTime());
			rpi.setPeriodEndTime(single.getPeriodEndTime());
			rpi.setInterestDays(single.getInterestDays());
			rpi.setBeginAmount(single.getBeginAmount());
			rpi.setEndAmount(single.getEndAmount());
			rpi.setPayAmount(single.getPayAmount());

			rpi.setBeginAmountLease(single.getBeginAmountLease());
			rpi.setEndAmountLease(single.getEndAmountLease());
			rpi.setCapitalLease(single.getCapitalLease());
			rpi.setInterestLease(single.getInterestLease());
			rpi.setLeaseTotal(single.getLeaseFee());
			rpi.setServiceTotal(single.getPlatformFee());

			rpi.setPredictAmount(single.getPredictTotalAmount());
			rpi.setPredictCapital(single.getPredictCapital());
			rpi.setPredictInterest(single.getPredictInterest());
			rpi.setPredictServiceCharge(single.getPredictServiceCharge());
			rpi.setPredictCharge(single.getPredictCharge());
			rpi.setPredictPenalty(new BigDecimal(0));

			if(rpi.getPeriodEndTime().before(deadline)){
				curPeriod = rpi.getPeriodNum();
				rpi.setPeriodStatus(RepaymentStatus.REPAYMENT_DONE.getValue());
				rpi.setActualAmount(single.getPredictTotalAmount());
				rpi.setActualCapital(single.getPredictCapital());
				rpi.setActualInterest(single.getPredictInterest());
				rpi.setActualServiceCharge(single.getPredictServiceCharge());
				rpi.setActualCharge(single.getPredictCharge());
				rpi.setActualPenalty(new BigDecimal(0));

				repaymentTotalAmount = repaymentTotalAmount.add(single.getPredictTotalAmount());
				interestTotalAmount = interestTotalAmount.add(single.getPredictInterest());
				serviceTotalAmount = serviceTotalAmount.add(single.getPredictServiceCharge());
				capitalTotalAmount = capitalTotalAmount.add(single.getPredictCapital());
				chargeTotalAmount = chargeTotalAmount.add(single.getPredictCharge());

			}else{
				rpi.setPeriodStatus(RepaymentStatus.NO_BEGIN.getValue());
				rpi.setActualAmount(new BigDecimal(0));
				rpi.setActualCapital(new BigDecimal(0));
				rpi.setActualInterest(new BigDecimal(0));
				rpi.setActualServiceCharge(new BigDecimal(0));
				rpi.setActualCharge(new BigDecimal(0));
				rpi.setActualPenalty(new BigDecimal(0));
			}

			rpi.setIsOverdue(0);
			rpi.setIsLock(0);
			rpi.setOverdueDays(0);
			rpi.setOverduePenalty(new BigDecimal(0));
			rpi.setCreateTime(date);
			rpi.setUpdateTime(date);
			rpi.setIsDeleted(0);
			repaymentPlanInfoDao.save(rpi);
		}

		RepaymentInfo temp = new RepaymentInfo();
		temp.setId(info.getId());
		if(curPeriod > 0) {
			temp.setCurrentPeriodNum(curPeriod);
		}
		temp.setActualAmount(repaymentTotalAmount.setScale(2,BigDecimal.ROUND_HALF_UP));
		temp.setActualCapital(capitalTotalAmount.setScale(2,BigDecimal.ROUND_HALF_UP));
		temp.setActualInterest(interestTotalAmount.setScale(2,BigDecimal.ROUND_HALF_UP));
		temp.setActualServiceCharge(serviceTotalAmount.setScale(2,BigDecimal.ROUND_HALF_UP));
		if(version != 0){
			temp.setActualCharge(chargeTotalAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
		}
		if(repaymentTotalAmount.compareTo(new BigDecimal(0)) > 0){
			temp.setCurStatus(RepaymentApproveStatus.REPAYMENT_PART.getValue());
			temp.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_PART.getDesc());
		}
		temp.setUpdateTime(date);

		update(temp);

	}

	@Override
	public RepaymentInfo selectInfoByPrimaryKeyForUpdate(Long id) {

		return mapper.selectInfoByPrimaryKeyForUpdate(id);
	}

	@Override
	public ResultVO<Object> manualDeductMoney(Long repaymentId, Integer period, Integer payType) {

		if(repaymentId == null || period == null || payType == null){
			LOG.error("param null repaymentId={} period={} payType={}", repaymentId, period, payType);
			return ResultVO.build(ErrorCode.PARAM_EMPTY);
		}

		if(payType != 1 && payType != 2){
			LOG.error("param payType error payType={}", payType);
			return ResultVO.build(ErrorCode.PARAM_ERROR);
		}

		ResultVO<Object> vo = repaymentTxService.manualDeductMoney(repaymentId, period, payType);
		if(!vo.isSuccess()){
			return vo;
		}

		vo = repaymentTxService.allotPayUpdatePlanInfo(Long.parseLong(vo.getData().toString()), false);
		if(!vo.isSuccess()){
			return vo;
		}
		
		RepaymentInfo repaymentInfo = repaymentInfoService.selectInfoByPrimaryKeyForUpdate(repaymentId);
		//如果是最后一期
		if(period==repaymentInfo.getLoanPeriod()) {
			System.out.println("--------最后一期还款---------");
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

		return ResultVO.build(ErrorCode.SUCCESS);
	}

	@Override
	public ResultVO<RepaymentPlanInfo> getSpecifyPeriodPlanMoney(Long repaymentId, Integer period) {
		if(repaymentId == null || period == null){
			LOG.error("param null repaymentId={} period={}", repaymentId, period);
			return ResultVO.build(ErrorCode.PARAM_EMPTY);
		}

		RepaymentPlanInfo plan = repaymentPlanInfoDao.selectByPeriod(repaymentId, period);
		if(plan == null){
			LOG.error("===>[getSpecifyPeriodPlanMoney] not find repaylment plan repaymentId={} period={}", repaymentId, period);
			return ResultVO.build(ErrorCode.REPAYMENT_PLAN_INFO_NOT_EXIST);
		}

		return ResultVO.build(ErrorCode.SUCCESS, plan);
	}

	/**
	 * 获取一次性提前还款信息
	 * @param repaymentId
	 * @return
	 */
	@Override
	public ResultVO<OnceRepaymentInfoVO> getOnceRepaymentInfo(Long repaymentId, String appointDate) {

		boolean isBreach = false;
		RepaymentPlanInfo planInfo;
		RepaymentPlanInfo nextPlanInfo;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if(repaymentId == null || StringUtils.isEmpty(appointDate)){
			LOG.error("param null repaymentId={} appointDate={}", repaymentId, appointDate);
			return ResultVO.build(ErrorCode.PARAM_EMPTY);
		}

		try {
			RepaymentInfo repaymentInfo = this.selectById(repaymentId);
			if (repaymentInfo == null) {
				LOG.error("not find repayment info by repaymentId={}", repaymentId);
				return ResultVO.build(ErrorCode.REPAYMENT_INFO_NOT_EXIST);
			}

			if (repaymentInfo.getIsOnceEarlyRepayment() == 1) {
				LOG.error("repayment info by repaymentId={} already early repayment", repaymentId);
				return ResultVO.build(ErrorCode.REPAYMENT_INFO_NOT_EXIST);
			}

			if (repaymentInfo.getCurStatus() == RepaymentApproveStatus.REPAYMENT_DONE.getValue()) {
				LOG.error("repayment info by repaymentId={} already repayment finish", repaymentId);
				return ResultVO.build(ErrorCode.REPAYMENT_INFO_NOT_EXIST);
			}

			List<RepaymentPlanInfo> planList = repaymentPlanInfoDao.listByRepaymentId(repaymentInfo.getId());
			if (planList == null || planList.size() != repaymentInfo.getLoanPeriod()) {
				LOG.error("not find repayment plan info by repaymentId={} or planList.size={}", repaymentInfo.getId(), planList.size());
				return ResultVO.build(ErrorCode.REPAYMENT_INFO_NOT_EXIST);
			}

			OnceRepaymentInfoVO vo = new OnceRepaymentInfoVO();
			vo.setOnceRepaymentRate(repaymentInfo.getOnceRepaymentRate());

			//判断是否有违约金
			Date appoint = sdf.parse(appointDate);

			planInfo = planList.get(repaymentInfo.getCurrentPeriodNum());
			nextPlanInfo = planList.get(repaymentInfo.getCurrentPeriodNum()+1);

			if (repaymentInfo.getCurrentPeriodNum() < 2) {
				//收取提前还款违约金
				isBreach = true;
			} else if (repaymentInfo.getCurrentPeriodNum() == 2) {

				if(appoint.before(planInfo.getPeriodEndTime())){
					isBreach = true;
				}
			}

			//约定提前还款时间必须是当时的3天以后
			long time1 = appoint.getTime();
			long time2 = sdf.parse(sdf.format(new Date())).getTime();
			if((time1-time2)/1000 < ONCE_EARLY_REPAYMENT_DATE*24*3600){
				LOG.error("appointDate not right, appointDate={}", appointDate);
				return ResultVO.build(ErrorCode.REPAYMENT_ONCE_EARLY_ERROR_APPOINT_DATE);
			}

			//如果约定提前还款日 大于 客户下一期正常还款日 则不允许跨期
			if(appoint.after(planInfo.getPeriodEndTime())){
				LOG.error("do not support across period... appointDate={}, plan EndTime={}", appointDate, planInfo.getPeriodEndTime());
				return ResultVO.build(ErrorCode.REPAYMENT_ONCE_EARLY_NOT_SUPPORT_ACROSS_PEROID);
			}

			if (isBreach == true) {
				//计算违约金
				vo.setOnceRepaymentBreach(repaymentInfo.getOnceRepaymentRate().multiply(nextPlanInfo.getBeginAmount()).setScale(2, BigDecimal.ROUND_HALF_UP));
			} else {
				vo.setOnceRepaymentBreach(new BigDecimal(0));
			}

			//计算剩余本金
			vo.setOnceRepaymentCapital(nextPlanInfo.getBeginAmount());

			//计算剩余手续费
			if (repaymentInfo.getVersion() == 0) {
				vo.setOnceRepaymentCharge(new BigDecimal(0));
			} else {
				vo.setOnceRepaymentCharge(repaymentInfo.getPredictCharge().subtract(repaymentInfo.getActualCharge()).subtract(planInfo.getPredictCharge()));
			}

			//加入当期正常还款费用
			vo.setPlan(planInfo);

			//合计
			vo.setOnceRepaymentTotal(vo.getOnceRepaymentBreach().
						add(vo.getOnceRepaymentCapital()).add(vo.getOnceRepaymentCharge()).add(planInfo.getPredictAmount()));


			return ResultVO.build(ErrorCode.SUCCESS, vo);
		}catch (Exception e){
			LOG.error("getOnceRepaymentInfo--exception e={}", e);
			return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
		}
	}

	/**
	 * 根据条件查询还款总表
	 * @return
	 */
	public List<RepaymentInfo> listByCondition(String custName, String custMobile, String beginTime, String endTime, String curStatus){

		return mapper.listByCondition(custName,custMobile,beginTime,endTime,curStatus);
	}


	/**
	 * 提前还款 -- 业务申请
	 * @param repaymentId
	 * @param appointDate
	 * @param userId
	 * @param userName
	 * @return
	 */
	@Override
	@Transactional
	public ResultVO<Object> onceEarlyRepaymentApply(Long repaymentId, String appointDate, Long userId, String userName){


		try {
			RepaymentInfo repaymentInfo = mapper.selectById(repaymentId);

			//判断还款状态
			if (repaymentInfo.getCurStatus() != RepaymentApproveStatus.NO_BEGIN.getValue() &&
					repaymentInfo.getCurStatus() != RepaymentApproveStatus.REPAYMENTING.getValue() &&
					repaymentInfo.getCurStatus() != RepaymentApproveStatus.REPAYMENT_PART.getValue()) {
				LOG.error("业务人员申请提前还款不正确 请求状态不正确 repaymentId={},status={}", repaymentId, repaymentInfo.getCurStatus());
				return ResultVO.build(ErrorCode.REPAYMENT_ONCE_EARLY_PROCESS_ERROR);
			}


			//获取还款信息
			ResultVO<OnceRepaymentInfoVO> vo = getOnceRepaymentInfo(repaymentId, appointDate);
			if (vo.getStatus() != ErrorCode.SUCCESS.getErrCode()) {
				LOG.error("业务申请提前还款有误 repaymentId={} appointDate={}", repaymentId, appointDate);
				return ResultVO.build(vo.getStatus(), vo.getMsg());
			}

			//1 更新还款总表
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			RepaymentInfo temp = new RepaymentInfo();
			temp.setId(repaymentId);
			temp.setCurStatus(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_CREATE.getValue());
			temp.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_CREATE.getDesc());
			temp.setUpdateTime(now);
			mapper.update(temp);

			//2 保存流程记录
			RepaymentApporveRecord record = new RepaymentApporveRecord();
			record.setRepaymentId(repaymentId);
			record.setOperatorId(userId);
			record.setOperatorName(userName);
			record.setOperatorResult(1);
			record.setOperatorTip("提交申请");
			record.setOperatorTime(new Date());
			record.setPreStatus(repaymentInfo.getCurStatus());
			record.setPreStatusDesc(repaymentInfo.getCurStatusDesc());
			record.setAfterStatus(temp.getCurStatus());
			record.setAfterStatusDesc(temp.getCurStatusDesc());
			repaymentApproveRecordService.save(record);

			//3 添加提前还款记录

			OnceEarlyRepaymentRecord onceRecord = onceEarlyRepaymentRecordService.selectByRepaymentId(repaymentId);
			if(null == onceRecord){
				onceRecord = new OnceEarlyRepaymentRecord();
			}
			onceRecord.setRepaymentId(repaymentId);
			onceRecord.setOnceRepaymentBreach(vo.getData().getOnceRepaymentBreach());
			onceRecord.setOnceRepaymentCapital(vo.getData().getOnceRepaymentCapital());
			onceRecord.setOnceRepaymentCharge(vo.getData().getOnceRepaymentCharge());
			onceRecord.setOnceRepaymentTotal(vo.getData().getOnceRepaymentTotal());
			onceRecord.setAppointDate(sdf.parse(appointDate));
			onceRecord.setCurPeriodNum(vo.getData().getPlan().getPeriodNum());
			onceRecord.setCurPeriodAmount(vo.getData().getPlan().getPredictAmount());
			onceRecord.setCurStatus(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_CREATE.getValue());
			onceRecord.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_CREATE.getDesc());
			onceRecord.setIsDeleted(0);
			onceRecord.setCreateTime(now);
			onceRecord.setUpdateTime(now);
			if(null==onceRecord.getId()){
				onceEarlyRepaymentRecordService.save(onceRecord);
			}else{
				onceEarlyRepaymentRecordService.update(onceRecord);
			}


			//4 修改当期状态为锁定
			RepaymentPlanInfo plan = new RepaymentPlanInfo();
			plan.setId(vo.getData().getPlan().getId());
			plan.setIsLock(1);
			plan.setUpdateTime(now);
			repaymentPlanInfoDao.update(plan);

			//TODO 4 短信通知



		}catch (Exception e){
			LOG.error("onceEarlyRepaymentApply  exception = {}", e);
			return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
		}

		return ResultVO.build(ErrorCode.SUCCESS);
	}

	/**
	 * 提前还款-- 业务经理审批
	 * @param onceEarlyId
	 * @param operatorResult
	 * @param operatorTip
	 * @param operatorId
	 * @param operatorName
	 * @return
	 */
	@Override
	@Transactional
	public ResultVO<Object> onceEarlyRepaymentBussApprove(Long onceEarlyId, Integer operatorResult, String operatorTip, Long operatorId, String operatorName) {

		try {
			Date now = new Date();
			OnceEarlyRepaymentRecord onceRecord = onceEarlyRepaymentRecordService.selectById(onceEarlyId);


			RepaymentInfo repaymentInfo = mapper.selectById(onceRecord.getRepaymentId());


			//1 判断状态是否正确
			if(repaymentInfo.getCurStatus() != RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_CREATE.getValue()){
				LOG.error("业务经理审批提前还款状态不正确 onceEarlyId={} repaymentId={},status={}", onceEarlyId, onceRecord.getRepaymentId(), repaymentInfo.getCurStatus());
				return ResultVO.build(ErrorCode.REPAYMENT_ONCE_EARLY_PROCESS_ERROR);
			}

			RepaymentInfo temp = new RepaymentInfo();
			RepaymentPlanInfo planInfo =  repaymentPlanInfoDao.selectByPeriod(onceRecord.getRepaymentId(), onceRecord.getCurPeriodNum());

			OnceEarlyRepaymentRecord onceEarlyRepaymentRecord = new OnceEarlyRepaymentRecord();
			onceEarlyRepaymentRecord.setId(onceEarlyId);

			temp.setId(onceRecord.getRepaymentId());
			if(operatorResult == 1){
				temp.setCurStatus(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_BUSS_APPROVE.getValue());
				temp.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_BUSS_APPROVE.getDesc());
				onceEarlyRepaymentRecord.setCurStatus(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_BUSS_APPROVE.getValue());
				onceEarlyRepaymentRecord.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_BUSS_APPROVE.getDesc());
			}else{
				temp.setCurStatus(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_BUSS_APPROVE_FAIL.getValue());
				temp.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_BUSS_APPROVE_FAIL.getDesc());
				onceEarlyRepaymentRecord.setCurStatus(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_BUSS_APPROVE_FAIL.getValue());
				onceEarlyRepaymentRecord.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_BUSS_APPROVE_FAIL.getDesc());

				if(planInfo.getIsLock() == 1){
					RepaymentPlanInfo tempPlan = new RepaymentPlanInfo();
					tempPlan.setId(planInfo.getId());

					tempPlan.setIsLock(0);
					tempPlan.setUpdateTime(now);
					repaymentPlanInfoDao.update(tempPlan);
				}
			}
			mapper.update(temp);
			onceEarlyRepaymentRecordService.update(onceEarlyRepaymentRecord);


			//3 保存流程记录
			RepaymentApporveRecord record = new RepaymentApporveRecord();
			record.setRepaymentId(onceRecord.getRepaymentId());
			record.setOperatorId(operatorId);
			record.setOperatorName(operatorName);
			record.setOperatorResult(operatorResult);
			record.setOperatorTip(operatorTip);
			record.setOperatorTime(new Date());
			record.setPreStatus(repaymentInfo.getCurStatus());
			record.setPreStatusDesc(repaymentInfo.getCurStatusDesc());
			record.setAfterStatus(repaymentInfo.getCurStatus());
			record.setAfterStatusDesc(temp.getCurStatusDesc());
			repaymentApproveRecordService.save(record);

			//TODO 4 短信通知



		}catch(Exception e){
			LOG.error("onceEarlyRepaymentBussApprove  exception = {}", e);
			return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
		}

		return ResultVO.build(ErrorCode.SUCCESS);
	}

	/**
	 * 财务审批
	 * @param onceEarlyId
	 * @param operatorResult
	 * @param operatorTip
	 * @param operatorId
	 * @param operatorName
	 * @return
	 */
	@Override
	@Transactional
	public ResultVO<Object> onceEarlyRepaymentFinanceApprove(Long onceEarlyId, Integer operatorResult, String operatorTip, Long operatorId, String operatorName) {
		try {
			Date now = new Date();
			OnceEarlyRepaymentRecord onceRecord = onceEarlyRepaymentRecordService.selectById(onceEarlyId);


			RepaymentInfo repaymentInfo = mapper.selectById(onceRecord.getRepaymentId());


			//1 判断状态是否正确
			if(repaymentInfo.getCurStatus() != RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_BUSS_APPROVE.getValue()){
				LOG.error("财务审批提前还款状态不正确 onceEarlyId={} repaymentId={},status={}", onceEarlyId, onceRecord.getRepaymentId(), repaymentInfo.getCurStatus());
				return ResultVO.build(ErrorCode.REPAYMENT_ONCE_EARLY_PROCESS_ERROR);
			}

			RepaymentInfo temp = new RepaymentInfo();
			RepaymentPlanInfo planInfo =  repaymentPlanInfoDao.selectByPeriod(onceRecord.getRepaymentId(), onceRecord.getCurPeriodNum());

			OnceEarlyRepaymentRecord onceEarlyRepaymentRecord = new OnceEarlyRepaymentRecord();
			onceEarlyRepaymentRecord.setId(onceEarlyId);

			temp.setId(onceRecord.getRepaymentId());
			if(operatorResult == 1){
				temp.setCurStatus(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FINANCE.getValue());
				temp.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FINANCE.getDesc());
				onceEarlyRepaymentRecord.setCurStatus(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FINANCE.getValue());
				onceEarlyRepaymentRecord.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FINANCE.getDesc());
			}else{
				temp.setCurStatus(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FINANCE_FAIL.getValue());
				temp.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FINANCE_FAIL.getDesc());
				onceEarlyRepaymentRecord.setCurStatus(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FINANCE_FAIL.getValue());
				onceEarlyRepaymentRecord.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FINANCE_FAIL.getDesc());

				if(planInfo.getIsLock() == 1){
					RepaymentPlanInfo tempPlan = new RepaymentPlanInfo();
					tempPlan.setId(planInfo.getId());

					tempPlan.setIsLock(0);
					tempPlan.setUpdateTime(now);
					repaymentPlanInfoDao.update(tempPlan);
				}
			}
			mapper.update(temp);
			onceEarlyRepaymentRecordService.update(onceEarlyRepaymentRecord);


			//3 保存流程记录
			RepaymentApporveRecord record = new RepaymentApporveRecord();
			record.setRepaymentId(onceRecord.getRepaymentId());
			record.setOperatorId(operatorId);
			record.setOperatorName(operatorName);
			record.setOperatorResult(operatorResult);
			record.setOperatorTip(operatorTip);
			record.setOperatorTime(new Date());
			record.setPreStatus(repaymentInfo.getCurStatus());
			record.setPreStatusDesc(repaymentInfo.getCurStatusDesc());
			record.setAfterStatus(repaymentInfo.getCurStatus());
			record.setAfterStatusDesc(temp.getCurStatusDesc());
			repaymentApproveRecordService.save(record);

			//TODO 4 短信通知



		}catch(Exception e){
			LOG.error("onceEarlyRepaymentBussApprove  exception = {}", e);
			return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
		}

		return ResultVO.build(ErrorCode.SUCCESS);
	}

	/**
	 * 财务经理审批
	 * @param onceEarlyId
	 * @param operatorResult
	 * @param operatorTip
	 * @param operatorId
	 * @param operatorName
	 * @return
	 */
	@Override
	@Transactional
	public ResultVO<Object> onceEarlyRepaymentFinanceManApprove(Long onceEarlyId, Integer operatorResult, String operatorTip, Long operatorId, String operatorName) {
		try {
			Date now = new Date();
			OnceEarlyRepaymentRecord onceRecord = onceEarlyRepaymentRecordService.selectById(onceEarlyId);


			RepaymentInfo repaymentInfo = mapper.selectById(onceRecord.getRepaymentId());


			//1 判断状态是否正确
			if(repaymentInfo.getCurStatus() != RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FINANCE.getValue()){
				LOG.error("财务经理审批提前还款状态不正确 onceEarlyId={} repaymentId={},status={}", onceEarlyId, onceRecord.getRepaymentId(), repaymentInfo.getCurStatus());
				return ResultVO.build(ErrorCode.REPAYMENT_ONCE_EARLY_PROCESS_ERROR);
			}

			RepaymentInfo temp = new RepaymentInfo();
			RepaymentPlanInfo planInfo =  repaymentPlanInfoDao.selectByPeriod(onceRecord.getRepaymentId(), onceRecord.getCurPeriodNum());

			OnceEarlyRepaymentRecord onceEarlyRepaymentRecord = new OnceEarlyRepaymentRecord();
			onceEarlyRepaymentRecord.setId(onceEarlyId);

			temp.setId(onceRecord.getRepaymentId());
			if(operatorResult == 1){
				temp.setIsOnceEarlyRepayment(1);
				temp.setCurStatus(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FIN_BUSS_APPROVE.getValue());
				temp.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FIN_BUSS_APPROVE.getDesc());
				onceEarlyRepaymentRecord.setCurStatus(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FIN_BUSS_APPROVE.getValue());
				onceEarlyRepaymentRecord.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FIN_BUSS_APPROVE.getDesc());
			}else{
				temp.setCurStatus(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FIN_BUSS_APPROVE_FAIL.getValue());
				temp.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FIN_BUSS_APPROVE_FAIL.getDesc());
				onceEarlyRepaymentRecord.setCurStatus(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FIN_BUSS_APPROVE_FAIL.getValue());
				onceEarlyRepaymentRecord.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FIN_BUSS_APPROVE_FAIL.getDesc());

				if(planInfo.getIsLock() == 1){
					RepaymentPlanInfo tempPlan = new RepaymentPlanInfo();
					tempPlan.setId(planInfo.getId());

					tempPlan.setIsLock(0);
					tempPlan.setUpdateTime(now);
					repaymentPlanInfoDao.update(tempPlan);
				}
			}
			mapper.update(temp);
			onceEarlyRepaymentRecordService.update(onceEarlyRepaymentRecord);


			//3 保存审批流程记录
			RepaymentApporveRecord record = new RepaymentApporveRecord();
			record.setRepaymentId(onceRecord.getRepaymentId());
			record.setOperatorId(operatorId);
			record.setOperatorName(operatorName);
			record.setOperatorResult(operatorResult);
			record.setOperatorTip(operatorTip);
			record.setOperatorTime(new Date());
			record.setPreStatus(repaymentInfo.getCurStatus());
			record.setPreStatusDesc(repaymentInfo.getCurStatusDesc());
			record.setAfterStatus(repaymentInfo.getCurStatus());
			record.setAfterStatusDesc(temp.getCurStatusDesc());
			repaymentApproveRecordService.save(record);

			//4 解除锁定
			if(planInfo.getIsLock() == 1) {
				RepaymentPlanInfo tempPlan = new RepaymentPlanInfo();
				tempPlan.setId(planInfo.getId());

				tempPlan.setIsLock(0);
				tempPlan.setUpdateTime(now);
				repaymentPlanInfoDao.update(tempPlan);
			}

			//5 开始分账
			//5.1 当期还款  -- 对公转账
			ResultVO<Object> vo = repaymentTxService.manualDeductMoney(onceRecord.getRepaymentId(),
					onceRecord.getCurPeriodNum(), 2);
			if(!vo.isSuccess()){
				LOG.error("提前还款分账-- 当期还款错误 onceEarlyId={} repaymentId={}, period={} status={}",
						onceEarlyId, onceRecord.getRepaymentId(), onceRecord.getCurPeriodNum(), repaymentInfo.getCurStatus());
				return vo;
			}

			vo = repaymentTxService.allotPayUpdatePlanInfo(Long.parseLong(vo.getData().toString()), true);
			if(!vo.isSuccess()){
				LOG.error("提前还款分账-- 当期还款分配错误 onceEarlyId={} repaymentId={}, period={} status={}",
						onceEarlyId, onceRecord.getRepaymentId(), onceRecord.getCurPeriodNum(), repaymentInfo.getCurStatus());
				return vo;
			}

			//重新读取数据
			repaymentInfo = mapper.selectById(onceRecord.getRepaymentId());

			BigDecimal capital1 = planInfo.getEndAmountLease();
			BigDecimal capital2 = onceRecord.getOnceRepaymentCapital().subtract(capital1);

			//5.2 当期剩余本金 -- 租赁剩余本金
			RepaymentPayInfo repaymentPayInfo = new RepaymentPayInfo();
			repaymentPayInfo.setRepaymentId(onceRecord.getRepaymentId());
			repaymentPayInfo.setPayType(RepaymentType.PAY_TYPE_ONCE_EARLY_CAPITAL.getValue());
			repaymentPayInfo.setPayTypeDesc(RepaymentType.PAY_TYPE_ONCE_EARLY_CAPITAL.getDesc());
			repaymentPayInfo.setPayingNum(0);
			repaymentPayInfo.setAmount(capital1);
			repaymentPayInfo.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
			repaymentPayInfo.setPayTime(now);
			repaymentPayInfo.setPayBank(repaymentInfo.getBankName());
			repaymentPayInfo.setPayCard(repaymentInfo.getBankCardNo());
			repaymentPayInfo.setBindingMobile(repaymentInfo.getCustMobile());
			repaymentPayInfo.setCreateTime(now);
			repaymentPayInfo.setUpdateTime(now);
			repaymentPayInfo.setIsDeleted(0);
			repaymentPayInfoService.save(repaymentPayInfo);

			//5.3 当期剩余本金 -- 天津费用
			repaymentPayInfo = new RepaymentPayInfo();
			repaymentPayInfo.setRepaymentId(onceRecord.getRepaymentId());
			repaymentPayInfo.setPayType(RepaymentType.PAY_TYPE_ONCE_EARLY_INTEREST.getValue());
			repaymentPayInfo.setPayTypeDesc(RepaymentType.PAY_TYPE_ONCE_EARLY_INTEREST.getDesc());
			repaymentPayInfo.setPayingNum(0);
			repaymentPayInfo.setAmount(capital2);
			repaymentPayInfo.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
			repaymentPayInfo.setPayTime(now);
			repaymentPayInfo.setPayBank(repaymentInfo.getBankName());
			repaymentPayInfo.setPayCard(repaymentInfo.getBankCardNo());
			repaymentPayInfo.setBindingMobile(repaymentInfo.getCustMobile());
			repaymentPayInfo.setCreateTime(now);
			repaymentPayInfo.setUpdateTime(now);
			repaymentPayInfo.setIsDeleted(0);
			repaymentPayInfoService.save(repaymentPayInfo);

			//5.4 当期剩余手续费
			if(onceRecord.getOnceRepaymentCharge().compareTo(new BigDecimal(0)) > 0){
				repaymentPayInfo = new RepaymentPayInfo();
				repaymentPayInfo.setRepaymentId(onceRecord.getRepaymentId());
				repaymentPayInfo.setPayType(RepaymentType.PAY_TYPE_ONCE_EARLY_CHARGE.getValue());
				repaymentPayInfo.setPayTypeDesc(RepaymentType.PAY_TYPE_ONCE_EARLY_CHARGE.getDesc());
				repaymentPayInfo.setPayingNum(0);
				repaymentPayInfo.setAmount(onceRecord.getOnceRepaymentCharge());
				repaymentPayInfo.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
				repaymentPayInfo.setPayTime(now);
				repaymentPayInfo.setPayBank(repaymentInfo.getBankName());
				repaymentPayInfo.setPayCard(repaymentInfo.getBankCardNo());
				repaymentPayInfo.setBindingMobile(repaymentInfo.getCustMobile());
				repaymentPayInfo.setCreateTime(now);
				repaymentPayInfo.setUpdateTime(now);
				repaymentPayInfo.setIsDeleted(0);
				repaymentPayInfoService.save(repaymentPayInfo);
			}

			//5.5 当期剩违约金
			if(onceRecord.getOnceRepaymentBreach().compareTo(new BigDecimal(0)) > 0){
				repaymentPayInfo = new RepaymentPayInfo();
				repaymentPayInfo.setRepaymentId(onceRecord.getRepaymentId());
				repaymentPayInfo.setPayType(RepaymentType.PAY_TYPE_ONCE_EARLY_BREACH.getValue());
				repaymentPayInfo.setPayTypeDesc(RepaymentType.PAY_TYPE_ONCE_EARLY_BREACH.getDesc());
				repaymentPayInfo.setPayingNum(0);
				repaymentPayInfo.setAmount(onceRecord.getOnceRepaymentBreach());
				repaymentPayInfo.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
				repaymentPayInfo.setPayTime(now);
				repaymentPayInfo.setPayBank(repaymentInfo.getBankName());
				repaymentPayInfo.setPayCard(repaymentInfo.getBankCardNo());
				repaymentPayInfo.setBindingMobile(repaymentInfo.getCustMobile());
				repaymentPayInfo.setCreateTime(now);
				repaymentPayInfo.setUpdateTime(now);
				repaymentPayInfo.setIsDeleted(0);
				repaymentPayInfoService.save(repaymentPayInfo);
			}

			//6 更改还款总表

			BigDecimal monthLeaseTotal = new BigDecimal(0);
			BigDecimal monthServiceTotal =  new BigDecimal(0);
			BigDecimal leaseCapitalTotal = new BigDecimal(0);		//租赁本金总计
			BigDecimal leaseInterestTotal = new BigDecimal(0);		//租赁利息总计

			List<RepaymentPlanInfo> planList = repaymentPlanInfoDao.listByRepaymentId(repaymentInfo.getId());
			for(int i=0;i<onceRecord.getCurPeriodNum();i++){
				leaseCapitalTotal = leaseCapitalTotal.add(planList.get(i).getCapitalLease());
				leaseInterestTotal = leaseInterestTotal.add(planList.get(i).getInterestLease());
				monthLeaseTotal = monthLeaseTotal.add(planList.get(i).getLeaseTotal());
				monthServiceTotal = monthServiceTotal.add(planList.get(i).getServiceTotal());
			}
			//分配租赁提前还款金额
			leaseCapitalTotal = leaseCapitalTotal.add(capital1);
			monthLeaseTotal = monthLeaseTotal.add(capital1);

			//分配天津氢诺提前还款金额
			monthServiceTotal = monthServiceTotal.add(capital2);
			monthServiceTotal = monthServiceTotal.add(onceRecord.getOnceRepaymentCharge());
			monthServiceTotal = monthServiceTotal.add(onceRecord.getOnceRepaymentBreach());


			RepaymentInfo newInfo = new RepaymentInfo();
			newInfo.setId(repaymentInfo.getId());
			newInfo.setPredictInterest(repaymentInfo.getActualInterest());
			newInfo.setPredictServiceCharge(repaymentInfo.getActualServiceCharge());
			newInfo.setPredictAmount(repaymentInfo.getPredictCharge()
						.add(repaymentInfo.getPredictCapital())
						.add(newInfo.getPredictInterest())
						.add(newInfo.getPredictServiceCharge()));
			newInfo.setActualCharge(repaymentInfo.getActualCharge().add(onceRecord.getOnceRepaymentCharge()));
			newInfo.setActualCapital(repaymentInfo.getActualCapital().add(onceRecord.getOnceRepaymentCapital()));
			newInfo.setActualAmount(newInfo.getActualCharge()
							.add(newInfo.getActualCapital())
							.add(repaymentInfo.getActualInterest())
							.add(repaymentInfo.getActualServiceCharge()));
			newInfo.setLeaseCapitalTotal(leaseCapitalTotal);
			newInfo.setLeaseInterestTotal(leaseInterestTotal);
			newInfo.setMonthServiceTotal(monthServiceTotal);
			newInfo.setMonthLeaseTotal(monthLeaseTotal);
			LOG.info("repaymentId={} name={} predictAmount={} actualAmount={}", repaymentInfo.getId(), repaymentInfo.getCustName(),
						newInfo.getPredictAmount(), newInfo.getActualAmount());
			newInfo.setBreachAmount(onceRecord.getOnceRepaymentBreach());
			newInfo.setCurStatus(RepaymentApproveStatus.REPAYMENT_DONE.getValue());
			newInfo.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_DONE.getDesc());

			//7 添加还款变更记录
			RepaymentChangeRecord changeRecord = new RepaymentChangeRecord();
			changeRecord.setRepaymentId(onceRecord.getRepaymentId());
			changeRecord.setChangeType(RepaymentChangeType.CHANGE_TYPE_EARLY_PAY.getValue());
			changeRecord.setChangeDesc(RepaymentChangeType.CHANGE_TYPE_EARLY_PAY.getDesc());
			changeRecord.setOnceRepaymentCapital(onceRecord.getOnceRepaymentCapital());
			changeRecord.setOnceRepaymentCharge(onceRecord.getOnceRepaymentCharge());
			changeRecord.setOnceRepaymentBreach(onceRecord.getOnceRepaymentBreach());
			changeRecord.setOnceRepaymentTotal(onceRecord.getOnceRepaymentTotal());
			changeRecord.setCurPeriodNum(onceRecord.getCurPeriodNum());
			changeRecord.setCurPeriodAmount(onceRecord.getCurPeriodAmount());
			changeRecord.setOldAmount(repaymentInfo.getPredictAmount());
			changeRecord.setOldServiceCharge(repaymentInfo.getPredictServiceCharge());
			changeRecord.setOldInterest(repaymentInfo.getPredictInterest());
			changeRecord.setOldLeaseCapitalTotal(repaymentInfo.getLeaseCapitalTotal());
			changeRecord.setOldLeaseInterestTotal(repaymentInfo.getLeaseInterestTotal());
			changeRecord.setOldMonthLeaseTotal(repaymentInfo.getMonthLeaseTotal());
			changeRecord.setOldMonthServiceTotal(repaymentInfo.getMonthServiceTotal());
			changeRecord.setNewAmount(newInfo.getPredictAmount());
			changeRecord.setNewInterest(newInfo.getPredictInterest());
			changeRecord.setNewServiceCharge(newInfo.getPredictServiceCharge());
			changeRecord.setNewLeaseCapitalTotal(leaseCapitalTotal);
			changeRecord.setNewLeaseInterestTotal(leaseInterestTotal);
			changeRecord.setNewMonthLeaseTotal(monthLeaseTotal);
			changeRecord.setNewMonthServiceTotal(monthServiceTotal);
			changeRecord.setCreateTime(now);
			changeRecord.setUpdateTime(now);
			changeRecord.setIsDeleted(0);
			repaymentChangeRecordService.save(changeRecord);

			mapper.update(newInfo);

			//8 删除剩余还款计划表
			planList = repaymentPlanInfoDao.listByRepaymentId(repaymentInfo.getId());
			for (RepaymentPlanInfo info : planList) {
				if(info.getPeriodNum() <= onceRecord.getCurPeriodNum()){
					continue;
				}

				RepaymentPlanInfo tempPlan = new RepaymentPlanInfo();
				tempPlan.setId(info.getId());
				tempPlan.setIsDeleted(1);
				tempPlan.setUpdateTime(now);
				repaymentPlanInfoDao.update(tempPlan);
			}

			//TODO 4 短信通知



		}catch(Exception e){
			LOG.error("onceEarlyRepaymentBussApprove  exception = {}", e);
			return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
		}

		return ResultVO.build(ErrorCode.SUCCESS);
	}


    /**
	 * 前4笔订单的手续费总额 version=0 前4笔比较特殊一次性收全手续费
	 * @return
	 */
	@Override
	public List<Map<String, Object>> countChargeBefore() {
		return mapper.countChargeBefore();
	}

	/**
	 * 主要是对租赁等金额的计算导入 20180310 之前的数据
	 */
	@Override
	public void exportLeaseMoney() {
		List<RepaymentInfo> list = mapper.listByCondition(null, null, null, null, null);
		for(RepaymentInfo info : list){
			List<RepaymentPlanInfo> planList = repaymentPlanInfoDao.listByRepaymentId(info.getId());

			RepaymentCalculatorVO vo;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			if(info.getVersion() == 0 || info.getVersion() == 1){
				//
				LOG.info("----> 正在导入客户[{}] 版本[{}] 调用before算法生成...", info.getCustName(), info.getVersion());
				vo = RepaymentInfoServiceImpl.calculatorRepaymentBefore(info.getLoanAmount().toString(), info.getLoanPeriod(), sdf.format(info.getInterestBeginTime()), info.getVersion());
			}else{
				LOG.info("----> 正在导入客户[{}] 版本[{}] 调用 正常 算法生成...", info.getCustName(), info.getVersion());
				vo = RepaymentInfoServiceImpl.calculatorRepayment(info.getLoanAmount().toString(), info.getLoanPeriod(), sdf.format(info.getInterestBeginTime()), info.getVersion(), 0);
			}

			for(int i=0;i<planList.size();i++){
				RepaymentPlanInfo temp = new RepaymentPlanInfo();
				temp.setId(planList.get(i).getId());
				if(planList.get(i).getPeriodNum() != vo.getPlans().get(i).getPeriodNum()){
					LOG.error("出现错误 repaymentId={} 数据库期数{} 计算期数{}", planList.get(i).getPeriodNum(),
							vo.getPlans().get(i).getPeriodNum());
				}
				temp.setBeginAmountLease(vo.getPlans().get(i).getBeginAmountLease());
				temp.setEndAmountLease(vo.getPlans().get(i).getEndAmountLease());
				temp.setCapitalLease(vo.getPlans().get(i).getCapitalLease());
				temp.setInterestLease(vo.getPlans().get(i).getInterestLease());
				temp.setLeaseTotal(vo.getPlans().get(i).getLeaseFee());
				temp.setServiceTotal(vo.getPlans().get(i).getPlatformFee());
				repaymentPlanInfoDao.update(temp);
			}
			RepaymentInfo infoTemp = new RepaymentInfo();
			infoTemp.setId(info.getId());
			infoTemp.setMonthLeaseTotal(vo.getMonthLeaseTotal());
			infoTemp.setMonthServiceTotal(vo.getMonthServiceTotal());
			infoTemp.setLeaseCapitalTotal(vo.getLeaseCapitalTotal());
			infoTemp.setLeaseInterestTotal(vo.getLeaseInterestTotal());
			this.update(infoTemp);
		}
	}


	/**
	 * 临时用 导入合同编号
	 * @return
	 */
	public ResultVO<Object> testExportContractNo(Long repaymentId, String contractNo){

		if(repaymentId == null || StringUtils.isEmpty(contractNo)){
			LOG.error("param is null, repaymentId={} contractNo={}", repaymentId, contractNo);
			return ResultVO.build(ErrorCode.PARAM_ERROR);
		}
		RepaymentInfo info = mapper.selectById(repaymentId);
		if(info == null){
			LOG.error("not exist repaymentInfo with repaymentId={}", repaymentId);
			return ResultVO.build(ErrorCode.REPAYMENT_INFO_NOT_EXIST);
		}

		PayInfo payInfo = payInfoService.selectById(info.getPayId());


		RepaymentInfo temp = new RepaymentInfo();
		temp.setId(repaymentId);
		temp.setContractNo(contractNo);
		mapper.update(temp);

		PayInfo temp1 = new PayInfo();
		temp1.setId(payInfo.getId());
		temp1.setContractNo(contractNo);
		payInfoService.update(temp1);

		return ResultVO.build(ErrorCode.SUCCESS);
	}

	@Override
	public RepaymentInfo selectByApplyId(Long applyId) {
		// TODO Auto-generated method stub
		return mapper.selectByApplyId(applyId);
	}

}

