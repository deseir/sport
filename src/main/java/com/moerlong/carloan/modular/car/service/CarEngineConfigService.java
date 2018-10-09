package com.moerlong.carloan.modular.car.service;

import com.moerlong.carloan.modular.car.entity.CarEngineConfig;

public interface CarEngineConfigService {

	public CarEngineConfig selectByPrimaryKey(Long id);

	public CarEngineConfig selectByApplyId(Long applyId);

	public int deleteByPrimaryKey(Long id);

	public int save(CarEngineConfig record);

	public int insertSelective(CarEngineConfig record);

	public int updateWithoutNull(CarEngineConfig record);

	public int update(CarEngineConfig record);

}

