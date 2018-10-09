package com.moerlong.carloan.modular.car.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moerlong.carloan.modular.car.dao.CarChassisSteeringMapper;
import com.moerlong.carloan.modular.car.entity.CarChassisSteering;
import com.moerlong.carloan.modular.car.service.CarChassisSteeringService;
@Service
public class CarChassisSteeringServiceImpl implements CarChassisSteeringService {
	@Autowired
	private CarChassisSteeringMapper mapper;
	
	@Override
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrupdate(CarChassisSteering record) {
		if (record.getId()!=null && this.selectById(record.getId())!=null) {
			this.update(record);
		}else {
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
	public void save(CarChassisSteering record) {
		mapper.save(record);
	}

	@Override
	public CarChassisSteering selectByApplyId(Long id) {
		return mapper.selectByApplyId(id);
	}

	@Override
	public CarChassisSteering selectById(Long id) {
		return mapper.selectById(id);
	}

	@Override
	@Transactional(rollbackFor=Throwable.class)
	public void update(CarChassisSteering record) {
		mapper.update(record);
	}

}
