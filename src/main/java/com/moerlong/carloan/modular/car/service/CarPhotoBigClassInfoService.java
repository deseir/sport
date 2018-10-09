package com.moerlong.carloan.modular.car.service;

import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.entity.CarPhotoBigClassInfo;
import com.moerlong.carloan.modular.car.entity.vo.CarPhotoBigClassInfoVo;

import java.util.List;
import java.util.Map;

public interface CarPhotoBigClassInfoService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(CarPhotoBigClassInfo entity);
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(CarPhotoBigClassInfo entity);

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
	public void update(CarPhotoBigClassInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CarPhotoBigClassInfo entity);
	
	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CarPhotoBigClassInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CarPhotoBigClassInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<CarPhotoBigClassInfo> selectPage(int pageSize, int pageNum, Map<String, Object> param);

	public void saveBigClass(CarPhotoBigClassInfoVo entity);

	public List<CarPhotoBigClassInfo> findByCarId(Long id);
}

