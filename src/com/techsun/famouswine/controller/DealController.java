package com.techsun.famouswine.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techsun.famouswine.domain.Balance;
import com.techsun.famouswine.domain.BalanceDeal;
import com.techsun.famouswine.domain.Deal;
import com.techsun.famouswine.service.BalanceService;
import com.techsun.famouswine.service.DealService;
import com.techsun.famouswine.util.ResultErrorInfo;
import com.techsun.famouswine.util.ResultSuccessInfo;

@Controller("DealController")
public class DealController {
	private DealService dealService;

	public DealService getDealService() {
		return dealService;
	}
	@Autowired
	public void setDealService(DealService dealService) {
		this.dealService = dealService;
	}
	
	
	private BalanceService balanceService;	

	public BalanceService getBalanceService() {
		return balanceService;
	}
	@Autowired
	public void setBalanceService(BalanceService balanceService) {
		this.balanceService = balanceService;
	}
	
	@RequestMapping(value="/deal/getBalanceDeal")
	public @ResponseBody
	Object getBalanceDeal(HttpServletRequest request,
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String userId = request.getParameter("userId");
		String userType = request.getParameter("userType");
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		if(userId==null||userId.equals("")){
			reInfo.setErrorMessage("获取用户信息错误！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if(userType==null||userType.equals("")){
			reInfo.setErrorMessage("用户类型错误！");
			reInfo.setErrorCode("10005");
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
		
		BalanceDeal bd = new BalanceDeal();
		
		Balance balance = null;
		
		try {
			
			balance = balanceService.getBalance(Integer.parseInt(userId), Integer.parseInt(userType));
			
		}catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("获取余额失败！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if(balance == null){
			
			reInfo.setErrorMessage("你的交易为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
			
		}
		
		
		List<Deal> dealList = null;
		try {

			
			dealList = dealService.getAllDealList(Integer.parseInt(userId), 
					Integer.parseInt(userType),Integer.parseInt(pageNum),
					Integer.parseInt(pageSize));
			if(dealList==null){
				rsInfo.setErrorMessage("交易记录为空！");
				rsInfo.setResult(dealList);
				return rsInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("获取交易记录失败！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		bd.setBalance(balance.getBalance());
		bd.setDealList(dealList);
		
		rsInfo.setErrorMessage("获取交易记录成功！");
		rsInfo.setResult(bd);
		return rsInfo;
	}
	
	
	@RequestMapping(value="/deal/getDealList")
	public @ResponseBody
	Object getDealList(HttpServletRequest request,
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String userId = request.getParameter("userId");
		String userType = request.getParameter("userType");
		String dealType = request.getParameter("dealType");
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		if(userId==null||userId.equals("")){
			reInfo.setErrorMessage("获取用户信息错误！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if(userType==null||userType.equals("")){
			reInfo.setErrorMessage("用户类型错误！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if(dealType==null||dealType.equals("")){
			reInfo.setErrorMessage("操作类型错误！");
			reInfo.setErrorCode("10005");
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
		
		
		
		
		List<Deal> dealList = null;
		try {
			dealList = dealService.getDealListByDealType(Integer.parseInt(userId), 
					Integer.parseInt(userType),Integer.parseInt(dealType),Integer.parseInt(pageNum),
					Integer.parseInt(pageSize));
			if(dealList==null){
				rsInfo.setErrorMessage("交易记录为空！");
				rsInfo.setResult(dealList);
				return rsInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("获取交易记录失败！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		
		rsInfo.setErrorMessage("获取交易记录成功！");
		rsInfo.setResult(dealList);
		return rsInfo;
	}
}
