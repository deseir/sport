package com.moerlong.carloan.modular.car.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.entity.CarPriceInfo;
import com.moerlong.carloan.modular.car.dao.CarPriceInfoDao;
import com.moerlong.carloan.modular.car.service.CarPriceInfoService;

@Service
public class CarPriceInfoServiceImpl implements CarPriceInfoService{

	@Autowired
	CarPriceInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CarPriceInfo entity) {
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
	public void save(CarPriceInfo entity) {
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
	public void update(CarPriceInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CarPriceInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public CarPriceInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CarPriceInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CarPriceInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CarPriceInfo> pageList = mapper.selectPage(param);
		PageInfo<CarPriceInfo> pageInfo = new PageInfo<CarPriceInfo>(pageList);
		return pageInfo;
	}

	public CarPriceInfo selByApplyId(Map<String,Object> param){
		return mapper.selByApplyId(param);
	}

}

