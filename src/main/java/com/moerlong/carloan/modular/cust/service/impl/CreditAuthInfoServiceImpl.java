package com.moerlong.carloan.modular.cust.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.CreditAuthInfo;
import com.moerlong.carloan.modular.cust.dao.CreditAuthInfoDao;
import com.moerlong.carloan.modular.cust.service.CreditAuthInfoService;

@Service
public class CreditAuthInfoServiceImpl implements CreditAuthInfoService{

	@Autowired
	CreditAuthInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CreditAuthInfo entity) {
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
	public void save(CreditAuthInfo entity) {
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
	public void update(CreditAuthInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CreditAuthInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public CreditAuthInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CreditAuthInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CreditAuthInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CreditAuthInfo> pageList = mapper.selectPage(param);
		PageInfo<CreditAuthInfo> pageInfo = new PageInfo<CreditAuthInfo>(pageList);
		return pageInfo;
	}

	public CreditAuthInfo selByApplyIdAndType(Map<String,Object> param){
		return mapper.selByApplyIdAndType(param);
	}
}

