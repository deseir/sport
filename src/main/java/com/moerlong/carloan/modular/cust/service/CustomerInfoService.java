package com.moerlong.carloan.modular.cust.service;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.CustomerInfo;
import com.moerlong.carloan.modular.cust.entity.vo.CustAddHistoryInfoVo;
import com.moerlong.carloan.modular.cust.entity.vo.CustomerInfoVo;

public interface CustomerInfoService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(CustomerInfo entity);
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(CustomerInfo entity);

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
	public void update(CustomerInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CustomerInfo entity);
	
	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CustomerInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CustomerInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<CustomerInfo> selectPage(int pageSize,int pageNum, String name);

	public PageInfo<CustomerInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param);

	/**
	 * 客户新增查询
	 * @param certId
	 * @return
	 */
	public List<CustAddHistoryInfoVo> searchCustHistory(String certId);
	/**
	 * 身份证信息录入
	 * cjh
	 */
	public Object custInfoAdd(CustomerInfoVo vo);
	/**
	 * 身份证信息获取
	 * cjh
	 */
	public CustomerInfo custInfoGetByApplyId(Long param);
}

