package com.moerlong.carloan.modular.car.service.impl;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.entity.CarTrafficInsureInfo;
import com.moerlong.carloan.modular.car.dao.CarTrafficInsureInfoDao;
import com.moerlong.carloan.modular.car.service.CarTrafficInsureInfoService;

@Service
public class CarTrafficInsureInfoServiceImpl implements CarTrafficInsureInfoService{

	@Autowired
	CarTrafficInsureInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CarTrafficInsureInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(CarTrafficInsureInfo entity) {
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
	public void update(CarTrafficInsureInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CarTrafficInsureInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public CarTrafficInsureInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CarTrafficInsureInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CarTrafficInsureInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CarTrafficInsureInfo> pageList = mapper.selectPage(param);
		PageInfo<CarTrafficInsureInfo> pageInfo = new PageInfo<CarTrafficInsureInfo>(pageList);
		return pageInfo;
	}

}

