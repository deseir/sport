package com.moerlong.carloan.modular.paybackMgr.dao;

import org.apache.ibatis.annotations.Param;

import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentApporveRecord;

import java.util.List;

public interface RepaymentApproveRecordDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(RepaymentApporveRecord entity);

	/**
	 * 删除
	 * @param id
	 */
	public void delete(Long id);

	/**
	 * 更新
	 * @param entity
	 */
	public void update(RepaymentApporveRecord entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public RepaymentApporveRecord selectById(Long id);

	public List<RepaymentApporveRecord> selectByRepaymentId(@Param("repaymentId") Long repaymentId);



}

