package com.moerlong.carloan.modular.car.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.entity.CarGpsInfo;
import com.moerlong.carloan.modular.car.dao.CarGpsInfoDao;
import com.moerlong.carloan.modular.car.service.CarGpsInfoService;

@Service
public class CarGpsInfoServiceImpl implements CarGpsInfoService{

	@Autowired
	CarGpsInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CarGpsInfo entity) {
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
	public void save(CarGpsInfo entity) {
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
	public void update(CarGpsInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CarGpsInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public CarGpsInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CarGpsInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CarGpsInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CarGpsInfo> pageList = mapper.selectPage(param);
		PageInfo<CarGpsInfo> pageInfo = new PageInfo<CarGpsInfo>(pageList);
		return pageInfo;
	}

	public CarGpsInfo selectByApplyId (Map<String,Object> param){
		return mapper.selectByApplyId(param);
	}

}

