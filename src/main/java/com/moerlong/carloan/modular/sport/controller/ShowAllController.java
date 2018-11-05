package com.moerlong.carloan.modular.sport.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Api(tags = { "controller接口类" })
public class ShowAllController {

	private final Logger log = LoggerFactory.getLogger(ShowAllController.class);

	@Value("${file.identity_pic_urls}")
	private String idPicUrls;

	/**
	 * 跳转添加器材页面
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/showAll")
	public String showAddCd(Model model) {
		model.addAttribute("idPicUrls",idPicUrls);
		return "/sporthoutai/alldepts.html";
	}
}

