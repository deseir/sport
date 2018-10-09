package com.moerlong.carloan.modular.cust.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.cust.entity.IdAuthInfo;

public interface IdAuthInfoDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(IdAuthInfo entity);

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
	public void update(IdAuthInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(IdAuthInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public IdAuthInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<IdAuthInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<IdAuthInfo> selectPage(Map<String,Object> param);

	/**
	 * 根据 applyId 和Type查询
	 * @param param
	 * @return
	 */
	public IdAuthInfo selectByApplyIdAndType (Map<String,Object> param);

}

