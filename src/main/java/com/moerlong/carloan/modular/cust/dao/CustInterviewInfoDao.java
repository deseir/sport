package com.moerlong.carloan.modular.cust.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.cust.entity.CustInterviewInfo;

public interface CustInterviewInfoDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(CustInterviewInfo entity);

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
	public void update(CustInterviewInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CustInterviewInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CustInterviewInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CustInterviewInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<CustInterviewInfo> selectPage(Map<String,Object> param);

	/**
	 * 根据传入参数查询
	 * @param param
	 * @return
	 */
	public CustInterviewInfo selByApplyId(Map<String,Object> param);

	/**
	 * 更新内勤状态
	 * @param applyId
	 */
	public void updateNqStatus(Long applyId);

	/**
	 * 更新验车师状态
	 * @param applyId
	 */
	public void updateYcStatus(Long applyId);

}

