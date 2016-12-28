package com.techsun.famouswine.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techsun.famouswine.domain.Category;
import com.techsun.famouswine.service.CategoryService;
import com.techsun.famouswine.util.ResultErrorInfo;
import com.techsun.famouswine.util.ResultSuccessInfo;

@Controller
public class CategoryController {
	private CategoryService categoryService;

	public CategoryService getCategoryService() {
		return categoryService;
	}
	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@RequestMapping(value="/category/getCategoryList")
	public @ResponseBody
	Object getCategoryList(HttpServletRequest request,HttpSession session, 
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String type = request.getParameter("type");
		if(type==null||type.equals("")){
			reInfo.setErrorMessage("获取类型失败！");
			reInfo.setErrorCode("10002");
			return reInfo;
		}
		List<Category> categoryList = null;
		try {
			categoryList = categoryService.getCategoryList(Integer.parseInt(type));
			if(categoryList.size()==0){
				reInfo.setErrorMessage("此类型为空！");
				reInfo.setErrorCode("10002");
				return reInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("获取类型失败");
			reInfo.setErrorCode("10002");
			return reInfo;
		}
		rsInfo.setErrorMessage("获取类型成功！");
		rsInfo.setResult(categoryList);
		return rsInfo;
	}
}
