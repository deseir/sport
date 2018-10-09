package com.moerlong.carloan.modular.cust.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.cust.entity.CreditPersonalQueryRecord;

public interface CreditPersonalQueryRecordDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(CreditPersonalQueryRecord entity);

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
	public void update(CreditPersonalQueryRecord entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CreditPersonalQueryRecord entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CreditPersonalQueryRecord selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CreditPersonalQueryRecord> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<CreditPersonalQueryRecord> selectPage(Map<String,Object> param);

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
	public CreditPersonalQueryRecord selByApplyIdAndType(Map<String,Object> param);

	/**
	 * 根据applyId和type
	 * @param param
	 * @return
	 */
	public List<CreditPersonalQueryRecord> selListByApplyIdAndType (Map<String,Object> param);

	/**
	 * 根据id一次删除多个记录
	 * @param list
	 */
	public void deleteByIds(List list);

}

