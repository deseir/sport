package com.moerlong.carloan.modular.loan.controller;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.moerlong.carloan.common.exception.BizExceptionEnum;
import com.moerlong.carloan.common.exception.BussinessException;
import com.moerlong.carloan.core.util.ToolUtil;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import com.moerlong.carloan.util.EscapeUtil;
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
import com.moerlong.carloan.modular.loan.entity.SupplementInfo;
import com.moerlong.carloan.modular.loan.service.SupplementInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Api(tags = { "controller接口类" })
public class SupplementInfoController {

	private final Logger log = LoggerFactory.getLogger(SupplementInfoController.class);

	@Autowired
	SupplementInfoService service;
	@Autowired
	ApplyInfoService applyInfoService;
	@Value("${file.identity_pic_path}")
	private String identityPicPath;
	@Value("${file.contact_pdf_path}")
	private String contactPdfPath;
	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "SupplementInfo", value = "明细")
	@RequestMapping(value = "/supplementInfo/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(SupplementInfo entity) {
		Map<String, Object> res = new HashMap<>();
		try {
			Long applyID= entity.getApplyId();
			if (ToolUtil.isEmpty(applyID)) {
				throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
			}
			ApplyInfo applyinfo = applyInfoService.selectById(applyID);
			if (ToolUtil.isEmpty(applyinfo)) {
				throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
			}
			entity.setCustId(applyinfo.getCustId());
			service.saveOrUpdate(entity);
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
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "SupplementInfo", value = "明细")
	@RequestMapping(value = "/supplementInfo/updateWithOutNull", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWithOutNull(@RequestBody SupplementInfo entity) {
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
	@RequestMapping(value = "/supplementInfo/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object deleteById(@RequestBody Map<String,Object> param) {
	
		Map<String, Object> res = new HashMap<>();
		try {
			Long id = Long.valueOf(param.get("id").toString());
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
	@RequestMapping(value = "/supplementInfo/deleteLogicById", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object deleteLogic(@RequestBody Map<String,Object> param) {
		Map<String, Object> res = new HashMap<>();
		try {
			Long id = Long.valueOf(param.get("id").toString());
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
	@RequestMapping(value = "/supplementInfo/findById", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object findById(@RequestBody Map<String,Object> param) {
		Map<String, Object> res = new HashMap<>();
		try {
			Long id = Long.valueOf(param.get("id").toString());
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
	@RequestMapping(value = "/supplementInfo/listAll", method = {RequestMethod.POST,RequestMethod.GET})
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

	@ApiOperation(value = "分页查询")
	@ApiImplicitParam(paramType = "body", name = "queryMap", required = false, dataType = "Map", value = "查询条件")
	@RequestMapping(value = "/supplementInfo/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestBody Map<String,Object> queryMap) {
		this.log.info("/supplementInfo/pageQuery param:{}",queryMap);
		Map<String, Object> res = new HashMap<>();
		Integer pageNum = 1; //页数从1开始
		Integer pageSize = 10; //页面大小
			
		try {
			if(queryMap!=null) {
				if(queryMap.get("pageNum")!=null) {
					pageNum = (Integer)queryMap.get("pageNum");
				}
				if(queryMap.get("pageSize")!=null) {
					pageSize = (Integer)queryMap.get("pageSize");
				}
			}
			
			Object pageInfo = this.service.selectPage(pageSize, pageNum, queryMap);
			
			res.put("data", pageInfo);
			res.put("status", 0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		
		return res;
	}

	@ApiOperation(value = "根据applyId查找")
	@ApiImplicitParam(paramType = "body", name = "param", required = false, dataType = "Map", value = "参数")
	@RequestMapping(value = "/supplementInfo/findByApplyId", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object findByApplyId(@RequestParam Map<String,Object> param) {
		Map<String, Object> res = new HashMap<>();
		try {
			SupplementInfo supplementInfo = service.selectByApplyId(param);
			res.put("data", supplementInfo);
			res.put("status", supplementInfo==null?1:0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}
	/**
	 * 下载补充材料附件
	 */
	@RequestMapping(value = "/supplementInfo/downLoadSupplementaryMaterial", method = RequestMethod.GET)
	public void downLoadSupplementaryMaterial(HttpServletResponse response,String params) throws Exception{
		params = EscapeUtil.unescape(params);
		String fileName=params.substring(params.lastIndexOf("/"));
		params=params.replace(fileName,"");
		String fileName1=params.substring(params.lastIndexOf("/")+1);
		File f = new File(identityPicPath+File.separator+fileName1+File.separator+fileName);
		if (!f.exists()) {
			response.sendError(404, "File not found!");
			return;
		}
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
		byte[] buf = new byte[1024];
		int len = 0;
		response.reset(); // 非常重要
		String showName = f.getName();
		if(showName.contains("$")){
			StringTokenizer st = new StringTokenizer(showName,"$");
			while(st.hasMoreElements()){
				showName = st.nextToken();
			}
		}
		if (false) { // 在线打开方式
			URL u = new URL("file:///" + identityPicPath+File.separator+fileName1+File.separator+fileName);
			response.setContentType(u.openConnection().getContentType());
			response.setHeader("Content-Disposition", "inline; filename=" + new String(showName.getBytes("UTF-8"),"ISO-8859-1"));
			// 文件名应该编码成UTF-8
		} else { // 纯下载方式
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename=" + new String(showName.getBytes("UTF-8"),"ISO-8859-1"));
		}
		OutputStream out = response.getOutputStream();
		while ((len = br.read(buf)) > 0){
			out.write(buf, 0, len);
		}
		br.close();
		out.close();
	}
	/**
	 * 下载补充材料附件
	 */
	@RequestMapping(value = "/supplementInfo/downLoadSupplementaryMaterials", method = RequestMethod.GET)
	public void downLoadSupplementaryMaterials(HttpServletResponse response,String params) throws Exception{
		String fileName=params.substring(params.lastIndexOf("/"));
		params=params.replace(fileName,"");
		//String fileName1=params.substring(params.lastIndexOf("/")+1);
		File f = new File(contactPdfPath+File.separator+File.separator+fileName);
		if (!f.exists()) {
			response.sendError(404, "File not found!");
			return;
		}
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
		byte[] buf = new byte[1024];
		int len = 0;
		response.reset(); // 非常重要
		if (false) { // 在线打开方式
			URL u = new URL("file:///" + identityPicPath+File.separator+File.separator+fileName);
			response.setContentType(u.openConnection().getContentType());
			response.setHeader("Content-Disposition", "inline; filename=" + new String(f.getName().getBytes("UTF-8"),"ISO-8859-1"));
			// 文件名应该编码成UTF-8
		} else { // 纯下载方式
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename=" + new String(f.getName().getBytes("UTF-8"),"ISO-8859-1"));
		}
		OutputStream out = response.getOutputStream();
		while ((len = br.read(buf)) > 0){
			out.write(buf, 0, len);
		}
		br.close();
		out.close();
	}
}

