package com.moerlong.carloan.modular.payMgr.controller;

import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.core.shiro.ShiroKit;
import com.moerlong.carloan.core.shiro.ShiroUser;
import com.moerlong.carloan.modular.loan.entity.vo.SendMsgVo;
import com.moerlong.carloan.modular.payMgr.entity.PayApproveRecord;
import com.moerlong.carloan.modular.payMgr.entity.PayInfo;
import com.moerlong.carloan.modular.payMgr.entity.enums.PayApproveStatus;
import com.moerlong.carloan.modular.payMgr.service.PayApproveRecordService;
import com.moerlong.carloan.modular.payMgr.service.PayInfoService;
import com.moerlong.carloan.modular.payMgr.service.SMSService;
import com.moerlong.carloan.modular.payMgr.warpper.PayOrderWarpper;
import com.moerlong.carloan.util.CommonUtil;
import com.moerlong.carloan.util.ParamConstants;
import com.moerlong.carloan.util.RepeatRefuseUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

//@Controller
//@Api(tags = { "controller接口类" })
//@RequestMapping(value = "/payApply")
public class PayApplyController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(PayApplyController.class);

	private String PREFIX = "/payMgr/payApply/";

	@Autowired
	PayInfoService service;

	@Autowired
	PayApproveRecordService payApproveRecordService;

	@Autowired
	SMSService	smsService;

	/**
	 * 获取放款总表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam(required = false) String custName, @RequestParam(required = false) String custMobile, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer payStatus) {

		List<PayInfo> res=this.service.listByCondition(custName,custMobile,beginTime,endTime,payStatus);
		String resString="";
		//log.info("放款总表查询返回值为" + JSON.toJSONString(res));
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			resString= CommonUtil.obj2json(res);
			list= CommonUtil.json2Object(resString, List.class);
		}catch (Exception e){
			this.log.error(e.getMessage(),e);
		}
		return super.warpObject(new PayOrderWarpper(list));
	}

	/**
	 * 跳转到放款总表记录
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "payApply.html";
	}

	/**
	 * 跳转到添加放款申请单
	 */
	@RequestMapping("/add")
	public String payAdd() {
		return PREFIX + "payApply_add.html";
	}

	/**
	 * 新增单笔代付
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Object add() {
		return super.SUCCESS_TIP;
	}

	/**
	 * 删除单笔代付
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete() {
		return SUCCESS_TIP;
	}


	/**
	 * 修改单笔代付
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update() {
		return super.SUCCESS_TIP;
	}

	/**
	 * 单笔代付详情
	 */
	@RequestMapping(value = "/detail")
	@ResponseBody
	public Object detail() {
		return null;
	}


	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "PayInfo", value = "明细")
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(@RequestParam String custName,
							   @RequestParam String custIdNo,
							   @RequestParam String contractNo,
							   @RequestParam String custMobile,
							   @RequestParam String bankCardNo,
							   @RequestParam String totalAmount,
							   @RequestParam String periodNum,
							   @RequestParam String bankName,
							   @RequestParam String receptionAmount,
							   @RequestParam String isReplaceCost,
							   @RequestParam String isPerCharge,
							   @RequestParam String receptionDepart,
							   @RequestParam String receptionManager,
							   @RequestParam String transSource,
							   @RequestParam String uuid,
							   HttpSession session) {
		Map<String, Object> res = new HashMap<>();
		ShiroUser shiroUser = ShiroKit.getUser();
		Long operatorId=Long.valueOf(shiroUser.getId());

		try {
			log.info("custName={} uuid={}", custName, uuid);
			RepeatRefuseUtil.repeatRefuse(uuid, session);

			Date now = new Date();
			PayInfo entity=new PayInfo();
			entity.setCustName(custName);
			entity.setContractNo(contractNo);
			entity.setCustIdNo(custIdNo);
			entity.setCustMobile(custMobile);
			entity.setBankCardNo(bankCardNo);
			entity.setBankName(bankName);
			entity.setReceptionDepart(receptionDepart);
			entity.setReceptionManager(receptionManager);
			entity.setTransSource(transSource);
			entity.setPayedAmount(new BigDecimal(0));
			entity.setTotalAmount(new BigDecimal(totalAmount));
			entity.setNotPayAmount(new BigDecimal(totalAmount));
			entity.setPeriodNum(Integer.valueOf(periodNum));
			entity.setFirstBussId(operatorId);
			entity.setPayStatus(PayApproveStatus.CREATE.getValue());
			entity.setPayStatusDesc(PayApproveStatus.CREATE.getDesc());
			entity.setReceptionAmount(new BigDecimal(receptionAmount));
			entity.setIsReplaceCost(Integer.valueOf(isReplaceCost));
			entity.setIsPerCharge(Integer.valueOf(isPerCharge));
			entity.setUpdateTime(now);
			entity.setCreateTime(now);
			entity.setIsDeleted(0);

			service.save(entity);

			PayApproveRecord record = new PayApproveRecord();
			record.setPayId(entity.getId());
			record.setOperatorId(operatorId);
			record.setOperatorName(shiroUser.getName());
			record.setOperatorTime(now);
			record.setOperatorResult(1);
			record.setOperatorTip("申请单提交");
			record.setCreateTime(now);
			record.setUpdateTime(now);
			record.setIsDeleted(0);

			payApproveRecordService.save(record);
			log.info("申请单 id={} name={} save", entity.getId(), entity.getCustName());
			// 这里短信通知业务领导
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("name", custName);
			List<SendMsgVo> mobileList = ParamConstants.getValue(ParamConstants.ROLE_BUSS_MA);
			boolean ret;
			try {
				ret = smsService.sendMsgSMS(mobileList, SMSService.APPLY_TO_AUDIT, param);
				if(ret == false){
					log.error("===>>>申请单：{}, 请求审核，发送短信通知失败", entity.getId());
				}else{
					log.info("===>>>申请单：{}, 请求审核，发送短信通知成功", entity.getId());
				}
			}catch(Exception e){
				log.error("===>>>申请单：{}, 请求审核，发送短信通知异常 {}", entity.getId(), e);
			}

			res.put("status", 0);
			res.put("msg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("msg", e.toString());
		}
		return res;
	}


	@ApiOperation(value = "根据卡号查询银行")
	@ApiImplicitParam(paramType = "form", name = "bankCardNo", required = true, dataType = "String", value = "查询")
	@RequestMapping(value = "/selectBankName", method = RequestMethod.POST)
	@ResponseBody
	public Object selectBankName(@RequestParam String bankCardNo){
		Map<String, Object> res = new HashMap<>();
		res=service.selectBankName(bankCardNo);

		if(res!=null&&res.size()>0){
			log.info("===>>>根据卡号查询银行结果：{}, ", res.toString());
		}

		return res;
	}
}

