package com.moerlong.carloan.modular.paybackMgr.service;

import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.paybackMgr.entity.CostOrderApply;

import java.util.List;

public interface CostOrderApplyService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(CostOrderApply entity);
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(CostOrderApply entity);

	/**
	 * 删除
	 * @param id
	 */
	public void delete(Long id);

	/**
	 * 更新
	 * @param entity
	 */
	public void update(CostOrderApply entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CostOrderApply selectById(Long id);

	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<CostOrderApply> selectPage(int pageSize,int pageNum, String orderCondition );


	/**
	 * 根据条件查询审核订单
	 * @return
	 */
	public List<CostOrderApply> listByCondition(String batchNo, String beginTime, String endTime, String status);

}

