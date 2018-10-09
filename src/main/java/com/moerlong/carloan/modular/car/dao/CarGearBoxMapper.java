package com.moerlong.carloan.modular.car.dao;


import com.moerlong.carloan.modular.car.entity.CarGearBox;

public interface CarGearBoxMapper {

	/**
	 * 删除
	 * @param id
	 * @return
	 */
    public void delete(Long id);
    /**
     * 新增
     * @param record
     * @return
     */
    public void save(CarGearBox record);
    /**
     * 根据applyId查询
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
     * 修改
     * @param record
     */
    public void update(CarGearBox record);
}