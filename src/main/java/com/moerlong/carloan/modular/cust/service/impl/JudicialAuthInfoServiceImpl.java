package com.moerlong.carloan.modular.cust.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.JudicialAuthInfo;
import com.moerlong.carloan.modular.cust.dao.JudicialAuthInfoDao;
import com.moerlong.carloan.modular.cust.service.JudicialAuthInfoService;

@Service
public class JudicialAuthInfoServiceImpl implements JudicialAuthInfoService{

	@Autowired
	JudicialAuthInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(JudicialAuthInfo entity) {
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
	public void save(JudicialAuthInfo entity) {
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
	public void update(JudicialAuthInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(JudicialAuthInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public JudicialAuthInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<JudicialAuthInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<JudicialAuthInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<JudicialAuthInfo> pageList = mapper.selectPage(param);
		PageInfo<JudicialAuthInfo> pageInfo = new PageInfo<JudicialAuthInfo>(pageList);
		return pageInfo;
	}

	public JudicialAuthInfo selByApplyIdAndType(Map<String,Object> params){
		return mapper.selByApplyIdAndType(params);
	}

}

