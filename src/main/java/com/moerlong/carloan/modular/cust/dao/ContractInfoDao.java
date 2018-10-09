package com.moerlong.carloan.modular.cust.dao;

import java.util.Map;
import java.util.List;

import com.moerlong.carloan.core.datascope.DataScope;
import com.moerlong.carloan.modular.cust.entity.ContractInfo;
import com.moerlong.carloan.modular.cust.entity.vo.ContractInfoVo;
import org.apache.ibatis.annotations.Param;

public interface ContractInfoDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(ContractInfo entity);

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
	public void update(ContractInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(ContractInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public ContractInfo selectById(Long id);
	
	/**
	 * 根据订单号查询
	 */
	public ContractInfo selectByApplyId(Long applyid);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<ContractInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<ContractInfo> selectPage(Map<String,Object> param);
	/**
	 * 根据条件查询合同列表
	 *
	 * @return
	 * @date 2017年2月12日 下午9:14:34
	 */
	public ContractInfo selectLastContract();
	public List<ContractInfoVo> selectContractInfos(Map<String,Object> param);
}

