package com.moerlong.carloan.modular.payMgr.controller;

import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.core.shiro.ShiroKit;
import com.moerlong.carloan.core.shiro.ShiroUser;
import com.moerlong.carloan.modular.payMgr.entity.PayApproveRecord;
import com.moerlong.carloan.modular.payMgr.service.PayApproveRecordService;
import com.moerlong.carloan.modular.payMgr.service.PayDetailInfoService;
import com.moerlong.carloan.modular.payMgr.service.PayInfoService;
import com.moerlong.carloan.modular.payMgr.service.SMSService;
import com.moerlong.carloan.modular.payMgr.warpper.PayOrderWarpper;
import com.moerlong.carloan.util.CommonUtil;
import com.moerlong.carloan.util.RepeatRefuseUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller
//@Api(tags = { "controller接口类" })
//@RequestMapping(value = "/payApproveRecord")
public class PayApproveRecordController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(PayApproveRecordController.class);

	@Autowired
	PayApproveRecordService service;
	@Autowired
	PayInfoService payService;
	@Autowired
	PayDetailInfoService payDetailservice;
	@Autowired
	private PayInfoService payInfoService;

	@Autowired
	SMSService smsService;

	@ApiOperation(value = "业务经理审批")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(@RequestParam Long payId,
							   @RequestParam Integer operatorResult,
							   @RequestParam String operatorTip,
							   @RequestParam String uuid,
							   HttpSession session) {
		Map<String, Object> res = new HashMap<>();
		if(payId==null||operatorResult==null){
			res.put("status", 1);
			res.put("msg", "请求参数有误");
			return res;
		}
		try{
			RepeatRefuseUtil.repeatRefuse(uuid, session);

			ShiroUser shiroUser = ShiroKit.getUser();
			Long operatorId=Long.valueOf(shiroUser.getId());
			String operatorName=shiroUser.getName();

			ResultVO<Object> vo = payInfoService.bussManagerApprove(payId, operatorResult, operatorTip, operatorId, operatorName);
			res.put("status", vo.getStatus());
			res.put("msg", vo.getMsg());

		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("msg", e.toString());
		}
		return res;
	}

	@ApiOperation(value = "财务经理审批")
	@RequestMapping(value = "finaSave", method = RequestMethod.POST)
	@ResponseBody
	public Object finaSaveApprove(@RequestParam Long payId,
							   @RequestParam Integer operatorResult,
							   @RequestParam String operatorTip,
								  @RequestParam String uuid,
								  HttpSession session) {

		this.log.error("--->payId={} ========", payId);

		Map<String, Object> res = new HashMap<>();
		if(payId==null||operatorResult==null){
			res.put("status", 1);
			res.put("msg", "请求参数有误");
			return res;
		}

		ShiroUser shiroUser = ShiroKit.getUser();
		Long operatorId=Long.valueOf(shiroUser.getId());
		String operatorName=shiroUser.getName();

		try{
			RepeatRefuseUtil.repeatRefuse(uuid, session);
			ResultVO<Object> vo = payInfoService.financeManagerApprove(payId, operatorResult, operatorTip, operatorId, operatorName);

			res.put("status", vo.getStatus());
			res.put("msg", vo.getMsg());

		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("msg", e.toString());
		}
		return res;
	}

	@ApiOperation(value = "前台财务手续费确认")
	@RequestMapping(value = "bussSave", method = RequestMethod.POST)
	@ResponseBody
	public Object bussSaveApprove(@RequestParam Long payId) {
		Map<String, Object> res = new HashMap<>();
		if(payId==null){
			res.put("status", 1);
			res.put("msg", "请求参数有误");
			return res;
		}

		ShiroUser shiroUser = ShiroKit.getUser();
		Long operatorId=Long.valueOf(shiroUser.getId());
		String operatorName=shiroUser.getName();

		try{
			ResultVO<Object> vo = payInfoService.ReceptionFinanceApprove(payId, operatorId, operatorName);
			res.put("status", vo.getStatus());
			res.put("msg", vo.getMsg());

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

	@ApiOperation(value = "根据PayID查找")
	@ApiImplicitParam(paramType = "form", name = "id", required = true, dataType = "Long", value = "id")
	@RequestMapping(value = "/findByPayId", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object findByPayId(Long payId) {

		List<PayApproveRecord> res=this.service.listByPayId(payId);
		String resString="";
		//log.info("审批放款详情查询返回值为" + JSON.toJSONString(res));
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			resString= CommonUtil.obj2json(res);
			list= CommonUtil.json2Object(resString, List.class);
		}catch (Exception e){
			this.log.error(e.getMessage(),e);
		}
		return super.warpObject(new PayOrderWarpper(list));
	}

}

