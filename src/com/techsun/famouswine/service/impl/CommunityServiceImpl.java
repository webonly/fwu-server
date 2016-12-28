package com.techsun.famouswine.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.CommunityMapper;
import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.domain.Community;
import com.techsun.famouswine.service.CommunityService;

@Service("CommunityService")
public class CommunityServiceImpl implements CommunityService {

	private CommunityMapper communityMapper;

	public CommunityMapper getCommunityMapper() {
		return communityMapper;
	}

	@Autowired
	public void setCommunityMapper(CommunityMapper communityMapper) {
		this.communityMapper = communityMapper;
	}



	@Override
	public List<Community> getKnowledgeCommunity(int type, int pageNum, int pageSize) {
		CommonParam param = new CommonParam();
		param.setStartNum((pageNum-1)*pageSize);
		param.setEndNum(pageNum*pageSize);
		param.setType(type);
		
		
		List<Community> communityList;
		try{
			communityList = communityMapper.getKnowledgeCommunity(param);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取community页面失败");
		}
		return communityList;
	}

	

}
