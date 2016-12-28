package com.techsun.famouswine.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.BalanceMapper;
import com.techsun.famouswine.domain.Balance;
import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.service.BalanceService;


@Service("BalanceService")
public class BalanceServiceImpl implements BalanceService {

	private BalanceMapper balanceMapper;

	public BalanceMapper getBalanceMapper() {
		return balanceMapper;
	}

	@Autowired
	public void setBalanceMapper(BalanceMapper balanceMapper) {
		this.balanceMapper = balanceMapper;
	}

	@Override
	public int modifyBalance(int userId, int userType, double money) {
		
		Balance balance = new Balance();
		balance.setUserId(userId);
		balance.setUserType(userType);
		balance.setBalance(money);
		int count = 0;
		
		try{
			count = balanceMapper.updateByPrimaryKey(balance);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取balance页面失败");
		}
		return count;
	}

	@Override
	public Balance getBalance(int userId, int userType) {
		CommonParam param = new CommonParam();
		param.setUserId(userId);
		param.setUserType(userType);
		Balance balance = null;
		
		try{
			balance = balanceMapper.selectByUserType(param);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取balance页面失败");
		}
		return balance;
	}

	@Override
	public int addBalance(int userId, int userType, double money) {
		Balance balance = new Balance();
		balance.setUserId(userId);
		balance.setUserType(userType);
		balance.setBalance(money);
		int count = 0;
		
		try{
			count = balanceMapper.insertSelective(balance);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取balance页面失败");
		}
		return count;
	}

	

}
