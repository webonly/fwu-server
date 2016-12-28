package com.techsun.famouswine.service;

import java.util.List;

import com.techsun.famouswine.domain.KnowledgeComment;

public interface KnowledgeCommentService {
	//根据商品id获取商品评论
	List<KnowledgeComment> getCommentList(Integer knowledgeId,Integer pageNum,Integer pageSize);
	//添加评论
	int commentKnowledge(KnowledgeComment comment);
}
