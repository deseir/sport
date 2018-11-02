package com.moerlong.carloan.modular.loan.controller;

import java.util.HashMap;
import java.util.Map;

import com.moerlong.carloan.common.exception.BizExceptionEnum;
import com.moerlong.carloan.common.exception.BussinessException;
import com.moerlong.carloan.core.util.ToolUtil;
import com.moerlong.carloan.modular.cust.entity.CustInterviewInfo;
import com.moerlong.carloan.modular.cust.service.CustInterviewInfoService;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.moerlong.carloan.modular.loan.entity.FinalJudgementInfo;
import com.moerlong.carloan.modular.loan.service.FinalJudgementInfoService;

//@Controller
//@Api(tags = { "controller接口类" })
public class FinalJudgementInfoController {

	private final Logger log = LoggerFactory.getLogger(FinalJudgementInfoController.class);

	@Autowired
	FinalJudgementInfoService service;
	@Autowired
	ApplyInfoService applyInfoService;
	@Autowired
	CustInterviewInfoService interviewInfoService;

	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "FinalJudgementInfo", value = "明细")
	@RequestMapping(value = "/finalJudgementInfo/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(FinalJudgementInfo entity) {
		Map<String, Object> res = new HashMap<>();
		try {
			Long applyId = entity.getApplyId();
			if (ToolUtil.isEmpty(applyId)) {
				throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
			}
			ApplyInfo applyinfo = applyInfoService.selectById(applyId);
			if (ToolUtil.isEmpty(applyinfo)) {
				throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
			}
			applyinfo.setApplyAmount(entity.getLoanAmount());
			applyinfo.setApplyPeriod(entity.getLoanPeriod());
			applyInfoService.updateWithOutNull(applyinfo);//更新订单表的金额和期数
			entity.setCustId(applyinfo.getCustId());
			service.saveOrUpdate(entity);
			res.put("status", 0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.toString());
		}
		return res;
	}
	
	@ApiOperation(value = "只更新非空字段")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "FinalJudgementInfo", value = "明细")
	@RequestMapping(value = "/finalJudgementInfo/updateWithOutNull", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWithOutNull(@RequestBody FinalJudgementInfo entity) {
		Map<String, Object> res = new HashMap<>();
		try {
			service.updateWithOutNull(entity);
			res.put("status", 0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.toString());
		}
		return res;
	}

	@ApiOperation(value = "删除")
	@ApiImplicitParam(paramType = "body", name = "param", required = false, dataType = "Map", value = "参数")
	@RequestMapping(value = "/finalJudgementInfo/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object deleteById(@RequestBody Map<String,Object> param) {
	
		Map<String, Object> res = new HashMap<>();
		try {
			Long id = Long.valueOf(param.get("id").toString());
			service.delete(id);
			res.put("status", 0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}
	
	@ApiOperation(value = "逻辑删除")
	@ApiImplicitParam(paramType = "body", name = "param", required = false, dataType = "Map", value = "参数")
	@RequestMapping(value = "/finalJudgementInfo/deleteLogicById", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object deleteLogic(@RequestBody Map<String,Object> param) {
		Map<String, Object> res = new HashMap<>();
		try {
			Long id = Long.valueOf(param.get("id").toString());
			service.deleteLogic(id);
			res.put("status", 0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}

	@ApiOperation(value = "根据ID查找")
	@ApiImplicitParam(paramType = "body", name = "param", required = false, dataType = "Map", value = "参数")
	@RequestMapping(value = "/finalJudgementInfo/findById", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object findById(@RequestBody Map<String,Object> param) {
		Map<String, Object> res = new HashMap<>();
		try {
			Long id = Long.valueOf(param.get("id").toString());
			res.put("data", service.selectById(id));
			res.put("status", 0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}
	
	@ApiOperation(value = "显示所有")
	@RequestMapping(value = "/finalJudgementInfo/listAll", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object listAll() {
		Map<String, Object> res = new HashMap<>();
		try {
			res.put("data", service.listAll());
			res.put("status", 0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}

	@ApiOperation(value = "分页查询")
	@ApiImplicitParam(paramType = "body", name = "queryMap", required = false, dataType = "Map", value = "查询条件")
	@RequestMapping(value = "/finalJudgementInfo/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestBody Map<String,Object> queryMap) {
		this.log.info("/finalJudgementInfo/pageQuery param:{}",queryMap);
		Map<String, Object> res = new HashMap<>();
		Integer pageNum = 1; //页数从1开始
		Integer pageSize = 10; //页面大小
			
		try {
			if(queryMap!=null) {
				if(queryMap.get("pageNum")!=null) {
					pageNum = (Integer)queryMap.get("pageNum");
				}
				if(queryMap.get("pageSize")!=null) {
					pageSize = (Integer)queryMap.get("pageSize");
				}
			}
			
			Object pageInfo = this.service.selectPage(pageSize, pageNum, queryMap);
			
			res.put("data", pageInfo);
			res.put("status", 0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		
		return res;
	}

	@ApiOperation(value = "根据applyId查找")
	@ApiImplicitParam(paramType = "body", name = "param", required = false, dataType = "Map", value = "参数")
	@RequestMapping(value = "/finalJudgementInfo/findByApplyId", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object findByApplyId(@RequestParam Map<String,Object> param) {
		Map<String, Object> res = new HashMap<>();
		try {
			Long applyId = Long.valueOf(param.get("applyId").toString());
			FinalJudgementInfo finalJudgementInfo =  service.selectByApplyId(applyId);
			if(null != finalJudgementInfo){
				ApplyInfo applyInfo = applyInfoService.selectById(applyId);
				finalJudgementInfo.setLoanAmount(applyInfo.getApplyAmount());
				finalJudgementInfo.setLoanPeriod(applyInfo.getApplyPeriod());
			}else {
				finalJudgementInfo = new FinalJudgementInfo();
				CustInterviewInfo interviewInfo = interviewInfoService.selByApplyId(param);
				if(null != interviewInfo){
					finalJudgementInfo.setJudgementResult(0);
					finalJudgementInfo.setLoanAmount(interviewInfo.getLoanAmount());
					finalJudgementInfo.setLoanPeriod(interviewInfo.getLoanPeriod());
				}
			}

			res.put("data",finalJudgementInfo);
			res.put("status", finalJudgementInfo!=null?0:1);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}

}

