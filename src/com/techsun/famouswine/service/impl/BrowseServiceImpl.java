package com.techsun.famouswine.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.BrowseMapper;
import com.techsun.famouswine.domain.BrowseCount;
import com.techsun.famouswine.domain.BrowseResultSet;
import com.techsun.famouswine.service.BrowseService;

@Service("BrowseService")
public class BrowseServiceImpl implements BrowseService {

	private BrowseMapper browseMapper ;

	
	public BrowseMapper getBrowseMapper() {
		return browseMapper;
	}

	@Autowired
	public void setBrowseMapper(BrowseMapper browseMapper) {
		this.browseMapper = browseMapper;
	}

	@Override
	public BrowseCount selectBrowseCount(Integer merchantId,String thyDate) {
		BrowseCount browse;
		BrowseResultSet entitySet=new BrowseResultSet();
		entitySet.setCreatedDate(thyDate);
		entitySet.setMerchantId(merchantId);
		try {
			browse = browseMapper.selectBrowseCount(entitySet);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("统计流量统计错误");
		}
		return browse;
	}
	
	@Override
	public List<BrowseResultSet> selectYeBrowseCount(Integer merchantId,String thyDate) {
		List<BrowseResultSet> browse;
		BrowseResultSet entitySet=new BrowseResultSet();
		entitySet.setCreatedDate(thyDate);
		entitySet.setMerchantId(merchantId);
		try {
			browse = browseMapper.selectYeBrowseCount(entitySet);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("前一日统计流量统计错误");
		}
		return browse;
	}

	
}
