package com.moerlong.carloan.modular.cust.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.cust.entity.GongjieInfo;

public interface GongjieInfoDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(GongjieInfo entity);

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
	public void update(GongjieInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(GongjieInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public GongjieInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<GongjieInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<GongjieInfo> selectPage(Map<String,Object> param);

	/**
	 * 根据 custid 查询
	 * @param custId
	 * @return
	 */
	public GongjieInfo selectByCustId(Long custId);

}

