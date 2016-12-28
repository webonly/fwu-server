package com.techsun.famouswine.service;

import com.techsun.famouswine.domain.Balance;

public interface BalanceService {
	int modifyBalance(int userId, int userType, double money);
	int addBalance(int userId, int userType, double money);
	
	Balance getBalance(int userId, int userType);	
}
