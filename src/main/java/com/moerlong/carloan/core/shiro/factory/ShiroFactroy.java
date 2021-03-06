package com.moerlong.carloan.core.shiro.factory;

import com.moerlong.carloan.core.util.SpringContextHolder;
import com.moerlong.carloan.modular.loan.entity.vo.SendMsgVo;
import com.moerlong.carloan.modular.system.dao.UserMgrDao;
import com.moerlong.carloan.util.ParamConstants;
import com.moerlong.carloan.common.constant.factory.ConstantFactory;
import com.moerlong.carloan.common.constant.state.ManagerStatus;
import com.moerlong.carloan.core.shiro.ShiroUser;
import com.moerlong.carloan.core.util.Convert;
import com.moerlong.carloan.modular.system.dao.MenuDao;
import com.moerlong.carloan.common.persistence.model.User;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@DependsOn("springContextHolder")
@Transactional(readOnly = true)
public class ShiroFactroy implements IShiro {

    @Autowired
    private UserMgrDao userMgrDao;

    @Autowired
    private MenuDao menuDao;

    public static IShiro me() {
        return SpringContextHolder.getBean(IShiro.class);
    }

    @Override
    public User user(String account) {

        User user = userMgrDao.getByAccount(account);

        // 账号不存在
        if (null == user) {
            throw new CredentialsException();
        }
        // 账号被冻结
        if (user.getStatus() != ManagerStatus.OK.getCode()) {
            throw new LockedAccountException();
        }
        return user;
    }

    @Override
    public ShiroUser shiroUser(User user) {
        ShiroUser shiroUser = new ShiroUser();

        shiroUser.setId(user.getId());            // 账号id
        shiroUser.setAccount(user.getAccount());// 账号

        shiroUser.setName(user.getName());        // 用户名称

        Integer[] deptArray = Convert.toIntArray(user.getDeptid());// 部门集合
        List<Integer> deptList = new ArrayList<Integer>();
        List<String> deptNameList = new ArrayList<String>();
        for (int roleId : deptArray) {
            deptList.add(roleId);
            deptNameList.add(ConstantFactory.me().getSingleDeptName(roleId));
        }
        shiroUser.setDeptId(Integer.parseInt(user.getDeptid()));
        shiroUser.setDeptName(ConstantFactory.me().getSingleDeptName(Integer.parseInt(user.getDeptid())));
        shiroUser.setDeptList(deptList);
        shiroUser.setDeptNames(deptNameList);

        Integer[] roleArray = Convert.toIntArray(user.getRoleid());// 角色集合
        List<Integer> roleList = new ArrayList<Integer>();
        List<String> roleNameList = new ArrayList<String>();
        for (int roleId : roleArray) {
            roleList.add(roleId);
            roleNameList.add(ConstantFactory.me().getSingleRoleName(roleId));
        }
        shiroUser.setRoleList(roleList);
        shiroUser.setRoleNames(roleNameList);
    	
        return shiroUser;
    }

    @Override
    public List<String> findPermissionsByRoleId(Integer roleId) {
        List<String> resUrls = menuDao.getResUrlsByRoleId(roleId);
        return resUrls;
    }

    @Override
    public String findRoleNameByRoleId(Integer roleId) {
        return ConstantFactory.me().getSingleRoleName(roleId);
    }

    @Override
    public SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName) {
        String credentials = user.getPassword();
        // 密码加盐处理
        String source = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(source);
        return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    }

}
