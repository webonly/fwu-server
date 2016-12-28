package com.techsun.famouswine.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.CommentMapper;
import com.techsun.famouswine.domain.Comment;
import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.service.CommentService;

@Service("CommentService")
public class CommentServiceImpl implements CommentService {

	private CommentMapper commentMapper;
	
	
	public CommentMapper getCommentMapper() {
		return commentMapper;
	}

	@Autowired
	public void setCommentMapper(CommentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}


	@Override
	public List<Comment> getComment(Integer productId,Integer pageNum,Integer pageSize) {
		List<Comment> commentList = new ArrayList<Comment>();
		CommonParam page = new CommonParam();
		page.setProductId(productId);
		page.setStartNum(pageNum-1);
		page.setEndNum(pageSize);
		try {
			commentList = commentMapper.getCommentByParam(page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取评论信息错误");
		}
		return commentList;
	}

	@Override
	public int addComment(Comment comment) {
		int count=0;
		if(comment.getCreatedDate()==null||comment.getCreatedDate().equals("")){
			comment.setCreatedDate(new Date());
		}
		try {
			commentMapper.updateByPrimaryKeySelective(comment);
			count=commentMapper.insert(comment);
			if(count<0){
				throw new Exception("添加评论失败！");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("添加评论失败！");
		}
		return count;
	}

	@Override
	public int getCommentByCondition(CommonParam productComment) {
		int count = 0;
		return count;
	}

}
