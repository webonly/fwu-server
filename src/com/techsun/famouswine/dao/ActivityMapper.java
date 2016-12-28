package com.techsun.famouswine.dao;

import java.util.List;

import com.techsun.famouswine.domain.Activity;
import com.techsun.famouswine.domain.CommonParam;

public interface ActivityMapper {
    int deleteByPrimaryKey(Integer activityId);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Integer activityId);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);
    
    List<Activity> getActivityListByType(CommonParam param);
    List<Activity> getAllActivityList(CommonParam param);

    List<Activity> getActivityListByMerchantId(CommonParam param);

}