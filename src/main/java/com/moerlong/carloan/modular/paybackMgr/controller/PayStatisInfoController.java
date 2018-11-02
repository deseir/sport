package com.moerlong.carloan.modular.paybackMgr.controller;

import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.core.support.HttpKit;
import com.moerlong.carloan.modular.paybackMgr.entity.PayStatisInfo;
import com.moerlong.carloan.modular.paybackMgr.service.PayStatisInfoService;
import com.moerlong.carloan.util.CommonUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

//@Controller
//@Api(tags = { "controller接口类" })
//@RequestMapping(value = "/payStatisInfo")
public class PayStatisInfoController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(PayStatisInfoController.class);

	private String PREFIX = "/report/dayReport/";

	@Autowired
	PayStatisInfoService service;

	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "PayStatisInfo", value = "明细")
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(@RequestBody PayStatisInfo entity) {
		Map<String, Object> res = new HashMap<>();
		try {
			service.saveOrUpdate(entity);
			res.put("status", 0);
			res.put("msg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("msg", e.toString());
		}
		return res;
	}

	@ApiOperation(value = "删除")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "form", name = "id", required = true, dataType = "Long", value = "id") })
	@RequestMapping(value = "/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object deleteById(Long id) {
		Map<String, Object> res = new HashMap<>();
		try {
			service.delete(id);
			res.put("status", 0);
			res.put("msg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("msg", e.getMessage());
		}
		return res;
	}

	@ApiOperation(value = "根据ID查找")
	@ApiImplicitParam(paramType = "form", name = "id", required = true, dataType = "Long", value = "id")
	@RequestMapping(value = "/findById", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object findById(Long id) {
		Map<String, Object> res = new HashMap<>();
		try {
			res.put("entity", service.selectById(id));
			res.put("status", 0);
			res.put("msg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("msg", e.getMessage());
		}
		return res;
	}

	@ApiOperation(value = "分页查询")
	@ApiImplicitParam(paramType = "body", name = "queryMap", required = false, dataType = "Map", value = "查询条件")
	@RequestMapping(value = "/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestBody Map<String,Object> queryMap) {
		this.log.info("/pageQuery param:{}",queryMap);
		Map<String, Object> res = new HashMap<>();
		Integer pageNum = 1; //页数从1开始
		Integer pageSize = 10; //页面大小
		String orderCondition = " order by id desc ";	
			
		try {
			if(queryMap!=null) {
				if(queryMap.get("pageNum")!=null) {
					pageNum = (Integer)queryMap.get("pageNum");
				}
				if(queryMap.get("pageSize")!=null) {
					pageSize = (Integer)queryMap.get("pageSize");
				}
				if(queryMap.get("orderCondition")!=null) {
					orderCondition = (String)queryMap.get("orderCondition");
				}
			}
			
			Object pageInfo = this.service.selectPage(pageSize, pageNum, orderCondition);
			
			res.put("pageInfo", pageInfo);
			res.put("status", 0);
			res.put("msg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("msg", e.getMessage());
		}
		
		return res;
	}

	/**
	 * 获取日报表详细列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime,String deptId) {

		this.log.info("invoke list beginTime:{}",beginTime);
//		List<PayStatisInfo> res=this.service.listByCondition(beginTime,endTime);
//		String resString= "";
//		//log.info("日报表统计返回值为" + JSON.toJSONString(res));
//		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
//		try{
//			resString= CommonUtil.obj2json(res);
//			list= CommonUtil.json2Object(resString, List.class);
//		}catch (Exception e){
//			this.log.error(e.getMessage(),e);
//		}
//		return super.warpObject(new CostOrderWarpper(list));
		HttpServletRequest request = HttpKit.getRequest();
		int limit = Integer.valueOf(request.getParameter("limit"));
		int offset = Integer.valueOf(request.getParameter("offset"));
		Map<String, Object> res = new HashMap<>();
		Integer pageNum = (offset / limit + 1); //页数从1开始
		Integer pageSize = limit; //页面大小

		try {

			PageInfo<PayStatisInfo> pageInfo = this.service.listByCondition(pageSize, pageNum,beginTime,endTime,deptId);

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

	/**
	 * 跳转到日报表首页
	 */
	@RequestMapping("/index")
	public String index() {
		return "/report/dayReport/dayReport.html";
	}

	/**
	 * 跳转到统计报表页
	 */
	@RequestMapping("/countIndex")
	public String countIndex() {
		return "/report/dayReport/countReport.html";
	}


}

