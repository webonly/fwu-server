package com.techsun.famouswine.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.KnowledgeMapper;
import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.domain.Knowledge;
import com.techsun.famouswine.service.KnowledgeService;


@Service("KnowledgeService")
public class KnowledgeServiceImpl implements KnowledgeService {

	private KnowledgeMapper knowledgeMapper;

	public KnowledgeMapper getKnowledgeMapper() {
		return knowledgeMapper;
	}

	@Autowired
	public void setKnowledgeMapper(KnowledgeMapper knowledgeMapper) {
		this.knowledgeMapper = knowledgeMapper;
	}



	@Override
	public List<Knowledge> getKnowledgeList(int communityId, int pageNum, int pageSize) {
		CommonParam param = new CommonParam();
		param.setStartNum(pageNum-1);
		param.setEndNum(pageSize);
		param.setCommunityId(communityId);
		
		
		List<Knowledge> knowledgeList;
		try{
			knowledgeList = knowledgeMapper.getKnowledgeList(param);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取knowledge页面失败");
		}
		return knowledgeList;
	}

	@Override
	public int publishKnowledge(Knowledge knowledge) {
		int count=0;	
		if(knowledge.getCreatedDate()==null||knowledge.getCreatedDate().equals("")){
			knowledge.setCreatedDate(new Date());
		}
		try {
			knowledgeMapper.updateByPrimaryKeySelective(knowledge);
			count = knowledgeMapper.insertSelective(knowledge);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("发布名酒知识失败！！");
		}
		return count;
		
		
		
		
	}

	

}
