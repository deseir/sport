package com.moerlong.carloan.modular.car.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.entity.CarVerifyInfo;
import com.moerlong.carloan.modular.car.dao.CarVerifyInfoDao;
import com.moerlong.carloan.modular.car.service.CarVerifyInfoService;

@Service
public class CarVerifyInfoServiceImpl implements CarVerifyInfoService{

	@Autowired
	CarVerifyInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CarVerifyInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			entity.setUpdateTime(new Date());
			this.updateWithOutNull(entity);
		}else {
			entity.setCreateTime(new Date());
			entity.setIsDeleted(0);
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(CarVerifyInfo entity) {
		mapper.save(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void delete(Long id) {
		mapper.delete(id);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void deleteLogic(Long id){
		mapper.deleteLogic(id);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void update(CarVerifyInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CarVerifyInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public CarVerifyInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CarVerifyInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CarVerifyInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CarVerifyInfo> pageList = mapper.selectPage(param);
		PageInfo<CarVerifyInfo> pageInfo = new PageInfo<CarVerifyInfo>(pageList);
		return pageInfo;
	}

	public CarVerifyInfo selectByCarId(Long carId){
		return mapper.selectByCarId(carId);
	}
}

