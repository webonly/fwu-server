package com.techsun.famouswine.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.BannerMapper;
import com.techsun.famouswine.domain.Banner;
import com.techsun.famouswine.service.BannerService;


@Service("BannerService")
public class BannerServiceImpl implements BannerService {

	private BannerMapper bannerMapper;

	public BannerMapper getBannerMapper() {
		return bannerMapper;
	}

	@Autowired
	public void setBannerMapper(BannerMapper bannerMapper) {
		this.bannerMapper = bannerMapper;
	}

	@Override
	public List<Banner> getAllBanner() {
		
		List<Banner> bannerList;
		try{
			bannerList = bannerMapper.getAllBanner();
		} catch(Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取banner页面失败");
		}
		return bannerList;
	}

	

}
