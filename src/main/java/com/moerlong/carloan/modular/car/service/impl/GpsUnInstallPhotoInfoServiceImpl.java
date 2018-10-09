package com.moerlong.carloan.modular.car.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.dao.GpsUnInstallDao;
import com.moerlong.carloan.modular.car.entity.CarGpsUnInstallPhotoInfo;
import com.moerlong.carloan.modular.car.service.GpsUnInstallPhotoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class GpsUnInstallPhotoInfoServiceImpl implements GpsUnInstallPhotoInfoService{

	@Autowired
	GpsUnInstallDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CarGpsUnInstallPhotoInfo entity) {
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
	public void save(CarGpsUnInstallPhotoInfo entity) {
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
	public void update(CarGpsUnInstallPhotoInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CarGpsUnInstallPhotoInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public CarGpsUnInstallPhotoInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CarGpsUnInstallPhotoInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CarGpsUnInstallPhotoInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CarGpsUnInstallPhotoInfo> pageList = mapper.selectPage(param);
		PageInfo<CarGpsUnInstallPhotoInfo> pageInfo = new PageInfo<CarGpsUnInstallPhotoInfo>(pageList);
		return pageInfo;
	}

	public List<CarGpsUnInstallPhotoInfo> selectByCarId(String carId){
		return mapper.selectByCarId(carId);
	}

	public void insertList(List<CarGpsUnInstallPhotoInfo> list){
		mapper.insertList(list);
	}
}

