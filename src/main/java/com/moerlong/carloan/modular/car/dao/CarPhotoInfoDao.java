package com.moerlong.carloan.modular.car.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.car.entity.CarPhotoInfo;
import org.apache.ibatis.annotations.Param;

public interface CarPhotoInfoDao {

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
	 * @param param	查询参数
	 * @return
	 */
	public List<CarPhotoInfo> selectPage(Map<String,Object> param);

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

	public void deleteByBigClassId(@Param("id") Long id);

	public List<CarPhotoInfo> selectByBigClassId(@Param("id") Long id);
}

