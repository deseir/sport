package com.moerlong.carloan.modular.cust.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.CustFinanceInfo;
import com.moerlong.carloan.modular.cust.dao.CustFinanceInfoDao;
import com.moerlong.carloan.modular.cust.service.CustFinanceInfoService;

@Service
public class CustFinanceInfoServiceImpl implements CustFinanceInfoService{

	@Autowired
	CustFinanceInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CustFinanceInfo entity) {
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
	public void save(CustFinanceInfo entity) {
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
	public void update(CustFinanceInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CustFinanceInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public CustFinanceInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CustFinanceInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CustFinanceInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CustFinanceInfo> pageList = mapper.selectPage(param);
		PageInfo<CustFinanceInfo> pageInfo = new PageInfo<CustFinanceInfo>(pageList);
		return pageInfo;
	}

	public List<CustFinanceInfo> selByApplyIdAndType(Map<String,Object> param){
		return mapper.selByApplyIdAndType(param);
	}

	public void deleteByIds(List list){
		mapper.deleteByIds(list);
	}
}

