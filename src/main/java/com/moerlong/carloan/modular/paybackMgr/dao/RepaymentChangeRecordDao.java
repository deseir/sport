package com.moerlong.carloan.modular.paybackMgr.dao;

import org.apache.ibatis.annotations.Param;

import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentChangeRecord;

import java.util.List;

public interface RepaymentChangeRecordDao {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(RepaymentChangeRecord entity);

	/**
	 * 保存
	 * @param entity
	 */
	public void save(RepaymentChangeRecord entity);

	/**
	 * 删除
	 * @param id
	 */
	public void delete(Long id);

	/**
	 * 更新
	 * @param entity
	 */
	public void update(RepaymentChangeRecord entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public RepaymentChangeRecord selectById(Long id);

	public List<RepaymentChangeRecord> selectByRepaymentId(@Param("repaymentId") Long repaymentId);

	public List<RepaymentChangeRecord> selectByRepaymentPlanId(@Param("repaymentPlanId") Long repaymentPlanId);

	/**
	 * 根据条件查询
	 * @return
	 */
	public List<RepaymentChangeRecord> listByCondition(@Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("changeType") Integer changeType);



}

