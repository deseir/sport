package com.moerlong.carloan.modular.cust.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.cust.entity.TelecomCallContactDetail;

public interface TelecomCallContactDetailDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(TelecomCallContactDetail entity);

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
	public void update(TelecomCallContactDetail entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(TelecomCallContactDetail entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public TelecomCallContactDetail selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<TelecomCallContactDetail> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<TelecomCallContactDetail> selectPage(Map<String,Object> param);

}

