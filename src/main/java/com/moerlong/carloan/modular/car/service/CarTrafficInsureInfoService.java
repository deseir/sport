package com.moerlong.carloan.modular.car.service;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.entity.CarTrafficInsureInfo;

public interface CarTrafficInsureInfoService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(CarTrafficInsureInfo entity);
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(CarTrafficInsureInfo entity);

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
	public void update(CarTrafficInsureInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CarTrafficInsureInfo entity);
	
	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CarTrafficInsureInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CarTrafficInsureInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<CarTrafficInsureInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param);

}

