package com.moerlong.carloan.modular.car.service;

import com.moerlong.carloan.modular.car.entity.CarBoliConfig;

import java.util.List;

public interface CarBoliConfigService {

    /**
     * 保存或更新
     * @param record
     */
    public void saveOrUpdate(CarBoliConfig record);

    /**
     * 保存
     * @param record
     */
    void save(CarBoliConfig record);

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
    void update(CarBoliConfig record);
    /**
     * 更新非空字段
     * @param record
     */

    void updateWithOutNull(CarBoliConfig record);

    /**
     * 按id查询
     * @param id
     * @return
     */

    CarBoliConfig selectById(Long id);

    CarBoliConfig selectByApplyId(Long applyId);

    /**
     * 查询所有
     * @return
     */

    List<CarBoliConfig> listAll();
}
