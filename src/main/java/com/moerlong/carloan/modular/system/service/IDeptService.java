package com.moerlong.carloan.modular.system.service;

import com.moerlong.carloan.common.persistence.model.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门服务
 *
 * @author hwl
 * @date 2017-04-27 17:00
 */
public interface IDeptService {

    /**
     * 删除部门
     *
     * @author hwl
     * @Date 2017/7/11 22:30
     */
   void deleteDept(Integer deptId);

    String getAllSubDept(Integer userId);
    String selectAllDept(Integer userId);
    List<Dept> getAllSubDeptByDeptId (@Param("deptId") Integer deptId);
}
