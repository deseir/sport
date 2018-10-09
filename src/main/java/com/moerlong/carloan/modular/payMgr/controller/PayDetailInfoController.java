package com.moerlong.carloan.modular.payMgr.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.core.support.HttpKit;
import com.moerlong.carloan.modular.payMgr.entity.enums.PayType;
import com.moerlong.carloan.modular.payMgr.entity.vo.PayDetailInfoVO;
import com.moerlong.carloan.modular.payMgr.warpper.PayOrderWarpper;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentPlanInfo;
import com.moerlong.carloan.modular.paybackMgr.entity.enums.PayStatus;
import com.moerlong.carloan.modular.paybackMgr.warpper.CostOrderWarpper;
import com.moerlong.carloan.util.CommonUtil;
import org.apache.commons.lang.StringUtils;
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
import com.moerlong.carloan.modular.payMgr.entity.PayDetailInfo;
import com.moerlong.carloan.modular.payMgr.service.PayDetailInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Api(tags = { "controller接口类" })
public class PayDetailInfoController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(PayDetailInfoController.class);

	private String PREFIX = "/payMgr/payInfo/";

	@Autowired
	PayDetailInfoService service;

	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "PayDetailInfo", value = "明细")
	@RequestMapping(value = "/payDetailInfo/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(@RequestBody PayDetailInfo entity) {
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
	@RequestMapping(value = "/payDetailInfo/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/payDetailInfo/findById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/payDetailInfo/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestBody Map<String,Object> queryMap) {
		this.log.info("/pageQuery param:{}",queryMap);
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


	@ApiOperation(value = "根据放款总表ID查询放款详情")
	@ApiImplicitParam(paramType = "form", name = "id", required = true, dataType = "Long", value = "id")
	@RequestMapping(value = "/payDetailInfo/findListByPayId", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object findListByPayId(Long payId) {

		List<PayDetailInfo> res=this.service.listByPayId(payId);
		String resString="";
		//log.info("放款详情查询返回值为" + JSON.toJSONString(res));
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
	 * 获取代扣订单详细列表
	 */
	@RequestMapping(value = "/payDetailInfo/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam(required = false) String payCode, @RequestParam(required = false) String serialNo, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime,
					   @RequestParam(required = false) String payState, @RequestParam(required = false) Integer payType) {

		HttpServletRequest request = HttpKit.getRequest();
		int limit = Integer.valueOf(request.getParameter("limit"));
		int offset = Integer.valueOf(request.getParameter("offset"));
		Map<String, Object> res = new HashMap<>();
		Integer pageNum = (offset / limit + 1); //页数从1开始
		Integer pageSize = limit; //页面大小

		try {

			PageInfo<PayDetailInfoVO> pageInfo = this.service.selectPage(pageSize, pageNum,payCode,serialNo,beginTime,endTime,payState, payType);

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
	@RequestMapping("/payDetailInfo")
	public String index() {
		return PREFIX + "payDetailInfo.html";
	}


	private void fillExportRepayExecl(XSSFWorkbook workbook, XSSFSheet sheet, int nrow, PayDetailInfoVO payInfo){
		XSSFRow row;
		XSSFCell cell;
		XSSFCellStyle cellStyle;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		row = sheet.createRow(nrow);
		int k = 0;
		//ID
		cell = row.createCell(k++);
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellValue(payInfo.getId());
		cell.setCellStyle(cellStyle);

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

		//银行卡号
		cell = row.createCell(k++);
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellValue(payInfo.getBankCardNo());
		cell.setCellStyle(cellStyle);

		//银行名称
		cell = row.createCell(k++);
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellValue(payInfo.getBankName());
		cell.setCellStyle(cellStyle);

		//金额
		cell = row.createCell(k++);
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		cellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
		cell.setCellValue(Double.parseDouble(payInfo.getCurPayAmount().toString()));
		cell.setCellStyle(cellStyle);

		//收款类型
		cell = row.createCell(k++);
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellValue(PayType.getEnumsByValue(payInfo.getPayType()).getDesc());
		cell.setCellStyle(cellStyle);

		//时间
		cell = row.createCell(k++);
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellValue(sdf.format(payInfo.getUpdateTime()));
		cell.setCellStyle(cellStyle);
	}

	/**
	 * 导出付款记录信息
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping(value = "/payDetailInfo/exportPayList", method = RequestMethod.GET)
	@ResponseBody
	public Object exportPayList(String beginTime, String endTime, HttpServletResponse response){
		if(StringUtils.isBlank(beginTime) || StringUtils.isBlank(endTime)){
			return ResultVO.build(ErrorCode.PARAM_EMPTY);
		}

		String downFileName = String.format("payRecord_%s-%s.xlsx", beginTime, endTime);

		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("付款记录表");

			int i = 0;
			XSSFRow row;
			XSSFCell cell;
			XSSFCellStyle cellStyle;


			String [] titles = {"编号", "流水号", "姓名", "银行卡号", "银行名称", "金额", "收款类型", "时间"};
			row = sheet.createRow(i++);

			for (int k = 0; k < titles.length; k++) {
				cellStyle = workbook.createCellStyle();
				cell = row.createCell(k);
				cell.setCellValue(titles[k]);
				if(k==0){
					sheet.setColumnWidth(k, 2500);
				}else if(k == 1 || k == 3) {
					sheet.setColumnWidth(k, 5500);
				}else{
					sheet.setColumnWidth(k, 3500);
				}
				cellStyle.setAlignment(HorizontalAlignment.CENTER);
				cell.setCellStyle(cellStyle);
			}
			int count = 0;

			List<PayDetailInfoVO> list = service.listByCondition(null, null, beginTime, endTime, null, null);

			for (PayDetailInfoVO payInfo : list) {

				if (payInfo.getPayStatus() != PayStatus.PAY_SUCCESS.getValue()) {
					continue;
				}

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
			log.error("导出付款记录出错 e={}", e);
			return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
		}
		return ResultVO.build(ErrorCode.SUCCESS);
	}

}

