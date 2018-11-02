package com.moerlong.carloan.modular.paybackMgr.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.moerlong.carloan.modular.paybackMgr.warpper.CostOrderWarpper;
import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.modular.payMgr.entity.PayInfo;
import com.moerlong.carloan.modular.payMgr.warpper.PayOrderWarpper;
import com.moerlong.carloan.modular.paybackMgr.business.RepaymentBusiness;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentPayInfo;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentPlanInfo;
import com.moerlong.carloan.modular.paybackMgr.entity.vo.OnceRepaymentInfoVO;
import com.moerlong.carloan.modular.paybackMgr.entity.vo.RepaymentCalculatorVO;
import com.moerlong.carloan.modular.paybackMgr.entity.vo.SinglePeriodRepaymentPlanVO;
import com.moerlong.carloan.modular.paybackMgr.service.impl.RepaymentInfoServiceImpl;
import com.moerlong.carloan.util.CommonUtil;
import com.moerlong.carloan.util.RepeatRefuseUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentInfo;
import com.moerlong.carloan.modular.paybackMgr.service.RepaymentInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@Controller
//@Api(tags = { "controller接口类" })
//@RequestMapping(value = "/repaymentInfo")
public class RepaymentInfoController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(RepaymentInfoController.class);


	private String PREFIX = "/paybackMgr/repaymentInfo/";

	@Value("${system.excel.filepath}")
	private String exportFilePath;

	@Autowired
	RepaymentInfoService service;

	@Autowired
	RepaymentBusiness repaymentBusiness;
	
	/**
	 * 所有借款
	 * @return
	 */
	@RequestMapping(value = "/repaymentAllPage", method = RequestMethod.GET)
	public String repaymentAllPage() {
		return "/repayment/repaymentList.html";
	}
	
	/**
	 * 办理中的贷款
	 * @return
	 */
	@RequestMapping(value = "/repaymentHandlingPage", method = RequestMethod.GET)
	public String repaymentHandlingPage() {
		return "/repayment/hrepaymentList.html";
	}
	
	/**
	 * 已完成的借款
	 */
	@RequestMapping(value = "/repaymentFinishPage", method = RequestMethod.GET)
	public String repaymentFinishPage() {
		return "/repayment/frepaymentList.html";
	}
	
	/**
	 * 逾期还款
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/repaymentOverDuePage", method = RequestMethod.GET)
	public String repaymentOverDuePage() {
		return "/repayment/orepaymentList.html";
	}

	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "RepaymentInfo", value = "明细")
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(@RequestBody RepaymentInfo entity) {
		Map<String, Object> res = new HashMap<>();
		try {
			service.saveOrUpdate(entity);
			res.put("status", 0);
			res.put("msg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("msg", e.toString());
		}
		return res;
	}

	@ApiOperation(value = "删除")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "form", name = "id", required = true, dataType = "Long", value = "id") })
	@RequestMapping(value = "/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object deleteById(Long id) {
		Map<String, Object> res = new HashMap<>();
		try {
			service.delete(id);
			res.put("status", 0);
			res.put("msg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("msg", e.getMessage());
		}
		return res;
	}

	@ApiOperation(value = "根据ID查找")
	@ApiImplicitParam(paramType = "form", name = "id", required = true, dataType = "Long", value = "id")
	@RequestMapping(value = "/findById", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object findById(Long id) {
		Map<String, Object> res = new HashMap<>();
		try {
			res.put("entity", service.selectById(id));
			res.put("status", 0);
			res.put("msg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("msg", e.getMessage());
		}
		return res;
	}

	@ApiOperation(value = "分页查询")
	@ApiImplicitParam(paramType = "body", name = "queryMap", required = false, dataType = "Map", value = "查询条件")
	@RequestMapping(value = "/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(String custName,String contractNo,String curStatus,String overdueTimes, Integer limit, Integer offset) throws Exception{
		
		Map<String, Object> res = new HashMap<>();
		//Integer pageSize = 10; //页面大小
		RepaymentInfo repaymentInfo=new RepaymentInfo();
		repaymentInfo.setCustName(custName);
		repaymentInfo.setContractNo(contractNo);
		if(curStatus!=null) {
			repaymentInfo.setCurStatus(Integer.valueOf(curStatus));
		}
		if(overdueTimes!=null) {
			repaymentInfo.setOverdueTimes(Integer.valueOf(overdueTimes));
		}
		return this.service.selectByExampleWithPage(repaymentInfo, limit, offset);
			
		/*try {
			pageNum=(offset / limit + 1);
			pageSize=limit;
			
			Object pageInfo = this.service.selectPage(pageSize, pageNum, orderCondition);
			this.service.selectByExampleWithPage(repaymentInfo, pageSize, offset);
			res.put("pageInfo", pageInfo);
			res.put("status", 0);
			res.put("msg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("msg", e.getMessage());
		}
		
		return res;*/
	}

	@ApiOperation(value = "导入")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "RepaymentInfo", value = "明细")
	@RequestMapping(value = "/export", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Object export(String name, String idNo, String mobile, String bankName, String bankCardNo,
						 String amount, Integer period, String beginDate, String deadlineDate, Integer version) {
		Map<String, Object> res = new HashMap<>();

		service.export(null, null,0, name, idNo, mobile, bankName, bankCardNo, amount, "0.00", period, beginDate, deadlineDate, version);

		res.put("status", 0);
		res.put("msg", "");
		return res;
	}

	/**
	 * 获取还款总表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam(required = false) String custName, @RequestParam(required = false) String custMobile, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) String curStatus) {

		List<RepaymentInfo> res=this.service.listByCondition(custName,custMobile,beginTime,endTime,curStatus);
		String resString="";
		//log.info("还款总表查询返回值为" + JSON.toJSONString(res));
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			resString= CommonUtil.obj2json(res);
			list= CommonUtil.json2Object(resString, List.class);
		}catch (Exception e){
			this.log.error(e.getMessage(),e);
		}
		return super.warpObject(new CostOrderWarpper(list));
	}

	/**
	 * 跳转到还款总表记录
	 */
	@RequestMapping("/dayRecePayIndex")
	public String dayRecePayIndex() {
		return "/paybackMgr/dayRecePay/" + "dayRecePay.html";
	}
	/**
	 * 跳转到还款总表记录
	 */
	@RequestMapping("/completeIndex")
	public String completeindex() {
		return PREFIX + "completerepayment.html";
	}

	/**
	 * 跳转到还款总表记录
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "repaymentInfo.html";
	}


	@RequestMapping("calcPMTIndex")
	public String calcPMTIndex() {
		return "/tools/repaymentCalc.html";
	}

	@RequestMapping(value = "calcPMT", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Object calcPMT(String amount, Integer period, String beginDate, Integer isPerCharge){

		if(StringUtils.isBlank(amount) || period == null || StringUtils.isBlank(beginDate) || isPerCharge == null){
			return ResultVO.build(ErrorCode.PARAM_EMPTY);
		}
		RepaymentCalculatorVO vo = RepaymentInfoServiceImpl.calculatorRepayment(amount, period, beginDate, 2, isPerCharge);

		return ResultVO.build(ErrorCode.SUCCESS, vo);
	}

	@RequestMapping(value = "exportPMT", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Object exportPMT(String amount, Integer period, String beginDate, Integer isPerCharge, HttpServletResponse response){

		if(StringUtils.isBlank(amount) || period == null || StringUtils.isBlank(beginDate)){
			return ResultVO.build(ErrorCode.PARAM_EMPTY);
		}

		byte[] buffer = new byte[1024];
		FileInputStream fis = null;
		BufferedInputStream bis = null;

		File downFile = new File(exportFilePath + "plan.xlsx");
		String downFileName = "plan.xlsx";


		try{
			RepaymentCalculatorVO vo = RepaymentInfoServiceImpl.calculatorRepayment(amount, period, beginDate, 2, isPerCharge);

			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("还款计划表");

			int i = 0;
			XSSFRow row;
			XSSFCell cell;
			XSSFCellStyle cellStyle;

			String [] titles = {"编号", "还款日", "期初金额", "月还本金", "月还利息", "服务费", "流量费", "月还款合计"};
			row = sheet.createRow(i++);

			for(int k=0;k<titles.length;k++){
				cellStyle = workbook.createCellStyle();
				cell = row.createCell(k);
				cell.setCellValue(titles[k]);
				if(k==0){
					sheet.setColumnWidth(k, 1200);
					cellStyle.setBorderLeft(BorderStyle.THIN);
				}else{
					sheet.setColumnWidth(k, 2800);
					if(k == titles.length-1){
						cellStyle.setBorderRight(BorderStyle.THIN);
					}
				}
				cellStyle.setBorderTop(BorderStyle.THIN);
				cellStyle.setBorderBottom(BorderStyle.THIN);
				cellStyle.setAlignment(HorizontalAlignment.CENTER);
				cell.setCellStyle(cellStyle);

			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			for(int j=0;j<period+1;j++){

				row = sheet.createRow(j+i);
				int k = 0;
				cell = row.createCell(k++);
				cellStyle = workbook.createCellStyle();
				cellStyle.setAlignment(HorizontalAlignment.RIGHT);
				cellStyle.setBorderLeft(BorderStyle.THIN);
				if(j == period){
					cell.setCellValue("合计");
					cellStyle.setBorderBottom(BorderStyle.THIN);
				}else{
					cell.setCellValue(j+1);
				}
				cell.setCellStyle(cellStyle);

				cell = row.createCell(k++);
				cellStyle = workbook.createCellStyle();
				cellStyle.setAlignment(HorizontalAlignment.RIGHT);
				if(j == period){
					cell.setCellValue("");
					cellStyle.setBorderBottom(BorderStyle.THIN);
				}else{
					cell.setCellValue(sdf.format(vo.getPlans().get(j).getPeriodEndTime()));
				}
				cell.setCellStyle(cellStyle);

				cell = row.createCell(k++);
				cellStyle = workbook.createCellStyle();
				cellStyle.setAlignment(HorizontalAlignment.RIGHT);
				cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
				if(j == period){
					cell.setCellValue("");
					cellStyle.setBorderBottom(BorderStyle.THIN);
				}else{
					cell.setCellValue(Double.parseDouble(vo.getPlans().get(j).getBeginAmount().toString()));
				}
				cell.setCellStyle(cellStyle);

				cell = row.createCell(k++);
				cellStyle = workbook.createCellStyle();
				cellStyle.setAlignment(HorizontalAlignment.RIGHT);
				cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
				if(j == period){
					cell.setCellValue(Double.parseDouble(vo.getPredictCapital().toString()));
					cellStyle.setBorderBottom(BorderStyle.THIN);
				}else{
					cell.setCellValue(Double.parseDouble(vo.getPlans().get(j).getPredictCapital().toString()));
				}
				cell.setCellStyle(cellStyle);

				cell = row.createCell(k++);
				cellStyle = workbook.createCellStyle();
				cellStyle.setAlignment(HorizontalAlignment.RIGHT);
				cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
				if(j == period){
					cell.setCellValue(Double.parseDouble(vo.getPredictInterest().toString()));
					cellStyle.setBorderBottom(BorderStyle.THIN);
				}else{
					cell.setCellValue(Double.parseDouble(vo.getPlans().get(j).getPredictInterest().toString()));
				}
				cell.setCellStyle(cellStyle);

				cell = row.createCell(k++);
				cellStyle = workbook.createCellStyle();
				cellStyle.setAlignment(HorizontalAlignment.RIGHT);
				cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
				if(j == period){
					cell.setCellValue(Double.parseDouble(vo.getPredictCharge().toString()));
					cellStyle.setBorderBottom(BorderStyle.THIN);
				}else{
					cell.setCellValue(Double.parseDouble(vo.getPlans().get(j).getPredictCharge().toString()));
				}
				cell.setCellStyle(cellStyle);

				cell = row.createCell(k++);
				cellStyle = workbook.createCellStyle();
				cellStyle.setAlignment(HorizontalAlignment.RIGHT);
				cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
				if(j == period){
					cell.setCellValue(Double.parseDouble(vo.getPredictServiceCharge().toString()));
					cellStyle.setBorderBottom(BorderStyle.THIN);
				}else{
					cell.setCellValue(Double.parseDouble(vo.getPlans().get(j).getPredictServiceCharge().toString()));
				}
				cell.setCellStyle(cellStyle);

				cell = row.createCell(k++);
				cellStyle = workbook.createCellStyle();
				cellStyle.setAlignment(HorizontalAlignment.RIGHT);
				cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
				cellStyle.setBorderRight(BorderStyle.THIN);
				if(j == period){
					cell.setCellValue(Double.parseDouble(vo.getPredictAmount().toString()));
					cellStyle.setBorderBottom(BorderStyle.THIN);
				}else{
					cell.setCellValue(Double.parseDouble(vo.getPlans().get(j).getPredictTotalAmount().toString()));
				}
				cell.setCellStyle(cellStyle);
			}

			OutputStream os = response.getOutputStream();
			response.reset();
			response.setContentType("application/force-download");// 设置强制下载不打开
			response.addHeader("Content-Disposition", "attachment;fileName=" + downFileName);// 设置文件名
			workbook.write(os);
			workbook.close();
			os.close();
		}catch (Exception e){
			log.error("导出还款计划表出错 e={}", e);
			return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
		}
		return ResultVO.build(ErrorCode.SUCCESS);
	}

	/**
	 * 手工扣款
	 * @param repaymentId
	 * @param period
	 * @param payType	1--手动代扣 2--对公转账
	 * @return
	 */
	@RequestMapping(value = "/manualDeductMoney", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Object padPlan(Long repaymentId, Integer period, Integer payType,
						  @RequestParam String uuid,
						  HttpSession session) {
		Map<String, Object> res = new HashMap<>();
		try {
			RepeatRefuseUtil.repeatRefuse(uuid, session);
			ResultVO<Object> vo = service.manualDeductMoney(repaymentId, period, payType);

			res.put("status", vo.getStatus());
			res.put("msg", vo.getMsg());
			return res;
		}catch(Exception e){
			log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("msg", e.toString());
		}
		return res;
	}

	/**
	 * 根据给定的期数获取其当期数据
	 * @param repaymentId
	 * @param period
	 * @return
	 */
	@RequestMapping(value = "/getSpecifyPeriodPlanMoney", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Object getSpecifyPeriodPlanMoney(Long repaymentId, Integer period) {
		Map<String, Object> res = new HashMap<>();

		ResultVO<RepaymentPlanInfo> vo = service.getSpecifyPeriodPlanMoney(repaymentId, period);

		return vo;
	}


	/**
	 * 跳转到还款总表记录
	 */
	@RequestMapping("OnceEarlyListPage")
	public String OnceEarlyListPage() {
		return "/paybackMgr/onceEarlyRepay/onceEarlyApply.html";
	}

	@RequestMapping(value = "/OnceEarlyList", method = RequestMethod.POST)
	@ResponseBody
	public Object OnceEarlyList(@RequestParam(required = false) String custName, @RequestParam(required = false) String custMobile, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime){

		List<RepaymentInfo> res = service.listByCondition(custName,custMobile,beginTime,endTime,null);
		String resString="";
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			resString= CommonUtil.obj2json(res);
			list= CommonUtil.json2Object(resString, List.class);
		}catch (Exception e){
			this.log.error(e.getMessage(),e);
		}
		return super.warpObject(new PayOrderWarpper(list));
	}

	/**
	 * 获取一次性提前还款数据信息
	 * @param repaymentId	还款id
	 * @param appointDate	约定还款日期
	 * @return
	 */
	@RequestMapping(value = "/getOnceRepaymentInfo", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Object getSpecifyPeriodPlanMoney(Long applyId, String appointDate){
		Map<String, Object> res = new HashMap<>();

		RepaymentInfo repaymentInfo =service.selectByApplyId(applyId);
		
		ResultVO<OnceRepaymentInfoVO> vo = service.getOnceRepaymentInfo(repaymentInfo.getId(), appointDate);

		return vo;
	}


	@RequestMapping(value = "testExportLeaseMoney", method = RequestMethod.GET)
	public void testExportLeaseMoney(){
		service.exportLeaseMoney();
	}

	@RequestMapping(value = "testPMT2", method = RequestMethod.GET)
	public void testPMT2(String name, String amount, Integer period, String beginDate, Integer version, Integer isPerCharge) throws IOException {
		//RepaymentCalculatorVO vo = RepaymentInfoServiceImpl.calculatorRepaymentBefore(amount, period, beginDate, version);
		RepaymentCalculatorVO vo = RepaymentInfoServiceImpl.calculatorRepayment(amount, period, beginDate, 2, isPerCharge);
		String path = "/Users/admin/work/temp/";
		String fileName = path + name + ".csv";
		File file = new File(fileName);

		FileOutputStream fos  = null;
		PrintWriter pw = null;
		try {
			fos = new FileOutputStream(file);
			pw = new PrintWriter(fos);

			StringBuffer buffer = new StringBuffer();
			buffer.append("period,begin,monthAmount,capital,interest,serviceFee,ChargeFee,End,Total,LeaseBegin,LeaseCapital,LeaseInterest,LeaseEnd,Lease,Service").
					append(System.getProperty("line.separator"));
			for(int i = 0; i < period; i++){
				SinglePeriodRepaymentPlanVO plan = vo.getPlans().get(i);
				buffer.append(i+1).append(",").
						append(plan.getBeginAmount()).append(",").
						append(plan.getPayAmount()).append(",").
						append(plan.getPredictCapital()).append(",").
						append(plan.getPredictInterest()).append(",").
						append(plan.getPredictServiceCharge()).append(",").
						append(plan.getPredictCharge()).append(",").
						append(plan.getEndAmount()).append(",").
						append(plan.getPredictTotalAmount()).append(",").
						append(plan.getBeginAmountLease()).append(",").
						append(plan.getCapitalLease()).append(",").
						append(plan.getInterestLease()).append(",").
						append(plan.getEndAmountLease()).append(",").
						append(plan.getLeaseFee()).append(",").
						append(plan.getPlatformFee()).append(",").
						append(System.getProperty("line.separator"));
			}

			buffer.append("0,0,0,").append(vo.getPredictCapital()).append(",").
					append(vo.getPredictInterest()).append(",").
					append(vo.getPredictServiceCharge()).append(",").
					append(vo.getPredictCharge()).append(",").
					append("0,").
					append(vo.getPredictAmount()).append(",").
					append("0,").
					append(vo.getLeaseCapitalTotal()).append(",").
					append(vo.getLeaseInterestTotal()).append(",").
					append("0,").
					append(vo.getMonthLeaseTotal()).append(",").
					append(vo.getMonthServiceTotal()).append(",").
					append(System.getProperty("line.separator"));

			pw.write(buffer.toString().toCharArray());
			pw.flush();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			//不要忘记关闭
			if (pw != null) {
				pw.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
	}

	/**
	 * 自动扣款
	 * @throws IOException
	 */
	@RequestMapping(value = "testRepay", method = RequestMethod.GET)
	public void testRepay(String name, String pwd) throws IOException {
		if(name == null || pwd == null){
			return;
		}
		if(name.equals("tsingnuo") && pwd.equals("@tsingnuo2017")){
			repaymentBusiness.autoDeductMoneyForRepayment();
		}
	}

	@RequestMapping(value = "testStatis", method = RequestMethod.GET)
	public void testStatis() throws IOException {
		repaymentBusiness.statisFinance();
	}

	@RequestMapping(value = "testRefreshPlanStatus", method = RequestMethod.GET)
	public void testRefreshPlanStatus() throws IOException {
		repaymentBusiness.refreshRepaymentPlanStatus();
	}

	@RequestMapping(value = "testCalcOverdue", method = RequestMethod.GET)
	public void testCalcOverdue() throws IOException {
		repaymentBusiness.calcOverdue();
	}

	@RequestMapping(value = "testCheckFinance", method = RequestMethod.GET)
	public void testCheckFinance() throws IOException {
		repaymentBusiness.checkFinance();
	}
	@RequestMapping(value = "testRemainCustomerRepayment", method = RequestMethod.GET)
	public void testRemainCustomerRepayment() throws IOException {
		repaymentBusiness.autoRemainCustomerForRepayment();
	}

	@RequestMapping(value = "exportContractNo", method = RequestMethod.POST)
	@ResponseBody
	public Object testExportContractNo(@RequestParam(required = false) Long repaymentId, @RequestParam(required = false) String contractNo) throws IOException {
		return service.testExportContractNo(repaymentId, contractNo);
	}


	/**
	 * 跳转到收款查询页面
	 */
	@RequestMapping("/queryRecePayIndex")
	public String queryRecePayIndex() {
		return "/paybackMgr/dayRecePay/" + "queryRecePay.html";
	}

}

