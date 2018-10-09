package com.moerlong.carloan.modular.cust.dao;

import java.util.Map;
import java.util.List;
import com.moerlong.carloan.modular.cust.entity.CustomerInfo;
import com.moerlong.carloan.modular.cust.entity.vo.CustAddHistoryInfoVo;
import org.apache.ibatis.annotations.Param;

public interface CustomerInfoDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(CustomerInfo entity);

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
	public void update(CustomerInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(CustomerInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public CustomerInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CustomerInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<CustomerInfo> selectPage(@Param("name") String name);

	public List<CustomerInfo> selectPage(Map<String,Object> param);


	/**
	 * 客户新增查询
	 * @param certId
	 * @return
	 */
	public List<CustAddHistoryInfoVo> searchCustHistory(@Param("certId") String certId);

}

