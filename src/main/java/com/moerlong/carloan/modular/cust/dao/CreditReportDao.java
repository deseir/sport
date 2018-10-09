package com.moerlong.carloan.modular.cust.dao;

import java.math.BigDecimal;
import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.cust.entity.CreditReport;

public interface CreditReportDao {

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
	 * @param param	查询参数
	 * @return
	 */
	public List<CreditReport> selectPage(Map<String,Object> param);

	/**
	 * 根据传入参数查询
	 * @param param
	 * @return
	 */
	public CreditReport selByApplyIdAndType(Map<String,Object> param);

	public BigDecimal selAllCreditLoanMonthAmount(Map<String,Object> param);

}

