package com.moerlong.carloan.modular.car.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moerlong.carloan.modular.car.dao.CarWheelBrakesMapper;
import com.moerlong.carloan.modular.car.entity.CarWheelBrakes;
import com.moerlong.carloan.modular.car.service.CarWheelBrakesService;
@Service
public class CarWheelBrakesServiceImpl implements CarWheelBrakesService {
	@Autowired
	private CarWheelBrakesMapper mapper;
	@Override
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrupdate(CarWheelBrakes record) {
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
	public void save(CarWheelBrakes record) {
		mapper.save(record);
	}

	@Override
	public CarWheelBrakes selectByApplyId(Long id) {
		return mapper.selectByApplyId(id);
	}

	@Override
	public CarWheelBrakes selectById(Long id) {
		return mapper.selectById(id);
	}

	@Override
	@Transactional(rollbackFor=Throwable.class)
	public void update(CarWheelBrakes record) {
		mapper.update(record);
	}

}
