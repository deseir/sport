package com.moerlong.carloan.modular.loan.dao;

import com.moerlong.carloan.modular.loan.entity.ApplyOperator;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ApplyOperatorDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(ApplyOperator entity);

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
	public void update(ApplyOperator entity);

	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(ApplyOperator entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public ApplyOperator selectById(Long id);

	/**
	 * 查询所有
	 * @return
	 */
	public List<ApplyOperator> listAll();

	/**
	 * 分页查询
	 * @param param	查询参数
	 * @return
	 */
	public List<ApplyOperator> selectPage(Map<String, Object> param);

	/**
	 * 按applyId来查询
	 * @param ApplyId
	 * @return
	 */
	public List<ApplyOperator> selectByApplyId(Long ApplyId);


	/**
	 * 按applyId和roleId来查询
	 * @param applyId
	 * @return
	 */
	public List<ApplyOperator> selectByApplyIdAndRoleId(@Param("applyId") Long applyId, @Param("roleId") Long roleId);


}

