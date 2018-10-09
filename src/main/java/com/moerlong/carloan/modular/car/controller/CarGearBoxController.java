package com.moerlong.carloan.modular.car.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moerlong.carloan.modular.car.entity.CarGearBox;
import com.moerlong.carloan.modular.car.service.CarGearBoxService;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(tags="Controller接口类")
public class CarGearBoxController {
	private final Logger log=Logger.getLogger(CarGearBoxController.class);
	@Autowired
	private CarGearBoxService service;
	@Autowired
	ApplyInfoService appService;
	
	@ApiOperation(value="保存或更新")
	@ApiImplicitParam(value="车辆详细配置_变速箱配置",dataType="CarGearBox",paramType="body",name="entity",required=false)
	@RequestMapping(value="/carGearBox/saveOrUpdate",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object saveOrUpdate(CarGearBox entity) {
		Map<String, Object> res=new HashMap<String, Object>();
		try {
			ApplyInfo applyInfo=appService.selectById(entity.getApplyId());
			entity.setCustId(applyInfo.getCustId());
			service.saveOrUpdate(entity);
			res.put("status",0);
			res.put("errMsg","操作成功");
		} catch (Exception e) {
			this.log.error(e.getMessage());
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}
	
	@ApiOperation("根据订单id获取数据")
	@ApiImplicitParam(paramType="body",name="params",required=false,dataType="Map",value="参数")
	@RequestMapping(value="/carGearBox/findByApplyId",method= {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object selectByApplyId(@RequestParam Map<String, Object> params) {
		Map<String, Object> res=new HashMap<>();
		Long id=Long.valueOf(params.get("applyId").toString());
		try {
				res.put("data", service.selectByApplyId(id));
				res.put("status", 0);
				res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.toString());
		}
		return res;
	}
}
