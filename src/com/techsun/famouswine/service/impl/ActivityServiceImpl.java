package com.techsun.famouswine.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.ActivityMapper;
import com.techsun.famouswine.domain.Activity;
import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.service.ActivityService;

/**
 * 
* @ClassName: ActivityServiceImpl  
* @author zhanglijun
* @date 2015-7-15  1:52:25 
*
 */
@Service("ActivityService")
public class ActivityServiceImpl implements ActivityService {

	private ActivityMapper activityMapper;

	public ActivityMapper getActivityMapper() {
		return activityMapper;
	}

	@Autowired
	public void setActivityMapper(ActivityMapper activityMapper) {
		this.activityMapper = activityMapper;
	}

	@Override
	public List<Activity> getActivityListByType(int type,int pageNum, int pageSize) {
		
		List<Activity> activityList;
		
		CommonParam param = new CommonParam();
		param.setStartNum((pageNum-1)*pageSize);
		param.setEndNum(pageNum*pageSize);
		try{
			if(type == 0 ){
				activityList = activityMapper.getAllActivityList(param);
				
			}else{

				param.setType(type);
				
				activityList = activityMapper.getActivityListByType(param);
			}
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取Activity页面失败");
		}
		return activityList;
	}

	
	
	@Override
	public List<Activity> getActivityListByMerchantId(int merchantId,int pageNum, int pageSize) {
		
		List<Activity> activityList;
		
		CommonParam param = new CommonParam();
		param.setStartNum((pageNum-1)*pageSize);
		param.setEndNum(pageNum*pageSize);
		param.setMerchantId(merchantId);
		try {

			activityList = activityMapper.getActivityListByMerchantId(param);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取Activity页面失败");
		}
		return activityList;
	}
	

}
