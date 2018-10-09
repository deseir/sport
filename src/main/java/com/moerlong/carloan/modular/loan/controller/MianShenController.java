package com.moerlong.carloan.modular.loan.controller;

import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.modular.cust.entity.CreditReport;
import com.moerlong.carloan.modular.cust.service.CreditReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/mianshen")
public class MianShenController extends BaseController {

    private static String PREFIX = "/cust/";
    @Autowired
    CreditReportService creditReportService;

    @Value("${file.identity_pic_urls}")
    private String idPicUrls;

    /**
     * 跳转到主借人面审信息录入页面
     */
    @RequestMapping("/showMs")
    public String mianshen(@RequestParam String applyId,@RequestParam String interfaceAddress, Model model) {
        model.addAttribute("applyId",applyId);
        model.addAttribute("interfaceAddress",interfaceAddress);
        model.addAttribute("idPicUrls",idPicUrls);
        Map<String,Object> param = new HashMap<>();
        param.put("applyId",applyId);
        param.put("type",0);
        CreditReport report =  creditReportService.selByApplyIdAndType(param);
        if(null != report){
            model.addAttribute("creditLoanMonthAmount",report.getCreditLoanMonthAmount());
        }else{
            model.addAttribute("creditLoanMonthAmount",0);
        }

        return PREFIX + "mianshen.html";
    }

    /**
     * 跳转到共借人面审信息录入页面
     */
    @RequestMapping("/showGjrMs")
    public String gjrmianshen(@RequestParam String applyId,@RequestParam String interfaceAddress, Model model) {
        model.addAttribute("applyId",applyId);
        model.addAttribute("interfaceAddress",interfaceAddress);
        Map<String,Object> param = new HashMap<>();
        param.put("applyId",applyId);
        param.put("type",1);
        model.addAttribute("idPicUrls",idPicUrls);
        CreditReport report =  creditReportService.selByApplyIdAndType(param);
        if(null != report){
            model.addAttribute("creditLoanMonthAmount",report.getCreditLoanMonthAmount());
        }else{
            model.addAttribute("creditLoanMonthAmount",0);
        }
        return PREFIX + "gjrmianshen.html";
    }


    /**
     * 跳转到面审主管页面
     */
    @RequestMapping("/showZg")
    public String showZg(@RequestParam String applyId,@RequestParam String interfaceAddress, Model model) {
        model.addAttribute("applyId",applyId);
        model.addAttribute("interfaceAddress",interfaceAddress);
        model.addAttribute("idPicUrls",idPicUrls);
        return PREFIX + "mianshenzhuguan.html";
    }



    /**
     * 面审主管查看主借人面审信息的页面
     */
    @RequestMapping("/showMsDetail")
    public String showMsDetail(@RequestParam String applyId,@RequestParam String interfaceAddress,@RequestParam String flag, Model model) {
        model.addAttribute("applyId",applyId);
        model.addAttribute("interfaceAddress",interfaceAddress);
        model.addAttribute("flag",flag);
        model.addAttribute("idPicUrls",idPicUrls);
        return PREFIX + "showmianshen.html";
    }

    /**
     * 面审主管查看共借人面审信息的页面
     */
    @RequestMapping("/showGjrMsDetail")
    public String showGjrMsDetail(@RequestParam String applyId,@RequestParam String interfaceAddress,@RequestParam String flag, Model model) {
        model.addAttribute("applyId",applyId);
        model.addAttribute("interfaceAddress",interfaceAddress);
        model.addAttribute("flag",flag);
        model.addAttribute("idPicUrls",idPicUrls);
        return PREFIX + "showgjrmianshen.html";
    }

    /**
     * 跳转面审页面
     * @param applyId
     * @param model
     * @return
     */
    @RequestMapping("/showMsCommon")
    public String showMsCommon(@RequestParam String applyId,@RequestParam String interfaceAddress, Model model) {
        model.addAttribute("applyId",applyId);
        model.addAttribute("interfaceAddress",interfaceAddress);
        model.addAttribute("idPicUrls",idPicUrls);
        return PREFIX + "showMsCommon.html";
    }


    /**
     * 显示内勤资料只读页面
     * @return
     */
    @RequestMapping("/showCustNq")
    public String showCustNq(@RequestParam String applyId,@RequestParam String interfaceAddress,@RequestParam String flag, Model model){
        model.addAttribute("applyId",applyId);
        model.addAttribute("interfaceAddress",interfaceAddress);
        model.addAttribute("flag",flag);
        model.addAttribute("idPicUrls",idPicUrls);
        return PREFIX+"showCustNq.html";
    }

}
