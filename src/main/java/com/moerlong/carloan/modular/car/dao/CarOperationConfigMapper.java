package com.moerlong.carloan.modular.car.dao;


import com.moerlong.carloan.modular.car.entity.CarOperationConfig;

public interface CarOperationConfigMapper {

	/**
	 * 删除
	 * @param id
	 */
    public void delete(Long id);
    /**
     * 新增
     * @param record
     */
    public void save(CarOperationConfig record);
    /**
     * 根据ApplyId查询
     * @param id
     * @return
     */
    public CarOperationConfig selectByApplyId(Long id);
    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public CarOperationConfig selectById(Long id);
    /**
     * 修改
     * @param record
     */
    public void update(CarOperationConfig record);
}