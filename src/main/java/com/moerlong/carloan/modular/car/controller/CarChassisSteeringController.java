package com.moerlong.carloan.modular.car.controller;

import com.moerlong.carloan.modular.car.entity.CarChassisSteering;
import com.moerlong.carloan.modular.car.service.CarChassisSteeringService;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

//@Controller
//@Api(tags = { "controller接口类" })
public class CarChassisSteeringController {
	private final Logger log = LoggerFactory.getLogger(CarChassisSteeringController.class);
	@Autowired
	private CarChassisSteeringService service;
	@Autowired
	ApplyInfoService appService;
	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "CarChassisSteering", value = "车辆详细配置_底盘转向")
	@RequestMapping(value = "/carChassisSteering/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(CarChassisSteering entity) {
		Map<String, Object> res = new HashMap<>();
		try {
			ApplyInfo applyInfo=appService.selectById(entity.getApplyId());
			entity.setCustId(applyInfo.getCustId());
			System.out.println(entity.getApplyId());
			service.saveOrupdate(entity);
			res.put("status", 0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.toString());
		}
		return res;
	}
	
	@ApiOperation("根据订单id获取数据")
	@ApiImplicitParam(paramType="body",name="params",required=false,dataType="Map",value="参数")
	@RequestMapping(value="/carChassisSteering/findByApplyId",method= {RequestMethod.POST,RequestMethod.GET})
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
