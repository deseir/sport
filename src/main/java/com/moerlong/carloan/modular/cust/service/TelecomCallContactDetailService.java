package com.moerlong.carloan.modular.cust.service;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.TelecomCallContactDetail;

public interface TelecomCallContactDetailService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(TelecomCallContactDetail entity);
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(TelecomCallContactDetail entity);

	/**
	 * 删除
	 * @param id
	 */
	public void delete(Long id);
	
	/**
	 * 逻辑删除
	 * @param id
	 */
	public void deleteLogic(Long id);
	
	/**
	 * 更新
	 * @param entity
	 */
	public void update(TelecomCallContactDetail entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(TelecomCallContactDetail entity);
	
	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public TelecomCallContactDetail selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<TelecomCallContactDetail> listAll();
	
	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<TelecomCallContactDetail> selectPage(int pageSize,int pageNum, Map<String,Object> param);

}

