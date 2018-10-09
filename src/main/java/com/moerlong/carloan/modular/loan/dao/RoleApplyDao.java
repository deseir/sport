package com.moerlong.carloan.modular.loan.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.loan.entity.RoleApply;

public interface RoleApplyDao {

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
	 * @param param	查询参数
	 * @return
	 */
	public List<RoleApply> selectPage(Map<String,Object> param);

}

