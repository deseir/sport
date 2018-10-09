package com.moerlong.carloan.modular.cust.service.impl;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.LivenessAuthInfo;
import com.moerlong.carloan.modular.cust.dao.LivenessAuthInfoDao;
import com.moerlong.carloan.modular.cust.service.LivenessAuthInfoService;

@Service
public class LivenessAuthInfoServiceImpl implements LivenessAuthInfoService{

	@Autowired
	LivenessAuthInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(LivenessAuthInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(LivenessAuthInfo entity) {
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
	public void update(LivenessAuthInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(LivenessAuthInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public LivenessAuthInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<LivenessAuthInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<LivenessAuthInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<LivenessAuthInfo> pageList = mapper.selectPage(param);
		PageInfo<LivenessAuthInfo> pageInfo = new PageInfo<LivenessAuthInfo>(pageList);
		return pageInfo;
	}

	public LivenessAuthInfo selectByApplyIdAndType (Map<String,Object> param){
		return mapper.selectByApplyIdAndType(param);
	}

}

