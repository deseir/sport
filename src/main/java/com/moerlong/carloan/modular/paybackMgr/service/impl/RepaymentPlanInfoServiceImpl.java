package com.moerlong.carloan.modular.paybackMgr.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import com.moerlong.carloan.modular.paybackMgr.dao.RepaymentInfoDao;
import com.moerlong.carloan.modular.paybackMgr.dao.RepaymentPlanInfoDao;
import com.moerlong.carloan.modular.paybackMgr.service.RepaymentPlanInfoService;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentInfo;
import com.moerlong.carloan.modular.paybackMgr.entity.vo.RepaymentPlanInfoVO;
import com.moerlong.carloan.modular.paybackMgr.entity.vo.RepaymentPlanTodayInfoVO;
import com.moerlong.carloan.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentPlanInfo;

@Service
public class RepaymentPlanInfoServiceImpl implements RepaymentPlanInfoService{

	@Autowired
	RepaymentPlanInfoDao mapper;

	@Autowired
	RepaymentInfoDao repaymentInfoDao;
	
	@Transactional
	public void saveOrUpdate(RepaymentPlanInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional
	public void save(RepaymentPlanInfo entity) {
		mapper.save(entity);
	}
	
	@Transactional
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Transactional
	public void update(RepaymentPlanInfo entity) {
		mapper.update(entity);
	}
	
	public RepaymentPlanInfo selectById(Long id) {
		return mapper.selectById(id);
	}

	@Override
	public RepaymentPlanInfo selectByPeriod(Long repaymentId, Integer period) {
		return mapper.selectByPeriod(repaymentId, period);
	}

	public PageInfo<RepaymentPlanInfo> selectPage(int pageSize,int pageNum, String orderCondition ) {
		PageHelper.startPage(pageNum, pageSize);
		List<RepaymentPlanInfo> pageList = mapper.selectPage( orderCondition);
		PageInfo<RepaymentPlanInfo> pageInfo = new PageInfo<RepaymentPlanInfo>(pageList);
		return pageInfo;
	}

	/**
	 * 获取逾期的还款计划列表  每日定时扣款用
	 * @return
	 */
	@Override
	public List<RepaymentPlanInfo> selectOverduePlan() {
		return mapper.selectOverduePlan();
	}

	/**
	 * 获取今天到期的还款计划表  每日定时扣款用
	 * @return
	 */
	@Override
	public List<RepaymentPlanInfo> selectTodayPlan() {
		return mapper.selectTodayPlan();
	}

	/**
	 * 查询已经开始还款的还款计划列表	每日定时更新还款计划状态用
	 * @return
	 */
	@Override
	public List<RepaymentPlanInfo> selectPlanToRepaymenting() {
		return mapper.selectPlanToRepaymenting();
	}

	/**
	 * 查询已经到逾期的还款计划列表  每日定时更新还款计划状态用
	 * @return
	 */
	@Override
	public List<RepaymentPlanInfo> selectPlanToOverdue() {
		return mapper.selectPlanToOverdue();
	}

	/**
	 * 根据还款id查询还款计划列表
	 * @return
	 */
	public List<RepaymentPlanInfo> listByRepaymentId(Long repaymentId){
		return mapper.listByRepaymentId(repaymentId);
	}

	@Override
	public List<RepaymentPlanTodayInfoVO> listToday(String checkDate) {

		int beforeFlag = 0;
		int curDayFlag = 0;

		List<RepaymentPlanTodayInfoVO> res = new ArrayList<>();
		List<RepaymentPlanInfo> plans = new ArrayList<RepaymentPlanInfo>();
		List<RepaymentPlanInfo> payedPlans = new ArrayList<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		String day;
		Date check;

		if(checkDate == null){
			curDayFlag = 1;
			day = sdf.format(new Date());
		}else{
			check = DateUtil.str2Date(checkDate ,DateUtil.YMD_YMD);
			if(check.before(new Date())){
				beforeFlag = 1;
			}
			if(checkDate.equals(sdf.format(new Date()))){
				curDayFlag = 1;
			}
			day = checkDate;
		}

		if(beforeFlag == 0 || curDayFlag == 1){
			// 获取最后还款日还款计划
			List<RepaymentPlanInfo> curPlans = mapper.selectCurDayList(day, null);
			if (curPlans != null && curPlans.size() > 0) {
				plans.addAll(curPlans);
			}
		}

		if(curDayFlag == 1){
			//如果是今天 则需要获取逾期还款计划
			List<RepaymentPlanInfo> overduePlans = mapper.selectOverduePlan();
			if (overduePlans != null && overduePlans.size() > 0) {
				plans.addAll(overduePlans);
			}
		}

		payedPlans = mapper.selectCurDayPayRecord(day);

		//去重
		Map<Long, RepaymentPlanInfo> all = new HashMap<>();
		for(RepaymentPlanInfo plan : plans){
			all.put(plan.getId(), plan);
		}
		for(RepaymentPlanInfo plan : payedPlans){
			all.put(plan.getId(), plan);
		}

		for(RepaymentPlanInfo planInfo : all.values()){
			RepaymentInfo info = repaymentInfoDao.selectById(planInfo.getRepaymentId());
			RepaymentPlanTodayInfoVO a = new RepaymentPlanTodayInfoVO();
			a.setId(planInfo.getId());
			a.setRepaymentId(info.getId());
			a.setContractNo(info.getContractNo());
			a.setCustName(info.getCustName());
			a.setBankName(info.getBankName());
			a.setBankNo(info.getBankCardNo());
			a.setIdCard(info.getCustIdNo());
			a.setMobile(info.getCustMobile());
			a.setPeriodNum(planInfo.getPeriodNum());
			a.setPeriodStatus(planInfo.getPeriodStatus());
			a.setPeriodBeginTime(planInfo.getPeriodBeginTime());
			a.setPeriodEndTime(planInfo.getPeriodEndTime());
			a.setPredictAmount(planInfo.getPredictAmount());
			res.add(a);
		}
		return res;
	}

	/**
	 * 统计未还款总额
	 * @return
	 */
	@Override
	public List<Map<String, Object>> countAllNotRepaymentMoney() {
		return mapper.countAllNotRepaymentMoney();
	}

	/**
	 * 根据还款总表id统计已还款总额
	 * @param repaymentId
	 * @return
	 */
	@Override
	public Map<String, Object> countRepaymentMoneyByRepaymentId(Long repaymentId) {
		return mapper.countRepaymentMoneyByRepaymentId(repaymentId);
	}


	/**
	 * 获取当日到期的列表  （这里这个需要与 selectTodayPlan 区分，上一个只获取未还款完成的列表，这个不区分还款状态）
	 * @return
	 */
	@Override
	public List<RepaymentPlanInfo> selectCurDayList(String checkDate, Integer type) {
		return mapper.selectCurDayList(checkDate, type);
	}

	/**
	 * 获取当日扣款记录
	 * @param checkDate
	 * @return
	 */
	@Override
	public List<RepaymentPlanInfo> selectCurDayPayRecord(String checkDate) {
		return mapper.selectCurDayPayRecord(checkDate);
	}

	/**
	 * 根据条件查询还款计划表
	 * @return
	 */
	public PageInfo<RepaymentPlanInfoVO> listByCondition(Integer pageSize, Integer pageNum, String custName, String custMobile, String beginTime, String endTime, String curStatus) {
		PageHelper.startPage(pageNum, pageSize);
		List<RepaymentPlanInfoVO> pageList = mapper.listByCondition(custName, custMobile, beginTime, endTime, curStatus);
		PageInfo<RepaymentPlanInfoVO> pageInfo = new PageInfo<RepaymentPlanInfoVO>(pageList);
		return pageInfo;
	}

	/**
	 * 获取某个客户的总应还罚息金额
	 * @param repaymentId
	 * @return
	 */
    @Override
    public BigDecimal sumPenaltyByRepaymentId(Long repaymentId) {
        return mapper.sumPenaltyByRepaymentId(repaymentId);
    }

}

