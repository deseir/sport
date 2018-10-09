package com.moerlong.carloan.modular.paybackMgr.controller;

import java.math.BigDecimal;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.common.exception.BizExceptionEnum;
import com.moerlong.carloan.common.exception.BussinessException;
import com.moerlong.carloan.common.persistence.model.CommonElement;
import com.moerlong.carloan.config.properties.SystemProperties;
import com.moerlong.carloan.core.shiro.ShiroKit;
import com.moerlong.carloan.core.shiro.ShiroUser;
import com.moerlong.carloan.modular.paybackMgr.service.IOrderSequenceService;
import com.moerlong.carloan.modular.paybackMgr.warpper.CostOrderWarpper;
import com.moerlong.carloan.util.CommonUtil;
import com.moerlong.carloan.util.HttpClientUtil;
import com.moerlong.carloan.util.IDGenerator;
import com.moerlong.carloan.util.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.moerlong.carloan.modular.paybackMgr.entity.CostOrderApply;
import com.moerlong.carloan.modular.paybackMgr.service.CostOrderApplyService;

@Controller
@Api(tags = { "controller接口类" })
public class CostOrderApplyController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(CostOrderApplyController.class);
	@Autowired
	SystemProperties systemProperties;
	@Autowired
	CostOrderApplyService service;
	@Autowired
	IOrderSequenceService orderSequenceService;

	private String PREFIX = "/paybackMgr/costOrder/";

	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "CostOrderApply", value = "明细")
	@RequestMapping(value = "/cost/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(@RequestBody CostOrderApply entity) {
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

	@ApiOperation(value = "向三方发起代扣请求")
	@ApiImplicitParam(paramType = "body", name = "id", required = true, dataType = "String", value = "审核单ID")
	@RequestMapping(value = "/cost/confirmCostTrans", method = RequestMethod.POST)
	@ResponseBody
	public Object confirmCostTrans(@RequestParam String id) {
		Map<String, Object> res = new HashMap<>();
		Map<String,Object> req= new HashMap<String,Object>();
		Long idl=Long.valueOf(id);
		CostOrderApply costOrderApply=service.selectById(idl);
		if(costOrderApply==null){
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}

		String accName=costOrderApply.getAccName();
		String idNo=costOrderApply.getIdNo();
		String accNo=costOrderApply.getAccNo();
		String mobile=costOrderApply.getMobile();
		String amount=costOrderApply.getAccName();
		String remark=costOrderApply.getRemark();
		String state=costOrderApply.getState();
		String userUuid=costOrderApply.getUserUuid();

		//1.先检查审核单状态是否是未审核
		if(StringUtils.isBlank(state)|| !"0".equals(state)){
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}

		//2.检查审核单参数是否缺少
		if (StringUtils.isBlank(accName)||StringUtils.isBlank(idNo)||
				StringUtils.isBlank(accNo)||StringUtils.isBlank(mobile)||
				StringUtils.isBlank(amount)||StringUtils.isBlank(userUuid)) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}

		//3.修改审核状态
		ShiroUser shiroUser = ShiroKit.getUser();
		int uid=shiroUser.getId();
		String puserUuid = uid+"";
		String puserName=shiroUser.getName();
		costOrderApply.setPuserUuid(puserUuid);
		costOrderApply.setPuserName(puserName);
		costOrderApply.setUpdateTime(new Date());
		costOrderApply.setState("1");//未审核状态
		try {
			service.saveOrUpdate(costOrderApply);
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
		}

		//4.发送代扣指令到paycenter
		final String channelKey = "HFadface123@12#as";
		String interType = "3000";
		String terminalId = "10011";
		String timestamp = String.valueOf(System.currentTimeMillis());
		String msgSign = MD5Utils.MD5Encode(timestamp + channelKey, "UTF-8");
		CommonElement commonElement = getCommonElement(costOrderApply);
		String transDetails = JSON.toJSONString(commonElement);

		Map<String, String> createMap = Maps.newHashMap();
		createMap.put("interType", interType);
		createMap.put("terminalId", terminalId);
		createMap.put("timestamp", timestamp);
		createMap.put("msgSign", msgSign);
		createMap.put("transDetails", transDetails);

		try{
			//发起代付请求paycenter-service
			String result = HttpClientUtil.doPost(this.systemProperties.getPaycenter().getCostUrl(), createMap, "utf-8");
			res = CommonUtil.json2map(result);
			this.log.info(CommonUtil.obj2json(res));
			if (res!=null&& "200".equals(res.get("status"))){
				res.put("status", 0);
			}else{
				res.put("status", 1);
			}
		}catch (Exception e){
			this.log.error(e.getMessage(),e);
			res.put("status", 1);
		}

		return res;

	}

	/**
	 * 封装请求payCenter实体类
	 * @return
	 */
	private CommonElement getCommonElement(CostOrderApply costOrderApply) {

		String bankAccount = costOrderApply.getAccNo();
		String bankAccountName = costOrderApply.getAccName();
		String tradeAmount = String.valueOf(costOrderApply.getAmount());
		String idNum = costOrderApply.getIdNo();
		String custTel = costOrderApply.getMobile();
		String bankName = costOrderApply.getBankName();
		String userUuid = costOrderApply.getUserUuid();
		String batchNo = costOrderApply.getBatchNo();
		String remark=costOrderApply.getRemark();

		CommonElement commonElement = new CommonElement();
		if (StringUtils.isBlank(batchNo)) {
			batchNo = orderSequenceService.getTradeSequence();
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

		if (!StringUtils.isBlank(custTel)) {
			commonElement.setMobile(custTel);
		}
		if (!StringUtils.isBlank(userUuid)) {
			commonElement.setUserUuid(userUuid);
		}

		if (!StringUtils.isBlank(idNum)) {
			commonElement.setIdNo(idNum);
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


	@ApiOperation(value = "删除")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "form", name = "id", required = true, dataType = "Long", value = "id") })
	@RequestMapping(value = "/cost/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/cost/findById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/cost/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestBody Map<String,Object> queryMap) {
		this.log.info("/cost/pageQuery param:{}",queryMap);
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
	 * 获取代扣订单列表
	 */
	@RequestMapping(value = "/costOrderApply/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam(required = false) String batchNo, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) String status) {

		this.log.info("invoke sendCostTrans batchNo:{}",batchNo);
		List<CostOrderApply> res=this.service.listByCondition(batchNo,beginTime,endTime,status);
		String resString="";
		//log.info("代扣审核查询返回值为" + JSON.toJSONString(res));
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			resString= CommonUtil.obj2json(res);
			list= CommonUtil.json2Object(resString, List.class);
		}catch (Exception e){
			this.log.error(e.getMessage(),e);
		}
		return super.warpObject(new CostOrderWarpper(list));
	}

	/**
	 * 跳转到代付订单首页
	 */
	@RequestMapping("/costOrderApply")

	public String index() {
		return PREFIX + "costOrderApply.html";
	}

}

