package com.moerlong.carloan.modular.car.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.car.entity.CarGpsDetailInfo;

public interface CarGpsDetailInfoDao {

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
	 * @param param	查询参数
	 * @return
	 */
	public List<CarGpsDetailInfo> selectPage(Map<String,Object> param);

	/**
	 * 根据id一次删除多个记录
	 * @param list
	 */
	public void deleteByIds(List list);

}

