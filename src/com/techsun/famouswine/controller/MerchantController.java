package com.techsun.famouswine.controller;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techsun.famouswine.domain.Balance;
import com.techsun.famouswine.domain.CRCode;
import com.techsun.famouswine.domain.Deal;
import com.techsun.famouswine.domain.DistanceMerchant;
import com.techsun.famouswine.domain.Merchant;
import com.techsun.famouswine.service.BalanceService;
import com.techsun.famouswine.service.DealService;
import com.techsun.famouswine.service.MerchantService;
import com.techsun.famouswine.util.Location;
import com.techsun.famouswine.util.ResultErrorInfo;
import com.techsun.famouswine.util.ResultSuccessInfo;
import com.techsun.famouswine.util.SendSmsUtil;

@Controller
public class MerchantController {

	  private MerchantService merchantService;

	public MerchantService getMerchantService() {
		return merchantService;
	}

	@Autowired
	public void setMerchantService(MerchantService merchantService) {
		this.merchantService = merchantService;
	}

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
	
	@RequestMapping(value = "/merchant/getMerchantInfo")
	public @ResponseBody
	Object getMerchantInfo(HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Integer merchantId = Integer.parseInt(request.getParameter("merchantId"));
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		DistanceMerchant merchant = null;
		try {
			merchant = merchantService.getMerchantInfo(merchantId);
			if(merchant==null){
				reInfo.setErrorMessage("没有此商家！");
				reInfo.setErrorCode("10001");
				return reInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("获取商家信息失败");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		rsInfo.setErrorMessage("获取商家信息成功！");
		rsInfo.setResult(merchant);
		return rsInfo;
	}
	
	//搜商户
	@RequestMapping(value = "/merchant/getByMerchantName")
	public @ResponseBody
	Object searchByMerchantName(HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String merchantName = request.getParameter("merchantName");
		String distance = request.getParameter("distance");
		String userLon = request.getParameter("userLon");
		String userLat = request.getParameter("userLat");
		Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
		Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
		Integer distances;
		String name;
		if(merchantName==null||merchantName.equals("")){
			name=null;
		}else{
			name = merchantName;
		}
		
		if(userLon==null||userLon.equals("")){
			reInfo.setErrorMessage("获取用户经度失败！");
			reInfo.setErrorCode("10000");
			return reInfo;
		}
		if(userLat==null||userLat.equals("")){
			reInfo.setErrorMessage("获取用户纬度失败！");
			reInfo.setErrorCode("10000");
			return reInfo;
		}
		if(pageNum == null || pageNum.equals("")){
			reInfo.setErrorMessage("页码不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		if(pageSize == null || pageSize.equals("")){
			reInfo.setErrorMessage("页大小不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		if(distance==null||distance.equals(""))
			distances=3001;
		else
			distances = Integer.parseInt(distance);
		List<DistanceMerchant>  merchantList=null;
		try {
			merchantList=merchantService.searchByMerchantName(name,Double.parseDouble(userLon),Double.parseDouble(userLat),distances,pageNum,pageSize);
			if(merchantList.size()==0){
				reInfo.setErrorMessage("商家为空！");
				reInfo.setErrorCode("10001");
				return reInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("获取商家信息失败");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		
		rsInfo.setErrorMessage("获取商家信息成功！");
		rsInfo.setResult(merchantList);
		return rsInfo;
	}
	
	
	
	
	

	
	
	/**
	 * 
	 * <p>
	 * Description: 商户登录
	 * </p>
	 * 
	 * @author LW
	 * @date 2015-11-12 
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @version 1.0
	 */
	@RequestMapping(value = "/merchant/login")
	public @ResponseBody
	Object login(HttpServletRequest request, HttpServletResponse response) {
		String userName;
		String password;
		response.addHeader("Access-Control-Allow-Origin", "*");
		userName = request.getParameter("userName");
		password = request.getParameter("password");
		
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();

		if (userName == null || userName.equals("")) {
			reInfo.setErrorMessage("用户名不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;

		}

		if (password == null || password.equals("")) {
			reInfo.setErrorMessage("密码不能为空！");
			reInfo.setErrorCode("10002");
			return reInfo;
		}
		Merchant merchant = null;
		try {
			merchant = merchantService.getMerchantByUserName(userName);

		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("登录出错！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		if (merchant != null) {
			if (password.equals(merchant.getPassword())) {
				rsInfo.setResult(merchant);
				rsInfo.setErrorMessage("登录成功！");

			} else {

				reInfo.setErrorMessage("密码不正确！");
				reInfo.setErrorCode("10003");
				return reInfo;
			}

		} else {

			reInfo.setErrorMessage("用户不存在！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}

		return rsInfo;
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:注册
	 * </p>
	 * 
	 * @author LW
	 * @date 2015-11-12 
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @version 1.0
	 */
	@RequestMapping(value = "/merchant/register")
	public @ResponseBody
	Object register(HttpServletRequest request, HttpSession session,
			HttpServletResponse response) {
		ResultErrorInfo reInfo = new ResultErrorInfo();
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		response.addHeader("Access-Control-Allow-Origin", "*");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String confirmPwd = request.getParameter("confirmPwd");
		String authcode = request.getParameter("authcode");

		if (userName == null || userName.equals("")) {
			reInfo.setErrorMessage("用户名不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;

		}

		if (password == null || password.equals("")) {
			reInfo.setErrorMessage("密码不能为空！");
			reInfo.setErrorCode("10002");
			return reInfo;
		}
		
		if (confirmPwd == null || confirmPwd.equals("")) {
			reInfo.setErrorMessage("确认密码不能为空！");
			reInfo.setErrorCode("10002");
			return reInfo;
		}

		
		if (authcode == null || authcode.equals("")) {
			reInfo.setErrorMessage("验证码不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;

		}
		
		
		//密码校验
		if(!password.equals(confirmPwd)){
			rsInfo.setErrorMessage("登录密码和确认密码不一致");
			return rsInfo;
		}

		//验证用户是否已经存在
		Merchant merchant = null;
		try {
			merchant = merchantService.getMerchantByUserName(userName);

		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("获取用户出错！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		if (merchant != null) {
			reInfo.setErrorMessage("用户已经注册了！");
			reInfo.setErrorCode("10003");
			return reInfo;
			
		}
		
		// 验证码校验
		CRCode codes = (CRCode) session.getAttribute(userName);
		if (codes != null) {
			

			long nowtime = System.currentTimeMillis();
			long pasttime = codes.getSendDate().getTime();

			if ((nowtime - pasttime) > 1800000) {
				rsInfo.setErrorMessage("验证码已过期");
				return rsInfo;
			}else {
				rsInfo.setErrorMessage("验证码可以使用");
				
			}
		}
		

		
		merchant = new Merchant();
		merchant.setUserName(userName);
		merchant.setPassword(password);
		merchant.setCreatedDate(new Date());

		// ActionContext.getContext().getSession().put("merchant", merchant);

		int count = 0;
		try {
			count = merchantService.addMerchant(merchant);

		} catch (Exception e) {
			reInfo.setErrorMessage("注册失败！");
			reInfo.setErrorCode("10000");
			return reInfo;
		}
		
		if(count <= 0){
			reInfo.setErrorMessage("注册失败！");
			reInfo.setErrorCode("10000");
			return reInfo;
			
		}

		rsInfo.setErrorMessage("注册成功！");
		rsInfo.setResult(merchant);

		return rsInfo;
	}
	
	
	

	
	/**
	 * 
	 * <p>
	 * Description:修改密码
	 * </p>
	 * 
	 * @author LW
	 * @date 2015-11-12 
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @version 1.0
	 */
	@RequestMapping(value = "/merchant/modifyPassword")
	public @ResponseBody
	Object modifyPassword(HttpServletRequest request, HttpSession session) {

		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();

		String userName = request.getParameter("userName");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		if (userName == null || userName.equals("")) {
			reInfo.setErrorMessage("商家的用户名不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		if (oldPassword == null || oldPassword.equals("")) {
			reInfo.setErrorMessage("旧密码不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		if (newPassword == null || newPassword.equals("")) {
			reInfo.setErrorMessage("新密码不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		CRCode codes = (CRCode) session.getAttribute(userName);
		if (codes != null) {
			

			long nowtime = System.currentTimeMillis();
			long pasttime = codes.getSendDate().getTime();

			if ((nowtime - pasttime) > 1800000) {
				rsInfo.setErrorMessage("验证码已过期");
				return rsInfo;
			}else {
				rsInfo.setErrorMessage("验证码可以使用");
				
			}
		}
		
		Merchant merchant = null;
		try {
			merchant = merchantService.getMerchantByUserName(userName);

		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("获取用户出错！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		if (merchant != null) {
			if (oldPassword.equals(merchant.getPassword())) {
				int count;
				merchant.setPassword(newPassword);
				try {
					count = merchantService.modifyMerchant(merchant);
				} catch (Exception e) {
					reInfo.setErrorMessage("修改用户密码出错！");
					reInfo.setErrorCode("10003");
					return reInfo;
				}
				if (count > 0) {
					rsInfo.setResult(count);
					rsInfo.setErrorMessage("修改用户密码成功");
				}

			} else {

				reInfo.setErrorMessage("旧密码不正确！");
				reInfo.setErrorCode("10003");     
				return reInfo;
			}

		} else {

			reInfo.setErrorMessage("用户不存在！");
			reInfo.setErrorCode("10003");
			return reInfo;

		}

		return rsInfo;
	}

	
	/**
	 * 
	 * <p>
	 * Description:找回密码
	 * </p>
	 * 
	 * @author LW
	 * @date 2015-11-12 
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @version 1.0
	 */
	@RequestMapping(value = "/merchant/findPassword")
	public @ResponseBody
	Object findPassword(HttpServletRequest request, HttpSession session) {

		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();

		String newPwd = request.getParameter("newPwd");
		String confirmPwd = request.getParameter("confirmPwd");
		String userName = request.getParameter("userName");
		String authcode = request.getParameter("authcode");
	
		if (newPwd == null || newPwd.equals("")) {
			reInfo.setErrorMessage("新密码不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		if (confirmPwd == null || confirmPwd.equals("")) {
			reInfo.setErrorMessage("确认密码不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		if (authcode == null || authcode.equals("")) {
			reInfo.setErrorMessage("验证码不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		
		if(!newPwd.equals(confirmPwd)){
			reInfo.setErrorMessage("输入密码不一致！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		
		
		
		//不理解待问
		CRCode codes = (CRCode) session.getAttribute(authcode);
		if (codes != null) {
			

			long nowtime = System.currentTimeMillis();
			long pasttime = codes.getSendDate().getTime();

			if ((nowtime - pasttime) > 1800000) {
				rsInfo.setErrorMessage("验证码已过期");
				return rsInfo;
			}else {
				rsInfo.setErrorMessage("验证码可以使用");
				
			}
		}
		
		Merchant merchantPhone = null;
		try {
			merchantPhone = merchantService.getMerchantByUserName(userName);

		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("获取商家信息错误！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		if (merchantPhone != null) {
			merchantPhone.setPassword(newPwd);
			int count = merchantService.modifyMerchant(merchantPhone);
			if(count<0){
				reInfo.setErrorMessage("设置密码错误！");
				reInfo.setErrorCode("10000");
				return reInfo;
			}
		} else {

			reInfo.setErrorMessage("获取商家信息错误！");
			reInfo.setErrorCode("10003");
			return reInfo;

		}
		rsInfo.setErrorMessage("设置密码成功！");
		rsInfo.setResult(merchantPhone);
		return rsInfo;
	}
	
	
	
	/**
	 * 
	 * <p>
	 * Description: 查询商户信息
	 * </p>
	 * 
	 * @author LW
	 * @date 2015-11-17
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @version 1.0
	 */
	@RequestMapping(value = "/obtainMerchantNameList")
	public @ResponseBody
	Object doObtainMerchantNameList(HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		String merchantName = request.getParameter("merchantName");
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		List<Merchant>  MerchantList= merchantService.getMerchantNameList(merchantName);
		if (MerchantList != null&&MerchantList.size()!=0) {
			rsInfo.setResult(MerchantList);
			rsInfo.setErrorMessage("获取用户信息成功");
		} else {
			reInfo.setErrorMessage("获取用户信息失败");
			rsInfo.setResult(null);
		}

		return rsInfo;
	}
	
	
	@RequestMapping(value = "/getMerchantAuthCode ")
	protected @ResponseBody
	Object getMerchantAuthCode(HttpServletRequest request, HttpSession session) {
		StringBuilder code = new StringBuilder();
		String mobile = request.getParameter("merchantName");
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();

		if (mobile == null || mobile.equals("")) {
			reInfo.setErrorMessage("用户名不能为空!");
			reInfo.setErrorCode("10001");
			return reInfo;

		}
		CRCode codes = (CRCode) session.getAttribute(mobile);
		if (codes!=null ) {
			

			long nowtime = System.currentTimeMillis();
			long pasttime = codes.getSendDate().getTime();

			if ((nowtime - pasttime) > 1800000) {
				Random random = new Random();

				// 4位验证码
				for (int i = 0; i < 4; i++) {
					code.append(String.valueOf(random.nextInt(10)));
				}
				// session.setAttribute(mobile + "", mobile);

				/*
				 * session.setAttribute(mobile + "crcode", code.toString());
				 * session.setAttribute(mobile + "date", new Date().getTime());
				 * String smsText = "验证码：" + code; System.out.println("手机号：" +
				 * mobile + ", " + smsText);
				 */
				Date currentTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String dateString = formatter.format(currentTime);
				ParsePosition pos = new ParsePosition(0);
				Date currentTimeTwo = formatter.parse(dateString, pos);
				CRCode crCode = new CRCode();
				crCode.setCrCode(code.toString());
				crCode.setMobile(mobile);
				crCode.setSendDate(currentTimeTwo);

				SendSmsUtil.sendSms(mobile, code.toString());

				session.setAttribute(mobile, crCode);

				rsInfo.setResult("1");
				rsInfo.setErrorMessage("获取验证码成功！");
				return rsInfo;
			} else {
				SendSmsUtil.sendSms(mobile, codes.getCrCode());
				rsInfo.setResult("2");
				rsInfo.setErrorMessage("获取验证码成功！");
				return rsInfo;
			}

		}

		Random random = new Random();

		// 4位验证码
		for (int i = 0; i < 4; i++) {
			code.append(String.valueOf(random.nextInt(10)));
		}
		// session.setAttribute(mobile + "", mobile);

		/*
		 * session.setAttribute(mobile + "crcode", code.toString());
		 * session.setAttribute(mobile + "date", new Date().getTime()); String
		 * smsText = "验证码：" + code; System.out.println("手机号：" + mobile + ", " +
		 * smsText);
		 */
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(0);
		Date currentTimeTwo = formatter.parse(dateString, pos);
		CRCode crCode = new CRCode();
		crCode.setCrCode(code.toString());
		crCode.setMobile(mobile);
		crCode.setSendDate(currentTimeTwo);

		SendSmsUtil.sendSms(mobile, code.toString());

		session.setAttribute(mobile, crCode);

		rsInfo.setResult("3");
		rsInfo.setErrorMessage("获取验证码成功！");
		return rsInfo;

	}
	

	/*//修改余额
		@RequestMapping(value = "/merchant/modifyBalance")
		public @ResponseBody
		Object modifyBalance(HttpServletRequest request, HttpServletResponse response) {
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String merchantId =request.getParameter("merchantId");
		String money = request.getParameter("money");
		String type = request.getParameter("type");
		if(merchantId==null||merchantId.equals("")){
			reInfo.setErrorMessage("获取商家信息失败！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if(merchantId==null||merchantId.equals("")){
			reInfo.setErrorMessage("获取商家信息失败！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if (money==null||money.equals("")) {
			reInfo.setErrorMessage("获取金额错误！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		Merchant merchant=null;
		int count=0;
		merchant=merchantService.getMerchantInfo(Integer.parseInt(merchantId));
		if(merchant==null){
			reInfo.setErrorMessage("获取商家信息失败！");
			reInfo.setErrorCode("10000");
			return reInfo;
		}
		if(type.equals("1")){
				try {
					float balance=merchant.getBalance();
					balance+=Integer.parseInt(money);
					merchant.setBalance(balance);
					count=merchantService.modifyMerchant(merchant);
					if(count>0){
						rsInfo.setErrorMessage("余额充值成功！");
						rsInfo.setResult(count);
						return rsInfo;
					}
				} catch (Exception e) {
					e.printStackTrace();
					reInfo.setErrorMessage("操作错误！");
					reInfo.setErrorCode("10004");
					return reInfo;
				}
				//1充值，2支付，3提现
		}else if(type.equals("2")||type.equals("3")){
				try {
					if (type.equals("2")
							&& merchant.getBalance() < Integer.parseInt(money)) {
						reInfo.setErrorMessage("余额不足不能支付，请充值！");
						reInfo.setErrorCode("10005");
						return reInfo;
					} else {
						float balance = merchant.getBalance();
						balance -= Integer.parseInt(money);
						merchant.setBalance(balance);
						count = merchantService.modifyMerchant(merchant);
						if (count > 1) {
							rsInfo.setErrorMessage("余额支付成功！");
							return rsInfo;
						}
					}
					
					if (type.equals("3")&& merchant.getBalance() < Integer.parseInt(money)) {
						reInfo.setErrorMessage("余额不足不能提现，请充值！");
						reInfo.setErrorCode("10005");
						return reInfo;
					} else {
						float balance = merchant.getBalance();
						balance -= Integer.parseInt(money);
						merchant.setBalance(balance);
						count = merchantService.modifyMerchant(merchant);
						if (count > 1) {
							rsInfo.setErrorMessage("余额提现成功！");
							return rsInfo;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					reInfo.setErrorMessage("操作错误！");
					reInfo.setErrorCode("10004");
					return reInfo;
				}
			}else{
				reInfo.setErrorMessage("操作错误！");
				reInfo.setErrorCode("10004");
				return reInfo;
			}
		
		return reInfo;
	
	}*/
	
	
	//修改余额
	@RequestMapping(value = "/merchant/modifyBalance")
	public @ResponseBody
	Object ModifyBalance(HttpServletRequest request) {
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String merchantId =request.getParameter("merchantId");			
		String money = request.getParameter("money");
		String type = request.getParameter("type");
		Deal deal = null;
		int count=0;
		if(merchantId==null||merchantId.equals("")){
			reInfo.setErrorMessage("商家ID不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if (money==null||money.equals("")) {
			reInfo.setErrorMessage("输入金额错误！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if(type==null||type.equals("")){
			reInfo.setErrorMessage("金额类型错误！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		
		try {
			Balance balance = balanceService.getBalance(Integer.parseInt(merchantId), 2);
			
			if(balance == null){
				balance = new Balance();
				if(type.equals("1")){
					balance.setUserId(Integer.parseInt(merchantId));
					balance.setUserType(2);
					balance.setBalance(Double.parseDouble(money));
					count = balanceService.addBalance(Integer.parseInt(merchantId),2, Double.parseDouble(money));
					if(count <= 0){
						reInfo.setErrorMessage("修改余额出错！");
						reInfo.setErrorCode("10005");
						return reInfo;
						
					}
				
				}else if(type.equals("2")){
					reInfo.setErrorMessage("余额不足，不能提现！");
					reInfo.setErrorCode("10005");
					return reInfo;
					
				}else if(type.equals("3")){
					reInfo.setErrorMessage("余额不足，不能支付！");
					reInfo.setErrorCode("10005");
					return reInfo;
				}
				
			}else {
				
			double balanceValue = balance.getBalance();
			if(type.equals("1")){
				balanceValue += Double.parseDouble(money);//充值
			}else if(type.equals("2")){
				if(balanceValue >= Double.parseDouble(money)){
					balanceValue-=Double.parseDouble(money);//提现
				}else{
					reInfo.setErrorMessage("余额不足，不能提现！");
					reInfo.setErrorCode("10005");
					return reInfo;
				}
			}else if(type.equals("3")){
				if(balanceValue>=Double.parseDouble(money)){
					balanceValue-=Double.parseDouble(money);//支付
				}else{
					reInfo.setErrorMessage("余额不足，不能支付！");
					reInfo.setErrorCode("10005");
					return reInfo;
				}
			}
			
			count = balanceService.modifyBalance(Integer.parseInt(merchantId), 2, balanceValue);
			if(count <= 0){
				reInfo.setErrorMessage("修改余额出错！");
				reInfo.setErrorCode("10005");
				return reInfo;
				
			}
			
			}
			

			
			deal = new Deal();
			deal.setUserId(Integer.parseInt(merchantId));
			deal.setUserType(2);
			deal.setDealType(Integer.parseInt(type));
			deal.setMoney(Double.parseDouble(money));
			count = dealService.addDeal(deal);
			
			if(count <= 0){
				reInfo.setErrorMessage("修改余额出错！");
				reInfo.setErrorCode("10005");
				return reInfo;
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("修改余额错误！");
			reInfo.setErrorCode("10004");
			return reInfo;
		}
		rsInfo.setResult(count);
		rsInfo.setErrorMessage("余额修改成功！");
		return rsInfo;
	}
	//附近
		@RequestMapping(value = "/user/getNearbyMerchant")
		public @ResponseBody
		Object getNearbyMerchant(HttpServletRequest request,HttpSession session,
				HttpServletResponse response) {
			ResultSuccessInfo rsInfo = new ResultSuccessInfo();
			ResultErrorInfo reInfo = new ResultErrorInfo();	
			String merchantName = request.getParameter("merchantName");
			String distance = request.getParameter("distance");
			String userLon = request.getParameter("userLon");
			String userLat = request.getParameter("userLat");
			String sort = request.getParameter("sort");
			Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
			Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
			int distances=0,sortIndex;
			String name=null;
			if(userLon==null||userLon.equals("")){
				reInfo.setErrorMessage("用户经度为空！");
				reInfo.setErrorCode("10000");
				return reInfo;
			}
			if(userLat==null||userLat.equals("")){
				reInfo.setErrorMessage("用户纬度为空！");
				reInfo.setErrorCode("10000");
				return reInfo;
			}
			if(pageNum == null || pageNum.equals("")){
				reInfo.setErrorMessage("页码不能为空！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
			if(pageSize == null || pageSize.equals("")){
				reInfo.setErrorMessage("页大小不能为空！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
			if(merchantName==null||merchantName.equals(""))
				name=null;
			else
				name = merchantName;
			if(distance==null||distance.equals(""))
				distances = 10001;
			else if(distance.equals("1"))
				distances = 1000;
			else if(distance.equals("2"))
				distances = 3000;
			else if(distance.equals("3"))
				distances = 5000;
			else if(distance.equals("4"))
				distances = 10000;
			else if(distance.equals("5"))
				distances = 10002;
			if(sort==null||sort.equals(""))
				sortIndex=0;
			else
				sortIndex = Integer.parseInt(sort);
			
			List<DistanceMerchant> merchantList = new ArrayList<DistanceMerchant>();
			try {
				merchantList = merchantService.getMerchantList(Double.parseDouble(userLon),Double.parseDouble(userLat),distances,sortIndex,name,pageNum,pageSize);
				if(merchantList.size()==0){
					reInfo.setErrorMessage("商家为空！");
					reInfo.setErrorCode("10000");
					return reInfo;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				reInfo.setErrorMessage("获取附近商家失败！");
				reInfo.setErrorCode("10000");
				return reInfo;
			}
			rsInfo.setErrorMessage("获取附近商家成功！");
			rsInfo.setResult(merchantList);
			return rsInfo;
		}

}
