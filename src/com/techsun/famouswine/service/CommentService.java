package com.techsun.famouswine.service;

import java.util.List;

import com.techsun.famouswine.domain.Comment;
import com.techsun.famouswine.domain.CommonParam;

public interface CommentService {
	//根据商品id获取商品评论
	List<Comment> getComment(Integer productId,Integer pageNum,Integer pageSize);
	//添加评论
	int addComment(Comment comment);
	//根据商品id获取各种评论数
	int getCommentByCondition(CommonParam productComment);
}
