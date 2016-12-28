package com.techsun.famouswine.dao;

import com.techsun.famouswine.domain.ActivityProduct;
import com.techsun.famouswine.domain.ActivityProductKey;

public interface ActivityProductMapper {
    int deleteByPrimaryKey(ActivityProductKey key);

    int insert(ActivityProduct record);

    int insertSelective(ActivityProduct record);

    ActivityProduct selectByPrimaryKey(ActivityProductKey key);

    int updateByPrimaryKeySelective(ActivityProduct record);

    int updateByPrimaryKey(ActivityProduct record);
}