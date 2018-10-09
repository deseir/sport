package com.moerlong.carloan.modular.car.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moerlong.carloan.modular.car.dao.CarSafetyEquipmentMapper;
import com.moerlong.carloan.modular.car.entity.CarSafetyEquipment;
import com.moerlong.carloan.modular.car.service.CarSafetyEquipmentService;
@Service
public class CarSafetyEquipmentServiceImpl implements CarSafetyEquipmentService {
	
	@Autowired
	private CarSafetyEquipmentMapper mapper;
	@Override
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrupdate(CarSafetyEquipment record) {
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
	public void save(CarSafetyEquipment record) {
		mapper.save(record);
	}

	@Override
	public CarSafetyEquipment selectByApplyId(Long id) {
		return mapper.selectByApplyId(id);
	}

	@Override
	public CarSafetyEquipment selectById(Long id) {
		return mapper.selectById(id);
	}

	@Override
	@Transactional(rollbackFor=Throwable.class)
	public void update(CarSafetyEquipment record) {
		mapper.update(record);
	}

}
