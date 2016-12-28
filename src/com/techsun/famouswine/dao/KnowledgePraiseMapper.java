package com.techsun.famouswine.dao;

import com.techsun.famouswine.domain.KnowledgePraise;

public interface KnowledgePraiseMapper {
    int deleteByPrimaryKey(Integer praiseId);

    int insert(KnowledgePraise record);

    int insertSelective(KnowledgePraise record);

    KnowledgePraise selectByPrimaryKey(Integer praiseId);

    int updateByPrimaryKeySelective(KnowledgePraise record);

    int updateByPrimaryKey(KnowledgePraise record);
}