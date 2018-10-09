package com.moerlong.carloan.modular.car.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.moerlong.carloan.common.exception.BizExceptionEnum;
import com.moerlong.carloan.common.exception.BussinessException;
import com.moerlong.carloan.core.util.ToolUtil;
import com.moerlong.carloan.modular.car.entity.CarBussMortgageInfo;
import com.moerlong.carloan.modular.car.entity.CarInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import com.moerlong.carloan.modular.car.service.CarBussMortgageInfoService;
import com.moerlong.carloan.modular.car.service.CarInfoService;
import com.moerlong.carloan.modular.cust.entity.CustomerInfo;
import com.moerlong.carloan.modular.cust.service.CustomerInfoService;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;

@Controller
@Api(tags = { "controller接口类" })
public class CarBussMortgageInfoController {

	private final Logger log = LoggerFactory.getLogger(CarBussMortgageInfoController.class);

	@Autowired
	CarBussMortgageInfoService service;
	
	@Resource
    private CustomerInfoService customerInfoService;
    
    @Resource
    private ApplyInfoService applyInfoService;
    
    @Resource
    private CarInfoService carInfoService;
    
    @Value("${file.mortgage_contract_pic_path}")
    private String mortgagecontractpicpath;
    
    @Value("${file.proxy_book_pic_path}")
    private String proxybookpicpath;
    
    @Value("${file.cert_photo_pic_path}")
    private String certphotopicpath;
    
