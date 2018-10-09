package com.moerlong.carloan.modular.car.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moerlong.carloan.common.exception.BizExceptionEnum;
import com.moerlong.carloan.common.exception.BussinessException;
import com.moerlong.carloan.common.persistence.dao.DictMapper;
import com.moerlong.carloan.common.persistence.model.Dict;
import com.moerlong.carloan.core.util.ToolUtil;
import com.moerlong.carloan.modular.car.entity.CarInfo;
import com.moerlong.carloan.modular.car.entity.CarVerifyInfo;
import com.moerlong.carloan.modular.car.service.CarInfoService;
import com.moerlong.carloan.modular.car.service.CarVerifyInfoService;
import com.moerlong.carloan.modular.cust.entity.TelecomAuthInfo;
import com.moerlong.carloan.modular.cust.service.GetDataService;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.moerlong.carloan.modular.car.entity.CarPriceInfo;
import com.moerlong.carloan.modular.car.service.CarPriceInfoService;

import javax.annotation.Resource;

@Controller
@Api(tags = { "controller接口类" })
public class CarPriceInfoController {

	private final Logger log = LoggerFactory.getLogger(CarPriceInfoController.class);

	@Autowired
	CarPriceInfoService service;
	@Autowired
	ApplyInfoService applyInfoService;
	@Autowired
	CarInfoService carInfoService;
	@Value("${car300.get_car_price}")
	private String car300PriceUrl;
	@Resource
	DictMapper dictMapper;
	@Autowired
	GetDataService getDataService;
	@Autowired
	CarVerifyInfoService verifyInfoService;
	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "CarPriceInfo", value = "明细")
	@RequestMapping(value = "/carPriceInfo/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(CarPriceInfo entity) {
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
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "CarPriceInfo", value = "明细")
	@RequestMapping(value = "/carPriceInfo/updateWithOutNull", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWithOutNull(@RequestBody CarPriceInfo entity) {
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
	@RequestMapping(value = "/carPriceInfo/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/carPriceInfo/deleteLogicById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/carPriceInfo/findById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/carPriceInfo/listAll", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/carPriceInfo/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestBody Map<String,Object> queryMap) {
		this.log.info("/carPriceInfo/pageQuery param:{}",queryMap);
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
	@RequestMapping(value = "/carPriceInfo/findByApplyId", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object findByApplyIdAndType(@RequestParam Map<String,Object> param) {
		Map<String, Object> res = new HashMap<>();
		try {
			Long applyId = Long.valueOf(param.get("applyId").toString());
			CarPriceInfo carPriceInfo = service.selByApplyId(param);
			if(null == carPriceInfo){
				carPriceInfo = new CarPriceInfo();
			}

			ApplyInfo applyInfo = applyInfoService.selectById(applyId);
			CarInfo carInfo = carInfoService.selectByCusId(applyInfo.getCustId());
			String modelId = "";//车型id
			String regDate = "";//上牌日期 需要格式化为 yyyy-MM
			String makeDate = "";//出厂日期 需要格式化为 yyyy-MM
			String mile = "";//行车公里数
			String zone = "";//城市
			String color = "";//颜色
			String interior = "";//内饰状况
			String surface = "";//漆面状况
			String workState = "";//工况状况

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			if(null != carInfo){
				modelId = carInfo.getCarModelId();//车型id
				regDate = sdf.format(carInfo.getFirstLicDate());//上牌日期 需要格式化为 yyyy-MM
				makeDate = sdf.format(carInfo.getProductDate());//出厂日期 需要格式化为 yyyy-MM
				mile = carInfo.getMileage().toString();//行车公里数
				zone = carInfo.getCarCityId();//城市
				color = carInfo.getCarColor();//颜色
				CarVerifyInfo verifyInfo = verifyInfoService.selectByCarId(carInfo.getId());
				interior = verifyInfo.getNszk();//内饰状况
				surface = verifyInfo.getQmzk();//漆面状况
				workState = verifyInfo.getGkzk();//工况状况
			}
			Dict dict=dictMapper.selectById(40);
			StringBuilder url = new StringBuilder(car300PriceUrl);
			url.append("?token=").append(dict.getName()).append("&modelId=").append(modelId)
					.append("&regDate=").append(regDate).append("&makeDate=").append(makeDate)
					.append("&mile=").append(mile).append("&zone=").append(zone)
					.append("&color=").append(color).append("&interior=").append(interior)
					.append("&surface=").append(surface).append("&workState=").append(workState);

			//调用车300接口返回车300价格
			HttpMethod method = HttpMethod.GET;
			String jsonText = getDataService.client(url.toString(),method);
			if(!"".equals(jsonText)&&null!=jsonText){
				JSONObject jsonObj = JSONObject.parseObject(jsonText);
				boolean success = (boolean)jsonObj.get("success");
				if(success){
					JSONObject jsb = jsonObj.getJSONObject("data");
					BigDecimal che300Price = (BigDecimal)jsb.get("c2b_price");//手车价格
					BigDecimal che300ThPrice = (BigDecimal)jsb.get("b2b_price");//同行交易价（批发价）
					carPriceInfo.setChe300Price(che300Price.multiply(new BigDecimal(10000)));
					carPriceInfo.setChe300ThPrice(che300ThPrice.multiply(new BigDecimal(10000)));
				}else {
					String message = (String)jsonObj.get("message");
					res.put("status", 1);
					res.put("errMsg", "获取车300价格失败！"+message);
					return res;
				}
			}

			res.put("data",carPriceInfo);
			res.put("status", 0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}





	@ApiOperation(value = "根据applyId查询（此方法不调用车300接口，只是从库中查询）")
	@ApiImplicitParam(paramType = "body", name = "param", required = true, dataType = "Map", value = "参数")
	@RequestMapping(value = "/carPriceInfo/onlyFindByApplyId", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object onlyFindByApplyId(@RequestParam Map<String,Object> param) {
		Map<String, Object> res = new HashMap<>();
		try {
			Long applyId = Long.valueOf(param.get("applyId").toString());
			CarPriceInfo carPriceInfo = service.selByApplyId(param);
			res.put("data",carPriceInfo);
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

