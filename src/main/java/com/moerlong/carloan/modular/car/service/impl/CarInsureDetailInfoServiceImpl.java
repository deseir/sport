package com.moerlong.carloan.modular.car.service.impl;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.entity.CarInsureDetailInfo;
import com.moerlong.carloan.modular.car.dao.CarInsureDetailInfoDao;
import com.moerlong.carloan.modular.car.service.CarInsureDetailInfoService;

@Service
public class CarInsureDetailInfoServiceImpl implements CarInsureDetailInfoService{

	@Autowired
	CarInsureDetailInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CarInsureDetailInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(CarInsureDetailInfo entity) {
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
	public void update(CarInsureDetailInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CarInsureDetailInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public CarInsureDetailInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CarInsureDetailInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CarInsureDetailInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CarInsureDetailInfo> pageList = mapper.selectPage(param);
		PageInfo<CarInsureDetailInfo> pageInfo = new PageInfo<CarInsureDetailInfo>(pageList);
		return pageInfo;
	}

}

