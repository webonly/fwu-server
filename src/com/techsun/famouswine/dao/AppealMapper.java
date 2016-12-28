package com.techsun.famouswine.dao;

import java.util.List;

import com.techsun.famouswine.domain.Appeal;
import com.techsun.famouswine.domain.CommonParam;

public interface AppealMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Appeal record);

    int insertSelective(Appeal record);

    Appeal selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Appeal record);

    int updateByPrimaryKey(Appeal record);
    
    List<Appeal> selectAppealOrderList(CommonParam param);
}