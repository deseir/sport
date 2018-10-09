package com.moerlong.carloan.modular.paybackMgr.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentPayInfo;
import com.moerlong.carloan.modular.paybackMgr.entity.vo.RepaymentPayInfoVO;
import com.moerlong.carloan.modular.paybackMgr.entity.vo.RepaymentPayReportInfoVO;

public interface RepaymentPayInfoDao {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(RepaymentPayInfo entity);

	/**
	 * 保存
	 * @param entity
	 */
	public void save(RepaymentPayInfo entity);

	/**
	 * 删除
	 * @param id
	 */
	public void delete(Long id);
	
	/**
	 * 更新
	 * @param entity
	 */
	public void update(RepaymentPayInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public RepaymentPayInfo selectById(Long id);

	public RepaymentPayInfo selectInfoByPrimaryKeyForUpdate(Long id);
	
	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public List<RepaymentPayInfo> selectPage( @Param("orderCondition") String orderCondition);

	/**
	 * 根据条件查询代扣订单
	 * @return
	 */
	public List<RepaymentPayInfoVO> listByCondition(@Param("payCode")String payCode, @Param("batchNo")String batchNo, @Param("beginTime")String beginTime, @Param("endTime")String endTime,
													@Param("status")String status, @Param("payType") Integer payType);


	public List<RepaymentPayInfoVO> listByConditionMrl(@Param("payCode")String payCode, @Param("batchNo")String batchNo, @Param("beginTime")String beginTime, @Param("endTime")String endTime,
													@Param("status")String status, @Param("payType") Integer payType);

	/**
	 * 根据时间条件查询历史订单报表
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<RepaymentPayReportInfoVO> listReport(@Param("beginTime")String beginTime, @Param("endTime")String endTime);


	List<RepaymentPayInfo> selectInfoByRepaymentIdBetweenStatus(@Param("repaymentId") Long repaymentId,
																@Param("aStatus") Integer aStatus, @Param("bStatus") Integer bStatus);


	List<RepaymentPayInfo> selectPaysByStatus(@Param("payStatus") Integer payStatus);

	/**
	 * 获取昨天（T-1) 成功放款金额总计
	 * @return
	 */
	List<Map<String ,Object>> getPaysCountLastDaySuccess();

	/**
	 * 获取昨天（T-1)成功收取前期费用总计
	 * @return
	 */
	List<Map<String ,Object>> getPreFeePaysCountLastDaySuccess();

	/**
	 * 统计累计还款金额总计
	 * @return
	 */
	List<Map<String ,Object>> countAllRepaymentMoney();

	/**
	 * 统计累计收取前期费用总计
	 * @return
	 */
	List<Map<String, Object>> countAllRepaymentPreFee();

	/**
	 * 根据还款id统计已还金额
	 * @return
	 */
	List<Map<String, Object>> countRepaymentMoneyByRepaymentId(@Param("repaymentId") Long repaymentId);
	
	BigDecimal countRepaymentMoneyByRepaymentId2(@Param("repaymentId") Long repaymentId);


	/**
	 * 根据提前还款类型获取提前还款的统计数据
	 * @param payType  10 11 12 13
	 * @return
	 */
	List<Map<String, Object>> countAllOnceEarlyRepayment(@Param("payType") Integer payType);
}

