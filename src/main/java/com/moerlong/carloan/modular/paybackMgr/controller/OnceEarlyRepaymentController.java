package com.moerlong.carloan.modular.paybackMgr.controller;

import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.paybackMgr.warpper.CostOrderWarpper;
import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.core.shiro.ShiroKit;
import com.moerlong.carloan.core.shiro.ShiroUser;
import com.moerlong.carloan.core.support.HttpKit;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import com.moerlong.carloan.modular.paybackMgr.entity.OnceEarlyRepaymentRecord;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentInfo;
import com.moerlong.carloan.modular.paybackMgr.entity.vo.OnceEarlyRepaymentRecordVO;
import com.moerlong.carloan.modular.paybackMgr.entity.vo.RepaymentPayInfoVO;
import com.moerlong.carloan.modular.paybackMgr.service.OnceEarlyRepaymentRecordService;
import com.moerlong.carloan.modular.paybackMgr.service.RepaymentInfoService;
import com.moerlong.carloan.util.CommonUtil;
import com.moerlong.carloan.util.RepeatRefuseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class OnceEarlyRepaymentController  extends BaseController {
    private final Logger log = LoggerFactory.getLogger(OnceEarlyRepaymentController.class);


    @Autowired
    private RepaymentInfoService repaymentInfoService;

    @Autowired
    private OnceEarlyRepaymentRecordService onceEarlyRepaymentRecordService;
    
    @Autowired
	ApplyInfoService service;

    /**
     * 一次性提前还款 业务申请
     * @param repaymentId
     * @param appointDate
     * @param uuid
     * @param session
     * @return
     */
    @RequestMapping(value = "/onceEarlyRepayment/apply", method = RequestMethod.POST)
    @ResponseBody
    public Object onceEarlyApply(@RequestParam Long applyId, @RequestParam String appointDate, @RequestParam String uuid,
                                 HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());
        Date now = new Date();
        try{
            RepeatRefuseUtil.repeatRefuse(uuid, session);
            RepaymentInfo repaymentInfo =repaymentInfoService.selectByApplyId(applyId);
            ResultVO<Object> vo =  repaymentInfoService.onceEarlyRepaymentApply(repaymentInfo.getId(), appointDate, operatorId, shiroUser.getName());
            if(vo.getStatus()==0) {
            	ApplyInfo applyInfo=new ApplyInfo();
                applyInfo.setId(applyId);
                applyInfo.setStatus(9300);
                applyInfo.setStatusDesc("提前还款申请");
                service.updateWithOutNull(applyInfo);
                
            }
            res.put("status", vo.getStatus());
            res.put("msg", vo.getMsg());
        }catch(Exception e){
            this.log.error(e.getMessage(), e);
            res.put("status", 1);
            res.put("msg", e.toString());
        }

        return res;
    }

    /**
     * 跳转到还款总表记录
     */
    @RequestMapping("onceEarlyRepayment/bussApproveListPage")
    public String bussApproveListPage() {
        return "/paybackMgr/onceEarlyRepay/onceEarlyBussApprove.html";
    }


    /**
     * 审批列表
     * @param custName
     * @param custMobile
     * @param beginTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/onceEarlyRepayment/bussApproveList", method = RequestMethod.POST)
    @ResponseBody
    public Object onceEarlyBussApprove(@RequestParam(required = false) String custName, @RequestParam(required = false) String custMobile,
                                 @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime) {

        HttpServletRequest request = HttpKit.getRequest();
        int limit = Integer.valueOf(request.getParameter("limit"));
        int offset = Integer.valueOf(request.getParameter("offset"));
        Map<String, Object> res = new HashMap<>();
        Integer pageNum = (offset / limit + 1); //页数从1开始
        Integer pageSize = limit; //页面大小
        try {

            PageInfo<OnceEarlyRepaymentRecordVO> pageInfo = this.onceEarlyRepaymentRecordService.selectPage(pageSize, pageNum,custName,custMobile,beginTime,endTime);

            res.put("total",pageInfo.getTotal());
            res.put("rows",pageInfo.getList());
            return CommonUtil.obj2json(res);
        } catch (Throwable e) {
            this.log.error(e.getMessage(), e);
            res.put("total",0);
            res.put("rows",null);
        }

        return res;


    }

    /**
     * 一次性提前还款  业务经理审批
     * @param onceEarlyId
     * @param uuid
     * @param session
     * @return
     */
    @RequestMapping(value = "/onceEarlyRepayment/bussApprove", method = RequestMethod.POST)
    @ResponseBody
    public Object bussApprove(@RequestParam Long onceEarlyId, @RequestParam Integer operatorResult,
                              @RequestParam String operatorTip,@RequestParam String uuid,HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());
        Date now = new Date();
        try{
            RepeatRefuseUtil.repeatRefuse(uuid, session);
            ResultVO<Object> vo =  repaymentInfoService.onceEarlyRepaymentBussApprove(onceEarlyId,
                    operatorResult, operatorTip, operatorId, shiroUser.getName());

            res.put("status", vo.getStatus());
            res.put("msg", vo.getMsg());
        }catch(Exception e){
            this.log.error(e.getMessage(), e);
            res.put("status", 1);
            res.put("msg", e.toString());
        }

        return res;
    }


    /**
     * 一次性提前还款 财务跳转页面
     */
    @RequestMapping("onceEarlyRepayment/financeApproveListPage")
    public String financeApproveListPage() {
        return "/paybackMgr/onceEarlyRepay/onceEarlyFinanceApprove.html";
    }


    @RequestMapping(value = "/onceEarlyRepayment/financeApprove", method = RequestMethod.POST)
    @ResponseBody
    public Object financeApprove(@RequestParam Long onceEarlyId, @RequestParam Integer operatorResult,
                              @RequestParam String operatorTip,@RequestParam String uuid,HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());
        Date now = new Date();
        try{
            RepeatRefuseUtil.repeatRefuse(uuid, session);
            ResultVO<Object> vo =  repaymentInfoService.onceEarlyRepaymentFinanceApprove(onceEarlyId,
                    operatorResult, operatorTip, operatorId, shiroUser.getName());

            res.put("status", vo.getStatus());
            res.put("msg", vo.getMsg());
        }catch(Exception e){
            this.log.error(e.getMessage(), e);
            res.put("status", 1);
            res.put("msg", e.toString());
        }

        return res;
    }

    /**
     * 一次性提前还款 财务经理跳转页面
     */
    @RequestMapping("onceEarlyRepayment/financeManApproveListPage")
    public String financeApproveManListPage() {
        return "/paybackMgr/onceEarlyRepay/onceEarlyFinanceManApprove.html";
    }


    @RequestMapping(value = "/onceEarlyRepayment/financeManApprove", method = RequestMethod.POST)
    @ResponseBody
    public Object financeManApprove(@RequestParam Long onceEarlyId, @RequestParam Integer operatorResult,
                                 @RequestParam String operatorTip,@RequestParam String uuid,HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());
        Date now = new Date();
        try{
            RepeatRefuseUtil.repeatRefuse(uuid, session);
            ResultVO<Object> vo =  repaymentInfoService.onceEarlyRepaymentFinanceManApprove(onceEarlyId,
                    operatorResult, operatorTip, operatorId, shiroUser.getName());

            res.put("status", vo.getStatus());
            res.put("msg", vo.getMsg());
        }catch(Exception e){
            this.log.error(e.getMessage(), e);
            res.put("status", 1);
            res.put("msg", e.toString());
        }

        return res;
    }

}
