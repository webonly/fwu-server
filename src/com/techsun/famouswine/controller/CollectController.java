package com.techsun.famouswine.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techsun.famouswine.domain.Collect;
import com.techsun.famouswine.domain.Merchant;
import com.techsun.famouswine.domain.Product;
import com.techsun.famouswine.service.CollectService;
import com.techsun.famouswine.util.ResultErrorInfo;
import com.techsun.famouswine.util.ResultSuccessInfo;


@Controller
public class CollectController {


	private CollectService collectService;

	public CollectService getCollectService() {
		return collectService;
	}

	@Autowired
	public void setCollectService(CollectService collectService) {
		this.collectService = collectService;
	}
	
	
	@RequestMapping(value = "/collect/collectProduct")
	public @ResponseBody
	Object collectProduct(HttpServletRequest request, HttpServletResponse response) {
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String userId = request.getParameter("userId");	
		String collectedId = request.getParameter("productId");			
		String type = request.getParameter("userType");
		if(userId==null||userId.equals("")){
			reInfo.setErrorMessage("用户ID不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		
		
		if(type==null||type.equals("")){
			reInfo.setErrorMessage("活动类型不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		
		if(collectedId==null||collectedId.equals("")){
			reInfo.setErrorMessage("商家ID不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		
		Collect collect = new Collect();
		collect.setUserId(Integer.parseInt(userId));
		collect.setCollectedId(Integer.parseInt(collectedId));
		collect.setType(Integer.parseInt(type));
		
		
		int count = 0;
		try {
			count = collectService.collect(collect);
			

		} catch (Exception e) {
			reInfo.setErrorMessage("收藏失败！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		if (count > 0) {
			rsInfo.setResult(count);
			rsInfo.setErrorMessage("收藏成功！");
		}else{
			reInfo.setErrorMessage("收藏失败！");
			reInfo.setErrorCode("10003");
			return reInfo;
			
		}
		
		return rsInfo;
	}
	
	@RequestMapping(value = "/collect/getMyCollect")
	public @ResponseBody
	Object getMyCollect(HttpServletRequest request, HttpServletResponse response) {
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String userId = request.getParameter("userId");	
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String type = request.getParameter("type");
		
		if(userId==null||userId.equals("")){
			reInfo.setErrorMessage("用户ID不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		
		
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
		
		if(Integer.parseInt(type) == 1){
		
		List<Product> productList = null;
		try {
			productList = collectService.getMyCollectProduct(Integer.parseInt(userId), Integer.parseInt(pageNum), Integer.parseInt(pageSize));
			

		} catch (Exception e) {
			reInfo.setErrorMessage("收藏失败！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
				
		
		if (productList != null) {
			rsInfo.setResult(productList);
			rsInfo.setErrorMessage("收藏成功！");
		}else{
			reInfo.setErrorMessage("收藏失败！");
			reInfo.setErrorCode("10003");
			return reInfo;
			
		}
		
		}else if(Integer.parseInt(type) == 2){
			List<Merchant> merchantList = null;
			try {
				merchantList = collectService.getMyCollectMerchant(Integer.parseInt(userId), Integer.parseInt(pageNum), Integer.parseInt(pageSize));
				

			} catch (Exception e) {
				reInfo.setErrorMessage("收藏失败！");
				reInfo.setErrorCode("10003");
				return reInfo;
			}
					
			
			if (merchantList != null) {
				rsInfo.setResult(merchantList);
				rsInfo.setErrorMessage("收藏成功！");
			}else{
				reInfo.setErrorMessage("收藏失败！");
				reInfo.setErrorCode("10003");
				return reInfo;
				
			}	
			
			
		}
		
		return rsInfo;
	}
	
	@RequestMapping(value = "/collect/collectMerchant")
	public @ResponseBody
	Object collectMerchant(HttpServletRequest request, HttpServletResponse response) {
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String userId = request.getParameter("userId");	
		String collectedId = request.getParameter("merchantId");		
		String userType = request.getParameter("userType");
		
		if(userId==null||userId.equals("")){
			reInfo.setErrorMessage("用户ID不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		
		
		if(userType==null||userType.equals("")){
			reInfo.setErrorMessage("活动类型不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		
		if(collectedId==null||collectedId.equals("")){
			reInfo.setErrorMessage("商家ID不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		
		Collect collect = new Collect();
		collect.setUserId(Integer.parseInt(userId));
		collect.setCollectedId(Integer.parseInt(collectedId));
		collect.setType(Integer.parseInt(userType));
		int count = 0;
		try {
			count = collectService.collect(collect);
			

		} catch (Exception e) {
			reInfo.setErrorMessage("收藏失败！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		if (count > 0) {
			rsInfo.setResult(count);
			rsInfo.setErrorMessage("收藏成功！");
		}else{
			reInfo.setErrorMessage("收藏失败！");
			reInfo.setErrorCode("10003");
			return reInfo;
			
		}

		return rsInfo;
	}
	
	
	
	
	
}
