package com.moerlong.carloan.modular.cust.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.TelecomBasicInfo;
import com.moerlong.carloan.modular.cust.dao.TelecomBasicInfoDao;
import com.moerlong.carloan.modular.cust.service.TelecomBasicInfoService;

@Service
public class TelecomBasicInfoServiceImpl implements TelecomBasicInfoService{

	@Autowired
	TelecomBasicInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(TelecomBasicInfo entity) {
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
	public void save(TelecomBasicInfo entity) {
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
	public void update(TelecomBasicInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(TelecomBasicInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public TelecomBasicInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<TelecomBasicInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<TelecomBasicInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<TelecomBasicInfo> pageList = mapper.selectPage(param);
		PageInfo<TelecomBasicInfo> pageInfo = new PageInfo<TelecomBasicInfo>(pageList);
		return pageInfo;
	}

	public TelecomBasicInfo selectByApplyIdAndType(Map<String,Object> param){
		return mapper.selectByApplyIdAndType(param);
	}

}

