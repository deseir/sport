package com.moerlong.carloan.modular.car.service;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.entity.CarGpsDetailInfo;

public interface CarGpsDetailInfoService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(CarGpsDetailInfo entity);
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(CarGpsDetailInfo entity);

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
	public void update(CarGpsDetailInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CarGpsDetailInfo entity);
	
	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CarGpsDetailInfo selectById(Long id);
	
	public List<CarGpsDetailInfo> selectByCarId(Long carid);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CarGpsDetailInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<CarGpsDetailInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param);

	/**
	 * 根据id一次删除多条数据
	 * @param list
	 */
	public void deleteByIds(List list);

}

