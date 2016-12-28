package com.techsun.famouswine.service;

import java.util.List;

import com.techsun.famouswine.domain.City;

public interface CityService {
	//获取城市列表
	List<City> getCityList(Integer pageNum , Integer pageSize);
}
