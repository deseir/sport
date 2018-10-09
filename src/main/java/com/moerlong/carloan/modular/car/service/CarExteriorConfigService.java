package com.moerlong.carloan.modular.car.service;

import com.moerlong.carloan.modular.car.entity.CarExteriorConfig;

import java.util.List;

public interface CarExteriorConfigService {

    /**
     * 保存或更新
     * @param record
     */
    public void saveOrUpdate(CarExteriorConfig record);

    /**
     * 保存
     * @param record
     */
    void save(CarExteriorConfig record);

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
    void update(CarExteriorConfig record);

    /**
     * 更新非空字段
     * @param record
     */
    void updateWithOutNull(CarExteriorConfig record);
    /**
     * 按id查询
     * @param id
     * @return
     */
    CarExteriorConfig selectById(Long id);

    CarExteriorConfig selectByApplyId(Long applyId);
    /**
     * 查询所有
     * @return
     */

    List<CarExteriorConfig> listAll();
}
