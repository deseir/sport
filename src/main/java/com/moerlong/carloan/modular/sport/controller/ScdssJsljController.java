package com.moerlong.carloan.modular.sport.controller;

import com.moerlong.carloan.modular.sport.entity.SCdssJslj;
import com.moerlong.carloan.modular.sport.service.SCdssJsljService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@Api(tags = { "controller接口类" })
public class ScdssJsljController {

	private final Logger log = LoggerFactory.getLogger(ScdssJsljController.class);

	@Autowired
	SCdssJsljService service;

	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "TelecomRoamInfo", value = "明细")
	@RequestMapping(value = "/cdssjslj/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(SCdssJslj entity) {
		Map<String, Object> res = new HashMap<>();
		try {
			if(entity.getId()!=null && service.selectById(entity.getId())!=null) {
				service.updateWithOutNull(entity);
			}else {
				service.save(entity);
			}
			res.put("jsljId",entity.getId());
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
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "TelecomRoamInfo", value = "明细")
	@RequestMapping(value = "/cdssjslj/updateWithOutNull", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWithOutNull(SCdssJslj entity) {
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
	@RequestMapping(value = "/cdssjslj/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object deleteById(@RequestBody Map<String,Object> param) {
	
		Map<String, Object> res = new HashMap<>();
		try {
			Integer id = Integer.parseInt(param.get("id").toString());
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
	@RequestMapping(value = "/cdssjslj/deleteLogicById", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object deleteLogic(@RequestBody Map<String,Object> param) {
		Map<String, Object> res = new HashMap<>();
		try {
			Integer id = Integer.parseInt(param.get("id").toString());
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
	@RequestMapping(value = "/cdssjslj/findById", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object findById(@RequestParam Map<String,Object> param) {
		Map<String, Object> res = new HashMap<>();
		try {
			Integer id = Integer.parseInt(param.get("id").toString());
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
	@RequestMapping(value = "/cdssjslj/listAll", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/cdssjslj/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestParam Map<String,Object> queryMap) {
		this.log.info("/cdssjslj/pageQuery param:{}",queryMap);
		Map<String, Object> res = new HashMap<>();
		Integer pageNum = 1; //页数从1开始
		Integer pageSize = 10; //页面大小
			
		try {
			if(queryMap!=null) {
				if(queryMap.get("pageNum")!=null&&!"".equals(queryMap.get("pageNum"))) {
					pageNum = Integer.parseInt((String)queryMap.get("pageNum"));
				}
				if(queryMap.get("pageSize")!=null) {
					pageSize = Integer.parseInt((String)queryMap.get("pageSize"));
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
	 * 跳转添加场地页面
	 * @param prjType
	 * @param model
	 * @return
	 */
	@RequestMapping("/cdssjslj/showAddJslj")
	public String showAddJslj(@RequestParam Integer prjId, @RequestParam String prjType, Model model) {
		SCdssJslj jslj = new SCdssJslj();
		jslj.setPrjid(prjId);
		jslj.setPrjtype(prjType);
		model.addAttribute("jslj",jslj);
		return "/sport/addjslj.html";
	}

	/**
	 * 跳转健身路径修改页面
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/cdssjslj/showJsljDetail")
	public String showJsljDetail(@RequestParam Integer jsljId, Model model) {
		SCdssJslj jslj = this.service.selectById(jsljId);
		model.addAttribute("jslj",jslj);
//		ShiroUser shiroUser = ShiroKit.getUser();
//		model.addAttribute("deptId",shiroUser.getDeptId());
		return "/sport/addcd.html";
	}

}

