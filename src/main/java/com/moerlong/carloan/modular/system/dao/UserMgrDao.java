package com.moerlong.carloan.modular.system.dao;

import com.moerlong.carloan.core.datascope.DataScope;
import com.moerlong.carloan.common.persistence.model.User;
import com.moerlong.carloan.common.persistence.model.UserVO;
import com.moerlong.carloan.modular.cust.entity.vo.CustomerInfoVo;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 管理员的dao
 *
 * @author hwl
 * @date 2017年2月12日 下午8:43:52
 */
public interface UserMgrDao {

    /**
     * 根据角色查询用户
     * */
    List<User> selectUserByRoleId( @Param("roleid") String roleid);

    /**
     * 修改用户状态
     *
     * @param user
     * @date 2017年2月12日 下午8:42:31
     */
    int setStatus(@Param("userId") Integer userId, @Param("status") int status);

    /**
     * 修改密码
     *
     * @param userId
     * @param pwd
     * @date 2017年2月12日 下午8:54:19
     */
    int changePwd(@Param("userId") Integer userId, @Param("pwd") String pwd);

    /**
     * 根据条件查询用户列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    List<Map<String, Object>> selectUsers(@Param("dataScope") DataScope dataScope, @Param("name") String name, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("deptid") Integer deptid);

    /**
     * 设置用户的角色
     *
     * @return
     * @date 2017年2月13日 下午7:31:30
     */
    int setRoles(@Param("userId") Integer userId, @Param("roleIds") String roleIds);

    /**
     * 通过账号获取用户
     *
     * @param account
     * @return
     * @date 2017年2月17日 下午11:07:46
     */
    User getByAccount(@Param("account") String account);


    User selectByMobile(@Param("mobile") String mobile);
    
   List<UserVO> selectByUserVOMobile(@Param("mobile") String mobile);
    /**
     * 通过部门ID获取用户
     *
     * @param deptId,roleId
     * @return
     * @date 2018年2月17日 下午11:07:46
     */
    List<String> getByDeptIdAndRoleId(@Param("deptId") Integer deptId,@Param("roleId") String roleId);
    /**
     * 按id查询
     * @param id
     * @return
     */
    public User selectUserById(Integer id);



    List<User> selectUsersByDeptIdAndRoleId(@Param("deptId") Integer deptId,@Param("roleId") String roleId);

    /**
     * 根据roleId 查询用户名称
     * @param roleId
     * @return
     */
    public List<String> getByRoleId(@Param("roleId") String roleId,@Param("deptId") String deptId);
    
    /**
     * 修改个人信息
     * @param idCode
     * @param address
     * @param userId
     */
    public void updateByUsrVO(@Param("idCode")String idCode,@Param("address")String address,@Param("userId")Integer userId);
    /**
     * OCR身份识别
     * @param id
     * @return
     */
    public CustomerInfoVo findByOCR(Map<String, Object> map);
}
