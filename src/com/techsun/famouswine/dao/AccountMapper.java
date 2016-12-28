package com.techsun.famouswine.dao;

import java.util.List;

import com.techsun.famouswine.domain.Account;
import com.techsun.famouswine.domain.CommonParam;

public interface AccountMapper {
    int deleteByPrimaryKey(Integer accountId);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer accountId);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
    
    List<Account> getAccountList(CommonParam param);
    
    Account selectDefaultAccount(CommonParam param);
    
 
}