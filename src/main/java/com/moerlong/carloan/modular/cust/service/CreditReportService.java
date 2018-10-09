package com.moerlong.carloan.modular.cust.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.CreditReport;

public interface CreditReportService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(CreditReport entity);
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(CreditReport entity);

	/**
	 * 删除
	 * @param id
	 */
	public void delete(Long id);
	
	/**
	 * 逻辑删除
	 * @param id
	 */
	public void deleteLogic(Long id);
	
	/**
	 * 更新
	 * @param entity
	 */
	public void update(CreditReport entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CreditReport entity);
	
	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CreditReport selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CreditReport> listAll();
	
	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<CreditReport> selectPage(int pageSize,int pageNum, Map<String,Object> param);

	/**
	 * 根据传入参数查询
	 * @param param
	 * @return
	 */
	public CreditReport selByApplyIdAndType(Map<String,Object> param);

	public BigDecimal selAllCreditLoanMonthAmount(Map<String,Object> param);

}

