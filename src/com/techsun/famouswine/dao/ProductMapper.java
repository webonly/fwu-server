package com.techsun.famouswine.dao;


import java.util.List;

import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.domain.Product;
import com.techsun.famouswine.domain.ProductEntity;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer productId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer productId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
    //按名称查询商品
    List<ProductEntity> findProductByName(CommonParam page);
    //按类型查询
    List<ProductEntity> findProductByType(CommonParam page);
    //按分类、价格倒序查询
  	List<ProductEntity> getProductByParam(CommonParam page);
  	//按id查询商品详情
  	ProductEntity findProductDetail(Integer productId); 
 
  	List<ProductEntity> findMerchantProductByName(CommonParam page);
}