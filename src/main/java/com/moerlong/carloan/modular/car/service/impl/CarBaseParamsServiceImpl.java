package com.moerlong.carloan.modular.car.service.impl;

import com.moerlong.carloan.modular.car.dao.CarBaseParamsDao;
import com.moerlong.carloan.modular.car.entity.CarBaseParams;
import com.moerlong.carloan.modular.car.service.CarBaseParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarBaseParamsServiceImpl implements CarBaseParamsService {

	@Autowired
	CarBaseParamsDao mapper;
	

	@Transactional(rollbackFor=Throwable.class)
	public int save(CarBaseParams record){
		return mapper.save(record);
	}

	@Transactional(rollbackFor=Throwable.class)
	public int insertSelective(CarBaseParams record){
		return mapper.insertSelective(record);
	}

	@Transactional(rollbackFor=Throwable.class)
	public int deleteById(Long id){
		return mapper.deleteById(id);
	}

	@Transactional(rollbackFor=Throwable.class)
	public int updateWithoutNull(CarBaseParams record){
		return mapper.updateWithoutNull(record);
	}

	@Transactional(rollbackFor=Throwable.class)
	public int update(CarBaseParams record){
		return mapper.update(record);
	}

	public CarBaseParams selectById(Long id){
		return mapper.selectById(id);
	}

	public CarBaseParams selectByApplyId(Long applyId){
		return mapper.selectByApplyId(applyId);
	}

}

