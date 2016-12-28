package com.techsun.famouswine.service;

import java.util.List;

import com.techsun.famouswine.domain.Coupon;

public interface CouponService {
	//兑换优惠券
	int exchangeCoupon(Coupon coupon);
	//修改优惠券状态
	int modifyCoupon(Coupon coupon);
	//获取优惠券列表
	List<Coupon> getCouponList(Integer userId);
	//获取默认账户
	Coupon getBestCoupon(Integer userId);
}

