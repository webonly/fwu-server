package com.techsun.famouswine.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techsun.famouswine.domain.ProductEntity;
import com.techsun.famouswine.service.ProductService;
import com.techsun.famouswine.util.ResultErrorInfo;
import com.techsun.famouswine.util.ResultSuccessInfo;

@Controller
public class ProductController {
	private ProductService productService;
	public ProductService getProductService() {
		return productService;
	}
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	//根据商品id查询名酒
	@RequestMapping(value="/product/getProductDetail")
	public @ResponseBody
	Object getProductDetail(HttpServletRequest request,
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String productId = request.getParameter("productId");
		if(productId==null||productId.equals("")){
			reInfo.setErrorMessage("商品id为空！");
			reInfo.setErrorCode("10000");
			return reInfo;
		}
		ProductEntity product = null;
		try {
			product = productService.getProductDetail(Integer.parseInt(productId));
			if(product==null){
				reInfo.setErrorMessage("没有此商品！");
				reInfo.setErrorCode("10000");
				return reInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("获取商品失败！");
			reInfo.setErrorCode("10000");
		}
		rsInfo.setResult(product);
		rsInfo.setErrorMessage("获取商品成功！");
		return rsInfo;
	}
	
	//首页里根据名酒名称模糊查询名酒
	@RequestMapping(value="/product/getProductByName")
	public @ResponseBody
	Object getProductByName(HttpServletRequest request,
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String productName = request.getParameter("productName");
		String startPrice = request.getParameter("startPrice");
		String endPrice = request.getParameter("endPrice");
		Integer pageNum=Integer.parseInt(request.getParameter("pageNum"));
		Integer pageSize=Integer.parseInt(request.getParameter("pageSize"));
		String userLon = request.getParameter("userLon");
		String userLat = request.getParameter("userLat");
		Double startPrices,endPrices;
		if(userLon==null||userLon.equals("")){
			reInfo.setErrorMessage("用户经度为空！");
			reInfo.setErrorCode("10000");
			return reInfo;
		}
		if(userLat==null||userLat.equals("")){
			reInfo.setErrorMessage("用户纬度为空！");
			reInfo.setErrorCode("10000");
			return reInfo;
		}
		if(productName==null||productName.equals("")){
			reInfo.setErrorMessage("名酒名称为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		if(pageNum==null||pageNum.equals("")){
			reInfo.setErrorMessage("页码不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		
		if(pageSize==null||pageSize.equals("")){
			reInfo.setErrorMessage("页大小不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		if(startPrice==null||startPrice.equals(""))
			startPrices=0.0;
		else
			startPrices = Double.parseDouble(startPrice);
		if(endPrice==null||endPrice.equals(""))
			endPrices=0.0;
		else
			endPrices = Double.parseDouble(endPrice);
		
		List<ProductEntity> productEntity=new ArrayList<ProductEntity>();
			try {
				productEntity=productService.getProductByName(Double.parseDouble(userLon),Double.parseDouble(userLat)
						,productName,pageNum,pageSize,startPrices,endPrices);
				if(productEntity.size()==0){
					reInfo.setErrorMessage("商品不存在！");
					reInfo.setErrorCode("10006");
					return reInfo;
				}
			} catch (Exception e) {
				e.printStackTrace();
				reInfo.setErrorMessage("商品不存在！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
		
		rsInfo.setResult(productEntity);
		rsInfo.setErrorMessage("查询成功！");
		return rsInfo;
	}
	
	//店铺里根据名酒名称模糊查询名酒
	@RequestMapping(value="/product/getMerchantProductByName")
	public @ResponseBody
	Object getMerchantProductByName(HttpServletRequest request,
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		Integer merchantId = Integer.parseInt(request.getParameter("merchantId"));
		String productName = request.getParameter("productName");
		Integer pageNum=Integer.parseInt(request.getParameter("pageNum"));
		Integer pageSize=Integer.parseInt(request.getParameter("pageSize"));
		if(productName==null||productName.equals("")){
			reInfo.setErrorMessage("名酒名称为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		if(pageNum==null||pageNum.equals("")){
			reInfo.setErrorMessage("页码不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		
		if(pageSize==null||pageSize.equals("")){
			reInfo.setErrorMessage("页大小不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		List<ProductEntity> productEntity=new ArrayList<ProductEntity>();
		
			try {
				productEntity=productService.getMerchantProductByName(merchantId, productName, pageNum, pageSize);
				if(productEntity.size()<0){
					reInfo.setErrorMessage("商品不存在！");
					reInfo.setErrorCode("10006");
					return reInfo;
				}
			} catch (Exception e) {
				e.printStackTrace();
				reInfo.setErrorMessage("商品不存在！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
		
		rsInfo.setResult(productEntity);
		rsInfo.setErrorMessage("查询成功！");
		return rsInfo;
	}
	//店铺商品
		@RequestMapping(value="/product/getProductByType")
		public @ResponseBody
		Object getProductByType(HttpServletRequest request,
				HttpServletResponse response){
			ResultSuccessInfo rsInfo = new ResultSuccessInfo();
			ResultErrorInfo reInfo = new ResultErrorInfo();
			String productType = request.getParameter("productType");
			String merchantId = request.getParameter("merchantId");
			Integer pageNum=Integer.parseInt(request.getParameter("pageNum"));
			Integer pageSize=Integer.parseInt(request.getParameter("pageSize"));
			Integer type=0;
			if(productType==null||productType.equals("")){
				type=0;
			}else{
				type = Integer.parseInt(productType);
			}
			if(merchantId==null||merchantId.equals("")){
				reInfo.setErrorMessage("商家信息为空！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
			if(pageNum==null||pageNum.equals("")){
				reInfo.setErrorMessage("页码不能为空！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
			
			if(pageSize==null||pageSize.equals("")){
				reInfo.setErrorMessage("页大小不能为空！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
			List<ProductEntity> productEntity=new ArrayList<ProductEntity>();
			try {
				productEntity=productService.getProductByType(type,Integer.parseInt(merchantId),pageNum,pageSize);
				if(productEntity.size()==0){
					reInfo.setErrorMessage("没有此种商品！");
					reInfo.setErrorCode("10006");
					return reInfo;
				}
			} catch (Exception e) {
				e.printStackTrace();
				reInfo.setErrorMessage("获取商品失败！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
			rsInfo.setResult(productEntity);
			rsInfo.setErrorMessage("获取商品成功！");
			return rsInfo;
		}
		//选酒
		@RequestMapping(value="/product/getProductByParam")
		public @ResponseBody
		Object getProductByParam(HttpServletRequest request,
				HttpServletResponse response){
			ResultSuccessInfo rsInfo = new ResultSuccessInfo();
			ResultErrorInfo reInfo = new ResultErrorInfo();
			String productType = request.getParameter("productType");
			Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
			Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
			Integer type=0;
			if(productType==null||productType.equals("")){
				type=0;
			}
			else{
				type = Integer.parseInt(productType);
			}
			if(pageNum==null||pageNum.equals("")){
				reInfo.setErrorMessage("页码不能为空！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
			
			if(pageSize==null||pageSize.equals("")){
				reInfo.setErrorMessage("页大小不能为空！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
			List<ProductEntity> productList=null;
			try {
				productList = productService.getProductByParam(type,pageNum, pageSize);
			} catch (Exception e) {
				e.printStackTrace();
				reInfo.setErrorMessage("获取商品失败！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
			rsInfo.setResult(productList);
			rsInfo.setErrorMessage("获取商品成功！");
			return rsInfo;
		}

}
