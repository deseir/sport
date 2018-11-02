package com.moerlong.carloan.modular.payMgr.controller;

import java.math.BigDecimal;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.core.shiro.ShiroKit;
import com.moerlong.carloan.core.shiro.ShiroUser;
import com.moerlong.carloan.modular.payMgr.entity.PayApproveRecord;
import com.moerlong.carloan.modular.payMgr.entity.PayDetailInfo;
import com.moerlong.carloan.modular.payMgr.entity.enums.PayApproveStatus;
import com.moerlong.carloan.modular.payMgr.service.PayApproveRecordService;
import com.moerlong.carloan.modular.payMgr.service.PayDetailInfoService;
import com.moerlong.carloan.modular.payMgr.service.SMSService;
import com.moerlong.carloan.modular.payMgr.warpper.PayOrderWarpper;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentInfo;
import com.moerlong.carloan.modular.paybackMgr.entity.enums.PayStatus;
import com.moerlong.carloan.modular.paybackMgr.service.IOrderSequenceService;
import com.moerlong.carloan.modular.paybackMgr.warpper.CostOrderWarpper;
import com.moerlong.carloan.util.CommonUtil;
import com.moerlong.carloan.util.RepeatRefuseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.moerlong.carloan.modular.payMgr.entity.PayInfo;
import com.moerlong.carloan.modular.payMgr.service.PayInfoService;

import javax.servlet.http.HttpSession;

//@Controller
//@Api(tags = { "controller接口类" })
//@RequestMapping(value = "/payInfo")
public class PayInfoController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(PayInfoController.class);

	private String PREFIX = "/payMgr/payInfo/";

	@Autowired
	PayInfoService service;

	@Autowired
	private PayApproveRecordService payApproveRecordService;

	@Autowired
	private SMSService smsService;

	@Autowired
	private PayDetailInfoService payDetailInfoService;

	@Autowired
	private IOrderSequenceService orderSequenceService;

	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "PayInfo", value = "明细")
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(@RequestBody PayInfo entity) {
		Map<String, Object> res = new HashMap<>();
		try {
			service.saveOrUpdate(entity);
			res.put("status", 0);
			res.put("msg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("msg", e.toString());
		}
		return res;
	}

	@ApiOperation(value = "删除")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "form", name = "id", required = true, dataType = "Long", value = "id") })
	@RequestMapping(value = "/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object deleteById(Long id) {
		Map<String, Object> res = new HashMap<>();
		try {
			service.delete(id);
			res.put("status", 0);
			res.put("msg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("msg", e.getMessage());
		}
		return res;
	}

	@ApiOperation(value = "根据ID查找")
	@ApiImplicitParam(paramType = "form", name = "id", required = true, dataType = "Long", value = "id")
	@RequestMapping(value = "/findById", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object findById(Long id) {
		Map<String, Object> res = new HashMap<>();
		try {
			res.put("entity", service.selectById(id));
			res.put("status", 0);
			res.put("msg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("msg", e.getMessage());
		}
		return res;
	}

	@ApiOperation(value = "分页查询")
	@ApiImplicitParam(paramType = "body", name = "queryMap", required = false, dataType = "Map", value = "查询条件")
	@RequestMapping(value = "/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestBody Map<String,Object> queryMap) {
		this.log.info("/pageQuery param:{}",queryMap);
		Map<String, Object> res = new HashMap<>();
		Integer pageNum = 1; //页数从1开始
		Integer pageSize = 10; //页面大小
		String orderCondition = " order by id desc ";	
			
		try {
			if(queryMap!=null) {
				if(queryMap.get("pageNum")!=null) {
					pageNum = (Integer)queryMap.get("pageNum");
				}
				if(queryMap.get("pageSize")!=null) {
					pageSize = (Integer)queryMap.get("pageSize");
				}
				if(queryMap.get("orderCondition")!=null) {
					orderCondition = (String)queryMap.get("orderCondition");
				}
			}
			
			Object pageInfo = this.service.selectPage(pageSize, pageNum, orderCondition);
			
			res.put("pageInfo", pageInfo);
			res.put("status", 0);
			res.put("msg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("msg", e.getMessage());
		}
		
		return res;
	}

	/**
	 * 财务审批
	 * @param payId
	 * @param amount
	 * @param operatorResult
	 * @param operatorTip
	 * @return
	 */
	@RequestMapping(value = "/payLoanRequest", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object payLoanRequest(@RequestParam Long payId, @RequestParam String amount, @RequestParam Integer operatorResult,
								 @RequestParam String operatorTip,
								 @RequestParam String uuid,
								 HttpSession session) {

		Map<String, Object> res = new HashMap<>();
		ShiroUser shiroUser = ShiroKit.getUser();
		Long operatorId=Long.valueOf(shiroUser.getId());
		Date now = new Date();
		try{
			RepeatRefuseUtil.repeatRefuse(uuid, session);
			ResultVO<Object> vo =  service.financeApprove(payId, amount, operatorResult, operatorTip, operatorId, shiroUser.getName());

			res.put("status", vo.getStatus());
			res.put("msg", vo.getMsg());
		}catch(Exception e){
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("msg", e.toString());
		}

		return res;
	}

	/**
	 * 财务前期费用补签，获取前期费用
	 * @param payId
	 * @return
	 */
	@RequestMapping(value = "/getPreFeeByPayId", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object getPreFeeByPayId(@RequestParam Long payId) {

		Map<String, Object> res = new HashMap<>();
		ShiroUser shiroUser = ShiroKit.getUser();
		Long operatorId=Long.valueOf(shiroUser.getId());
		Date now = new Date();
		try{
			ResultVO<Object> vo =  service.getPreFeeByPayId(payId);
			res.put("status", vo.getStatus());
			res.put("data", vo.getData());
			res.put("msg", vo.getMsg());
		}catch(Exception e){
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("msg", e.toString());
		}

		return res;
	}


	/**
	 * 前期费用失败，财务补签
	 * @param payId
	 * @return
	 */
	@RequestMapping(value = "/preRepayFailManual", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object preRepayFailManual(@RequestParam Long payId, @RequestParam Integer payType) {

		Map<String, Object> res = new HashMap<>();
		ShiroUser shiroUser = ShiroKit.getUser();
		Long operatorId=Long.valueOf(shiroUser.getId());
		Date now = new Date();
		try{
			ResultVO<Object> vo =  service.preRepayFailManual(payId, payType, operatorId, shiroUser.getName());

			res.put("status", vo.getStatus());
			res.put("msg", vo.getMsg());
		}catch(Exception e){
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("msg", e.toString());
		}

		return res;
	}

	/**
	 * 获取放款总表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam(required = false) String custName, @RequestParam(required = false) String custMobile, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer payStatus) {

		List<PayInfo> res=this.service.listByCondition(custName,custMobile,beginTime,endTime,payStatus);
		String resString="";
		//log.info("放款总表查询返回值为" + JSON.toJSONString(res));
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
	 * 跳转到放款总表记录
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "payInfo.html";
	}

}

