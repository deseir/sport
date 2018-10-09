package com.moerlong.carloan.modular.cust.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.moerlong.carloan.modular.cust.entity.CreditReport;
import com.moerlong.carloan.modular.cust.service.CreditLoanDetailService;
import com.moerlong.carloan.modular.cust.service.CreditReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.CreditCardDetail;
import com.moerlong.carloan.modular.cust.dao.CreditCardDetailDao;
import com.moerlong.carloan.modular.cust.service.CreditCardDetailService;

@Service
public class CreditCardDetailServiceImpl implements CreditCardDetailService{

	@Autowired
	CreditCardDetailDao mapper;

	@Autowired
	CreditLoanDetailService loanDetailService;

	@Autowired
	CreditReportService reportService;

	@Autowired
	CreditCardDetailService cardDetailService;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CreditCardDetail entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			entity.setUpdateTime(new Date());
			this.updateWithOutNull(entity);
		}else {
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
	public void save(CreditCardDetail entity) {
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
	public void update(CreditCardDetail entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CreditCardDetail entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public CreditCardDetail selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CreditCardDetail> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CreditCardDetail> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CreditCardDetail> pageList = mapper.selectPage(param);
		PageInfo<CreditCardDetail> pageInfo = new PageInfo<CreditCardDetail>(pageList);
		return pageInfo;
	}

	public BigDecimal selAllUsedAmount(Map<String,Object> param){
		return mapper.selAllUsedAmount(param);
	}

	public CreditCardDetail selByApplyIdAndType(Map<String,Object> param){
		return mapper.selByApplyIdAndType(param);
	}

	public List<CreditCardDetail> selListByApplyIdAndType (Map<String,Object> param){
		return mapper.selListByApplyIdAndType(param);
	}

	public void deleteByIds(List list){
		mapper.deleteByIds(list);
	}

}

