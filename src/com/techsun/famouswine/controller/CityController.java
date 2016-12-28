package com.techsun.famouswine.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techsun.famouswine.domain.City;
import com.techsun.famouswine.service.CityService;
import com.techsun.famouswine.util.ResultErrorInfo;
import com.techsun.famouswine.util.ResultSuccessInfo;

@Controller
public class CityController {
	private CityService cityService;

	public CityService getCityService() {
		return cityService;
	}
	@Autowired
	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}
	
	
	@RequestMapping(value = "/city/getAllCity")
	public @ResponseBody
	Object getCityList(HttpServletRequest request, HttpServletResponse response) {
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		if(pageNum==null||pageNum.equals("")){
			reInfo.setErrorMessage("页码不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		
		if(pageSize==null||pageSize.equals("")){
			reInfo.setErrorMessage("页大小不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		List<City> cityList = new ArrayList<City>();
		try {
			cityList = cityService.getCityList(Integer.parseInt(pageNum),Integer.parseInt(pageSize));
			if(cityList==null){
				reInfo.setErrorCode("10002");
				reInfo.setErrorMessage("城市列表为空！");
				return reInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorCode("10002");
			reInfo.setErrorMessage("获取城市列表失败！");
			return reInfo;
		}
		rsInfo.setErrorMessage("获取城市列表成功！");
		rsInfo.setResult(cityList);
		return rsInfo;
	}
}
