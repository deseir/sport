package com.moerlong.carloan.modular.car.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moerlong.carloan.modular.car.dao.CarOperationConfigMapper;
import com.moerlong.carloan.modular.car.entity.CarOperationConfig;
import com.moerlong.carloan.modular.car.service.CarOperationConfigService;
@Service
public class CarOperationConfigServiceImpl implements CarOperationConfigService {
	@Autowired
	private CarOperationConfigMapper mapper;
	@Override
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CarOperationConfig record) {
		if (record.getId()!=null && this.selectById(record.getId())!=null) {
			this.update(record);
		} else {
			this.save(record);
		}
	}

	@Override
	@Transactional(rollbackFor=Throwable.class)
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Override
	@Transactional(rollbackFor=Throwable.class)
	public void save(CarOperationConfig record) {
		mapper.save(record);
	}

	@Override
	
	public CarOperationConfig selectByApplyId(Long id) {
		return mapper.selectByApplyId(id);
	}

	@Override
	public CarOperationConfig selectById(Long id) {
		return mapper.selectById(id);
	}

	@Override
	@Transactional(rollbackFor=Throwable.class)
	public void update(CarOperationConfig record) {
		mapper.update(record);
	}

}
