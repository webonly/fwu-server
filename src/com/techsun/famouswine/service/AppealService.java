package com.techsun.famouswine.service;

import java.util.List;

import com.techsun.famouswine.domain.Appeal;

public interface AppealService {
	
	int addAppeal(Appeal appeal);
	
	int modifyAppeal(Appeal appeal);
	
	List<Appeal> getAppealOrderList(int merchantId, int pageNum, int pageSize);
	
}
