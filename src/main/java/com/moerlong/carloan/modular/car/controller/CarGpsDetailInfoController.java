package com.moerlong.carloan.modular.car.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import com.moerlong.carloan.core.util.JoinMoreImage;
import com.moerlong.carloan.modular.car.entity.CarGpsDetailInfo;
import com.moerlong.carloan.modular.car.entity.CarInfo;
import com.moerlong.carloan.modular.car.service.CarGpsDetailInfoService;
import com.moerlong.carloan.modular.car.service.CarInfoService;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;

@Controller
@Api(tags = { "controller接口类" })
public class CarGpsDetailInfoController {

	private final Logger log = LoggerFactory.getLogger(CarGpsDetailInfoController.class);

	@Autowired
	CarGpsDetailInfoService service;
	
	@Resource
    private ApplyInfoService applyInfoService;
	
	@Resource
    private CarInfoService carInfoService;
	
	@Value("${file.car_pgs_pic_path}")
    private String carpgspicpath;

	@RequestMapping(value = "/carGpsDetailInfo/checkW", method = RequestMethod.GET)
	@ResponseBody
	public void checkW(Long applyid,String type,HttpServletResponse response) throws Exception{
		ApplyInfo applyInfo=applyInfoService.selectById(applyid);
		CarInfo carInfo=carInfoService.selectByCusId(applyInfo.getCustId());
		List<CarGpsDetailInfo> carGpsDetailInfos= service.selectByCarId(carInfo.getId());
		String[] imgs=new String[carGpsDetailInfos.size()];
		for(int i=0;i<carGpsDetailInfos.size();i++) {
			if(type.equals(carGpsDetailInfos.get(i).getIsWiredless().toString())) {
				imgs[i]=carGpsDetailInfos.get(i).getGpsPhotoUrl();
			}
		}
		if(JoinMoreImage.merge(imgs,"jpg",carpgspicpath+File.separator+"test.jpg")) {
			File _file=new File(carpgspicpath+File.separator+"test.jpg");
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
	
	@RequestMapping(value = "/carGpsDetailInfo/uploadgpspic", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadgpspic(Long applyid,String wiredless ,MultipartFile file) throws Exception {
		Map<String, Object> res = new HashMap<>();
		ApplyInfo applyInfo=applyInfoService.selectById(applyid);
		CarInfo carInfo=carInfoService.selectByCusId(applyInfo.getCustId());
		File _file=new File(carpgspicpath+File.separator+applyInfo.getId()+file.getOriginalFilename());
		file.transferTo(_file);
		CarGpsDetailInfo CarGpsDetailInfo=new CarGpsDetailInfo();
		if(carInfo!=null) {
			CarGpsDetailInfo.setCarId(carInfo.getId());
		}
		CarGpsDetailInfo.setGpsPhotoUrl(carpgspicpath+File.separator+applyInfo.getId()+file.getOriginalFilename());
		//CarGpsDetailInfo.setGpsWiredNo(wiredless);
		CarGpsDetailInfo.setIsWiredless(Integer.valueOf(wiredless));
		CarGpsDetailInfo.setUpdateTime(new Date());
		CarGpsDetailInfo.setCreateTime(new Date());
		saveOrUpdate(CarGpsDetailInfo);
		res.put("status", 0);
		res.put("errMsg", "操作成功");
		return res;
	}
	
	@ApiOperation(value = "保存或更新")
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "CarGpsDetailInfo", value = "明细")
	@RequestMapping(value = "/carGpsDetailInfo/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(CarGpsDetailInfo entity) {
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
	@ApiImplicitParam(paramType = "body", name = "entity", required = true, dataType = "CarGpsDetailInfo", value = "明细")
	@RequestMapping(value = "/carGpsDetailInfo/updateWithOutNull", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWithOutNull(@RequestBody CarGpsDetailInfo entity) {
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
	@RequestMapping(value = "/carGpsDetailInfo/deleteById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/carGpsDetailInfo/deleteLogicById", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/carGpsDetailInfo/findById", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object findById(@RequestParam Map<String,Object> param) {
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
	@RequestMapping(value = "/carGpsDetailInfo/listAll", method = {RequestMethod.POST,RequestMethod.GET})
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
	@RequestMapping(value = "/carGpsDetailInfo/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestBody Map<String,Object> queryMap) {
		this.log.info("/carGpsDetailInfo/pageQuery param:{}",queryMap);
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


	@ApiOperation(value = "根据carId查询")
	@RequestMapping(value = "/carGpsDetailInfo/selectByCarId", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object selectByCarId(@RequestParam Map<String ,Object> params) {
		Map<String, Object> res = new HashMap<>();
		try {
			Long carId = Long.parseLong(params.get("carId").toString());
			List<CarGpsDetailInfo> list = service.selectByCarId(carId);
			res.put("data", list);
			res.put("status", list!=null?0:1);
			res.put("errMsg", "操作成功");
		} catch (Throwable e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.getMessage());
		}
		return res;
	}

	@ApiOperation(value = "根据id一次删除多个")
	@ApiImplicitParam(paramType = "body", name = "param", required = false, dataType = "Map", value = "参数")
	@RequestMapping(value = "/carGpsDetailInfo/deleteByIds", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object deleteByIds(@RequestParam String ids) {
		Map<String, Object> res = new HashMap<>();
		List idList = new ArrayList();
		String [] idsArr = ids.split(",");
		for(int i=0;i<idsArr.length;i++){
			idList.add(Long.parseLong(idsArr[i]));
		}
		try {
			service.deleteByIds(idList);
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

