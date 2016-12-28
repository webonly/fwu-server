package com.techsun.famouswine.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.DealMapper;
import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.domain.Deal;
import com.techsun.famouswine.service.DealService;
@Service("DealService")
public class DealServiceImpl implements DealService {
	
	private DealMapper dealMapper;
	
	public DealMapper getDealMapper() {
		return dealMapper;
	}
	@Autowired
	public void setDealMapper(DealMapper dealMapper) {
		this.dealMapper = dealMapper;
	}

	@Override
	public int addDeal(Deal deal) {
		int count=0;
		if(deal.getCreatedDate()==null||deal.getCreatedDate().equals("")){
			deal.setCreatedDate(new Date());
		}
		if(deal.getDealType()==1){
			deal.setContent("账户充值");
		}if(deal.getDealType()==2){
			deal.setContent("账户提现");
		}if(deal.getDealType()==3){
			deal.setContent("购物支付");
		}
		try {
			dealMapper.updateByPrimaryKeySelective(deal);
			count = dealMapper.insertSelective(deal);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("添加交易记录失败！");
		}
		return count;
	}

	@Override
	public List<Deal> getDealListByDealType(Integer userId,Integer userType,Integer dealType,
			Integer pageNum,Integer pageSize) {
		List<Deal> dealList=new ArrayList<Deal>();
		CommonParam page = new CommonParam();
		page.setUserId(userId);
		page.setUserType(userType);;
		page.setDealType(dealType);
		page.setStartNum(pageNum-1);
		page.setEndNum(pageSize);
		try {
			dealList = dealMapper.findUserDealList(page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取交易记录失败！");
		}
		return dealList;
	}
	
	
	@Override
	public List<Deal> getAllDealList(Integer userId,Integer userType,
			Integer pageNum,Integer pageSize) {
		List<Deal> dealList=new ArrayList<Deal>();
		CommonParam page = new CommonParam();
		page.setUserId(userId);
		page.setUserType(userType);
		page.setStartNum(pageNum-1);
		page.setEndNum(pageSize);
		try {
			dealList = dealMapper.selectAllDealList(page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取交易记录失败！");
		}
		return dealList;
	}



}
