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
    public String showAddPrj(@RequestParam String prjType, Model model) {
        SPrjBase prjBase = new SPrjBase();
        prjBase.setPrjtype(prjType);
        model.addAttribute("prjBase",prjBase);
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
        model.addAttribute("prjId",prjId);
        SPrjBase prjBase = prjBaseService.selectById(prjId);
        model.addAttribute("prjBase",prjBase);
        return PREFIX + "addprj.html";
    }

}
