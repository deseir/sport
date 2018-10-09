package com.moerlong.carloan.modular.cust.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.core.shiro.ShiroKit;
import com.moerlong.carloan.core.shiro.ShiroUser;
import com.moerlong.carloan.core.support.HttpKit;
import com.moerlong.carloan.modular.cust.business.CustomerInfoBusiness;
import com.moerlong.carloan.modular.cust.entity.vo.CustomerInfoVo;
import com.moerlong.carloan.modular.loan.dao.ApplyInfoDao;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import com.moerlong.carloan.util.CommonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import com.moerlong.carloan.modular.cust.entity.CustomerInfo;
import com.moerlong.carloan.modular.cust.service.CustomerInfoService;

import javax.servlet.http.HttpServletRequest;

@Controller
@Api(tags = { "controller接口类" })
public class CustomerInfoController {

	private final Logger log = LoggerFactory.getLogger(CustomerInfoController.class);

	@Autowired
	CustomerInfoService service;
	@Autowired
	private CustomerInfoBusiness customerInfoBusiness;
	@Autowired
	ApplyInfoDao applyInfoDao;
	@Value("${file.identity_pic_urls}")
	private String idPicUrls;

	@Autowired
	ApplyInfoService applyInfoService;
	
	@RequestMapping(value = "/customerInfo/getPage", method = RequestMethod.GET)
	public String getPage() {
		return "/cust/custList.html";
	}
	
	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "CustomerInfo", value = "明细")
	@RequestMapping(value = "/customerInfo/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(@RequestBody CustomerInfo entity) {
		Map<String, Object> res = new HashMap<>();
		try {
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
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "CustomerInfo", value = "明细")
	@RequestMapping(value = "/customerInfo/updateWithOutNull", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWithOutNull(@RequestBody CustomerInfo entity) {
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
	@RequestMapping(value = "/customerInfo/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/customerInfo/deleteLogicById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/customerInfo/findById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/customerInfo/listAll", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/customerInfo/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(String name,Integer limit, Integer offset) {
		Map<String, Object> res = new HashMap<>();
			
		try {

			PageInfo<CustomerInfo> pageInfo = this.service.selectPage(limit, offset, name);
			
			res.put("total",pageInfo.getTotal());
			res.put("rows",pageInfo.getList());
			return CommonUtil.obj2json(res);
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("total",0);
			res.put("rows",null);
		}
		
		return res;
	}

	@ApiOperation(value = "客户详细页面分页查询")
	@ApiImplicitParam(paramType = "body", name = "queryMap", required = false, dataType = "Map", value = "查询条件")
	@RequestMapping(value = "/carInfo/pageCustDetailQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestParam Map<String,Object> queryMap) {
		this.log.info("/customerInfo/pageCustDetailQuery param:{}",queryMap);
		HttpServletRequest request = HttpKit.getRequest();
		int limit = Integer.valueOf(request.getParameter("limit"));
		int offset = Integer.valueOf(request.getParameter("offset"));
		Map<String, Object> res = new HashMap<>();
		Integer pageNum = (offset / limit + 1); //页数从1开始
		Integer pageSize = limit; //页面大小

		try {
			PageInfo<CustomerInfo> pageInfo = this.service.selectPage(pageSize, pageNum, queryMap);
			res.put("total",pageInfo.getTotal());
			res.put("rows",pageInfo.getList());
			return CommonUtil.obj2json(res);
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}

		return res;
	}

	/**
	 * 内勤资料录入界面
	 * @return
	 */
	@RequestMapping("/cust/custNqDataAddPage")
	public String getCustNqDataAddPage(Model model){
		model.addAttribute("idPicUrls",idPicUrls);
		return "/cust/custNqDataAdd.html";
	}
	/**
	 * 内勤资料录入2界面
	 * @return
	 */
	@RequestMapping("/cust/custNqDataAddPage2")
	public String getCustNqDataAddPage2(@RequestParam String applyId,@RequestParam String interfaceAddress, Model model){
		model.addAttribute("applyId",applyId);
		model.addAttribute("interfaceAddress",interfaceAddress);
		model.addAttribute("idPicUrls",idPicUrls);
		return "/cust/custNqDataAdd2.html";
	}

	/**
	 * 跳转内勤提交页面
	 * @return
	 */
	@RequestMapping("/cust/showNqSubmit")
	public String showNqSubmit(@RequestParam String applyId,@RequestParam String interfaceAddress, Model model){
		model.addAttribute("applyId",applyId);
		model.addAttribute("interfaceAddress",interfaceAddress);
		return "/cust/showNq.html";
	}

	/**
	 * 客户新增查询接口
	 * @param name
	 * @param idcard
	 * @return
	 */
	@RequestMapping("/cust/searchCustAddInfo")
	@ResponseBody
	public Object searchCustAddInfo(String name, String idcard){
		return customerInfoBusiness.searchCustHistory(name, idcard);
	}
	/**
	 * 客户新增接口
	 * @param name
	 * @param idcard
	 * @return
	 */
	@RequestMapping("/cust/custAddInfo")
	@ResponseBody
	public Object custAddInfo(String name, String idcard,BigDecimal leftAmount){
		ShiroUser shiroUser = ShiroKit.getUser();
		Long operatorId=Long.valueOf(shiroUser.getId());
		return customerInfoBusiness.custAddInfo(operatorId, shiroUser.getName(), name, idcard,leftAmount);
	}
	/**
	 * 身份证信息录入
	 * cjh
	 */

    @ApiOperation(value = "保存或更新")
    @RequestMapping(value = "/cust/custInfoAdd", method = RequestMethod.POST)
    @ResponseBody
    public Object custInfoAdd(@RequestBody CustomerInfoVo vo) {
        return  this.service.custInfoAdd(vo);
    }
	/**
	 * 获取身份证信息录入信息
	 * cjh
	 */

	@ApiOperation(value = "获取身份证信息录入信息")
	@ApiImplicitParam(paramType = "body", name = "param", required = true, dataType = "ApplyId", value = "查询条件")
	@RequestMapping(value = "/cust/custInfoGetByApplyId", method = RequestMethod.POST)
	@ResponseBody
	public Object custInfoGetByApplyId(@RequestBody Map<String,Object> param) {
		Map<String, Object> res = new HashMap<>();
		try {
			res.put("data",this.service.custInfoGetByApplyId(Long.valueOf(param.get("id").toString())));
			res.put("status", 0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}

	/**
	 * 客户管理跳转查看详细页面
	 * @return
	 */
	@RequestMapping("/cust/showCustomerDetail")
	public String showCustomerDetail(@RequestParam Long custId, Model model) {
		ApplyInfo applyInfo = applyInfoService.selectByCustId(custId);
		model.addAttribute("applyId",applyInfo.getId());
		model.addAttribute("showFlag",1);//标志为查看详细信息页面
		return "/cust/showCustomerDetail.html";
	}
}

