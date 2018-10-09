package com.moerlong.carloan.modular.loan.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.entity.vo.AppInforVO;
import com.moerlong.carloan.modular.loan.entity.vo.LoanAppAppinfoVO;
import com.moerlong.carloan.modular.loan.entity.vo.LoanApplyInfoVo;
import org.apache.ibatis.annotations.Param;

public interface ApplyInfoDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(ApplyInfo entity);

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
	public void update(ApplyInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(ApplyInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public ApplyInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<ApplyInfo> listAll();
	
	public List<LoanApplyInfoVo> selectUnApplyGps(@Param("deptIdList") String deptIdList);
	
	public List<LoanAppAppinfoVO> selectUnAppApplyGps(@Param("deptIdList") String deptIdList);
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<ApplyInfo> selectPage(Map<String,Object> param);

	/**
	 * 根据条件查询
	 * @param custName
	 * @param custIdNo
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<LoanApplyInfoVo> listByCondition(@Param("custName") String custName, @Param("custIdNo") String custIdNo, @Param("beginTime")String beginTime, @Param("endTime")String endTime);

	/**
	 * 获取待办列表
	 * @param userId
	 * @param custName
	 * @param custIdNo
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<LoanApplyInfoVo> selectTodoApplyList(@Param("userId") Long userId, @Param("deptIdList") String deptIdList, @Param("custName") String custName, @Param("custIdNo") String custIdNo, @Param("beginTime")String beginTime, @Param("endTime")String endTime);
	
	/**
	 * App获取待办列表
	 * @param userId
	 * @param custName
	 * @param custIdNo
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<LoanAppAppinfoVO> selectToAppdoApplyList(@Param("userId") Long userId, @Param("deptIdList") String deptIdList);

	/**
	 * 获取待办（同步）列表
	 * @param userId
	 * @param deptIdList
	 * @param custName
	 * @param custIdNo
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<LoanApplyInfoVo> selectSyncTodoApplyList(@Param("userId") Long userId, @Param("deptIdList") String deptIdList,
														 @Param("syncFieldName") String syncFieldName,
														 @Param("custName") String custName, @Param("custIdNo") String custIdNo, @Param("beginTime")String beginTime, @Param("endTime")String endTime);
	/**
	 * 获取回退（同步）列表App
	 * @param userId
	 * @param deptIdList
	 * @param syncFieldName
	 * @param custName
	 * @param custIdNo
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<LoanAppAppinfoVO> selectSyncToAppDoApplyList(@Param("userId") Long userId, @Param("deptIdList") String deptIdList, @Param("syncFieldName") String syncFieldName); 
	/**
	 * 获取待办（驳回）列表
	 * @param userId
	 * @param custName
	 * @param custIdNo
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<LoanApplyInfoVo> selectBackApplyList(@Param("userId") Long userId, @Param("custName") String custName, @Param("custIdNo") String custIdNo, @Param("beginTime")String beginTime, @Param("endTime")String endTime);


	/**
	 * 获取经办列表
	 * @param userId
	 * @param custName
	 * @param custIdNo
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<LoanApplyInfoVo> selectHandledApplyList(@Param("userId") Long userId, @Param("custName") String custName, @Param("custIdNo") String custIdNo, @Param("beginTime")String beginTime, @Param("endTime")String endTime);


	/**
	 * 判断同步任务是否完成
	 * @param applyId
	 * @param syncFieldName
	 * @param processStatus
	 * @return
	 */
	public Integer isComplateSyncTask(@Param("applyId") Long applyId, @Param("syncFieldName") String syncFieldName, @Param("processStatus") Integer processStatus);

	/**
	 * 更新同步节点状态
	 * @param applyId
	 * @param syncFieldName
	 * @param processStatus
	 * @return
	 */
	public Integer setSyncStatus(@Param("applyId") Long applyId, @Param("syncFieldName") String syncFieldName, @Param("processStatus") Integer processStatus);


	public ApplyInfo selectByCustId(Long custId);
	/**
	 * App订单
	 * @param map
	 * @return
	 */
	public List<LoanAppAppinfoVO> selectAppHandledApplyList(Map<String, Object> map);
	
	public Integer countAppHandledApply(Map<String, Object> map);
	
	public AppInforVO selectAppAppinforById(@Param("id") long id);
}

