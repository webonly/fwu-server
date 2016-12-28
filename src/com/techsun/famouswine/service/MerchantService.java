package com.techsun.famouswine.service;

import java.util.List;

import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.domain.DistanceMerchant;
import com.techsun.famouswine.domain.Merchant;


public interface MerchantService {
		
	
	//注册
	int addMerchant(Merchant merchant);
	
	//修改
	int modifyMerchant( Merchant merchant);
	
	//找回密码
	int verification( Merchant merchant);
	
	//查找手机号码
	Merchant selectByMerchantPhone(Integer merchantPhone);	
	
	//根据ID用户信息
	DistanceMerchant getMerchantInfo(Integer merchantId);
	
	//根据ID用户信息
	List<Merchant> getMerchantNameList(String merchantName);

	//根据名称模糊查询
	List<DistanceMerchant> searchByMerchantName(String merchantName,Double userLon,Double userLat,Integer distances,Integer pageNum,Integer pageSize);
	
	//根据商家用户名
	Merchant getMerchantByUserName(String userName);
	
	//获取所有商家
	List<DistanceMerchant> getMerchantList(Double userLon,Double userLat,Integer distance,Integer sortIndex,String name,Integer pageNum,Integer pageSize);

}
