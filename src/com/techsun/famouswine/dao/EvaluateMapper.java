package com.techsun.famouswine.dao;

import java.util.List;

import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.domain.Evaluate;

public interface EvaluateMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Evaluate record);

    int insertSelective(Evaluate record);

    Evaluate selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Evaluate record);

    int updateByPrimaryKey(Evaluate record);
    
    List<Evaluate> selectEvaluateListByMerchantId(CommonParam param);
    
    List<Evaluate> selectEvaluateListByProductId(CommonParam param);
}