package com.moerlong.carloan.modular.paybackMgr.dao;

import org.apache.ibatis.annotations.Param;

import com.moerlong.carloan.modular.paybackMgr.entity.OnceEarlyRepaymentRecord;
import com.moerlong.carloan.modular.paybackMgr.entity.vo.OnceEarlyRepaymentRecordVO;

import java.util.List;

public interface OnceEarlyRepaymentRecordDao {


	/**
	 * 保存
	 * @param entity
	 */
	public void save(OnceEarlyRepaymentRecord entity);

	/**
	 * 删除
	 * @param id
	 */
	public void delete(Long id);

	/**
	 * 更新
	 * @param entity
	 */
	public void update(OnceEarlyRepaymentRecord entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public OnceEarlyRepaymentRecord selectById(Long id);

	public OnceEarlyRepaymentRecord selectByRepaymentId(@Param("repaymentId") Long repaymentId);


	/**
	 * 根据条件查询
	 * @return
	 */
	public List<OnceEarlyRepaymentRecordVO> listByCondition(@Param("custName") String custName, @Param("custMobile") String custMobile, @Param("beginTime") String beginTime, @Param("endTime") String endTime);



}

