package com.moerlong.carloan.modular.paybackMgr.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.paybackMgr.warpper.CostOrderWarpper;
import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.core.support.HttpKit;
import com.moerlong.carloan.modular.paybackMgr.entity.CostOrderApply;
import com.moerlong.carloan.modular.paybackMgr.entity.enums.PayStatus;
import com.moerlong.carloan.modular.paybackMgr.entity.enums.RepaymentType;
import com.moerlong.carloan.modular.paybackMgr.entity.vo.RepaymentPayInfoVO;
import com.moerlong.carloan.modular.paybackMgr.entity.vo.RepaymentPayReportInfoVO;
import com.moerlong.carloan.modular.paybackMgr.entity.vo.RepaymentPlanInfoVO;
import com.moerlong.carloan.util.CommonUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentPayInfo;
import com.moerlong.carloan.modular.paybackMgr.service.RepaymentPayInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Api(tags = { "controller接口类" })
public class RepaymentPayInfoController extends BaseController {


	private final Logger log = LoggerFactory.getLogger(RepaymentPayInfoController.class);

	private String PREFIX = "/paybackMgr/repaymentPayInfo/";

	@Autowired
	RepaymentPayInfoService service;

	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "RepaymentPayInfo", value = "明细")
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(@RequestBody RepaymentPayInfo entity) {
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
	public Object pageQuery(@RequestBody Map<String,Object> queryMap) {
		this.log.info("pageQuery param:{}",queryMap);
		Map<String, Object> res = new HashMap<>();
		Integer pageNum = 1; //页数从1开始
		Integer pageSize = 10; //页面大小
		String orderCondition = " order by id desc ";	
			
		try {
			if(queryMap!=null) {
				if(queryMap.get("pageNum")!=null) {
					pageNum = (Integer)queryMap.get("pageNum");
				}
				if(queryMap.get("pageSize")!=null) {
					pageSize = (Integer)queryMap.get("pageSize");
				}
				if(queryMap.get("orderCondition")!=null) {
					orderCondition = (String)queryMap.get("orderCondition");
				}
			}
			
			Object pageInfo = this.service.selectPage(pageSize, pageNum, orderCondition);
			
			res.put("pageInfo", pageInfo);
			res.put("status", 0);
			res.put("msg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("msg", e.getMessage());
		}
		
		return res;
	}

	/**
	 * 获取代扣订单详细列表
	 */
	@RequestMapping(value = "/repaymentPayInfo/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam(required = false) String payCode,@RequestParam(required = false) String serialNo, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime,
					   @RequestParam(required = false) String payState, @RequestParam(required = false) Integer payType) {

		HttpServletRequest request = HttpKit.getRequest();
		int limit = Integer.valueOf(request.getParameter("limit"));
		int offset = Integer.valueOf(request.getParameter("offset"));
		Map<String, Object> res = new HashMap<>();
		Integer pageNum = (offset / limit + 1); //页数从1开始
		Integer pageSize = limit; //页面大小

		try {

			PageInfo<RepaymentPayInfoVO> pageInfo = this.service.selectPage(pageSize, pageNum,payCode,serialNo,beginTime,endTime,payState, payType);

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


	@RequestMapping(value = "/repaymentPayInfo/listMrl", method = RequestMethod.POST)
	@ResponseBody
	public Object listMrl(@RequestParam(required = false) String payCode,@RequestParam(required = false) String serialNo, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime,
					   @RequestParam(required = false) String payState, @RequestParam(required = false) Integer payType) {

		HttpServletRequest request = HttpKit.getRequest();
		int limit = Integer.valueOf(request.getParameter("limit"));
		int offset = Integer.valueOf(request.getParameter("offset"));
		Map<String, Object> res = new HashMap<>();
		Integer pageNum = (offset / limit + 1); //页数从1开始
		Integer pageSize = limit; //页面大小

		try {

			PageInfo<RepaymentPayInfoVO> pageInfo = this.service.selectPageMrl(pageSize, pageNum,payCode,serialNo,beginTime,endTime,payState, payType);

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
	 * 跳转到代付订单首页
	 */
	@RequestMapping("/repaymentPayInfo")
	public String index() {
		return PREFIX + "repaymentPayInfo.html";
	}

	/**
	 * 跳转到服务费查询页面
	 */
	@RequestMapping("/repaymentPayInfo/mrlIndex")
	public String mrlIndex() {
		return PREFIX + "repaymentPayInfoMrl.html";
	}



	private void fillExportRepayExecl(XSSFWorkbook workbook, XSSFSheet sheet, int nrow, RepaymentPayReportInfoVO payInfo){
		XSSFRow row;
		XSSFCell cell;
		XSSFCellStyle cellStyle;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		row = sheet.createRow(nrow);
		int k = 0;
		//收款记录ID
		cell = row.createCell(k++);
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellValue(payInfo.getPayId());
		cell.setCellStyle(cellStyle);

		//还款ID
		cell = row.createCell(k++);
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellValue(payInfo.getRepaymentId());
		cell.setCellStyle(cellStyle);

		if(payInfo.getRepaymentPlanId() != null){
			//还款计划ID
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cell.setCellValue(payInfo.getRepaymentPlanId());
			cell.setCellStyle(cellStyle);
		}else{
			k++;
		}

		//流水号
		cell = row.createCell(k++);
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellValue(payInfo.getSerialNo());
		cell.setCellStyle(cellStyle);

		//姓名
		cell = row.createCell(k++);
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellValue(payInfo.getCustName());
		cell.setCellStyle(cellStyle);

		//手机号
		cell = row.createCell(k++);
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellValue(payInfo.getCustMobile());
		cell.setCellStyle(cellStyle);

		//身份证号
		cell = row.createCell(k++);
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellValue(payInfo.getCustIdNo());
		cell.setCellStyle(cellStyle);

		//银行卡号
		cell = row.createCell(k++);
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellValue(payInfo.getPayCard());
		cell.setCellStyle(cellStyle);

		//银行名称
		cell = row.createCell(k++);
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellValue(payInfo.getPayBank());
		cell.setCellStyle(cellStyle);

		//收款金额
		cell = row.createCell(k++);
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
		cell.setCellValue(Double.parseDouble(payInfo.getAmount().toString()));
		cell.setCellStyle(cellStyle);

		//收款类型
		cell = row.createCell(k++);
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellValue(RepaymentType.getEnumsByValue(payInfo.getPayType()).getDesc());
		cell.setCellStyle(cellStyle);

		//支付时间
		cell = row.createCell(k++);
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellValue(sdf.format(payInfo.getPayTime()));
		cell.setCellStyle(cellStyle);

		BigDecimal leaseTotal = new BigDecimal(0);			//租赁总计
		BigDecimal serviceTotal = new BigDecimal(0);		//天津总计
		BigDecimal thirdPartFee = new BigDecimal(0);		//三方手续费

		if(payInfo.getPayType() == RepaymentType.PAY_TYPE_AUTO_PLAN.getValue() ||
				payInfo.getPayType() == RepaymentType.PAY_TYPE_MANUAL_DEDUCT_PLAN.getValue() ||
				payInfo.getPayType() == RepaymentType.PAY_TYPE_MANUAL_PUBLIC_PLAN.getValue())
		{
			//期数
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cell.setCellValue(payInfo.getPeriodNum());
			cell.setCellStyle(cellStyle);

			//当期开始时间
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cell.setCellValue(sdf.format(payInfo.getPeriodBeginTime()));
			cell.setCellStyle(cellStyle);

			//当期结束时间
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cell.setCellValue(sdf.format(payInfo.getPeriodEndTime()));
			cell.setCellStyle(cellStyle);

			//期初余额
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
			cell.setCellValue(Double.parseDouble(payInfo.getBeginAmount().toString()));
			cell.setCellStyle(cellStyle);

			//期末余额
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
			cell.setCellValue(Double.parseDouble(payInfo.getEndAmount().toString()));
			cell.setCellStyle(cellStyle);

			//月还款
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
			cell.setCellValue(Double.parseDouble(payInfo.getPayAmount().toString()));
			cell.setCellStyle(cellStyle);

			//当期实还总额
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
			cell.setCellValue(Double.parseDouble(payInfo.getActualAmount().toString()));
			cell.setCellStyle(cellStyle);

			//当期实还本金
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
			cell.setCellValue(Double.parseDouble(payInfo.getActualCapital().toString()));
			cell.setCellStyle(cellStyle);

			//当期实还利息
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
			cell.setCellValue(Double.parseDouble(payInfo.getActualInterest().toString()));
			cell.setCellStyle(cellStyle);

			//当期实还服务费
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
			cell.setCellValue(Double.parseDouble(payInfo.getActualServiceCharge().toString()));
			cell.setCellStyle(cellStyle);
			serviceTotal = serviceTotal.add(payInfo.getActualServiceCharge());

			//当期实还手续费
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
			cell.setCellValue(Double.parseDouble(payInfo.getActualCharge().toString()));
			cell.setCellStyle(cellStyle);
			serviceTotal = serviceTotal.add(payInfo.getActualCharge());

			//当期实还罚息
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
			cell.setCellValue(Double.parseDouble(payInfo.getActualPenalty().toString()));
			cell.setCellStyle(cellStyle);
			serviceTotal = serviceTotal.add(payInfo.getActualPenalty());

			//租赁期初余额
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
			cell.setCellValue(Double.parseDouble(payInfo.getBeginAmountLease().toString()));
			cell.setCellStyle(cellStyle);

			//租赁期末余额
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
			cell.setCellValue(Double.parseDouble(payInfo.getEndAmountLease().toString()));
			cell.setCellStyle(cellStyle);

			//租赁本金
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
			cell.setCellValue(Double.parseDouble(payInfo.getCapitalLease().toString()));
			cell.setCellStyle(cellStyle);
			leaseTotal = leaseTotal.add(payInfo.getCapitalLease());

			//租赁利息
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
			cell.setCellValue(Double.parseDouble(payInfo.getInterestLease().toString()));
			cell.setCellStyle(cellStyle);
			leaseTotal = leaseTotal.add(payInfo.getInterestLease());

			//服务合同
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
			cell.setCellValue(Double.parseDouble(payInfo.getServiceTotal().toString()));
			cell.setCellStyle(cellStyle);
			serviceTotal = serviceTotal.add(payInfo.getServiceTotal());

			k+=4;
		}
		else if(payInfo.getPayType() == RepaymentType.PAY_TYPE_ONCE_CHARGE.getValue() ||
				payInfo.getPayType() == RepaymentType.PAY_TYPE_ONCE_CHARGE_MAN.getValue() ||
				payInfo.getPayType() == RepaymentType.PAY_TYPE_ONCE_CHARGE_PUBLIC.getValue()) {
			//一次性手续费

			k+=10;
			//当期实还手续费
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
			cell.setCellValue(Double.parseDouble(payInfo.getAmount().toString()));
			cell.setCellStyle(cellStyle);
			serviceTotal = serviceTotal.add(payInfo.getAmount());
			k+=10;

		}else if(payInfo.getPayType() == RepaymentType.PAY_TYPE_PRE_FEE.getValue() ||
				payInfo.getPayType() == RepaymentType.PAY_TYPE_PRE_FEE_MANUAL.getValue() ||
				payInfo.getPayType() == RepaymentType.PAY_TYPE_PRE_FEE_PUBLIC.getValue() ){

			//前期费用
			k+=17;

			//天津押金
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
			cell.setCellValue(Double.parseDouble("500"));
			cell.setCellStyle(cellStyle);

			//天津前期费用
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
			cell.setCellValue(Double.parseDouble("1000"));
			cell.setCellStyle(cellStyle);

			//天津前期费用总计
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
			cell.setCellValue(Double.parseDouble("1500"));
			cell.setCellStyle(cellStyle);
			serviceTotal = serviceTotal.add(payInfo.getAmount());
			k+=1;
		} else if(payInfo.getPayType() == RepaymentType.PAY_TYPE_ONCE_EARLY_BREACH.getValue()){
			//提前还款违约金
			k+=20;
			//天津违约金
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
			cell.setCellValue(Double.parseDouble(payInfo.getAmount().toString()));
			cell.setCellStyle(cellStyle);
			serviceTotal = serviceTotal.add(payInfo.getAmount());
		} else if(payInfo.getPayType() == RepaymentType.PAY_TYPE_ONCE_EARLY_CAPITAL.getValue()){
			//提前还款剩余本金--租赁
			k+=14;
			//租赁本金
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
			cell.setCellValue(Double.parseDouble(payInfo.getAmount().toString()));
			cell.setCellStyle(cellStyle);
			leaseTotal = leaseTotal.add(payInfo.getAmount());
			k+=6;
		} else if(payInfo.getPayType() == RepaymentType.PAY_TYPE_ONCE_EARLY_INTEREST.getValue()){
			//提前还款剩余本金--天津氢诺
			k+=16;
			//服务合同
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
			cell.setCellValue(Double.parseDouble(payInfo.getAmount().toString()));
			cell.setCellStyle(cellStyle);
			serviceTotal = serviceTotal.add(payInfo.getAmount());
			k+=4;
		} else if(payInfo.getPayType() == RepaymentType.PAY_TYPE_ONCE_EARLY_CHARGE.getValue()){
			//提前还款剩余手续费
			k+=10;
			//当期实还手续费
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
			cell.setCellValue(Double.parseDouble(payInfo.getAmount().toString()));
			cell.setCellStyle(cellStyle);
			serviceTotal = serviceTotal.add(payInfo.getAmount());
			k+=10;
		}
		//租赁总计
		cell = row.createCell(k++);
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
		cell.setCellValue(Double.parseDouble(leaseTotal.toString()));
		cell.setCellStyle(cellStyle);

		//服务总计
		cell = row.createCell(k++);
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
		cell.setCellValue(Double.parseDouble(serviceTotal.toString()));
		cell.setCellStyle(cellStyle);

		if(payInfo.getPayType() != RepaymentType.PAY_TYPE_MANUAL_PUBLIC_PLAN.getValue() &&
				payInfo.getPayType() != RepaymentType.PAY_TYPE_PRE_FEE_PUBLIC.getValue() &&
				payInfo.getPayType() != RepaymentType.PAY_TYPE_ONCE_EARLY_CAPITAL.getValue() &&
				payInfo.getPayType() != RepaymentType.PAY_TYPE_ONCE_EARLY_CHARGE.getValue() &&
				payInfo.getPayType() != RepaymentType.PAY_TYPE_ONCE_EARLY_BREACH.getValue() &&
				payInfo.getPayType() != RepaymentType.PAY_TYPE_ONCE_EARLY_INTEREST.getValue() &&
				payInfo.getPayType() != RepaymentType.PAY_TYPE_ONCE_CHARGE_PUBLIC.getValue()){
			//三方手续费率
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cell.setCellValue("0.0015");
			cell.setCellStyle(cellStyle);

			thirdPartFee = payInfo.getAmount().multiply(new BigDecimal("0.0015")).setScale(2, BigDecimal.ROUND_HALF_UP);

			//三方手续费
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
			cell.setCellValue(Double.parseDouble(thirdPartFee.toString()));
			cell.setCellStyle(cellStyle);

			//实际到账金额
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
			cell.setCellValue(Double.parseDouble(payInfo.getAmount().subtract(thirdPartFee).toString()));
			cell.setCellStyle(cellStyle);
		}else{
			//三方手续费率
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cell.setCellValue("0");
			cell.setCellStyle(cellStyle);

			//三方手续费
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
			cell.setCellValue(Double.parseDouble("0.00"));
			cell.setCellStyle(cellStyle);

			//实际到账金额
			cell = row.createCell(k++);
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
			cell.setCellValue(Double.parseDouble(payInfo.getAmount().toString()));
			cell.setCellStyle(cellStyle);
		}

	}

	/**
	 * 导出还款记录信息
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping(value = "/repaymentPayInfo/exportRepayList", method = RequestMethod.GET)
	@ResponseBody
	public Object exportRepayList(String beginTime, String endTime, HttpServletResponse response){

		if(StringUtils.isBlank(beginTime) || StringUtils.isBlank(endTime)){
			return ResultVO.build(ErrorCode.PARAM_EMPTY);
		}

		String downFileName = String.format("repaymentPay_%s-%s.xlsx", beginTime, endTime);

		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("收款记录表");

			int i = 0;
			XSSFRow row;
			XSSFCell cell;
			XSSFCellStyle cellStyle;


			String [] titles = {"收款记录ID", "还款ID", "还款计划ID", "流水号", "姓名", "手机号", "身份证号", "银行卡号", "银行名称", "收款金额",
						"收款类型", "支付时间", "期数", "当期开始时间", "当期结束时间", "期初余额", "期末余额", "月还款", "当期实还总额", "当期实还本金",
						"当期实还利息", "当期实还服务费(天津GPS)", "当期实还手续费(天津手续费)", "当期实还罚息",	 "租赁期初余额", "租赁期末余额", "租赁本金",
						"租赁利息", "服务合同(天津利息)", "天津押金",
						"天津前期费用", "天津前期费用总计", "天津违约金", "租赁总计", "服务总计(天津)", "三方手续费率", "三方手续费", "实际到账金额"};
			row = sheet.createRow(i++);

			for (int k = 0; k < titles.length; k++) {
				cellStyle = workbook.createCellStyle();
				cell = row.createCell(k);
				cell.setCellValue(titles[k]);
				if(k==0){
					sheet.setColumnWidth(k, 3000);
				}else if(k == 3 || k == 6 || k == 7 || k==21 || k==22) {
					sheet.setColumnWidth(k, 5500);
				}else if(k == 12) {
					sheet.setColumnWidth(k, 2500);
				}else{
					sheet.setColumnWidth(k, 3500);
				}
				cellStyle.setAlignment(HorizontalAlignment.CENTER);
				cell.setCellStyle(cellStyle);
			}
			int count = 0;

			List<RepaymentPayReportInfoVO> list = service.listReport(beginTime, endTime);
			List<RepaymentPayReportInfoVO> list2 = new ArrayList<>();

			for (RepaymentPayReportInfoVO payInfo : list) {

				if (payInfo.getPayType() == RepaymentType.PAY_TYPE_RECEPTION_CHARGE_FEE.getValue() ||
						payInfo.getPayType() == RepaymentType.PAY_TYPE_RECEPTION_CHARGE_FEE_MANUAL.getValue() ||
						payInfo.getPayType() == RepaymentType.PAY_TYPE_RECEPTION_CHARGE_FEE_PUBLIC.getValue()) {
					//前台服务费的都不管
					continue;
				} else if (payInfo.getPayType() == RepaymentType.PAY_TYPE_AUTO_PLAN.getValue() ||
						payInfo.getPayType() == RepaymentType.PAY_TYPE_MANUAL_DEDUCT_PLAN.getValue()){
					//还款
					if(payInfo.getPayStatus() != PayStatus.REPAYMENT_SUCCESS.getValue()){
						continue;
					}
				} else if (payInfo.getPayType() == RepaymentType.PAY_TYPE_PRE_FEE.getValue() ||
						payInfo.getPayType() == RepaymentType.PAY_TYPE_PRE_FEE_MANUAL.getValue()) {
					//前期费用
					if (payInfo.getPayStatus() != PayStatus.PAY_SUCCESS.getValue()) {
						continue;
					}
				}else if (payInfo.getPayType() == RepaymentType.PAY_TYPE_ONCE_CHARGE.getValue() ||
						payInfo.getPayType() == RepaymentType.PAY_TYPE_ONCE_CHARGE_MAN.getValue()) {
					//一次性手续费
					if (payInfo.getPayStatus() != PayStatus.PAY_SUCCESS.getValue()) {
						continue;
					}
				}else if (payInfo.getPayType() == RepaymentType.PAY_TYPE_MANUAL_BALANCE.getValue()) {
					//手工冲账
					if (payInfo.getPayStatus() != PayStatus.PAY_SUCCESS.getValue()) {
						continue;
					}
				} else if(payInfo.getPayType() == RepaymentType.PAY_TYPE_MANUAL_PUBLIC_PLAN.getValue() ||
						payInfo.getPayType() == RepaymentType.PAY_TYPE_PRE_FEE_PUBLIC.getValue() ||
						payInfo.getPayType() == RepaymentType.PAY_TYPE_ONCE_CHARGE_PUBLIC.getValue() ||
						payInfo.getPayType() == RepaymentType.PAY_TYPE_ONCE_EARLY_CAPITAL.getValue() ||
						payInfo.getPayType() == RepaymentType.PAY_TYPE_ONCE_EARLY_CHARGE.getValue() ||
						payInfo.getPayType() == RepaymentType.PAY_TYPE_ONCE_EARLY_BREACH.getValue() ||
						payInfo.getPayType() == RepaymentType.PAY_TYPE_ONCE_EARLY_INTEREST.getValue()){
					list2.add(payInfo);
					continue;
				}

				fillExportRepayExecl(workbook, sheet, i++, payInfo);
			}

			//对公转账的
			i+=2;
			for (RepaymentPayReportInfoVO payInfo : list2) {
				fillExportRepayExecl(workbook, sheet, i++, payInfo);
			}


			OutputStream os = response.getOutputStream();
			response.reset();
			response.setContentType("application/force-download");// 设置强制下载不打开
			response.addHeader("Content-Disposition", "attachment;fileName=" + downFileName);// 设置文件名
			workbook.write(os);
			workbook.close();
			os.close();

		}catch (Exception e){
			log.error("导出还款记录出错 e={}", e);
			return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
		}
		return ResultVO.build(ErrorCode.SUCCESS);
	}

}

