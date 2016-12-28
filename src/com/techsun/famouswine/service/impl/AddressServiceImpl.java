package com.techsun.famouswine.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.AddressMapper;
import com.techsun.famouswine.domain.Address;
import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.service.AddressService;

@Service("AddressService")
public class AddressServiceImpl implements AddressService {
	private AddressMapper addressMapper;
	
	

	public AddressMapper getAddressMapper() {
		return addressMapper;
	}


	@Autowired
	public void setAddressMapper(AddressMapper addressMapper) {
		this.addressMapper = addressMapper;
	}



	@Override
	public int addAddress(Address address) {
		int count=0;
		if(address.getIsDefault()==null||address.getIsDefault().equals(""))
			address.setIsDefault(false);
		if(address.getCreatedDate()==null||address.getCreatedDate().equals("")){
			address.setCreatedDate(new Date());
		}
		try {
			addressMapper.updateByPrimaryKeySelective(address);
			count = addressMapper.insertSelective(address);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("添加地址失败！！");
		}
		return count;
	}
	
	
	@Override
	public int deleteAddress(int addressId) {

		int count=0;	
		try {
			count = addressMapper.deleteByPrimaryKey(addressId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("添加地址失败！！");
		}
		return count;
	}

	@Override
	public Integer setDefaultAddress(Integer userId, Integer addressId) {
		CommonParam param = new CommonParam();
		param.setUserId(userId);
		param.setUserType(addressId);	
		int count = 0;
		Address address = null;
		try {
			address= addressMapper.selectDefaultAddress(param);
			if(address != null){
				address.setIsDefault(false);
				addressMapper.updateByPrimaryKeySelective(address);
			}

			address = new Address();
			address = addressMapper.selectByPrimaryKey(addressId);
			
			address.setIsDefault(true);;
			count = addressMapper.updateByPrimaryKeySelective(address);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取地址失败！");
		}
		return count;
	}

	@Override
	public Address getDefaultAddress(Integer userId, Integer userType) {
		CommonParam param = new CommonParam();
		param.setUserId(userId);
		param.setUserType(userType);
		
		Address address = null;
		try {
			address = addressMapper.selectDefaultAddress(param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取地址失败！");
		}
		return address;
	}
	


	


	

}
