package com.techsun.famouswine.dao;

import java.util.List;

import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.domain.OrderProduct;
import com.techsun.famouswine.domain.OrderResult;

public interface OrderProductMapper {
    int deleteByPrimaryKey(Integer opId);

    int insert(OrderProduct record);

    int insertSelective(OrderProduct record);

    OrderProduct selectByPrimaryKey(Integer opId);

    int updateByPrimaryKeySelective(OrderProduct record);

    int updateByPrimaryKey(OrderProduct record);
    //根据订单号获取订单商品信息
    List<OrderResult> findOrderProductList(CommonParam page);
}