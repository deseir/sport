package com.moerlong.carloan.modular.loan.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.loan.entity.SupplementInfo;

public interface SupplementInfoDao {

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
	 * @param param	查询参数
	 * @return
	 */
	public List<SupplementInfo> selectPage(Map<String,Object> param);

	/**
	 * 根据applyId查询
	 * @param param
	 * @return
	 */
	public SupplementInfo selectByApplyId (Map<String,Object> param);

}

