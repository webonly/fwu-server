package com.techsun.famouswine.service;

import java.util.List;

import com.techsun.famouswine.domain.Category;

public interface CategoryService {
	//获取分类的类型
	List<Category> getCategoryList(Integer type);
}
