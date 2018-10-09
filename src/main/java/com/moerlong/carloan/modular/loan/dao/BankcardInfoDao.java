package com.moerlong.carloan.modular.loan.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.loan.entity.BankcardInfo;

public interface BankcardInfoDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(BankcardInfo entity);

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
	public void update(BankcardInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(BankcardInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public BankcardInfo selectById(Long id);
	
	/**
	 * 根据cus_id查询
	 */
	public BankcardInfo selectByCusId(Long cusid);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<BankcardInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<BankcardInfo> selectPage(Map<String,Object> param);

}

