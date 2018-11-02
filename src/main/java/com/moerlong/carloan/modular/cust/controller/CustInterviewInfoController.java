package com.moerlong.carloan.modular.cust.controller;

import com.moerlong.carloan.common.exception.BizExceptionEnum;
import com.moerlong.carloan.common.exception.BussinessException;
import com.moerlong.carloan.core.shiro.ShiroKit;
import com.moerlong.carloan.core.shiro.ShiroUser;
import com.moerlong.carloan.core.util.ToolUtil;
import com.moerlong.carloan.modular.cust.entity.CustInterviewInfo;
import com.moerlong.carloan.modular.cust.entity.CustomerInfo;
import com.moerlong.carloan.modular.cust.service.CustInterviewInfoService;
import com.moerlong.carloan.modular.cust.service.CustomerInfoService;
import com.moerlong.carloan.modular.loan.dao.ApplyOperatorDao;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.entity.ApplyOperator;
import com.moerlong.carloan.modular.loan.entity.MainApproveRecord;
import com.moerlong.carloan.modular.loan.entity.vo.SendMsgVo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import com.moerlong.carloan.modular.loan.service.MainApproveRecordService;
import com.moerlong.carloan.modular.payMgr.service.SMSService;
import com.moerlong.carloan.util.ParamConstants;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

//@Controller
//@Api(tags = { "controller接口类" })
public class CustInterviewInfoController {

	private final Logger log = LoggerFactory.getLogger(CustInterviewInfoController.class);

