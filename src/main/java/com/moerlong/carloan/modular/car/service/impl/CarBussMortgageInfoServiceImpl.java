package com.moerlong.carloan.modular.car.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.List;

import com.moerlong.carloan.modular.car.entity.CarBussMortgageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.dao.CarBussMortgageInfoDao;
import com.moerlong.carloan.modular.car.service.CarBussMortgageInfoService;

@Service
public class CarBussMortgageInfoServiceImpl implements CarBussMortgageInfoService {

	@Autowired
    CarBussMortgageInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CarBussMortgageInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.updateWithOutNull(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(CarBussMortgageInfo entity) {
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
	public void update(CarBussMortgageInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CarBussMortgageInfo entity) {
		entity.setUpdateTime(new Date());
		mapper.updateWithOutNull(entity);
	}
	
	public CarBussMortgageInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CarBussMortgageInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CarBussMortgageInfo> selectPage(int pageSize, int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CarBussMortgageInfo> pageList = mapper.selectPage(param);
		PageInfo<CarBussMortgageInfo> pageInfo = new PageInfo<CarBussMortgageInfo>(pageList);
		return pageInfo;
	}

	@Override
	public CarBussMortgageInfo selectByApplyId(Long applyid) {
		
		return mapper.selectByApplyId(applyid);
	}

}

