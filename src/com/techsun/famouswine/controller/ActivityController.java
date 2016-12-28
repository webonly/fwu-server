package com.techsun.famouswine.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techsun.famouswine.domain.Activity;
import com.techsun.famouswine.service.ActivityService;
import com.techsun.famouswine.util.ResultErrorInfo;
import com.techsun.famouswine.util.ResultSuccessInfo;


@Controller
public class ActivityController {

	
	private ActivityService activityService;

	public ActivityService getActivityService() {
		return activityService;
	}

	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}
	
	
	@RequestMapping(value = "/activity/getActivityListByType")
	public @ResponseBody
	Object getAllActivity(HttpServletRequest request, HttpServletResponse response) {
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		
		String type = request.getParameter("type");		
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		
		
		if(type==null||type.equals("")){
			reInfo.setErrorMessage("活动类型不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		
		if(pageNum==null||pageNum.equals("")){
			reInfo.setErrorMessage("页码不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		
		if(pageSize==null||pageSize.equals("")){
			reInfo.setErrorMessage("页面大小不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		
		
		
		List<Activity> activityList = null;
		try {
			activityList = activityService.getActivityListByType(Integer.parseInt(type), Integer.parseInt(pageNum),
					Integer.parseInt(pageSize));
			if (activityList.size()==0) {
				rsInfo.setResult(activityList);
				rsInfo.setErrorMessage("activity不存在！");
				return rsInfo;
			}

		} catch (Exception e) {
			reInfo.setErrorMessage("获取activity出错！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		rsInfo.setResult(activityList);
		rsInfo.setErrorMessage("获取activity成功！");
		return rsInfo;
	}
	
	
	
	@RequestMapping(value = "/activity/getActivityListByMerchantId")
	public @ResponseBody
	Object getActivityListByMerchantId(HttpServletRequest request, HttpServletResponse response) {
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		
		String merchantId = request.getParameter("merchantId");		
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		
		
		if(merchantId==null||merchantId.equals("")){
			reInfo.setErrorMessage("商家ID不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		
		if(pageNum==null||pageNum.equals("")){
			reInfo.setErrorMessage("页码不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		
		if(pageSize==null||pageSize.equals("")){
			reInfo.setErrorMessage("页面大小不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		
		
		
		List<Activity> activityList = null;
		try {
			activityList = activityService.getActivityListByMerchantId(Integer.parseInt(merchantId), Integer.parseInt(pageNum),
					Integer.parseInt(pageSize));
			

		} catch (Exception e) {
			reInfo.setErrorMessage("获取activity出错！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		if (activityList != null) {
			rsInfo.setResult(activityList);
			rsInfo.setErrorMessage("获取activity成功！");
		}

		return rsInfo;
	}
	
}