    @RequestMapping(value = "/carBussMortgageInfo/checkpic", method = RequestMethod.GET)
   	@ResponseBody
    public void checkpic(String applyid,String type,HttpServletResponse response)throws Exception{
    	CarBussMortgageInfo carBussMortgageInfo =service.selectByApplyId(Long.valueOf(applyid));
    	if(carBussMortgageInfo!=null) {//certphotopicpath
    		File _file=null;
    		if(type.equals("1")) {
    			_file=new File(carBussMortgageInfo.getMortgageContractUrl());
    			
				
			}else if(type.equals("2")) {
				_file=new File(carBussMortgageInfo.getProxyBookUrl());
    			
			}else if(type.equals("3")) {
				_file=new File(carBussMortgageInfo.getCertPhotoUrl());
				
			}
    		FileInputStream fis=new FileInputStream(_file);
			response.setContentType("image/jpeg");
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
    }
    
    @RequestMapping(value = "/carBussMortgageInfo/delFiles", method = RequestMethod.POST)
	@ResponseBody
	public Object delFiles(String applyid,String type) throws Exception{
    	Map<String, Object> res = new HashMap<>();
    	CarBussMortgageInfo carBussMortgageInfo =service.selectByApplyId(Long.valueOf(applyid));
    	if(carBussMortgageInfo!=null) {//certphotopicpath
    		if(type.equals("1")) {
    			File _file=new File(carBussMortgageInfo.getMortgageContractUrl());
    			_file.delete();
				carBussMortgageInfo.setMortgageContractUrl("");
			}else if(type.equals("2")) {
				File _file=new File(carBussMortgageInfo.getProxyBookUrl());
    			_file.delete();
				carBussMortgageInfo.setProxyBookUrl("");
			}else if(type.equals("3")) {
				File _file=new File(carBussMortgageInfo.getCertPhotoUrl());
    			_file.delete();
				carBussMortgageInfo.setCertPhotoUrl("");
			}
    		updateWithOutNull(carBussMortgageInfo);
    	}
    	res.put("status", 0);
		res.put("errMsg", "操作成功");
    	return res;
    }
	
	/**
	 * 文件上传
	 * @return
	 */
	@RequestMapping(value = "/carBussMortgageInfo/upLoadFiles", method = RequestMethod.POST)
	@ResponseBody
	public Object upLoadFiles(String applyid,MultipartFile file,String type) throws Exception{
		Map<String, Object> res = new HashMap<>();
		ApplyInfo applyInfo =applyInfoService.selectById(Long.valueOf(applyid));
		CarInfo carInfo =carInfoService.selectByCusId(applyInfo.getCustId());
		
		//上传图片到服务器
		InputStream in=file.getInputStream();
		if(type.equals("1")) {
			File _file=new File(mortgagecontractpicpath+File.separator+applyInfo.getId()+"_"+file.getOriginalFilename());
			file.transferTo(_file);
		}else if(type.equals("2")) {
			File _file=new File(proxybookpicpath+File.separator+applyInfo.getId()+"_"+file.getOriginalFilename());
			file.transferTo(_file);
		}else if(type.equals("3")) {
			File _file=new File(certphotopicpath+File.separator+applyInfo.getId()+"_"+file.getOriginalFilename());
			file.transferTo(_file);
		}
		//判断是否有汽车抵押记录
		CarBussMortgageInfo carBussMortgageInfo =service.selectByApplyId(applyInfo.getId());
		if(carBussMortgageInfo==null) {
			CustomerInfo customerInfo =customerInfoService.selectById(applyInfo.getCustId());
			carBussMortgageInfo=new CarBussMortgageInfo();
			carBussMortgageInfo.setApplyId(applyInfo.getId());
			carBussMortgageInfo.setCarId(carInfo.getId());
			carBussMortgageInfo.setCustId(applyInfo.getCustId());
			carBussMortgageInfo.setCustName(customerInfo.getName());
			carBussMortgageInfo.setCarId(Long.valueOf(customerInfo.getCertId()));
			carBussMortgageInfo.setCustSex(customerInfo.getSex());
			carBussMortgageInfo.setCreateTime(new Date());
			carBussMortgageInfo.setUpdateTime(new Date());
			if(type.equals("1")) {
				carBussMortgageInfo.setMortgageContractUrl(mortgagecontractpicpath+File.separator+applyInfo.getId()+"_"+file.getOriginalFilename());
			}else if(type.equals("2")) {
				carBussMortgageInfo.setProxyBookUrl(proxybookpicpath+File.separator+applyInfo.getId()+"_"+file.getOriginalFilename());
			}else if(type.equals("3")) {
				carBussMortgageInfo.setCertPhotoUrl(certphotopicpath+File.separator+applyInfo.getId()+"_"+file.getOriginalFilename());
			}
			saveOrUpdate(carBussMortgageInfo);
		}else {
			if(type.equals("1")) {
				carBussMortgageInfo.setMortgageContractUrl(mortgagecontractpicpath+File.separator+applyInfo.getId()+"_"+file.getOriginalFilename());
			}else if(type.equals("2")) {
				carBussMortgageInfo.setProxyBookUrl(proxybookpicpath+File.separator+applyInfo.getId()+"_"+file.getOriginalFilename());
			}else if(type.equals("3")) {
				carBussMortgageInfo.setCertPhotoUrl(certphotopicpath+File.separator+applyInfo.getId()+"_"+file.getOriginalFilename());
			}
			updateWithOutNull(carBussMortgageInfo);
		}
		res.put("status", 0);
		res.put("errMsg", "操作成功");
		return res;
	}

	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "CarBussMortgageInfo", value = "明细")
	@RequestMapping(value = "/carBussMortgageInfo/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(CarBussMortgageInfo entity) {
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
			CarInfo carInfo = carInfoService.selectByCusId(applyinfo.getCustId());
			if(null != carInfo){
				entity.setCarId(carInfo.getId());
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
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "CarBussMortgageInfo", value = "明细")
	@RequestMapping(value = "/carBussMortgageInfo/updateWithOutNull", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWithOutNull(@RequestBody CarBussMortgageInfo entity) {
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
	@RequestMapping(value = "/carBussMortgageInfo/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/carBussMortgageInfo/deleteLogicById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/carBussMortgageInfo/findById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/carBussMortgageInfo/listAll", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/carBussMortgageInfo/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestBody Map<String,Object> queryMap) {
		this.log.info("/carBussMortgageInfo/pageQuery param:{}",queryMap);
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
	@RequestMapping(value = "/carBussMortgageInfo/selectByApplyId", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object selectByApplyId(@RequestParam Map<String,Object> param) {
		Map<String, Object> res = new HashMap<>();
		try {
			Long applyId = Long.valueOf(param.get("applyId").toString());
			res.put("data", service.selectByApplyId(applyId));
			res.put("status", 0);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}

}

