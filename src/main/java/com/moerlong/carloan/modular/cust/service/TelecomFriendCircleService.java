package com.moerlong.carloan.modular.cust.service;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.TelecomFriendCircle;

public interface TelecomFriendCircleService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(TelecomFriendCircle entity);
	
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
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<TelecomFriendCircle> selectPage(int pageSize,int pageNum, Map<String,Object> param);

	/**
	 * 根据applyId 和type 查询
	 * @param param
	 * @return
	 */
	public List<TelecomFriendCircle> selectByApplyIdAndType (Map<String,Object> param);

}

