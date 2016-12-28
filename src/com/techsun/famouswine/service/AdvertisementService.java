package com.techsun.famouswine.service;

import java.util.List;

import com.techsun.famouswine.domain.Advertisement;

public interface AdvertisementService {
	
	int addAdvertisement(Advertisement advertisement);
	
	List<Advertisement> getAdvertisementListByType(int type, int pageNum, int pageSize);
	List<Advertisement> getHomeAdvertisementList();	
}
