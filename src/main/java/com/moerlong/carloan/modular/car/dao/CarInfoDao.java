package com.moerlong.carloan.modular.car.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.car.entity.CarInfo;
import com.moerlong.carloan.modular.car.entity.vo.CarInfoVo;
import com.moerlong.carloan.modular.car.entity.vo.InitCarVerifyVo;

public interface CarInfoDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(CarInfo entity);

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
	public void update(CarInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CarInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CarInfo selectById(Long id);
	
	/**
	 * 按客户id查询
	 */
	public CarInfo selectByCusId(Long cusid);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CarInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<CarInfo> selectPage(Map<String,Object> param);
	public CarInfo selectCarInfoVoByApplyId(Long applyId);

	/**
	 * 根据custId查询验车师初始化页面的车辆信息
	 * @param custId
	 * @return
	 */
	public InitCarVerifyVo selInitCarverifyByCustId(Long custId);
}

