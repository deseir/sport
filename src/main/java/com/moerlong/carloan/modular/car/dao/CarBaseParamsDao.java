package com.moerlong.carloan.modular.car.dao;

import com.moerlong.carloan.modular.car.entity.CarBaseParams;

public interface CarBaseParamsDao {

    int save(CarBaseParams record);

    int insertSelective(CarBaseParams record);

    int deleteById(Long id);

    int updateWithoutNull(CarBaseParams record);

    int update(CarBaseParams record);

    CarBaseParams selectById(Long id);

    CarBaseParams selectByApplyId(Long applyId);


}