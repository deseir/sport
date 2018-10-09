package com.moerlong.carloan.modular.car.dao;

import java.util.List;

import com.moerlong.carloan.modular.car.entity.CarBodyConfig;
import org.apache.ibatis.annotations.Param;

public interface CarBodyConfigDao {

    CarBodyConfig selectByPrimaryKey(Long id);

    CarBodyConfig selectByApplyId(Long id);

    int deleteByPrimaryKey(Long id);

    int save(CarBodyConfig record);

    int insertSelective(CarBodyConfig record);

    int updateWithoutNull(CarBodyConfig record);

    int updateByPrimaryKey(CarBodyConfig record);
}