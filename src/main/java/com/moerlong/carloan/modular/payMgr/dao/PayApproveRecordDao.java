package com.moerlong.carloan.modular.payMgr.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.moerlong.carloan.modular.payMgr.entity.PayApproveRecord;

public interface PayApproveRecordDao {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(PayApproveRecord entity);

	/**
	 * 保存
	 * @param entity
	 */
	public void save(PayApproveRecord entity);

	/**
	 * 删除
	 * @param id
	 */
	public void delete(Long id);
	
	/**
	 * 更新
	 * @param entity
	 */
	public void update(PayApproveRecord entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public PayApproveRecord selectById(Long id);
	
	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public List<PayApproveRecord> selectPage( @Param("orderCondition") String orderCondition);

	/**
	 * 按Payid查询
	 * @param payId
	 * @return
	 */
	public List<PayApproveRecord> listByPayId(@Param("payId") Long payId);

}

