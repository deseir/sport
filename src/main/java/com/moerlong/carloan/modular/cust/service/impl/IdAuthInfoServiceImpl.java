package com.moerlong.carloan.modular.cust.service.impl;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.IdAuthInfo;
import com.moerlong.carloan.modular.cust.dao.IdAuthInfoDao;
import com.moerlong.carloan.modular.cust.service.IdAuthInfoService;

@Service
public class IdAuthInfoServiceImpl implements IdAuthInfoService{

	@Autowired
	IdAuthInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(IdAuthInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(IdAuthInfo entity) {
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
	public void update(IdAuthInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(IdAuthInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public IdAuthInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<IdAuthInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<IdAuthInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<IdAuthInfo> pageList = mapper.selectPage(param);
		PageInfo<IdAuthInfo> pageInfo = new PageInfo<IdAuthInfo>(pageList);
		return pageInfo;
	}

	public IdAuthInfo selectByApplyIdAndType (Map<String,Object> param){
		return mapper.selectByApplyIdAndType(param);
	}
}

