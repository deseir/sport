package com.moerlong.carloan.modular.payMgr.controller;

import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.common.exception.BizExceptionEnum;
import com.moerlong.carloan.common.exception.BussinessException;
import com.moerlong.carloan.config.properties.SystemProperties;
import com.moerlong.carloan.core.shiro.ShiroKit;
import com.moerlong.carloan.core.shiro.ShiroUser;
import com.moerlong.carloan.modular.payMgr.entity.PayOrderApply;
import com.moerlong.carloan.modular.payMgr.service.PayOrderApplyService;
import com.moerlong.carloan.modular.paybackMgr.controller.CostController;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 单笔代付控制器
 *
 * @author hwl
 * @Date 2018-01-20 10:06:49
 */
//@Controller
//@RequestMapping("/pay")
public class PayController extends BaseController {

    private final Logger log = LoggerFactory.getLogger(CostController.class);

    @Autowired
    SystemProperties systemProperties;
    @Autowired
    PayOrderApplyService payOrderApplyService;


    private String PREFIX = "/payMgr/pay/";

    /**
     * 跳转到单笔代付首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "pay.html";
    }

    /**
     * 跳转到添加单笔代付
     */
    @RequestMapping("/pay_add")
    public String payAdd() {
        return PREFIX + "pay_add.html";
    }

    /**
     * 跳转到修改单笔代付
     */
    @RequestMapping("/pay_update/{payId}")
    public String payUpdate(@PathVariable Integer payId, Model model) {
        return PREFIX + "pay_edit.html";
    }

    /**
     * 获取单笔代付列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return null;
    }

    /**
     * 新增单笔代付
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add() {
        return super.SUCCESS_TIP;
    }


    /**
     * 新增单笔代扣
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "发起代付交易")
    @ApiImplicitParam(paramType = "body", name = "payParam", required = true, dataType = "String", value = "代付参数")
    @RequestMapping(value = "/sendPayTrans", method = {RequestMethod.POST})
    @ResponseBody
    public Object sendPayTrans(@RequestParam  String accName,
                               @RequestParam String idNo,
                               @RequestParam String accNo,
                               @RequestParam String mobile,
                               @RequestParam String amount,
                               @RequestParam String remark,
                               @RequestParam  String bankName,
                               @RequestParam String openProvince,
                               @RequestParam String openCity,
                               @RequestParam String bankBranch) {

        this.log.info("invoke sendCostTrans accName:{}",accName);
        Map<String,Object> req= new HashMap<String,Object>();
        Map<String,Object> res=new HashMap<String,Object>();

        if (StringUtils.isBlank(accName)||StringUtils.isBlank(idNo)||
                StringUtils.isBlank(accNo)||StringUtils.isBlank(mobile)||
                StringUtils.isBlank(amount)||StringUtils.isBlank(bankName)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }

        PayOrderApply entity= new PayOrderApply();
        entity.setAccName(accName);
        entity.setIdNo(idNo);
        entity.setAccNo(accNo);
        entity.setMobile(mobile);
        entity.setAmount(new BigDecimal(amount));
        entity.setRemark(remark);
        entity.setState("0");//未审核状态
        ShiroUser shiroUser = ShiroKit.getUser();
        int uid=shiroUser.getId();
        entity.setUserUuid(uid+"");
        String userName=shiroUser.getName();
        entity.setUserName(userName);
        String deptId=String.valueOf(shiroUser.getDeptId());
        entity.setDeptId(deptId);
        entity.setCny("cny");
        entity.setCreateTime(new Date());
        try {
            payOrderApplyService.saveOrUpdate(entity);
            res.put("status", 0);
            res.put("msg", "操作成功");
        } catch (Throwable e) {
            this.log.error(e.getMessage(), e);
            res.put("status", 1);
            res.put("msg", e.toString());
        }
        return res;

    }

    /**
     * 删除单笔代付
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete() {
        return SUCCESS_TIP;
    }


    /**
     * 修改单笔代付
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update() {
        return super.SUCCESS_TIP;
    }

    /**
     * 单笔代付详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Object detail() {
        return null;
    }
}
