package com.moerlong.carloan.modular.payMgr.service;

import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.modular.payMgr.entity.PayInfo;

import java.util.List;
import java.util.Map;

public interface PayInfoService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(PayInfo entity);
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(PayInfo entity);

	/**
	 * 删除
	 * @param id
	 */
	public void delete(Long id);

	/**
	 * 更新
	 * @param entity
	 */
	public void update(PayInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public PayInfo selectById(Long id);
	
	public PayInfo selectByApplyId(Long id);

	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<PayInfo> selectPage(int pageSize,int pageNum, String orderCondition );

	public ResultVO<Object> bussManagerApprove(Long payId, Integer operatorResult, String operatorTip, Long operatorId, String operatorName);

	public ResultVO<Object> financeApprove(Long payId, String amount, Integer operatorResult,
										   String operatorTip, Long operatorId, String oeratorName);

	public ResultVO<Object> getPreFeeByPayId(Long payId);

	public ResultVO<Object> preRepayFailManual(Long payId, Integer payType, Long operatorId, String oeratorName);

	public ResultVO<Object> financeManagerApprove(Long payId, Integer operatorResult,
												  String operatorTip, Long operatorId, String oeratorName);

	public ResultVO<Object> ReceptionFinanceApprove(Long payId, Long operatorId, String operatorName);

	public ResultVO<Object> paySuccessOpt(Long payId);

	public ResultVO<Object> repaymentCharge(Long payId);

	public ResultVO<Object> repaymentReceptionFee(Long payId);

	public ResultVO<Map<String,String>> repaymentPreFee(Long payId, Integer payType);
	/**
	 * 根据条件查询放款总表
	 * @return
	 */
	public List<PayInfo> listByCondition(String custName, String custMobile, String beginTime, String endTime, Integer payStatus);

	/**
	 * 根据卡号查询出银行名称
	 * @return
	 */
	public Map<String,Object> selectBankName(String bankCardNo);

}

