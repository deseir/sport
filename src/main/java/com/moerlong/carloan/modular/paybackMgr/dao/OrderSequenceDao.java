package com.moerlong.carloan.modular.paybackMgr.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.moerlong.carloan.modular.paybackMgr.entity.OrderSequence;

/**
 * 
 * OrderSequence数据库操作接口类
 * 
 **/

@Repository
public interface OrderSequenceDao {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	OrderSequence selectByPrimaryKey(@Param("seqId") Long seqId);

	/**
	 *
	 * 删除（根据主键ID删除）
	 *
	 **/
	int deleteByPrimaryKey(@Param("seqId") Long seqId);

	/**
	 *
	 * 添加
	 *
	 **/
	int insert(OrderSequence record);

	/**
	 *
	 * 修改 （匹配有值的字段）
	 *
	 **/
	int updateByPrimaryKeySelective(OrderSequence record);


	/**
	 *
	 * count查询
	 *
	 **/
	long count(@Param("record") OrderSequence record);


}