package com.moerlong.carloan.modular.loan.service;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.loan.entity.SupplementInfo;

public interface SupplementInfoService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(SupplementInfo entity);
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(SupplementInfo entity);

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
	public void update(SupplementInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(SupplementInfo entity);
	
	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public SupplementInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<SupplementInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<SupplementInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param);

	/**
	 * 根据applyId查询
	 * @param param
	 * @return
	 */
	public SupplementInfo selectByApplyId (Map<String,Object> param);

}

