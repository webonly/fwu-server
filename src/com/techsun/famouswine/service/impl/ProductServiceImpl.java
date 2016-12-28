package com.techsun.famouswine.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.ProductMapper;
import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.domain.ProductEntity;
import com.techsun.famouswine.service.ProductService;

@Service("ProductService")
public class ProductServiceImpl implements ProductService {
	
	private ProductMapper productMapper;
	
	
	public ProductMapper getProductMapper() {
		return productMapper;
	}

	@Autowired
	public void setProductMapper(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}
	
	

	@Override
	public List<ProductEntity> getProductByName(Double userLon,Double userLat,String productName,
			Integer pageNum,Integer pageSize,Double startPrices,Double endPrices) {
		
		List<ProductEntity> productList = new ArrayList<ProductEntity>();
		CommonParam page = new CommonParam();
		page.setProductName(productName);
		page.setStartNum(pageNum-1);
		page.setEndNum(pageSize);
		page.setUserLon(userLon);
		page.setUserLat(userLat);
		page.setStartPrice(startPrices);
		page.setEndPrice(endPrices);
		try {
			productList = productMapper.findProductByName(page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取商品信息错误");
		}
		return productList;
	}

	@Override
	public List<ProductEntity> getProductByType(Integer type,Integer merchantId,Integer pageNum,Integer pageSize) {
		
		List<ProductEntity> productList = new ArrayList<ProductEntity>();
		CommonParam page = new CommonParam();
		page.setType(type);
		page.setStartNum(pageNum-1);
		page.setEndNum(pageSize);
		page.setMerchantId(merchantId);
		try {
			productList = productMapper.findProductByType(page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取商品信息错误");
		}
		return productList;
	}

	

	@Override
	public List<ProductEntity> getProductByParam(Integer type,
			Integer pageNum, Integer pageSize) {
		List<ProductEntity> productList = new ArrayList<ProductEntity>();
		CommonParam page = new CommonParam();
		page.setType(type);;
		page.setStartNum(pageNum-1);
		page.setEndNum(pageSize);
		try {
			productList = productMapper.getProductByParam(page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取商品信息错误");
		}
		return productList;
	}



	@Override
	public ProductEntity getProductDetail(Integer productId) {
		ProductEntity product=new ProductEntity();
		try {
			product = productMapper.findProductDetail(productId); 
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取商品信息错误");
		}
		return product;
	}

	@Override
	public List<ProductEntity> getMerchantProductByName(Integer merchantId,
			String productName, Integer pageNum, Integer pageSize) {
		List<ProductEntity> productList = new ArrayList<ProductEntity>();
		CommonParam page = new CommonParam();
		page.setMerchantId(merchantId);
		page.setProductName(productName);
		page.setStartNum(pageNum-1);
		page.setEndNum(pageSize);
		try {
			productList = productMapper.findMerchantProductByName(page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取商品信息错误");
		}
		return productList;
	}

	

	

	


	
}
