package com.moerlong.carloan.modular.cust.service;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.MarryInfo;
import com.moerlong.carloan.modular.cust.entity.vo.MarryInfoVo;

public interface MarryInfoService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(MarryInfo entity);
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(MarryInfo entity);

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
	public void update(MarryInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(MarryInfo entity);
	
	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public MarryInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<MarryInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<MarryInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param);
	/**
	 * 信息录入
	 * cjh
	 */
	public Object addSaveOrUpdatemarryInfo(MarryInfoVo vo);
	/**
	 * 获取内勤资料录入婚姻基本信息
	 * cjh
	 */
	public MarryInfoVo findmarryInfoByApplyId(Long ApplyId);
}

