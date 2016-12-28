package com.techsun.famouswine.service;

import com.techsun.famouswine.domain.Schedule;

public interface ScheduleService {
	//根据订单号查询进度
	Schedule getScheduleByCode(Integer orderId);
	//生成订单时自动生成订单进度
	int generateSchedule(Schedule schedule);
}
