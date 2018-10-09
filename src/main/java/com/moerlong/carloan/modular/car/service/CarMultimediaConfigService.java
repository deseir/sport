package com.moerlong.carloan.modular.car.service;

import com.moerlong.carloan.modular.car.entity.CarMultimediaConfig;

import java.util.List;

public interface CarMultimediaConfigService {

    /**
     * 保存或更新
     * @param record
     */
    public void saveOrUpdate(CarMultimediaConfig record);

    /**
     * 保存
     * @param record
     */
    void save(CarMultimediaConfig record);

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
    void update(CarMultimediaConfig record);

    /**
     * 更新非空字段
     * @param record
     */
    void updateWithOutNull(CarMultimediaConfig record);

    /**
     * 按id查询
     * @param id
     * @return
     */
    CarMultimediaConfig selectById(Long id);

    CarMultimediaConfig selectByApplyId(Long applyId);

    /**
     * 查询所有
     * @return
     */

    List<CarMultimediaConfig> listAll();
}
