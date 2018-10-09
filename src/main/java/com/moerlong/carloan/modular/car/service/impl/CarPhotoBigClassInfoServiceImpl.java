package com.moerlong.carloan.modular.car.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.dao.CarPhotoBigClassInfoDao;
import com.moerlong.carloan.modular.car.dao.CarPhotoBigClassInfoDao;
import com.moerlong.carloan.modular.car.entity.CarPhotoBigClassInfo;
import com.moerlong.carloan.modular.car.entity.vo.CarPhotoBigClassInfoVo;
import com.moerlong.carloan.modular.car.service.CarPhotoBigClassInfoService;
import com.moerlong.carloan.modular.car.service.CarPhotoBigClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CarPhotoBigClassInfoServiceImpl implements CarPhotoBigClassInfoService {


	@Autowired
	CarPhotoBigClassInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CarPhotoBigClassInfo entity) {
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
	public void save(CarPhotoBigClassInfo entity) {
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
	public void update(CarPhotoBigClassInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CarPhotoBigClassInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public CarPhotoBigClassInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CarPhotoBigClassInfo> listAll() {
		return mapper.listAll();
	}

	public PageInfo<CarPhotoBigClassInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CarPhotoBigClassInfo> pageList = mapper.selectPage(param);
		PageInfo<CarPhotoBigClassInfo> pageInfo = new PageInfo<CarPhotoBigClassInfo>(pageList);
		return pageInfo;
	}

	@Override
	public void saveBigClass(CarPhotoBigClassInfoVo entity) {
		mapper.saveBigClass(entity);
	}
	@Override
	public List<CarPhotoBigClassInfo> findByCarId(Long id) {
		return mapper.findByCarId(id);
	}
}

