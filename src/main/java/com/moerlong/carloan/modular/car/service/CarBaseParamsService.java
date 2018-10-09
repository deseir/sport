package com.moerlong.carloan.modular.car.service;

import com.moerlong.carloan.modular.car.entity.CarBaseParams;

public interface CarBaseParamsService {

	public int save(CarBaseParams record);

	public int insertSelective(CarBaseParams record);

	public int deleteById(Long id);

	public int updateWithoutNull(CarBaseParams record);

	public int update(CarBaseParams record);

	public CarBaseParams selectById(Long id);

	public CarBaseParams selectByApplyId(Long applyId);

}

