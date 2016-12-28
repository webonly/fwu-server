package com.techsun.famouswine.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techsun.famouswine.domain.Coupon;
import com.techsun.famouswine.service.CouponService;
import com.techsun.famouswine.util.ResultErrorInfo;
import com.techsun.famouswine.util.ResultSuccessInfo;

@Controller
public class CouponController {
	private CouponService couponService;

	
	public CouponService getCouponService() {
		return couponService;
	}
	@Autowired
	public void setCouponService(CouponService couponService) {
		this.couponService = couponService;
	}

	//获取所有账户
	@RequestMapping(value="/coupon/getCouponList")
	public @ResponseBody
	Object getCouponList(HttpServletRequest request,HttpSession session, 
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String userId = request.getParameter("userId");
		if(userId==null||userId.equals("")){
			reInfo.setErrorMessage("获取用户信息失败！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		
		List<Coupon> couponList=null;
		try {
			couponList=couponService.getCouponList(Integer.parseInt(userId));
			if(couponList.size()<0){
				reInfo.setErrorMessage("获取优惠券失败！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("获取优惠券失败！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		rsInfo.setResult(couponList);
		rsInfo.setErrorMessage("查询成功！");
		return rsInfo;
	}
	
	//修改优惠券状态
	@RequestMapping(value="/coupon/modifyCoupon")
	public @ResponseBody
	Object modifyCoupon(HttpServletRequest request,HttpSession session, 
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String userId = request.getParameter("userId");
		String couponId = request.getParameter("couponId");
		String isUsed = request.getParameter("isUsed");
		if(userId==null||userId.equals("")){
			reInfo.setErrorMessage("获取用户不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		if(couponId==null||couponId.equals("")){
			reInfo.setErrorMessage("获取账户不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		
		if(isUsed==null||isUsed.equals("")){
			reInfo.setErrorMessage("是否使用不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		
		Coupon coupon = new Coupon();
		coupon.setUserId(Integer.parseInt(userId));
		coupon.setCouponId(Integer.parseInt(couponId));
		coupon.setIsUsed(Boolean.parseBoolean(isUsed));
		int count = 0;
		try {
			count=couponService.modifyCoupon(coupon);
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
	
	/*//积分兑换优惠券
	@RequestMapping(value="/coupon/addCoupon")
	public @ResponseBody
	Object addCoupon(HttpServletRequest request,HttpSession session, 
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String userId=request.getParameter("userId");
		String userType=request.getParameter("money");
		String bank = request.getParameter("bank");
		
		if(userId==null||userId.equals("")){
			reInfo.setErrorMessage("用户ID不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		if(coupon==null||coupon.equals("")){
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
		
		Coupon acc=new Coupon();
		acc.setCoupon(couponId);
		acc.setUserId(Integer.parseInt(userId));
		acc.setBank(bank);
		int count=0;
		try {
			count = couponService.addCoupon(acc);
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("添加账户失败！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		rsInfo.setResult(count);
		rsInfo.setErrorMessage("添加账户成功！");
		return rsInfo;
	}*/
	
	
	
	
	/*//删除账户
		@RequestMapping(value="/coupon/deleteCoupon")
		public @ResponseBody
		Object deleteCoupon(HttpServletRequest request,HttpSession session, 
				HttpServletResponse response){
			ResultSuccessInfo rsInfo = new ResultSuccessInfo();
			ResultErrorInfo reInfo = new ResultErrorInfo();
			String userId=request.getParameter("userId");
			String couponId=request.getParameter("couponId");
			
			if(userId==null||userId.equals("")){
				reInfo.setErrorMessage("用户ID不能为空！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
			if(couponId==null||couponId.equals("")){
				reInfo.setErrorMessage("账号ID不能为空！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
			
			int count=0;
			try {
				count = couponService.deleteCoupon(Integer.parseInt(userId),Integer.parseInt(couponId));
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
	@RequestMapping(value="/coupon/getBestCoupon")
	public @ResponseBody
	Object getDefaultCoupon(HttpServletRequest request,HttpSession session, 
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String userId=request.getParameter("userId");
		if(userId==null||userId.equals("")){
			reInfo.setErrorMessage("用户ID不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		Coupon coupon = null;
		try {
			coupon=couponService.getBestCoupon(Integer.parseInt(userId));
			if(coupon ==null){
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
		rsInfo.setResult(coupon);
		rsInfo.setErrorMessage("获取默认账户成功！");
		return rsInfo;
	}
} 
