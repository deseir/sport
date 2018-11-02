package com.moerlong.carloan.modular.paybackMgr.controller;


import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.common.exception.BizExceptionEnum;
import com.moerlong.carloan.common.exception.BussinessException;
import com.moerlong.carloan.common.persistence.model.CommonElement;
import com.moerlong.carloan.config.properties.SystemProperties;
import com.moerlong.carloan.core.shiro.ShiroKit;
import com.moerlong.carloan.core.shiro.ShiroUser;
import com.moerlong.carloan.modular.paybackMgr.entity.CostOrderApply;
import com.moerlong.carloan.modular.paybackMgr.service.CostOrderApplyService;
import com.moerlong.carloan.util.IDGenerator;
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
 * 单笔代扣控制器
 *
 * @author hwl
 * @Date 2018-01-20 10:06:01
 */
//@Controller
//@RequestMapping("/cost")
public class CostController extends BaseController {

    private final Logger log = LoggerFactory.getLogger(CostController.class);

    @Autowired
    SystemProperties systemProperties;

    @Autowired
    CostOrderApplyService costOrderApplyService;

    private String PREFIX = "/paybackMgr/cost/";

    /**
     * 跳转到单笔代扣首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "cost.html";
    }

    /**
     * 跳转到添加单笔代扣
     */
    @RequestMapping("/cost_add")
    public String costAdd() {
        return PREFIX + "cost_add.html";
    }

    /**
     * 跳转到修改单笔代扣
     */
    @RequestMapping("/cost_update/{costId}")
    public String costUpdate(@PathVariable Integer costId, Model model) {
        return PREFIX + "cost_edit.html";
    }

    /**
     * 获取单笔代扣列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return null;
    }

    /**
     * 新增单笔代扣
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "发起代扣请求提交主管审核")
    @ApiImplicitParam(paramType = "body", name = "costParam", required = true, dataType = "String", value = "代扣参数")
    @RequestMapping(value = "/sendCostTrans", method = {RequestMethod.POST})
    @ResponseBody
    public Object sendCostTrans(@RequestParam  String accName,
                                @RequestParam String idNo,
                                @RequestParam String accNo,
                                @RequestParam String mobile,
                                @RequestParam String amount,
                                @RequestParam String remark) {

        this.log.info("invoke sendCostTrans accName:{}",accName);
        Map<String,Object> req= new HashMap<String,Object>();
        Map<String,Object> res=new HashMap<String,Object>();

        if (StringUtils.isBlank(accName)||StringUtils.isBlank(idNo)||
                StringUtils.isBlank(accNo)||StringUtils.isBlank(mobile)||
                StringUtils.isBlank(amount)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }

        CostOrderApply entity= new CostOrderApply();
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
            costOrderApplyService.saveOrUpdate(entity);
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
     * 封装请求payCenter实体类
     * @return
     */
    private CommonElement getCommonElement(Map<String,Object> req) {

            String bankAccount = (String) req.get("accNo");
            String bankAccountName = (String) req.get("accName");
            String tradeAmount = (String) req.get("amount");
            String openProvince = (String) req.get("openProvince");
            String openCity = (String) req.get("openCity");
            String bankBranch = (String) req.get("bankBranch");
            String idNum = (String) req.get("idNo");
            String custTel = (String) req.get("mobile");
            String bankName = (String) req.get("bankName");
            ShiroUser shiroUser = ShiroKit.getUser();
            int uid=shiroUser.getId();
            String userUuid = "payfront"+uid;
            String batchNo = (String) req.get("batchNo");
            String smsCode = (String) req.get("smsCode");
            String remark=(String) req.get("remark");
            if(smsCode==null){
                smsCode="";
            }
            CommonElement commonElement = new CommonElement();
            if (StringUtils.isBlank(batchNo)) {
                batchNo = IDGenerator.getNumUUID();
                commonElement.setBatchNo(batchNo);
            } else {
                commonElement.setBatchNo(batchNo);
            }
            if (!StringUtils.isBlank(bankAccount)) {
                commonElement.setAccNo(bankAccount);
            }
            if (!StringUtils.isBlank(bankAccountName)) {
                commonElement.setAccName(bankAccountName);
            }
            if (!StringUtils.isBlank(openProvince)) {
                commonElement.setOpenProvince(openProvince);
            }
            if (!StringUtils.isBlank(openCity)) {
                commonElement.setOpenCity(openCity);
            }
            if (!StringUtils.isBlank(custTel)) {
                commonElement.setMobile(custTel);
            }
            if (!StringUtils.isBlank(userUuid)) {
                commonElement.setUserUuid(userUuid);
            }
            if (!StringUtils.isBlank(bankBranch)) {
                commonElement.setBankBranch(bankBranch);
            }
            if (!StringUtils.isBlank(idNum)) {
                commonElement.setIdNo(idNum);
            }
            if (!StringUtils.isBlank(smsCode)) {
                commonElement.setSmsCode(smsCode);
            }
            if (!StringUtils.isBlank(tradeAmount)) {
                commonElement.setAmount(tradeAmount);
            }
            if (!StringUtils.isBlank(bankName)) {
                commonElement.setBankName(bankName);
            }
            if (!StringUtils.isBlank(bankName)) {
                commonElement.setRemark(remark);
            }

            commonElement.setIdType("id");

            return commonElement;
    }


    /**
     * 删除单笔代扣
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete() {
        return SUCCESS_TIP;
    }


    /**
     * 修改单笔代扣
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update() {
        return super.SUCCESS_TIP;
    }

    /**
     * 单笔代扣详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Object detail() {
        return null;
    }
}
