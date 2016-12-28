package com.techsun.famouswine.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.EvaluateMapper;
import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.domain.Evaluate;
import com.techsun.famouswine.service.EvaluateService;

@Service("EvaluateService")
public class EvaluateServiceImpl implements EvaluateService {
	private EvaluateMapper evaluateMapper;
	
	

	public EvaluateMapper getEvaluateMapper() {
		return evaluateMapper;
	}


	@Autowired
	public void setEvaluateMapper(EvaluateMapper evaluateMapper) {
		this.evaluateMapper = evaluateMapper;
	}



	@Override
	public int addEvaluate(Evaluate evaluate) {
		
		
		int count=0;
		try {
			count = evaluateMapper.insertSelective(evaluate);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("添加地址失败！！");
		}
		return count;
	}
	
	
	@Override
	public int modifyEvaluate(Evaluate evaluate) {
		
		
		int count=0;
		try {
			count = evaluateMapper.updateByPrimaryKeySelective(evaluate);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("修改评价失败！！");
		}
		return count;
	}
	
	
	
	
	@Override
	public int deleteEvaluate(int evaluateId) {

		int count=0;	
		try {
			count = evaluateMapper.deleteByPrimaryKey(evaluateId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("添加地址失败！！");
		}
		return count;
	}



	@Override
	public List<Evaluate> getEvaluateListByMerchantId(int merchantId,
			int pageNum, int pageSize) {
		CommonParam param = new CommonParam();
		param.setStartNum(pageNum-1);
		param.setEndNum(pageSize);
		param.setMerchantId(merchantId);
		
		List<Evaluate> evaluateList = null;
		try {
			evaluateList = evaluateMapper.selectEvaluateListByMerchantId(param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取评价失败！");
		}
		return evaluateList;
	}


	@Override
	public List<Evaluate> getEvaluateListByProductId(int productId,
			int merchantId, int pageNum, int pageSize) {
		CommonParam param = new CommonParam();
		param.setStartNum(pageNum-1);
		param.setEndNum(pageSize);
		param.setMerchantId(merchantId);
		param.setProductId(productId);
		
		List<Evaluate> evaluateList = null;
		try {
			evaluateList = evaluateMapper.selectEvaluateListByProductId(param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取评价失败！");
		}
		return evaluateList;
	}


	@Override
	public List<Evaluate> getEvaluateListByUserId(int userId, int pageNum,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}


	


	


	

}
