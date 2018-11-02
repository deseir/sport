package com.moerlong.carloan.modular.loan.controller;

import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.modular.cust.service.CreditReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@Controller
//@RequestMapping("/zhongshen")
public class ZhongShenController extends BaseController {

    private static String PREFIX = "/cust/";
    @Autowired
    CreditReportService creditReportService;

    @Value("${file.identity_pic_urls}")
    private String idPicUrls;

    /**
     * 跳转到面审主管信息提交页面
     */
    @RequestMapping("/showmszg")
    public String showZg(@RequestParam String applyId,@RequestParam String interfaceAddress, Model model) {
        model.addAttribute("applyId",applyId);
        model.addAttribute("interfaceAddress",interfaceAddress);
        model.addAttribute("idPicUrls",idPicUrls);
        return PREFIX + "mianshenzhuguan.html";
    }



    /**
     * 终审查看主借人面审信息的页面
     */
    @RequestMapping("/zsSeeMsZjr")
    public String showMsDetail(@RequestParam String applyId,@RequestParam String interfaceAddress,@RequestParam String flag, Model model) {
        model.addAttribute("applyId",applyId);
        model.addAttribute("interfaceAddress",interfaceAddress);
        model.addAttribute("flag",flag);
        model.addAttribute("idPicUrls",idPicUrls);
        return PREFIX + "showmianshen.html";
    }

    /**
     * 终审查看共借人面审信息的页面
     */
    @RequestMapping("/zsSeeMsGjr")
    public String showGjrMsDetail(@RequestParam String applyId,@RequestParam String interfaceAddress,@RequestParam String flag, Model model) {
        model.addAttribute("applyId",applyId);
        model.addAttribute("interfaceAddress",interfaceAddress);
        model.addAttribute("flag",flag);
        model.addAttribute("idPicUrls",idPicUrls);
        return PREFIX + "showgjrmianshen.html";
    }

    /**
     * 跳转终审页面
     * @param applyId
     * @param model
     * @return
     */
    @RequestMapping("/showZsCommon")
    public String showMsCommon(@RequestParam String applyId,@RequestParam String interfaceAddress, Model model) {
        model.addAttribute("applyId",applyId);
        model.addAttribute("interfaceAddress",interfaceAddress);
        model.addAttribute("idPicUrls",idPicUrls);
        return PREFIX + "showZsCommon.html";
    }


    /**
     * 终审查看内勤资料只读页面
     * @return
     */
    @RequestMapping("/zsSeeNq")
    public String showCustNq(@RequestParam String applyId,@RequestParam String interfaceAddress,@RequestParam String flag, Model model){
        model.addAttribute("applyId",applyId);
        model.addAttribute("interfaceAddress",interfaceAddress);
        model.addAttribute("flag",flag);
        model.addAttribute("idPicUrls",idPicUrls);
        return PREFIX+"showCustNq.html";
    }

}
