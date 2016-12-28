package com.techsun.famouswine.service;




import com.techsun.famouswine.domain.Address;

public interface AddressService {
	
	int addAddress(Address address);
	
	int deleteAddress(int addressId);
	
	//设置默认地址
	Integer setDefaultAddress(Integer userId, Integer addressId);
	//获取默认地址
	Address getDefaultAddress(Integer userId, Integer userType);
}
