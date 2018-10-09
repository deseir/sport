package com.moerlong.carloan.modular.car.controller;

import com.moerlong.carloan.modular.car.entity.CarInteriorCollocation;
import com.moerlong.carloan.modular.car.entity.CarKontiaoConfig;
import com.moerlong.carloan.modular.car.service.CarInteriorCollocationService;
import com.moerlong.carloan.modular.car.service.CarKontiaoConfigService;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(tags = { "controller接口类" })
@RestController
@RequestMapping("/carKontiaoConfig")
public class CarKontiaoConfigController {

    private final Logger LOG = LoggerFactory.getLogger(CarKontiaoConfigController.class);

    @Autowired
    private CarKontiaoConfigService service;
    @Autowired
    private ApplyInfoService applyInfoService;

    @ApiOperation(value = "保存或更新")
    @ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "CarKontiaoConfig")
    @RequestMapping(value = "/saveOrUpdate")
    public Object saveOrUpdate(@RequestBody CarKontiaoConfig entity) {
        Map<String, Object> res = new HashMap<>();
        ApplyInfo applyInfo=applyInfoService.selectById(entity.getApplyId());
        entity.setCustId(applyInfo.getCustId());
        try {
            service.saveOrUpdate(entity);
            res.put("status", 0);
            res.put("errMsg", "操作成功");
        } catch (Throwable e) {
            this.LOG.error(e.getMessage(), e);
            res.put("status", 1);
            res.put("errMsg", e.toString());
        }
        return res;
    }

    @ApiOperation(value = "根据ID查找")
    @ApiImplicitParam(paramType = "body", name = "param", required = false, dataType = "Map", value = "参数")
    @RequestMapping(value = "/selectByApplyId")
    public Object findById(@RequestBody Map<String,Object> param) {
        Map<String, Object> res = new HashMap<>();
        try {
            Long id = Long.valueOf(param.get("applyId").toString());
            res.put("data", service.selectByApplyId(id));
            res.put("status", 0);
            res.put("errMsg", "操作成功");
        } catch (Throwable e) {
            this.LOG.error(e.getMessage(), e);
            res.put("status", 1);
            res.put("errMsg", e.getMessage());
        }
        return res;
    }
}
