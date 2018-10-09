package com.moerlong.carloan.modular.payMgr.service;

import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.payMgr.entity.PayApproveRecord;

import java.util.List;

public interface PayApproveRecordService {

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
	public PageInfo<PayApproveRecord> selectPage(int pageSize,int pageNum, String orderCondition );

	/**
	 * 按Payid查询
	 * @param payId
	 * @return
	 */
	public List<PayApproveRecord> listByPayId(Long payId);

}

