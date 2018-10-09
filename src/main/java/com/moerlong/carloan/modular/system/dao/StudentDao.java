package com.moerlong.carloan.modular.system.dao;

import java.util.List;
import java.util.Map;

import com.moerlong.carloan.common.persistence.model.OperationLog;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * 学生Dao
 *
 * @author hwl
 * @Date 2017-09-04 16:08:55
 */
public interface StudentDao {
	
	List<Map<String, Object>> selectStudentList();
	
	List<Map<String, Object>> getStudentByPage(@Param("name") String name, @Param("page") Page<OperationLog> page, @Param("orderByField") String orderByField, @Param("isAsc") boolean isAsc);


}
