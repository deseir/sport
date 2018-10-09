package com.moerlong.carloan.modular.payMgr.controller;

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
import com.moerlong.carloan.modular.payMgr.warpper.PayOrderWarpper;
import com.moerlong.carloan.modular.paybackMgr.entity.CostOrderApply;
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
import com.moerlong.carloan.modular.payMgr.entity.PayOrderApply;
import com.moerlong.carloan.modular.payMgr.service.PayOrderApplyService;

@Controller
@Api(tags = { "controller接口类" })
public class PayOrderApplyController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(PayOrderApplyController.class);

	@Autowired
	PayOrderApplyService service;
	@Autowired
	SystemProperties systemProperties;

	private String PREFIX = "/payMgr/payOrder/";

	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "PayOrderApply", value = "明细")
	@RequestMapping(value = "/payo/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(@RequestBody PayOrderApply entity) {
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


	@ApiOperation(value = "向三方发起代付请求")
	@ApiImplicitParam(paramType = "body", name = "id", required = true, dataType = "Integer", value = "代付ID")
	@RequestMapping(value = "/pay/confirmPayTrans", method = RequestMethod.POST)
	@ResponseBody
	public Object confirmPayTrans(@RequestParam String id) {
		Map<String, Object> res = new HashMap<>();
		Map<String,Object> req= new HashMap<String,Object>();
		Long idl=Long.valueOf(id);
		PayOrderApply payOrderApply=service.selectById(idl);
		if(payOrderApply==null){
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}

		String accName=payOrderApply.getAccName();
		String idNo=payOrderApply.getIdNo();
		String accNo=payOrderApply.getAccNo();
		String mobile=payOrderApply.getMobile();
		BigDecimal amount=payOrderApply.getAmount();
		String remark=payOrderApply.getRemark();
		String state=payOrderApply.getState();
		String userUuid=payOrderApply.getUserUuid();
		String bankName=payOrderApply.getBankName();
		String openProvince=payOrderApply.getOpenProvince();
		String openCity=payOrderApply.getOpenCity();
		String bankBranch=payOrderApply.getBankBranch();

		//1.先检查审核单状态是否是未审核
		if(StringUtils.isBlank(state)|| "1".equals(state)){
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}

		//2.检查审核单参数是否缺少
		if (StringUtils.isBlank(accName)||StringUtils.isBlank(idNo)||
				StringUtils.isBlank(accNo)||StringUtils.isBlank(mobile)||
				amount == null ||StringUtils.isBlank(userUuid)||
				StringUtils.isBlank(bankName)) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}

		//3.修改审核状态
		ShiroUser shiroUser = ShiroKit.getUser();
		int uid=shiroUser.getId();
		String puserUuid = uid+"";
		String puserName=shiroUser.getName();
		payOrderApply.setPuserUuid(puserUuid);
		payOrderApply.setPuserName(puserName);
		payOrderApply.setUpdateTime(new Date());
		payOrderApply.setState("1");//未审核状态
		try {
			service.saveOrUpdate(payOrderApply);
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
		}


		//4.支付中心发送请求
		final String channelKey = "HFadface123@12#as";
		String interType = "4001";
		String terminalId = "10011";
		String timestamp = String.valueOf(System.currentTimeMillis());
		String msgSign = MD5Utils.MD5Encode(timestamp + channelKey, "UTF-8");
		CommonElement commonElement = getCommonElement(payOrderApply);
		String transDetails = JSON.toJSONString(commonElement);

		Map<String, String> createMap = Maps.newHashMap();
		createMap.put("interType", interType);
		createMap.put("terminalId", terminalId);
		createMap.put("timestamp", timestamp);
		createMap.put("msgSign", msgSign);
		createMap.put("transDetails", transDetails);

		try{
			//发起代付请求paycenter-service
			String result = HttpClientUtil.doPost(this.systemProperties.getPaycenter().getPayUrl(), createMap, "utf-8");
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
	private CommonElement getCommonElement(PayOrderApply payOrderApply) {

		String bankAccount = payOrderApply.getAccNo();
		String bankAccountName = payOrderApply.getAccName();
		String tradeAmount = String.valueOf(payOrderApply.getAmount());
		String idNum = payOrderApply.getIdNo();
		String custTel = payOrderApply.getMobile();
		String bankName = payOrderApply.getBankName();
		String userUuid = "payfront"+payOrderApply.getUserUuid();
		String batchNo = payOrderApply.getBatchNo();
		String remark=payOrderApply.getRemark();
		String openProvince = payOrderApply.getOpenProvince();
		String openCity = payOrderApply.getOpenCity();
		String bankBranch = payOrderApply.getBankBranch();

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
	@RequestMapping(value = "/payo/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/payo/findById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/payo/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestBody Map<String,Object> queryMap) {
		this.log.info("/payo/pageQuery param:{}",queryMap);
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
	@RequestMapping(value = "/payOrderApply/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam(required = false) String batchNo, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) String status) {

		this.log.info("invoke sendCostTrans batchNo:{}",batchNo);
		List<PayOrderApply> res=this.service.listByCondition(batchNo,beginTime,endTime,status);
		String resString=JSON.toJSONString(res);
		//log.info("代付审核查询返回值为" + JSON.toJSONString(res));
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{

			list= CommonUtil.json2Object(resString, List.class);
		}catch (Exception e){
			this.log.error(e.getMessage(),e);
		}
		return super.warpObject(new PayOrderWarpper(list));
	}

	/**
	 * 跳转到代付订单首页
	 */
	@RequestMapping("/payOrderApply")

	public String index() {
		return PREFIX + "payOrderApply.html";
	}


}

