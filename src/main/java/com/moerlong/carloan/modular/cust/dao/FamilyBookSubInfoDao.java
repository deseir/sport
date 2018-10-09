package com.moerlong.carloan.modular.cust.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.cust.entity.FamilyBookSubInfo;
import com.moerlong.carloan.modular.cust.entity.vo.FamilyCustInfoPo;
import org.apache.ibatis.annotations.Param;

public interface FamilyBookSubInfoDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(FamilyBookSubInfo entity);

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
	public void update(FamilyBookSubInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(FamilyBookSubInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public FamilyBookSubInfo selectById(Long id);

	/**
	 * 根据身份证号查询家庭客户信息
	 * @param certId
	 * @return
	 */
	public List<FamilyCustInfoPo> selectCustByCertId(@Param("certId") String certId);

	/**
	 * 查询所有
	 * @return
	 */
	public List<FamilyBookSubInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<FamilyBookSubInfo> selectPage(Map<String,Object> param);

	/***
	 * 内勤录入户口本信息，保存与户主关联人信息
	 * @param list
	 */
	public  void saveAll(List<FamilyBookSubInfo> list);
	public  void updateAll(List<FamilyBookSubInfo> list);
	public  List<FamilyBookSubInfo> selectByBookId(Long id);
}

