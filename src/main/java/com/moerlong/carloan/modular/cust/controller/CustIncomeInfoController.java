package com.moerlong.carloan.modular.cust.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.moerlong.carloan.common.exception.BizExceptionEnum;
import com.moerlong.carloan.common.exception.BussinessException;
import com.moerlong.carloan.core.util.ToolUtil;
import com.moerlong.carloan.modular.cust.entity.CreditReport;
import com.moerlong.carloan.modular.cust.service.CreditReportService;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import com.moerlong.carloan.util.CalcDti;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.moerlong.carloan.modular.cust.entity.CustIncomeInfo;
import com.moerlong.carloan.modular.cust.service.CustIncomeInfoService;

@Controller
@Api(tags = { "controller接口类" })
public class CustIncomeInfoController {

	private final Logger log = LoggerFactory.getLogger(CustIncomeInfoController.class);

	@Autowired
	CustIncomeInfoService service;
	@Autowired
	ApplyInfoService applyInfoService;
	@Autowired
	CreditReportService creditReportService;

	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "CustIncomeInfo", value = "明细")
	@RequestMapping(value = "/custIncomeInfo/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(@RequestBody CustIncomeInfo entity) {
		Map<String, Object> res = new HashMap<>();
		try {
			Long applyID= entity.getApplyId();
			if (ToolUtil.isEmpty(applyID)) {
				throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
			}
			ApplyInfo applyinfo = applyInfoService.selectById(applyID);
			if (ToolUtil.isEmpty(applyinfo)) {
				throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
			}
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
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "CustIncomeInfo", value = "明细")
	@RequestMapping(value = "/custIncomeInfo/updateWithOutNull", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWithOutNull(@RequestBody CustIncomeInfo entity) {
		Map<String, Object> res = new HashMap<>();
		try {
			entity.setUpdateTime(new Date());
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
	@RequestMapping(value = "/custIncomeInfo/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/custIncomeInfo/deleteLogicById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/custIncomeInfo/findById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/custIncomeInfo/listAll", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/custIncomeInfo/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestBody Map<String,Object> queryMap) {
		this.log.info("/custIncomeInfo/pageQuery param:{}",queryMap);
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

	@ApiOperation(value = "根据applyId查询")
	@ApiImplicitParam(paramType = "body", name = "param", required = true, dataType = "Map", value = "参数")
	@RequestMapping(value = "/custIncomeInfo/findByApplyId", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object findByApplyIdAndType(@RequestParam Map<String,Object> param) {
		Map<String, Object> res = new HashMap<>();
		try {
			CustIncomeInfo incomeInfo = service.selByApplyId(param);

			if(incomeInfo ==null){
				incomeInfo = new CustIncomeInfo();
				Long applyId = Long.parseLong(param.get("applyId").toString());
				ApplyInfo applyInfo = applyInfoService.selectById(applyId);
				if(null != applyInfo){
					incomeInfo.setApplyAmount(applyInfo.getApplyAmount());
					incomeInfo.setApplyPeriod(applyInfo.getApplyPeriod());
				}
			}
			BigDecimal loanMonthAmount =  creditReportService.selAllCreditLoanMonthAmount(param);
			if(loanMonthAmount == null){
				incomeInfo.setCreditLoanMonthAmount(new BigDecimal(0));
			}else{
				incomeInfo.setCreditLoanMonthAmount(loanMonthAmount);
			}

			res.put("data", incomeInfo);
			res.put("status", 0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}

//	@RequestMapping(value = "/custIncomeInfo/calcDti", method = {RequestMethod.POST,RequestMethod.GET})
//	@ResponseBody
//	public Object calcDti(@RequestParam BigDecimal applyAmount,@RequestParam BigDecimal applyPeriod,@RequestParam BigDecimal incomeConfirmAmount,@RequestParam BigDecimal creditLoanMonthAmount) {
//		Map<String, Object> res = new HashMap<>();
//		try {
//			String dti = CalcDti.getDti(applyAmount,applyPeriod,incomeConfirmAmount,creditLoanMonthAmount);
//			res.put("data", dti);
//			res.put("status", 0);
//			res.put("errMsg", "操作成功");
//		} catch (Throwable e) {
//			this.log.error(e.getMessage(), e);
//			res.put("status", 1);
//			res.put("errMsg", e.getMessage());
//		}
//		return res;
//	}

}

