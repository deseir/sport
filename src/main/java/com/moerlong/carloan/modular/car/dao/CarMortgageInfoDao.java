package com.moerlong.carloan.modular.car.dao;

import com.moerlong.carloan.modular.car.entity.CarMortgageInfo;

import java.util.List;
import java.util.Map;

public interface CarMortgageInfoDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(CarMortgageInfo entity);

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
	public void update(CarMortgageInfo entity);

	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CarMortgageInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CarMortgageInfo selectById(Long id);

	/**
	 * 查询所有
	 * @return
	 */
	public List<CarMortgageInfo> listAll();

	/**
	 * 分页查询
	 * @param param	查询参数
	 * @return
	 */
	public List<CarMortgageInfo> selectPage(Map<String, Object> param);
	public  void saveAll(List<CarMortgageInfo> list);
	public  void updateAll(List<CarMortgageInfo> list);
	public List<CarMortgageInfo> findCarMortgageInfoByCarId(Long carId);
}

