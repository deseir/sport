package com.moerlong.carloan.modular.cust.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.cust.entity.TelecomCallRiskAnalysis;

public interface TelecomCallRiskAnalysisDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(TelecomCallRiskAnalysis entity);

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
	public void update(TelecomCallRiskAnalysis entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(TelecomCallRiskAnalysis entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public TelecomCallRiskAnalysis selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<TelecomCallRiskAnalysis> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<TelecomCallRiskAnalysis> selectPage(Map<String,Object> param);

	/**
	 * 按applyId 和 Type 查询
	 * @param param
	 * @return
	 */
	public List<TelecomCallRiskAnalysis> selectByApplyIdAndType(Map<String,Object> param);

}

