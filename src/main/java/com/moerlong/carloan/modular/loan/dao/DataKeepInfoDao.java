package com.moerlong.carloan.modular.loan.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.loan.entity.DataKeepInfo;

public interface DataKeepInfoDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(DataKeepInfo entity);

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
	public void update(DataKeepInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(DataKeepInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public DataKeepInfo selectById(Long id);
	/**
	 * 按applyId查询
	 * @param id
	 * @return
	 */
	public DataKeepInfo selectByApplyId(Long id);
	/**
	 * 查询所有
	 * @return
	 */
	public List<DataKeepInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<DataKeepInfo> selectPage(Map<String,Object> param);

}

