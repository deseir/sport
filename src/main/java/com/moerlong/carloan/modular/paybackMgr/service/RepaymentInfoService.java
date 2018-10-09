package com.moerlong.carloan.modular.paybackMgr.service;

import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentInfo;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentPlanInfo;
import com.moerlong.carloan.modular.paybackMgr.entity.vo.OnceRepaymentInfoVO;

import java.util.List;
import java.util.Map;

public interface RepaymentInfoService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(RepaymentInfo entity);
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(RepaymentInfo entity);

	/**
	 * 删除
	 * @param id
	 */
	public void delete(Long id);

	/**
	 * 更新
	 * @param entity
	 */
	public void update(RepaymentInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public RepaymentInfo selectById(Long id);

	public RepaymentInfo selectByIdNumber(String idNumber);

	public RepaymentInfo selectByPayId(Long payId);
	
	public RepaymentInfo selectByApplyId(Long applyId);
	
	public Map<String,Object> selectByExampleWithPage(RepaymentInfo repaymentInfo, Integer pageSize, Integer offset) throws Exception;

	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<RepaymentInfo> selectPage(int pageSize,int pageNum, String orderCondition );

	/**
	 * 导入
	 * @param name
	 * @param idNo
	 * @param mobile
	 * @param bankName
	 * @param bankCardNo
	 * @param amount
	 * @param period
	 * @param beginDate
	 */
	public void export(Long payId, String contractNo, Integer isPerCharge, String name, String idNo, String mobile, String bankName, String bankCardNo,
					   String amount, String receptionAmount, Integer period, String beginDate, String deadlineDate, Integer version);


	public RepaymentInfo selectInfoByPrimaryKeyForUpdate(Long id);

	public ResultVO<Object> manualDeductMoney(Long repaymentId, Integer period, Integer payType);


	public ResultVO<RepaymentPlanInfo> getSpecifyPeriodPlanMoney(Long repaymentId, Integer period);


	/**
	 * 获取提前一次性还款数据信息
	 * @param repaymentId
	 * @return
	 */
	public ResultVO<OnceRepaymentInfoVO> getOnceRepaymentInfo(Long repaymentId, String appointDate);

	/**
	 * 根据条件查询还款总表
	 * @return
	 */
	public List<RepaymentInfo> listByCondition(String custName, String custMobile, String beginTime, String endTime, String curStatus);

	/**
	 * 前4笔订单的手续费总额 version=0 前4笔比较特殊一次性收全手续费
	 * @return
	 */
	public List<Map<String, Object>> countChargeBefore();


	/**
	 * 主要是对租赁等金额的计算导入 20180310 之前的数据
	 */
	public void exportLeaseMoney();


	/**
	 * 提前还款 业务申请
	 * @param repayment
	 * @param appointDate
	 * @param userId
	 * @param userName
	 * @return
	 */
	public ResultVO<Object> onceEarlyRepaymentApply(Long repaymentId, String appointDate, Long userId, String userName);

	/**
	 * 提前还款 业务经理审批
	 * @param onceEarlyId
	 * @param operatorResult
	 * @param operatorTip
	 * @param operatorId
	 * @param operatorName
	 * @return
	 */
	public ResultVO<Object> onceEarlyRepaymentBussApprove(Long onceEarlyId, Integer operatorResult, String operatorTip, Long operatorId, String operatorName);


	/**
	 * 提前还款 财务审批
	 * @param onceEarlyId
	 * @param operatorResult
	 * @param operatorTip
	 * @param operatorId
	 * @param operatorName
	 * @return
	 */
	public ResultVO<Object> onceEarlyRepaymentFinanceApprove(Long onceEarlyId, Integer operatorResult, String operatorTip, Long operatorId, String operatorName);

	/**
	 * 提前还款 财务经理审批
	 * @param onceEarlyId
	 * @param operatorResult
	 * @param operatorTip
	 * @param operatorId
	 * @param operatorName
	 * @return
	 */
	public ResultVO<Object> onceEarlyRepaymentFinanceManApprove(Long onceEarlyId, Integer operatorResult, String operatorTip, Long operatorId, String operatorName);


	/**
	 * 临时用 导入合同编号
	 * @return
	 */
	public ResultVO<Object> testExportContractNo(Long repaymentId, String contractNo);

}

