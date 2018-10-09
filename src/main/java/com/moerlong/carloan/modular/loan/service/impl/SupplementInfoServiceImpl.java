package com.moerlong.carloan.modular.loan.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.loan.entity.SupplementInfo;
import com.moerlong.carloan.modular.loan.dao.SupplementInfoDao;
import com.moerlong.carloan.modular.loan.service.SupplementInfoService;

@Service
public class SupplementInfoServiceImpl implements SupplementInfoService{

	@Autowired
	SupplementInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(SupplementInfo entity) {
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
	public void save(SupplementInfo entity) {
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
	public void update(SupplementInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(SupplementInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public SupplementInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<SupplementInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<SupplementInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<SupplementInfo> pageList = mapper.selectPage(param);
		PageInfo<SupplementInfo> pageInfo = new PageInfo<SupplementInfo>(pageList);
		return pageInfo;
	}

	public SupplementInfo selectByApplyId (Map<String,Object> param){
		return mapper.selectByApplyId(param);
	}

}

