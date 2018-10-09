package com.moerlong.carloan.modular.cust.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.cust.entity.TelecomAuthInfo;

public interface TelecomAuthInfoDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(TelecomAuthInfo entity);

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
	public void update(TelecomAuthInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(TelecomAuthInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public TelecomAuthInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<TelecomAuthInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<TelecomAuthInfo> selectPage(Map<String,Object> param);

	/**
	 * 根据applyId 和 type 查询
	 * @param param
	 * @return
	 */
	public TelecomAuthInfo selectByApplyIdAndType (Map<String,Object> param);

}

