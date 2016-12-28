package com.techsun.famouswine.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.MerchantMapper;
import com.techsun.famouswine.domain.DistanceMerchant;
import com.techsun.famouswine.domain.Merchant;
import com.techsun.famouswine.service.MerchantService;

@Service("MerchantService")
public class MerchantServiceImpl  implements MerchantService{

	private MerchantMapper merchantMapper;
	
	public MerchantMapper getMerchantMapper() {
		return merchantMapper;
	}
	@Autowired
	public void setMerchantMapper(MerchantMapper merchantMapper) {
		this.merchantMapper = merchantMapper;
	}


	
	@Override
	public DistanceMerchant getMerchantInfo(Integer merchantId) {
		DistanceMerchant merchant;
		try {
			merchant = merchantMapper.getMerchantInfo(merchantId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("通过商户ID查询商户余额错误！");
		}
		return merchant;
	}
	
	
	@Override
	public Merchant getMerchantByUserName(String userName) {
		Merchant merchant;
		try {
			merchant = merchantMapper.getMerchantByUserName(userName);
			  
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("通过用户名查询商户错误！");
		}
		return merchant;
	}
	//获取所有商家
	@Override
	public List<DistanceMerchant> getMerchantList(Double userLon,Double userLat,Integer distance,Integer sortIndex,String name,Integer pageNum,Integer pageSize) {
		List<DistanceMerchant> merchantList = null;
		DistanceMerchant merchant = new DistanceMerchant();
		merchant.setUserLon(userLon);
		merchant.setUserLat(userLat);
		merchant.setDistance(distance);
		merchant.setSales(sortIndex);
		merchant.setMerchantName(name);
		merchant.setPageNum(pageNum);
		merchant.setPageSize(pageSize);
		try {
			merchantList = merchantMapper.getAllMerchant(merchant);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取商家信息失败！");
		}
		return merchantList;
	}
	
	
	/**
	 * 商户添加
	 */
	@Override
	public int addMerchant(Merchant merchant) {
		int count;
		try {
			merchantMapper.updateByPrimaryKeySelective(merchant);
			count = merchantMapper.insertSelective(merchant);
			if(count==0){
				throw new Exception("该手机号码已经注册了");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("增加商户失败2");
		}
		return count;
	}

	/**
	 * 商户修改密码
	 * 
	 */
	@Override
	public int modifyMerchant(Merchant merchant) {
		int count;
		try {
				count = merchantMapper.updateByPrimaryKeySelective(merchant);
				if(count<0){
					throw new Exception("修改商户失败1");
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("修改商户失败2");
		}
		return count;
	}
	
	/**
	 * 商户密码找回
	 */
	@Override
	public int verification(Merchant merchant) {
		int count;
		try {
				count = merchantMapper.updateByVerification(merchant);
				if(count<0){
					throw new Exception("商户密码找回失败1");
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("商户密码找回失败2");
		}
		return count;
	}
	
	/**
	 * 商户查找手机号码
	 */
	@Override
	public Merchant selectByMerchantPhone(Integer merchantPhone) {
		Merchant merchant;
		try {
			merchant = merchantMapper.selectByMerchantPhone(merchantPhone);
			  
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("通过手机号码是否是为注册的手机号码错误");
		}
		return merchant;
	}
	
	

	@Override
	public List<Merchant> getMerchantNameList(String merchantName) {
		List<Merchant> merchant;
		try {
			merchant = merchantMapper.getMerchantNameList(merchantName);
			  
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("通过商户ID查询商户余额错误！");
		}
		return merchant;
	}
	@Override
	public List<DistanceMerchant> searchByMerchantName(String merchantName,Double userLon,Double userLat,Integer distances,Integer pageNum,Integer pageSize) {
		List<DistanceMerchant> merchantList= new ArrayList<DistanceMerchant>();
		DistanceMerchant merchant = new DistanceMerchant();
		merchant.setMerchantName(merchantName);
		merchant.setUserLon(userLon);
		merchant.setUserLat(userLat);
		merchant.setDistance(distances);
		merchant.setPageNum(pageNum);
		merchant.setPageSize(pageSize);
		try {
			merchantList = merchantMapper.searchByMerchantName(merchant);
			  
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("通过商户ID查询商户余额错误！");
		}
		return merchantList;
	}




}
