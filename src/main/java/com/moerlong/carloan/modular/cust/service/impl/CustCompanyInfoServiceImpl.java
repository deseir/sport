package com.moerlong.carloan.modular.cust.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.CustCompanyInfo;
import com.moerlong.carloan.modular.cust.dao.CustCompanyInfoDao;
import com.moerlong.carloan.modular.cust.service.CustCompanyInfoService;

@Service
public class CustCompanyInfoServiceImpl implements CustCompanyInfoService{

	@Autowired
	CustCompanyInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CustCompanyInfo entity) {
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
	public void save(CustCompanyInfo entity) {
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
	public void update(CustCompanyInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CustCompanyInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public CustCompanyInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CustCompanyInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CustCompanyInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CustCompanyInfo> pageList = mapper.selectPage(param);
		PageInfo<CustCompanyInfo> pageInfo = new PageInfo<CustCompanyInfo>(pageList);
		return pageInfo;
	}

	public CustCompanyInfo selByApplyIdAndType(Map<String,Object> param){
		return mapper.selByApplyIdAndType(param);
	}
}

