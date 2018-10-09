package com.moerlong.carloan.modular.loan.service;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.entity.vo.AppInforVO;
import com.moerlong.carloan.modular.loan.entity.vo.ApplyBasicInfoVo;
import com.moerlong.carloan.modular.loan.entity.vo.LoanAppAppinfoVO;
import com.moerlong.carloan.modular.loan.entity.vo.LoanApplyInfoVo;
import com.moerlong.carloan.util.AppInforPage;

public interface ApplyInfoService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(ApplyInfo entity);
	
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
	
	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @return
	 */
	public PageInfo<ApplyInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param);

	public PageInfo<LoanApplyInfoVo> selectPage(int pageSize, int pageNum, String custName, String custIdNo, String beginTime, String endTime);
	/**
	 * cjh
	 * 保存内勤资料录入借款基本信息
	 *
	 * @return
	 */
	public Object saveOrUpdateApplyInfo(ApplyBasicInfoVo vo);

	/**
	 * 获取待办列表
	 * @param userId
	 * @param custName
	 * @param custIdNo
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<LoanApplyInfoVo> selectTodoApplyList(Long userId, String custName,  String custIdNo, String beginTime, String endTime);
	/**
	 * 获取App的代办列表
	 * @param userId
	 * @param custName
	 * @param custIdNo
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<LoanAppAppinfoVO> selectToAppdoApplyList(Long userId);
	/**
	 * 获取经办列表
	 * @param userId
	 * @param custName
	 * @param custIdNo
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<LoanApplyInfoVo> selectHandledApplyList(Long userId, String custName,  String custIdNo, String beginTime, String endTime);
	/**
	 * cjh
	 *获取内勤资料录入基本信息
	 *
	 * @return
	 */
	public ApplyInfo getBackApplyInfo( Long param);

	/**
	 * 更新同步节点状态
	 * @param applyId
	 * @param syncFieldName
	 * @param processStatus
	 * @return
	 */
	public Integer setSyncStatus(Long applyId, String syncFieldName, Integer processStatus);

	/**
	 * 判断同步任务是否完成
	 * @param applyId
	 * @param syncFieldName
	 * @param processStatus
	 * @return
	 */
	public Integer isComplateSyncTask(Long applyId, String syncFieldName, Integer processStatus);


	public ApplyInfo selectByCustId(Long custId);

	/**
	 * App获取回退（同步）列表
	 * @param userId
	 * @param custName
	 * @param custIdNo
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<LoanAppAppinfoVO> selectToAppDoApplyList(Long userId,Integer pageNum,Integer pageSize);

	public List<LoanAppAppinfoVO> selectAppHandledApplyList(Map<String, Object> map);
	//订单列表
	public AppInforPage<LoanAppAppinfoVO> getAppHandleApplyPage(Map<String, Object> map);
	
	public AppInforVO selectAppAppinforById(Long id);
	
	
}

