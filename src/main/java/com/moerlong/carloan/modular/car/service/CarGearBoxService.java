package com.moerlong.carloan.modular.car.service;

import com.moerlong.carloan.modular.car.entity.CarGearBox;

public interface CarGearBoxService {
	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(CarGearBox record);
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Long id);
	/**
	 * 根据Id查询
	 * @param id
	 * @return
	 */
	public CarGearBox selectByApplyId(Long id);
	/**
     * 根据ID查询 
     * @param id
     * @return
     */
    public CarGearBox selectById(Long id);
    /**
     * 新增
     * @param record
     * @return
     */
    public void save(CarGearBox record);
    /**
     * 修改
     * @param record
     */
    public void update(CarGearBox record);
}
