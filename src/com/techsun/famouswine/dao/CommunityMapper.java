package com.techsun.famouswine.dao;

import java.util.List;

import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.domain.Community;

public interface CommunityMapper {
    int deleteByPrimaryKey(Integer communityId);

    int insert(Community record);

    int insertSelective(Community record);

    Community selectByPrimaryKey(Integer communityId);

    int updateByPrimaryKeySelective(Community record);

    int updateByPrimaryKey(Community record);
    
    List<Community> getKnowledgeCommunity(CommonParam param);
}