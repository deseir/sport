package com.moerlong.carloan.modular.car.service;

import com.moerlong.carloan.modular.car.entity.CarBodyConfig;

public interface CarBodyConfigService {

	public CarBodyConfig selectByPrimaryKey(Long id);

	public CarBodyConfig selectByApplyId(Long id);

	public int deleteByPrimaryKey(Long id);

	public int save(CarBodyConfig record);

	public int insertSelective(CarBodyConfig record);

	public int updateWithoutNull(CarBodyConfig record);

	public int updateByPrimaryKey(CarBodyConfig record);

}

