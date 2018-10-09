package com.moerlong.carloan.modular.cust.service.impl;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.TelecomRoamInfo;
import com.moerlong.carloan.modular.cust.dao.TelecomRoamInfoDao;
import com.moerlong.carloan.modular.cust.service.TelecomRoamInfoService;

@Service
public class TelecomRoamInfoServiceImpl implements TelecomRoamInfoService{

	@Autowired
	TelecomRoamInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(TelecomRoamInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(TelecomRoamInfo entity) {
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
	public void update(TelecomRoamInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(TelecomRoamInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public TelecomRoamInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<TelecomRoamInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<TelecomRoamInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<TelecomRoamInfo> pageList = mapper.selectPage(param);
		PageInfo<TelecomRoamInfo> pageInfo = new PageInfo<TelecomRoamInfo>(pageList);
		return pageInfo;
	}

}

