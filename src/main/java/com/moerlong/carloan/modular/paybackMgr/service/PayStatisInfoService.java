package com.moerlong.carloan.modular.paybackMgr.service;

import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.paybackMgr.entity.PayStatisInfo;


public interface PayStatisInfoService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(PayStatisInfo entity);
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(PayStatisInfo entity);

	/**
	 * 删除
	 * @param id
	 */
	public void delete(Long id);

	/**
	 * 更新
	 * @param entity
	 */
	public void update(PayStatisInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public PayStatisInfo selectById(Long id);

	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<PayStatisInfo> selectPage(int pageSize,int pageNum, String orderCondition );

	/**
	 * 根据条件查询日报表记录
	 * @return
	 */
	public PageInfo<PayStatisInfo> listByCondition(Integer pageSize,Integer pageNum,String beginTime, String endTime,String deptId);

}

