package com.moerlong.carloan.modular.cust.controller;

import com.moerlong.carloan.common.exception.BizExceptionEnum;
import com.moerlong.carloan.common.exception.BussinessException;
import com.moerlong.carloan.core.util.ToolUtil;
import com.moerlong.carloan.modular.cust.entity.CustFinanceInfo;
import com.moerlong.carloan.modular.cust.service.CustFinanceInfoService;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//@Controller
//@Api(tags = { "controller接口类" })
public class CustFinanceInfoController {

	private final Logger log = LoggerFactory.getLogger(CustFinanceInfoController.class);

	@Autowired
	CustFinanceInfoService service;
	@Autowired
	ApplyInfoService applyInfoService;

	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "CustFinanceInfo", value = "明细")
	@RequestMapping(value = "/custFinanceInfo/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(@RequestBody CustFinanceInfo entity) {
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
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "CustFinanceInfo", value = "明细")
	@RequestMapping(value = "/custFinanceInfo/updateWithOutNull", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWithOutNull(@RequestBody CustFinanceInfo entity) {
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
	@RequestMapping(value = "/custFinanceInfo/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
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

	@ApiOperation(value = "根据id一次删除多个记录")
	@ApiImplicitParam(paramType = "body", name = "param", required = false, dataType = "Map", value = "参数")
	@RequestMapping(value = "/custFinanceInfo/deleteByIds", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object deleteByIds(@RequestParam String ids) {
		Map<String, Object> res = new HashMap<>();
		List idList = new ArrayList();
		String [] idsArr = ids.split(",");
		for(int i=0;i<idsArr.length;i++){
			idList.add(Long.parseLong(idsArr[i]));
		}
		try {
			service.deleteByIds(idList);
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
	@RequestMapping(value = "/custFinanceInfo/deleteLogicById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/custFinanceInfo/findById", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object findById(@RequestParam Map<String,Object> param) {
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
	@RequestMapping(value = "/custFinanceInfo/listAll", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/custFinanceInfo/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestBody Map<String,Object> queryMap) {
		this.log.info("/custFinanceInfo/pageQuery param:{}",queryMap);
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

	@ApiOperation(value = "根据applyId和type查询")
	@ApiImplicitParam(paramType = "body", name = "param", required = true, dataType = "Map", value = "参数")
	@RequestMapping(value = "/custFinanceInfo/findByApplyIdAndType", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object findByApplyIdAndType(@RequestParam Map<String,Object> param) {
		Map<String, Object> res = new HashMap<>();
		try {
			List<CustFinanceInfo> list = service.selByApplyIdAndType(param);
			res.put("data", list);
			res.put("status", (list==null || list.size()==0)?1:0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}

	/**
	 * 跳转到添加机构查询的页面
	 */
	@RequestMapping("/custFinanceInfo/showAddFinance")
	public String showAddFinance(@RequestParam String applyId,@RequestParam String type,@RequestParam String id, Model model) {
		model.addAttribute("applyId",applyId);
		model.addAttribute("type",type);
		model.addAttribute("financeId",id);
		return "/cust/addFinance.html";
	}
}

