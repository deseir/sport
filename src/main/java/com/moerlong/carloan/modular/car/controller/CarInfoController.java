package com.moerlong.carloan.modular.car.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.common.exception.BizExceptionEnum;
import com.moerlong.carloan.common.exception.BussinessException;
import com.moerlong.carloan.common.persistence.dao.DictMapper;
import com.moerlong.carloan.common.persistence.model.Dict;
import com.moerlong.carloan.core.support.HttpKit;
import com.moerlong.carloan.core.util.ToolUtil;
import com.moerlong.carloan.modular.car.entity.CarInfo;
import com.moerlong.carloan.modular.car.entity.CarVerifyInfo;
import com.moerlong.carloan.modular.car.entity.vo.CarInfoVo;
import com.moerlong.carloan.modular.car.entity.vo.InitCarVerifyVo;
import com.moerlong.carloan.modular.car.service.CarInfoService;
import com.moerlong.carloan.modular.car.service.CarVerifyInfoService;
import com.moerlong.carloan.modular.cust.service.GetDataService;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import com.moerlong.carloan.util.CommonUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

//@Controller
//@Api(tags = { "controller接口类" })
public class CarInfoController {
	private static String PREFIX = "/cust/";
	private final Logger log = LoggerFactory.getLogger(CarInfoController.class);

