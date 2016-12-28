package com.techsun.famouswine.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techsun.famouswine.domain.Evaluate;
import com.techsun.famouswine.service.EvaluateService;
import com.techsun.famouswine.util.ResultErrorInfo;
import com.techsun.famouswine.util.ResultSuccessInfo;

@Controller
public class EvaluateController {
	private EvaluateService evaluateService;

	public EvaluateService getEvaluateService() {
		return evaluateService;
	}

	@Autowired
	public void setEvaluateService(EvaluateService evaluateService) {
		this.evaluateService = evaluateService;
	}

	@RequestMapping(value = "/evaluate/addEvaluate")
	public @ResponseBody Object getEvaluate(HttpServletRequest request,
			HttpSession session, HttpServletResponse response) {
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String userId = request.getParameter("userId");
		String orderId = request.getParameter("orderId");
		String productAgreement = request.getParameter("productAgreement");
		String merchantService = request.getParameter("merchantService");
		String expressService = request.getParameter("expressService");
		String content = request.getParameter("content");

		if (userId == null || userId.equals("")) {
			reInfo.setErrorMessage("用户ID不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}

		if (orderId == null || orderId.equals("")) {
			reInfo.setErrorMessage("订单ID不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}

		if (productAgreement == null || productAgreement.equals("")) {
			reInfo.setErrorMessage("产品是否相符不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}

		if (merchantService == null || merchantService.equals("")) {
			reInfo.setErrorMessage("商家服务不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}

		if (expressService == null || expressService.equals("")) {
			reInfo.setErrorMessage("物流服务不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}

		Evaluate evaluate = new Evaluate();
		evaluate.setUserId(Integer.parseInt(userId));
		evaluate.setOrderId(Integer.parseInt(orderId));
		evaluate.setProductAgreement(Integer.parseInt(productAgreement));
		evaluate.setMerchantService(Integer.parseInt(merchantService));
		evaluate.setExpressService(Integer.parseInt(expressService));
		evaluate.setContent(content);

		int count = 0;
		try {
			count = evaluateService.addEvaluate(evaluate);
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("添加地址失败！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if (count > 0) {
			rsInfo.setErrorMessage("添加地址成功！");
			rsInfo.setResult(count);
		} else {
			reInfo.setErrorMessage("添加地址失败！");
			reInfo.setErrorCode("10005");
			return reInfo;

		}

		return rsInfo;
	}

	// 获取商家评价
	@RequestMapping(value = "/evaluate/getEvaluateListByMerchantId")
	public @ResponseBody Object getEvaluateListByMerchantId(
			HttpServletRequest request, HttpSession session,
			HttpServletResponse response) {
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String merchantId = request.getParameter("merchantId");
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");

		if (merchantId == null || merchantId.equals("")) {
			reInfo.setErrorMessage("商家ID不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if (pageNum == null || pageNum.equals("")) {
			reInfo.setErrorMessage("页码不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if (pageSize == null || pageSize.equals("")) {
			reInfo.setErrorMessage("页大小不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		List<Evaluate> evaluateList = null;
		try {
			evaluateList = evaluateService.getEvaluateListByMerchantId(
					Integer.parseInt(merchantId), Integer.parseInt(pageNum),
					Integer.parseInt(pageSize));
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("获取评价失败！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if (evaluateList == null) {
			reInfo.setErrorMessage("获取评价失败！");
			reInfo.setErrorCode("10005");
			return reInfo;

		}

		rsInfo.setErrorMessage("获取评价成功！");
		rsInfo.setResult(evaluateList);
		return rsInfo;
	}

	// 根据地址获取经度纬度
	@RequestMapping(value = "/evaluate/getEvaluateListByProductId")
	public @ResponseBody Object getEvaluateListByProductId(
			HttpServletRequest request, HttpSession session,
			HttpServletResponse response) {
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String productId = request.getParameter("productId");
		String merchantId = request.getParameter("merchantId");
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");

		if (productId == null || productId.equals("")) {
			reInfo.setErrorMessage("产品ID不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}

		if (merchantId == null || merchantId.equals("")) {
			reInfo.setErrorMessage("商家ID不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if (pageNum == null || pageNum.equals("")) {
			reInfo.setErrorMessage("页码不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if (pageSize == null || pageSize.equals("")) {
			reInfo.setErrorMessage("页大小不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		List<Evaluate> evaluateList = null;
		try {
			evaluateList = evaluateService.getEvaluateListByProductId(
					Integer.parseInt(productId), Integer.parseInt(merchantId),
					Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("添加地址失败！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if (evaluateList == null) {
			reInfo.setErrorMessage("添加地址失败！");
			reInfo.setErrorCode("10005");
			return reInfo;

		}

		rsInfo.setErrorMessage("获取商品评价成功！");
		rsInfo.setResult(evaluateList);
		return rsInfo;
	}

	@RequestMapping(value = "/evaluate/replyEvaluate")
	public @ResponseBody Object replyEvaluate(HttpServletRequest request,
			HttpSession session, HttpServletResponse response) {
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String orderId = request.getParameter("orderId");
		String reply = request.getParameter("reply");

		if (orderId == null || orderId.equals("")) {
			reInfo.setErrorMessage("订单ID不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}

		if (reply == null || reply.equals("")) {
			reInfo.setErrorMessage("评价回复不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}

		Evaluate evaluate = new Evaluate();
		evaluate.setOrderId(Integer.parseInt(orderId));
		evaluate.setReply(reply);

		int count = 0;
		try {
			count = evaluateService.modifyEvaluate(evaluate);
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("添加地址失败！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if (count > 0) {
			rsInfo.setErrorMessage("添加地址成功！");
			rsInfo.setResult(count);
		} else {
			reInfo.setErrorMessage("添加地址失败！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}

		return rsInfo;
	}

}
