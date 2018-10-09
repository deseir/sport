package com.moerlong.carloan.modular.car.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.List;

import com.moerlong.carloan.modular.car.entity.CarInfo;
import com.moerlong.carloan.modular.car.service.CarInfoService;
import com.moerlong.carloan.modular.cust.entity.CustomerInfo;
import com.moerlong.carloan.modular.cust.service.CustomerInfoService;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.entity.CarDetentionInfo;
import com.moerlong.carloan.modular.car.dao.CarDetentionInfoDao;
import com.moerlong.carloan.modular.car.service.CarDetentionInfoService;

import javax.annotation.Resource;

@Service
public class CarDetentionInfoServiceImpl implements CarDetentionInfoService{

	@Autowired
	CarDetentionInfoDao mapper;
	@Resource
	private ApplyInfoService applyInfoService;
	@Resource
	private CustomerInfoService customerInfoService;
	@Resource
	private CarInfoService carInfoService;
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CarDetentionInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.updateWithOutNull(entity);
		}else {
			ApplyInfo applyInfo = applyInfoService.selectById(entity.getApplyId());
			CustomerInfo customerInfo = customerInfoService.selectById(applyInfo.getCustId());
			entity.setCustId(customerInfo.getId());
			entity.setCustName(customerInfo.getName());
			entity.setCustSex(customerInfo.getSex());
			entity.setCustIdNo(customerInfo.getCertId());
			CarInfo carInfo = carInfoService.selectByCusId(customerInfo.getId());
			entity.setCarId(carInfo.getId());
			entity.setIsSettle(0);//财务是否结清 0--否 1--是
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(CarDetentionInfo entity) {
		entity.setIsDeleted(0);
		entity.setCreateTime(new Date());
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
	public void update(CarDetentionInfo entity) {
		entity.setUpdateTime(new Date());
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CarDetentionInfo entity) {
		entity.setUpdateTime(new Date());
		mapper.updateWithOutNull(entity);
	}
	
	public CarDetentionInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CarDetentionInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CarDetentionInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CarDetentionInfo> pageList = mapper.selectPage(param);
		PageInfo<CarDetentionInfo> pageInfo = new PageInfo<CarDetentionInfo>(pageList);
		return pageInfo;
	}
	public CarDetentionInfo selectByApplyId(Long applyId){
		return mapper.selectByApplyId(applyId);
	}

}

