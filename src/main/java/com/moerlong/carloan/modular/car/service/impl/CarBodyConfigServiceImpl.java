package com.moerlong.carloan.modular.car.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.dao.CarBodyConfigDao;
import com.moerlong.carloan.modular.car.dao.CarBussInsureInfoDao;
import com.moerlong.carloan.modular.car.entity.CarBodyConfig;
import com.moerlong.carloan.modular.car.entity.CarBussInsureInfo;
import com.moerlong.carloan.modular.car.service.CarBodyConfigService;
import com.moerlong.carloan.modular.car.service.CarBussInsureInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class CarBodyConfigServiceImpl implements CarBodyConfigService {

	@Autowired
	CarBodyConfigDao mapper;



	public CarBodyConfig selectByPrimaryKey(Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public CarBodyConfig selectByApplyId(Long id){
		return mapper.selectByApplyId(id);
	}

	@Transactional(rollbackFor=Throwable.class)
	public int deleteByPrimaryKey(Long id){
		return mapper.deleteByPrimaryKey(id);
	}

	@Transactional(rollbackFor=Throwable.class)
	public int save(CarBodyConfig record){
		return mapper.save(record);
	}

	@Transactional(rollbackFor=Throwable.class)
	public int insertSelective(CarBodyConfig record){
		return mapper.insertSelective(record);
	}

	@Transactional(rollbackFor=Throwable.class)
	public int updateWithoutNull(CarBodyConfig record){
		return mapper.updateWithoutNull(record);
	}

	@Transactional(rollbackFor=Throwable.class)
	public int updateByPrimaryKey(CarBodyConfig record){
		return mapper.updateByPrimaryKey(record);
	}


}

