package com.moerlong.carloan.modular.car.dao;

import com.moerlong.carloan.modular.car.entity.CarEngineConfig;

public interface CarEngineConfigDao {

    CarEngineConfig selectByPrimaryKey(Long id);
    CarEngineConfig selectByApplyId(Long applyId);

    int deleteByPrimaryKey(Long id);

    int save(CarEngineConfig record);

    int insertSelective(CarEngineConfig record);

    int updateWithoutNull(CarEngineConfig record);

    int update(CarEngineConfig record);
}