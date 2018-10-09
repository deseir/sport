package com.moerlong.carloan.modular.cust.service;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.ContractInfo;
import com.moerlong.carloan.modular.cust.entity.vo.ContractInfoVo;

public interface ContractInfoService {

	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(ContractInfo entity);
	
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
	 * @param pageSize	页面大小
	 * @param pageNum	第几页
	 * @param orderCondition		排序条件
	 * @return
	 */
	public PageInfo<ContractInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param);
	public PageInfo<ContractInfoVo> selectContractInfos(int pageSize, int pageNum,Map<String,Object> name);
	public ContractInfo selectLastContract();

}

