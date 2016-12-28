package com.techsun.famouswine.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.CityMapper;
import com.techsun.famouswine.domain.City;
import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.service.CityService;
@Service("CityService")
public class CityServiceImpl implements CityService {
	private CityMapper cityMapper;
	
	public CityMapper getCityMapper() {
		return cityMapper;
	}
	@Autowired
	public void setCityMapper(CityMapper cityMapper) {
		this.cityMapper = cityMapper;
	}


	@Override
	public List<City> getCityList(Integer pageNum,Integer pageSize) {
		List<City> cityList = new ArrayList<City>();
		CommonParam page = new CommonParam();
		page.setStartNum(pageNum-1);
		page.setEndNum(pageSize);
		try {
			cityList = cityMapper.findCityList(page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取城市列表失败！");
		}
		return cityList;
	}

}
