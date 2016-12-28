package com.techsun.famouswine.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.KnowledgeCommentMapper;
import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.domain.KnowledgeComment;
import com.techsun.famouswine.service.KnowledgeCommentService;

@Service("KnowledgeCommentService")
public class KnowledgeCommentServiceImpl implements KnowledgeCommentService {

	private KnowledgeCommentMapper knowledgeCommentMapper;
	



	public KnowledgeCommentMapper getKnowledgeCommentMapper() {
		return knowledgeCommentMapper;
	}
	@Autowired
	public void setKnowledgeCommentMapper(
			KnowledgeCommentMapper knowledgeCommentMapper) {
		this.knowledgeCommentMapper = knowledgeCommentMapper;
	}

	@Override
	public List<KnowledgeComment> getCommentList(Integer knowledgeId,Integer pageNum,Integer pageSize) {
		List<KnowledgeComment> commentList = new ArrayList<KnowledgeComment>();
		CommonParam page = new CommonParam();
		page.setKnowledgeId(knowledgeId);
		page.setStartNum(pageNum-1);
		page.setEndNum(pageSize);
		try {
			commentList = knowledgeCommentMapper.findCommentList(page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取评论信息错误");
		}
		return commentList;
	}


	@Override
	public int commentKnowledge(KnowledgeComment comment) {
		int count=0;
		if(comment.getCreateDate()==null||comment.getCreateDate().equals("")){
			comment.setCreateDate(new Date());
		}
		try {
			knowledgeCommentMapper.updateByPrimaryKeySelective(comment);
			count=knowledgeCommentMapper.insert(comment);
			if(count<0){
				throw new Exception("添加评论失败！");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("添加评论失败！");
		}
		return count;
	}

}
