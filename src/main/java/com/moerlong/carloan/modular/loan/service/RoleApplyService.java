package com.moerlong.carloan.modular.loan.service;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.loan.entity.RoleApply;

public interface RoleApplyService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(RoleApply entity);
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(RoleApply entity);

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
	public void update(RoleApply entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(RoleApply entity);
	
	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public RoleApply selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<RoleApply> listAll();
	
	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<RoleApply> selectPage(int pageSize,int pageNum, Map<String,Object> param);

}

