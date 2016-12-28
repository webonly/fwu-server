package com.techsun.famouswine.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techsun.famouswine.domain.Banner;
import com.techsun.famouswine.service.BannerService;
import com.techsun.famouswine.util.ResultErrorInfo;
import com.techsun.famouswine.util.ResultSuccessInfo;


@Controller
public class BannerController {

	
	private BannerService bannerService;

	public BannerService getBannerService() {
		return bannerService;
	}

	@Autowired
	public void setBannerService(BannerService bannerService) {
		this.bannerService = bannerService;
	}
	
	
	@RequestMapping(value = "/banner/getAllBanner")
	public @ResponseBody
	
	
	Object getAllBanner(HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		List<Banner> bannerList = null;
		try {
			bannerList = bannerService.getAllBanner();

		} catch (Exception e) {
			reInfo.setErrorMessage("获取banner出错！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		if (bannerList != null) {
			rsInfo.setResult(bannerList);
			rsInfo.setErrorMessage("获取banner成功！");
		}

		return rsInfo;
	}
	
}
