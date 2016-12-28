package com.techsun.famouswine.service;

import java.util.List;

import com.techsun.famouswine.domain.ProductEntity;

public interface ProductService {
	//根据名称分页查询
	List<ProductEntity> getProductByName(Double userLon,Double userLat,String productName,Integer pageNum,Integer pageSize,
			Double startPrices,Double endPrices);
	//根据类型分页查询
	List<ProductEntity> getProductByType(Integer type,Integer merchantId,Integer pageNum,Integer pageSize);
	//查询单个商品
	ProductEntity getProductDetail(Integer productId);
	//按分类、价格倒序查询
	List<ProductEntity> getProductByParam(Integer type,Integer pageNum,Integer pageSize);
	
	List<ProductEntity> getMerchantProductByName(Integer merchantId,String productName,Integer pageNum,Integer pageSize);
	
	
}
