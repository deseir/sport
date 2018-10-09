package com.moerlong.carloan.modular.car.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.car.entity.CarDetentionInfo;

public interface CarDetentionInfoDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(CarDetentionInfo entity);

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
	public void update(CarDetentionInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CarDetentionInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CarDetentionInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CarDetentionInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<CarDetentionInfo> selectPage(Map<String,Object> param);

	/**
	 *  根据applyId查询
	 * @param
	 * @return
	 */
	public CarDetentionInfo selectByApplyId(Long applyId);

}

