package com.moerlong.carloan.modular.payMgr.controller;

import com.alibaba.fastjson.JSON;
import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.core.shiro.ShiroKit;
import com.moerlong.carloan.core.shiro.ShiroUser;
import com.moerlong.carloan.modular.payMgr.entity.PayInfo;
import com.moerlong.carloan.modular.payMgr.entity.enums.PayApproveStatus;
import com.moerlong.carloan.modular.payMgr.service.PayInfoService;
import com.moerlong.carloan.modular.payMgr.warpper.PayOrderWarpper;
import com.moerlong.carloan.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;

@Controller
@Api(tags = { "controller接口类" })
@RequestMapping(value = "/payApproval")
public class PayApprovalController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(PayApprovalController.class);

	private String PREFIX = "/payMgr/payApproval/";

	@Autowired
	PayInfoService service;



	/**
	 * 跳转到业务经理放款审批记录
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "payApproval.html";
	}

	/**
	 * 获取放款申请单审批记录
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam(required = false) String custName, @RequestParam(required = false) String custMobile, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime) {

		/**只查询需要业务经理审批*/
		Integer payStatus= PayApproveStatus.CREATE.getValue();
		List<PayInfo> res=this.service.listByCondition(custName,custMobile,beginTime,endTime,payStatus);
		String resString="";
		//log.info("查询返回值为" + JSON.toJSONString(res));
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			resString= CommonUtil.obj2json(res);
			list= CommonUtil.json2Object(resString, List.class);
		}catch (Exception e){
			this.log.error(e.getMessage(),e);
		}
		return super.warpObject(new PayOrderWarpper(list));
	}

	/**
	 * 获取财务经理放款审批列表
	 */
	@RequestMapping(value = "/finaList", method = RequestMethod.POST)
	@ResponseBody
	public Object finaList(@RequestParam(required = false) String custName, @RequestParam(required = false) String custMobile, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime) {

		/**只查询需要财务经理审批*/
		List<PayInfo> all = new ArrayList<>();
		Integer payStatus = PayApproveStatus.FIRST_FINANCE_APPROVE.getValue();//还得加上二次财务经理审核
		List<PayInfo> res=this.service.listByCondition(custName,custMobile,beginTime,endTime,payStatus);
		all.addAll(res);
		payStatus = PayApproveStatus.SECOND_FINANCE_APPROVE.getValue();
		res=this.service.listByCondition(custName,custMobile,beginTime,endTime,payStatus);
		all.addAll(res);
		String resString="";
		//log.info("查询返回值为" + JSON.toJSONString(all));
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			resString= CommonUtil.obj2json(all);
			list= CommonUtil.json2Object(resString, List.class);
		}catch (Exception e){
			this.log.error(e.getMessage(),e);
		}
		return super.warpObject(new PayOrderWarpper(list));
	}

	/**
	 * 跳转到财务经理放款审批记录
	 */
	@RequestMapping("finaIndex")
	public String finaIndex() {
		return PREFIX + "finaPayApproval.html";
	}


	/**
	 * 获取前台财务服务费确认列表
	 */
	@RequestMapping(value = "/bussList", method = RequestMethod.POST)
	@ResponseBody
	public Object bussList(@RequestParam(required = false) String custName, @RequestParam(required = false) String custMobile, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime) {

		/**只查询前台手续费收取成功的*/
		List<PayInfo> all = new ArrayList<>();
		Integer payStatus = PayApproveStatus.COST_CHARGE_SUCCESS.getValue();
		List<PayInfo> res=this.service.listByCondition(custName,custMobile,beginTime,endTime,payStatus);
		payStatus = PayApproveStatus.COST_CHARGE_FAIL.getValue();
		List<PayInfo> res2=this.service.listByCondition(custName,custMobile,beginTime,endTime,payStatus);
		all.addAll(res);
		all.addAll(res2);
		String resString="";
		//log.info("查询业务收取服务费确认返回值为" + JSON.toJSONString(all));
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			resString= CommonUtil.obj2json(all);
			list= CommonUtil.json2Object(resString, List.class);
		}catch (Exception e){
			this.log.error(e.getMessage(),e);
		}
		return super.warpObject(new PayOrderWarpper(list));
	}

	/**
	 * 跳转到业务收取服务费确认列表
	 */
	@RequestMapping("bussIndex")
	public String bussIndex() {
		return PREFIX + "bussApproval.html";
	}

}

