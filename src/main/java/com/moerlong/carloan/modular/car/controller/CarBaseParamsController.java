package com.moerlong.carloan.modular.car.controller;

import com.moerlong.carloan.modular.car.entity.CarBaseParams;
import com.moerlong.carloan.modular.car.entity.CarBussInsureInfo;
import com.moerlong.carloan.modular.car.service.CarBaseParamsService;
import com.moerlong.carloan.modular.car.service.CarBussInsureInfoService;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@Api(tags = { "controller接口类" })
public class CarBaseParamsController {

	private final Logger log = LoggerFactory.getLogger(CarBaseParamsController.class);

	@Autowired
	CarBaseParamsService service;
	@Autowired
	ApplyInfoService applyInfoService;

	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "CarBaseParams", value = "明细")
	@RequestMapping(value = "/carBaseParams/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(CarBaseParams entity) {
		Map<String, Object> res = new HashMap<>();
		try {
			ApplyInfo applyInfo = applyInfoService.selectById(entity.getApplyId());
			if(null==entity.getId()){
				if(null != applyInfo){
					entity.setCustId(applyInfo.getCustId());
				}
				entity.setIsDeleted(0);
				entity.setCreateTime(new Date());
				service.save(entity);
			}else{
				entity.setUpdateTime(new Date());
				service.updateWithoutNull(entity);
			}
			res.put("status", 0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.toString());
		}
		return res;
	}
	

	@ApiOperation(value = "根据applyId查找")
	@ApiImplicitParam(paramType = "body", name = "param", required = false, dataType = "Map", value = "参数")
	@RequestMapping(value = "/carBaseParams/findByApplyId", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object findById(@RequestParam Map<String,Object> param) {
		Map<String, Object> res = new HashMap<>();
		try {
			Long applyId = Long.valueOf(param.get("applyId").toString());
			CarBaseParams carBaseParams = service.selectByApplyId(applyId);
			res.put("data", carBaseParams);
			res.put("status", carBaseParams!=null?0:1);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}
	

}

