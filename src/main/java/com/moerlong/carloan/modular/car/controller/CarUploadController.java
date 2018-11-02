package com.moerlong.carloan.modular.car.controller;

import com.moerlong.carloan.modular.car.service.CarBussInsureInfoService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//@Controller
//@Api(tags = { "controller接口类" })
public class CarUploadController {

	private final Logger log = LoggerFactory.getLogger(CarUploadController.class);

	@Autowired
	CarBussInsureInfoService service;

	@Value("${file.pic_path}")
	private String picPath;

	/**
	 *上传图片根据交易类型存贮
	 */
	@RequestMapping(value = "/carInfo/upload", method = RequestMethod.POST)
	@ResponseBody
	public Object upload(@RequestParam(name = "file", required = false) MultipartFile myfile) {
		Map<String, Object> res = new HashMap<>();
		String newFileName="";
		try {

			if (myfile != null && !myfile.isEmpty()) {

				// 创建目录
				File f = new File(picPath);
				if (!f.exists()) {
					f.mkdirs();
				}

				// 生成一个新的不会重复的文件名
				String suffix = FilenameUtils.getExtension(myfile.getOriginalFilename());
				newFileName = UUID.randomUUID().toString() + "." + suffix;
				log.info("上传服务器文件名称是：{}", newFileName);

				// 把上传的文件存储指定位置
				myfile.transferTo(new File(f, newFileName));

				res.put("status", 0);
				res.put("errMsg", "操作成功");
				res.put("picKey", newFileName);
			}else{
				res.put("status", 1);
				res.put("errMsg", "文件上传失败");
			}
		} catch (Exception e) {
			this.log.error(e.getMessage(), e);
			res.put("status", 1);
			res.put("errMsg", e.toString());
		}
		return res;
	}


}

