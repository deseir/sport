package com.moerlong.carloan.modular.loan.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.loan.entity.MainApproveRecord;

public interface MainApproveRecordDao {

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
	 * @param param	查询参数
	 * @return
	 */
	public List<MainApproveRecord> selectPage(Map<String,Object> param);

	public List<MainApproveRecord> getTop10(Map<String,Object> param);

}

