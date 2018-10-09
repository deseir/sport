package com.moerlong.carloan.modular.car.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.entity.CarGpsDetailInfo;
import com.moerlong.carloan.modular.car.dao.CarGpsDetailInfoDao;
import com.moerlong.carloan.modular.car.service.CarGpsDetailInfoService;

@Service
public class CarGpsDetailInfoServiceImpl implements CarGpsDetailInfoService{

	@Autowired
	CarGpsDetailInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CarGpsDetailInfo entity) {
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
	public void save(CarGpsDetailInfo entity) {
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
	public void update(CarGpsDetailInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CarGpsDetailInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public CarGpsDetailInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CarGpsDetailInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CarGpsDetailInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CarGpsDetailInfo> pageList = mapper.selectPage(param);
		PageInfo<CarGpsDetailInfo> pageInfo = new PageInfo<CarGpsDetailInfo>(pageList);
		return pageInfo;
	}

	@Override
	public List<CarGpsDetailInfo> selectByCarId(Long carid) {
		// TODO Auto-generated method stub
		return mapper.selectByCarId(carid);
	}

	public void deleteByIds(List list){
		mapper.deleteByIds(list);
	}

}

