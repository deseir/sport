package com.moerlong.carloan.modular.system.service;

import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.common.persistence.model.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
    List<Dept> selectByDeptName (Map<String,Object> param);
    /**
     * 分页查询
     * @param param	查询参数
     * @return
     */
    public PageInfo<Dept> selectPage(int pageSize, int pageNum, Map<String,Object> param);
    public PageInfo<Dept> selectPage2(int pageSize, int pageNum, Map<String,Object> param);


    public int upSfxj (Map<String,Object> param);
}
