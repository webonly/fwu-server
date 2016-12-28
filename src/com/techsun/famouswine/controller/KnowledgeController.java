package com.techsun.famouswine.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techsun.famouswine.domain.Knowledge;
import com.techsun.famouswine.service.KnowledgeService;
import com.techsun.famouswine.util.ResultErrorInfo;
import com.techsun.famouswine.util.ResultSuccessInfo;


@Controller
public class KnowledgeController {

	
	private KnowledgeService knowledgeService;

	public KnowledgeService getKnowledgeService() {
		return knowledgeService;
	}

	@Autowired
	public void setKnowledgeService(KnowledgeService knowledgeService) {
		this.knowledgeService = knowledgeService;
	}
	
	
	@RequestMapping(value = "/knowledge/getKnowledgeList")
	public @ResponseBody
	Object getKnowledgeList(HttpServletRequest request, HttpServletResponse response) {
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String communityId = request.getParameter("communityId");
		
		
		if(communityId==null||communityId.equals("")){
			reInfo.setErrorMessage("名酒社区ID不能为空！");
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
		List<Knowledge> knowledgeList = null;
		try {
			knowledgeList = knowledgeService.getKnowledgeList(Integer.parseInt(communityId), Integer.parseInt(pageNum), Integer.parseInt(pageSize));
			

		} catch (Exception e) {
			reInfo.setErrorMessage("获取knowledge出错！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		if (knowledgeList != null) {
			rsInfo.setResult(knowledgeList);
			rsInfo.setErrorMessage("获取knowledge成功！");
		}

		return rsInfo;
	}
	
	
	@RequestMapping(value="/knowledge/publishKnowledge")
	public @ResponseBody
	Object publishKnowledge(HttpServletRequest request,HttpSession session, 
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String userId = request.getParameter("userId");
		String title = request.getParameter("title");
		String imageUrl = request.getParameter("imageUrl");
		String communityId = request.getParameter("communityId");
		String content = request.getParameter("content");
		if(userId==null||userId.equals("")){
			reInfo.setErrorMessage("用户ID不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		
		if(title==null||title.equals("")){
			reInfo.setErrorMessage("标题不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if(communityId==null||communityId.equals("")){
			reInfo.setErrorMessage("社区ID不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if(imageUrl==null||imageUrl.equals("")){
			reInfo.setErrorMessage("图片不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if(content==null||content.equals("")){
			reInfo.setErrorMessage("内容不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		
		Knowledge knowledge = new Knowledge();
		knowledge.setUserId(Integer.parseInt(userId));
		knowledge.setCommunityId(Integer.parseInt(communityId));
		knowledge.setImageUrl(imageUrl);
		knowledge.setTitle(title);
		knowledge.setContent(content);
		
		int count = 0;
		try {
			count = knowledgeService.publishKnowledge(knowledge);
			
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("添加评论失败！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if(count > 0){
			rsInfo.setErrorMessage("添加评论成功！");
			rsInfo.setResult(count);			
		}else{
			reInfo.setErrorMessage("添加评论失败！");
			reInfo.setErrorCode("10005");
			return reInfo;
			
		}
		

		return rsInfo;
	}
	
}
