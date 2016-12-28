package com.techsun.famouswine.dao;

import java.util.List;

import com.techsun.famouswine.domain.Browse;
import com.techsun.famouswine.domain.BrowseCount;
import com.techsun.famouswine.domain.BrowseResultSet;

public interface BrowseMapper {
    int deleteByPrimaryKey(Integer browseId);

    int insert(Browse record);

    int insertSelective(Browse record);

    Browse selectByPrimaryKey(Integer browseId);

    int updateByPrimaryKeySelective(Browse record);

    int updateByPrimaryKey(Browse record);
    
    BrowseCount selectBrowseCount(BrowseResultSet BrowseSet);
    
    List<BrowseResultSet> selectYeBrowseCount(BrowseResultSet entitySet);
    
}