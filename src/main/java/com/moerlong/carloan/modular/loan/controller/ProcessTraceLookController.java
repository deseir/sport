package com.moerlong.carloan.modular.loan.controller;

import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.modular.cust.entity.ContractInfo;
import com.moerlong.carloan.modular.cust.entity.CustomerInfo;
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
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.math.BigDecimal;

//@Controller
//@RequestMapping("/processTraceLook")
public class ProcessTraceLookController extends BaseController {

    private static String PREFIX = "/cust/";
    @Autowired
    CreditReportService creditReportService;
    @Resource
    private ApplyInfoService applyInfoService;
    @Resource
    com.moerlong.carloan.modular.cust.service.ContractInfoService ContractInfoService;
    @Resource
    private CustomerInfoService customerInfoService;
    @Autowired
    PayInfoService payInfoService;
    @Resource
    RepaymentInfoService repaymentInfoService;
    @Autowired
    private OnceEarlyRepaymentRecordService onceEarlyRepaymentRecordService;
    @Resource
    private FinalJudgementInfoService finalJudgementInfoService;
    @Value("${file.identity_pic_urls}")
    private String idPicUrls;
    /**
     * 显示内勤资料只读页面
     * @return
     */
    @RequestMapping("/showCustNq")
    public String showCustNq(@RequestParam String applyId,@RequestParam String flag, Model model){
        model.addAttribute("applyId",applyId);
        model.addAttribute("flag",flag);
        model.addAttribute("idPicUrls",idPicUrls);
        return PREFIX+"lookShowCustNq.html";
    }
    /**
     * 验车师信息查看页面
     * @return
     */
    @RequestMapping("/showYcs")
    public String showYcs(@RequestParam String applyId,@RequestParam String flag,Model model){
        model.addAttribute("applyId",applyId);
        model.addAttribute("flag",flag);
        model.addAttribute("idPicUrls",idPicUrls);
        String skipurl="";
        if("2".equals(flag)){
            skipurl=PREFIX +"lookShowAllyancheshi.html";
        }else{
            skipurl= PREFIX + "lookShowyancheshi.html";
        }
        return  skipurl;
    }
    /**
     * 查看主借人面审信息的页面
     */
    @RequestMapping("/showMsDetails")
    public String showMsDetails(@RequestParam String applyId,@RequestParam String flag, Model model) {
        model.addAttribute("applyId",applyId);
        model.addAttribute("flag",flag);
        model.addAttribute("idPicUrls",idPicUrls);
        String skipurl="";
        if("2".equals(flag)){
            skipurl=PREFIX +"loookShowAllmianshen.html";
        }else{
            skipurl= PREFIX + "loookShowmianshen.html";
        }
        return skipurl;
    }
    /**
     * 查看共借人面审信息的页面
     */
    @RequestMapping("/showGjrMsDetails")
    public String showGjrMsDetails(@RequestParam String applyId,@RequestParam String flag, Model model) {
        model.addAttribute("applyId",applyId);
        model.addAttribute("flag",flag);
        model.addAttribute("idPicUrls",idPicUrls);
        String skipurl="";
        if("2".equals(flag)){
            skipurl=PREFIX +"lookShowAllgjrmianshen.html";
        }else{
            skipurl= PREFIX + "lookShowgjrmianshen.html";
        }
        return skipurl;
    }
    /**
     * 查看签订合同的页面
     */
    @RequestMapping("/showsignContract")
    public String showsignContract(@RequestParam String applyId,@RequestParam String flag, Model model) {
        model.addAttribute("applyId",applyId);
        model.addAttribute("flag",flag);
        model.addAttribute("idPicUrls",idPicUrls);
        String skipurl="";
        if("2".equals(flag)){
            skipurl= "/cust/lookShowAllsignContract.html";
        }else{
            skipurl= "/cust/lookShowsignContract.html";
        }
        return skipurl;
    }
    /**
     * 查看抵押申请材料的页面
     */
    @RequestMapping("/mortgageCarConfirmApply")
    public String mortgageCarConfirmApply(@RequestParam String applyId,@RequestParam String flag, Model model) {
        model.addAttribute("applyId",applyId);
        model.addAttribute("flag",flag);
        model.addAttribute("idPicUrls",idPicUrls);
        String skipurl="";
        if("2".equals(flag)){
            skipurl=PREFIX +"lookShowAllMortgageCarConfirmApply.html";
        }else{
            skipurl= PREFIX + "lookMortgageCarConfirmApply.html";
        }
        return skipurl;
    }
    /**
     * 查看抵押办理成功的页面
     */
    @RequestMapping("/mortgageCarBussFinish")
    public String mortgageCarBussFinish(@RequestParam String applyId,@RequestParam String flag, Model model) {
        model.addAttribute("applyId",applyId);
        model.addAttribute("flag",flag);
        model.addAttribute("idPicUrls",idPicUrls);
        String skipurl="";
        if("2".equals(flag)){
            skipurl=PREFIX +"lookShowAllMortgageCarBussFinish.html";
        }else{
            skipurl= PREFIX + "lookMortgageCarBussFinish.html";
        }
        return skipurl;
    }
    /**
     * 查看gps安装的页面
     */
    @RequestMapping("/gpsinstallcomplete")
    public String gpsinstallcomplete(@RequestParam String applyId,@RequestParam String flag, Model model) {
        model.addAttribute("applyId",applyId);
        model.addAttribute("flag",flag);
        model.addAttribute("idPicUrls",idPicUrls);
        String skipurl="";
        if("2".equals(flag)){
            skipurl=PREFIX +"lookShowAllGpsinstallcomplete.html";
        }else{
            skipurl= PREFIX + "lookGpsinstallcomplete.html";
        }
        return skipurl;
    }
    /**
     * 查看资料留存的页面
     */
    @RequestMapping("/dataKeep")
    public String dataKeep(@RequestParam String applyId,@RequestParam String flag, Model model) {
        model.addAttribute("applyId",applyId);
        model.addAttribute("flag",flag);
        model.addAttribute("idPicUrls",idPicUrls);
        String skipurl="";
        if("2".equals(flag)){
            skipurl=PREFIX +"lookShowAllDataKeep.html";
        }else{
            skipurl= PREFIX + "lookDataKeep.html";
        }
        return skipurl;
    }
    /**
     * 查看请款申请的页面
     */
    @RequestMapping("/detentionCarApply")
    public String detentionCarApply(@RequestParam String applyId,@RequestParam String flag, Model model) {
        model.addAttribute("applyId",applyId);
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
        CustomerInfo CustomerInfo=customerInfoService.selectById(applyInfo.getCustId());
        if(CustomerInfo!=null) {
            model.addAttribute("name",CustomerInfo.getName());
            model.addAttribute("idno",CustomerInfo.getCertId());
            model.addAttribute("custMobile",CustomerInfo.getMobile());
            model.addAttribute("custSex",CustomerInfo.getSex());
            if(!StringUtils.isEmpty(CustomerInfo.getSex())) {
                model.addAttribute("sex",CustomerInfo.getSex()==1?"男":"女");
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

            }else {
                model.addAttribute("repaymentid",null);
            }

        }else {
            model.addAttribute("isReplaceCost",null);
            model.addAttribute("isPerCharge",null);
            model.addAttribute("bankCardNo",null);
            model.addAttribute("bankName",null);
            model.addAttribute("receptionAmount",null);
            model.addAttribute("receptionDepart",null);
            model.addAttribute("receptionManager",null);
            model.addAttribute("amount",null);
            model.addAttribute("notamount",null);
        }
        String HtmlUrl="";
        model.addAttribute("flag",flag);
        if(flag=="1"||"1".equals(flag)){
            HtmlUrl= PREFIX + "lookDetentionCarApply.html";
        }else if(flag=="2"||"2".equals(flag)){
            HtmlUrl= PREFIX + "lookFirstDetentionCarApply.html";
        }else if(flag=="3"||"3".equals(flag)){
            HtmlUrl= PREFIX + "lookTwoDetentionCarApply.html";
        }
        if(flag=="9"||"9".equals(flag)||flag=="10"||"10".equals(flag)||flag=="11"||"11".equals(flag)||flag=="12"||"12".equals(flag)){
            HtmlUrl= PREFIX + "lookShowAllDetentionCarApply.html";
        }else if(flag=="13"||"13".equals(flag)||flag=="14"||"14".equals(flag)){
            HtmlUrl= PREFIX + "lookShowAllFirstDetentionCarApply.html";
        }else if(flag=="33"||"33".equals(flag)){
            HtmlUrl= PREFIX + "lookShowAllTwoDetentionCarApply.html";
        }
        return HtmlUrl;
    }
    /**
     * 查看借贷基本信息的页面
     */
    @RequestMapping("/lookAll")
    public String lookAll(@RequestParam String applyId,@RequestParam String flag, Model model) {
        model.addAttribute("applyId",applyId);
        model.addAttribute("flag",flag);
        model.addAttribute("idPicUrls",idPicUrls);
        return PREFIX + "lookShowAll.html";
    }
    /**
     * 查看提前还款的页面
     */
    @RequestMapping("/prepaymentSuccessful")
    public String prepaymentSuccessful(@RequestParam String applyId,@RequestParam String flag, Model model) {
        model.addAttribute("applyId",applyId);
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
        CustomerInfo CustomerInfo=customerInfoService.selectById(applyInfo.getCustId());
        if(CustomerInfo!=null) {
            model.addAttribute("name",CustomerInfo.getName());
            model.addAttribute("idno",CustomerInfo.getCertId());
            model.addAttribute("custMobile",CustomerInfo.getMobile());
            model.addAttribute("custSex",CustomerInfo.getSex());
            if(!StringUtils.isEmpty(CustomerInfo.getSex())) {
                model.addAttribute("sex",CustomerInfo.getSex()==1?"男":"女");
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
                    OnceEarlyRepaymentRecord onceEarlyRepaymentRecord =onceEarlyRepaymentRecordService.selectByRepaymentId(repaymentInfo.getId());
                    model.addAttribute("onceRepaymentRapital",onceEarlyRepaymentRecord.getOnceRepaymentCapital());
                    model.addAttribute("onceRepaymentCharge",onceEarlyRepaymentRecord.getOnceRepaymentCharge());
                    model.addAttribute("onceRepaymentBreach",onceEarlyRepaymentRecord.getOnceRepaymentBreach());
                    model.addAttribute("curPeriodNum",onceEarlyRepaymentRecord.getCurPeriodNum());
                    model.addAttribute("curPeriodAmount",onceEarlyRepaymentRecord.getCurPeriodAmount());
                    model.addAttribute("onceRepaymentTotal",onceEarlyRepaymentRecord.getOnceRepaymentTotal());
                    model.addAttribute("appointDate",onceEarlyRepaymentRecord.getAppointDate());

            }else {
                model.addAttribute("repaymentid",null);
            }

        }else {
            model.addAttribute("isReplaceCost",null);
            model.addAttribute("isPerCharge",null);
            model.addAttribute("bankCardNo",null);
            model.addAttribute("bankName",null);
            model.addAttribute("receptionAmount",null);
            model.addAttribute("receptionDepart",null);
            model.addAttribute("receptionManager",null);
            model.addAttribute("amount",null);
            model.addAttribute("notamount",null);
        }
        model.addAttribute("flag",flag);
        return PREFIX + "lookOnceEarlyNqzgAudit.html";
    }
    /**
     * 查看解押申请相关的页面
     */
    @RequestMapping("/lookdetentionFinSettleConfirm")
    public String lookdetentionFinSettleConfirm(@RequestParam String applyId,@RequestParam String flag, Model model) {
        model.addAttribute("applyId",applyId);
        model.addAttribute("flag",flag);
        model.addAttribute("idPicUrls",idPicUrls);
        ApplyInfo applyInfo=applyInfoService.selectById(Long.valueOf(applyId));
        CustomerInfo CustomerInfo=customerInfoService.selectById(applyInfo.getCustId());
        if(CustomerInfo!=null) {
            model.addAttribute("name",CustomerInfo.getName());
            model.addAttribute("idno",CustomerInfo.getCertId());
            model.addAttribute("custMobile",CustomerInfo.getMobile());
            model.addAttribute("custSex",CustomerInfo.getSex());
            if(!StringUtils.isEmpty(CustomerInfo.getSex())) {
                model.addAttribute("sex",CustomerInfo.getSex()==1?"男":"女");
            }else {
                model.addAttribute("sex","");
            }
        }
        return PREFIX + "lookDetentionFinSettleConfirm.html";
    }
    /**
     * 查看gps拆卸的页面
     */
    @RequestMapping("/gpsUninstallConfirm")
    public String gpsUninstallConfirm(@RequestParam String applyId,@RequestParam String flag, Model model) {
        model.addAttribute("applyId",applyId);
        model.addAttribute("flag",flag);
        model.addAttribute("idPicUrls",idPicUrls);
        String skipurl="";
        if("2".equals(flag)||flag=="2"){
            skipurl=PREFIX +"lookAllgpsUninstallConfirm.html";
        }else{
            skipurl= PREFIX + "lookgpsUninstallConfirm.html";
        }
        return skipurl;
    }
}