	@Autowired
	CarInfoService service;
	@Autowired
	ApplyInfoService applyInfoService;
	@Autowired
	CarVerifyInfoService carVerifyInfoService;
	@Value("${file.identity_pic_urls}")
	private String idPicUrls;
	@Autowired
	GetDataService getDataService;
	@Value("${car300.get_All_City_List}")
	private String getAllCityList;
	@Value("${car300.get_All_Brand_List}")
	private String getAllBrandList;
	@Resource
	DictMapper dictMapper;
	@Value("${car300.get_Car_Series_List}")
	private String getCarSeriesList;
	@Value("${car300.get_Car_Model_List}")
	private String getCarModelList;
	@RequestMapping(value = "/carInfo/getPage", method = RequestMethod.GET)
	public String getPage() {
		return "";
	}
	
	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "CarInfo", value = "明细")
	@RequestMapping(value = "/carInfo/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(CarInfo entity) {
		Map<String, Object> res = new HashMap<>();
		try {
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
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "CarInfo", value = "明细")
	@RequestMapping(value = "/carInfo/updateWithOutNull", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWithOutNull(@RequestBody CarInfo entity) {
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
	@RequestMapping(value = "/carInfo/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/carInfo/deleteLogicById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/carInfo/findById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/carInfo/listAll", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/carInfo/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestParam Map<String,Object> queryMap) {
		this.log.info("/carInfo/pageQuery param:{}",queryMap);
		HttpServletRequest request = HttpKit.getRequest();
		int limit = Integer.valueOf(request.getParameter("limit"));
		int offset = Integer.valueOf(request.getParameter("offset"));
		Map<String, Object> res = new HashMap<>();
		Integer pageNum = (offset / limit + 1); //页数从1开始
		Integer pageSize = limit; //页面大小

		try {
			PageInfo<CarInfo> pageInfo = this.service.selectPage(pageSize, pageNum, queryMap);
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
	 * cjh
	 * 保存内勤资料录入车辆基本信息
	 *
	 * @return
	 */
	@ApiOperation(value = "保存或更新")
	@RequestMapping(value = "/carInfo/addSaveOrUpdateCarInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object addSaveOrUpdateCarInfo(@RequestBody CarInfoVo vo) {
		return  this.service.addSaveOrUpdateCarInfo(vo);
	}
	/**
	 * cjh
	 * 获取内勤资料录入车辆基本信息根据ApplyId
	 *
	 * @return
	 */
	@ApiOperation(value = "查询根据ApplyId")
	@RequestMapping(value = "/carInfo/findCarInfoByApplyId", method = RequestMethod.POST)
	@ResponseBody
	public Object findCarInfoByApplyId(String ApplyId) {
		Map<String, Object> res = new HashMap<>();
		try {
			res.put("data", this.service.findCarInfoByApplyId(Long.valueOf(ApplyId)));
			Dict dict=dictMapper.selectById(40);
			String url = getAllCityList+"?token="+dict.getName();
			HttpMethod method = HttpMethod.GET;
			String jsonText = getDataService.client(url,method);
			JSONObject jSONObject = JSONObject.parseObject(jsonText);
			res.put("getAllCityList",jSONObject.get("data"));
			url=getAllBrandList+"?token="+dict.getName();
			jsonText = getDataService.client(url,method);
			jSONObject = JSONObject.parseObject(jsonText);
			res.put("getAllBrandList",jSONObject.get("data"));
			res.put("status", 0);
			res.put("idPicUrls",idPicUrls);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}
	/**
	 * cjh
	 * 根据选择车系查询车型
	 *
	 * @return
	 */
	@ApiOperation(value = "查询")
	@RequestMapping(value = "/carInfo/findcarModelBySeriesId", method = RequestMethod.POST)
	@ResponseBody
	public Object findcarModelBySeriesId(String seriesId) {
		Map<String, Object> res = new HashMap<>();
		try {
			Dict dict=dictMapper.selectById(40);
			String url = getCarModelList+"?token="+dict.getName()+"&seriesId="+seriesId;
			HttpMethod method = HttpMethod.GET;
			String jsonText = getDataService.client(url,method);
			JSONObject jSONObject = JSONObject.parseObject(jsonText);
			res.put("getCarModelList",jSONObject.get("data"));
			res.put("status", 0);
			res.put("idPicUrls",idPicUrls);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}
	/**
	 * cjh
	 * 根据选择车辆品牌查询车系
	 *
	 * @return
	 */
	@ApiOperation(value = "查询")
	@RequestMapping(value = "/carInfo/findcarSeriesByCarBrandId", method = RequestMethod.POST)
	@ResponseBody
	public Object findcarSeriesByCarBrandId(String brandId) {
		Map<String, Object> res = new HashMap<>();
		try {
			Dict dict=dictMapper.selectById(40);
			String url = getCarSeriesList+"?token="+dict.getName()+"&brandId="+brandId;
			HttpMethod method = HttpMethod.GET;
			String jsonText = getDataService.client(url,method);
			JSONObject jSONObject = JSONObject.parseObject(jsonText);
			res.put("getCarSeriesList",jSONObject.get("data"));
			res.put("status", 0);
			res.put("idPicUrls",idPicUrls);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}
	/**
	 * 跳转到添加newAddCarInfo页面
	 */
	@RequestMapping("/carInfo/newAddCarInfo")
	public String newAddCarInfo( @RequestParam String CarInfoId,Model model) {
		model.addAttribute("CarInfoId",CarInfoId);
		return PREFIX + "newAddCarInfo.html";
	}
	/**
	 * 跳转到添加newAddCarInfo页面
	 */
	@RequestMapping("/carInfo/newAddCarInfo2")
	public String newAddCarInfo2(@RequestParam String name, @RequestParam String certId, @RequestParam String getType,
								 @RequestParam String regDate, @RequestParam String id, Model model) {
		model.addAttribute("name",name);
		model.addAttribute("certId",certId);
		model.addAttribute("getType",getType);
		model.addAttribute("regDate",regDate);
		if("".equals(id)){
			model.addAttribute("id","null");
		}else{
			model.addAttribute("id",id);
		}
		return PREFIX + "newAddCarInfo2.html";
	}
    /**
     * 跳转到添加newAddCarMortgageInfo页面
     */
    @RequestMapping("/carInfo/newAddCarMortgageInfo")
    public String newAddCarMortgageInfo(@RequestParam String CarInfoId,Model model) {
		model.addAttribute("CarInfoId",CarInfoId);
        return PREFIX + "newAddCarMortgageInfo.html";
    }
	@RequestMapping("/carInfo/newAddCarMortgageInfo2")
	public String newAddCarMortgageInfo2(@RequestParam String name, @RequestParam String certId,
										 @RequestParam String regDate, @RequestParam String id, Model model) {
		model.addAttribute("name",name);
		model.addAttribute("certId",certId);
		model.addAttribute("regDate",regDate);
		if("".equals(id)){
			model.addAttribute("id","null");
		}else{
			model.addAttribute("id",id);
		}
		return PREFIX + "newAddCarMortgageInfo2.html";
	}
	/**
	 *
	 * @return
	 */
	@ApiOperation(value = "查询根据ApplyId查询验车师需要初始化的数据")
	@RequestMapping(value = "/carInfo/selInitCarverifyByCustId", method = RequestMethod.POST)
	@ResponseBody
	public Object selInitCarverifyByCustId(@RequestParam Map<String,Object> param) {
		Map<String, Object> res = new HashMap<>();
		String applyID = (String)param.get("applyId");
//		String carId = (String)param.get("carId");
		try {
			if (ToolUtil.isEmpty(applyID)) {
				throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
			}
			ApplyInfo applyinfo = applyInfoService.selectById(Long.parseLong(applyID));
			if (ToolUtil.isEmpty(applyinfo)) {
				throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
			}
			InitCarVerifyVo initCarVerifyVo = this.service.selInitCarverifyByCustId(applyinfo.getCustId());

			CarVerifyInfo verifyInfo = carVerifyInfoService.selectByCarId(initCarVerifyVo.getCarId());
			if(verifyInfo !=null){
				initCarVerifyVo.setId(verifyInfo.getId());
				initCarVerifyVo.setCarCond(verifyInfo.getCarCond());
				initCarVerifyVo.setSuggestion(verifyInfo.getSuggestion());
				initCarVerifyVo.setConfigTablePhoto(verifyInfo.getConfigTablePhoto());
				initCarVerifyVo.setMaintainPhoto(verifyInfo.getMaintainPhoto());
				initCarVerifyVo.setCarAssessmentPrice(verifyInfo.getCarAssessmentPrice());
				initCarVerifyVo.setQmzk(verifyInfo.getQmzk());
				initCarVerifyVo.setNszk(verifyInfo.getNszk());
				initCarVerifyVo.setGkzk(verifyInfo.getGkzk());
			}
			res.put("data", initCarVerifyVo);
			res.put("status", initCarVerifyVo!=null?0:1);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}

	/**
	 * 跳转到添加车辆信息管理页面
	 */
	@RequestMapping("/carInfo/showCarmgr")
	public String showCarmgr() {
		return PREFIX + "carMgr.html";
	}

	@RequestMapping("/carInfo/showCar")
	public String showCar(@RequestParam Long carId, Model model) {
		CarInfo carInfo = service.selectById(carId);
		if(null !=carInfo) {
			ApplyInfo applyInfo = applyInfoService.selectByCustId(carInfo.getCustId());
			model.addAttribute("applyId",applyInfo.getId());
		}

		model.addAttribute("carId",carId);
		model.addAttribute("idPicUrls",idPicUrls);
		return PREFIX + "showCar.html";
	}

	@RequestMapping(value = "/carInfo/findCarInfoById", method = RequestMethod.POST)
	@ResponseBody
	public Object findCarInfoById(Long carId) {
		CarInfo carInfo = service.selectById(carId);
		if(null !=carInfo){
			ApplyInfo applyInfo = applyInfoService.selectByCustId(carInfo.getCustId());
			Map<String, Object> res = new HashMap<>();
			try {
				res.put("data", this.service.findCarInfoByApplyId(applyInfo.getId()));
				res.put("status", 0);
				res.put("idPicUrls",idPicUrls);
				res.put("errMsg", "操作成功");
			} catch (Throwable e) {
				this.log.error(e.getMessage(), e);
				res.put("status", 1);
				res.put("errMsg", e.getMessage());
			}
			return res;
		}
		return null;

	}
}

