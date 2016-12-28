package com.techsun.famouswine.dao;

import java.util.List;

import com.techsun.famouswine.domain.Banner;

public interface BannerMapper {
    int deleteByPrimaryKey(Integer bannerId);

    int insert(Banner record);

    int insertSelective(Banner record);

    Banner selectByPrimaryKey(Integer bannerId);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);
    
    List<Banner> getAllBanner();
}