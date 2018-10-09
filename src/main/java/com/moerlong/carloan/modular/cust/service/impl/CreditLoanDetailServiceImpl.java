package com.moerlong.carloan.modular.cust.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.moerlong.carloan.modular.cust.entity.CreditReport;
import com.moerlong.carloan.modular.cust.service.CreditCardDetailService;
import com.moerlong.carloan.modular.cust.service.CreditReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.CreditLoanDetail;
import com.moerlong.carloan.modular.cust.dao.CreditLoanDetailDao;
import com.moerlong.carloan.modular.cust.service.CreditLoanDetailService;

@Service
public class CreditLoanDetailServiceImpl implements CreditLoanDetailService{

	@Autowired
	CreditLoanDetailDao mapper;

	@Autowired
	CreditLoanDetailService loanDetailService;

	@Autowired
	CreditReportService reportService;

	@Autowired
	CreditCardDetailService cardDetailService;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CreditLoanDetail entity) {
		if (entity.getId() != null && this.selectById(entity.getId()) != null) {
			entity.setUpdateTime(new Date());
			this.updateWithOutNull(entity);
		} else {
			entity.setCreateTime(new Date());
			entity.setIsDeleted(0);
			this.save(entity);
		}
		Map map = new HashMap();
		map.put("applyId", entity.getApplyId());
		map.put("type", entity.getType());
		BigDecimal loanAmount = loanDetailService.selLoanAmount(map);
		CreditReport creditReport = reportService.selByApplyIdAndType(map);
		if (creditReport == null) {
			creditReport = new CreditReport();
			creditReport.setApplyId(entity.getApplyId());
			creditReport.setType(entity.getType());
			creditReport.setCustId(entity.getCustId());
			creditReport.setIsDeleted(0);
			creditReport.setCreateTime(new Date());
		}
		BigDecimal allUsedAmount = cardDetailService.selAllUsedAmount(map);
		if(allUsedAmount != null ){
			BigDecimal usedAmountTmp = allUsedAmount.divide(new BigDecimal(10));
			creditReport.setCardMonthAmount(usedAmountTmp);//信用卡月还款（信用卡已用额度/10)
		}else {
			creditReport.setCardMonthAmount(BigDecimal.ZERO);
		}
		if (loanAmount != null) {
			creditReport.setCreditLoanMonthAmount(loanAmount.add(creditReport.getCardMonthAmount()));//信用贷款月还款金额 (信用贷款月还款+信用卡月还款)
		}
		reportService.saveOrUpdate(creditReport);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(CreditLoanDetail entity) {
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
	public void update(CreditLoanDetail entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CreditLoanDetail entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public CreditLoanDetail selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CreditLoanDetail> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CreditLoanDetail> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CreditLoanDetail> pageList = mapper.selectPage(param);
		PageInfo<CreditLoanDetail> pageInfo = new PageInfo<CreditLoanDetail>(pageList);
		return pageInfo;
	}

	public BigDecimal selLoanAmount(Map<String,Object> param){
		return mapper.selLoanAmount(param);
	}

	public CreditLoanDetail selByApplyIdAndType(Map<String,Object> param){
		return mapper.selByApplyIdAndType(param);
	}

	public List<CreditLoanDetail> selListByApplyIdAndType(Map<String,Object> param){
		return mapper.selListByApplyIdAndType(param);
	}

	public void deleteByIds(List list){
		mapper.deleteByIds(list);
	}
}

