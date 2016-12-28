package com.techsun.famouswine.service;

import java.util.List;

import com.techsun.famouswine.domain.Knowledge;




/**
 * 
* @ClassName: BannerService  
* @author zhanglijun
* @date 2015-8-15 1:52:33 
*
 */
public interface KnowledgeService {
	
	List<Knowledge> getKnowledgeList(int communityId, int pageNum, int pageSize);
	
	int publishKnowledge(Knowledge knowledge);
	
	

}
