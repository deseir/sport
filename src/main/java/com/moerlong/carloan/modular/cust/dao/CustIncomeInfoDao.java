package com.moerlong.carloan.modular.cust.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.cust.entity.CustIncomeInfo;

public interface CustIncomeInfoDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(CustIncomeInfo entity);

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
	public void update(CustIncomeInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CustIncomeInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CustIncomeInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CustIncomeInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<CustIncomeInfo> selectPage(Map<String,Object> param);

	/**
	 * 根据传入参数查询
	 * @param param
	 * @return
	 */
	public CustIncomeInfo selByApplyId(Map<String,Object> param);

}

