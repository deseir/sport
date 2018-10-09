package com.moerlong.carloan.modular.cust.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.cust.entity.MarryInfo;
import com.moerlong.carloan.modular.cust.entity.vo.MarryInfoVo;

public interface MarryInfoDao {

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
	 * @param param	查询参数
	 * @return
	 */
	public List<MarryInfo> selectPage(Map<String,Object> param);
	/**
	 * 获取内勤资料录入婚姻基本信息
	 * cjh
	 */
	public List<MarryInfo> MarryInfoVoByApplyId(Long id);
}

