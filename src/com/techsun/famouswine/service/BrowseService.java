package com.techsun.famouswine.service;

import java.util.List;

import com.techsun.famouswine.domain.BrowseCount;
import com.techsun.famouswine.domain.BrowseResultSet;

public interface BrowseService {
	
	BrowseCount selectBrowseCount(Integer merchantId,String thyDate);
	  
	  List<BrowseResultSet> selectYeBrowseCount(Integer merchantId,String thyDate);
	
}
