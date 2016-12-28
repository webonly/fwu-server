package com.techsun.famouswine.domain;

import java.util.List;

public class CpMerchantSort{
	

    private Integer merchantId;
    
    private List<CartProductSort> cpsList;

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public List<CartProductSort> getCpsList() {
		return cpsList;
	}

	public void setCpsList(List<CartProductSort> cpsList) {
		this.cpsList = cpsList;
	}

    
}