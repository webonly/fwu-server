package com.techsun.famouswine.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.ScheduleMapper;
import com.techsun.famouswine.domain.Schedule;
import com.techsun.famouswine.service.ScheduleService;

@Service("ScheduleService")
public class ScheduleServiceImpl implements ScheduleService {
	private ScheduleMapper scheduleMapper;
	
	public ScheduleMapper getScheduleMapper() {
		return scheduleMapper;
	}
	@Autowired
	public void setScheduleMapper(ScheduleMapper scheduleMapper) {
		this.scheduleMapper = scheduleMapper;
	}
	@Override
	public Schedule getScheduleByCode(Integer orderId) {
		Schedule schedule = null;
		try {
			schedule = scheduleMapper.getScheduleByCode(orderId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取进度失败！");
		}
		return schedule;
	}
	@Override
	public int generateSchedule(Schedule schedule) {
		int count = 0;
		if(schedule.getSchedule()==null||schedule.getSchedule().equals("")){
			schedule.setSchedule("商家已接单！");
		}
		if(schedule.getCreatedDate()==null||schedule.getCreatedDate().equals("")){
			schedule.setCreatedDate(new Date());
		}
		if(schedule.getUpdatedDate()==null||schedule.getUpdatedDate().equals("")){
			schedule.setUpdatedDate(new Date());
		}
		try {
			scheduleMapper.updateByPrimaryKeySelective(schedule);
			count = scheduleMapper.insertSelective(schedule);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("生成订单进度失败！");
		}
		return count;
	}

}
