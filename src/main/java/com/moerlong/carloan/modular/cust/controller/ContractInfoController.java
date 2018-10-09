package com.moerlong.carloan.modular.cust.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.*;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.common.exception.BizExceptionEnum;
import com.moerlong.carloan.common.exception.BussinessException;
import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.core.shiro.ShiroKit;
import com.moerlong.carloan.core.shiro.ShiroUser;
import com.moerlong.carloan.core.support.HttpKit;
import com.moerlong.carloan.core.util.ToolUtil;
import com.moerlong.carloan.modular.cust.dao.ContractInfoDao;
import com.moerlong.carloan.modular.cust.entity.vo.ContractInfoVo;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import com.moerlong.carloan.util.CommonUtil;
import com.moerlong.carloan.util.EscapeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import com.moerlong.carloan.modular.cust.entity.ContractInfo;
import com.moerlong.carloan.modular.cust.service.ContractInfoService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Api(tags = { "controller接口类" })
public class ContractInfoController {

	private final Logger log = LoggerFactory.getLogger(ContractInfoController.class);
	private static String PREFIX = "/cust/";
	@Autowired
	ContractInfoService service;
	@Autowired
	ApplyInfoService applyInfoService;
	@Autowired
	ContractInfoDao mapper;
	@Value("${file.identity_pic_path}")
	private String idPicPath;
	@Value("${file.identity_pic_url}")
	private String idPicUrl;
	@Value("${file.contact_pdf_path}")
	private String contactPdfPath;

	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "ContractInfo", value = "明细")
	@RequestMapping(value = "/contractInfo/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(@RequestBody ContractInfo entity) {
		Map<String, Object> res = new HashMap<>();
		try {
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
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "ContractInfo", value = "明细")
	@RequestMapping(value = "/contractInfo/updateWithOutNull", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWithOutNull(@RequestBody ContractInfo entity) {
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
	@RequestMapping(value = "/contractInfo/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/contractInfo/deleteLogicById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/contractInfo/findById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/contractInfo/listAll", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/contractInfo/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestParam Map<String,Object> queryMap) {
		this.log.info("/contractInfo/pageQuery param:{}",queryMap);
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
	/**
	 * 跳转到添加contractInfo页面
	 */
	@RequestMapping("/contractInfo/contractInfo")
	public String contractInfo() {
		return PREFIX + "contractInfo.html";
	}
	/**
	 * 查询合同列表
	 */
	@RequestMapping(value = "/contractInfo/findlist", method = RequestMethod.POST)
	@ResponseBody
	public Object findlist(@RequestParam Map<String,Object> queryMap){
		HttpServletRequest request = HttpKit.getRequest();
		int limit = Integer.valueOf(request.getParameter("limit"));
		int offset = Integer.valueOf(request.getParameter("offset"));
		Map<String, Object> res = new HashMap<>();
		Integer pageNum = (offset / limit + 1); //页数从1开始
		Integer pageSize = limit; //页面大小

		try {
			PageInfo<ContractInfoVo> pageInfo = this.service.selectContractInfos(pageSize, pageNum,queryMap);
			res.put("total",pageInfo.getTotal());
			for (ContractInfoVo contractInfoVo : pageInfo.getList()) {
				String ontractUrl1=contractInfoVo.getContractUrl1();
				String ontractUrl2=contractInfoVo.getContractUrl2();
				String ontractUrl3=contractInfoVo.getContractUrl3();
				String ontractUrl4=contractInfoVo.getContractUrl4();
				String ontractUrl5=contractInfoVo.getContractUrl5();
				String ontractUrl6=contractInfoVo.getContractUrl6();
				String contractSceneUrl=contractInfoVo.getContractSceneUrl();
				String contractAttachUrl=contractInfoVo.getContractAttachUrl();
				if(ontractUrl1!=null){
					ontractUrl1="<a class=LookPDF' style='color:#30af90' id='"+ontractUrl1+"' title='edit' onclick='LookPDF(this)'>查看</a>";
					contractInfoVo.setContractUrl1(ontractUrl1);
				}
				if(ontractUrl2!=null){
					ontractUrl2="<a class=LookPDF' style='color:#30af90' id='"+ontractUrl2+"' title='edit' onclick='LookPDF(this)'>查看</a>";
					contractInfoVo.setContractUrl2(ontractUrl2);
				}
				if(ontractUrl3!=null){
					ontractUrl3="<a class=LookPDF' style='color:#30af90' id='"+ontractUrl3+"' title='edit' onclick='LookPDF(this)'>查看</a>";
					contractInfoVo.setContractUrl3(ontractUrl3);
				}
				if(ontractUrl4!=null){
					ontractUrl4="<a class=LookPDF' style='color:#30af90' id='"+ontractUrl4+"' title='edit' onclick='LookPDF(this)'>查看</a>";
					contractInfoVo.setContractUrl4(ontractUrl4);
				}
				if(ontractUrl5!=null){
					ontractUrl5="<a class=LookPDF' style='color:#30af90' id='"+ontractUrl5+"' title='edit' onclick='LookPDF(this)'>查看</a>";
					contractInfoVo.setContractUrl5(ontractUrl5);
				}
				if(ontractUrl6!=null){
					ontractUrl6="<a class=LookPDF' style='color:#30af90' id='"+ontractUrl6+"' title='edit' onclick='LookPDF(this)'>查看</a>";
					contractInfoVo.setContractUrl6(ontractUrl6);
				}
				if(contractSceneUrl!=null){
					contractSceneUrl="<a class=LookPDF' style='color:#30af90' id='"+contractSceneUrl+"' title='edit' onclick='LookPDF(this)'>查看</a>";
					contractInfoVo.setContractSceneUrl(contractSceneUrl);
				}
				if(contractAttachUrl!=null){
					contractAttachUrl="<a class=LookPDF' style='color:#30af90' id='"+contractAttachUrl+"' title='edit' onclick='LookPDF(this)'>查看</a>";
					contractInfoVo.setContractAttachUrl(contractAttachUrl);
				}
			}

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
	 * 跳转到上传PDF页面
	 */
	@RequestMapping("/contractInfo/uploadField")
	public String newAddCarInfo() {
		return PREFIX + "uploadField.html";
	}
	/**
	 * 上传合同模板信息
	 * @param myfile
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST,value="/contractInfo/uploadContractInfoField")
	@ResponseBody
	private Object uploadContractInfoField(@RequestParam(name = "file", required = false) MultipartFile myfile)  {
		return saveFile(myfile, idPicPath, idPicUrl);
	}
	/**
	 * 保存文件
	 * @param myfile
	 * @return
	 */
	private Object saveFile(MultipartFile myfile, String fileSubPath, String fileUrl){
		String FilePath ="";
		if (myfile != null && !myfile.isEmpty()) {
			// 获取文件的原始名称
			String oldName = myfile.getOriginalFilename();
			// 获取文件大小
			Long fileSize = myfile.getSize();
			// 组装文件存储路径
			//判断文件夹是否存在,不存在则创建
			String filePath = fileSubPath + File.separator + "uploadContractInfoField";
			// 创建目录
			File f = new File(filePath);
			if (!f.exists()) {
				f.mkdirs();
			}
			//获取文件名,判断文件夹下是否存在同名文件,存在则覆盖,不存在直接上传
			String newFileName = myfile.getOriginalFilename();
			String directoryFilePath =  fileSubPath + File.separator + "uploadContractInfoField/"+newFileName;
			f = new File(directoryFilePath);
			if(f.isDirectory()){
				f.delete();
			}
			f = new File(filePath);
			// 把上传的文件存储指定位置
			try{
				myfile.transferTo(new File(f, newFileName));
			}catch (Exception e){
				log.error("===>[saveFile] exception e={}", e);
				return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
			}

			FilePath=fileUrl+ File.separator + File.separator + newFileName;
			log.info("上传成功！！文件路径===》{}",FilePath);
			return ResultVO.build(ErrorCode.SUCCESS, FilePath);

		} else {
			log.error("===>[saveFile] file error");
			return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
		}
	}

	/**
	 * 生成合同编号
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
		ContractInfo lastContract = service.selectLastContract();
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

	@ApiOperation(value = "保存或更新合同签署现场照片")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "ContractInfo", value = "明细")
	@RequestMapping(value = "/contractInfo/saveOrUpdateContractSceneUrl", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdateContractSceneUrl(ContractInfo entity) {
		entity.setHeTongFuJianUrl(entity.getHeTongFuJianUrl().replaceAll("& #40;","(").replaceAll("& #41;",")"));
		Map<String, Object> res = new HashMap<>();
		try {
			Long applyId = entity.getApplyId();
			if (ToolUtil.isEmpty(applyId)) {
				throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
			}
			ApplyInfo applyinfo = applyInfoService.selectById(applyId);
			if (ToolUtil.isEmpty(applyinfo)) {
				throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
			}

			ContractInfo contractInfo = service.selectByApplyId(applyId);
			if(null != contractInfo){
				entity.setId(contractInfo.getId());
				if(null == contractInfo.getCustId()){
					entity.setCustId(applyinfo.getCustId());
				}
				if(null == contractInfo.getContractNo()||"".equals(contractInfo.getContractNo())){
					entity.setContractNo(this.returnContractNo());
				}
			}else{
				entity.setContractNo(this.returnContractNo());
				entity.setCustId(applyinfo.getCustId());
			}
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
	/**
	 * 查看文件
	 * @param
	 * @return
	 */
	@RequestMapping( method = RequestMethod.GET,value="/contractInfo/downloadFile")
	private void downloadFile(HttpServletResponse response, String downloadFileurl) throws Exception{
		File _file=new File(contactPdfPath+File.separator+downloadFileurl);
		FileInputStream fis=new FileInputStream(_file);
		response.setContentType("application/pdf");
		OutputStream os=response.getOutputStream();
		try {
			int count=0;
			byte[] buffer=new byte[1024*1024];
			while((count=fis.read(buffer))!=-1) {
				os.write(buffer,0,count);
			}
			os.flush();
		} finally {
			os.close();
			fis.close();
		}

	}

	@ApiOperation(value = "根据applyId查找")
	@ApiImplicitParam(paramType = "body", name = "param", required = false, dataType = "Map", value = "参数")
	@RequestMapping(value = "/contractInfo/findByApplyId", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object findByApplyId(@RequestParam Map<String,Object> param) {
		Map<String, Object> res = new HashMap<>();
		try {
			Long applyId = Long.valueOf(param.get("applyId").toString());
			if (ToolUtil.isEmpty(applyId)) {
				throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
			}
			ContractInfo contractInfo = service.selectByApplyId(applyId);
			res.put("data", contractInfo);
			res.put("status", contractInfo !=null?0:1);
			res.put("contactPdfPath", contactPdfPath);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}








}

