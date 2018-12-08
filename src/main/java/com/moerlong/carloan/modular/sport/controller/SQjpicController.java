package com.moerlong.carloan.modular.sport.controller;

import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.sport.entity.SQjpic;
import com.moerlong.carloan.modular.sport.service.SQjPicService;
import com.moerlong.carloan.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Api(tags = { "controller接口类" })
public class SQjpicController {

	private final Logger log = LoggerFactory.getLogger(SQjpicController.class);

	@Autowired
	SQjPicService service;
	@Value("${file.identity_pic_urls}")
	private String idPicUrls;

	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "TelecomRoamInfo", value = "明细")
	@RequestMapping(value = "/qjpic/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(SQjpic entity) {
		Map<String, Object> res = new HashMap<>();
		try {
			if(entity.getId()!=null && service.selectById(entity.getId())!=null) {
				service.updateWithOutNull(entity);
			}else {
				service.save(entity);
			}
			res.put("qcId",entity.getId());
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
	@RequestMapping(value = "/qjpic/updateWithOutNull", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWithOutNull(SQjpic entity) {
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

	@ApiOperation(value = "根据id一次删除多个")
	@ApiImplicitParam(paramType = "body", name = "param", required = false, dataType = "Map", value = "参数")
	@RequestMapping(value = "/qjpic/deleteByIds", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object deleteByIds(@RequestParam String ids) {
		Map<String, Object> res = new HashMap<>();
		List idList = new ArrayList();
		String [] idsArr = ids.split(",");
		for(int i=0;i<idsArr.length;i++){
			idList.add(Long.parseLong(idsArr[i]));
		}
		try {
			int i = service.deleteByIds(idList);
			if(i>0){
				res.put("status", 0);
				res.put("errMsg", "删除成功");
			}else{
				res.put("status", 1);
				res.put("errMsg", "删除失败");
			}

		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}

	@ApiOperation(value = "删除")
	@ApiImplicitParam(paramType = "body", name = "param", required = false, dataType = "Map", value = "参数")
	@RequestMapping(value = "/qjpic/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object deleteById(@RequestParam Map<String,Object> param) {
	
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
	@RequestMapping(value = "/qjpic/deleteLogicById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/qjpic/findById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/qjpic/listAll", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object listAll(@RequestParam Map<String,Object> params) {
		Map<String, Object> res = new HashMap<>();
		try {
			res.put("data", service.listAll(params));
			res.put("status", 0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}

	@ApiOperation(value = "手机端分页查询")
	@ApiImplicitParam(paramType = "body", name = "queryMap", required = false, dataType = "Map", value = "查询条件")
	@RequestMapping(value = "/qjpic/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestParam Map<String,Object> queryMap) {
		this.log.info("/qjpic/pageQuery param:{}",queryMap);
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
			res.put("data",pageInfo);
			res.put("status", 0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}

		return res;
	}


	@ApiOperation(value = "后台分页查询")
	@ApiImplicitParam(paramType = "body", name = "queryMap", required = false, dataType = "Map", value = "查询条件")
	@RequestMapping(value = "/qjpic/houtai/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object htPageQuery(@RequestParam Map<String,Object> queryMap) {
		this.log.info("/qjpic/houtai/pageQuery param:{}",queryMap);
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

			PageInfo pageInfo = this.service.selectPage(pageSize, pageNum, queryMap);
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
	 * 跳转添加器材页面
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/qjpic/showAddQc")
	public String showAddCd(@RequestParam String deptId, @RequestParam String deptPid, Model model) {
		model.addAttribute("deptPid",deptPid);
		model.addAttribute("idPicUrls",idPicUrls);
		return "/sport/addqc.html";
	}

	/**
	 * 跳转器材修改页面
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/qjpic/showQcDetail")
	public String showCdDetail(@RequestParam Integer qcId,@RequestParam String deptPid, Model model) {
		SQjpic SQjpic = this.service.selectById(qcId);
		model.addAttribute("SQjpic",SQjpic);
		model.addAttribute("deptPid",deptPid);
		model.addAttribute("idPicUrls",idPicUrls);
		return "/sport/addqc.html";
	}

	/**
	 * 跳转器材列表页面
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/qjpic/showQcs")
	public String showQcs(@RequestParam Integer deptPid,@RequestParam Integer deptId, Model model) {
		model.addAttribute("idPicUrls",idPicUrls);
		model.addAttribute("deptId",deptId);
		model.addAttribute("deptPid",deptPid);
		return "/sport/showqcs.html";
	}



	/**
	 * 后台跳转器材列表页面
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/qjpic/showAllQc")
	public String showAllQc(@RequestParam Integer deptId, Model model) {
		model.addAttribute("idPicUrls",idPicUrls);
		model.addAttribute("deptId",deptId);
		return "/sporthoutai/allqc.html";
	}

	/**
	 * 后台跳转添加器材页面
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/qjpic/openAddQc")
	public String openAddQc(@RequestParam String deptId, Model model) {
		model.addAttribute("idPicUrls",idPicUrls);
		return "/sporthoutai/addqc.html";
	}

	/**
	 * 后台跳转修改器材页面
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/qjpic/openQcEdit")
	public String openQcEdit(@RequestParam Integer qcId, Model model) {
		SQjpic SQjpic = service.selectById(qcId);
		model.addAttribute("SQjpic",SQjpic);
		model.addAttribute("idPicUrls",idPicUrls);
		return "/sporthoutai/addqc.html";
	}


	/**
	 * 后台跳转器材查看详细页面
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/qjpic/showHoutaiQcDetail")
	public String showHoutaiQcDetail(@RequestParam Integer qcId, Model model) {
		SQjpic SQjpic = service.selectById(qcId);
		model.addAttribute("SQjpic",SQjpic);
		model.addAttribute("idPicUrls",idPicUrls);
		return "/sporthoutai/qcdetail.html";
	}

	@RequestMapping("/qjpic/houtai/showQjPic")
	public String showQjPic(@RequestParam String deptId, Model model) {
		model.addAttribute("deptId",deptId);
		model.addAttribute("idPicUrls",idPicUrls);
		return "/sporthoutai/qjpic.html";
	}

}

