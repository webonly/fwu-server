package com.techsun.famouswine.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.CollectMapper;
import com.techsun.famouswine.domain.Banner;
import com.techsun.famouswine.domain.Collect;
import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.domain.Merchant;
import com.techsun.famouswine.domain.Product;
import com.techsun.famouswine.service.CollectService;

/**
 * 
* @ClassName: CollectServiceImpl  
* @author zhanglijun
* @date 2015-7-15  1:52:25 
*
 */
@Service("CollectService")
public class CollectServiceImpl implements CollectService {

	private CollectMapper collectMapper;

	public CollectMapper getCollectMapper() {
		return collectMapper;
	}

	@Autowired
	public void setCollectMapper(CollectMapper collectMapper) {
		this.collectMapper = collectMapper;
	}

	@Override
	public int collect(Collect collect) {
		int count=0;
		if(collect.getCreatedDate()==null||collect.getCreatedDate().equals("")){
			collect.setCreatedDate(new Date());
		}
		try {
			collectMapper.updateByPrimaryKeySelective(collect);
			count = collectMapper.insertSelective(collect);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("收藏失败！！");
		}
		return count;
	}

	@Override
	public List<Product> getMyCollectProduct(int userId, int pageNum, int pageSize) {
		
		CommonParam param = new CommonParam();
		param.setStartNum((pageNum-1)*pageSize);
		param.setEndNum(pageNum*pageSize);
		param.setUserId(userId);
		List<Product> productList;
		try{
			productList = collectMapper.getMyCollectProduct(param);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取收藏失败");
		}
		return productList;
	}

	@Override
	public List<Merchant> getMyCollectMerchant(int userId, int pageNum, int pageSize) {
		CommonParam param = new CommonParam();
		param.setStartNum(pageNum-1);
		param.setEndNum(pageSize);
		param.setUserId(userId);
		
		List<Merchant> merchantList;
		try{
			merchantList = collectMapper.getMyCollectMerchant(param);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取收藏失败");
		}
		return merchantList;
	}

	

}
