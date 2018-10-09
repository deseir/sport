package com.moerlong.carloan.modular.car.controller;

import com.moerlong.carloan.modular.car.entity.CarMortgageInfo;
import com.moerlong.carloan.modular.car.entity.CarTransferInfo;
import com.moerlong.carloan.modular.car.service.CarMortgageInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@Api(tags = { "controller接口类" })
public class CarMortgageInfoController {

	private final Logger log = LoggerFactory.getLogger(CarMortgageInfoController.class);

	@Autowired
	CarMortgageInfoService service;

	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "CarMortgageInfo", value = "明细")
	@RequestMapping(value = "/carMortgageInfo/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(@RequestBody CarMortgageInfo entity) {
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
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "CarMortgageInfo", value = "明细")
	@RequestMapping(value = "/carMortgageInfo/updateWithOutNull", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWithOutNull(@RequestBody CarMortgageInfo entity) {
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
	@RequestMapping(value = "/carMortgageInfo/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
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
	
	@ApiOperation(value = "逻辑删除")
	@ApiImplicitParam(paramType = "body", name = "param", required = false, dataType = "Map", value = "参数")
	@RequestMapping(value = "/carMortgageInfo/deleteLogicById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/carMortgageInfo/findById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/carMortgageInfo/listAll", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/carMortgageInfo/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestBody Map<String,Object> queryMap) {
		this.log.info("/carMortgageInfo/pageQuery param:{}",queryMap);
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
	@ApiOperation(value = "新增")
	@ApiImplicitParam(paramType = "body", name = "param", required = false, dataType = "Map", value = "参数")
	@RequestMapping(value = "/carMortgageInfo/saveMortgageInfo", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object saveMortgageInfo(@RequestParam Map<String,Object> data) {

		Map<String, Object> res = new HashMap<>();
		CarMortgageInfo mortgageInfo = new CarMortgageInfo();
		Date now = new Date();
		try {
			if(data.get("id")!=""&&data.get("id")!=null&&data.get("id")!="null"){
				mortgageInfo.setIsDeleted(0);
				mortgageInfo.setCreateTime(now);
				mortgageInfo.setUpdateTime(now);
				mortgageInfo.setId(Long.valueOf(data.get("id").toString()));
				mortgageInfo.setName(data.get("custName").toString());
				mortgageInfo.setCertId(data.get("custIdNo").toString());
				if(data.get("acceptConfirmDate")!=null&&!"".equals(data.get("acceptConfirmDate"))){
					mortgageInfo.setRegDate(new SimpleDateFormat("YYYY-MM-DD").parse(data.get("acceptConfirmDate").toString()));
				}
				updateWithOutNull(mortgageInfo);
			}else{
				mortgageInfo.setCreateTime(now);
				mortgageInfo.setUpdateTime(now);
				mortgageInfo.setIsDeleted(0);
				mortgageInfo.setCarId(Long.valueOf(data.get("CarInfoId2").toString()));
				mortgageInfo.setName(data.get("custName").toString());
				mortgageInfo.setCertId(data.get("custIdNo").toString());
				if(data.get("acceptConfirmDate")!=null&&!"".equals(data.get("acceptConfirmDate"))){
					mortgageInfo.setRegDate(new SimpleDateFormat("YYYY-MM-DD").parse(data.get("acceptConfirmDate").toString()));
				}

				saveOrUpdate(mortgageInfo);
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
}

