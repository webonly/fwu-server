package com.techsun.famouswine.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.ShoppingCartMapper;
import com.techsun.famouswine.domain.Banner;
import com.techsun.famouswine.domain.CartProduct;
import com.techsun.famouswine.domain.ShoppingCart;
import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.domain.Merchant;
import com.techsun.famouswine.domain.Product;
import com.techsun.famouswine.domain.ShoppingCartKey;
import com.techsun.famouswine.service.ShoppingCartService;

/**
 * 
* @ClassName: ShoppingCartServiceImpl  
* @author zhanglijun
* @date 2015-7-15  1:52:25 
*
 */
@Service("ShoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService {

	private ShoppingCartMapper shoppingCartMapper;

	public ShoppingCartMapper getShoppingCartMapper() {
		return shoppingCartMapper;
	}

	@Autowired
	public void setShoppingCartMapper(ShoppingCartMapper shoppingCartMapper) {
		this.shoppingCartMapper = shoppingCartMapper;
	}





	@Override
	public int addProductToCart(ShoppingCart shoppingCart) {
		int count=0;	
		try {
			count = shoppingCartMapper.insertSelective(shoppingCart);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("添加购物车失败！！");
		}
		return count;
	}
	
	
	
	@Override
	public int updateProductToCart(ShoppingCart shoppingCart) {
		int count=0;	
		try {
			count = shoppingCartMapper.updateByPrimaryKey(shoppingCart);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("更新购物车失败！！");
		}
		return count;
	}

	@Override
	public List<CartProduct> getShoppingCartList(int userId, int pageNum,
			int pageSize) {
		CommonParam param = new CommonParam();
		param.setStartNum((pageNum-1)*pageSize);
		param.setEndNum(pageNum*pageSize);
		param.setUserId(userId);
		List<CartProduct> productList;
		try{
			productList = shoppingCartMapper.getShoppingCartList(param);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取收藏失败");
		}
		return productList;
	}

	@Override
	public ShoppingCart getShoppingCartByKey(int userId, int activityId,
			int productId, int merchantId) {
		ShoppingCartKey key = new ShoppingCartKey();
		key.setUserId(userId);
		key.setActivityId(activityId);
		key.setProductId(productId);
		key.setMerchantId(merchantId);
		
		ShoppingCart shoppingCart;
		try{
			shoppingCart = shoppingCartMapper.getShoppingCartByKey(key);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取收藏失败");
		}
		return shoppingCart;
	}



	

}
