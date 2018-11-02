package com.moerlong.carloan.modular.cust.controller;

import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.common.exception.BizExceptionEnum;
import com.moerlong.carloan.common.exception.BussinessException;
import com.moerlong.carloan.core.shiro.ShiroKit;
import com.moerlong.carloan.core.shiro.ShiroUser;
import com.moerlong.carloan.core.support.HttpKit;
import com.moerlong.carloan.core.util.ToolUtil;
import com.moerlong.carloan.modular.cust.entity.ChannelInfo;
import com.moerlong.carloan.modular.cust.service.ChannelInfoService;
import com.moerlong.carloan.util.CommonUtil;
import com.moerlong.carloan.util.RepeatRefuseUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@Controller
//@Api(tags = { "controller接口类" })
public class ChannelInfoController {

	private final Logger log = LoggerFactory.getLogger(ChannelInfoController.class);

	@Autowired
	ChannelInfoService service;

	/*@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "ChannelInfo", value = "明细")
	@RequestMapping(value = "/cust/channelInfo/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(String uuid,Long id, String channelName, String city, String fanyongRate,
				String accountCardno, String joinMobile, String address, String accountName, String accountBank,
				String joinPerson, String bussName, HttpSession session) {
		Map<String, Object> res = new HashMap<>();
		try {
			Date now = new Date();
			ShiroUser shiroUser = ShiroKit.getUser();
			Long operatorId=Long.valueOf(shiroUser.getId());

			RepeatRefuseUtil.repeatRefuse(uuid, session);
			ChannelInfo entity = new ChannelInfo();
			entity.setChannelName(channelName);
			entity.setCity(city);
			entity.setFanyongRate(new BigDecimal(fanyongRate));
			entity.setAccountCardno(accountCardno);
			entity.setJoinMobile(joinMobile);
			entity.setAddress(address);
			entity.setAccountName(accountName);
			entity.setAccountBank(accountBank);
			entity.setJoinPerson(joinPerson);
			entity.setBussName(bussName);

			if(id == null){
				entity.setCreateTime(now);
				entity.setIsDeleted(0);
				entity.setCreateUserId(operatorId);
				entity.setCreateUserName(shiroUser.getName());
			}
			entity.setUpdateTime(now);
			service.saveOrUpdate(entity);

			res.put("status", 0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.toString());
		}
		return res;
	}*/

	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "ChannelInfo", value = "明细")
	@RequestMapping(value = "/cust/channelInfo/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(String uuid,ChannelInfo entity, HttpSession session) {
		Map<String, Object> res = new HashMap<>();
		try {
			Date now = new Date();
			ShiroUser shiroUser = ShiroKit.getUser();
			Long operatorId=Long.valueOf(shiroUser.getId());

			RepeatRefuseUtil.repeatRefuse(uuid, session);



			if(entity.getId() == null){
				entity.setCreateTime(now);
				entity.setIsDeleted(0);
				entity.setCreateUserId(operatorId);
				entity.setCreateUserName(shiroUser.getName());
			}
			entity.setUpdateTime(now);
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
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "ChannelInfo", value = "明细")
	@RequestMapping(value = "/channelInfo/updateWithOutNull", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWithOutNull(@RequestBody ChannelInfo entity) {
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
	@RequestMapping(value = "/channelInfo/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/channelInfo/deleteLogicById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/channelInfo/findById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/channelInfo/listAll", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/channelInfo/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestBody Map<String,Object> queryMap) {
		this.log.info("/channelInfo/pageQuery param:{}",queryMap);
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
	 * @cjh
	 * 查询渠道名称和ID
	 */
	@ApiOperation(value = "查询渠道名称和ID")
	@RequestMapping(value = "/channelInfo/findChannelId", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object findChannelId() {
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


	@RequestMapping("/cust/channelInfo/getPage")
	public String getChannelPage(){
		return "/cust/channel/channelList.html";
	}

	@RequestMapping("/cust/channelInfo/getAddPage")
	public String getChannelAddPage() {
		return "/cust/channel/channelInfo_add.html";
	}

	/**
	 * 获取渠道列表
	 * @return
	 */
	@RequestMapping(value = "/cust/channelInfo/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam(required = false) String channelName){
		HttpServletRequest request = HttpKit.getRequest();
		int limit = Integer.valueOf(request.getParameter("limit"));
		int offset = Integer.valueOf(request.getParameter("offset"));
		Map<String, Object> res = new HashMap<>();
		Integer pageNum = (offset / limit + 1); //页数从1开始
		Integer pageSize = limit; //页面大小

		try {

			PageInfo<ChannelInfo> pageInfo = this.service.selectPage(pageSize, pageNum,channelName);

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

	@RequestMapping(value = "/cust/channelInfo/channelInfo_edit/{channelId}")
	public String channelInfoEdit(@PathVariable Long channelId, Model model) {
		if (ToolUtil.isEmpty(channelId)) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		ChannelInfo channelInfo = this.service.selectById(channelId);

		model.addAttribute("channelInfo", channelInfo);
		return "/cust/channel/channelInfo_edit.html";
	}


}

