package com.moerlong.carloan.modular.cust.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.CreditCardDetail;

public interface CreditCardDetailService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(CreditCardDetail entity);
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(CreditCardDetail entity);

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
	public void update(CreditCardDetail entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CreditCardDetail entity);
	
	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CreditCardDetail selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CreditCardDetail> listAll();
	
	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<CreditCardDetail> selectPage(int pageSize,int pageNum, Map<String,Object> param);

	/**
	 * 根据applyId 和type 查询信用卡已用的总额度
	 * @param param
	 * @return
	 */
	public BigDecimal selAllUsedAmount(Map<String,Object> param);

	/**
	 * 根据传入参数查询
	 * @param param
	 * @return
	 */
	public CreditCardDetail selByApplyIdAndType(Map<String,Object> param);

	/**
	 * 根据applyId 和type 查询所有的数据
	 * @param param
	 * @return
	 */
	public List<CreditCardDetail> selListByApplyIdAndType (Map<String,Object> param);

	/**
	 * 批量删除
	 * @param list
	 */
	public void deleteByIds(List list);
}

