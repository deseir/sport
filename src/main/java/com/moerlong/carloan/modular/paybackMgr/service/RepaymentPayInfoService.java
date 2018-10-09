package com.moerlong.carloan.modular.paybackMgr.service;

import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentPayInfo;
import com.moerlong.carloan.modular.paybackMgr.entity.vo.RepaymentPayInfoVO;
import com.moerlong.carloan.modular.paybackMgr.entity.vo.RepaymentPayReportInfoVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface RepaymentPayInfoService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(RepaymentPayInfo entity);
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(RepaymentPayInfo entity);

	/**
	 * 删除
	 * @param id
	 */
	public void delete(Long id);

	/**
	 * 更新
	 * @param entity
	 */
	public void update(RepaymentPayInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public RepaymentPayInfo selectById(Long id);

	public RepaymentPayInfo selectInfoByPrimaryKeyForUpdate(Long id);

	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @return
	 */
	public PageInfo<RepaymentPayInfoVO> selectPage(int pageSize,int pageNum, String payCode,String batchNo, String beginTime, String endTime, String status, Integer payType);

	public PageInfo<RepaymentPayInfoVO> selectPageMrl(int pageSize,int pageNum, String payCode,String batchNo, String beginTime, String endTime, String status, Integer payType);


	public PageInfo<RepaymentPayInfo> selectPage(int pageSize,int pageNum, String orderCondition);
	/**
	 * 根据条件查询代扣订单
	 * @return
	 */
	public List<RepaymentPayInfoVO> listByCondition(String payCode, String batchNo, String beginTime, String endTime, String status, Integer payType);

	/**
	 * 根据时间条件查询历史订单报表
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<RepaymentPayReportInfoVO> listReport(String beginTime, String endTime);

	public List<RepaymentPayInfo> selectRepaymentPayingsRecord(Long repaymentId);

	List<RepaymentPayInfo> selectPaysByStatus(Integer payStatus);

	/**
	 * 获取昨天（T-1) 成功还款金额总计
	 * @return
	 */
	List<Map<String ,Object>> getPaysCountLastDaySuccess();

	/**
	 * 获取昨天（T-1)成功收取前期费用总计
	 * @return
	 */
	List<Map<String ,Object>> getPreFeePaysCountLastDaySuccess();

	/**
	 * 统计累计收款金额总计
	 * @return
	 */
	List<Map<String ,Object>> countAllRepaymentMoney();

	/**
	 * 统计累计收取前期费用总计
	 * @return
	 */
	List<Map<String, Object>> countAllRepaymentPreFee();

	/**
	 * 根据还款id统计已还金额
	 * @return
	 */
	List<Map<String ,Object>> countRepaymentMoneyByRepaymentId(Long repaymentId);
	
	BigDecimal countRepaymentMoneyByRepaymentId2(Long repaymentId);

	/**
	 * 根据提前还款类型获取提前还款的统计数据
	 * @param payType  10 11 12 13
	 * @return
	 */
	List<Map<String ,Object>> countAllOnceEarlyRepayment(Integer payType);

}

