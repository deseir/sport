package com.moerlong.carloan.modular.sport.service;

import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.sport.entity.SAttach;

import java.util.List;
import java.util.Map;

public interface SAttachService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(SAttach entity);
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(SAttach entity);

	/**
	 * 删除
	 * @param id
	 */
	public void delete(Integer id);
	
	/**
	 * 逻辑删除
	 * @param id
	 */
	public void deleteLogic(Integer id);
	
	/**
	 * 更新
	 * @param entity
	 */
	public void update(SAttach entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(SAttach entity);
	
	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public SAttach selectById(Integer id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<SAttach> listAll();
	
	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param param		排序条件
	 * @return
	 */
	public PageInfo<SAttach> selectPage(int pageSize, int pageNum, Map<String, Object> param);


}

