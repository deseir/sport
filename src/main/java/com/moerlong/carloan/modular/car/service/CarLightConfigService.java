package com.moerlong.carloan.modular.car.service;

import com.moerlong.carloan.modular.car.entity.CarLightConfig;

import java.util.List;

public interface CarLightConfigService {
    /**
     * 保存或更新
     * @param record
     */

    void saveOrUpdate(CarLightConfig record);
    /**
     * 保存
     * @param record
     */
    void save(CarLightConfig record);

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
    void update(CarLightConfig record);

    /**
     * 更新非空字段
     * @param record
     */
    void updateWithOutNull(CarLightConfig record);

    /**
     * 按id查询
     * @param id
     * @return
     */
    CarLightConfig selectById(Long id);

    CarLightConfig selectByApplyId(Long applyId);
    /**
     * 查询所有
     * @return
     */
    List<CarLightConfig> listAll();
}
