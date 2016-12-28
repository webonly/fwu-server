package com.techsun.famouswine.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techsun.famouswine.domain.Appeal;
import com.techsun.famouswine.service.AppealService;
import com.techsun.famouswine.util.ResultErrorInfo;
import com.techsun.famouswine.util.ResultSuccessInfo;

@Controller
public class AppealController {
	private AppealService appealService;

	public AppealService getAppealService() {
		return appealService;
	}
	@Autowired
	public void setAppealService(AppealService appealService) {
		this.appealService = appealService;
	}
	
	
	
	
	@RequestMapping(value="/appeal/appealOrder")
	public @ResponseBody
	Object appealOrder(HttpServletRequest request,HttpSession session, 
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String userId = request.getParameter("userId");
		String orderId = request.getParameter("orderId");
		String appealType = request.getParameter("appealType");
		String content = request.getParameter("content");
		
		
		if(userId==null||userId.equals("")){
			reInfo.setErrorMessage("用户ID不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		
		if(orderId==null||orderId.equals("")){
			reInfo.setErrorMessage("订单ID不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		
		if(appealType==null||appealType.equals("")){
			reInfo.setErrorMessage("申诉类型不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		
		
		if(content==null||content.equals("")){
			reInfo.setErrorMessage("申诉内容不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
				
		
		Appeal appeal = new Appeal();
		appeal.setUserId(Integer.parseInt(userId));
		appeal.setOrderId(Integer.parseInt(orderId));
		appeal.setAppealType(Integer.parseInt(appealType));
		appeal.setContent(content);
		
		int count = 0;
		try {
			count = appealService.addAppeal(appeal);
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("添加地址失败！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if(count > 0){
			rsInfo.setErrorMessage("添加地址成功！");
			rsInfo.setResult(count);			
		}else{
			reInfo.setErrorMessage("添加地址失败！");
			reInfo.setErrorCode("10005");
			return reInfo;
			
		}
		

		return rsInfo;
	}
	
	
	
	@RequestMapping(value="/appeal/modifyAppeal")
	public @ResponseBody
	Object modifyAppeal(HttpServletRequest request,HttpSession session, 
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String orderId = request.getParameter("orderId");
		String result = request.getParameter("result");		
		

		
		if(orderId==null||orderId.equals("")){
			reInfo.setErrorMessage("订单ID不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		
		if(result==null||result.equals("")){
			reInfo.setErrorMessage("申诉结果不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}		
		
		Appeal appeal = new Appeal();
		appeal.setOrderId(Integer.parseInt(orderId));
		appeal.setResult(result);
		
		int count = 0;
		try {
			count = appealService.modifyAppeal(appeal);
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("添加地址失败！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if(count > 0){
			rsInfo.setErrorMessage("添加地址成功！");
			rsInfo.setResult(count);			
		}else{
			reInfo.setErrorMessage("添加地址失败！");
			reInfo.setErrorCode("10005");
			return reInfo;
			
		}
		

		return rsInfo;
	}
	
	
	
		//根据地址获取经度纬度
		@RequestMapping(value="/getAppealedOrderList")
		public @ResponseBody
		Object getAppealListByProductId(HttpServletRequest request,HttpSession session, 
				HttpServletResponse response){
			ResultSuccessInfo rsInfo = new ResultSuccessInfo();
			ResultErrorInfo reInfo = new ResultErrorInfo();
			String merchantId = request.getParameter("merchantId");
			String pageNum = request.getParameter("pageNum");
			String pageSize = request.getParameter("pageSize");
			
			if(merchantId==null||merchantId.equals("")){
				reInfo.setErrorMessage("商家ID不能为空！");
				reInfo.setErrorCode("10005");
				return reInfo;
			}
			if(pageNum==null||pageNum.equals("")){
				reInfo.setErrorMessage("页码不能为空！");
				reInfo.setErrorCode("10005");
				return reInfo;
			}
			if(pageSize==null||pageSize.equals("")){
				reInfo.setErrorMessage("页大小不能为空！");
				reInfo.setErrorCode("10005");
				return reInfo;
			}
			List<Appeal> appealList = null;
			try {
				appealList = appealService.getAppealOrderList(Integer.parseInt(merchantId), Integer.parseInt(pageNum), Integer.parseInt(pageSize));
			} catch (Exception e) {
				e.printStackTrace();
				reInfo.setErrorMessage("添加地址失败！");
				reInfo.setErrorCode("10005");
				return reInfo;
			}
			if(appealList == null){
				reInfo.setErrorMessage("添加地址失败！");
				reInfo.setErrorCode("10005");
				return reInfo;
				
			}
			
			rsInfo.setErrorMessage("添加地址成功！");
			rsInfo.setResult(appealList);	
			return rsInfo;
		}
		
		
}
