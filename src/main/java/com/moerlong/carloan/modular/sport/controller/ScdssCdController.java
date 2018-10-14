package com.moerlong.carloan.modular.sport.controller;

import com.moerlong.carloan.modular.sport.entity.SCdssCd;
import com.moerlong.carloan.modular.sport.service.SCdssCdService;
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
public class ScdssCdController {

	private final Logger log = LoggerFactory.getLogger(ScdssCdController.class);

	@Autowired
	SCdssCdService service;

	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "TelecomRoamInfo", value = "明细")
	@RequestMapping(value = "/cdsscd/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(SCdssCd entity) {
		Map<String, Object> res = new HashMap<>();
		try {
			if(entity.getId()!=null && service.selectById(entity.getId())!=null) {
				service.updateWithOutNull(entity);
			}else {
				service.save(entity);
			}
			res.put("cdId",entity.getId());
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
	@RequestMapping(value = "/cdsscd/updateWithOutNull", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWithOutNull(SCdssCd entity) {
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
	@RequestMapping(value = "/cdsscd/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/cdsscd/deleteLogicById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/cdsscd/findById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/cdsscd/listAll", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/cdsscd/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestParam Map<String,Object> queryMap) {
		this.log.info("/cdsscd/pageQuery param:{}",queryMap);
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
	@RequestMapping("/cdsscd/showAddCd")
	public String showAddCd(@RequestParam Integer prjId, @RequestParam String prjType, Model model) {
		SCdssCd cdsscd = new SCdssCd();
		cdsscd.setPrjid(prjId);
		cdsscd.setPrjtype(prjType);
		model.addAttribute("cdsscd",cdsscd);
		return "/sport/addcd.html";
	}

	/**
	 * 跳转场地修改页面
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/cdsscd/showCdDetail")
	public String showCdDetail(@RequestParam Integer cdId, Model model) {
		SCdssCd cdsscd = this.service.selectById(cdId);
		model.addAttribute("cdsscd",cdsscd);
//		ShiroUser shiroUser = ShiroKit.getUser();
//		model.addAttribute("deptId",shiroUser.getDeptId());
		return "/sport/addcd.html";
	}


	/**
	 * 跳转添加场地器材的页面
	 * @param prjType
	 * @param model
	 * @return
	 */
	@RequestMapping("/cdsscd/showQcCdList")
	public String showQcCdList(@RequestParam Integer prjId, @RequestParam Integer prjType,@RequestParam Integer cdId, Model model) {
		model.addAttribute("prjId",prjId);
		model.addAttribute("prjType",prjType);
		model.addAttribute("cdId",cdId);
		return "/sport/qccdlist.html";
	}
}

