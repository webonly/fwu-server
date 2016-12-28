package com.techsun.famouswine.dao;

import com.techsun.famouswine.domain.ProductRecommend;

public interface ProductRecommendMapper {
    int deleteByPrimaryKey(Integer prId);

    int insert(ProductRecommend record);

    int insertSelective(ProductRecommend record);

    ProductRecommend selectByPrimaryKey(Integer prId);

    int updateByPrimaryKeySelective(ProductRecommend record);

    int updateByPrimaryKey(ProductRecommend record);
}