package com.techsun.famouswine.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techsun.famouswine.domain.Comment;
import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.service.CommentService;
import com.techsun.famouswine.util.ResultErrorInfo;
import com.techsun.famouswine.util.ResultSuccessInfo;

@Controller
public class CommentController {
	private CommentService commentService;

	public CommentService getCommentService() {
		return commentService;
	}
	@Autowired
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	} 
	
	
	
	//根据商品id获取商品评论
	@RequestMapping(value="/comment/getCommentList")
	public @ResponseBody
	Object getComment(HttpServletRequest request,HttpSession session, 
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		Integer productId = Integer.parseInt(request.getParameter("productId"));
		Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
		Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
		
		if(productId==null||productId.equals("")){
			reInfo.setErrorMessage("商品id不能为空！");
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
		List<Comment> commentList=new ArrayList<Comment>();
		try {
			commentList = commentService.getComment(productId,pageNum,pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("获取商品评价失败！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		rsInfo.setResult(commentList);
		rsInfo.setErrorMessage("获取商品评价成功！");
		return rsInfo;
	}
	//添加评论
	@RequestMapping(value="/comment/addComment")
	public @ResponseBody
	Object addComment(HttpServletRequest request,HttpSession session, 
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String userId = request.getParameter("userId");
		String merchantId = request.getParameter("merchantId");
		String productId = request.getParameter("productId");
		String merchantScore = request.getParameter("merchantScore");
		String merchantContent = request.getParameter("merchantContent");
		String productScore = request.getParameter("productScore");
		String productContent = request.getParameter("productContent");
		String serveScore = request.getParameter("serveScore");
		if(userId==null||userId.equals("")){
			reInfo.setErrorMessage("获取用户信息失败！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		if(productId==null||productId.equals("")){
			reInfo.setErrorMessage("获取商品信息失败！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		if(merchantId==null||merchantId.equals("")){
			reInfo.setErrorMessage("获取商家信息失败！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		if(merchantScore==null||merchantScore.equals("")){
			reInfo.setErrorMessage("店铺评分为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		if(merchantContent==null||merchantContent.equals("")){
			reInfo.setErrorMessage("店铺评价为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		if(productScore==null||productScore.equals("")){
			reInfo.setErrorMessage("商品评分为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		if(productContent==null||productContent.equals("")){
			reInfo.setErrorMessage("商品评价为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		if(serveScore==null||serveScore.equals("")){
			reInfo.setErrorMessage("服务评价为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		int count=0;
		Comment comment=null;
		try {
			comment = new Comment();
			comment.setUserId(Integer.parseInt(userId));
			comment.setProductId(Integer.parseInt(productId));
			comment.setMerchantId(Integer.parseInt(merchantId));
			comment.setProductContent(productContent);
			comment.setProductScore(Double.parseDouble(productScore));
			comment.setMerchantScore(Double.parseDouble(merchantScore));
			comment.setMerchantContent(merchantContent);
			comment.setServeScore(Double.parseDouble(serveScore));
			count = commentService.addComment(comment);
			if(count<0){
				reInfo.setErrorMessage("添加评论失败！");
				reInfo.setErrorCode("10007");
				return reInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("添加评论失败！");
			reInfo.setErrorCode("10007");
			return reInfo;
		}
		rsInfo.setResult(comment);
		rsInfo.setErrorMessage("添加评论成功！");
		return rsInfo;
	}
}
