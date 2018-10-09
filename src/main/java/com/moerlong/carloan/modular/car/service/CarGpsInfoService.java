package com.moerlong.carloan.modular.car.service;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.entity.CarGpsInfo;

public interface CarGpsInfoService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(CarGpsInfo entity);
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(CarGpsInfo entity);

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
	public void update(CarGpsInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CarGpsInfo entity);
	
	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CarGpsInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CarGpsInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<CarGpsInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param);

	/**
	 * 根据ApplyId 查询
	 * @param param
	 * @return
	 */
	public CarGpsInfo selectByApplyId (Map<String,Object> param);
}

