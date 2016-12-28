package com.techsun.famouswine.dao;

import java.util.List;

import com.techsun.famouswine.domain.Category;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
    
    List<Category> findCategoryList(Integer type);
}