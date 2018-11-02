package com.moerlong.carloan.modular.loan.controller;

import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.core.shiro.ShiroKit;
import com.moerlong.carloan.core.shiro.ShiroUser;
import com.moerlong.carloan.core.support.HttpKit;
import com.moerlong.carloan.modular.loan.dao.ApplyOperatorDao;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.entity.ApplyOperator;
import com.moerlong.carloan.modular.loan.entity.MainApproveRecord;
import com.moerlong.carloan.modular.loan.entity.vo.ApplyBasicInfoVo;
import com.moerlong.carloan.modular.loan.entity.vo.LoanApplyInfoVo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import com.moerlong.carloan.modular.loan.service.MainApproveRecordService;
import com.moerlong.carloan.util.CommonUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@Controller
//@Api(tags = { "controller接口类" })
public class ApplyInfoController {

	private final Logger log = LoggerFactory.getLogger(ApplyInfoController.class);

	@Autowired
	ApplyInfoService service;
	@Resource
	private ApplyOperatorDao applyOperatorDao;
	@Resource
	private MainApproveRecordService mainApproveRecordService;

	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "ApplyInfo", value = "明细")
	@RequestMapping(value = "/applyInfo/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(ApplyInfo entity) {
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
	
	@ApiOperation(value = "只更新非空字段")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "ApplyInfo", value = "明细")
	@RequestMapping(value = "/applyInfo/updateWithOutNull", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWithOutNull(@RequestBody ApplyInfo entity) {
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
	@RequestMapping(value = "/applyInfo/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/applyInfo/deleteLogicById", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object deleteLogic(@RequestBody Map<String,Object> param) {
		Map<String, Object> res = new HashMap<>();
		try {
			Long id = Long.valueOf(param.get("id").toString());
			ApplyInfo applyinfo = service.selectById(id);
			applyinfo.setStatus(0);
			applyinfo.setStatusDesc("订单已删除");
			applyinfo.setIsDeleted(1);//删除
			service.updateWithOutNull(applyinfo);

			// 写入借款操作员表
			ApplyOperator applyOperator = new ApplyOperator();
			ShiroUser shiroUser = ShiroKit.getUser();
			Integer userId = shiroUser.getId();
			Date now = new Date();
			applyOperator.setApplyId(id);
			applyOperator.setUserId(Long.parseLong(userId.toString()));
			applyOperator.setCreateTime(now);
			applyOperator.setUpdateTime(now);
			applyOperator.setIsDeleted(0);
			applyOperatorDao.save(applyOperator);
			// 写入审批记录表
			MainApproveRecord record = new MainApproveRecord();
			record.setApplyId(id);
			record.setOperatorId(Long.parseLong(userId.toString()));
			record.setOperatorName(shiroUser==null?null:shiroUser.getName());
			record.setOperatorTime(now);
			record.setProcessNodeDesc("订单已删除");
			record.setProcessNodeId(0L);
			record.setAuditRemark("订单已删除");
			record.setIsDeleted(0);
			record.setCreateTime(now);
			record.setUpdateTime(now);
			mainApproveRecordService.save(record);
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
	@RequestMapping(value = "/applyInfo/findById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/applyInfo/listAll", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/applyInfo/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestBody Map<String,Object> queryMap) {
		this.log.info("/applyInfo/pageQuery param:{}",queryMap);
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


	@RequestMapping("custAdd/getPage")
	public String getCustAddPage(){
		return "/cust/custAdd.html";
	}

    @RequestMapping("loanApply/getPage")
    public String getLoanApplyPage(){
        return "/loan/loanList.html";
    }

	@RequestMapping(value = "/loanApply/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam(required = false) String custName, @RequestParam(required = false) String custIdNo,
			@RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime){
		HttpServletRequest request = HttpKit.getRequest();
		int limit = Integer.valueOf(request.getParameter("limit"));
		int offset = Integer.valueOf(request.getParameter("offset"));
		Map<String, Object> res = new HashMap<>();
		Integer pageNum = (offset / limit + 1); //页数从1开始
		Integer pageSize = limit; //页面大小

		try {

			PageInfo<LoanApplyInfoVo> pageInfo = this.service.selectPage(pageSize, pageNum,custName, custIdNo, beginTime,endTime);

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
	 * cjh
	 * 保存内勤资料录入借款基本信息
	 *
	 * @return
	 */
	@ApiOperation(value = "保存或更新")
	@RequestMapping(value = "" +
			"/applyInfo/saveOrUpdateApplyInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdateApplyInfo(@RequestBody ApplyBasicInfoVo vo) {
		return  this.service.saveOrUpdateApplyInfo(vo);

	}
	/**
	 * cjh
	 * 获取内勤资料录入借款基本信息
	 *
	 * @return
	 */
	@ApiOperation(value = "获取内勤资料录入借款基本信息")
	@ApiImplicitParam(paramType = "body", name = "param", required = true, dataType = "ApplyId", value = "查询条件")
	@RequestMapping(value = "/applyInfo/getBackApplyInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object getBackApplyInfo(String ApplyId) {
		Map<String, Object> res = new HashMap<>();
		try {
			res.put("data", this.service.getBackApplyInfo( Long.valueOf(ApplyId)));
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

