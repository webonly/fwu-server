package com.techsun.famouswine.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techsun.famouswine.domain.Feedback;
import com.techsun.famouswine.domain.Merchant;
import com.techsun.famouswine.service.FeedBackService;
import com.techsun.famouswine.service.MerchantService;
import com.techsun.famouswine.util.ResultErrorInfo;
import com.techsun.famouswine.util.ResultSuccessInfo;


@Controller
public class FeedbackController {
	
	private FeedBackService feedbackService;

	public FeedBackService getFeedbackService() {
		return feedbackService;
	}
	@Autowired
	public void setFeedbackService(FeedBackService feedbackService) {
		this.feedbackService = feedbackService;
	}
	
	private MerchantService merchantService;
	
	
	public MerchantService getMerchantService() {
		return merchantService;
	}
	@Autowired
	public void setMerchantService(MerchantService merchantService) {
		this.merchantService = merchantService;
	}

	
	@RequestMapping(value = "/feedback/feedback")
	public @ResponseBody
	Object feedback(HttpServletRequest request, HttpSession session) {

		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		String content = request.getParameter("content");
		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		
		if(userId==null || userId.equals("")){
			reInfo.setErrorMessage("用户id不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		
		if (name == null || name.equals("")) {
			reInfo.setErrorMessage("用户名不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		
		if (content == null || content.equals("")) {
			reInfo.setErrorMessage("内容不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;
		} 
		
		if (phone == null || phone.equals("")) {
			reInfo.setErrorMessage("电话不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		
		if (type == null || type.equals("")) {
			reInfo.setErrorMessage("类型为空！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		
		Feedback feedback=new Feedback();
		feedback.setPhone(phone);
		feedback.setName(name);
		feedback.setContent(content);
		feedback.setUserType(Integer.parseInt(type));
		feedback.setUserId(userId);
		try {
			int count=feedbackService.addFeedBack(feedback);
			if(count>0){
				reInfo.setErrorMessage("商户反馈成功！");
				rsInfo.setResult(count);
			}else{
				reInfo.setErrorMessage("商户反馈失败！");
				reInfo.setErrorCode("10000");
				return reInfo;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("获取用户出错！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		return rsInfo;
	}
	

}
