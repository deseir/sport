package com.moerlong.carloan.modular.car.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.car.entity.CarTrafficInsureInfo;

public interface CarTrafficInsureInfoDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(CarTrafficInsureInfo entity);

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
	public void update(CarTrafficInsureInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CarTrafficInsureInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CarTrafficInsureInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CarTrafficInsureInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<CarTrafficInsureInfo> selectPage(Map<String,Object> param);
	public CarTrafficInsureInfo findCarTrafficInsureInfoByCarId(Long carId);
}

