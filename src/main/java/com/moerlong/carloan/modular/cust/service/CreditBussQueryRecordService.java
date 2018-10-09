package com.moerlong.carloan.modular.cust.service;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.CreditBussQueryRecord;

public interface CreditBussQueryRecordService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(CreditBussQueryRecord entity);
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(CreditBussQueryRecord entity);

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
	public void update(CreditBussQueryRecord entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CreditBussQueryRecord entity);
	
	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CreditBussQueryRecord selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CreditBussQueryRecord> listAll();
	
	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<CreditBussQueryRecord> selectPage(int pageSize,int pageNum, Map<String,Object> param);

	/**
	 * 根据applyId和type查询最近两个月的查询记录数
	 * @param param
	 * @return
	 */
	public Integer selRecent2MonCount(Map<String,Object> param);

	/**
	 * 根据传入参数查询
	 * @param param
	 * @return
	 */
	public CreditBussQueryRecord selByApplyIdAndType(Map<String,Object> param);

	/**
	 * 根据applyId和type 查询所有的数据
	 * @param param
	 * @return
	 */
	public List<CreditBussQueryRecord> selListByApplyIdAndType(Map<String,Object> param);

	/**
	 * 根据id一次删除多条记录
	 * @param list
	 */
	public void deleteByIds(List list);

}