	@Autowired
	CustInterviewInfoService service;
	@Autowired
	ApplyInfoService applyInfoService;
	@Resource
	private CustomerInfoService customerInfoService;
	@Resource
	private SMSService smsService;
	@Resource
	private ApplyOperatorDao applyOperatorDao;
	@Resource
	private MainApproveRecordService mainApproveRecordService;
	@Resource
	private CustInterviewInfoService interviewInfoService;
	
	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "CustInterviewInfo", value = "明细")
	@RequestMapping(value = "/custInterviewInfo/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(CustInterviewInfo entity) {
		Map<String, Object> res = new HashMap<>();
		try {
			Long applyID= entity.getApplyId();
			if (ToolUtil.isEmpty(applyID)) {
				throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
			}
			ApplyInfo applyinfo = applyInfoService.selectById(applyID);
			if (ToolUtil.isEmpty(applyinfo)) {
				throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
			}
			if(null != entity.getLoanAmount()&&null !=entity.getLoanPeriod()){
				applyinfo.setApplyAmount(entity.getLoanAmount());
				applyinfo.setApplyPeriod(entity.getLoanPeriod());
				applyInfoService.updateWithOutNull(applyinfo);//更新订单表的金额和期数
			}

			entity.setCustId(applyinfo.getCustId());
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
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "CustInterviewInfo", value = "明细")
	@RequestMapping(value = "/custInterviewInfo/updateWithOutNull", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWithOutNull(@RequestBody CustInterviewInfo entity) {
		Map<String, Object> res = new HashMap<>();
		try {
			entity.setUpdateTime(new Date());
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
	@RequestMapping(value = "/custInterviewInfo/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/custInterviewInfo/deleteLogicById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/custInterviewInfo/findById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/custInterviewInfo/listAll", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/custInterviewInfo/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestBody Map<String,Object> queryMap) {
		this.log.info("/custInterviewInfo/pageQuery param:{}",queryMap);
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

	@ApiOperation(value = "根据applyId查询")
	@ApiImplicitParam(paramType = "body", name = "param", required = true, dataType = "Map", value = "参数")
	@RequestMapping(value = "/custInterviewInfo/findByApplyId", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object findByApplyId(@RequestParam Map<String,Object> param) {
		Map<String, Object> res = new HashMap<>();
		try {
			CustInterviewInfo interviewInfo = service.selByApplyId(param);
			ApplyInfo applyInfo = applyInfoService.selectById(Long.parseLong(param.get("applyId").toString()));
			if(null == interviewInfo){
				interviewInfo = new CustInterviewInfo();
				if(null != applyInfo){
					interviewInfo.setLoanAmount(applyInfo.getApplyAmount());
					interviewInfo.setLoanPeriod(applyInfo.getApplyPeriod());
				}
			}
			if(interviewInfo != null && (interviewInfo.getLoanAmount()==null||interviewInfo.getLoanAmount()==BigDecimal.ZERO
					||interviewInfo.getLoanPeriod()==null||interviewInfo.getLoanPeriod()==0)){
				if(null != applyInfo){
					interviewInfo.setLoanAmount(applyInfo.getApplyAmount());
					interviewInfo.setLoanPeriod(applyInfo.getApplyPeriod());
				}

			}
			res.put("data",interviewInfo);
			res.put("status", 0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}

	@ApiOperation(value = "更新内勤状态")
	@ApiImplicitParam(paramType = "body", name = "param", required = false, dataType = "Map", value = "参数")
	@RequestMapping(value = "/custInterviewInfo/updateNqYcStatus", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object updateNqStatus(@RequestParam Map<String,Object> param) {

		Map<String, Object> res = new HashMap<>();
		try {
			Long applyId = Long.valueOf(param.get("applyId").toString());
			String result = param.get("result").toString();
			param.put("applyId",applyId);
			ApplyInfo applyInfo = applyInfoService.selectById(applyId);
			CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
			//发短信通知面审
        	Map<String, Object> sendParam = new HashMap<String, Object>();
			//sendParam.put("name", customerInfo.getName());
			sendParam.put("businessName","车贷业务系统");
			sendParam.put("applyCode",applyId);
			sendParam.put("custName",customerInfo.getName());
			List<SendMsgVo> mobileList = new ArrayList<>();

			boolean ret;
			String smsCode= SMSService.ALL_SUBMIT;

			ShiroUser shiroUser = ShiroKit.getUser();
			Long operatorId=Long.valueOf(shiroUser.getId());

			// 写入借款操作员表
			ApplyOperator applyOperator = new ApplyOperator();
			// 写入审批记录表
			MainApproveRecord record = new MainApproveRecord();

			if(shiroUser.getRoleList().get(0)==5){//
				applyInfo.setStatusDesc("面审主管驳回");
			}else if(shiroUser.getRoleList().get(0)==6){//
				applyInfo.setStatusDesc("面审驳回");
			}
			if("1".equals(result)){//驳回到内勤，更新内勤状态
				applyInfo.setStatus(1000);
				applyInfo.setNqlrStatus(1);
//				applyInfo.setStatusDesc("新增客户");
				applyInfoService.updateWithOutNull(applyInfo);
				applyOperator.setRoleId((long)shiroUser.getRoleList().get(0));//角色
				//成功则通知面审
				mobileList = ParamConstants.getValue(ParamConstants.ROLE_NQ);
				sendParam.put("con", "内勤资料录入");
			}else if("2".equals(result)){//驳回到验车师，更新验车师状态
				applyInfo.setStatus(1000);
//				applyInfo.setStatusDesc("新增客户");
				applyInfo.setYcStatus(1);
				applyInfoService.updateWithOutNull(applyInfo);
				applyOperator.setRoleId((long)shiroUser.getRoleList().get(0));
				mobileList = ParamConstants.getValue(ParamConstants.ROLE_YCS);
				sendParam.put("con", "验车师资料录入");
			}else if("3".equals(result)) {//面审主管驳回到面审
				applyInfo.setStatus(1000);
//				applyInfo.setStatusDesc("新增客户");
				applyInfoService.updateWithOutNull(applyInfo);
				applyOperator.setRoleId((long)shiroUser.getRoleList().get(0));
				mobileList = ParamConstants.getValue(ParamConstants.ROLE_MS);
				sendParam.put("con", "面审资料录入");
			}

			if(shiroUser.getRoleList().get(0)==6){//面审
				applyOperator.setProcessNodeId((long)10002);//节点id
				record.setProcessNodeId((long)10002);
				record.setProcessNodeDesc("面审驳回");

				//面审需要增加或者修改面审意见表
				CustInterviewInfo interviewInfo = interviewInfoService.selByApplyId(param);
				if(null == interviewInfo){
					interviewInfo = new CustInterviewInfo();
				}
				interviewInfo.setApplyId(applyId);
				interviewInfo.setCustId(customerInfo.getId());
				interviewInfo.setOverview(param.get("overview").toString());
				interviewInfo.setInterviewResult(Integer.parseInt(result));
				interviewInfo.setSceneEvidenceUrl1(param.get("sceneEvidenceUrl1").toString());
				interviewInfo.setSceneEvidenceUrl2(param.get("sceneEvidenceUrl2").toString());
//				interviewInfo.setRemark(param.get("remark").toString());
				interviewInfo.setLoanPeriod(0);
				interviewInfo.setLoanAmount(BigDecimal.ZERO);
				interviewInfoService.saveOrUpdate(interviewInfo);
			}else if(shiroUser.getRoleList().get(0)==5){//面审主管
				applyOperator.setProcessNodeId((long)10007);//面审主管驳回
				record.setProcessNodeId((long)10007);
				record.setProcessNodeDesc("面审主管驳回");
			}
			applyOperator.setApplyId(applyId);
			applyOperator.setUserId(operatorId);
			applyOperator.setCreateTime(new Date());
			applyOperator.setUpdateTime(new Date());
			applyOperator.setIsDeleted(0);
			applyOperatorDao.save(applyOperator);

			record.setApplyId(applyId);
			record.setOperatorId(operatorId);
			record.setOperatorName(shiroUser.getName());
			record.setOperatorTime(new Date());
 			record.setAuditRemark(param.get("overview").toString());
			record.setIsDeleted(0);
			record.setCreateTime(new Date());
			record.setUpdateTime(new Date());
			mainApproveRecordService.save(record);
			try{
				ret = smsService.sendMsgSMS(mobileList, smsCode, sendParam);
				if(ret == false){
					log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
				}else{
					log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
				}
			}catch(Exception e){
				log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
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

