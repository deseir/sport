package com.moerlong.carloan.modular.sport.controller;

import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.modular.cust.service.CreditReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/qiantai")
public class QianTaiController extends BaseController {

    private static String PREFIX = "/sport/";
    @Autowired
    CreditReportService creditReportService;

    @Value("${file.identity_pic_urls}")
    private String idPicUrls;

    /**
     * 跳转到主借人面审信息录入页面
     */
    @RequestMapping("/showAddPrj")
    public String mianshen(Model model) {
        return PREFIX + "addprj.html";
    }

}
