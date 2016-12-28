package com.techsun.famouswine.service;

import java.util.List;

import com.techsun.famouswine.domain.Community;





public interface CommunityService {
	
	List<Community> getKnowledgeCommunity(int type, int pageNum, int pageSize) ;
	
	

}
