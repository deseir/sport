package com.moerlong.carloan.modular.car.dao;

import com.moerlong.carloan.modular.car.entity.CarPhotoBigClassInfo;
import com.moerlong.carloan.modular.car.entity.vo.CarPhotoBigClassInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CarPhotoBigClassInfoDao {

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
	 * @param param	查询参数
	 * @return
	 */
	public List<CarPhotoBigClassInfo> selectPage(Map<String, Object> param);


	/**
	 * 获取ID的新增
	 * @param entity
	 * @return
	 */
	public void saveBigClass(CarPhotoBigClassInfoVo entity);

	public List<CarPhotoBigClassInfo> findByCarId(@Param("carId") Long id);
}

