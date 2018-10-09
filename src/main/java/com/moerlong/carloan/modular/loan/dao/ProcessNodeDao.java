package com.moerlong.carloan.modular.loan.dao;

import com.moerlong.carloan.modular.car.entity.CarInsureDetailInfo;
import com.moerlong.carloan.modular.car.entity.CarMortgageInfo;
import com.moerlong.carloan.modular.car.entity.CarTransferInfo;
import com.moerlong.carloan.modular.cust.entity.*;
import com.moerlong.carloan.modular.loan.entity.ProcessNode;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Map;

public interface ProcessNodeDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(ProcessNode entity);

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
	public void update(ProcessNode entity);

	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(ProcessNode entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public ProcessNode selectById(Long id);

	/**
	 * 按状态查询
	 * @param status
	 * @return
	 */
	public ProcessNode selectByStatus(Integer status);

	/**
	 *
	 * @param name
	 * @return
	 */
	public ProcessNode selectByName(String name);

	/**
	 * 查询所有
	 * @return
	 */
	public List<ProcessNode> listAll();

	/**
	 * 分页查询
	 * @param param	查询参数
	 * @return
	 */
	public List<ProcessNode> selectPage(Map<String, Object> param);


	/**
	 * 获取下一个主流程节点
	 * @param status
	 * @param type
	 * @return
	 */
	public ProcessNode selectNextNode(@Param("status") Integer status, @Param("type") Integer type);

	/**
	 * 获取节点
	 * @param status
	 * @param type
	 * @return
	 */
	public List<ProcessNode> selectNodeByType(@Param("status") Integer status, @Param("type") Integer type);

	/**
	 * 获取所有同步节点的数据域
	 * @return
	 */
	public List<Map<String, Object>> selectAllField();

	/**
	 * 获取订单的同步节点的下一个节点
	 * @param applyId
	 * @param type
	 * @param syncFieldName
	 * @return
	 */
	public ProcessNode selectSyncNextNode(@Param("applyId") Long applyId, @Param("type") Integer type, @Param("syncFieldName") String syncFieldName);

	/**
	 *
	 * @return
	 */
    public Map<String, Object> machineRiskControlv(@Param("applyId") Long applyId,@Param("custId") Long custId);
	public List<FamilyBookSubInfo> machineRiskControlvx(@Param("familyBookInfoId") Long familyBookInfoId);
    public List<CustFinanceInfo> machineRiskControlCustFinanceInfo(@Param("applyId") Long applyId, @Param("custId") Long custId);
    public List<CreditPersonalQueryRecord> machineRiskControlCreditPersonalQueryRecord(@Param("applyId") Long applyId, @Param("custId") Long custId);
    public List<CreditLoanDetail> machineRiskControlCreditLoanDetail(@Param("applyId") Long applyId, @Param("custId") Long custId);
    public List<CreditCardDetail> machineRiskControlCreditCardDetail(@Param("applyId") Long applyId, @Param("custId") Long custId);
    public List<CreditBussQueryRecord> machineRiskControlCreditBussQueryRecord(@Param("applyId") Long applyId, @Param("custId") Long custId);
	public Map<String, Object> machineRiskControlCar(@Param("carId") Long carId);
	public  List<CarTransferInfo> machineRiskControlCarTramsferImfo(@Param("carId") Long carId);
	public  List<CarMortgageInfo> machineRiskControlCarMortgageInfo(@Param("carId") Long carId);
	public  List<CarInsureDetailInfo> machineRiskControlCarInsureDetailInfo(@Param("insureId") Long insureId);
}

