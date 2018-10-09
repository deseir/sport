package com.moerlong.carloan.modular.car.service.impl;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.entity.CarPeccancyInfo;
import com.moerlong.carloan.modular.car.dao.CarPeccancyInfoDao;
import com.moerlong.carloan.modular.car.service.CarPeccancyInfoService;

@Service
public class CarPeccancyInfoServiceImpl implements CarPeccancyInfoService{

	@Autowired
	CarPeccancyInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CarPeccancyInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(CarPeccancyInfo entity) {
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
	public void update(CarPeccancyInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CarPeccancyInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public CarPeccancyInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CarPeccancyInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CarPeccancyInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CarPeccancyInfo> pageList = mapper.selectPage(param);
		PageInfo<CarPeccancyInfo> pageInfo = new PageInfo<CarPeccancyInfo>(pageList);
		return pageInfo;
	}

}

