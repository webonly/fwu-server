package com.techsun.famouswine.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.AdvertisementMapper;
import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.domain.Advertisement;
import com.techsun.famouswine.service.AdvertisementService;

@Service("AdvertisementService")
public class AdvertisementServiceImpl implements AdvertisementService {
	private AdvertisementMapper advertisementMapper;
	
	

	public AdvertisementMapper getAdvertisementMapper() {
		return advertisementMapper;
	}


	@Autowired
	public void setAdvertisementMapper(AdvertisementMapper advertisementMapper) {
		this.advertisementMapper = advertisementMapper;
	}



	@Override
	public int addAdvertisement(Advertisement advertisement) {
		
		
		int count=0;
		try {
			count = advertisementMapper.insertSelective(advertisement);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("添加地址失败！！");
		}
		return count;
	}
	
	@Override
	public List<Advertisement> getAdvertisementListByType(int type, int pageNum, int pageSize) {
		CommonParam param = new CommonParam();
		param.setStartNum(pageNum-1);
		param.setEndNum(pageSize);
		param.setType(type);
		
		List<Advertisement> advertisementList = null;
		try {
			advertisementList = advertisementMapper.selectAdvertisementListByType(param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取评价失败！");
		}
		return advertisementList;
	}


	@Override
	public List<Advertisement> getHomeAdvertisementList() {

		
		List<Advertisement> advertisementList = null;
		try {
			advertisementList = advertisementMapper.selectHomeAdvertisementList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取评价失败！");
		}
		return advertisementList;
	}

}
