package com.techsun.famouswine.dao;

import com.techsun.famouswine.domain.Schedule;


public interface ScheduleMapper {
    int deleteByPrimaryKey(Integer scheduleId);

    int insert(Schedule record);

    int insertSelective(Schedule record);

    Schedule selectByPrimaryKey(Integer scheduleId);

    int updateByPrimaryKeySelective(Schedule record);

    int updateByPrimaryKey(Schedule record);
    //根据订单号查询进度
  	Schedule getScheduleByCode(Integer orderId);
}