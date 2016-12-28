package com.techsun.famouswine.dao;

import com.techsun.famouswine.domain.Balance;
import com.techsun.famouswine.domain.BalanceKey;
import com.techsun.famouswine.domain.CommonParam;

public interface BalanceMapper {
    int deleteByPrimaryKey(BalanceKey key);

    int insert(Balance record);

    int insertSelective(Balance record);

    Balance selectByPrimaryKey(BalanceKey key);

    int updateByPrimaryKeySelective(Balance record);

    int updateByPrimaryKey(Balance record);
    
    int insertOrUpdate(Balance record);
    
    Balance selectByUserType(CommonParam param);
}