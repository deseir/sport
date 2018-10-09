package com.moerlong.carloan.modular.paybackMgr.controller;


import com.moerlong.carloan.modular.paybackMgr.warpper.CostOrderWarpper;
import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.common.warpper.BaseControllerWarpper;
import com.moerlong.carloan.config.properties.SystemProperties;
import com.moerlong.carloan.modular.paybackMgr.service.CostOrderApplyService;
import com.moerlong.carloan.util.CommonUtil;
import com.moerlong.carloan.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代扣订单控制器
 *
 * @author hwl
 * @Date 2018-01-20 10:06:25
 */
@Controller
@RequestMapping("/costOrder")
public class CostOrderController extends BaseController {

    private final Logger log = LoggerFactory.getLogger(CostController.class);

    @Autowired
    SystemProperties systemProperties;


    private String PREFIX = "/paybackMgr/costOrder/";

    /**
     * 跳转到代扣订单首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "costOrder.html";
    }

    /**
     * 跳转到添加代扣订单
     */
    @RequestMapping("/costOrder_add")
    public String costOrderAdd() {
        return PREFIX + "costOrder_add.html";
    }

    /**
     * 跳转到修改代扣订单
     */
    @RequestMapping("/costOrder_update/{costOrderId}")
    public String costOrderUpdate(@PathVariable Integer costOrderId, Model model) {
        return PREFIX + "costOrder_edit.html";
    }

    /**
     * 获取代扣订单列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String mobile,@RequestParam(required = false) String merOrderNo, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime,  @RequestParam(required = false) String status) {

        List<Map<String, Object>> list = null;
        this.log.info("invoke sendCostTrans merOrderNo:{}",merOrderNo);
        Map<String,String> req= new HashMap<String,String>();
        req.put("mobile",mobile);
        req.put("merOrderNo",merOrderNo);
        req.put("beginTime",beginTime);
        req.put("endTime",endTime);
        req.put("status",status);
        try{
            //请求paycenter-service查询订单信息
            String result = HttpClientUtil.doPost(this.systemProperties.getPaycenter().getCostQueryUrl(), req, "utf-8");
            list = CommonUtil.json2Object(result, List.class);
        }catch (Exception e){
            this.log.error(e.getMessage(),e);
        }

        return super.warpObject(new CostOrderWarpper(list));

    }

    /**
     * 新增代扣订单
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add() {
        return super.SUCCESS_TIP;
    }

    /**
     * 删除代扣订单
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete() {
        return SUCCESS_TIP;
    }


    /**
     * 修改代扣订单
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update() {
        return super.SUCCESS_TIP;
    }

    /**
     * 代扣订单详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Object detail() {
        return null;
    }
}
