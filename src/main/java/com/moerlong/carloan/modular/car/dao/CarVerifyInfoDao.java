package com.moerlong.carloan.modular.car.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.car.entity.CarVerifyInfo;

public interface CarVerifyInfoDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(CarVerifyInfo entity);

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
	public void update(CarVerifyInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CarVerifyInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CarVerifyInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CarVerifyInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<CarVerifyInfo> selectPage(Map<String,Object> param);

	/**
	 * 根据carId查询验车信息
	 * @param carId
	 * @return
	 */
	public CarVerifyInfo selectByCarId(Long carId);

}

