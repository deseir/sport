package com.moerlong.carloan.modular.sport.controller;

import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.modular.sport.entity.SPrjBase;
import com.moerlong.carloan.modular.sport.service.SPrjBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/qiantai")
public class QianTaiController extends BaseController {

    private static String PREFIX = "/sport/";
    @Autowired
    SPrjBaseService prjBaseService;

    @Value("${file.identity_pic_urls}")
    private String idPicUrls;

    /**
     * 添加工程
     */
    @RequestMapping("/showAddPrj")
    public String showAddPrj(@RequestParam Map<String,Object> params, Model model) {
        SPrjBase prjBase = new SPrjBase();
        prjBase.setLocal(params.get("deptSubName").toString());
        prjBase.setPrjtype(params.get("prjType").toString());
        prjBase.setDeptid(params.get("deptId").toString());
        model.addAttribute("prjBase",prjBase);
        model.addAttribute("idPicUrls",idPicUrls);
        return PREFIX + "addprj.html";
    }

    /**
     * 修改工程
     * @param prjId
     * @param model
     * @return
     */
    @RequestMapping("/showPrjDetail")
    public String showPrjDetail(@RequestParam Integer prjId, Model model) {
//        model.addAttribute("prjId",prjId);
        SPrjBase prjBase = prjBaseService.selectById(prjId);
        model.addAttribute("prjBase",prjBase);
        model.addAttribute("idPicUrls",idPicUrls);
        return PREFIX + "addprj.html";
    }

    /**
     * 跳转前台(非首页)
     * @param
     * @param model
     * @return
     */
    @RequestMapping("/showQt")
    public String showQt(@RequestParam Map<String,Object> params, Model model) {
        model.addAttribute("deptId",params.get("deptId").toString());
//        model.addAttribute("deptSubId",params.get("deptSubId").toString());
        model.addAttribute("deptSubName",params.get("deptSubName"));
        model.addAttribute("prjType",params.get("prjType").toString());
        model.addAttribute("idPicUrls",idPicUrls);
        return "/qiantai.html";
    }

    /**
     * 返回首页（index页面）
     */
    @RequestMapping("/backIndex")
    public String backIndex(Model model) {
        model.addAttribute("idPicUrls",idPicUrls);
        return "/index.html";
    }

}
