package com.moerlong.carloan.modular.payMgr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.moerlong.carloan.modular.payMgr.entity.PayOrderApply;

public interface PayOrderApplyDao {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(PayOrderApply entity);

	/**
	 * 保存
	 * @param entity
	 */
	public void save(PayOrderApply entity);

	/**
	 * 删除
	 * @param id
	 */
	public void delete(Long id);
	
	/**
	 * 更新
	 * @param entity
	 */
	public void update(PayOrderApply entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public PayOrderApply selectById(Long id);
	
	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public List<PayOrderApply> selectPage( @Param("orderCondition") String orderCondition);

	/**
	 * 根据条件查询审核订单
	 * @return
	 */
	public List<PayOrderApply> listByCondition(@Param("batchNo")String batchNo, @Param("beginTime")String beginTime, @Param("endTime")String endTime, @Param("status")String status);


}

