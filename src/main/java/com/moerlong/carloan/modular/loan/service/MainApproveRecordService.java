package com.moerlong.carloan.modular.loan.service;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.loan.entity.MainApproveRecord;

public interface MainApproveRecordService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(MainApproveRecord entity);
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(MainApproveRecord entity);

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
	public void update(MainApproveRecord entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(MainApproveRecord entity);
	
	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public MainApproveRecord selectById(Long id);

	public List<MainApproveRecord> selectByApplyId(Long applyId);
	/**
	 * 查询所有
	 * @return
	 */
	public List<MainApproveRecord> listAll();
	
	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<MainApproveRecord> selectPage(int pageSize,int pageNum, Map<String,Object> param);

	public List<MainApproveRecord> getTop10(Map<String,Object> param);

}

