package com.moerlong.carloan.modular.cust.dao;

import com.moerlong.carloan.modular.cust.entity.JudicialAuthInfo;

import java.util.List;
import java.util.Map;

public interface JudicialAuthInfoDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(JudicialAuthInfo entity);

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
	public void update(JudicialAuthInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(JudicialAuthInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public JudicialAuthInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<JudicialAuthInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<JudicialAuthInfo> selectPage(Map<String,Object> param);

	/**
	 * 根据传入参数查询
	 * @param params
	 * @return
	 */
	public JudicialAuthInfo selByApplyIdAndType(Map<String,Object> params);

}

