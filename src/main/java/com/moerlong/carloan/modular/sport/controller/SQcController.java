package com.moerlong.carloan.modular.sport.controller;

import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.common.persistence.dao.DeptMapper;
import com.moerlong.carloan.common.persistence.model.Dept;
import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.core.shiro.ShiroKit;
import com.moerlong.carloan.core.shiro.ShiroUser;
import com.moerlong.carloan.modular.sport.dao.SQcMapper;
import com.moerlong.carloan.modular.sport.entity.Huizong;
import com.moerlong.carloan.modular.sport.entity.SQc;
import com.moerlong.carloan.modular.sport.service.SQcService;
import com.moerlong.carloan.modular.system.dao.DeptDao;
import com.moerlong.carloan.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Api(tags = { "controller接口类" })
public class SQcController {

	private final Logger log = LoggerFactory.getLogger(SQcController.class);

	@Autowired
	SQcMapper mapper;

	@Autowired
	SQcService service;
	@Autowired
	DeptDao deptDao;
	@Resource
	DeptMapper deptMapper;
	@Value("${file.identity_pic_urls}")
	private String idPicUrls;

	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "TelecomRoamInfo", value = "明细")
	@RequestMapping(value = "/sqc/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(SQc entity) {
		Map<String, Object> res = new HashMap<>();
		try {
			if(entity.getId()!=null && service.selectById(entity.getId())!=null) {
				service.updateWithOutNull(entity);
			}else {
				Map<String,Object> queryMap = new HashMap<>();
				queryMap.put("deptid",entity.getDeptid());
				List<Dept> depts = deptDao.selectPage(queryMap);
				entity.setDeptname(depts.get(0).getSimplename());

				queryMap.put("deptid",entity.getDeptpid());
				List<Dept> depts2 = deptDao.selectPage(queryMap);
				entity.setDeptpname(depts2.get(0).getSimplename());
				service.save(entity);
			}
			res.put("qcId",entity.getId());
			res.put("status", 0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.toString());
		}
		return res;
	}
	
	@ApiOperation(value = "只更新非空字段")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "TelecomRoamInfo", value = "明细")
	@RequestMapping(value = "/sqc/updateWithOutNull", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWithOutNull(SQc entity) {
		Map<String, Object> res = new HashMap<>();
		try {
			service.updateWithOutNull(entity);
			res.put("status", 0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.toString());
		}
		return res;
	}

	@ApiOperation(value = "删除")
	@ApiImplicitParam(paramType = "body", name = "param", required = false, dataType = "Map", value = "参数")
	@RequestMapping(value = "/sqc/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object deleteById(@RequestParam Map<String,Object> param) {
	
		Map<String, Object> res = new HashMap<>();
		try {
			Integer id = Integer.parseInt(param.get("id").toString());
			service.delete(id);
			res.put("status", 0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}
	
	@ApiOperation(value = "逻辑删除")
	@ApiImplicitParam(paramType = "body", name = "param", required = false, dataType = "Map", value = "参数")
	@RequestMapping(value = "/sqc/deleteLogicById", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object deleteLogic(@RequestBody Map<String,Object> param) {
		Map<String, Object> res = new HashMap<>();
		try {
			Integer id = Integer.parseInt(param.get("id").toString());
			service.deleteLogic(id);
			res.put("status", 0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}

	@ApiOperation(value = "根据ID查找")
	@ApiImplicitParam(paramType = "body", name = "param", required = false, dataType = "Map", value = "参数")
	@RequestMapping(value = "/sqc/findById", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object findById(@RequestParam Map<String,Object> param) {
		Map<String, Object> res = new HashMap<>();
		try {
			Integer id = Integer.parseInt(param.get("id").toString());
			res.put("data", service.selectById(id));
			res.put("status", 0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}
	
	@ApiOperation(value = "显示所有")
	@RequestMapping(value = "/sqc/listAll", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object listAll() {
		Map<String, Object> res = new HashMap<>();
		try {
			res.put("data", service.listAll());
			res.put("status", 0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}

	@ApiOperation(value = "手机端分页查询")
	@ApiImplicitParam(paramType = "body", name = "queryMap", required = false, dataType = "Map", value = "查询条件")
	@RequestMapping(value = "/sqc/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestParam Map<String,Object> queryMap) {
		this.log.info("/sqc/pageQuery param:{}",queryMap);
		Map<String, Object> res = new HashMap<>();
		Integer pageNum = 1; //页数从1开始
		Integer pageSize = 10; //页面大小
			
		try {
			if(queryMap!=null) {
				if(queryMap.get("pageNum")!=null&&!"".equals(queryMap.get("pageNum"))) {
					pageNum = Integer.parseInt((String)queryMap.get("pageNum"));
				}
				if(queryMap.get("pageSize")!=null) {
					pageSize = Integer.parseInt((String)queryMap.get("pageSize"));
				}
			}

			Object pageInfo = this.service.selectPage(pageSize, pageNum, queryMap);
			res.put("data",pageInfo);
			res.put("status", 0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}

		return res;
	}


	@ApiOperation(value = "后台分页查询")
	@ApiImplicitParam(paramType = "body", name = "queryMap", required = false, dataType = "Map", value = "查询条件")
	@RequestMapping(value = "/sqc/houtai/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object htPageQuery(@RequestParam Map<String,Object> queryMap) {
		this.log.info("/sqc/houtai/pageQuery param:{}",queryMap);
		Map<String, Object> res = new HashMap<>();
		int offset = Integer.valueOf(queryMap.get("offset").toString());
		int pageSize = Integer.valueOf(queryMap.get("limit").toString());
		Integer pageNum = (offset / pageSize + 1); //页数从1开始

		try {
			if(queryMap!=null) {
				if(queryMap.get("pageNum")!=null&&!"".equals(queryMap.get("pageNum"))) {
					pageNum = Integer.parseInt((String)queryMap.get("pageNum"));
				}
				if(queryMap.get("pageSize")!=null) {
					pageSize = Integer.parseInt((String)queryMap.get("pageSize"));
				}
			}

			PageInfo pageInfo = this.service.selectPage(pageSize, pageNum, queryMap);
			res.put("total",pageInfo.getTotal());
			res.put("rows",pageInfo.getList());
			return CommonUtil.obj2json(res);
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}

		return res;
	}


	/**
	 * 跳转添加器材页面
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/sqc/showAddQc")
	public String showAddCd(@RequestParam String deptId, @RequestParam String deptPid, Model model) {
		SQc sqc = new SQc();
		sqc.setDeptid(deptId);
		model.addAttribute("sqc",sqc);
		model.addAttribute("deptPid",deptPid);
		model.addAttribute("idPicUrls",idPicUrls);
		return "/sport/addqc.html";
	}

	/**
	 * 跳转器材修改页面
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/sqc/showQcDetail")
	public String showCdDetail(@RequestParam Integer qcId,@RequestParam String deptPid, Model model) {
		SQc sqc = this.service.selectById(qcId);
		model.addAttribute("sqc",sqc);
		model.addAttribute("deptPid",deptPid);
		model.addAttribute("idPicUrls",idPicUrls);
		return "/sport/addqc.html";
	}

	/**
	 * 跳转器材列表页面
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/sqc/showQcs")
	public String showQcs(@RequestParam Integer deptPid,@RequestParam Integer deptId, Model model) {
		model.addAttribute("idPicUrls",idPicUrls);
		model.addAttribute("deptId",deptId);
		model.addAttribute("deptPid",deptPid);
		return "/sport/showqcs.html";
	}



	/**
	 * 后台跳转器材列表页面
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/sqc/showAllQc")
	public String showAllQc(@RequestParam Integer deptId,@RequestParam Integer deptPid, Model model) {
		model.addAttribute("idPicUrls",idPicUrls);
		model.addAttribute("deptId",deptId);
		model.addAttribute("deptPid",deptPid);
		return "/sporthoutai/allqc.html";
	}

	/**
	 * 后台跳转添加器材页面
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/sqc/openAddQc")
	public String openAddQc(@RequestParam String deptId,@RequestParam String deptPid, Model model) {
		SQc sqc = new SQc();
		sqc.setDeptid(deptId);
		sqc.setDeptpid(deptPid);
		model.addAttribute("sqc",sqc);
		model.addAttribute("idPicUrls",idPicUrls);
		return "/sporthoutai/addqc.html";
	}

	/**
	 * 后台跳转修改器材页面
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/sqc/openQcEdit")
	public String openQcEdit(@RequestParam Integer qcId, Model model) {
		SQc sqc = service.selectById(qcId);
		model.addAttribute("sqc",sqc);
		model.addAttribute("idPicUrls",idPicUrls);
		return "/sporthoutai/addqc.html";
	}


	/**
	 * 后台跳转器材查看详细页面
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/sqc/showHoutaiQcDetail")
	public String showHoutaiQcDetail(@RequestParam Integer qcId, Model model) {
		SQc sqc = service.selectById(qcId);
		model.addAttribute("sqc",sqc);
		model.addAttribute("idPicUrls",idPicUrls);
		return "/sporthoutai/qcdetail.html";
	}

	/**
	 * 后台跳转全景照片页面
	 * @param deptId
	 * @param model
	 * @return
	 */
	@RequestMapping("/sqc/houtai/showQjPic")
	public String showQjPic(@RequestParam String deptId, Model model) {
		model.addAttribute("deptId",deptId);
		model.addAttribute("idPicUrls",idPicUrls);
		return "/sporthoutai/qjpic.html";
	}

	/**
	 * 前台跳转全景照片页面
	 * @param deptId
	 * @param model
	 * @return
	 */
	@RequestMapping("/sqc/qiantai/showQjPic")
	public String qtShowQjPic(@RequestParam String deptId,@RequestParam String deptPid, Model model) {
		model.addAttribute("deptId",deptId);
		model.addAttribute("deptPid",deptPid);
		model.addAttribute("idPicUrls",idPicUrls);
		return "/sport/qjpic.html";
	}

	/**
	 * 街道汇总统计
	 * @param queryMap
	 * @return
	 */
	@RequestMapping(value = "/huizongStatis/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object huizongQuery(@RequestParam Map<String,Object> queryMap) {
		this.log.info("/sqc/houtai/pageQuery param:{}",queryMap);
		Map<String, Object> res = new HashMap<>();
		int offset = Integer.valueOf(queryMap.get("offset").toString());
		int pageSize = Integer.valueOf(queryMap.get("limit").toString());
		Integer pageNum = (offset / pageSize + 1); //页数从1开始

		try {
			if(queryMap!=null) {
				if(queryMap.get("pageNum")!=null&&!"".equals(queryMap.get("pageNum"))) {
					pageNum = Integer.parseInt((String)queryMap.get("pageNum"));
				}
				if(queryMap.get("pageSize")!=null) {
					pageSize = Integer.parseInt((String)queryMap.get("pageSize"));
				}
			}


			Map<String,Object> params = new HashMap<>();
			List<String> deptids = new ArrayList<>();
			//如果传入的参数中 街道id 不为空，则deptids 里直接add deptid
			if(null !=queryMap.get("deptid")&&(!"".equals(queryMap.get("deptid").toString()))){
				deptids.add(queryMap.get("deptid").toString());
			}else {//如果没有传入街道id，则根据当前管理员所在的县查询出所有的街道，并且add 到 deptids里面
				ShiroUser user = ShiroKit.getUser();
				params.put("pid", user.getDeptId());
				List<Dept> depts = deptDao.selectPage(params);
				for (Dept dept : depts){
					deptids.add(dept.getId().toString());
				}
			}

			PageInfo pageInfo = this.service.selectHuizong(pageSize, pageNum,deptids);
			res.put("total",pageInfo.getTotal());
			res.put("rows",this.convertHuizong(pageInfo.getList()));
			return CommonUtil.obj2json(res);
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}

		return res;
	}

	/**
	 * 跳转街道汇总页面
	 * @return
	 */
	@RequestMapping("/huizongStatis/showHuizong")
	public String showHuizong() {
		return "/sporthoutai/huizong.html";
	}


	/**
	 * 器材列表页面的返回
	 * @param deptId 器材所属部门id
	 * @param model
	 * @return
	 */
	@RequestMapping("/sqc/qcBack")
	public String backDept(@RequestParam Integer deptId,Model model) {
		ShiroUser user = ShiroKit.getUser();
		Integer shiroDept = user.getDeptId();
		if(shiroDept == deptId){//如果当前登录用户部门的id和器材所属部门的id一样，直接返回首页
			model.addAttribute("idPicUrls",idPicUrls);
			return "/index.html";
		}else{//不一样，则跳转部门列表页
			Dept dept = deptMapper.selectById(deptId);
			model.addAttribute("deptId",dept.getPid());
			model.addAttribute("idPicUrls",idPicUrls);
			return "/sport/subdepts.html";
		}

	}





	/**
	 * 导出汇总统计
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/huizongStatis/exportHuizongList", method = RequestMethod.GET)
	@ResponseBody
	public Object exportHuizongList(@RequestParam Map<String,Object> queryMap, HttpServletResponse response){

		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("汇总统计");

			int i = 0;
			XSSFRow row;
			XSSFCell cell;
			XSSFCellStyle cellStyle;


			String [] titles = {"部门名称", "总数", "损坏", "维修", "拆除", "正常使用", "单位"};
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

			Map<String,Object> params = new HashMap<>();
			List<String> deptids = new ArrayList<>();
			//如果传入的参数中 街道id 不为空，则deptids 里直接add deptid
			if(null !=queryMap.get("deptid")&&(!"".equals(queryMap.get("deptid").toString()))){
				deptids.add(queryMap.get("deptid").toString());
			}else {//如果没有传入街道id，则根据当前管理员所在的县查询出所有的街道，并且add 到 deptids里面
				ShiroUser user = ShiroKit.getUser();
				params.put("pid", user.getDeptId());
				List<Dept> depts = deptDao.selectPage(params);
				for (Dept dept : depts){
					deptids.add(dept.getId().toString());
				}
			}
			List<Huizong> list = mapper.selectHuizong(deptids);
			Map<String,Object> queryparams = new HashMap<>();
			for(Huizong huizong : list){
				queryparams.put("deptpid",huizong.getDeptId());
				queryparams.put("qcxz","正常使用");
				Integer normal = mapper.selectHuizongByQcxz(queryparams);
				huizong.setNormal(normal);
				queryparams.put("qcxz","损坏");
				Integer badCount = mapper.selectHuizongByQcxz(queryparams);
				huizong.setBadCount(badCount);
				queryparams.put("qcxz","维修");
				Integer weixiu = mapper.selectHuizongByQcxz(queryparams);
				huizong.setWeixiu(weixiu);
				queryparams.put("qcxz","拆除");
				Integer  chaichu= mapper.selectHuizongByQcxz(queryparams);
				huizong.setChaichu(chaichu);
			}

			List<Huizong> huizongs = this.convertHuizong(list);

			for (Huizong huizong : huizongs) {
				fillExportRepayExecl(workbook, sheet, i++, huizong);
			}

			OutputStream os = response.getOutputStream();
			response.reset();
			response.setContentType("application/force-download");// 设置强制下载不打开
			response.addHeader("Content-Disposition", "attachment;fileName=" + new String("汇总统计.xlsx".getBytes("UTF-8"),"ISO-8859-1"));// 设置文件名
			workbook.write(os);
			workbook.close();
			os.close();

		}catch (Exception e){
			log.error("导出汇总统计出错 e={}", e);
			return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
		}
		return ResultVO.build(ErrorCode.SUCCESS);
	}

	private void fillExportRepayExecl(XSSFWorkbook workbook, XSSFSheet sheet, int nrow, Huizong huizong){
		XSSFRow row;
		XSSFCell cell;
		XSSFCellStyle cellStyle;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		row = sheet.createRow(nrow);
		int k = 0;
		//部门名称
		cell = row.createCell(k++);
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellValue(huizong.getDeptName());
		cell.setCellStyle(cellStyle);

		//总数
		cell = row.createCell(k++);
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellValue(huizong.getTotalCount());
		cell.setCellStyle(cellStyle);


		//损坏
		cell = row.createCell(k++);
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellValue(huizong.getBadCount());
		cell.setCellStyle(cellStyle);

		//维修
		cell = row.createCell(k++);
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellValue(huizong.getWeixiu());
		cell.setCellStyle(cellStyle);

		//拆除
		cell = row.createCell(k++);
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellValue(huizong.getChaichu());
		cell.setCellStyle(cellStyle);

		//正常使用
		cell = row.createCell(k++);
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellValue(huizong.getNormal());
		cell.setCellStyle(cellStyle);

		//单位
		cell = row.createCell(k++);
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		cell.setCellValue("件");
		cell.setCellStyle(cellStyle);
	}

	//汇总合计
	public List<Huizong> convertHuizong(List<Huizong> huizongs){
		 BigDecimal sumTotalCount = BigDecimal.ZERO;
		 BigDecimal sumBadCount = BigDecimal.ZERO;
		BigDecimal sumWeiXiu = BigDecimal.ZERO;
		BigDecimal sumChaiChu = BigDecimal.ZERO;
		BigDecimal sumNormal = BigDecimal.ZERO;
		Huizong huizongTmp = new Huizong();
		for (Huizong huizong : huizongs){
			sumTotalCount = sumTotalCount.add(new BigDecimal(huizong.getTotalCount()));
			sumBadCount = sumBadCount.add(new BigDecimal(huizong.getBadCount()));
			sumWeiXiu = sumWeiXiu.add(new BigDecimal(huizong.getWeixiu()));
			sumChaiChu = sumChaiChu.add(new BigDecimal(huizong.getChaichu()));
			sumNormal = sumNormal.add(new BigDecimal(huizong.getNormal()));
		}
		huizongTmp.setDeptName("合计");
		huizongTmp.setTotalCount(Integer.parseInt(sumTotalCount.toString()));
		huizongTmp.setBadCount(Integer.parseInt(sumBadCount.toString()));
		huizongTmp.setWeixiu(Integer.parseInt(sumWeiXiu.toString()));
		huizongTmp.setChaichu(Integer.parseInt(sumChaiChu.toString()));
		huizongTmp.setNormal(Integer.parseInt(sumNormal.toString()));
		huizongTmp.setDanwei("件");
		huizongs.add(huizongTmp);
		return huizongs;
	}

}

