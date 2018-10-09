package com.moerlong.carloan.modular.car.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.car.entity.CarBussInsureInfo;

public interface CarBussInsureInfoDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(CarBussInsureInfo entity);

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
	public void update(CarBussInsureInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CarBussInsureInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CarBussInsureInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CarBussInsureInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<CarBussInsureInfo> selectPage(Map<String,Object> param);
	public CarBussInsureInfo findCarBussInsureInfoByCarId(Long carId);
}

