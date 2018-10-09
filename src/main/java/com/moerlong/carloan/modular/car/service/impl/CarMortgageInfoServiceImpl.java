package com.moerlong.carloan.modular.car.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.dao.CarMortgageInfoDao;
import com.moerlong.carloan.modular.car.entity.CarMortgageInfo;
import com.moerlong.carloan.modular.car.service.CarMortgageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CarMortgageInfoServiceImpl implements CarMortgageInfoService {

	@Autowired
    CarMortgageInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CarMortgageInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(CarMortgageInfo entity) {
		entity.setCreateTime(new Date());
		entity.setIsDeleted(0);
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
	public void update(CarMortgageInfo entity) {
		entity.setUpdateTime(new Date());
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CarMortgageInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public CarMortgageInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CarMortgageInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CarMortgageInfo> selectPage(int pageSize, int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CarMortgageInfo> pageList = mapper.selectPage(param);
		PageInfo<CarMortgageInfo> pageInfo = new PageInfo<CarMortgageInfo>(pageList);
		return pageInfo;
	}

}

