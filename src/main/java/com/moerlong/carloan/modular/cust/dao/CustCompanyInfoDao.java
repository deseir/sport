package com.moerlong.carloan.modular.cust.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.cust.entity.CustCompanyInfo;

public interface CustCompanyInfoDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(CustCompanyInfo entity);

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
	public void update(CustCompanyInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CustCompanyInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CustCompanyInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CustCompanyInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<CustCompanyInfo> selectPage(Map<String,Object> param);

	/**
	 * 根据传入参数查询
	 * @param param
	 * @return
	 */
	public CustCompanyInfo selByApplyIdAndType(Map<String,Object> param);

}

