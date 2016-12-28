package com.techsun.famouswine.dao;

import java.util.List;

import com.techsun.famouswine.domain.Advertisement;
import com.techsun.famouswine.domain.CommonParam;

public interface AdvertisementMapper {
    int deleteByPrimaryKey(Integer advertisementId);

    int insert(Advertisement record);

    int insertSelective(Advertisement record);

    Advertisement selectByPrimaryKey(Integer advertisementId);

    int updateByPrimaryKeySelective(Advertisement record);

    int updateByPrimaryKey(Advertisement record);
    
    List<Advertisement> selectAdvertisementListByType(CommonParam param);
    List<Advertisement> selectHomeAdvertisementList();


}