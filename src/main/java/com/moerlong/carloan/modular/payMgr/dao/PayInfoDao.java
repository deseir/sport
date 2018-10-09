package com.moerlong.carloan.modular.payMgr.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.moerlong.carloan.modular.payMgr.entity.PayInfo;

public interface PayInfoDao {

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
	public List<PayInfo> selectPage( @Param("orderCondition") String orderCondition);

	/**
	 * 根据条件查询放款总表
	 * @return
	 */
	public List<PayInfo> listByCondition(@Param("custName") String custName,@Param("custMobile") String custMobile, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("payStatus") Integer payStatus);

}

