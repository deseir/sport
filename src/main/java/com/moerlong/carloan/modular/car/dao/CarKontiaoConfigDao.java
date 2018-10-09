package com.moerlong.carloan.modular.car.dao;

import com.moerlong.carloan.modular.car.entity.CarKontiaoConfig;
import java.util.List;

public interface CarKontiaoConfigDao {

    /**
     * 保存
     * @param record
     */
    void save(CarKontiaoConfig record);
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
    void update(CarKontiaoConfig record);
    /**
     * 更新非空字段
     * @param record
     */
    void updateWithOutNull(CarKontiaoConfig record);
    /**
     * 按id查询
     * @param id
     * @return
     */

    CarKontiaoConfig selectById(Long id);

    CarKontiaoConfig selectByApplyId(Long applyId);

    /**
     * 查询所有
     * @return
     */
    List<CarKontiaoConfig> listAll();

}