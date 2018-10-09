package com.moerlong.carloan.modular.cust.dao;

import com.moerlong.carloan.modular.cust.entity.ChannelInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ChannelInfoDao {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(ChannelInfo entity);

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
	public void update(ChannelInfo entity);
	
	/**
	 * 更新非空字段
	 * @param entity
	 */
	public void updateWithOutNull(ChannelInfo entity);

	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public ChannelInfo selectById(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<ChannelInfo> listAll();
	
	/**
	 * 分页查询 
	 * @param param	查询参数
	 * @return
	 */
	public List<ChannelInfo> selectPage(Map<String,Object> param);

	public List<ChannelInfo> selectPage(@Param(value="channelName") String channelName);

}

