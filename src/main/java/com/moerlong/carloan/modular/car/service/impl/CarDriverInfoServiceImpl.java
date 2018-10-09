package com.moerlong.carloan.modular.car.service.impl;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.entity.CarDriverInfo;
import com.moerlong.carloan.modular.car.dao.CarDriverInfoDao;
import com.moerlong.carloan.modular.car.service.CarDriverInfoService;

@Service
public class CarDriverInfoServiceImpl implements CarDriverInfoService{

	@Autowired
	CarDriverInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CarDriverInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(CarDriverInfo entity) {
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
	public void update(CarDriverInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CarDriverInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public CarDriverInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CarDriverInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CarDriverInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CarDriverInfo> pageList = mapper.selectPage(param);
		PageInfo<CarDriverInfo> pageInfo = new PageInfo<CarDriverInfo>(pageList);
		return pageInfo;
	}

}

