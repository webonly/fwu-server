package com.techsun.famouswine.service;

import java.util.List;

import com.techsun.famouswine.domain.Activity;




/**
 * 
* @ClassName: ActivityService  
* @author zhanglijun
* @date 2015-8-15 1:52:33 
*
 */
public interface ActivityService {
	
	List<Activity> getActivityListByType(int type, int pageNum, int pageSize);
	List<Activity> getActivityListByMerchantId(int merchantId, int pageNum, int pageSize);
}
