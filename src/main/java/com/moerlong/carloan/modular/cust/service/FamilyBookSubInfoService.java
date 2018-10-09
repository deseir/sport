package com.moerlong.carloan.modular.cust.service;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.FamilyBookSubInfo;
import com.moerlong.carloan.modular.cust.entity.vo.FamilyCustInfoPo;

public interface FamilyBookSubInfoService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(FamilyBookSubInfo entity);
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(FamilyBookSubInfo entity);

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
	public void update(FamilyBookSubInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(FamilyBookSubInfo entity);
	
	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public FamilyBookSubInfo selectById(Long id);

	/**
	 * 根据身份证号查询家庭客户信息
	 * @param certId
	 * @return
	 */
	public List<FamilyCustInfoPo> selectCustByCertId(String certId);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<FamilyBookSubInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<FamilyBookSubInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param);

}

