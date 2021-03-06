package com.moerlong.carloan.modular.system.controller;

import com.google.code.kaptcha.Constants;
import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.common.exception.InvalidKaptchaException;
import com.moerlong.carloan.common.node.MenuNode;
import com.moerlong.carloan.common.persistence.dao.UserMapper;
import com.moerlong.carloan.common.persistence.model.User;
import com.moerlong.carloan.core.log.LogManager;
import com.moerlong.carloan.core.log.factory.LogTaskFactory;
import com.moerlong.carloan.core.shiro.ShiroKit;
import com.moerlong.carloan.core.shiro.ShiroUser;
import com.moerlong.carloan.core.support.HttpKit;
import com.moerlong.carloan.core.util.ToolUtil;
import com.moerlong.carloan.modular.system.dao.MenuDao;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static com.moerlong.carloan.core.support.HttpKit.getIp;

/**
 * 登录控制器
 *
 * @author hwl
 * @Date 2017年1月10日 下午8:25:24
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    MenuDao menuDao;

    @Autowired
    UserMapper userMapper;
    @Value("${file.identity_pic_urls}")
    private String idPicUrls;

    /**
     * 手机端登录之后跳转的页面
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        ShiroKit.getSession().setAttribute("qhFlag","1");//设置前后台登录标志 1-前台 2-后台
        if(ShiroKit.getUser() == null){
            return "/login.html";
        }
        //获取菜单列表
        List<Integer> roleList = ShiroKit.getUser().getRoleList();
        if(roleList == null || roleList.size() == 0){
            ShiroKit.getSubject().logout();
            model.addAttribute("tips", "该用户没有角色，无法登陆");
            return "/login.html";
        }
        List<MenuNode> menus = menuDao.getMenusByRoleIds(roleList);
        List<MenuNode> titles = MenuNode.buildTitle(menus);
        model.addAttribute("titles", titles);

        //获取用户头像
        Integer id = ShiroKit.getUser().getId();
        User user = userMapper.selectById(id);
        String avatar = user.getAvatar();
        model.addAttribute("avatar", avatar);
//        model.addAttribute("prjType",1);//默认设置项目类型为 1-农民体育健身工程
        model.addAttribute("idPicUrls",idPicUrls);
        return "/index.html";
    }

    /**
     * 跳转到登录页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        ShiroKit.getSession().setAttribute("qhFlag","1");//设置前后台登录标志 1-前台 2-后台
        if (ShiroKit.isAuthenticated() || ShiroKit.getUser() != null) {
            return REDIRECT + "/";
        } else {
            return "/login.html";
        }
    }

    /**
     * 点击登录执行的动作
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginVali() {
        ShiroKit.getSession().setAttribute("qhFlag","1");//设置前后台登录标志 1-前台 2-后台

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

        currentUser.login(token);

        ShiroUser shiroUser = ShiroKit.getUser();
        super.getSession().setAttribute("shiroUser", shiroUser);
        super.getSession().setAttribute("username", shiroUser.getAccount());

        LogManager.me().executeLog(LogTaskFactory.loginLog(shiroUser.getId(), HttpKit.getIp()));

        ShiroKit.getSession().setAttribute("sessionFlag",true);

        return REDIRECT + "/";
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/qiantai/logout", method = RequestMethod.GET)
    public String logOut() {
        if(ShiroKit.getUser() != null){
            LogManager.me().executeLog(LogTaskFactory.exitLog(ShiroKit.getUser().getId(), getIp()));
        }
        ShiroKit.getSubject().logout();
        return REDIRECT + "/login";
    }
}
