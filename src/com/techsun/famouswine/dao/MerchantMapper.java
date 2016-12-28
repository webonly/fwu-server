package com.techsun.famouswine.dao;

import java.util.List;

import com.techsun.famouswine.domain.DistanceMerchant;
import com.techsun.famouswine.domain.Merchant;

public interface MerchantMapper {
    int deleteByPrimaryKey(Integer merchantId);

    int insert(Merchant record);

    int insertSelective(Merchant record);

    Merchant selectByPrimaryKey(Integer merchantId);

    int updateByPrimaryKeySelective(Merchant record);

    int updateByPrimaryKey(Merchant record);
   
    Merchant selectByMerchantNames(String merchantName);
    
    Merchant selectByMerchantPhone(Integer merchantPhone);
    
    int updateByVerification(Merchant merchant);
    
    DistanceMerchant getMerchantInfo(Integer merchantId);
    
    Merchant getMerchantByUserName(String userName);
    
    List<DistanceMerchant> searchByMerchantName(DistanceMerchant merchant);
   
	
	Merchant selectByMerchantList(Integer merchantId);
    
    List<Merchant> getMerchantNameList(String  merchantName);
    
    List<DistanceMerchant> getAllMerchant(DistanceMerchant merchant);
}