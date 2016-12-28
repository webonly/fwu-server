package com.techsun.famouswine.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.CategoryMapper;
import com.techsun.famouswine.domain.Category;
import com.techsun.famouswine.service.CategoryService;
@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService {

	private CategoryMapper categoryMapper;
	
	public CategoryMapper getCategoryMapper() {
		return categoryMapper;
	}
	@Autowired
	public void setCategoryMapper(CategoryMapper categoryMapper) {
		this.categoryMapper = categoryMapper;
	}



	@Override
	public List<Category> getCategoryList(Integer type) {
		List<Category> categoryList = null;
		try {
			categoryList = categoryMapper.findCategoryList(type);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取类型失败！");
		}
		return categoryList;
	}

}
