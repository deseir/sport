package com.moerlong.carloan.modular.cust.controller;

import java.util.HashMap;
import java.util.Map;

import com.moerlong.carloan.modular.cust.entity.vo.FamilyBookInfoVo;
import com.moerlong.carloan.modular.cust.service.CustomerInfoService;
import com.moerlong.carloan.modular.cust.service.MarryInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.moerlong.carloan.modular.cust.entity.FamilyBookInfo;
import com.moerlong.carloan.modular.cust.service.FamilyBookInfoService;

//@Controller
//@Api(tags = { "controller接口类" })
public class FamilyBookInfoController {
	private static String PREFIX = "/cust/";
	private final Logger log = LoggerFactory.getLogger(FamilyBookInfoController.class);
	@Autowired
	MarryInfoService marryInfoService;

	@Autowired
	FamilyBookInfoService service;
	@Autowired
	CustomerInfoService customerInfoService;
	@Value("${file.identity_pic_urls}")
	private String idPicUrls;
	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "FamilyBookInfo", value = "明细")
	@RequestMapping(value = "/familyBookInfo/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(@RequestBody FamilyBookInfo entity) {
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
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "FamilyBookInfo", value = "明细")
	@RequestMapping(value = "/familyBookInfo/updateWithOutNull", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWithOutNull(@RequestBody FamilyBookInfo entity) {
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
	@RequestMapping(value = "/familyBookInfo/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/familyBookInfo/deleteLogicById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/familyBookInfo/findById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/familyBookInfo/listAll", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/familyBookInfo/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestBody Map<String,Object> queryMap) {
		this.log.info("/familyBookInfo/pageQuery param:{}",queryMap);
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
	/**
	 * 户口本信息录入
	 * cjh
	 */

	@ApiOperation(value = "保存或更新户口本信息")
	@ApiImplicitParam(paramType = "body", name = "vo", required = false, dataType = "FamilyBookInfoVo", value = "查询条件")
	@RequestMapping(value = "/familyBookInfo/familyBookInfoAdd",method = RequestMethod.POST)
	@ResponseBody
	public Object familyBookInfoAdd(@RequestBody FamilyBookInfoVo vo) {
		return  this.service.familyBookInfoAdd(vo);
	}
	/**
	 * 获取身份证信息录入信息
	 * cjh
	 */

	@ApiOperation(value = "获取户口本信息录入信息")
	@ApiImplicitParam(paramType = "body", name = "param", required = true, dataType = "ApplyId", value = "查询条件")
	@RequestMapping(value = "/familyBookInfo/familyBookInfoGetByApplyId", method = RequestMethod.POST)
	@ResponseBody
	public Object familyBookInfoGetByApplyId(String ApplyId) {
		Map<String, Object> res = new HashMap<>();
		try {
			res.put("data", this.service.familyBookInfoGetByApplyId( Long.valueOf(ApplyId)));
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
	 * 跳转到添加newAddFamilyBookSubInfo页面
	 */
	@RequestMapping("/familyBookInfo/familyBookInfoPage")
	public String newAddFamilyBookSubInfo( @RequestParam String familyBookInfoId, @RequestParam String applyId,Model model){
		model.addAttribute("familyBookInfoId",familyBookInfoId);
		model.addAttribute("applyId",applyId);
		return "/cust/newAddFamilyBookSubinfo.html";
	}
	/**
	 * 跳转到修改newAddFamilyBookSubInfo页面
	 */
	@RequestMapping("/familyBookInfo/familyBookInfoPage2")
	public String newAddFamilyBookSubInfo2(@RequestParam String familyBookSubInfoRelationship, @RequestParam String familyBookSubInfoName, @RequestParam String familyBookSubInfoCertId,
										   @RequestParam String familyBookSubInfoSex, @RequestParam String bookPhotoUrl, @RequestParam String id, Model model){
		model.addAttribute("familyBookSubInfoRelationship",familyBookSubInfoRelationship);
		model.addAttribute("familyBookSubInfoName",familyBookSubInfoName);
		model.addAttribute("familyBookSubInfoSex",familyBookSubInfoSex);

		model.addAttribute("idPicUrls",idPicUrls);

		if("0".equals(familyBookSubInfoSex)){//男
			model.addAttribute("familyBookSubInfoSexName","男");
		}else if("1".equals(familyBookSubInfoSex)){//女
			model.addAttribute("familyBookSubInfoSexName","女");
		}
		model.addAttribute("familyBookSubInfoCertId",familyBookSubInfoCertId);
		model.addAttribute("bookPhotoUrl",bookPhotoUrl);
		if("".equals(id)){
			model.addAttribute("id","null");
		}else{
			model.addAttribute("id",id);
		}
		return "/cust/newAddFamilyBookSubinfo2.html";
	}
	/**
	 * 回显客户基本信息页面
	 * cjh
	 */

	@ApiOperation(value = "获取户口本信息录入信息")
	@ApiImplicitParam(paramType = "body", name = "param", required = true, dataType = "ApplyId", value = "查询条件")
	@RequestMapping(value = "/familyBookInfo/goBackfamilyBookInfoByApplyId", method = RequestMethod.POST)
	@ResponseBody
	public Object goBackfamilyBookInfoByApplyId(String ApplyId) {
		Map<String, Object> res = new HashMap<>();
		try {
			res.put("data", this.service.familyBookInfoGetByApplyId( Long.valueOf(ApplyId)));
			res.put("data1", customerInfoService.custInfoGetByApplyId( Long.valueOf(ApplyId)));
			res.put("data2",  this.marryInfoService.findmarryInfoByApplyId(Long.valueOf(ApplyId)));
			res.put("idPicUrls",idPicUrls);
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

