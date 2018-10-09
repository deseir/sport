package com.moerlong.carloan.modular.car.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moerlong.carloan.modular.car.dao.CarGearBoxMapper;
import com.moerlong.carloan.modular.car.entity.CarGearBox;
import com.moerlong.carloan.modular.car.service.CarGearBoxService;
@Service
public class CarGearBoxServiceImpl implements CarGearBoxService {
	@Autowired
	private CarGearBoxMapper mapper;
	
	@Override
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CarGearBox record) {
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
	public CarGearBox selectByApplyId(Long id) {
		return mapper.selectByApplyId(id);
	}

	@Override
	public CarGearBox selectById(Long id) {
		return mapper.selectById(id);
	}

	@Override
	@Transactional(rollbackFor=Throwable.class)
	public void save(CarGearBox record) {
		mapper.save(record);
	}

	@Override
	@Transactional(rollbackFor=Throwable.class)
	public void update(CarGearBox record) {
		mapper.update(record);
	}

}
