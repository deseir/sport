package com.moerlong.carloan.modular.car.service.impl;

import com.moerlong.carloan.modular.car.dao.CarBodyConfigDao;
import com.moerlong.carloan.modular.car.dao.CarEngineConfigDao;
import com.moerlong.carloan.modular.car.entity.CarBodyConfig;
import com.moerlong.carloan.modular.car.entity.CarEngineConfig;
import com.moerlong.carloan.modular.car.service.CarBodyConfigService;
import com.moerlong.carloan.modular.car.service.CarEngineConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarEngineConfigServiceImpl implements CarEngineConfigService {

	@Autowired
	CarEngineConfigDao mapper;

	public CarEngineConfig selectByPrimaryKey(Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public CarEngineConfig selectByApplyId(Long applyId){
		return mapper.selectByApplyId(applyId);
	}

	@Transactional(rollbackFor=Throwable.class)
	public int deleteByPrimaryKey(Long id){
		return mapper.deleteByPrimaryKey(id);
	}

	@Transactional(rollbackFor=Throwable.class)
	public int save(CarEngineConfig record){
		return mapper.save(record);
	}

	@Transactional(rollbackFor=Throwable.class)
	public int insertSelective(CarEngineConfig record){
		return mapper.insertSelective(record);
	}

	@Transactional(rollbackFor=Throwable.class)
	public int updateWithoutNull(CarEngineConfig record){
		return mapper.updateWithoutNull(record);
	}

	@Transactional(rollbackFor=Throwable.class)
	public int update(CarEngineConfig record){
		return mapper.update(record);
	}


}

