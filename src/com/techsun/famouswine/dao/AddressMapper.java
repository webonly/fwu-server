package com.techsun.famouswine.dao;

import com.techsun.famouswine.domain.Address;
import com.techsun.famouswine.domain.CommonParam;

public interface AddressMapper {
    int deleteByPrimaryKey(Integer addressId);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer addressId);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);
    
    Address selectDefaultAddress(CommonParam param);
}