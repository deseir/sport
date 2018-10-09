package com.moerlong.carloan.modular.cust.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.CreditReport;
import com.moerlong.carloan.modular.cust.dao.CreditReportDao;
import com.moerlong.carloan.modular.cust.service.CreditReportService;

@Service
public class CreditReportServiceImpl implements CreditReportService{

	@Autowired
	CreditReportDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CreditReport entity) {
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
	public void save(CreditReport entity) {
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
	public void update(CreditReport entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CreditReport entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public CreditReport selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CreditReport> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CreditReport> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CreditReport> pageList = mapper.selectPage(param);
		PageInfo<CreditReport> pageInfo = new PageInfo<CreditReport>(pageList);
		return pageInfo;
	}

	public CreditReport selByApplyIdAndType(Map<String,Object> param){
		return mapper.selByApplyIdAndType(param);
	}

	public BigDecimal selAllCreditLoanMonthAmount(Map<String,Object> param){
		return mapper.selAllCreditLoanMonthAmount(param);
	}
}

