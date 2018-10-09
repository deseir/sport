package com.moerlong.carloan.modular.car.dao;

import com.moerlong.carloan.modular.car.entity.CarWheelBrakes;

public interface CarWheelBrakesMapper {
	/**
	 * 删除
	 */
	public void delete(Long id);
	/**
	 * 新增
	 * @param record
	 */
	public void save(CarWheelBrakes record);
	/**
	 * 根据ApplyId查询
	 * @param id
	 * @return
	 */
	public CarWheelBrakes selectByApplyId(Long id);
	/**
	 * 根据Id查询
	 * @param id
	 * @return
	 */
	public CarWheelBrakes selectById(Long id);
	/**
	 * 修改
	 * @param record
	 */
    public void update(CarWheelBrakes record);
}