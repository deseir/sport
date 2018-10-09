package com.moerlong.carloan.modular.car.controller;

import java.util.*;

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
import com.moerlong.carloan.modular.car.entity.CarPhotoInfo;
import com.moerlong.carloan.modular.car.service.CarPhotoInfoService;

@Controller
@Api(tags = { "controller接口类" })
public class CarPhotoInfoController {

	private final Logger log = LoggerFactory.getLogger(CarPhotoInfoController.class);

	@Autowired
	CarPhotoInfoService service;
	@Value("${file.identity_pic_urls}")
	private String idPicUrls;

	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "CarPhotoInfo", value = "明细")
	@RequestMapping(value = "/carPhotoInfo/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(CarPhotoInfo entity) {
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
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "CarPhotoInfo", value = "明细")
	@RequestMapping(value = "/carPhotoInfo/updateWithOutNull", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWithOutNull(@RequestBody CarPhotoInfo entity) {
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
	@RequestMapping(value = "/carPhotoInfo/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object deleteById(@RequestParam Map<String,Object> param) {
	
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
	@RequestMapping(value = "/carPhotoInfo/deleteLogicById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/carPhotoInfo/findById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/carPhotoInfo/listAll", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/carPhotoInfo/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestBody Map<String,Object> queryMap) {
		this.log.info("/carPhotoInfo/pageQuery param:{}",queryMap);
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

	@ApiOperation(value = "根据carId查询")
	@ApiImplicitParam(paramType = "body", name = "queryMap", required =true, dataType = "Map", value = "查询条件")
	@RequestMapping(value = "/carPhotoInfo/selectByCarId", method = RequestMethod.POST)
	@ResponseBody
	public Object selectByCarId(@RequestParam Map<String,Object> queryMap) {
		Map<String, Object> res = new HashMap<>();
		try {
			String id = queryMap.get("carId").toString();
			List<CarPhotoInfo> list = this.service.selectByCarId(id);
			Set<Long> set=new HashSet<>();
			for (CarPhotoInfo car:list) {
				set.add(car.getBigClassId());
			}
			res.put("bigClass",set);
			res.put("data", list);
			res.put("status", (list !=null && list.size() !=0)?0:1);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}

		return res;
	}

	@ApiOperation(value = "根据carId查询")
	@ApiImplicitParam(paramType = "body", name = "queryMap", required =true, dataType = "Map", value = "查询条件")
	@RequestMapping(value = "/carPhotoInfo/selectByBigClassId", method = RequestMethod.POST)
	@ResponseBody
	public Object selectByBigClassId(@RequestParam Map<String,Object> queryMap) {
		Map<String, Object> res = new HashMap<>();
		try {
			Long id = Long.parseLong(queryMap.get("carId").toString());
			List<CarPhotoInfo> list = this.service.selectByBigClassId(id);
			res.put("data", list);
			res.put("status", (list !=null && list.size() !=0)?0:1);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}

		return res;
	}

	/**
	 * 跳转验车师添加照片的页面
	 * @return
	 */
	@RequestMapping("/carPhotoInfo/showAddPics")
	public String showAddPics(@RequestParam String applyId,@RequestParam String carId,@RequestParam String bigClassId, Model model){
		model.addAttribute("applyId",applyId);
		model.addAttribute("carId",carId);
		model.addAttribute("bigClassId",bigClassId);
		model.addAttribute("idPicUrls",idPicUrls);
		return "/cust/addcarverifypics.html";
	}

	/**
	 * 跳转验车师添加照片的页面
	 * @return
	 */
	@RequestMapping("/carPhotoInfo/showAddTitles")
	public String showAddTitles(@RequestParam String applyId,@RequestParam String carId, Model model){
		model.addAttribute("applyId",applyId);
		model.addAttribute("carId",carId);
		model.addAttribute("idPicUrls",idPicUrls);
		return "/cust/addcarverifytitles.html";
	}

}

