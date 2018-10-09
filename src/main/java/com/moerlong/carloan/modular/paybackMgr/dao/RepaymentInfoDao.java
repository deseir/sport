package com.moerlong.carloan.modular.paybackMgr.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentInfo;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentInfoExample;

public interface RepaymentInfoDao {

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

	public RepaymentInfo selectByIdNumber(@Param("idNumber") String idNumber);


	public RepaymentInfo selectByPayId(Long payId);
	
	public RepaymentInfo selectByApplyId(Long applyId);
	
	/**
	 * 分页查询 
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public List<RepaymentInfo> selectPage( @Param("orderCondition") String orderCondition);
	
	List<RepaymentInfo> selectByExampleWithPage(@Param("example") RepaymentInfoExample example,@Param("startPage") int startPage,  @Param("pageSize") int pageSize);

	int countByExample(RepaymentInfoExample example);
	
	public RepaymentInfo selectInfoByPrimaryKeyForUpdate(@Param("id") Long id);


	/**
	 * 根据条件查询还款总表
	 * @return
	 */
	public List<RepaymentInfo> listByCondition(@Param("custName") String custName, @Param("custMobile") String custMobile, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("curStatus")String curStatus);


	/**
	 * 前4笔订单的手续费总额 version=0 前4笔比较特殊一次性收全手续费
	 * @return
	 */
	public List<Map<String, Object>> countChargeBefore();

}

