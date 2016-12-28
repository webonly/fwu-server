package com.techsun.famouswine.dao;

import java.util.List;

import com.techsun.famouswine.domain.Coupon;

public interface CouponMapper {
    int deleteByPrimaryKey(Integer couponId);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    Coupon selectByPrimaryKey(Integer couponId);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);
    
    Coupon selectBestCoupon(Integer couponId);
    
    List<Coupon> selectCouponList(Integer couponId);    
}