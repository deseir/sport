package com.moerlong.carloan.modular.cust.controller;

import com.moerlong.carloan.common.exception.BizExceptionEnum;
import com.moerlong.carloan.common.exception.BussinessException;
import com.moerlong.carloan.core.util.ToolUtil;
import com.moerlong.carloan.modular.cust.entity.CreditReport;
import com.moerlong.carloan.modular.cust.service.*;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@Controller
//@Api(tags = { "controller接口类" })
public class CreditReportController {

	private final Logger log = LoggerFactory.getLogger(CreditReportController.class);

	@Autowired
	CreditReportService service;
	@Autowired
	ApplyInfoService applyInfoService;
	@Autowired
	CreditReportService CreditReportService;
	@Autowired
	CreditPersonalQueryRecordService personalQueryRecordService;
	@Autowired
	CreditBussQueryRecordService bussQueryRecordService;
	@Autowired
	CreditCardDetailService cardDetailService;
	@Autowired
	CreditLoanDetailService loanDetailService;
	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "CreditReport", value = "明细")
	@RequestMapping(value = "/creditReport/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(@RequestBody CreditReport entity) {
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
			entity.setCustId(applyinfo.getCustId());//客户id

			Map map = new HashMap();
			map.put("applyId",entity.getApplyId());
			map.put("type",entity.getType());
			Integer personalCount = personalQueryRecordService.selRecent2MonCount(map);
			Integer bussCount = bussQueryRecordService.selRecent2MonCount(map);
			entity.setHistoryQueryNum((personalCount==null?0:personalCount)+(bussCount==null?0:bussCount));//近两个月征信历史查询次数 (根据记录表 自动算出)

			BigDecimal allUsedAmount = cardDetailService.selAllUsedAmount(map);
			if(allUsedAmount != null ){
				BigDecimal usedAmountTmp = allUsedAmount.divide(new BigDecimal(10));
				entity.setCardMonthAmount(usedAmountTmp);//信用卡月还款（信用卡已用额度/10)
			}else {
				entity.setCardMonthAmount(BigDecimal.ZERO);
			}


			BigDecimal loanAmount = loanDetailService.selLoanAmount(map);
			if(loanAmount != null){
//				BigDecimal loanAmountTmp = loanAmount.setScale(2,BigDecimal.ROUND_HALF_UP);
				entity.setCreditLoanMonthAmount(loanAmount.add(entity.getCardMonthAmount()));//信用贷款月还款金额 (信用贷款月还款+信用卡月还款)
			}else {
				entity.setCreditLoanMonthAmount(BigDecimal.ZERO);
			}
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
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "CreditReport", value = "明细")
	@RequestMapping(value = "/creditReport/updateWithOutNull", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWithOutNull(@RequestBody CreditReport entity) {
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
	@RequestMapping(value = "/creditReport/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/creditReport/deleteLogicById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/creditReport/findById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/creditReport/listAll", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/creditReport/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestBody Map<String,Object> queryMap) {
		this.log.info("/creditReport/pageQuery param:{}",queryMap);
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

	@ApiOperation(value = "根据applyId 和 type 查询")
	@ApiImplicitParam(paramType = "body", name = "param", required = true, dataType = "Map", value = "参数")
	@RequestMapping(value = "/creditReport/findByApplyIdAndType", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object findByApplyIdAndType(@RequestParam Map<String,Object> param) {
 		Map<String, Object> res = new HashMap<>();
		try {
			res.put("data", service.selByApplyIdAndType(param));
			res.put("status", 0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}
}

