package com.moerlong.carloan.modular.cust.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.cust.entity.FamilyBookInfo;
import com.moerlong.carloan.modular.cust.entity.vo.FamilyBookInfoVo;

public interface FamilyBookInfoDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(FamilyBookInfo entity);

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
	public void update(FamilyBookInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(FamilyBookInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public FamilyBookInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<FamilyBookInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<FamilyBookInfo> selectPage(Map<String,Object> param);

	/**
	 * 根据applyID户口本信息获取
	 * @param applyId
	 */
	public List<FamilyBookInfo> familyBookInfoGetByApplyId(Long param);
}

