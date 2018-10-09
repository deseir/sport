package com.moerlong.carloan.modular.car.service.impl;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.entity.CarBussInsureInfo;
import com.moerlong.carloan.modular.car.dao.CarBussInsureInfoDao;
import com.moerlong.carloan.modular.car.service.CarBussInsureInfoService;

@Service
public class CarBussInsureInfoServiceImpl implements CarBussInsureInfoService{

	@Autowired
	CarBussInsureInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CarBussInsureInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(CarBussInsureInfo entity) {
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
	public void update(CarBussInsureInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CarBussInsureInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public CarBussInsureInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CarBussInsureInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CarBussInsureInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CarBussInsureInfo> pageList = mapper.selectPage(param);
		PageInfo<CarBussInsureInfo> pageInfo = new PageInfo<CarBussInsureInfo>(pageList);
		return pageInfo;
	}

}

