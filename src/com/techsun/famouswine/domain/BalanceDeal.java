package com.techsun.famouswine.domain;

import java.util.List;

public class BalanceDeal {
    private Double balance;

    private List<Deal> dealList;

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public List<Deal> getDealList() {
		return dealList;
	}

	public void setDealList(List<Deal> dealList) {
		this.dealList = dealList;
	}

    
}