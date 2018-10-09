package com.moerlong.carloan.modular.payMgr.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.moerlong.carloan.modular.payMgr.entity.PayDetailInfo;
import com.moerlong.carloan.modular.payMgr.entity.vo.PayDetailInfoVO;

public interface PayDetailInfoDao {

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


	public PayDetailInfo selectByPayIdAndStatus(@Param("payId") Long payId, @Param("status") Integer status);

	public List<PayDetailInfo> selectByStatus(@Param("status") Integer status);
	
	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public List<PayDetailInfo> selectPage( @Param("orderCondition") String orderCondition);

	/**
	 * 根据条件查询代付订单
	 * @return
	 */
	public List<PayDetailInfoVO> listByCondition(@Param("payCode")String payCode, @Param("batchNo")String batchNo, @Param("beginTime")String beginTime, @Param("endTime")String endTime,
												 @Param("status")String status, @Param("payType") Integer payType);


	/**
	 * 获取昨天（T-1)成功放款金额总计
	 * @return
	 */
	List<Map<String, Object>> getPaysCountLastDaySuccess();

	/**
	 * 统计累计放款总金额
	 * @param payStatus
	 * @return
	 */
	List<Map<String, Object>> countAllPayMoney(@Param("payStatus") Integer payStatus);

	/**
	 * 根据payId统计其放款成功总额
	 * @param payId
	 * @return
	 */
	List<Map<String, Object>> countPayMoneyByPayId(@Param("payId") Long payId);
	
	BigDecimal countPayMoneyByPayId2(@Param("payId") Long payId);

	/**
	 * 根据条件查询放款详情
	 * @return
	 */
	public List<PayDetailInfo> listByPayId(@Param("payId")Long payId);


}

