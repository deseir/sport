package com.moerlong.carloan.modular.car.service.impl;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.entity.CarTransferInfo;
import com.moerlong.carloan.modular.car.dao.CarTransferInfoDao;
import com.moerlong.carloan.modular.car.service.CarTransferInfoService;

@Service
public class CarTransferInfoServiceImpl implements CarTransferInfoService{

	@Autowired
	CarTransferInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CarTransferInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(CarTransferInfo entity) {
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
	public void update(CarTransferInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CarTransferInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public CarTransferInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CarTransferInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CarTransferInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CarTransferInfo> pageList = mapper.selectPage(param);
		PageInfo<CarTransferInfo> pageInfo = new PageInfo<CarTransferInfo>(pageList);
		return pageInfo;
	}

}

