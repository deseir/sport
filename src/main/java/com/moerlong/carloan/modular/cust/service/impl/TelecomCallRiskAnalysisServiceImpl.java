package com.moerlong.carloan.modular.cust.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.TelecomCallRiskAnalysis;
import com.moerlong.carloan.modular.cust.dao.TelecomCallRiskAnalysisDao;
import com.moerlong.carloan.modular.cust.service.TelecomCallRiskAnalysisService;

@Service
public class TelecomCallRiskAnalysisServiceImpl implements TelecomCallRiskAnalysisService{

	@Autowired
	TelecomCallRiskAnalysisDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(TelecomCallRiskAnalysis entity) {
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
	public void save(TelecomCallRiskAnalysis entity) {
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
	public void update(TelecomCallRiskAnalysis entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(TelecomCallRiskAnalysis entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public TelecomCallRiskAnalysis selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<TelecomCallRiskAnalysis> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<TelecomCallRiskAnalysis> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<TelecomCallRiskAnalysis> pageList = mapper.selectPage(param);
		PageInfo<TelecomCallRiskAnalysis> pageInfo = new PageInfo<TelecomCallRiskAnalysis>(pageList);
		return pageInfo;
	}

	public List<TelecomCallRiskAnalysis> selectByApplyIdAndType(Map<String,Object> param){
		return  mapper.selectByApplyIdAndType(param);
	}

}

