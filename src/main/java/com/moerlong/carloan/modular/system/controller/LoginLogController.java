package com.moerlong.carloan.modular.system.controller;

import com.baomidou.mybatisplus.mapper.SqlRunner;
import com.baomidou.mybatisplus.plugins.Page;
import com.moerlong.carloan.common.annotion.log.BussinessLog;
import com.moerlong.carloan.common.constant.Const;
import com.moerlong.carloan.common.constant.factory.PageFactory;
import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.modular.system.warpper.LogWarpper;
import com.moerlong.carloan.common.annotion.Permission;
import com.moerlong.carloan.modular.system.dao.LogDao;
import com.moerlong.carloan.common.persistence.model.OperationLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 日志管理的控制器
 *
 * @author hwl
 * @Date 2017年4月5日 19:45:36
 */
@Controller
@RequestMapping("/loginLog")
public class LoginLogController extends BaseController {

    private static String PREFIX = "/system/log/";

    @Resource
    private LogDao logDao;

    /**
     * 跳转到日志管理的首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "login_log.html";
    }

    /**
     * 查询登录日志列表
     */
    @RequestMapping("/list")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object list(@RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) String logName) {
        Page<OperationLog> page = new PageFactory<OperationLog>().defaultPage();
        List<Map<String, Object>> result = logDao.getLoginLogs(page, beginTime, endTime, logName, page.getOrderByField(), page.isAsc());
        page.setRecords((List<OperationLog>) new LogWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 清空日志
     */
    @BussinessLog("清空登录日志")
    @RequestMapping("/delLoginLog")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object delLog() {
        SqlRunner.db().delete("delete from login_log");
        return super.SUCCESS_TIP;
    }
}
