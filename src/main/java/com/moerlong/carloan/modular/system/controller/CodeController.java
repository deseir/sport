package com.moerlong.carloan.modular.system.controller;

import com.moerlong.carloan.common.exception.BizExceptionEnum;
import com.moerlong.carloan.core.template.engine.base.FundTemplateEngine;
import com.moerlong.carloan.common.annotion.Permission;
import com.moerlong.carloan.common.constant.Const;
import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.common.exception.BussinessException;
import com.moerlong.carloan.core.template.config.ContextConfig;
import com.moerlong.carloan.core.template.engine.SimpleTemplateEngine;
import com.moerlong.carloan.core.util.ToolUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 代码生成控制器
 *
 * @author hwl
 * @Date 2017-05-23 18:52:34
 */
@Controller
@RequestMapping("/code")
public class CodeController extends BaseController {

    private String PREFIX = "/system/code/";

    /**
     * 跳转到代码生成首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "code.html";
    }

    /**
     * 代码生成
     */
    @ApiOperation("生成代码")
    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    @ResponseBody
    @Permission(Const.ADMIN_NAME)
    public Object add(@ApiParam(value = "模块名称",required = true) @RequestParam  String moduleName,
                      @RequestParam String bizChName,
                      @RequestParam String bizEnName,
                      @RequestParam String path) {
        if (ToolUtil.isOneEmpty(bizChName, bizEnName)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        ContextConfig contextConfig = new ContextConfig();
        contextConfig.setBizChName(bizChName);
        contextConfig.setBizEnName(bizEnName);
        contextConfig.setModuleName(moduleName);
        if (ToolUtil.isNotEmpty(path)) {
            contextConfig.setProjectPath(path);
        }

        FundTemplateEngine fundTemplateEngine = new SimpleTemplateEngine();
        fundTemplateEngine.setContextConfig(contextConfig);
        fundTemplateEngine.start();

        return super.SUCCESS_TIP;
    }
}
