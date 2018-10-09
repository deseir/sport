package com.moerlong.carloan.modular.car.service;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.entity.CarPhotoInfo;

public interface CarPhotoInfoService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(CarPhotoInfo entity);
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(CarPhotoInfo entity);

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
	public void update(CarPhotoInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CarPhotoInfo entity);
	
	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CarPhotoInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CarPhotoInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<CarPhotoInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param);

	/**
	 * 根据carId 查询
	 * @param carId
	 * @return
	 */
	public List<CarPhotoInfo> selectByCarId(String carId);

	/**
	 * 一次插入多条数据
	 * @param list
	 */
	public void insertList(List<CarPhotoInfo> list);

	public void deleteByBigClassId(Long id);

	public List<CarPhotoInfo> selectByBigClassId(Long id);

}

