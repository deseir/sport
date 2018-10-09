package com.moerlong.carloan.modular.car.service;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.entity.CarInfo;
import com.moerlong.carloan.modular.car.entity.vo.CarInfoVo;
import com.moerlong.carloan.modular.car.entity.vo.InitCarVerifyVo;

public interface CarInfoService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(CarInfo entity);
	
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
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<CarInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param);
	/**
	 * cjh
	 * 保存内勤资料录入车辆基本信息
	 *
	 * @return
	 */
	public Object addSaveOrUpdateCarInfo(CarInfoVo vo);
	/**
	 * cjh
	 * 获取内勤资料录入车辆基本信息根据ApplyId
	 *
	 * @return
	 */
	public CarInfoVo findCarInfoByApplyId(Long ApplyId);
	/**
	 * 根据custId查询验车师初始化页面的车辆信息
	 * @param custId
	 * @return
	 */
	public InitCarVerifyVo selInitCarverifyByCustId(Long custId);
}

