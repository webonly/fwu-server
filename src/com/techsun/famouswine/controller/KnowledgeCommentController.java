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
import com.techsun.famouswine.domain.KnowledgeComment;
import com.techsun.famouswine.service.KnowledgeCommentService;
import com.techsun.famouswine.service.KnowledgeService;
import com.techsun.famouswine.util.ResultErrorInfo;
import com.techsun.famouswine.util.ResultSuccessInfo;


@Controller
public class KnowledgeCommentController {

	private KnowledgeCommentService knowledgeCommentService;

	
	
	
	public KnowledgeCommentService getKnowledgeCommentService() {
		return knowledgeCommentService;
	}

	@Autowired
	public void setKnowledgeCommentService(KnowledgeCommentService knowledgeCommentService) {
		this.knowledgeCommentService = knowledgeCommentService;
	}


	@RequestMapping(value = "/knowledge/getKnowledgeCommentList")
	public @ResponseBody
	Object getKnowledgeList(HttpServletRequest request, HttpServletResponse response) {
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String knowledgeId = request.getParameter("knowledgeId");
		
		
		if(knowledgeId==null||knowledgeId.equals("")){
			reInfo.setErrorMessage("名酒知识ID不能为空！");
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
		List<KnowledgeComment> knowledgeList = null;
		try {
			knowledgeList = knowledgeCommentService.getCommentList(Integer.parseInt(knowledgeId), Integer.parseInt(pageNum), Integer.parseInt(pageSize));
			if(knowledgeList.size()==0){
				reInfo.setErrorMessage("knowledge为空！");
				reInfo.setErrorCode("10003");
				return reInfo;
			}

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
	
	
	@RequestMapping(value="/knowledge/publishKnowledgeCommment")
	public @ResponseBody
	Object publishKnowledge(HttpServletRequest request,HttpSession session, 
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String userId = request.getParameter("userId");
		String knowledgeId = request.getParameter("knowledgeId");
		String content = request.getParameter("content");
		if(userId==null||userId.equals("")){
			reInfo.setErrorMessage("用户ID不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		
		if(knowledgeId==null||knowledgeId.equals("")){
			reInfo.setErrorMessage("社区ID不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		
		if(content==null||content.equals("")){
			reInfo.setErrorMessage("内容不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		
		KnowledgeComment comment = new KnowledgeComment();
		comment.setUserId(Integer.parseInt(userId));
		comment.setKnowledgeId(Integer.parseInt(knowledgeId));
		comment.setContent(content);
		
		int count = 0;
		try {
			count = knowledgeCommentService.commentKnowledge(comment);
			
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("评论失败！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if(count > 0){
			rsInfo.setErrorMessage("评论成功！");
			rsInfo.setResult(count);			
		}else{
			reInfo.setErrorMessage("评论失败！");
			reInfo.setErrorCode("10005");
			return reInfo;
			
		}
		

		return rsInfo;
	}
	
}
