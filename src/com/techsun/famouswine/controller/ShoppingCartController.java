package com.techsun.famouswine.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techsun.famouswine.domain.CartProduct;
import com.techsun.famouswine.domain.CartProductSort;
import com.techsun.famouswine.domain.CpMerchantSort;
import com.techsun.famouswine.domain.ShoppingCart;
import com.techsun.famouswine.service.ShoppingCartService;
import com.techsun.famouswine.util.ResultErrorInfo;
import com.techsun.famouswine.util.ResultSuccessInfo;

@Controller
public class ShoppingCartController {

	private ShoppingCartService shoppingCartService;

	public ShoppingCartService getShoppingCartService() {
		return shoppingCartService;
	}

	@Autowired
	public void setShoppingCartService(ShoppingCartService shoppingCartService) {
		this.shoppingCartService = shoppingCartService;
	}

	@RequestMapping(value = "/shoppingCart/getShoppingCartList")
	public @ResponseBody Object getShoppingCartList(HttpServletRequest request,
			HttpServletResponse response) {
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();

		String userId = request.getParameter("userId");
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");

		if (userId == null || userId.equals("")) {
			reInfo.setErrorMessage("用户ID不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}

		if (pageNum == null || pageNum.equals("")) {
			reInfo.setErrorMessage("页码不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}

		if (pageSize == null || pageSize.equals("")) {
			reInfo.setErrorMessage("页面大小不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}

		List<CartProduct> shoppingCarList = null;
		try {
			shoppingCarList = shoppingCartService.getShoppingCartList(
					Integer.parseInt(userId), Integer.parseInt(pageNum),
					Integer.parseInt(pageSize));

		} catch (Exception e) {
			reInfo.setErrorMessage("获取shoppingCart出错！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		if (shoppingCarList == null) {

			reInfo.setErrorMessage("获取shoppingCart出错！");
			reInfo.setErrorCode("10003");
			return reInfo;

		}

		HashSet<Integer> merchantIdHs = new HashSet<Integer>();

		List<CpMerchantSort> cpmList = new ArrayList<CpMerchantSort>();

		List<CartProduct> cpaList = new ArrayList<CartProduct>();

		HashMap<Integer, List<CartProductSort>> hs = new HashMap<Integer, List<CartProductSort>>();

		for (CartProduct cp : shoppingCarList) {
			merchantIdHs.add(cp.getMerchantId());
		}

		for (Integer mi : merchantIdHs) {
			CpMerchantSort cms = new CpMerchantSort();
			cms.setMerchantId(mi);
			cpmList.add(cms);
		}
		
		
		
		
		for (CpMerchantSort cms : cpmList) {
			
			List<CartProductSort> cpsList = new ArrayList<CartProductSort>();

			
		for (CartProduct cp : shoppingCarList) {

			
				if (cp.getMerchantId() == cms.getMerchantId()) {

					CartProductSort cps = new CartProductSort();
					cps.setActivityId(cp.getActivityId());
/*					if (cp.getActivityId() != 0) {
						List<CartProduct> cpList = new ArrayList<CartProduct>();
						cpList.add(cp);
						cps.setCpaList(cpList);
					} else {
						cps.setCartProduct(cp);
					}*/
					cps.setCartProduct(cp);
					cpsList.add(cps);
					
				}
			}
		
		cms.setCpsList(cpsList);

			/*
			 * if(hs.get(cp.getMerchantId()) == null){ CartProductSort cpss =
			 * new CartProductSort(); List<CartProduct> cpList = new
			 * ArrayList<CartProduct>();
			 * 
			 * if(cp.getActivityId() == 0){ cpss.setActivityId(0);
			 * cpss.setCartProduct(cp); }else{
			 * cpss.setActivityId(cp.getActivityId()); cpList.add(cp);
			 * cpss.setCartProductList(cpList); } cpmList.add(cms); }else{
			 * 
			 * 
			 * }
			 * 
			 * 
			 * List<CartProductSort> cpsList = new ArrayList<CartProductSort>();
			 * hs.put(cp.getMerchantId(), cpsList);
			 */

		}

		rsInfo.setResult(cpmList);
		rsInfo.setErrorMessage("获取shoppingCart成功！");
		return rsInfo;
	}

	@RequestMapping(value = "/shoppingCart/addProductToCart")
	public @ResponseBody Object addProductToCart(HttpServletRequest request,
			HttpServletResponse response) {
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String userId = request.getParameter("userId");
		String productId = request.getParameter("productId");
		String number = request.getParameter("number");
		String merchantId = request.getParameter("merchantId");

		if (userId == null || userId.equals("")) {
			reInfo.setErrorMessage("用户ID不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}

		if (number == null || number.equals("")) {
			reInfo.setErrorMessage("活动类型不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}

		if (productId == null || productId.equals("")) {
			reInfo.setErrorMessage("商品ID不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}

		if (merchantId == null || merchantId.equals("")) {
			reInfo.setErrorMessage("商家ID不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}

		ShoppingCart shoppingCart = null;
		try {
			shoppingCart = shoppingCartService.getShoppingCartByKey(
					Integer.parseInt(userId), 0, Integer.parseInt(productId),
					Integer.parseInt(merchantId));

		} catch (Exception e) {
			reInfo.setErrorMessage("添加商品失败！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}

		int count = 0;
		try {

			if (shoppingCart != null) {

				shoppingCart.setNumber(Integer.parseInt(number)
						+ shoppingCart.getNumber());
				count = shoppingCartService.updateProductToCart(shoppingCart);
			} else {
				shoppingCart = new ShoppingCart();
				shoppingCart.setUserId(Integer.parseInt(userId));
				shoppingCart.setProductId(Integer.parseInt(productId));
				shoppingCart.setActivityId(0);
				shoppingCart.setMerchantId(Integer.parseInt(merchantId));

				shoppingCart.setNumber(Integer.parseInt(number));
				count = shoppingCartService.addProductToCart(shoppingCart);
			}

		} catch (Exception e) {
			reInfo.setErrorMessage("添加商品失败！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}

		if (count > 0) {
			rsInfo.setResult(count);
			rsInfo.setErrorMessage("添加商品成功！");
		} else {
			reInfo.setErrorMessage("添加商品失败！");
			reInfo.setErrorCode("10003");
			return reInfo;

		}

		return rsInfo;
	}

	@RequestMapping(value = "/shoppingCart/addActivityToCart")
	public @ResponseBody Object addActivityToCart(HttpServletRequest request,
			HttpServletResponse response) {
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String userId = request.getParameter("userId");
		String activityId = request.getParameter("activityId");
		String number = request.getParameter("number");
		String merchantId = request.getParameter("merchantId");

		if (userId == null || userId.equals("")) {
			reInfo.setErrorMessage("用户ID不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}

		if (number == null || number.equals("")) {
			reInfo.setErrorMessage("商品数量不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}

		if (activityId == null || activityId.equals("")) {
			reInfo.setErrorMessage("组合商品ID不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}

		if (merchantId == null || merchantId.equals("")) {
			reInfo.setErrorMessage("商家ID不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}

		ShoppingCart shoppingCart = null;
		try {
			shoppingCart = shoppingCartService.getShoppingCartByKey(
					Integer.parseInt(userId), Integer.parseInt(activityId), 0,
					Integer.parseInt(merchantId));

		} catch (Exception e) {
			reInfo.setErrorMessage("添加商品失败！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}

		int count = 0;
		try {

			if (shoppingCart != null) {

				shoppingCart.setNumber(Integer.parseInt(number)
						+ shoppingCart.getNumber());
				count = shoppingCartService.updateProductToCart(shoppingCart);
			} else {
				shoppingCart = new ShoppingCart();
				shoppingCart.setUserId(Integer.parseInt(userId));
				shoppingCart.setProductId(0);
				shoppingCart.setActivityId(Integer.parseInt(activityId));
				shoppingCart.setMerchantId(Integer.parseInt(merchantId));

				shoppingCart.setNumber(Integer.parseInt(number));
				count = shoppingCartService.addProductToCart(shoppingCart);
			}

		} catch (Exception e) {
			reInfo.setErrorMessage("添加商品失败！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		if (count > 0) {
			rsInfo.setResult(count);
			rsInfo.setErrorMessage("添加商品成功！");
		} else {
			reInfo.setErrorMessage("添加商品失败！");
			reInfo.setErrorCode("10003");
			return reInfo;

		}

		return rsInfo;
	}

}
