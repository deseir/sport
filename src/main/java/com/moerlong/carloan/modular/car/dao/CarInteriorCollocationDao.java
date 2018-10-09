package com.moerlong.carloan.modular.car.dao;

import com.moerlong.carloan.modular.car.entity.CarBoliConfig;
import com.moerlong.carloan.modular.car.entity.CarInteriorCollocation;
import java.util.List;

public interface CarInteriorCollocationDao {
    /**
     * 保存
     * @param record
     */
    void save(CarInteriorCollocation record);
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
    void update(CarInteriorCollocation record);
    /**
     * 更新非空字段
     * @param record
     */
    void updateWithOutNull(CarInteriorCollocation record);

    /**
     * 按id查询
     * @param id
     * @return
     */
    CarInteriorCollocation selectById(Long id);

    CarInteriorCollocation selectByApplyId(Long applyId);

    /**
     * 查询所有
     * @return
     */
    List<CarInteriorCollocation> listAll();

}