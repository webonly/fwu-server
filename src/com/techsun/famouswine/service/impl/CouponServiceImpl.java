package com.techsun.famouswine.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.CouponMapper;
import com.techsun.famouswine.domain.Coupon;
import com.techsun.famouswine.service.CouponService;

@Service("CouponService")
public class CouponServiceImpl implements CouponService {

	private CouponMapper couponMapper;
	
	
	public CouponMapper getCouponMapper() {
		return couponMapper;
	}
	@Autowired
	public void setCouponMapper(CouponMapper couponMapper) {
		this.couponMapper = couponMapper;
	}

	
	

	@Override
	public int exchangeCoupon(Coupon coupon) {
		int count=0;
		try {
			count = couponMapper.insert(coupon);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("添加账户失败！");
		}
		return count;
	}
	
	
	
	
	@Override
	public List<Coupon> getCouponList(Integer userId) {
		List<Coupon> couponList = null;
		try {
			couponList=couponMapper.selectCouponList(userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取账户失败！");
		}
		return couponList;
	}

	@Override
	public Coupon getBestCoupon(Integer userId) {
		
		Coupon coupon = null;
		try {
			coupon = couponMapper.selectBestCoupon(userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取最佳优惠券失败！");
		}
		return coupon;
	}
	@Override
	public int modifyCoupon(Coupon coupon) {
		int count=0;
		try {
			count = couponMapper.updateByPrimaryKeySelective(coupon);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("添加账户失败！");
		}
		return count;
	}
	

}
