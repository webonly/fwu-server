package com.techsun.famouswine.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.AccountMapper;
import com.techsun.famouswine.domain.Account;
import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.service.AccountService;

@Service("AccountService")
public class AccountServiceImpl implements AccountService {

	private AccountMapper accountMapper;
	
	
	public AccountMapper getAccountMapper() {
		return accountMapper;
	}
	@Autowired
	public void setAccountMapper(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}

	
	

	@Override
	public int addAccount(Account account) {
		int count=0;
		try {
			count = accountMapper.insert(account);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("添加账户失败！");
		}
		return count;
	}
	
	
	
	
	@Override
	public List<Account> getAccountList(Integer userId, Integer userType) {
		CommonParam param = new CommonParam();
		param.setUserId(userId);
		param.setUserType(userType);
		
		List<Account> accountList = null;
		try {
			accountList=accountMapper.getAccountList(param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取账户失败！");
		}
		return accountList;
	}

	@Override
	public Integer setDefaultAccount(Integer userId, Integer userType, Integer accountId) {
		CommonParam param = new CommonParam();
		param.setUserId(userId);
		param.setUserType(userType);
		
		int count = 0;
		try {
			Account account= accountMapper.selectDefaultAccount(param);
			if(account != null){
				account.setIsDefault(false);
				accountMapper.updateByPrimaryKeySelective(account);
			}

			account = new Account();
			account = accountMapper.selectByPrimaryKey(accountId);
			
			account.setIsDefault(true);;
			count = accountMapper.updateByPrimaryKeySelective(account);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取账户失败！");
		}
		return count;
	}

	@Override
	public Account getDefaultAccount(Integer userId, Integer userType) {
		CommonParam param = new CommonParam();
		param.setUserId(userId);
		param.setUserType(userType);
		
		Account account = null;
		try {
			account = accountMapper.selectDefaultAccount(param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取账户失败！");
		}
		return account;
	}
	

}
