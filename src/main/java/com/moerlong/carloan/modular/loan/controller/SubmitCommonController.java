package com.moerlong.carloan.modular.loan.controller;

import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.modular.cust.entity.ContractInfo;
import com.moerlong.carloan.modular.cust.entity.CustomerInfo;
import com.moerlong.carloan.modular.cust.service.ContractInfoService;
import com.moerlong.carloan.modular.cust.service.CreditReportService;
import com.moerlong.carloan.modular.cust.service.CustomerInfoService;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.entity.FinalJudgementInfo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import com.moerlong.carloan.modular.loan.service.FinalJudgementInfoService;
import com.moerlong.carloan.modular.payMgr.entity.PayInfo;
import com.moerlong.carloan.modular.payMgr.service.PayInfoService;
import com.moerlong.carloan.modular.paybackMgr.entity.OnceEarlyRepaymentRecord;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentInfo;
import com.moerlong.carloan.modular.paybackMgr.service.OnceEarlyRepaymentRecordService;
import com.moerlong.carloan.modular.paybackMgr.service.RepaymentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Controller
@RequestMapping("/submitCommon")
public class SubmitCommonController extends BaseController {

    @Autowired
    CreditReportService creditReportService;
    
    @Resource
    private ApplyInfoService applyInfoService;
    
    @Resource
    private CustomerInfoService customerInfoService;
    
    @Resource
    private FinalJudgementInfoService finalJudgementInfoService;
    
    @Resource
    ContractInfoService ContractInfoService;
    
    @Autowired
   	PayInfoService payInfoService;
    
    @Resource
    RepaymentInfoService repaymentInfoService;
    
    @Autowired
    private OnceEarlyRepaymentRecordService onceEarlyRepaymentRecordService;

    @Value("${file.identity_pic_urls}")
    private String idPicUrls;
    

    /**
     * 跳转到对应的提交页面
     */
    @RequestMapping("/showPage")
    public String showPage(@RequestParam String applyId,@RequestParam String interfaceAddress,@RequestParam String showAddress, Model model) {

        model.addAttribute("applyId",applyId);
        model.addAttribute("interfaceAddress",interfaceAddress);
        //查询用户的基本信息
        ApplyInfo applyInfo=applyInfoService.selectById(Long.valueOf(applyId));
        ContractInfo contractInfo=ContractInfoService.selectByApplyId(applyInfo.getId());
        if(contractInfo!=null) {
        	model.addAttribute("contractNo",contractInfo.getContractNo());
        }else {
        	model.addAttribute("contractNo",null);
        }
        model.addAttribute("transSource",applyInfo.getChannelId());
        model.addAttribute("idPicUrls",idPicUrls);
        FinalJudgementInfo finalJudgementInfo =finalJudgementInfoService.selectByApplyId(Long.valueOf(applyId));
        if(finalJudgementInfo!=null) {
        	model.addAttribute("totalAmount",finalJudgementInfo.getLoanAmount());
        	model.addAttribute("periodNum",finalJudgementInfo.getLoanPeriod());
        }else {
        	model.addAttribute("totalAmount",0);
        	model.addAttribute("periodNum",0);
        }
        CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
        if(customerInfo!=null) {
	        model.addAttribute("name",customerInfo.getName());
	        model.addAttribute("idno",customerInfo.getCertId());
	        model.addAttribute("custMobile",customerInfo.getMobile());
            model.addAttribute("custSex",customerInfo.getSex());
	        if(!StringUtils.isEmpty(customerInfo.getSex())) {
                model.addAttribute("sex",customerInfo.getSex()==1?"女":"男");
	        }else {
	        	model.addAttribute("sex","");
	        }
        }
        
        
        PayInfo payInfo = payInfoService.selectByApplyId(Long.valueOf(applyId));
        if(payInfo!=null) {
        	model.addAttribute("isReplaceCost",payInfo.getIsReplaceCost());
        	model.addAttribute("isPerCharge",payInfo.getIsPerCharge());
        	model.addAttribute("bankCardNo",payInfo.getBankCardNo());
        	model.addAttribute("bankName",payInfo.getBankName());
        	model.addAttribute("receptionAmount",payInfo.getReceptionAmount());
        	model.addAttribute("receptionDepart",payInfo.getReceptionDepart());
        	model.addAttribute("receptionManager",payInfo.getReceptionManager());
        	if(payInfo.getPayedAmount()==null||payInfo.getPayedAmount().compareTo(new BigDecimal(0))==0) {
        		model.addAttribute("amount",payInfo.getApproveAmount());
        	}else {
        		model.addAttribute("amount",payInfo.getPayedAmount());
        	}
        	model.addAttribute("notamount",payInfo.getNotPayAmount());
        	
        	RepaymentInfo repaymentInfo =repaymentInfoService.selectByPayId(payInfo.getId());
        	if(repaymentInfo!=null) {
        		model.addAttribute("repaymentid",repaymentInfo.getId());
        		 //查看提前还款信息
                if(interfaceAddress.contains("onceEarly")) {
                	OnceEarlyRepaymentRecord onceEarlyRepaymentRecord =onceEarlyRepaymentRecordService.selectByRepaymentId(repaymentInfo.getId());
                	model.addAttribute("onceRepaymentRapital",onceEarlyRepaymentRecord.getOnceRepaymentCapital());
                	model.addAttribute("onceRepaymentCharge",onceEarlyRepaymentRecord.getOnceRepaymentCharge());
                	model.addAttribute("onceRepaymentBreach",onceEarlyRepaymentRecord.getOnceRepaymentBreach());
                	model.addAttribute("curPeriodNum",onceEarlyRepaymentRecord.getCurPeriodNum());
                	model.addAttribute("curPeriodAmount",onceEarlyRepaymentRecord.getCurPeriodAmount());
                	model.addAttribute("onceRepaymentTotal",onceEarlyRepaymentRecord.getOnceRepaymentTotal());
                	model.addAttribute("appointDate",onceEarlyRepaymentRecord.getAppointDate());
                }
        	}else {
        		model.addAttribute("repaymentid",null);
        	}
        	
        }else {
        	model.addAttribute("isReplaceCost",null);
        	model.addAttribute("isPerCharge",null);
        	model.addAttribute("bankCardNo",null);
        	model.addAttribute("bankName",null);
        	model.addAttribute("receptionAmount",0.00);
        	model.addAttribute("receptionDepart",null);
        	model.addAttribute("receptionManager",null);
        	model.addAttribute("amount",null);
        	model.addAttribute("notamount",null);
        }
        
        return showAddress;
    }

}
