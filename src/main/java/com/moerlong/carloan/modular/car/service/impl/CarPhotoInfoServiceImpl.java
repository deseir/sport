package com.moerlong.carloan.modular.car.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.entity.CarPhotoInfo;
import com.moerlong.carloan.modular.car.dao.CarPhotoInfoDao;
import com.moerlong.carloan.modular.car.service.CarPhotoInfoService;

@Service
public class CarPhotoInfoServiceImpl implements CarPhotoInfoService{

	@Autowired
	CarPhotoInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CarPhotoInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			entity.setUpdateTime(new Date());
			this.update(entity);
		}else {
			entity.setCreateTime(new Date());
			entity.setIsDeleted(0);
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(CarPhotoInfo entity) {
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
	public void update(CarPhotoInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CarPhotoInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public CarPhotoInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CarPhotoInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CarPhotoInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CarPhotoInfo> pageList = mapper.selectPage(param);
		PageInfo<CarPhotoInfo> pageInfo = new PageInfo<CarPhotoInfo>(pageList);
		return pageInfo;
	}

	public List<CarPhotoInfo> selectByCarId(String carId){
		return mapper.selectByCarId(carId);
	}

	public void insertList(List<CarPhotoInfo> list){
		mapper.insertList(list);
	}

	@Override
	public void deleteByBigClassId(Long id) {
		mapper.deleteByBigClassId(id);
	}

	@Override
	public List<CarPhotoInfo> selectByBigClassId(Long id) {
		return mapper.selectByBigClassId(id);
	}
}

