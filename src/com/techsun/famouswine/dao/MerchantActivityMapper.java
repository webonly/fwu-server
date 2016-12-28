package com.techsun.famouswine.dao;

import com.techsun.famouswine.domain.MerchantActivity;
import com.techsun.famouswine.domain.MerchantActivityKey;

public interface MerchantActivityMapper {
    int deleteByPrimaryKey(MerchantActivityKey key);

    int insert(MerchantActivity record);

    int insertSelective(MerchantActivity record);

    MerchantActivity selectByPrimaryKey(MerchantActivityKey key);

    int updateByPrimaryKeySelective(MerchantActivity record);

    int updateByPrimaryKey(MerchantActivity record);
}