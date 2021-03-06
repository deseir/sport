package com.moerlong.carloan.modular.sport.controller;

import com.moerlong.carloan.common.persistence.model.Dept;
import com.moerlong.carloan.modular.cust.controller.TelecomRoamInfoController;
import com.moerlong.carloan.modular.sport.entity.SPrjBase;
import com.moerlong.carloan.modular.sport.service.SPrjBaseService;
import com.moerlong.carloan.modular.system.service.IDeptService;
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
import java.util.List;
import java.util.Map;

@Controller
@Api(tags = { "controller接口类" })
public class SPrjBaseController {

	private final Logger log = LoggerFactory.getLogger(TelecomRoamInfoController.class);

	@Autowired
	SPrjBaseService service;

	@Autowired
	IDeptService deptService;

	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "TelecomRoamInfo", value = "明细")
	@RequestMapping(value = "/sprjbase/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(SPrjBase entity) {
		Map<String, Object> res = new HashMap<>();
		try {
			res.put("deptName",entity.getPlace());
			List<Dept> list = deptService.selectByDeptName(res);
			if(list==null || (null != list && list.size()<=0)){
				res.put("status", 1);
				res.put("errMsg","请输入正确的建设地点！");
				return res;
			}else{
				entity.setDeptsubid(list.get(0).getId().toString());
			}

			if(entity.getId()!=null && service.selectById(entity.getId())!=null) {
				service.updateWithOutNull(entity);
			}else {
				service.save(entity);
			}
			res.put("prjId",entity.getId());
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
	@RequestMapping(value = "/sprjbase/updateWithOutNull", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWithOutNull(SPrjBase entity) {
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
	@RequestMapping(value = "/sprjbase/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/sprjbase/deleteLogicById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/sprjbase/findById", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object findById(@RequestBody Map<String,Object> param) {
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
	@RequestMapping(value = "/sprjbase/listAll", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/sprjbase/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestParam Map<String,Object> queryMap) {
		this.log.info("/sprjbase/pageQuery param:{}",queryMap);
		Map<String, Object> res = new HashMap<>();
		Integer pageNum = 1; //页数从1开始
		Integer pageSize = 8; //页面大小

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

	@ApiOperation(value = "根据type 查询所有的工程")
	@RequestMapping(value = "/sprjbase/getPrjsByType", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object getPrjsByType() {
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

	/**
	 * 跳转场地列表页面
	 * @param prjType
	 * @param model
	 * @return
	 */
	@RequestMapping("/sprjbase/showCdList")
	public String showAddCd(@RequestParam Integer prjId, @RequestParam String prjType, Model model) {
		model.addAttribute("prjId",prjId);
		model.addAttribute("prjType",prjType);
		return "/sport/cdlist.html";
	}

	/**
	 * 跳转场地列表页面
	 * @param prjType
	 * @param model
	 * @return
	 */
	@RequestMapping("/sprjbase/showJsljList")
	public String showJsljList(@RequestParam Integer prjId, @RequestParam String prjType, Model model) {
		model.addAttribute("prjId",prjId);
		model.addAttribute("prjType",prjType);
		return "/sport/jsljlist.html";
	}

	/**
	 * 跳转其他列表页面
	 * @param prjType
	 * @param model
	 * @return
	 */
	@RequestMapping("/sprjbase/showQtList")
	public String showQtList(@RequestParam Integer prjId, @RequestParam String prjType, Model model) {
		model.addAttribute("prjId",prjId);
		model.addAttribute("prjType",prjType);
		return "/sport/qtlist.html";
	}
}

