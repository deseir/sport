package com.moerlong.carloan.modular.car.service;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.entity.CarPriceInfo;

public interface CarPriceInfoService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(CarPriceInfo entity);
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(CarPriceInfo entity);

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
	public void update(CarPriceInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CarPriceInfo entity);
	
	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CarPriceInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CarPriceInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<CarPriceInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param);

	/**
	 * 根据传入参数查询
	 * @param param
	 * @return
	 */
	public CarPriceInfo selByApplyId(Map<String,Object> param);

}

