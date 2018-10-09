package com.moerlong.carloan.modular.cust.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moerlong.carloan.common.exception.BizExceptionEnum;
import com.moerlong.carloan.common.exception.BussinessException;
import com.moerlong.carloan.core.util.ToolUtil;
import com.moerlong.carloan.modular.cust.entity.*;
import com.moerlong.carloan.modular.cust.service.*;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;


@Controller
@Api(tags = { "controller接口类" })
public class TelecomAuthInfoController {

	private final Logger log = LoggerFactory.getLogger(TelecomAuthInfoController.class);

	@Autowired
	TelecomAuthInfoService service;
	@Autowired
	ApplyInfoService applyInfoService;
	@Autowired
	GetDataService getDataService;
	@Autowired
	TelecomCallRiskAnalysisService callRiskAnalysisService;
	@Autowired
	TelecomRoamInfoService roamInfoService;
	@Autowired
	TelecomFriendCircleService friendCircleService;
	@Autowired
	TelecomCallContactDetailService callContactDetailService;
	@Autowired
	CustomerInfoService customerInfoService;
	@Autowired
	TelecomBasicInfoService telecomBasicInfoService;

	@Value("${system.telecomAuth.url}")
	private String telecomAuthUrl;

	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "TelecomAuthInfo", value = "明细")
	@RequestMapping(value = "/telecomAuthInfo/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(TelecomAuthInfo entity) {
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
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "TelecomAuthInfo", value = "明细")
	@RequestMapping(value = "/telecomAuthInfo/updateWithOutNull", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWithOutNull(@RequestBody TelecomAuthInfo entity) {
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
	@RequestMapping(value = "/telecomAuthInfo/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/telecomAuthInfo/deleteLogicById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/telecomAuthInfo/findById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/telecomAuthInfo/listAll", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/telecomAuthInfo/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestBody Map<String,Object> queryMap) {
		this.log.info("/telecomAuthInfo/pageQuery param:{}",queryMap);
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

	@ApiOperation(value = "根据applyId 和 type 查找")
	@ApiImplicitParam(paramType = "body", name = "param", required = false, dataType = "Map", value = "参数")
	@RequestMapping(value = "/telecomAuthInfo/findByApplyIdAndType", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object findByApplyIdAndType(@RequestParam Map<String,Object> param) {
		Map<String, Object> res = new HashMap<>();
		try {
			TelecomAuthInfo authInfo = service.selectByApplyIdAndType(param);
			res.put("data",authInfo);
			res.put("status", authInfo==null?1:0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}

	@ApiOperation(value = "调用大数据接口获取数据")
	@ApiImplicitParam(paramType = "body", name = "param", required = false, dataType = "Map", value = "参数")
	@RequestMapping(value = "/telecomAuthInfo/getTelecomData", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object getTelecomData(@RequestParam Map<String,Object> param) {
		Map<String, Object> res = new HashMap<>();
		try {
			String userName = param.get("userName").toString();
			String idNum = param.get("idNum").toString();
			String mobile = param.get("mobile").toString();
			Long applyId= Long.parseLong(param.get("applyId").toString());
			Integer type = Integer.parseInt(param.get("type").toString());
			if (ToolUtil.isEmpty(applyId)) {
				throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
			}
			ApplyInfo applyinfo = applyInfoService.selectById(applyId);
			if (ToolUtil.isEmpty(applyinfo)) {
				throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
			}
			Long custId = applyinfo.getCustId();

			//首先查询运营商信息表
			TelecomBasicInfo basicInfo = telecomBasicInfoService.selectByApplyIdAndType(param);
			if(null==basicInfo){//如果没有信息，则需要调用大数据接口获取信息

				String url = telecomAuthUrl+"?account_name="+userName
						+"&id_number="+idNum
						+"&account_mobile="+mobile
						+"&api_token=8c341f8c37b680dc356a0b936f33b9d7";
				HttpMethod method = HttpMethod.GET;
				String jsonText = getDataService.client(url,method);
				if("".equals(jsonText)||null==jsonText){//返回信息如果是空，则没有认证，提示用户去征信系统进行认证
					res.put("status", 1);
					res.put("errMsg", "请先在征信系统进行认证！");
					return res;
				}else {
					JSONObject jsonObj = JSONObject.parseObject(jsonText);
					//客户运营商认证
					JSONArray telecomAuthJsonArray = jsonObj.getJSONArray("cell_phone");
					TelecomAuthInfo authInfo = new TelecomAuthInfo();
					authInfo.setApplyId(applyId);
					authInfo.setType(type);
					authInfo.setCustId(custId);
					for(int i=0;i<telecomAuthJsonArray.size();i++){
						JSONObject jObject = (JSONObject)telecomAuthJsonArray.get(i);
						String keyStr = (String)jObject.get("key");//是否实名  1-是 0-否
						if(keyStr.equals("reliability")){
							if("实名认证".equals(jObject.get("value"))){
								authInfo.setIsRealAuth(1);
							}else if("未实名".equals(jObject.get("value"))){
								authInfo.setIsRealAuth(0);
							}
						}
						if(keyStr.equals("phone_attribution")){//手机归属地
							authInfo.setNativePlace((String)jObject.get("value"));
						}
						if(keyStr.equals("in_time")){//入网时长
							authInfo.setInTime(Integer.parseInt((String)jObject.get("value")));
						}
					}

					//活跃天数
					JSONArray activeJsonArray = jsonObj.getJSONArray("active_degree");
					for(int i=0;i<activeJsonArray.size();i++){
						JSONObject activeJobj = (JSONObject)activeJsonArray.get(i);
						String activeObj = (String)activeJobj.get("app_point");
						if("call_day".equals(activeObj)){
							JSONObject jobj = (JSONObject)activeJobj.get("item");
							authInfo.setActive3m(Integer.parseInt((String) jobj.get(("item_3m"))));//近三个月通话活跃天数
							authInfo.setActive6m(Integer.parseInt((String) jobj.get(("item_6m"))));//近六个月通话活跃天数
							break ;
						}
					}
					service.saveOrUpdate(authInfo);

					//漫游信息
					JSONArray roamJsonArray = jsonObj.getJSONArray("roam_analysis");
					for(int i=0;i<roamJsonArray.size();i++){
						TelecomRoamInfo roamInfo = new TelecomRoamInfo();
						JSONObject jObject = (JSONObject)roamJsonArray.get(i);
						String location  = (String)jObject.get("roam_location");
						Integer roamDayCnt3m  = (Integer)jObject.get("roam_day_cnt_3m");
						Integer roamDayCnt6m  = (Integer)jObject.get("roam_day_cnt_6m");
						Integer continueRoamCnt3m  = (Integer)jObject.get("continue_roam_cnt_3m");
						Integer continueRoamCnt6m  = (Integer)jObject.get("continue_roam_cnt_6m");
						Integer maxRoamDayCnt3m  = (Integer)jObject.get("max_roam_day_cnt_3m");
						Integer maxRoamDayCnt6m  = (Integer)jObject.get("max_roam_day_cnt_6m");
						roamInfo.setApplyId(applyId);
						roamInfo.setType(type);
						roamInfo.setCustId(custId);
						roamInfo.setLocation(location);
						roamInfo.setRoamDayCnt3m(roamDayCnt3m);
						roamInfo.setRoamDayCnt6m(roamDayCnt6m);
						roamInfo.setContinueRoamCnt3m(continueRoamCnt3m);
						roamInfo.setContinueRoamCnt6m(continueRoamCnt6m);
						roamInfo.setMaxRoamDayCnt3m(maxRoamDayCnt3m);
						roamInfo.setMaxRoamDayCnt6m(maxRoamDayCnt6m);
						roamInfoService.saveOrUpdate(roamInfo);

					}

					//风险分析
					JSONArray riskJsonArray = jsonObj.getJSONArray("call_risk_analysis");
					for (int i=0 ;i<riskJsonArray.size();i++){
						TelecomCallRiskAnalysis callRiskAnalysis = new TelecomCallRiskAnalysis();
						JSONObject jObject = (JSONObject)riskJsonArray.get(i);
						String analysisItem  = (String)jObject.get("analysis_item");
						String analysisDesc = (String)jObject.get("analysis_desc");
						JSONObject jboj = (JSONObject)jObject.get("analysis_point");
						Integer callCnt1m = (Integer)jboj.get("call_cnt_1m");
						Integer callCnt3m = (Integer)jboj.get("call_cnt_3m");
						Integer callCnt6m = (Integer)jboj.get("call_cnt_6m");
						Integer avgCallCnt3m = ((BigDecimal)jboj.get("avg_call_cnt_3m")).intValue();
						Integer avgCallCnt6m = ((BigDecimal)jboj.get("avg_call_cnt_6m")).intValue();
						Integer callTime1m = (Integer)jboj.get("call_time_1m");
						Integer callTime3m = (Integer)jboj.get("call_time_3m");
						Integer callTime6m = (Integer)jboj.get("call_time_6m");
						Integer avgCallTime3m = (Integer)jboj.get("avg_call_time_3m");
						Integer avgCallTime6m = (Integer)jboj.get("avg_call_time_6m");
						callRiskAnalysis.setApplyId(applyId);
						callRiskAnalysis.setCustId(custId);
						callRiskAnalysis.setType(type);
						callRiskAnalysis.setAnalysisItem(analysisItem);
						callRiskAnalysis.setAnalysisDesc(analysisDesc);
						callRiskAnalysis.setCallCnt1m(callCnt1m);
						callRiskAnalysis.setCallCnt3m(callCnt3m);
						callRiskAnalysis.setCallCnt6m(callCnt6m);
						callRiskAnalysis.setAvgCallCnt3m(avgCallCnt3m);
						callRiskAnalysis.setAvgCallCnt6m(avgCallCnt6m);
						callRiskAnalysis.setCallTime1m(callTime1m);
						callRiskAnalysis.setCallTime3m(callTime3m);
						callRiskAnalysis.setCallTime6m(callTime6m);
						callRiskAnalysis.setAvgCallTime3m(avgCallTime3m);
						callRiskAnalysis.setAvgCallTime6m(avgCallTime6m);
						callRiskAnalysisService.saveOrUpdate(callRiskAnalysis);
					}


					//朋友圈 top项
					JSONObject friendObj = (JSONObject)jsonObj.get("friend_circle");
					JSONArray friendJsonArray = friendObj.getJSONArray("peer_num_top_list");
					for(int i=0;i<friendJsonArray.size();i++){
						JSONObject jObject = (JSONObject)friendJsonArray.get(i);
						if("peer_num_top3_3m".equals(jObject.get("key"))){//近3月联系人
							JSONArray itemArray = jObject.getJSONArray("top_item");
							for (int j=0;j<itemArray.size();j++){
								JSONObject itemObj = (JSONObject)itemArray.get(i);
								String peerNumber = (String)itemObj.get("peer_number");
								String peerNumLoc = (String)itemObj.get("peer_num_loc");
								String groupName = (String)itemObj.get("group_name");
								String companyName = (String)itemObj.get("company_name");
								String callCnt = String.valueOf(itemObj.get("call_cnt"));
								String callTime = String.valueOf(itemObj.get("call_time"));
								String dialCnt = String.valueOf(itemObj.get("dial_cnt"));
								String dialedCnt = String.valueOf(itemObj.get("dialed_cnt"));
								String dialTime = String.valueOf(itemObj.get("dial_time"));
								String dialedTime = String.valueOf(itemObj.get("dialed_time"));

								TelecomFriendCircle friendCircle = new TelecomFriendCircle();
								friendCircle.setApplyId(applyId);
								friendCircle.setType(type);
								friendCircle.setCustId(custId);
								friendCircle.setPeerNumber(peerNumber);
								friendCircle.setPeerNumLoc(peerNumLoc);
								friendCircle.setGroupName(groupName);
								friendCircle.setCompanyName(companyName);
								friendCircle.setCallCnt(callCnt);
								friendCircle.setCallTime(callTime);
								friendCircle.setDialCnt(dialCnt);
								friendCircle.setDialedCnt(dialedCnt);
								friendCircle.setDialTime(dialTime);
								friendCircle.setDialedTime(dialedTime);
								friendCircle.setKeyTop("peer_num_top3_3m");
								friendCircleService.saveOrUpdate(friendCircle);
							}

						}else if("peer_num_top3_6m".equals(jObject.get("key"))){//近6月联系人
							JSONArray itemArray = jObject.getJSONArray("top_item");
							for (int j=0;j<itemArray.size();j++){
								JSONObject itemObj = (JSONObject)itemArray.get(i);
								String peerNumber = (String)itemObj.get("peer_number");
								String peerNumLoc = (String)itemObj.get("peer_num_loc");
								String groupName = (String)itemObj.get("group_name");
								String companyName = (String)itemObj.get("company_name");
								String callCnt = String.valueOf(itemObj.get("call_cnt"));
								String callTime = String.valueOf(itemObj.get("call_time"));
								String dialCnt = String.valueOf(itemObj.get("dial_cnt"));
								String dialedCnt = String.valueOf(itemObj.get("dialed_cnt"));
								String dialTime = String.valueOf(itemObj.get("dial_time"));
								String dialedTime = String.valueOf(itemObj.get("dialed_time"));

								TelecomFriendCircle friendCircle = new TelecomFriendCircle();
								friendCircle.setApplyId(applyId);
								friendCircle.setType(type);
								friendCircle.setCustId(custId);
								friendCircle.setPeerNumber(peerNumber);
								friendCircle.setPeerNumLoc(peerNumLoc);
								friendCircle.setGroupName(groupName);
								friendCircle.setCompanyName(companyName);
								friendCircle.setCallCnt(callCnt);
								friendCircle.setCallTime(callTime);
								friendCircle.setDialCnt(dialCnt);
								friendCircle.setDialedCnt(dialedCnt);
								friendCircle.setDialTime(dialTime);
								friendCircle.setDialedTime(dialedTime);
								friendCircle.setKeyTop("peer_num_top3_6m");
								friendCircleService.saveOrUpdate(friendCircle);
							}
						}
					}
					//通话记录详细
					JSONArray callDetailJsonArray = jsonObj.getJSONArray("call_contact_detail");

					CustomerInfo customerInfo = customerInfoService.selectById(custId);
					String phone1 = customerInfo.getContractPhone1();
					String phone2 = customerInfo.getContractPhone2();
					String phone3 = customerInfo.getContractPhone3();

					for(int i=0;i<callDetailJsonArray.size();i++) {
						TelecomCallContactDetail callDetail = new TelecomCallContactDetail();
						JSONObject jObject = (JSONObject) callDetailJsonArray.get(i);
						String city = (String)jObject.get("city");
						String peerNum = (String)jObject.get("peer_num");
						String pRelation = (String)jObject.get("p_relation");
						String groupName = (String)jObject.get("group_name");
						String companyName = (String)jObject.get("company_name");
						String callCnt1w = String.valueOf(jObject.get("call_cnt_1w"));
						String callCnt1m = String.valueOf(jObject.get("call_cnt_1m"));
						String callCnt3m = String.valueOf(jObject.get("call_cnt_3m"));
						String callCnt6m = String.valueOf(jObject.get("call_cnt_6m"));
						String callTime3m = String.valueOf(jObject.get("call_time_3m"));
						String callTime6m = String.valueOf(jObject.get("call_time_6m"));
						String dialCnt3m = String.valueOf(jObject.get("dial_cnt_3m"));
						String dialCnt6m = String.valueOf(jObject.get("dial_cnt_6m"));
						String dialTime3m = String.valueOf(jObject.get("dial_time_3m"));
						String dialTime6m = String.valueOf(jObject.get("dial_time_6m"));
						String dialedCnt3m = String.valueOf(jObject.get("dialed_cnt_3m"));
						String dialedCnt6m = String.valueOf(jObject.get("dialed_cnt_6m"));
						String dialedTime3m = String.valueOf(jObject.get("dialed_time_3m"));
						String dialedTime6m = String.valueOf(jObject.get("dialed_time_6m"));
						String callCntMorning3m = String.valueOf(jObject.get("call_cnt_morning_3m"));
						String callCntMorning6m = String.valueOf(jObject.get("call_cnt_morning_6m"));
						String callCntNoon3m = String.valueOf(jObject.get("call_cnt_noon_3m"));
						String callCntNoon6m = String.valueOf(jObject.get("call_cnt_noon_6m"));
						String callCntAfternoon3m = String.valueOf(jObject.get("call_cnt_afternoon_3m"));
						String callCntAfternoon6m = String.valueOf(jObject.get("call_cnt_afternoon_6m"));
						String callCntEvening3m = String.valueOf(jObject.get("call_cnt_evening_3m"));
						String callCntEvening6m = String.valueOf(jObject.get("call_cnt_evening_6m"));
						String callCntNight3m = String.valueOf(jObject.get("call_cnt_night_3m"));
						String callCntNight6m = String.valueOf(jObject.get("call_cnt_night_6m"));
						String callCntWeekday3m = String.valueOf(jObject.get("call_cnt_weekday_3m"));
						String callCntWeekday6m = String.valueOf(jObject.get("call_cnt_weekday_6m"));
						String callCntWeekend3m = String.valueOf(jObject.get("call_cnt_weekend_3m"));
						String callCntWeekend6m = String.valueOf(jObject.get("call_cnt_weekend_6m"));
						String callCntHoliday3m = String.valueOf(jObject.get("call_cnt_holiday_3m"));
						String callCntHoliday6m = String.valueOf(jObject.get("call_cnt_holiday_6m"));
						String callIfWholeDay3m = String.valueOf(jObject.get("call_if_whole_day_3m"));
						String callIfWholeDay6m = String.valueOf(jObject.get("call_if_whole_day_6m"));
						String transStart = String.valueOf(jObject.get("trans_start"));
						String transEnd = String.valueOf(jObject.get("trans_end"));

						callDetail.setApplyId(applyId);
						callDetail.setType(type);
						callDetail.setCustId(custId);
						callDetail.setCity(city);
						callDetail.setPeerNum(peerNum);
						if(peerNum.equals(phone1)||peerNum.equals(phone2)||peerNum.equals(phone3)){
							callDetail.setIsEmergency(1);// 是否是紧急联系人  0--否 1--是
						}else {
							callDetail.setIsEmergency(0);
						}
						callDetail.setPRelation(pRelation);
						callDetail.setGroupName(groupName);
						callDetail.setCompanyName(companyName);
						callDetail.setCallCnt1w(callCnt1w);
						callDetail.setCallCnt1m(callCnt1m);
						callDetail.setCallCnt3m(callCnt3m);
						callDetail.setCallCnt6m(callCnt6m);
						callDetail.setCallTime3m(callTime3m);
						callDetail.setCallTime6m(callTime6m);
						callDetail.setDialCnt3m(dialCnt3m);
						callDetail.setDialCnt6m(dialCnt6m);
						callDetail.setDialTime3m(dialTime3m);
						callDetail.setDialTime6m(dialTime6m);
						callDetail.setDialedCnt3m(dialedCnt3m);
						callDetail.setDialedCnt6m(dialedCnt6m);
						callDetail.setDialedTime3m(dialedTime3m);
						callDetail.setDialedTime6m(dialedTime6m);
						callDetail.setCallCntMorning3m(callCntMorning3m);
						callDetail.setCallCntMorning6m(callCntMorning6m);
						callDetail.setCallCntNoon3m(callCntNoon3m);
						callDetail.setCallCntNoon6m(callCntNoon6m);
						callDetail.setCallCntAfternoon3m(callCntAfternoon3m);
						callDetail.setCallCntAfternoon6m(callCntAfternoon6m);
						callDetail.setCallCntEvening3m(callCntEvening3m);
						callDetail.setCallCntEvening6m(callCntEvening6m);
						callDetail.setCallCntNight3m(callCntNight3m);
						callDetail.setCallCntNight6m(callCntNight6m);
						callDetail.setCallCntWeekday3m(callCntWeekday3m);
						callDetail.setCallCntWeekday6m(callCntWeekday6m);
						callDetail.setCallCntWeekend3m(callCntWeekend3m);
						callDetail.setCallCntWeekend6m(callCntWeekend6m);
						callDetail.setCallCntHoliday3m(callCntHoliday3m);
						callDetail.setCallCntHoliday6m(callCntHoliday6m);
						callDetail.setCallIfWholeDay3m(callIfWholeDay3m);
						callDetail.setCallIfWholeDay6m(callIfWholeDay6m);
						callDetail.setTransStart(transStart);
						callDetail.setTransEnd(transEnd);
						callContactDetailService.saveOrUpdate(callDetail);
					}

					//以上返回的json 数据已经解析完成，然后插入客户运营商信息表
					TelecomBasicInfo telecomBasicInfo = new TelecomBasicInfo();
					telecomBasicInfo.setApplyId(applyId);
					telecomBasicInfo.setType(type);
					telecomBasicInfo.setCustId(custId);
					telecomBasicInfo.setMobile(mobile);
					telecomBasicInfo.setAuditStatus(3);//认证完成
					telecomBasicInfo.setAuditTime(new Date());//认证时间
					telecomBasicInfoService.saveOrUpdate(telecomBasicInfo);
				}
				TelecomAuthInfo authInfo = service.selectByApplyIdAndType(param);
				res.put("status", 0);
				res.put("telecomAuthId",authInfo.getId());
				res.put("errMsg", "操作成功");
			}else{//如果有信息，则不需要调用，直接查询即可
				TelecomAuthInfo authInfo = service.selectByApplyIdAndType(param);
				res.put("status", 0);
				res.put("telecomAuthId",authInfo.getId());
				res.put("errMsg", "操作成功");
			}

		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}

}

