package com.techsun.famouswine.service;

import java.util.List;

import com.techsun.famouswine.domain.Account;

public interface AccountService {
	//添加账户
	int addAccount(Account account);
	
	//获取账户列表
	List<Account> getAccountList(Integer userId, Integer userType);
	//设置默认账户
	Integer setDefaultAccount(Integer userId, Integer userType, Integer accountId);
	//获取默认账户
	Account getDefaultAccount(Integer userId, Integer userType);

	
	
}
