package com.moerlong.carloan.modular.cust.service;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.GongjieInfo;
import com.moerlong.carloan.modular.cust.entity.vo.GongjieInfoVo;

public interface GongjieInfoService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(GongjieInfoVo entity);
	
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
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<GongjieInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param);

	/**
	 * 根据custId查询
	 * @param custId
	 * @return
	 */
	public GongjieInfo selectByCustId(Long custId);

}

