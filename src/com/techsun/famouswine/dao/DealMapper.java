package com.techsun.famouswine.dao;

import java.util.List;

import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.domain.Deal;

public interface DealMapper {
    int deleteByPrimaryKey(Integer dealId);

    int insert(Deal record);

    int insertSelective(Deal record);

    Deal selectByPrimaryKey(Integer dealId);

    int updateByPrimaryKeySelective(Deal record);

    int updateByPrimaryKey(Deal record);
    //根据用户id获取交易记录
  	List<Deal> findUserDealList(CommonParam page);
  	
  	List<Deal> selectAllDealList(CommonParam page);
  	
  	
}