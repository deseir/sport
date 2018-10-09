package com.moerlong.carloan.modular.cust.dao;

import java.math.BigDecimal;
import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.cust.entity.CreditLoanDetail;

public interface CreditLoanDetailDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(CreditLoanDetail entity);

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
	public void update(CreditLoanDetail entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CreditLoanDetail entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CreditLoanDetail selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CreditLoanDetail> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<CreditLoanDetail> selectPage(Map<String,Object> param);

	/**
	 * 根据applyId 和type 查询贷款月还款金额
	 * @param param
	 * @return
	 */
	public BigDecimal selLoanAmount(Map<String,Object> param);

	/**
	 * 根据传入参数查询
	 * @param param
	 * @return
	 */
	public CreditLoanDetail selByApplyIdAndType(Map<String,Object> param);

	/**
	 * 根据applyId 和 type查询所有的
	 * @param param
	 * @return
	 */
	public List<CreditLoanDetail> selListByApplyIdAndType(Map<String,Object> param);

	/**
	 * 批量删除
	 * @param list
	 */
	public void deleteByIds(List list);

}

