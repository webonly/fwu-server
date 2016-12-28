package com.techsun.famouswine.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.AppealMapper;
import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.domain.Appeal;
import com.techsun.famouswine.service.AppealService;

@Service("AppealService")
public class AppealServiceImpl implements AppealService {
	private AppealMapper appealMapper;
	
	

	public AppealMapper getAppealMapper() {
		return appealMapper;
	}


	@Autowired
	public void setAppealMapper(AppealMapper appealMapper) {
		this.appealMapper = appealMapper;
	}



	@Override
	public int addAppeal(Appeal appeal) {
		
		
		int count=0;
		try {
			count = appealMapper.insertSelective(appeal);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("添加地址失败！！");
		}
		return count;
	}
	
	
	@Override
	public int modifyAppeal(Appeal appeal) {

		int count=0;	
		try {
			count = appealMapper.updateByPrimaryKey(appeal);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("添加地址失败！！");
		}
		return count;
	}



	@Override
	public List<Appeal> getAppealOrderList(int merchantId,
			int pageNum, int pageSize) {
		CommonParam param = new CommonParam();
		param.setStartNum(pageNum-1);
		param.setEndNum(pageSize);
		param.setMerchantId(merchantId);
		
		List<Appeal> appealList = null;
		try {
			appealList = appealMapper.selectAppealOrderList(param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取评价失败！");
		}
		return appealList;
	}



}
