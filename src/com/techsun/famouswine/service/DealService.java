package com.techsun.famouswine.service;

import java.util.List;

import com.techsun.famouswine.domain.Deal;

public interface DealService {
	//添加交易记录
	int addDeal(Deal deal);
	//根据用户id获取交易记录
	List<Deal> getDealListByDealType(Integer userId,Integer userType,Integer dealType,
			Integer pageNum,Integer pageSize);
	
	List<Deal> getAllDealList(Integer userId,Integer userType,
			Integer pageNum,Integer pageSize);
}
