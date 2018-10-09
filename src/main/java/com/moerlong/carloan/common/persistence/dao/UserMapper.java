package com.moerlong.carloan.common.persistence.dao;

import com.moerlong.carloan.common.persistence.model.User;
import com.moerlong.carloan.common.persistence.model.UserVO;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 管理员表 Mapper 接口
 * </p>
 *
 * @author hwl
 * @since 2017-09-05
 */
public interface UserMapper extends BaseMapper<User> {
	
	UserVO findById(@Param("id")Long id);
	public void updateAvator(@Param("picUrl")String picUrl,@Param("id") long id);
}