package com.techsun.famouswine.dao;

import java.util.List;

import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.domain.Knowledge;

public interface KnowledgeMapper {
    int deleteByPrimaryKey(Integer knowledgeId);

    int insert(Knowledge record);

    int insertSelective(Knowledge record);

    Knowledge selectByPrimaryKey(Integer knowledgeId);

    int updateByPrimaryKeySelective(Knowledge record);

    int updateByPrimaryKey(Knowledge record);
    
    List<Knowledge> getKnowledgeList(CommonParam param);
}