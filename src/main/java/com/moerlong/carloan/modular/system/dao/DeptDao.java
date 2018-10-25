package com.moerlong.carloan.modular.system.dao;

import com.moerlong.carloan.common.node.ZTreeNode;
import com.moerlong.carloan.common.persistence.model.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 部门dao
 *
 * @author hwl
 * @date 2017年2月17日20:28:58
 */
public interface DeptDao {

    /**
     * 获取ztree的节点列表
     *
     * @return
     * @date 2017年2月17日 下午8:28:43
     */
    List<ZTreeNode> tree();

    /**
     * 获取部门列表树
     *
     * @return
     * @date 2017年2月18日 上午10:32:04
     */
    List<ZTreeNode> deptTreeListByDeptId(String[] deptId);

    List<Map<String, Object>> list(@Param("condition") String condition);

    List<Dept> selectAllSubDept(@Param("deptId") Integer deptId);

    List<Dept> getAllSubDeptByDeptId (@Param("deptId") Integer deptId);

    /**
     * 分页查询
     * @param param	查询参数
     * @return
     */
    public List<Dept> selectPage(Map<String,Object> param);
}
