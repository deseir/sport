package com.moerlong.carloan.modular.car.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.car.entity.CarTransferInfo;

public interface CarTransferInfoDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(CarTransferInfo entity);

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
	public void update(CarTransferInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CarTransferInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CarTransferInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CarTransferInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<CarTransferInfo> selectPage(Map<String,Object> param);
	public  void saveAll(List<CarTransferInfo> list);
	public  void updateAll(List<CarTransferInfo> list);
	public List<CarTransferInfo> findCarTransferInfoByCarId(Long carId);
}

