package com.techsun.famouswine.dao;

import java.util.List;

import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.domain.KnowledgeComment;

public interface KnowledgeCommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(KnowledgeComment record);

    int insertSelective(KnowledgeComment record);

    KnowledgeComment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(KnowledgeComment record);

    int updateByPrimaryKey(KnowledgeComment record);
    //获取名酒知识评论
    List<KnowledgeComment> findCommentList(CommonParam page);
    
}