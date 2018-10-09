package com.moerlong.carloan.modular.car.service;

import com.moerlong.carloan.modular.car.entity.CarSafetyEquipment;

public interface CarSafetyEquipmentService {
	/**
	 * 新增或修改
	 * @param record
	 */
	public void saveOrupdate(CarSafetyEquipment record);
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Long id);
	/**
	 * 新增
	 * @param record
	 */
	public void save(CarSafetyEquipment record);
	/**
	 * 根据ApplyId查询
	 * @param id
	 * @return
	 */
	public CarSafetyEquipment selectByApplyId(Long id);
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public CarSafetyEquipment selectById(Long id);
	/**
	 * 修改
	 * @param record
	 */
    public void update(CarSafetyEquipment record);
}