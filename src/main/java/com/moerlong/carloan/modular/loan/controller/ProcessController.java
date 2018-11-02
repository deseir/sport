package com.moerlong.carloan.modular.loan.controller;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.moerlong.carloan.common.exception.BizExceptionEnum;
import com.moerlong.carloan.common.exception.BussinessException;
import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.core.shiro.ShiroKit;
import com.moerlong.carloan.core.shiro.ShiroUser;
import com.moerlong.carloan.core.util.NumberToCN;
import com.moerlong.carloan.core.util.ToolUtil;
import com.moerlong.carloan.modular.car.entity.CarBussMortgageInfo;
import com.moerlong.carloan.modular.car.entity.CarDetentionInfo;
import com.moerlong.carloan.modular.car.entity.CarMortgageInfo;
import com.moerlong.carloan.modular.car.entity.vo.CarInfoVo;
import com.moerlong.carloan.modular.car.service.CarBussMortgageInfoService;
import com.moerlong.carloan.modular.car.service.CarDetentionInfoService;
import com.moerlong.carloan.modular.car.service.CarInfoService;
import com.moerlong.carloan.modular.car.service.CarMortgageInfoService;
import com.moerlong.carloan.modular.cust.entity.ContractInfo;
import com.moerlong.carloan.modular.cust.entity.CustInterviewInfo;
import com.moerlong.carloan.modular.cust.entity.CustomerInfo;
import com.moerlong.carloan.modular.cust.entity.GongjieInfo;
import com.moerlong.carloan.modular.cust.service.*;
import com.moerlong.carloan.modular.loan.bussiness.ProcessBussiness;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.entity.FinalJudgementInfo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import com.moerlong.carloan.modular.loan.service.FinalJudgementInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

//@Controller
//@Api(tags = { "controller接口类" })
public class ProcessController {
    private final Logger log = LoggerFactory.getLogger(ProcessController.class);

    @Resource
    private ProcessBussiness processBussiness;
    
    @Resource
    private CustomerInfoService customerInfoService;
    
    @Resource
    private ApplyInfoService applyInfoService;
    
    @Resource
    private ContractInfoService contractInfoService;
    
    @Resource
    private CarInfoService carInfoService;
    
    @Value("${file.contact_pdf_path}")
    private String contactPdfPath;

	@Resource
    private CustInterviewInfoService interviewInfoService;

	@Resource
	private FinalJudgementInfoService finalJudgementInfoService;

	@Resource
	private GongjieInfoService gongjieInfoService;

	@Resource
	private CarBussMortgageInfoService carBussMortgageInfoService;

	@Resource
	private CarMortgageInfoService carMortgageInfoService;

	@Resource
	private CarDetentionInfoService detentionInfoService;
	@Autowired
	CustWorkInfoService custWorkInfoService;


    //提前还款申请
    @RequestMapping("/OnceEarly/OnceEarlyListPage")
    public String getLoanApplyPage(){
        return "/loan/mywork/todoWork.html";
    }


