package com.moerlong.carloan.modular.cust.service;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.CustWorkInfo;
import com.moerlong.carloan.modular.cust.entity.vo.CustWorkInfoVo;

public interface CustWorkInfoService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(CustWorkInfo entity);
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(CustWorkInfo entity);

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
	public void update(CustWorkInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CustWorkInfo entity);
	
	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CustWorkInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CustWorkInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<CustWorkInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param);
	/**
	 * 保存或更新内存录入申请人工作信息
	 * cjh
	 */
	public Object addSaveOrUpdatemarryInfo(CustWorkInfoVo vo);
	/**
	 * 获取内勤录入申请人工作信息
	 * cjh
	 */
	public CustWorkInfo findCustWorkInfoByApplyId(Long ApplyId);
}

