package com.moerlong.carloan.modular.car.service;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.entity.CarDriverInfo;

public interface CarDriverInfoService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(CarDriverInfo entity);
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(CarDriverInfo entity);

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
	public void update(CarDriverInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CarDriverInfo entity);
	
	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CarDriverInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CarDriverInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<CarDriverInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param);

}

