package com.moerlong.carloan.modular.system.controller;

import com.google.code.kaptcha.Constants;
import com.moerlong.carloan.core.log.factory.LogTaskFactory;
import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.common.exception.InvalidKaptchaException;
import com.moerlong.carloan.common.persistence.dao.UserMapper;
import com.moerlong.carloan.core.log.LogManager;
import com.moerlong.carloan.core.shiro.ShiroKit;
import com.moerlong.carloan.core.shiro.ShiroUser;
import com.moerlong.carloan.core.util.ToolUtil;
import com.moerlong.carloan.modular.system.dao.MenuDao;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.moerlong.carloan.core.support.HttpKit.getIp;

/**
 * 登录控制器
 *
 * @author hwl
 * @Date 2017年1月10日 下午8:25:24
 */
//@Controller
public class BusinessController extends BaseController {

    @Autowired
    MenuDao menuDao;

    @Autowired
    UserMapper userMapper;

    /**
     * 跳转到登录页面
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login() {
        return "/main.html";

    }
    /**
     * 跳转到登录页面
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String login1() {
        return "/main.html";
    }
    /**
     * 点击登录执行的动作
     */
    @ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Object loginVali() {

        Map map = new HashMap<>();
        String username = super.getPara("username").trim();
        String password = super.getPara("password").trim();

        //验证验证码是否正确
        if(ToolUtil.getKaptchaOnOff()){
            String kaptcha = super.getPara("kaptcha").trim();
            String code = (String) super.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
            if(ToolUtil.isEmpty(kaptcha) || !kaptcha.equals(code)){
                throw new InvalidKaptchaException();
            }
        }

        Subject currentUser = ShiroKit.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());
        token.setRememberMe(true);
        try {
            currentUser.login(token);
        }catch(Exception e){
            ShiroKit.getSubject().logout();
            map.put("status",false);
            map.put("msg","用户名或密码错误");
            map.put("data",null);
            return map;
        }
        ShiroUser shiroUser = ShiroKit.getUser();
        super.getSession().setAttribute("shiroUser", shiroUser);
        super.getSession().setAttribute("username", shiroUser.getAccount());

        LogManager.me().executeLog(LogTaskFactory.loginLog(shiroUser.getId(), getIp()));

        ShiroKit.getSession().setAttribute("sessionFlag",true);

        //获取菜单列表
        List<Integer> roleList = ShiroKit.getUser().getRoleList();
        if(roleList == null || roleList.size() == 0){
            ShiroKit.getSubject().logout();
            map.put("status",false);
            map.put("msg","该用户没有角色，无法登录");
            map.put("data",null);
            return map;
        }
        /*List<MenuNode> menus = menuDao.getMenusByRoleIds(roleList);
        List<MenuNode> titles = MenuNode.buildTitle(menus);
        map.put("menus", titles);*/
        map.put("status",true);
        map.put("msg","登录成功");
        return map;
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public Object logOut() {
        Map map = new HashMap();
        map.put("msg",null);
        map.put("data",null);
        try {
            LogManager.me().executeLog(LogTaskFactory.exitLog(ShiroKit.getUser().getId(), getIp()));
            ShiroKit.getSubject().logout();
            map.put("status",true);
        }catch(Exception e){
            map.put("status",false);
        }
        return map;
    }

    /**
     *
     * */
    @RequestMapping(value = "/auth",method = RequestMethod.GET)
    @ResponseBody
    public Object auth(){
        Map map = new HashMap();
        map.put("msg",null);
        map.put("data",null);
        if (ShiroKit.isAuthenticated() || ShiroKit.getUser() != null) {
            map.put("status",true);
        } else {
            map.put("status",false);
        }
        return map;
    }
}
