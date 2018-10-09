package com.moerlong.carloan.modular.cust.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.cust.entity.LivenessAuthInfo;

public interface LivenessAuthInfoDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(LivenessAuthInfo entity);

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
	public void update(LivenessAuthInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(LivenessAuthInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public LivenessAuthInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<LivenessAuthInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<LivenessAuthInfo> selectPage(Map<String,Object> param);

	/**
	 * 根据applyId 和 type 查询
	 * @param param
	 * @return
	 */
	public LivenessAuthInfo selectByApplyIdAndType (Map<String,Object> param);

}

