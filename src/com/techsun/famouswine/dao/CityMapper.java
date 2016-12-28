package com.techsun.famouswine.dao;



import java.util.List;

import com.techsun.famouswine.domain.City;
import com.techsun.famouswine.domain.CommonParam;

public interface CityMapper {
    int deleteByPrimaryKey(Integer cityId);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(Integer cityId);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);
    //获取城市列表
    List<City> findCityList(CommonParam page);
}