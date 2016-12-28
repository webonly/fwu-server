package com.techsun.famouswine.controller;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.techsun.famouswine.domain.User;
import com.techsun.famouswine.service.BalanceService;
import com.techsun.famouswine.service.DealService;
import com.techsun.famouswine.service.MerchantService;
import com.techsun.famouswine.service.UserService;
import com.techsun.famouswine.util.ResultErrorInfo;
import com.techsun.famouswine.util.ResultSuccessInfo;
import com.techsun.famouswine.util.SendSmsUtil;

@Controller
public class UserController {

	private UserService userService;
	private MerchantService merchantService;

	public UserService getUserService() {
		return userService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
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

	@RequestMapping(value = "/user/login")
	public @ResponseBody
	Object doLogin(HttpServletRequest request, HttpServletResponse response) {
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
		User user = null;
		try {
			user = userService.getUserByUserName(userName);
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("登录出错！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		if (user != null) {
			if (password.equals(user.getPassword())) {
				rsInfo.setResult(user);
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
		
		request.getSession().setAttribute("token", ""+userName + "test");
		
		

		return rsInfo;
	}
	
	@RequestMapping(value = "/user/thirdPartyLogin")
	public @ResponseBody
	Object thirdPartyLogin(HttpServletRequest request,
			HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		String userName = request.getParameter("snsId");
		String nickName = request.getParameter("nickName");
		String icon = request.getParameter("icon");

		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();

		if (userName == null || userName.equals("")) {
			reInfo.setErrorMessage("sns ID不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;

		}

		if (nickName == null || nickName.equals("")) {
			reInfo.setErrorMessage("昵称不能为空!");
			reInfo.setErrorCode("10001");
			return reInfo;

		}

		if (icon == null || icon.equals("")) {
			reInfo.setErrorMessage("头像不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;

		}

		User user = null;
		int count = 0;
		try {
			user = userService.getUserByUserName(userName);

		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("登录出错！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		if (user != null) {

			rsInfo.setResult(user);
			rsInfo.setErrorMessage("登录成功！");

		} else {

			try {
				user = new User();
				user.setUserName(userName);
				user.setPassword("123456");
				user.setNickName(nickName);
				user.setIcon(icon);
				count = userService.addUser(user);

			} catch (Exception e) {
				e.printStackTrace();
				reInfo.setErrorMessage("登录出错！");
				reInfo.setErrorCode("10003");
				return reInfo;
			}
			if (count > 0) {
				try {
					user = userService.getUserByUserName(userName);

				} catch (Exception e) {
					e.printStackTrace();
					reInfo.setErrorMessage("登录出错！");
					reInfo.setErrorCode("10003");
					return reInfo;
				}

				rsInfo.setResult(user);
				rsInfo.setErrorMessage("登录成功！");
				return rsInfo;

			}

		}

		return rsInfo;
	}


	@RequestMapping(value = "/user/obtainUserInformation")
	public @ResponseBody
	Object obtainUserInformation(HttpServletRequest request,
			HttpServletResponse response) {

		response.addHeader("Access-Control-Allow-Origin", "*");
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		User user = null;
		user = userService.getUserByUserId(userId);
		if (user != null) {
			rsInfo.setResult(user);
			rsInfo.setErrorMessage("获取用户信息成功");
		} else {
			reInfo.setErrorMessage("获取用户信息失败");
			rsInfo.setResult(null);
		}

		return rsInfo;
	}

	
	@RequestMapping(value = "/user/register")
	public @ResponseBody
	Object register(HttpServletRequest request, HttpSession session,
			HttpServletResponse response) {
		ResultErrorInfo reInfo = new ResultErrorInfo();
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		response.addHeader("Access-Control-Allow-Origin", "*");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
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

		if (authcode == null || authcode.equals("")) {
			reInfo.setErrorMessage("验证码不能为空！");
			reInfo.setErrorCode("10002");
			return reInfo;
		}
		
		User user = null;
		try {
			user = userService.getUserByUserName(userName);

		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("获取用户出错！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		if (user != null) {
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
		user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setCreatedDate(new Date());
		
		// ActionContext.getContext().getSession().put("user", user);

		int count = 0;
		try {
			count = userService.addUser(user);

		} catch (Exception e) {
			reInfo.setErrorMessage("注册失败！");
			reInfo.setErrorCode("10000");
			return reInfo;
		}
		if(count <=0 ){
			reInfo.setErrorMessage("注册失败！");
			reInfo.setErrorCode("10000");
			return reInfo;

		}
		
		try {
			user = userService.getUserByUserName(userName);

		} catch (Exception e) {
			reInfo.setErrorMessage("获取用户失败！");
			reInfo.setErrorCode("10000");
			return reInfo;
		}
		if(user == null){
			reInfo.setErrorMessage("获取用户失败！");
			reInfo.setErrorCode("10000");
			return reInfo;
			
		}
				
		rsInfo.setErrorMessage("注册成功！");	
		rsInfo.setResult(user);

		return rsInfo;
	}

	@RequestMapping(value = "/user/modifyUser")
	public @ResponseBody
	Object doModifyUser(HttpServletRequest request) {

		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();

		String userId = request.getParameter("userId");
		
		if (userId == null || userId.equals("")) {
			reInfo.setErrorMessage("用户名不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;

		}

		String nickName = request.getParameter("nickName");
		String icon = request.getParameter("icon");
		String drinkAge = request.getParameter("drinkAge");
		String hobby = request.getParameter("hobby");
		String sex = request.getParameter("sex");
		
		
		User user = new User();
		try {
			user = userService.getUserByUserId(Integer.parseInt(userId));
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("獲取用戶信息失敗！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		user.setNickName(nickName);
		user.setDrinkAge(Integer.parseInt(drinkAge));
		user.setHobby(hobby);
		user.setGender(sex);
		user.setIcon(icon);

		int count;
		try {
			count = userService.modifyUser(user);
		} catch (Exception e) {
			reInfo.setErrorMessage("修改用户出错！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		if (count > 0) {
			rsInfo.setResult(count);
			rsInfo.setErrorMessage("修改用户成功");
		}

		return rsInfo;
	}
	//找回密碼
	@RequestMapping(value = "/user/findByPassword")
	public @ResponseBody
	Object findByPassword(HttpServletRequest request, HttpSession session) {

		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String userName = request.getParameter("userName");
		String authcode = request.getParameter("authcode");
		String newPwd = request.getParameter("newPwd");
		String confrimPwd = request.getParameter("confrimPwd");
		if (userName == null || userName.equals("")) {
			reInfo.setErrorMessage("获取用户信息失败！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		if (authcode == null ||authcode.equals("")) {
			reInfo.setErrorMessage("驗證碼不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}if (newPwd == null ||newPwd.equals("")) {
			reInfo.setErrorMessage("新密码不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		if (confrimPwd == null ||confrimPwd.equals("")) {
			reInfo.setErrorMessage("确认密码不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		
		if(!newPwd.equals(confrimPwd)){
			reInfo.setErrorMessage("两次密码输入不一致！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		User user = null;
		try {
			user = userService.getUserByUserName(userName);

		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("获取用户出错！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		if (user != null) {
				int count;
				CRCode codes = (CRCode) session.getAttribute(userName);
				if (codes!=null ) {
					long nowtime = System.currentTimeMillis();
					long pasttime = codes.getSendDate().getTime();
					if ((nowtime - pasttime) > 1800000) {
						reInfo.setErrorMessage("驗證碼已過期！");
						return reInfo;
					}else if(!codes.getCrCode().equals(authcode)){
						reInfo.setErrorMessage("驗證碼不正確！");
						return reInfo;
					}
				}
				user.setPassword(newPwd);
				try {
					count = userService.modifyUser(user);
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

			reInfo.setErrorMessage("用户不存在！");
			reInfo.setErrorCode("10003");
			return reInfo;

		}

		return rsInfo;
	}
	//修改密碼
	@RequestMapping(value = "/user/modifyPassword")
	public @ResponseBody
	Object modifyPassword(HttpServletRequest request, HttpSession session) {

		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String userName = request.getParameter("userName");
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		String confrimPwd = request.getParameter("confrimPwd");
		if (userName == null || userName.equals("")) {
			reInfo.setErrorMessage("获取用户信息失败！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		if (oldPwd == null ||oldPwd.equals("")) {
			reInfo.setErrorMessage("当前密码不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}if (newPwd == null ||newPwd.equals("")) {
			reInfo.setErrorMessage("新密码不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		if (confrimPwd == null ||confrimPwd.equals("")) {
			reInfo.setErrorMessage("确认密码不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		User user = null;
		try {
			user = userService.getUserByUserName(userName);

		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("获取用户出错！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		
		if(!newPwd.equals(confrimPwd)){
			reInfo.setErrorMessage("两次密码输入不一致！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		if (user != null) {
			if(!user.getPassword().endsWith(oldPwd)){
				reInfo.setErrorMessage("旧密码不正确！");
				reInfo.setErrorCode("10003");
				return reInfo;
			}
				int count;
				user.setPassword(newPwd);
				try {
					count = userService.modifyUser(user);
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

			reInfo.setErrorMessage("用户不存在！");
			reInfo.setErrorCode("10003");
			return reInfo;

		}

		return rsInfo;
	}

	@RequestMapping(value = "/modifyBindMobile")
	public @ResponseBody
	Object modifyBindMobile(HttpServletRequest request, HttpSession session) {

		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();

		String userId = request.getParameter("userId");
		String mobile = request.getParameter("mobile");
		String authcode = request.getParameter("authcode");
		if (userId == null || userId.equals("")) {
			reInfo.setErrorMessage("用户名不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		if (mobile == null || mobile.equals("")) {
			reInfo.setErrorMessage("手机号不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		if (authcode == null || authcode.equals("")) {
			reInfo.setErrorMessage("验证码不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		
		CRCode codes = (CRCode) session.getAttribute(mobile);

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
		
		int count;
		User user = new User();
		user.setUserId(Integer.parseInt(userId));
		user.setMobile(mobile);
		try {
			count = userService.modifyUser(user);
		} catch (Exception e) {
			reInfo.setErrorMessage("修改用户手机出错！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		if (count > 0) {
			rsInfo.setResult(count);
			rsInfo.setErrorMessage("修改用户手机成功");
		}

		return rsInfo;
	}

	

	@RequestMapping(value = "/user/genAuthCode")
	protected @ResponseBody
	Object genAuthCode(HttpServletRequest request, HttpSession session) {
		StringBuilder code = new StringBuilder();
		String mobile = request.getParameter("userName");
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

	@RequestMapping(value = "/user/signIn")
	public @ResponseBody
	Object signIn(HttpServletRequest request) {

		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();

		String userId = request.getParameter("userId");
		if (userId == null || userId.equals("")) {
			reInfo.setErrorMessage("用户名不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}

		int count = 0;

		
		if (count > 0) {
			rsInfo.setResult(count);
			rsInfo.setErrorMessage("用户签到成功");
		}

		return rsInfo;
	}
	//修改余额
		@RequestMapping(value = "/user/modifyBalance")
		public @ResponseBody
		Object ModifyBalance(HttpServletRequest request) {
			ResultSuccessInfo rsInfo = new ResultSuccessInfo();
			ResultErrorInfo reInfo = new ResultErrorInfo();
			String userId =request.getParameter("userId");			
			String money = request.getParameter("money");
			String type = request.getParameter("type");
			Deal deal = null;
			int count=0;
			if(userId==null||userId.equals("")){
				reInfo.setErrorMessage("获取用户信息错误！");
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
				Balance balance = balanceService.getBalance(Integer.parseInt(userId),1);
				
				if(balance == null){
					balance = new Balance();
					if(type.equals("1")){
						balance.setUserId(Integer.parseInt(userId));
						balance.setUserType(1);
						balance.setBalance(Double.parseDouble(money));
						count = balanceService.addBalance(Integer.parseInt(userId), 1, Double.parseDouble(money));
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
				
				count = balanceService.modifyBalance(Integer.parseInt(userId), 1, balanceValue);
				if(count <= 0){
					reInfo.setErrorMessage("修改余额出错！");
					reInfo.setErrorCode("10005");
					return reInfo;
					
				}
				
				}
				

				
				deal = new Deal();
				deal.setUserId(Integer.parseInt(userId));
				deal.setUserType(1);
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
		
		
		
		
	//快捷注册
//	@RequestMapping(value = "/user/quickRegister")
//	public @ResponseBody
//	Object quickRegister(HttpServletRequest request,HttpSession session,
//			HttpServletResponse response) {
//		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
//		ResultErrorInfo reInfo = new ResultErrorInfo();	
//		String mobile = request.getParameter("userName");
//		if(mobile==null||mobile.equals("")){
//			reInfo.setErrorMessage("获取用户失败！");
//			reInfo.setErrorCode("10006");
//			return reInfo;
//		}
//		String authcode = request.getParameter("authcode");
//		CRCode codes = (CRCode) session.getAttribute(mobile);
//		if (!authcode.equals(codes)) {
//			reInfo.setErrorMessage("验证码错误！");
//			return reInfo;
//		}
//		User u = null;
//		try {
//			u= userService.getUserByUserName(mobile);
//			if(u!=null){
//				reInfo.setErrorMessage("用户已经注册了！");
//				reInfo.setErrorCode("10003");
//				return reInfo;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			reInfo.setErrorMessage("信息错误！");
//			reInfo.setErrorCode("10003");
//			return reInfo;
//		}
//		rsInfo.setErrorMessage("注册成功！");
//		return rsInfo;
//	}
	//快捷登录
	@RequestMapping(value = "/user/quickLogin")
	public @ResponseBody
	Object quickLogin(HttpServletRequest request,HttpSession session,
			HttpServletResponse response) {
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();	
		String userName = request.getParameter("userName");
		if(userName==null||userName.equals("")){
			reInfo.setErrorMessage("手机号不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		String authcode = request.getParameter("authcode");
		CRCode codes = (CRCode) session.getAttribute(userName);
		if (!authcode.equals(codes.getCrCode())) {
			reInfo.setErrorCode("100000");
			reInfo.setErrorMessage("验证码错误！");
			return reInfo;
		}
		User user = null;
		try {
			user= userService.getUserByUserName(userName);
			if(user==null){
				user = new User();
				user.setUserName(userName);
				int count=userService.addUser(user);
				if(count>0){
					user= userService.getUserByUserName(userName);
				}else{
					reInfo.setErrorMessage("信息错误！");
					reInfo.setErrorCode("10003");
					return reInfo;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("信息错误！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		rsInfo.setErrorMessage("登录成功！");
		rsInfo.setResult(user);
		return rsInfo;
	}
	
	
}
