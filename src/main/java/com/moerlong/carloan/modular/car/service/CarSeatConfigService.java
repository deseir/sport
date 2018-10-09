package com.moerlong.carloan.modular.car.service;

import com.moerlong.carloan.modular.car.entity.CarSeatConfig;

import java.util.List;

public interface CarSeatConfigService {
    /**
     * 保存或更新
     * @param record
     */
    public void saveOrUpdate(CarSeatConfig record);

    /**
     * 保存
     * @param record
     */
    void save(CarSeatConfig record);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     * 逻辑删除
     * @param id
     */
    void deleteLogic(Long id);

    /**
     * 更新
     * @param record
     */
    void update(CarSeatConfig record);

    /**
     * 更新非空字段
     * @param record
     */
    void updateWithOutNull(CarSeatConfig record);

    /**
     * 按id查询
     * @param id
     * @return
     */
    CarSeatConfig selectById(Long id);

    CarSeatConfig selectByApplyId(Long applyId);

    /**
     * 查询所有
     * @return
     */

    List<CarSeatConfig> listAll();
}
