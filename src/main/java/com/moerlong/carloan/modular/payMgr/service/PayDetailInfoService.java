package com.moerlong.carloan.modular.payMgr.service;

import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.payMgr.entity.PayDetailInfo;
import com.moerlong.carloan.modular.payMgr.entity.vo.PayDetailInfoVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import java.math.BigDecimal;
import java.util.Map;

public interface PayDetailInfoService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(PayDetailInfo entity);
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(PayDetailInfo entity);

	/**
	 * 删除
	 * @param id
	 */
	public void delete(Long id);

	/**
	 * 更新
	 * @param entity
	 */
	public void update(PayDetailInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public PayDetailInfo selectById(Long id);

	public PayDetailInfo selectByPayIdAndStatus(Long payId, Integer status);

	public List<PayDetailInfo> selectByStatus(Integer status);

	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<PayDetailInfo> selectPage(int pageSize,int pageNum, String orderCondition );

	public PageInfo<PayDetailInfoVO> selectPage(int pageSize, int pageNum, String payCode, String batchNo, String beginTime, String endTime, String status, Integer payType);

	public List<PayDetailInfoVO> listByCondition(String payCode, String batchNo, String beginTime, String endTime, String status, Integer payType);

	/**
	 * 获取昨天（T-1)成功放款金额总计
	 * @return
	 */
	List<Map<String, Object>> getPaysCountLastDaySuccess();

	/**
	 * 统计上线后累计放款总金额
	 * @return
	 */
	List<Map<String, Object>> countAllPayMoney();

	/**
	 * 根据payId统计其放款成功总额
	 * @param payId
	 * @return
	 */
	List<Map<String, Object>> countPayMoneyByPayId(Long payId);
	
	BigDecimal countPayMoneyByPayId2(Long payId);


	/**
	 * 根据条件查询放款详情
	 * @return
	 */
	public List<PayDetailInfo> listByPayId(Long payId);

}

