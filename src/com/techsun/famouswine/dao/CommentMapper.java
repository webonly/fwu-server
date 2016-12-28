package com.techsun.famouswine.dao;

import java.util.List;

import com.techsun.famouswine.domain.Comment;
import com.techsun.famouswine.domain.CommonParam;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
    //根据用户id和商品id获取商品评论
  	List<Comment> getCommentByParam(CommonParam page);
  	//根据商品id获取各种评论数
  	int findCommentByCondition(CommonParam productComment);
}