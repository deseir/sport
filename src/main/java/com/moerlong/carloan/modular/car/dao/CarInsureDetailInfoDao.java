package com.moerlong.carloan.modular.car.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.car.entity.CarInsureDetailInfo;

public interface CarInsureDetailInfoDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(CarInsureDetailInfo entity);

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
	public void update(CarInsureDetailInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CarInsureDetailInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CarInsureDetailInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CarInsureDetailInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<CarInsureDetailInfo> selectPage(Map<String,Object> param);
	public  void saveAll(List<CarInsureDetailInfo> list);
	public  void updateAll(List<CarInsureDetailInfo> list);
	public List<CarInsureDetailInfo> findCarInsureDetailInfoByCarBussInsureInfoId(Long carBussInsureInfoId);
}

