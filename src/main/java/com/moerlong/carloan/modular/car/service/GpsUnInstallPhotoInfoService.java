package com.moerlong.carloan.modular.car.service;

import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.entity.CarGpsUnInstallPhotoInfo;

import java.util.List;
import java.util.Map;

public interface GpsUnInstallPhotoInfoService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(CarGpsUnInstallPhotoInfo entity);

	/**
	 * 保存
	 * @param entity
	 */
	public void save(CarGpsUnInstallPhotoInfo entity);

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
	public void update(CarGpsUnInstallPhotoInfo entity);

	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CarGpsUnInstallPhotoInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CarGpsUnInstallPhotoInfo selectById(Long id);

	/**
	 * 查询所有
	 * @return
	 */
	public List<CarGpsUnInstallPhotoInfo> listAll();

	/**
	 * 分页查询
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param
	 * @return
	 */
	public PageInfo<CarGpsUnInstallPhotoInfo> selectPage(int pageSize, int pageNum, Map<String, Object> param);

	/**
	 * 根据carId 查询
	 * @param carId
	 * @return
	 */
	public List<CarGpsUnInstallPhotoInfo> selectByCarId(String carId);

	/**
	 * 一次插入多条数据
	 * @param list
	 */
	public void insertList(List<CarGpsUnInstallPhotoInfo> list);

}

