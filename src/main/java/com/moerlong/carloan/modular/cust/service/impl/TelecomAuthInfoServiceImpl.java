package com.moerlong.carloan.modular.cust.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.TelecomAuthInfo;
import com.moerlong.carloan.modular.cust.dao.TelecomAuthInfoDao;
import com.moerlong.carloan.modular.cust.service.TelecomAuthInfoService;

@Service
public class TelecomAuthInfoServiceImpl implements TelecomAuthInfoService{

	@Autowired
	TelecomAuthInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(TelecomAuthInfo entity) {
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
	public void save(TelecomAuthInfo entity) {
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
	public void update(TelecomAuthInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(TelecomAuthInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public TelecomAuthInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<TelecomAuthInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<TelecomAuthInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<TelecomAuthInfo> pageList = mapper.selectPage(param);
		PageInfo<TelecomAuthInfo> pageInfo = new PageInfo<TelecomAuthInfo>(pageList);
		return pageInfo;
	}

	public TelecomAuthInfo selectByApplyIdAndType (Map<String,Object> param){
		return mapper.selectByApplyIdAndType(param);
	}

}

