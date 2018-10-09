package com.moerlong.carloan.modular.car.service;


import com.moerlong.carloan.modular.car.entity.CarChassisSteering;

public interface CarChassisSteeringService {
	/**
	 * 添加或修改
	 * @param record
	 */
	public void saveOrupdate(CarChassisSteering record);
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Long id);
	/**
	 * 保存
	 * @param record
	 */
    public void save(CarChassisSteering record);

    /**
     * 根据applyId查询
     * @param id
     * @return
     */
    public CarChassisSteering selectByApplyId(Long id);
    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public CarChassisSteering selectById(Long id);
    /**
     * 修改
     * @param record
     * @return
     */
    public void update(CarChassisSteering record);

}