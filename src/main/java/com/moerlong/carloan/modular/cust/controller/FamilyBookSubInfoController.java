package com.moerlong.carloan.modular.cust.controller;

import com.moerlong.carloan.modular.cust.entity.FamilyBookSubInfo;
import com.moerlong.carloan.modular.cust.service.FamilyBookSubInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@Controller
//@Api(tags = { "controller接口类" })
public class FamilyBookSubInfoController {

	private final Logger log = LoggerFactory.getLogger(FamilyBookSubInfoController.class);

	@Autowired
	FamilyBookSubInfoService service;

	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "FamilyBookSubInfo", value = "明细")
	@RequestMapping(value = "/familyBookSubInfo/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(@RequestBody FamilyBookSubInfo entity) {
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
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "FamilyBookSubInfo", value = "明细")
	@RequestMapping(value = "/familyBookSubInfo/updateWithOutNull", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWithOutNull(@RequestBody FamilyBookSubInfo entity) {
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
	@RequestMapping(value = "/familyBookSubInfo/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object deleteById(@RequestParam Map<String,Object> data) {
	
		Map<String, Object> res = new HashMap<>();
		try {
			Long id = Long.valueOf(data.get("id").toString());
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
	@ApiOperation(value = "新增")
	@ApiImplicitParam(paramType = "body", name = "param", required = false, dataType = "Map", value = "参数")
	@RequestMapping(value = "/familyBookSubInfo/saveFamilyBookSubInfo", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object saveFamilyBookSubInfo(@RequestParam Map<String,Object> data) {

		Map<String, Object> res = new HashMap<>();
		FamilyBookSubInfo familyBookSubInfo = new FamilyBookSubInfo();
		try {
			if(data.get("id")!=""&&data.get("id")!=null&&data.get("id")!="null"){
				familyBookSubInfo.setIsDeleted(0);
				Date now = new Date();
				familyBookSubInfo.setCreateTime(now);
				familyBookSubInfo.setUpdateTime(now);
				familyBookSubInfo.setId(Long.valueOf(data.get("id").toString()));
				familyBookSubInfo.setSex(Integer.valueOf(data.get("familyBookSubInfoSex2").toString()));
				familyBookSubInfo.setRelationship(data.get("familyBookSubInfoRelationship2").toString());
				familyBookSubInfo.setName(data.get("familyBookSubInfoName2").toString());
				familyBookSubInfo.setCertId(data.get("familyBookSubInfoCertId2").toString());
				//familyBookSubInfo.setBookPhotoUrl("/identityPic"+data.get("bookPhotoUrl22").toString().split("/identityPic")[1]);
				familyBookSubInfo.setBookPhotoUrl(data.get("bookPhotoUrl22").toString());
				updateWithOutNull(familyBookSubInfo);
			}else{
				familyBookSubInfo.setBookId(Long.valueOf(data.get("bookId").toString()));
				familyBookSubInfo.setSex(Integer.valueOf(data.get("familyBookSubInfoSex1").toString()));
				familyBookSubInfo.setRelationship(data.get("familyBookSubInfoRelationship1").toString());
				familyBookSubInfo.setName(data.get("familyBookSubInfoName1").toString());
				familyBookSubInfo.setCertId(data.get("familyBookSubInfoCertId1").toString());
				familyBookSubInfo.setBookPhotoUrl(data.get("bookPhotoUrl2").toString());
				saveOrUpdate(familyBookSubInfo);
			}
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
	@RequestMapping(value = "/familyBookSubInfo/deleteLogicById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/familyBookSubInfo/findById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/familyBookSubInfo/listAll", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/familyBookSubInfo/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestBody Map<String,Object> queryMap) {
		this.log.info("/familyBookSubInfo/pageQuery param:{}",queryMap);
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

}

