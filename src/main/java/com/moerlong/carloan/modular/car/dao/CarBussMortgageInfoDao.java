package com.moerlong.carloan.modular.car.dao;

import java.util.Map;
import java.util.List;

import com.moerlong.carloan.modular.car.entity.CarBussMortgageInfo;

public interface CarBussMortgageInfoDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(CarBussMortgageInfo entity);

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
	public void update(CarBussMortgageInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CarBussMortgageInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CarBussMortgageInfo selectById(Long id);
	
	public CarBussMortgageInfo selectByApplyId(Long applyid);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CarBussMortgageInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<CarBussMortgageInfo> selectPage(Map<String,Object> param);

}

