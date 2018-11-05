package com.moerlong.carloan.modular.sport.controller;

import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.common.constant.factory.ConstantFactory;
import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.common.persistence.dao.DeptMapper;
import com.moerlong.carloan.common.persistence.model.Dept;
import com.moerlong.carloan.common.persistence.model.DeptVo;
import com.moerlong.carloan.core.log.LogObjectHolder;
import com.moerlong.carloan.core.support.HttpKit;
import com.moerlong.carloan.modular.system.service.IDeptService;
import com.moerlong.carloan.util.CommonUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Controller
public class SDeptController extends BaseController {


    @Resource
    IDeptService deptService;//这个暂时用，后面合并到 sdeptservice

    @Value("${file.identity_pic_urls}")
    private String idPicUrls;

    @Resource
    DeptMapper deptMapper;


    @RequestMapping("/sdept")
    public String index(Model model) {
        model.addAttribute("idPicUrls",idPicUrls);
        return "/sport/sdept.html";
    }



    @ApiOperation(value = "分页查询")
    @ApiImplicitParam(paramType = "body", name = "queryMap", required = false, dataType = "Map", value = "查询条件")
    @RequestMapping(value = "/sdept/pageQuery", method = RequestMethod.POST)
    @ResponseBody
    public Object pageQuery(@RequestParam Map<String,Object> queryMap) {
        HttpServletRequest request = HttpKit.getRequest();
        int limit = Integer.valueOf(request.getParameter("limit"));
        int offset = Integer.valueOf(request.getParameter("offset"));
        Map<String, Object> res = new HashMap<>();
        Integer pageNum = (offset / limit + 1); //页数从1开始
        Integer pageSize = limit; //页面大小

        try {
            PageInfo<Dept> pageInfo = this.deptService.selectPage(pageSize, pageNum, queryMap);
            List<DeptVo> deptVos = new ArrayList<>();
            for (Dept dept:pageInfo.getList()) {
                DeptVo deptVo = new DeptVo();
                deptVo.setId(dept.getId());
                deptVo.setFullname(dept.getFullname());
                deptVo.setNum(dept.getNum());
                deptVo.setPid(dept.getPid());
                deptVo.setPids(dept.getPids());
                deptVo.setSimplename(dept.getSimplename());
                deptVo.setTips(dept.getTips());
                deptVo.setPname(ConstantFactory.me().getSingleDeptName(dept.getPid()));
                deptVo.setVersion(dept.getVersion());
                deptVos.add(deptVo);
            }
            res.put("total",pageInfo.getTotal());
            res.put("rows",deptVos);
            return CommonUtil.obj2json(res);
        } catch (Throwable e) {
            res.put("status", 1);
            res.put("errMsg", e.getMessage());
        }

        return res;
    }

    /**
     * 跳转到添加部门
     */
    @RequestMapping("/sdept/showadd")
    public String deptAdd(@RequestParam String pid,@RequestParam String pName, Model model) {
        model.addAttribute("pid", pid);
        model.addAttribute("pName", pName);
        model.addAttribute("idPicUrls",idPicUrls);
        return "/sport/sdeptadd.html";
    }

    @RequestMapping("/sdept/showedit/{deptId}")
    public String sdeptedit(@PathVariable Integer deptId, Model model) {
        Dept dept = deptMapper.selectById(deptId);
        model.addAttribute(dept);
        model.addAttribute("pName", ConstantFactory.me().getSingleDeptName(dept.getPid()));
        LogObjectHolder.me().set(dept);
        model.addAttribute("idPicUrls",idPicUrls);
        return "/sport/sdeptedit.html";
    }

    @RequestMapping("/sdept/showSubDepts")
    public String showSubDepts(@RequestParam Integer deptId,Model model) {
        model.addAttribute("deptId",deptId);
        model.addAttribute("idPicUrls",idPicUrls);
        return "/sport/subdepts.html";
    }

    @RequestMapping("/sdept/sfxj")
    @ResponseBody
    public Object upSfxj(@RequestParam Map<String,Object> params){
        Map<String, Object> res = new HashMap<>();
        int flag = deptService.upSfxj(params);
        if(flag<1){
            res.put("status",1);
            res.put("msg","数据修改失败");
        }else{
            res.put("status",0);
            res.put("msg","确认已成功");
        }
        return res;
    }

}
