package com.techsun.famouswine.service;

import java.util.List;

import com.techsun.famouswine.domain.Collect;
import com.techsun.famouswine.domain.Merchant;
import com.techsun.famouswine.domain.Product;




/**
 * 
* @ClassName: CollectService  
* @author zhanglijun
* @date 2015-8-15 1:52:33 
*
 */
public interface CollectService {
	
	int collect(Collect collect);
	
	List<Product> getMyCollectProduct(int userId, int pageNum, int pageSize);
	
	List<Merchant> getMyCollectMerchant(int userId, int pageNum, int pageSize);
}
