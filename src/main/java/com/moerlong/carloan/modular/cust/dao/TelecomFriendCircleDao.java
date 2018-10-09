package com.moerlong.carloan.modular.cust.dao;

import com.moerlong.carloan.modular.cust.entity.TelecomFriendCircle;

import java.util.List;
import java.util.Map;

public interface TelecomFriendCircleDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(TelecomFriendCircle entity);

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
	public void update(TelecomFriendCircle entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(TelecomFriendCircle entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public TelecomFriendCircle selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<TelecomFriendCircle> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<TelecomFriendCircle> selectPage(Map<String,Object> param);

	/**
	 * 根据applyId 和type 查询
	 * @param param
	 * @return
	 */
	public List<TelecomFriendCircle> selectByApplyIdAndType (Map<String,Object> param);
}