    /**
     * 内勤资料录入提交（内勤）
     * @return
     */
    @ApiOperation(value = "内勤资料录入提交")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/nqDataSaveSubmit", method = RequestMethod.POST)
    @ResponseBody
    public Object nqDataSaveSubmit(Long applyId, Integer result, String resultTip){

    	ApplyInfo applyInfo = applyInfoService.selectById(applyId);
//    	if(null != applyInfo){
//    		CarInfo carInfo = carInfoService.selectByCusId(applyInfo.getCustId());
//    		if(null==carInfo){
//				return ResultVO.build(ErrorCode.NO_SAVE_CAR);
//			}
//		}
//		if(applyInfo.getApplyAmount().toString().equals("0")||applyInfo.getLoanUsage().toString().equals("0")){
//			return ResultVO.build(ErrorCode.APPLYINFO_LOST);
//		}
		/*CustomerInfo cust= customerInfoService.custInfoGetByApplyId( Long.valueOf(applyId));

    	if(cust==null||cust.getNation()==null||cust.getLiveAddress()==null||cust.getEducation()==null||cust.getMobile()==null||cust.getSignOrg()==null||cust.getValidateBegin()==null||cust.getValidateEnd()==null||
				cust.getContractName1()==null||cust.getContractPhone1()==null||cust.getContractRelation1()==null||cust.getContractName2()==null||cust.getContractPhone2()==null||cust.getContractRelation2()==null
				||cust.getContractName3()==null||cust.getContractPhone3()==null||cust.getContractRelation3()==null||cust.getIdFrontPhotoUrl()==null||cust.getIdBackPhotoUrl()==null
				||"".equals(cust.getNation())|| "".equals(cust.getLiveAddress())||"".equals(cust.getEducation())||"".equals(cust.getMobile())||"".equals(cust.getSignOrg())||"".equals(cust.getValidateBegin())
				||"".equals(cust.getValidateEnd())||"".equals(cust.getContractName1())|| "".equals(cust.getContractPhone1())||"".equals(cust.getContractRelation1())||"".equals(cust.getContractName2())
				||"".equals(cust.getContractPhone2())||"".equals(cust.getContractRelation2())||"".equals(cust.getContractName3())||"".equals(cust.getContractPhone3())||"".equals(cust.getContractRelation3())||
				"".equals(cust.getIdFrontPhotoUrl())||"".equals(cust.getIdBackPhotoUrl())){
			return ResultVO.build(ErrorCode.CUSTOMERINFO_LOST);
		}
		CustWorkInfo custWorkInfo=custWorkInfoService.findCustWorkInfoByApplyId(Long.valueOf(applyId));
    	if(custWorkInfo!=null&&(custWorkInfo.getIncomeType()&2)!=2&&(custWorkInfo.getIncomeType()&8)!=8&&(custWorkInfo.getIncomeType()&16)!=16){
            if(custWorkInfo==null||custWorkInfo.getCompanyName()==null||custWorkInfo.getCompanyType()==null||custWorkInfo.getCompanyAddress()==null
                    ||custWorkInfo.getCompanyTel()==null||custWorkInfo.getDepartment()==null||custWorkInfo.getJob()==null||
                    "".equals(custWorkInfo.getCompanyName())||"".equals(custWorkInfo.getCompanyType())||"".equals(custWorkInfo.getCompanyAddress())||
                    "".equals(custWorkInfo.getCompanyTel())||"".equals(custWorkInfo.getDepartment())||"".equals(custWorkInfo.getJob())) {
                return ResultVO.build(ErrorCode.CUSTWORKINFO_LOST);
            }
        }
		CarInfoVo carInfoVo=carInfoService.findCarInfoByApplyId(Long.valueOf(applyId));
		if(carInfoVo==null||carInfoVo.getCarConfigName()==null||"".equals(carInfoVo.getCarConfigName())||carInfoVo.getCarNum()==null||"".equals(carInfoVo.getCarNum())||carInfoVo.getCurrentLicDate()==null||
				"".equals(carInfoVo.getCurrentLicDate())||carInfoVo.getProductDate()==null||"".equals(carInfoVo.getProductDate())|| carInfoVo.getCarType()==null||"".equals(carInfoVo.getCarType())
				||carInfoVo.getCarSeriesId()==null||"".equals(carInfoVo.getCarSeriesId())||carInfoVo.getEngineNo()==null||"".equals(carInfoVo.getEngineNo())||carInfoVo.getCarColor()==null||
				"".equals(carInfoVo.getCarColor())|| carInfoVo.getCarImportType()==null||"".equals(carInfoVo.getCarImportType())||carInfoVo.getDisplacement()==null||"".equals(carInfoVo.getDisplacement())
				||carInfoVo.getMileage()==null||"".equals(carInfoVo.getMileage())||carInfoVo.getVin()==null||"".equals(carInfoVo.getVin())|| carInfoVo.getFuelType()==null||"".equals(carInfoVo.getFuelType())||
				carInfoVo.getManufacturer()==null||"".equals(carInfoVo.getManufacturer())||carInfoVo.getGetType()==null||"".equals(carInfoVo.getGetType())||carInfoVo.getFirstLicDate()==null||
				"".equals(carInfoVo.getFirstLicDate())|| carInfoVo.getRegisterPhotoUrl1()==null||"".equals(carInfoVo.getRegisterPhotoUrl1())||
				carInfoVo.getCarDriverInfo()==null||carInfoVo.getCarDriverInfo().getIsDriverLic()==null||"".equals(carInfoVo.getCarDriverInfo().getIsDriverLic())||
				carInfoVo.getCarPeccancyInfo()==null||carInfoVo.getCarPeccancyInfo().getTotalNum()==null||"".equals(carInfoVo.getCarPeccancyInfo().getTotalNum())||carInfoVo.getCarPeccancyInfo().getTotalMoney()==null||
				"".equals(carInfoVo.getCarPeccancyInfo().getTotalMoney())|| carInfoVo.getCarPeccancyInfo().getTotalValue()==null||"".equals(carInfoVo.getCarPeccancyInfo().getTotalValue())||
				carInfoVo.getCarPeccancyInfo().getTotalFullNum()==null||"".equals(carInfoVo.getCarPeccancyInfo().getTotalFullNum())){
			return ResultVO.build(ErrorCode.CARINFOVO_LOST);
		}*/
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.nqDataSaveSubmit(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }


    /**
     * 验车师资料录入提交（验车师）
     * @return
     */
    @ApiOperation(value = "验车师资料提交")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/ycSubmit", method = RequestMethod.POST)
    @ResponseBody
    public Object ycSubmit(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.ycSubmit(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 面审资料提交（面审）
     * @return
     */
    @ApiOperation(value = "面审提交")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/msSubmit", method = RequestMethod.POST)
    @ResponseBody
    public Object msSubmit(@RequestParam Map<String,Object> params){
    	Object applyIdObj = params.get("applyId");
		Object resultObj = params.get("result");
		if (ToolUtil.isEmpty(applyIdObj)) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		if (ToolUtil.isEmpty(resultObj)) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		Long applyId = Long.parseLong(applyIdObj.toString());
		Integer result = Integer.parseInt(resultObj.toString());
		String resultTip = params.get("overview").toString();
		ApplyInfo applyInfo = applyInfoService.selectById(applyId);
		if (ToolUtil.isEmpty(applyInfo)) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		Map<String,Object> param = new HashMap<>();
		param.put("applyId",applyId);
		CustInterviewInfo interviewInfo = interviewInfoService.selByApplyId(param);
		if(null ==interviewInfo ){
			interviewInfo = new CustInterviewInfo();
		}
		interviewInfo.setApplyId(applyId);
		interviewInfo.setCustId(applyInfo.getCustId());
		interviewInfo.setOverview(resultTip);
		interviewInfo.setInterviewResult(result);
//		interviewInfo.setRejectionReason(params.get("rejectionReason").toString());
		interviewInfo.setLoanAmount(BigDecimal.valueOf(Long.parseLong(params.get("loanAmount").toString())));
		interviewInfo.setLoanPeriod(Integer.parseInt(params.get("loanPeriod").toString()));
		interviewInfo.setSceneEvidenceUrl1(params.get("sceneEvidenceUrl1").toString());
		interviewInfo.setSceneEvidenceUrl2(params.get("sceneEvidenceUrl2").toString());
//		interviewInfo.setRemark(params.get("remark").toString());
		interviewInfoService.saveOrUpdate(interviewInfo);


        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.msSubmit(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 面审主管审核（面审主管）
     * @return
     */
    @ApiOperation(value = "面审主管审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/msZgSubmit", method = RequestMethod.POST)
    @ResponseBody
    public Object mszgSubmit(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.mszgSubmit(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 终审审核（终审）
     * @return
     */
    @ApiOperation(value = "终审审核（终审）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/zsSubmit", method = RequestMethod.POST)
    @ResponseBody
    public Object zsSubmit(@RequestParam Map<String ,Object> params){
        try{
            return processBussiness.zsSubmit(params);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 签署合同（内勤）
     * @return
     */
    @ApiOperation(value = "签署合同（内勤）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    
    @RequestMapping(value = "/process/signContract", method = RequestMethod.POST)
    @ResponseBody
    public Object signContract(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

		ContractInfo contractInfo = contractInfoService.selectByApplyId(applyId);
		if(null == contractInfo){
			return ResultVO.build(ErrorCode.NO_CONTRACT_SAVE);
		}
		if(null == contractInfo.getContractSignDate()){
			return ResultVO.build(ErrorCode.NO_CONTRACT_SIGNDATE);
		}

        try{
            return processBussiness.signContract(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

	/**
	 * 生成合同编号(contractinfocontroller里面也有生成合同编号的方法，如果修改一块修改)
	 * @return
	 */
	public String returnContractNo(){
		StringBuilder sb = new StringBuilder("CD");
		ShiroUser shiroUser = ShiroKit.getUser();
		List<String> deptList = shiroUser.getDeptNames();
		String deptName = deptList.get(0);
		if(deptName.contains("北京")){
			sb.append("bj");
		}else if(deptName.contains("成都")){
			sb.append("cd");
		}else if(deptName.contains("重庆")){
			sb.append("cq");
		}
		Calendar calendar = Calendar.getInstance();
		String yearStr = String.valueOf(calendar.get(Calendar.YEAR));
		String curYear = yearStr.substring(2);
		sb.append(curYear);
		int iMonth = calendar.get(Calendar.MONTH)+1;
		DecimalFormat dcf  = new DecimalFormat("00");
		String monthStr = dcf.format(iMonth);
		sb.append(monthStr);
		ContractInfo lastContract = contractInfoService.selectLastContract();
		if(lastContract != null ){
			//取出最近的一条合同号，然后截取后四位，
			String lastContractNo = lastContract.getContractNo();
			//合同号后四位
			String last4 = lastContractNo.substring(lastContractNo.length()-4);
			//判断，如果是当前月份则后四位+1，如果不是当前月份则从0001 开始
			String lastYear = lastContractNo.substring(4,6);
			String lastMonth = lastContractNo.substring(6,8);
			if(curYear.equals(lastYear)&&monthStr.equals(lastMonth)){//如果是当前年月，则取后四位+1
				Integer iFinalNum = Integer.parseInt(last4)+1;
				String finaNumStr = iFinalNum.toString();
				DecimalFormat df  = new DecimalFormat("0000");
				String finalStr = df.format(Integer.parseInt(finaNumStr));
				sb.append(finalStr);
			}else{
				sb.append("0001");
			}
		}else{
			sb.append("0001");
		}
		String contractNo = sb.toString();
		return contractNo;
	}

    /**
     * 下载融资管理服务协议模板
     */
    @RequestMapping(value = "/process/downLoadrzglfwxy", method = RequestMethod.GET)
    public void downLoadrzglfwxy(HttpServletResponse response,@RequestParam Map<String,Object> params) throws Exception{
    	PdfReader reader=new PdfReader(new ClassPathResource("contactTemplate/tjqr-kj-rzglfwxy.pdf").getInputStream());
		String contractNo = "";
		Long applyid=Long.parseLong(params.get("applyid").toString());
    	ContractInfo contractInfo =contractInfoService.selectByApplyId(applyid);
    	ApplyInfo applyInfo =applyInfoService.selectById(applyid);
		CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
		FinalJudgementInfo judgementInfo = finalJudgementInfoService.selectByApplyId(applyid);
    	if(contractInfo==null) {
			//生成合同编号
			contractNo = this.returnContractNo();
    		ContractInfo contract=new ContractInfo();
    		contract.setApplyId(applyid);
    		contract.setContractNo(contractNo);
    		contract.setCustId(customerInfo.getId());
    		contract.setContractTitle1("融资管理服务协议");
    		contract.setContractUrl1(contractNo+"_融资管理服务协议.pdf");

			contract.setCreateTime(new Date());
    		contractInfoService.save(contract);
    	}else {
			contractNo=contractInfo.getContractNo();
    		contractInfo.setContractTitle1("融资管理服务协议");
    		contractInfo.setContractUrl1(contractNo+"_融资管理服务协议.pdf");
    		contractInfoService.updateWithOutNull(contractInfo);
    	}
    	FileOutputStream out=new FileOutputStream(contactPdfPath+File.separator+contractNo+"_融资管理服务协议.pdf");
    	PdfStamper ps=new PdfStamper(reader,out);
		AcroFields s=ps.getAcroFields();
		
		Map fieldMap=s.getFields();
		
		Iterator entrys=fieldMap.entrySet().iterator();
		while(entrys.hasNext()) {
			Map.Entry entry=(Map.Entry)entrys.next();
			System.out.println(entry.getKey()+"------"+entry.getValue());
		}
		
		BaseFont bf=BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
		s.setFieldProperty("customerName", "textfont", bf, null);
		s.setFieldProperty("address", "textfont", bf, null);
		s.setField("customerName", customerInfo.getName());//甲方
		s.setField("certNo", customerInfo.getCertId());//身份证号码
		s.setField("telphone",customerInfo.getMobile());//联系电话
		s.setField("address",customerInfo.getLiveAddress());//联系地址
		s.setField("contractNo0",contractNo);//协议编号
		s.setField("contractNo1",contractNo);
		s.setField("contractNo2",contractNo);
		s.setField("contractNo3",contractNo);
		//服务费=借款金额(终审意见表中通过的金额)*3%
		BigDecimal fwf = judgementInfo.getLoanAmount().multiply(new BigDecimal(0.03)).setScale(2,BigDecimal.ROUND_HALF_UP);
		//每期服务费=服务费/借款期数
		BigDecimal fwf_xx=fwf.divide(new BigDecimal(judgementInfo.getLoanPeriod()),2,BigDecimal.ROUND_HALF_UP);
		s.setField("rzglfwf-xx",fwf_xx.toString());
		s.setField("fqfwf-xx",params.get("eachPeriod").toString());//每月支付为实际借款金额的多少，不明确
		//s.setField("lybzj-xx","321");//履约保证金，不明确
		ps.setFormFlattening(true);
		ps.close();
		FileInputStream fileInputStream=new FileInputStream(contactPdfPath+File.separator+contractNo+"_融资管理服务协议.pdf");
		response.setHeader("content-disposition", "attachment;filename="+contractNo+new String("_融资管理服务协议.pdf".getBytes("UTF-8"),"ISO-8859-1"));
		response.setHeader("content-type", "application/pdf");
		ServletOutputStream outputStream=response.getOutputStream();
		int len=1;
		byte[] bs=new byte[1024];
		while((len=fileInputStream.read(bs))!=-1) {
			outputStream.write(bs,0,len);
		}
		fileInputStream.close();
		reader.close();
    }

    /**
     * 下载委托扣款授权书和授权人资料表
     */
    @RequestMapping(value = "/process/downLoadwtkksqs", method = RequestMethod.GET)
    public void downLoadwtkksqs(HttpServletResponse response,@RequestParam Map<String,Object> params) throws Exception{
    	PdfReader reader=new PdfReader(new ClassPathResource("contactTemplate/cdydw-wtkksqs.pdf").getInputStream());
		Long applyid = Long.parseLong(params.get("applyId").toString());
    	String contractNo="";
    	ContractInfo contractInfo =contractInfoService.selectByApplyId(applyid);
    	ApplyInfo applyInfo =applyInfoService.selectById(applyid);
		CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
		FinalJudgementInfo judgementInfo = finalJudgementInfoService.selectByApplyId(applyid);
    	if(contractInfo==null) {
			contractNo=this.returnContractNo();
    		ContractInfo contract=new ContractInfo();
    		contract.setApplyId(applyid);
    		contract.setContractNo(contractNo);
    		contract.setCreateTime(new Date());
    		contract.setUpdateTime(new Date());
    		contract.setCustId(customerInfo.getId());
    		contract.setContractTitle2("委托扣款授权书和授权人资料表");
    		contract.setContractUrl2(contractNo+"_委托扣款授权书和授权人资料表.pdf");
    		contractInfoService.save(contract);
    	}else {
    		contractNo=contractInfo.getContractNo();
    		contractInfo.setContractTitle2("委托扣款授权书和授权人资料表");
    		contractInfo.setContractUrl2(contractNo+"_委托扣款授权书和授权人资料表.pdf");
    		contractInfoService.updateWithOutNull(contractInfo);
    	}
    	FileOutputStream out=new FileOutputStream(contactPdfPath+File.separator+contractNo+"_委托扣款授权书和授权人资料表.pdf");
    	PdfStamper ps=new PdfStamper(reader,out);
		AcroFields s=ps.getAcroFields();
		
		Map fieldMap=s.getFields();
		
		Iterator entrys=fieldMap.entrySet().iterator();
		while(entrys.hasNext()) {
			Map.Entry entry=(Map.Entry)entrys.next();
			System.out.println(entry.getKey()+"------"+entry.getValue());
		}
		
		BaseFont bf=BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);

		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		s.setField("htqdrq-year", String.valueOf(calendar.get(Calendar.YEAR)));
		s.setField("htqdrq-month", String.valueOf(calendar.get(Calendar.MONTH)+1));
		s.setField("htqdrq-day", String.valueOf(calendar.get(Calendar.DATE)));
//		s.setField("customerName", customerInfo.getName());
//		s.setField("certNo", customerInfo.getCertId());
//		s.setField("telphone",customerInfo.getMobile());

		Date auditTime = judgementInfo.getAuditTime();//开始日期，就是终审的审核日期
		String ksDateStr = sdf.format(auditTime);
		String [] ksDateStrArr = ksDateStr.split("-");
		s.setField("dkrq-ks-year",ksDateStrArr[0]);
		s.setField("dkrq-ks-month",ksDateStrArr[1]);
		s.setField("dkrq-ks-day",ksDateStrArr[2]);

		calendar.setTime(auditTime);
		calendar.add(Calendar.MONTH,judgementInfo.getLoanPeriod());
		String jsDateStr = sdf.format(calendar.getTime());
		String [] jsDateStrArr = jsDateStr.split("-");
		s.setField("dkrq-js-year",jsDateStrArr[0]);
		s.setField("dkrq-js-month",jsDateStrArr[1]);
		s.setField("dkrq-js-day",jsDateStrArr[2]);

		//设置授权人信息

		s.setFieldProperty("customerName", "textfont", bf, null);
		s.setFieldProperty("yhzh-zh", "textfont", bf, null);
		s.setFieldProperty("yhzh-khyh", "textfont", bf, null);
		s.setFieldProperty("certNo", "textfont", bf, null);
		s.setFieldProperty("sqje", "textfont", bf, null);
		s.setFieldProperty("telphone", "textfont", bf, null);
		s.setField("customerName",params.get("accountName").toString());//银行卡户名
		s.setField("yhzh-zh",params.get("bankNum").toString()); //银行卡账号
		s.setField("yhzh-khyh",params.get("bankName").toString());//银行卡开户行
		s.setField("certNo",customerInfo.getCertId());//身份证号码
		s.setField("sqje",params.get("sqAmount").toString());//授权金额
		s.setField("telphone",customerInfo.getMobile());



		ps.setFormFlattening(true);
		ps.close();
		FileInputStream fileInputStream=new FileInputStream(contactPdfPath+File.separator+contractNo+"_委托扣款授权书和授权人资料表.pdf");
		response.setHeader("content-disposition", "attachment;filename="+contractNo+new String("_委托扣款授权书和授权人资料表.pdf".getBytes("UTF-8"),"ISO-8859-1"));
		response.setHeader("content-type", "application/pdf");
		ServletOutputStream outputStream=response.getOutputStream();
		int len=1;
		byte[] bs=new byte[1024];
		while((len=fileInputStream.read(bs))!=-1) {
			outputStream.write(bs,0,len);
		}
		fileInputStream.close();
		reader.close();
    }
    
    /**
     * 下载汽车抵押合同
     */
    @RequestMapping(value = "/process/downLoaddrhtcgs", method = RequestMethod.GET)
    public void downLoaddrhtcgs(HttpServletResponse response,Long applyid,String signAddress) throws Exception{
    	PdfReader reader=new PdfReader(new ClassPathResource("contactTemplate/szqr-rzzl-drht-cgs.pdf").getInputStream());
    	//生成合同编号
    	String contractNo="";
    	
    	ContractInfo contractInfo =contractInfoService.selectByApplyId(applyid);
    	ApplyInfo applyInfo =applyInfoService.selectById(applyid);
		CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
		//查询汽车信息
		CarInfoVo carInfoVo=carInfoService.findCarInfoByApplyId(applyid);
		FinalJudgementInfo judgementInfo = finalJudgementInfoService.selectByApplyId(applyid);
    	if(contractInfo==null) {
			contractNo=this.returnContractNo();
    		ContractInfo contract=new ContractInfo();
    		contract.setApplyId(applyid);
    		contract.setContractNo(contractNo);
    		contract.setCreateTime(new Date());
    		contract.setUpdateTime(new Date());
    		contract.setCustId(customerInfo.getId());
    		contract.setContractTitle3("汽车抵押合同");
    		contract.setContractUrl3(contractNo+"_汽车抵押合同.pdf");
    		contractInfoService.save(contract);
    	}else {
    		contractNo=contractInfo.getContractNo();
    		contractInfo.setContractTitle3("汽车抵押合同");
    		contractInfo.setContractUrl3(contractNo+"_汽车抵押合同.pdf");
    		contractInfoService.updateWithOutNull(contractInfo);
    	}
    	FileOutputStream out=new FileOutputStream(contactPdfPath+File.separator+contractNo+"_汽车抵押合同.pdf");
    	PdfStamper ps=new PdfStamper(reader,out);
		AcroFields s=ps.getAcroFields();
		
		Map fieldMap=s.getFields();
		
		Iterator entrys=fieldMap.entrySet().iterator();
		while(entrys.hasNext()) {
			Map.Entry entry=(Map.Entry)entrys.next();
			System.out.println(entry.getKey()+"------"+entry.getValue());
		}
		
		BaseFont bf=BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
		Calendar calendar=Calendar.getInstance();
		s.setFieldProperty("customerName", "textfont", bf, null);
		s.setFieldProperty("certType", "textfont", bf, null);
		s.setFieldProperty("address", "textfont", bf, null);
		s.setFieldProperty("carBand", "textfont", bf, null);
		s.setFieldProperty("carColor", "textfont", bf, null);
		s.setFieldProperty("contractAddress", "textfont", bf, null);
		s.setFieldProperty("carEngineNo", "textfont", bf, null);
		s.setFieldProperty("carVin", "textfont", bf, null);
		s.setFieldProperty("carModel", "textfont", bf, null);
		s.setFieldProperty("carNo", "textfont", bf, null);
		
		
		s.setField("contractNo0", contractNo);
		s.setField("contractNo1", contractNo);
		s.setField("customerName", customerInfo.getName());
		s.setField("certNo", customerInfo.getCertId());
		s.setField("certType", "身份证号码");
		s.setField("address",customerInfo.getLiveAddress());
		s.setField("telphone",customerInfo.getMobile());
		s.setField("contractAddress",signAddress);
		if(carInfoVo!=null) {
			s.setField("carBand",carInfoVo.getCarBrand());
			s.setField("carEngineNo",carInfoVo.getEngineNo());
			s.setField("carVin",carInfoVo.getVin());
			s.setField("carModel",carInfoVo.getRegistrationCarType());
			s.setField("carColor",carInfoVo.getCarColor());
			s.setField("carNo",carInfoVo.getCarNum());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dyKsDate = sdf.format(judgementInfo.getAuditTime());//抵押起始日期
			s.setField("mortgageDateStart",dyKsDate);

			calendar.setTime(judgementInfo.getAuditTime());
			calendar.add(Calendar.MONTH,judgementInfo.getLoanPeriod());
			String dyJsDate = sdf.format(calendar.getTime());
			s.setField("mortgageDateEnd",dyJsDate);
		}
		ps.setFormFlattening(true);
		ps.close();
		FileInputStream fileInputStream=new FileInputStream(contactPdfPath+File.separator+contractNo+"_汽车抵押合同.pdf");
		response.setHeader("content-disposition", "attachment;filename="+contractNo+new String("_汽车抵押合同.pdf".getBytes("UTF-8"),"ISO-8859-1"));
		response.setHeader("content-type", "application/pdf");
		ServletOutputStream outputStream=response.getOutputStream();
		int len=1;
		byte[] bs=new byte[1024];
		while((len=fileInputStream.read(bs))!=-1) {
			outputStream.write(bs,0,len);
		}
		fileInputStream.close();
		reader.close();
    }
    
    /**
     * 下载抵押人特别声明和委托扣款授权书
     */
    @RequestMapping(value = "/process/downLoadrzzlhtfjwb", method = RequestMethod.GET)
    public void downLoadrzzlhtfjwb(HttpServletResponse response,@RequestParam Map<String,Object> params) throws Exception{
    	PdfReader reader=new PdfReader(new ClassPathResource("contactTemplate/szqr-rzzl-rzzlht-fjwb.pdf").getInputStream());
		Long applyid = Long.parseLong(params.get("applyId").toString());

    	String contractNo="";
    	ContractInfo contractInfo =contractInfoService.selectByApplyId(applyid);
    	ApplyInfo applyInfo =applyInfoService.selectById(applyid);
		CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
		//查询汽车信息
		CarInfoVo carInfoVo=carInfoService.findCarInfoByApplyId(applyid);
		
    	if(contractInfo==null) {
			contractNo=this.returnContractNo();
    		ContractInfo contract=new ContractInfo();
    		contract.setApplyId(applyid);
    		contract.setContractNo(contractNo);
    		contract.setCreateTime(new Date());
    		contract.setUpdateTime(new Date());
    		contract.setCustId(customerInfo.getId());
    		contract.setContractTitle4("抵押人特别声明");
    		contract.setContractUrl4(contractNo+"_抵押人特别声明.pdf");
    		contractInfoService.save(contract);
    	}else {
    		contractNo=contractInfo.getContractNo();
    		contractInfo.setContractTitle4("抵押人特别声明");
    		contractInfo.setContractUrl4(contractNo+"_抵押人特别声明.pdf");
    		contractInfoService.updateWithOutNull(contractInfo);
    	}
    	FileOutputStream out=new FileOutputStream(contactPdfPath+File.separator+contractNo+"_抵押人特别声明.pdf");
    	PdfStamper ps=new PdfStamper(reader,out);
		AcroFields s=ps.getAcroFields();
		
		Map fieldMap=s.getFields();
		
		Iterator entrys=fieldMap.entrySet().iterator();
		while(entrys.hasNext()) {
			Map.Entry entry=(Map.Entry)entrys.next();
			System.out.println(entry.getKey()+"------"+entry.getValue());
		}
		
		BaseFont bf=BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
		Calendar calendar=Calendar.getInstance();
		s.setFieldProperty("customerName0", "textfont", bf, null);
		s.setFieldProperty("customerName1", "textfont", bf, null);
		s.setFieldProperty("customerName2", "textfont", bf, null);
		s.setFieldProperty("customerName3", "textfont", bf, null);
		s.setFieldProperty("customerName4", "textfont", bf, null);
		s.setFieldProperty("certType", "textfont", bf, null);
		s.setFieldProperty("address", "textfont", bf, null);
		s.setFieldProperty("carBand", "textfont", bf, null);
		s.setFieldProperty("yhzh-khyh1", "textfont", bf, null);
		s.setFieldProperty("yhzh-zh1", "textfont", bf, null);
		s.setFieldProperty("carBand", "textfont", bf, null);
		s.setFieldProperty("carColor", "textfont", bf, null);
		s.setFieldProperty("carEngineNo", "textfont", bf, null);
		s.setFieldProperty("carVin", "textfont", bf, null);
		s.setFieldProperty("carModel", "textfont", bf, null);
		s.setFieldProperty("carNo", "textfont", bf, null);
		
		s.setFieldProperty("carInfo", "textfont", bf, null);//车辆型号
		
		s.setField("customerName1", customerInfo.getName());//委托人
		s.setField("carInfo", carInfoVo.getCarBrand());
		s.setField("contractNo0", contractNo);
		s.setField("contractNo1", contractNo);
		s.setField("htqdrq-year0", String.valueOf(calendar.get(Calendar.YEAR)));
		s.setField("htqdrq-month0", String.valueOf(calendar.get(Calendar.MONTH)+1));
		s.setField("htqdrq-day0", String.valueOf(calendar.get(Calendar.DATE)));
		s.setField("htqdrq-year1", String.valueOf(calendar.get(Calendar.YEAR)));
		s.setField("htqdrq-month1", String.valueOf(calendar.get(Calendar.MONTH)+1));
		s.setField("htqdrq-day1", String.valueOf(calendar.get(Calendar.DATE)));
		s.setField("customerName0", customerInfo.getName());
		s.setField("certNo", customerInfo.getCertId());
		s.setField("certType", "身份证");
		s.setField("address",customerInfo.getLiveAddress());

		FinalJudgementInfo judgementInfo = finalJudgementInfoService.selectByApplyId(applyid);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String htqdrq = sdf.format(judgementInfo.getAuditTime());
		String [] htqdrqArr = htqdrq.split("-");

		s.setField("htqdrq-year0",htqdrqArr[0]);
		s.setField("htqdrq-month0",htqdrqArr[1]);
		s.setField("htqdrq-day0",htqdrqArr[2]);

		s.setField("htqdrq-year1",htqdrqArr[0]);
		s.setField("htqdrq-month1",htqdrqArr[1]);
		s.setField("htqdrq-day1",htqdrqArr[2]);

		s.setField("dkrq-ks-year0",htqdrqArr[0]);
		s.setField("dkrq-ks-month0",htqdrqArr[1]);
		s.setField("dkrq-ks-day0",htqdrqArr[2]);

		s.setField("dkrq-ks-year1",htqdrqArr[0]);
		s.setField("dkrq-ks-month1",htqdrqArr[1]);
		s.setField("dkrq-ks-day1",htqdrqArr[2]);

		calendar.setTime(judgementInfo.getAuditTime());
		calendar.add(Calendar.MONTH,judgementInfo.getLoanPeriod());
		String dkrq = sdf.format(calendar.getTime());
		String [] dkrqArr = dkrq.split("-");

		s.setField("dkrq-js-year0",dkrqArr[0]);
		s.setField("dkrq-js-month0",dkrqArr[1]);
		s.setField("dkrq-js-day0",dkrqArr[2]);

		s.setField("dkrq-js-year1",dkrqArr[0]);
		s.setField("dkrq-js-month1",dkrqArr[1]);
		s.setField("dkrq-js-day1",dkrqArr[2]);
		
		//授权人信息
		s.setField("customerName2",params.get("accountName").toString());//银行卡户名
		s.setField("yhzh-zh0",params.get("bankNum").toString()); //银行卡账号
		s.setField("yhzh-khyh0",params.get("bankName").toString());//银行卡开户行
		s.setField("certNo20",customerInfo.getCertId());//身份证号码
		s.setField("telphone0",customerInfo.getMobile());//手机
		
		//设置授权人信息
		s.setField("customerName4",params.get("accountName").toString());//银行卡户名
		s.setField("yhzh-zh1",params.get("bankNum").toString()); //银行卡账号
		s.setField("yhzh-khyh1",params.get("bankName").toString());//银行卡开户行
		s.setField("certNo21",customerInfo.getCertId());//身份证号码
		s.setField("sqje",params.get("sqAmount").toString());//授权金额
		s.setField("telphone1",customerInfo.getMobile());//手机

		if(carInfoVo!=null) {
			s.setField("carBand",carInfoVo.getCarBrand());
			s.setField("carEngineNo",carInfoVo.getEngineNo());
			s.setField("carVin",carInfoVo.getVin());
			s.setField("carModel",carInfoVo.getRegistrationCarType());
			s.setField("carColor",carInfoVo.getCarColor());
			s.setField("carNo",carInfoVo.getCarNum());
		}

		ps.setFormFlattening(true);
		ps.close();
		FileInputStream fileInputStream=new FileInputStream(contactPdfPath+File.separator+contractNo+"_抵押人特别声明.pdf");
		response.setHeader("content-disposition", "attachment;filename="+contractNo+new String("_抵押人特别声明.pdf".getBytes("UTF-8"),"ISO-8859-1"));
		response.setHeader("content-type", "application/pdf");
		ServletOutputStream outputStream=response.getOutputStream();
		int len=1;
		byte[] bs=new byte[1024];
		while((len=fileInputStream.read(bs))!=-1) {
			outputStream.write(bs,0,len);
		}
		fileInputStream.close();
		reader.close();
    }
    
    /**
     * 下载汽车融资租赁合同
     */
    @RequestMapping(value = "/process/downLoadrzzlhtzht", method = RequestMethod.GET)
    public void downLoadrzzlhtzht(HttpServletResponse response,@RequestParam Map<String,Object> params) throws Exception{
		Double buyCarMoney = params.get("buyCarMoney")==null||params.get("buyCarMoney")==""? null:Double.parseDouble(String.valueOf(params.get("buyCarMoney")));//车辆购车款总额
		Double rongZiMoney = params.get("rongZiMoney")==null||params.get("rongZiMoney")==""? null:Double.parseDouble(String.valueOf(params.get("rongZiMoney")));//融资总额
		Double rongZiMoneyStart = params.get("rongZiMoneyStart")==null||params.get("rongZiMoneyStart")==""? null:Double.parseDouble(String.valueOf(params.get("rongZiMoneyStart")));//融资首付款
		Double rongZiMoneyEnd = params.get("rongZiMoneyEnd")==null||params.get("rongZiMoneyEnd")==""? null:Double.parseDouble(String.valueOf(params.get("rongZiMoneyEnd")));//融资尾付款
		Double carRongZiProject = params.get("carRongZiProject")==null||params.get("carRongZiProject")==""? null:Double.parseDouble(String.valueOf(params.get("carRongZiProject")));//车辆融资项目
		String qiXian = String.valueOf(params.get("qiXian"));//租赁期限
		Double monthMoney = params.get("monthMoney")==null||params.get("monthMoney")==""? null:Double.parseDouble(String.valueOf(params.get("monthMoney")));//每月还多少
		Double baoZhengJin = params.get("baoZhengJin")==null||params.get("baoZhengJin")==""? 0:Double.parseDouble(String.valueOf(params.get("baoZhengJin")));//保证金

    	PdfReader reader=new PdfReader(new ClassPathResource("contactTemplate/szqr-rzzl-rzzlht-zht.pdf").getInputStream());
    	Long applyid = Long.parseLong(params.get("applyId").toString());
    	String signAddress = params.get("signAddress").toString();
		String contractNo="";
    	ContractInfo contractInfo =contractInfoService.selectByApplyId(applyid);
    	ApplyInfo applyInfo =applyInfoService.selectById(applyid);
		CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
		//查询汽车信息
		CarInfoVo carInfoVo=carInfoService.findCarInfoByApplyId(applyid);
		
    	if(contractInfo==null) {
			contractNo=this.returnContractNo();
    		ContractInfo contract=new ContractInfo();
    		contract.setApplyId(applyid);
    		contract.setContractNo(contractNo);
    		contract.setCreateTime(new Date());
    		contract.setUpdateTime(new Date());
    		contract.setCustId(customerInfo.getId());
    		contract.setContractTitle5("汽车融资租赁合同(主要条款)");
    		contract.setContractUrl5(contractNo+"_汽车融资租赁合同(主要条款).pdf");
    		contractInfoService.save(contract);
    	}else {
    		contractNo=contractInfo.getContractNo();
    		contractInfo.setContractTitle5("汽车融资租赁合同(主要条款)");
    		contractInfo.setContractUrl5(contractNo+"_汽车融资租赁合同(主要条款).pdf");
    		contractInfoService.updateWithOutNull(contractInfo);
    	}
    	FileOutputStream out=new FileOutputStream(contactPdfPath+File.separator+contractNo+"_汽车融资租赁合同(主要条款).pdf");
    	PdfStamper ps=new PdfStamper(reader,out);
		AcroFields s=ps.getAcroFields();
		
		Map fieldMap=s.getFields();
		
		Iterator entrys=fieldMap.entrySet().iterator();
		while(entrys.hasNext()) {
			Map.Entry entry=(Map.Entry)entrys.next();
			System.out.println(entry.getKey()+"------"+entry.getValue());
		}
		
		BaseFont bf=BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
		Calendar date=Calendar.getInstance();
		s.setFieldProperty("renterName", "textfont", bf, null);
		s.setFieldProperty("renterCertType", "textfont", bf, null);
		s.setFieldProperty("renterAddress", "textfont", bf, null);
		s.setFieldProperty("customerName3", "textfont", bf, null);
		s.setFieldProperty("customerName4", "textfont", bf, null);
		s.setFieldProperty("certType", "textfont", bf, null);
		s.setFieldProperty("address", "textfont", bf, null);
		s.setFieldProperty("contractAddress", "textfont", bf, null);
		s.setFieldProperty("carBand0", "textfont", bf, null);
		s.setFieldProperty("carColor0", "textfont", bf, null);
		s.setFieldProperty("carEngineNo0", "textfont", bf, null);
		s.setFieldProperty("carVin0", "textfont", bf, null);
		s.setFieldProperty("carModel0", "textfont", bf, null);
		s.setFieldProperty("carNo0", "textfont", bf, null);

		s.setFieldProperty("carBand1", "textfont", bf, null);
		s.setFieldProperty("carEngineNo1", "textfont", bf, null);
		s.setFieldProperty("carVin1", "textfont", bf, null);
		s.setFieldProperty("carVin2", "textfont", bf, null);
		s.setFieldProperty("carModel1", "textfont", bf, null);
		s.setFieldProperty("carNo1", "textfont", bf, null);

		s.setFieldProperty("carBand1", "textfont", bf, null);
		s.setFieldProperty("carEngineNo1", "textfont", bf, null);
		s.setFieldProperty("carVin1", "textfont", bf, null);
		s.setFieldProperty("carVin2", "textfont", bf, null);
		s.setFieldProperty("carModel1", "textfont", bf, null);
		s.setFieldProperty("carNo1", "textfont", bf, null);

		s.setFieldProperty("renterName", "textfont", bf, null);
		s.setFieldProperty("renterEcp1-name", "textfont", bf, null);
		s.setFieldProperty("renterEcp2-name", "textfont", bf, null);
		s.setFieldProperty("renterEcp3-name", "textfont", bf, null);

		s.setFieldProperty("bailName", "textfont", bf, null);
		s.setFieldProperty("bailCertType", "textfont", bf, null);
		s.setFieldProperty("bailAddress", "textfont", bf, null);
		s.setFieldProperty("czr-qr-khyh0", "textfont", bf, null);
		s.setFieldProperty("czr-qr-hm0", "textfont", bf, null);
		s.setFieldProperty("czr-qr-khyh1", "textfont", bf, null);
		s.setFieldProperty("czr-qr-hm1", "textfont", bf, null);
		//车辆购车总额(rzxm-clgccze-dx)
		s.setFieldProperty("rzxm-clgccze-dx", "textfont", bf, null);
		s.setField("rzxm-clgccze-dx",buyCarMoney==null?"":NumberToCN.number2CNMontrayUnit(new BigDecimal(buyCarMoney)));
		s.setFieldProperty("rzxm-clgccze-xx", "textfont", bf, null);
		s.setField("rzxm-clgccze-xx",buyCarMoney==null?"":String.valueOf(buyCarMoney));
		//融资总额(rzxm-rzze-dx)
		s.setFieldProperty("rzxm-rzze-dx", "textfont", bf, null);
		s.setField("rzxm-rzze-dx",rongZiMoney==null?"":NumberToCN.number2CNMontrayUnit(new BigDecimal(rongZiMoney)));
		s.setFieldProperty("rzxm-rzze-xx0", "textfont", bf, null);
		s.setField("rzxm-rzze-xx0",rongZiMoney==null?"":String.valueOf(rongZiMoney));
		//融资首付款(rzxm-rzsfk-dx)
		s.setFieldProperty("rzxm-rzsfk-dx", "textfont", bf, null);
		s.setField("rzxm-rzsfk-dx",rongZiMoneyStart==null?"":NumberToCN.number2CNMontrayUnit(new BigDecimal(rongZiMoneyStart)));
		s.setFieldProperty("rzxm-rzsfk-xx0", "textfont", bf, null);
		s.setField("rzxm-rzsfk-xx0",rongZiMoneyStart==null?"":String.valueOf(rongZiMoneyStart));
		//融资尾款(rzxm-rzwfk-dx)
		s.setFieldProperty("rzxm-rzwfk-dx", "textfont", bf, null);
		s.setField("rzxm-rzwfk-dx",rongZiMoneyEnd==null?"":NumberToCN.number2CNMontrayUnit(new BigDecimal(rongZiMoneyEnd)));
		s.setFieldProperty("rzxm-rzwfk-xx0", "textfont", bf, null);
		s.setField("rzxm-rzwfk-xx0",rongZiMoneyEnd==null?"":String.valueOf(rongZiMoneyEnd));
		//车辆融资项目金额(rzxm-clrzxx-ck-dx)
		s.setFieldProperty("rzxm-clrzxx-ck-dx", "textfont", bf, null);
		s.setField("rzxm-clrzxx-ck-dx",carRongZiProject==null?"":NumberToCN.number2CNMontrayUnit(new BigDecimal(carRongZiProject)));
		s.setFieldProperty("rzxm-clrzxx-ck-xx", "textfont", bf, null);
		s.setField("rzxm-clrzxx-ck-xx",carRongZiProject==null?"":String.valueOf(carRongZiProject));
		//购置
		s.setFieldProperty("rzxm-clrzxx-gzs-dx", "textfont", bf, null);
		s.setField("rzxm-clrzxx-gzs-dx","零");
		s.setFieldProperty("rzxm-clrzxx-gzs-xx", "textfont", bf, null);
		s.setField("rzxm-clrzxx-gzs-xx","0");
		//保险
		s.setFieldProperty("rzxm-clrzxx-bxf-dx", "textfont", bf, null);
		s.setField("rzxm-clrzxx-bxf-dx","零");
		s.setFieldProperty("rzxm-clrzxx-bxf-xx", "textfont", bf, null);
		s.setField("rzxm-clrzxx-bxf-xx","0");
		//车船费
		s.setFieldProperty("rzxm-clrzxx-ccs-dx", "textfont", bf, null);
		s.setField("rzxm-clrzxx-ccs-dx","零");
		s.setFieldProperty("rzxm-clrzxx-ccs-xx", "textfont", bf, null);
		s.setField("rzxm-clrzxx-ccs-xx","0");
		//加装
		s.setFieldProperty("rzxm-clrzxx-jzf-dx", "textfont", bf, null);
		s.setField("rzxm-clrzxx-jzf-dx","零");
		s.setFieldProperty("rzxm-clrzxx-jzf-xx", "textfont", bf, null);
		s.setField("rzxm-clrzxx-jzf-xx","0");
		//租赁期限（leasehold0）
		s.setFieldProperty("leasehold0", "textfont", bf, null);
		s.setField("leasehold0",qiXian);
		//每月还款（zjfkfs-mqhkzj-xx0）  zjfkfs-mqhkzj-dx
        s.setFieldProperty("zjfkfs-mqhkzj-dx", "textfont", bf, null);
        s.setField("zjfkfs-mqhkzj-dx",rongZiMoneyStart==null?"":NumberToCN.number2CNMontrayUnit(new BigDecimal(monthMoney)));
		s.setFieldProperty("zjfkfs-mqhkzj-xx0", "textfont", bf, null);
		s.setField("zjfkfs-mqhkzj-xx0",monthMoney==null?"":String.valueOf(monthMoney));
		//保证金 zlbzj-dx
		s.setFieldProperty("zlbzj-dx", "textfont", bf, null);
		s.setField("zlbzj-dx",baoZhengJin==null?"零":NumberToCN.number2CNMontrayUnit(new BigDecimal(baoZhengJin)));
		//融资合同第三页，需要有填写的界面，车辆融资首付款、车辆融资金额
		s.setFieldProperty("czr-qr-sfk", "textfont", bf, null);
		s.setField("czr-qr-sfk",rongZiMoneyStart==null?"":String.valueOf(rongZiMoneyStart));
		s.setFieldProperty("czr-qr-bzj0", "textfont", bf, null);
		s.setField("czr-qr-bzj0",baoZhengJin==null||baoZhengJin==0?"0":String.valueOf(baoZhengJin));
		s.setFieldProperty("czr-qr-sygck", "textfont", bf, null);
		s.setField("czr-qr-sygck",carRongZiProject==null?"":String.valueOf(carRongZiProject));

		//表
		//购置数量
		s.setFieldProperty("fksjb-gzsl", "textfont", bf, null);
		s.setField("fksjb-gzsl","壹");
		//车辆价格
		s.setFieldProperty("fksjb-cljg", "textfont", bf, null);
		s.setField("fksjb-cljg",buyCarMoney==null?"":String.valueOf(buyCarMoney));
		//租赁年限
		s.setFieldProperty("leasehold1", "textfont", bf, null);
		s.setField("leasehold1",qiXian);
		//首付款支付日
		s.setFieldProperty("fksjb-sfkzfr-year", "textfont", bf, null);
		s.setField("fksjb-sfkzfr-year", String.valueOf(date.get(Calendar.YEAR)));
		s.setFieldProperty("fksjb-sfkzfr-month", "textfont", bf, null);
		s.setField("fksjb-sfkzfr-month",String.valueOf(date.get(Calendar.MONTH)+1));
		s.setFieldProperty("fksjb-sfkzfr-day", "textfont", bf, null);
		s.setField("fksjb-sfkzfr-day",String.valueOf(date.get(Calendar.DATE)));
		//首付款
		s.setFieldProperty("rzxm-rzsfk-xx1", "textfont", bf, null);
		s.setField("rzxm-rzsfk-xx1",rongZiMoneyStart==null?"":String.valueOf(rongZiMoneyStart));
		//未付款
		s.setFieldProperty("rzxm-rzwfk-xx1", "textfont", bf, null);
		s.setField("rzxm-rzwfk-xx1","0");
		//承租人融资金额
		s.setFieldProperty("rzxm-rzze-xx1", "textfont", bf, null);
		s.setField("rzxm-rzze-xx1",rongZiMoney==null?"":String.valueOf(rongZiMoney));
		//承租人保证金
		s.setFieldProperty("czr-qr-bzj1", "textfont", bf, null);
		s.setField("czr-qr-bzj1",baoZhengJin==null?"":String.valueOf(baoZhengJin));
		//每月租金支付金额
		s.setFieldProperty("zjfkfs-mqhkzj-xx1", "textfont", bf, null);
		s.setField("zjfkfs-mqhkzj-xx1",monthMoney==null?"":String.valueOf(monthMoney));



		s.setField("contractNo0", contractNo);
		s.setField("contractNo1", contractNo);
		s.setField("contractNo2", contractNo);
		s.setField("contractNo3", contractNo);
		s.setField("contractNo4", contractNo);
		s.setField("contractNo5", contractNo);
		s.setField("contractNo6", contractNo);
		s.setField("contractNo7", contractNo);
		s.setField("contractNo8", contractNo);
		s.setField("contractNo9", contractNo);
		s.setField("contractNo10", contractNo);
		s.setField("htqdrq-year0", String.valueOf(date.get(Calendar.YEAR)));
		s.setField("htqdrq-month0", String.valueOf(date.get(Calendar.MONTH)+1));
		s.setField("htqdrq-day0", String.valueOf(date.get(Calendar.DATE)));
		s.setField("htqdrq-year1", String.valueOf(date.get(Calendar.YEAR)));
		s.setField("htqdrq-month1", String.valueOf(date.get(Calendar.MONTH)+1));
		s.setField("htqdrq-day1", String.valueOf(date.get(Calendar.DATE)));
		s.setField("contractAddress", signAddress);
		s.setField("czr-qr-khyh0", params.get("custBankName").toString());
		s.setField("czr-qr-hm0", params.get("custAccountName").toString());
		s.setField("czr-qr-zh0", params.get("custBankNum").toString());
		s.setField("czr-qr-khyh1", params.get("custBankName").toString());
		s.setField("czr-qr-hm1", params.get("custAccountName").toString());
		s.setField("czr-qr-zh1", params.get("custBankNum").toString());



		// =============承租人信息
		s.setField("renterName", customerInfo.getName());
		s.setField("renterCertType", "身份证");
		s.setField("renterCertNo", customerInfo.getCertId());
		s.setField("renterAddress", customerInfo.getLiveAddress());
		s.setField("renterTelphone", customerInfo.getMobile());
		s.setField("renterEcp1-name", customerInfo.getContractName1());
		s.setField("renterEcp1-mobile", customerInfo.getContractPhone1());
		s.setField("renterEcp2-name", customerInfo.getContractName2());
		s.setField("renterEcp2-mobile", customerInfo.getContractPhone2());
		s.setField("renterEcp3-name", customerInfo.getContractName3());
		s.setField("renterEcp3-mobile", customerInfo.getContractPhone3());


		//===============保证人信息（如果有）
		GongjieInfo gongjieInfo = gongjieInfoService.selectByCustId(customerInfo.getId());
		if(null != gongjieInfo){
			s.setField("bailName", gongjieInfo.getName());
			s.setField("bailCertType", "身份证");
			s.setField("bailCertNo", gongjieInfo.getCertId());
			s.setField("bailAddress", gongjieInfo.getLiveAddress());
			s.setField("bailTelphone", gongjieInfo.getMobile());
		}


		//租赁车辆信息
		if(carInfoVo!=null) {
			s.setField("carNo0",carInfoVo.getCarNum());
			s.setField("carBand0",carInfoVo.getCarBrand());
			s.setField("carEngineNo0",carInfoVo.getEngineNo());
			s.setField("carVin0",carInfoVo.getVin());
			s.setField("carModel0",carInfoVo.getRegistrationCarType());
//			s.setField("carColor",carInfoVo.getCarColor());

			s.setField("carNo1",carInfoVo.getCarNum());
			s.setField("carBand1",carInfoVo.getCarBrand());
			s.setField("carEngineNo1",carInfoVo.getEngineNo());
			s.setField("carVin1",carInfoVo.getVin());
			s.setField("carModel1",carInfoVo.getRegistrationCarType());

			s.setField("carVin2",carInfoVo.getVin());

		}

		ps.setFormFlattening(true);
		ps.close();
		FileInputStream fileInputStream=new FileInputStream(contactPdfPath+File.separator+contractNo+"_汽车融资租赁合同(主要条款).pdf");
		response.setHeader("content-disposition", "attachment;filename="+contractNo+new String("_汽车融资租赁合同(主要条款).pdf".getBytes("UTF-8"),"ISO-8859-1"));
		response.setHeader("content-type", "application/pdf");
		ServletOutputStream outputStream=response.getOutputStream();
		int len=1;
		byte[] bs=new byte[1024];
		while((len=fileInputStream.read(bs))!=-1) {
			outputStream.write(bs,0,len);
		}
		fileInputStream.close();
		reader.close();
    }

    /**
     * 下载委托书
     */
    @RequestMapping(value = "/process/downLoadwts", method = RequestMethod.GET)
    public void downLoadwts(HttpServletResponse response,Long applyid) throws Exception{
    	PdfReader reader=new PdfReader(new ClassPathResource("contactTemplate/szqr-rzzl-wts-cgs.pdf").getInputStream());
    	//生成合同编号
		String contractNo="";
    	
    	ContractInfo contractInfo =contractInfoService.selectByApplyId(applyid);
    	ApplyInfo applyInfo =applyInfoService.selectById(applyid);
		CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
		
    	if(contractInfo==null) {
			contractNo=this.returnContractNo();
    		ContractInfo contract=new ContractInfo();
    		contract.setApplyId(applyid);
    		contract.setContractNo(contractNo);
    		contract.setCreateTime(new Date());
    		contract.setUpdateTime(new Date());
    		contract.setCustId(customerInfo.getId());
    		contract.setContractTitle6("委托书");
    		contract.setContractUrl6(contractNo+"_委托书.pdf");
    		contractInfoService.save(contract);
    	}else {
    		contractNo=contractInfo.getContractNo();
    		contractInfo.setContractTitle6("委托书");
    		contractInfo.setContractUrl6(contractNo+"_委托书.pdf");
    		contractInfoService.updateWithOutNull(contractInfo);
    	}
    	FileOutputStream out=new FileOutputStream(contactPdfPath+File.separator+contractNo+"_委托书.pdf");
    	PdfStamper ps=new PdfStamper(reader,out);
		AcroFields s=ps.getAcroFields();
		
		Map fieldMap=s.getFields();
		
		Iterator entrys=fieldMap.entrySet().iterator();
		while(entrys.hasNext()) {
			Map.Entry entry=(Map.Entry)entrys.next();
			System.out.println(entry.getKey()+"------"+entry.getValue());
		}
		
		BaseFont bf=BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
		Calendar date=Calendar.getInstance();
		
		ps.setFormFlattening(true);
		ps.close();
		FileInputStream fileInputStream=new FileInputStream(contactPdfPath+File.separator+contractNo+"_委托书.pdf");
		response.setHeader("content-disposition", "attachment;filename="+contractNo+new String("_委托书.pdf".getBytes("UTF-8"),"ISO-8859-1"));
		response.setHeader("content-type", "application/pdf");
		ServletOutputStream outputStream=response.getOutputStream();
		int len=1;
		byte[] bs=new byte[1024];
		while((len=fileInputStream.read(bs))!=-1) {
			outputStream.write(bs,0,len);
		}
		fileInputStream.close();
		reader.close();
    }
    
    /**
     * 安装GPS申请（内勤）
     * @return
     */
    @ApiOperation(value = "安装GPS申请（内勤）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/gpsInstallApply", method = RequestMethod.POST)
    @ResponseBody
    public Object gpsInstallApply(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.gpsInstallApply(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 验车师安装GPS完成（验车师）
     * @return
     */
    @ApiOperation(value = "验车师安装GPS完成（验车师）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/gpsInstallComplete", method = RequestMethod.POST)
    @ResponseBody
    public Object gpsInstallComplete(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.gpsInstallComplete(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * GPS安装完成确认（面审主管）
     * @return
     */
    @ApiOperation(value = "GPS安装完成确认（面审主管）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/gpsInstallConfirm", method = RequestMethod.POST)
    @ResponseBody
    public Object gpsInstallConfirm(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.gpsInstallConfirm(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 抵押申请（内勤）
     * @return
     */
    @ApiOperation(value = "抵押申请（内勤）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/mortgageCarApply", method = RequestMethod.POST)
    @ResponseBody
    public Object mortgageCarApply(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());
		CarBussMortgageInfo carBussMortgageInfo = carBussMortgageInfoService.selectByApplyId(applyId);
		if(carBussMortgageInfo == null){
			return ResultVO.build(ErrorCode.NO_SAVE_APPLY);
		}
        try{
            return processBussiness.mortgageCarApply(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 确认抵押申请材料（抵押专员）
     * @return
     */
    @ApiOperation(value = "确认抵押申请材料（抵押专员）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/mortgageCarConfirmApply", method = RequestMethod.POST)
    @ResponseBody
    public Object mortgageCarConfirmApply(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.mortgageCarConfirmApply(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 接收资料确认（抵押专员）
     * @return
     */
    @ApiOperation(value = "接收资料确认（抵押专员）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/mortgageCarConfirmPaper", method = RequestMethod.POST)
    @ResponseBody
    public Object mortgageCarConfirmPaper(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

		CarBussMortgageInfo carBussMortgageInfo = carBussMortgageInfoService.selectByApplyId(applyId);
		carBussMortgageInfo.setRecvConfirm(1);
		carBussMortgageInfo.setRecvConfirmDate(new Date());
		carBussMortgageInfoService.updateWithOutNull(carBussMortgageInfo);
        try{
            return processBussiness.mortgageCarConfirmPaper(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }


    /**
     * 抵押已受理（抵押专员）
     * @return
     */
    @ApiOperation(value = "抵押已受理（抵押专员）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/mortgageCarBussHanding", method = RequestMethod.POST)
    @ResponseBody
    public Object mortgageCarBussHanding(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

		CarBussMortgageInfo carBussMortgageInfo = carBussMortgageInfoService.selectByApplyId(applyId);
		//校验小票
		if(null != carBussMortgageInfo){
			String billAttachUrl = carBussMortgageInfo.getBillAttachUrl();
			if(null == billAttachUrl ||"".equals(billAttachUrl)){
				return ResultVO.build(ErrorCode.NO_ACCEPT_CONFIRM);
			}
		}

		carBussMortgageInfo.setAcceptConfirm(1);
		carBussMortgageInfo.setAcceptConfirmDate(new Date());
		carBussMortgageInfoService.updateWithOutNull(carBussMortgageInfo);
        try{
            return processBussiness.mortgageCarBussHanding(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 抵押办理完成（抵押专员）
     * @return
     */
    @ApiOperation(value = "抵押办理完成（抵押专员）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/mortgageCarBussFinish", method = RequestMethod.POST)
    @ResponseBody
    public Object mortgageCarBussFinish(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());
		CarBussMortgageInfo carBussMortgageInfo = carBussMortgageInfoService.selectByApplyId(applyId);

		carBussMortgageInfo.setCompleteConfirm(1);//抵押业务是否完成
		carBussMortgageInfo.setCompleteConfirmDate(new Date());//抵押业务完成时间
		carBussMortgageInfoService.updateWithOutNull(carBussMortgageInfo);

		//车辆抵押登记信息表信息完成之后在抵押记录表中增加数据
		ApplyInfo applyInfo = applyInfoService.selectById(applyId);
		CustomerInfo customerInfo = customerInfoService.selectById(applyInfo.getCustId());
		CarMortgageInfo carMortgageInfo = new CarMortgageInfo();
		carMortgageInfo.setCarId(carBussMortgageInfo.getCarId());
		carMortgageInfo.setName(customerInfo.getName());
		carMortgageInfo.setCertId(customerInfo.getCertId());
		carMortgageInfo.setRegDate(carBussMortgageInfo.getCompleteConfirmDate());
		carMortgageInfoService.saveOrUpdate(carMortgageInfo);
        try{
            return processBussiness.mortgageCarBussFinish(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 资料存留（内勤）
     * @return
     */
    @ApiOperation(value = "资料存留（内勤）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/dataKeep", method = RequestMethod.POST)
    @ResponseBody
    public Object dataKeep(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.dataKeep(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }


    /**
     * 放款申请（内勤）
     * @return
     */
    @ApiOperation(value = "放款申请（内勤）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/payApply", method = RequestMethod.POST)
    @ResponseBody
    public Object payApply(@RequestParam Long applyId, 
    		@RequestParam String custMobile,
    		@RequestParam String receptionAmount,
    		@RequestParam String isPerCharge,
    		@RequestParam String isReplaceCost,
    		@RequestParam String bankCardNo,
    		@RequestParam String bankName,
    		@RequestParam String receptionDepart,
    		@RequestParam String receptionManager,
    		@RequestParam Integer result, @RequestParam String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());
        
        try{
            return processBussiness.payApply(applyId, operatorId, result, receptionDepart,
            		receptionManager,bankCardNo,bankName,custMobile,
            		receptionAmount,isPerCharge,isReplaceCost,
            		resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 放款内勤主管审核（内勤主管）
     * @return
     */
    @ApiOperation(value = "放款内勤主管审核（内勤主管）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/payNqzgAudit", method = RequestMethod.POST)
    @ResponseBody
    public Object payNqzgAudit(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.payNqzgAudit(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 放款业务经理审核（业务经理）
     * @return
     */
    @ApiOperation(value = "放款业务经理审核（业务经理）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/payBussAudit", method = RequestMethod.POST)
    @ResponseBody
    public Object payBussAudit(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.payBussAudit(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 放款财务首次审核（财务）
     * @return
     */
    @ApiOperation(value = "放款财务首次审核（财务）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/payFinFirstAudit", method = RequestMethod.POST)
    @ResponseBody
    public Object payFinFirstAudit(Long applyId, Integer result,String amount, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.payFinFirstAudit(applyId, operatorId,amount,result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 放款财务经理首次审核（财务经理）
     * @return
     */
    @ApiOperation(value = "放款财务经理首次审核（财务经理）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/payFinBussFirstAudit", method = RequestMethod.POST)
    @ResponseBody
    public Object payFinBussFirstAudit(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.payFinBussFirstAudit(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 放款前台财务确认（前台财务）
     * @return
     */
    @ApiOperation(value = "放款前台财务确认（前台财务）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/payReceptionConfirm", method = RequestMethod.POST)
    @ResponseBody
    public Object payReceptionConfirm(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.payReceptionConfirm(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 放款财务二次审核（财务）
     * @return
     */
    @ApiOperation(value = "放款财务二次审核（财务）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/payFinSecondAudit", method = RequestMethod.POST)
    @ResponseBody
    public Object payFinSecondAudit(Long applyId, Integer result,String amount, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.payFinSecondAudit(applyId, operatorId,amount, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 放款财务经理二次审核（财务经理）
     * @return
     */
    @ApiOperation(value = "放款财务经理二次审核（财务经理）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/payFinBussSecondAudit", method = RequestMethod.POST)
    @ResponseBody
    public Object payFinBussSecondAudit(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.payFinBussSecondAudit(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 提前还款业务申请（内勤）
     * @return
     */
    @ApiOperation(value = "提前还款业务申请（内勤）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/onceEarlyApply", method = RequestMethod.POST)
    @ResponseBody
    public Object onceEarlyApply(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.onceEarlyApply(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 提前还款内勤主管审核（内勤主管）
     * @return
     */
    @ApiOperation(value = "提前还款内勤主管审核（内勤主管）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/onceEarlyNqzgAudit", method = RequestMethod.POST)
    @ResponseBody
    public Object onceEarlyNqzgAudit(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.onceEarlyNqzgAudit(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 提前还款业务经理审核（业务经理）
     * @return
     */
    @ApiOperation(value = "提前还款业务经理审核（业务经理）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/onceEarlyBussApprove", method = RequestMethod.POST)
    @ResponseBody
    public Object onceEarlyBussApprove(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.onceEarlyBussApprove(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 提前还款财务确认（财务）
     * @return
     */
    @ApiOperation(value = "提前还款财务确认（财务）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/onceEarlyFinConfirm", method = RequestMethod.POST)
    @ResponseBody
    public Object onceEarlyFinConfirm(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.onceEarlyFinConfirm(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 提前还款财务经理审核（财务经理）
     * @return
     */
    @ApiOperation(value = "提前还款财务经理审核（财务经理）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/onceEarlyFinBussConfirm", method = RequestMethod.POST)
    @ResponseBody
    public Object onceEarlyFinBussConfirm(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.onceEarlyFinBussConfirm(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 解押申请（内勤）
     * @return
     */
    @ApiOperation(value = "解押申请（内勤）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/detentionCarApply", method = RequestMethod.POST)
    @ResponseBody
    public Object detentionCarApply(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        //内勤提交解押申请，内勤结清确认状态设置为 1-确认
        CarDetentionInfo detentionInfo = detentionInfoService.selectByApplyId(applyId);
        if(null != detentionInfo){
            detentionInfo.setSettleConfirem(1);
        }
		detentionInfoService.saveOrUpdate(detentionInfo);
        try{
            return processBussiness.detentionCarApply(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }


    /**
     * 解押结清确认（财务）
     * @return
     */
    @ApiOperation(value = "解押结清确认（财务）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/detentionFinSettleConfirm", method = RequestMethod.POST)
    @ResponseBody
    public Object detentionFinSettleConfirm(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());
		CarDetentionInfo detentionInfo = detentionInfoService.selectByApplyId(applyId);
		if(null != detentionInfo){
			//财务结清时间和是否结清需要查询某个表，暂时未确定
			detentionInfo.setIsSettle(1);//财务是否结清 0-否  1-是
			detentionInfo.setSettleDate(new Date());//财务结清时间
		}
		detentionInfoService.saveOrUpdate(detentionInfo);
        try{
            return processBussiness.detentionFinSettleConfirm(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 解押申请材料确认（抵押专员）
     * @return
     */
    @ApiOperation(value = "解押申请材料确认（抵押专员）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/detentionConfirmApply", method = RequestMethod.POST)
    @ResponseBody
    public Object detentionConfirmApply(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.detentionConfirmApply(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }


    /**
     * 解押申请材料确认接收（抵押专员）
     * @return
     */
    @ApiOperation(value = "解押申请材料确认接收（抵押专员）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/detentionConfirmPaper", method = RequestMethod.POST)
    @ResponseBody
    public Object detentionConfirmPaper(Long applyId, Integer result, String resultTip){

		CarDetentionInfo detentionInfo = detentionInfoService.selectByApplyId(applyId);
		if(null != detentionInfo){
			detentionInfo.setRecvConfirm(result);//接收材料确认 0-否  1-是  2-驳回
			detentionInfo.setRecvConfirmDate(new Date());//接收材料确认时间
			detentionInfo.setRefuseReason(resultTip);//驳回原因
			detentionInfo.setAcceptConfirm(0);//解押是否受理  0-否  1-是
		}
		detentionInfoService.saveOrUpdate(detentionInfo);
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.detentionConfirmPaper(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 解押已受理（抵押专员）
     * @return
     */
    @ApiOperation(value = "解押已受理（抵押专员）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/detentionBussHanding", method = RequestMethod.POST)
    @ResponseBody
    public Object detentionBussHanding(Long applyId, Integer result, String resultTip){
		CarDetentionInfo detentionInfo = detentionInfoService.selectByApplyId(applyId);
		if(null != detentionInfo){
			detentionInfo.setAcceptConfirm(1);//解押是否受理  0-否  1-是
			detentionInfo.setAcceptConfirmDate(new Date());//解押受理时间
			detentionInfo.setCompleteConfirm(0);//解押是否完成  0--否 1--是
		}
		detentionInfoService.saveOrUpdate(detentionInfo);

        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.detentionBussHanding(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 解押完成（抵押专员）
     * @return
     */
    @ApiOperation(value = "解押完成（抵押专员）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/detetionBussFinish", method = RequestMethod.POST)
    @ResponseBody
    public Object detetionBussFinish(Long applyId, Integer result, String resultTip){
		CarDetentionInfo detentionInfo = detentionInfoService.selectByApplyId(applyId);
		if(null != detentionInfo){
			detentionInfo.setCompleteConfirm(1);//解押是否完成  0--否 1--是
		}
		detentionInfoService.saveOrUpdate(detentionInfo);

        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.detetionBussFinish(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * gps卸载申请（内勤）
     * @return
     */
    @ApiOperation(value = "gps卸载申请（内勤）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/gpsUninstallApply", method = RequestMethod.POST)
    @ResponseBody
    public Object gpsUninstallApply(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.gpsUninstallApply(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * gps卸载完成（验车师）
     * @return
     */
    @ApiOperation(value = "gps卸载完成（验车师）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/gpsUnistallFinish", method = RequestMethod.POST)
    @ResponseBody
    public Object gpsUnistallFinish(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.gpsUnistallFinish(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * gps卸载完成确认（面审主管）
     * @return
     */
    @ApiOperation(value = "gps卸载完成确认（面审主管）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/gpsUninstallConfirm", method = RequestMethod.POST)
    @ResponseBody
    public Object gpsUninstallConfirm(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.gpsUninstallConfirm(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 资料移交（内勤）
     * @return
     */
    @ApiOperation(value = "资料移交（内勤）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "订单号", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "result", value = "审核结果", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "resultTip", value = "审核结果描述", required = false, dataType = "string", paramType = "form"),
    })
    @RequestMapping(value = "/process/dataTranfser", method = RequestMethod.POST)
    @ResponseBody
    public Object dataTranfser(Long applyId, Integer result, String resultTip){
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try{
            return processBussiness.dataTranfser(applyId, operatorId, result, resultTip);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

}
