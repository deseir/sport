package com.moerlong.carloan.modular.cust.service;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.FamilyBookInfo;
import com.moerlong.carloan.modular.cust.entity.vo.FamilyBookInfoVo;

public interface FamilyBookInfoService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(FamilyBookInfo entity);
	
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
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<FamilyBookInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param);
	/**
	 * 户口本信息录入
	 * cjh
	 */
	public Object familyBookInfoAdd(FamilyBookInfoVo vo);
	/**
	 * 根据applyID户口本信息获取
	 * cjh
	 */
	public FamilyBookInfoVo familyBookInfoGetByApplyId(Long param);
}

