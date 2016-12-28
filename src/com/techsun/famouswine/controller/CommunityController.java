package com.techsun.famouswine.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techsun.famouswine.domain.Community;
import com.techsun.famouswine.service.CommunityService;
import com.techsun.famouswine.util.ResultErrorInfo;
import com.techsun.famouswine.util.ResultSuccessInfo;


@Controller
public class CommunityController {

	
	private CommunityService communityService;

	public CommunityService getCommunityService() {
		return communityService;
	}

	@Autowired
	public void setCommunityService(CommunityService communityService) {
		this.communityService = communityService;
	}
	
	
	@RequestMapping(value = "/knowledge/getKnowledgeCommunity")
	public @ResponseBody
	Object getAllCommunity(HttpServletRequest request, HttpServletResponse response) {
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String type = request.getParameter("type");
		
		
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
			reInfo.setErrorMessage("页大小不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		List<Community> communityList = null;
		try {
			communityList = communityService.getKnowledgeCommunity(Integer.parseInt(type), Integer.parseInt(pageNum), Integer.parseInt(pageSize));
			if(communityList.size()==0){
				reInfo.setErrorMessage("获取community为空！");
				reInfo.setErrorCode("10003");
				return reInfo;
			}
		} catch (Exception e) {
			reInfo.setErrorMessage("获取community出错！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		if (communityList != null) {
			rsInfo.setResult(communityList);
			rsInfo.setErrorMessage("获取community成功！");
		}

		return rsInfo;
	}
	
}
