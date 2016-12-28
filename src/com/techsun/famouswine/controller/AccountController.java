package com.techsun.famouswine.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techsun.famouswine.domain.Account;
import com.techsun.famouswine.service.AccountService;
import com.techsun.famouswine.util.ResultErrorInfo;
import com.techsun.famouswine.util.ResultSuccessInfo;

@Controller
public class AccountController {
	private AccountService accountService;

	
	public AccountService getAccountService() {
		return accountService;
	}
	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	//获取所有账户
	@RequestMapping(value="/account/getAccountList")
	public @ResponseBody
	Object getAccountList(HttpServletRequest request,HttpSession session, 
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String userId = request.getParameter("userId");
		String userType = request.getParameter("userType");
		if(userId==null||userId.equals("")){
			reInfo.setErrorMessage("获取用户信息失败！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		
		if(userType==null||userType.equals("")){
			reInfo.setErrorMessage("获取用户类型失败！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		List<Account> accountList=null;
		try {
			accountList=accountService.getAccountList(Integer.parseInt(userId), Integer.parseInt(userType));
			if(accountList.size()<0){
				reInfo.setErrorMessage("用户未绑定账户！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("获取账户信息失败！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		rsInfo.setResult(accountList);
		rsInfo.setErrorMessage("查询成功！");
		return rsInfo;
	}
	
	//设置默认账户
	@RequestMapping(value="/account/setDefaultAccount")
	public @ResponseBody
	Object setDefaultAccount(HttpServletRequest request,HttpSession session, 
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String userId = request.getParameter("userId");
		String accountId = request.getParameter("accountId");
		String userType = request.getParameter("userType");
		if(userId==null||userId.equals("")){
			reInfo.setErrorMessage("获取用户信息失败！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		if(accountId==null||accountId.equals("")){
			reInfo.setErrorMessage("获取账户信息失败！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		
		if(userType==null||userType.equals("")){
			reInfo.setErrorMessage("获取用户类型失败！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		int count = 0;
		try {
			count=accountService.setDefaultAccount(Integer.parseInt(userId), Integer.parseInt(userType),Integer.parseInt(accountId));
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("获取账户信息失败！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		rsInfo.setResult(count);
		rsInfo.setErrorMessage("设置默认账户成功！");
		return rsInfo;
	}
	
	//添加账户
	@RequestMapping(value="/account/addAccount")
	public @ResponseBody
	Object addAccount(HttpServletRequest request,HttpSession session, 
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String userId=request.getParameter("userId");
		String account=request.getParameter("account");
		String userType=request.getParameter("userType");
		String bank = request.getParameter("bank");
		
		if(userId==null||userId.equals("")){
			reInfo.setErrorMessage("用户ID不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		if(account==null||account.equals("")){
			reInfo.setErrorMessage("账号不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		
		if(userType==null||userType.equals("")){
			reInfo.setErrorMessage("用户类型不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		if(bank==null||bank.equals("")){
			reInfo.setErrorMessage("银行名称不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		
		Account acc=new Account();
		acc.setAccount(account);
		acc.setUserId(Integer.parseInt(userId));
		acc.setUserType(Integer.parseInt(userType));
		acc.setBank(bank);
		int count=0;
		try {
			count = accountService.addAccount(acc);
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("添加账户失败！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		rsInfo.setResult(count);
		rsInfo.setErrorMessage("添加账户成功！");
		return rsInfo;
	}
	
	
	
	
	/*//删除账户
		@RequestMapping(value="/account/deleteAccount")
		public @ResponseBody
		Object deleteAccount(HttpServletRequest request,HttpSession session, 
				HttpServletResponse response){
			ResultSuccessInfo rsInfo = new ResultSuccessInfo();
			ResultErrorInfo reInfo = new ResultErrorInfo();
			String userId=request.getParameter("userId");
			String accountId=request.getParameter("accountId");
			
			if(userId==null||userId.equals("")){
				reInfo.setErrorMessage("用户ID不能为空！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
			if(accountId==null||accountId.equals("")){
				reInfo.setErrorMessage("账号ID不能为空！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
			
			int count=0;
			try {
				count = accountService.deleteAccount(Integer.parseInt(userId),Integer.parseInt(accountId));
			} catch (Exception e) {
				e.printStackTrace();
				reInfo.setErrorMessage("删除账户失败！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
			rsInfo.setResult(count);
			rsInfo.setErrorMessage("删除账户成功！");
			return rsInfo;
		}
	*/
	//获取默认账户
	@RequestMapping(value="/account/getDefaultAccount")
	public @ResponseBody
	Object getDefaultAccount(HttpServletRequest request,HttpSession session, 
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String userId=request.getParameter("userId");
		String userType=request.getParameter("userType");
		if(userId==null||userId.equals("")){
			reInfo.setErrorMessage("获取用户信息失败！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		
		if(userType==null||userType.equals("")){
			reInfo.setErrorMessage("获取用户类型失败！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		Account account = null;
		try {
			account=accountService.getDefaultAccount(Integer.parseInt(userId), Integer.parseInt(userType));
			if(account ==null){
				reInfo.setErrorMessage("获取账户信息失败！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("获取默认账户失败！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		rsInfo.setResult(account);
		rsInfo.setErrorMessage("获取默认账户成功！");
		return rsInfo;
	}
} 
