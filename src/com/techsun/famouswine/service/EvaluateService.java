package com.techsun.famouswine.service;

import java.util.List;

import com.techsun.famouswine.domain.Evaluate;

public interface EvaluateService {
	
	int addEvaluate(Evaluate evaluate);
	
	int modifyEvaluate(Evaluate evaluate);
	
	int deleteEvaluate(int evaluateId);
	
	List<Evaluate> getEvaluateListByUserId(int userId, int pageNum, int pageSize);
	
	List<Evaluate> getEvaluateListByMerchantId(int merchantId, int pageNum, int pageSize);

	List<Evaluate> getEvaluateListByProductId(int productId, int merchantId,
			int pageNum, int pageSize);	
}
