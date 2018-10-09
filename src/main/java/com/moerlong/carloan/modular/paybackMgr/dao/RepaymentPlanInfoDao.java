package com.moerlong.carloan.modular.paybackMgr.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentPlanInfo;
import com.moerlong.carloan.modular.paybackMgr.entity.vo.RepaymentPlanInfoVO;

public interface RepaymentPlanInfoDao {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(RepaymentPlanInfo entity);

	/**
	 * 保存
	 * @param entity
	 */
	public void save(RepaymentPlanInfo entity);

	/**
	 * 删除
	 * @param id
	 */
	public void delete(Long id);
	
	/**
	 * 更新
	 * @param entity
	 */
	public void update(RepaymentPlanInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public RepaymentPlanInfo selectById(Long id);


	public RepaymentPlanInfo selectByPeriod(@Param("repaymentId") Long repaymentId, @Param("period") Integer period);
	
	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public List<RepaymentPlanInfo> selectPage( @Param("orderCondition") String orderCondition);

	/**
	 * 获取逾期的还款计划列表  每日定时扣款用
	 * @return
	 */
	public List<RepaymentPlanInfo> selectOverduePlan();


	/**
	 * 获取今天到期的还款计划表  每日定时扣款用
	 * @return
	 */
	public List<RepaymentPlanInfo> selectTodayPlan();


	/**
	 * 查询已经开始还款的还款计划列表	每日定时更新还款计划状态用
	 * @return
	 */
	public List<RepaymentPlanInfo> selectPlanToRepaymenting();

	/**
	 * 查询已经到逾期的还款计划列表  每日定时更新还款计划状态用
	 * @return
	 */
	public List<RepaymentPlanInfo> selectPlanToOverdue();

	/**
	 * 根据还款id查询还款计划列表
	 * @return
	 */
	public List<RepaymentPlanInfo> listByRepaymentId(@Param("repaymentId")Long repaymentId);

	/**
	 * 统计未还款总额
	 * @return
	 */
	public List<Map<String, Object>> countAllNotRepaymentMoney();

	/**
	 * 根据还款总表id统计已还款总额
	 * @param repaymentId
	 * @return
	 */
	public Map<String, Object> countRepaymentMoneyByRepaymentId(@Param("repaymentId") Long repaymentId);

	/**
	 * 获取当日到期的列表  （这里这个需要与 selectTodayPlan 区分，上一个只获取未还款完成的列表，这个不区分还款状态）
	 * 如果type 为空 则不判断状态 ，如果type 不为空则只获取未还完的状态
	 * @return
	 */
	public List<RepaymentPlanInfo> selectCurDayList(@Param("checkDate") String checkDate, @Param("type") Integer type);

	/**
	 * 获取当日扣款记录 这里会因为扣款失败出现重复 注意去重
	 * @param checkDate
	 * @return
	 */
	public List<RepaymentPlanInfo> selectCurDayPayRecord(@Param("checkDate") String checkDate);

	/**
	 * 根据条件查询还款总表
	 * @return
	 */
	public List<RepaymentPlanInfoVO> listByCondition(@Param("custName") String custName, @Param("custMobile") String custMobile, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("curStatus")String curStatus);

	/**
	 * 获取某个客户的总应还罚息金额
	 * @param repaymentId
	 * @return
	 */
	public BigDecimal sumPenaltyByRepaymentId(@Param("repaymentId") Long repaymentId);
}

