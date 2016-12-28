package com.techsun.famouswine.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.FeedbackMapper;
import com.techsun.famouswine.domain.Feedback;
import com.techsun.famouswine.service.FeedBackService;

@Service("FeedBackService")
public class FeedBackServiceImpl implements FeedBackService {

	private FeedbackMapper feedBackMapper;

	public FeedbackMapper getFeedBackMapper() {
		return feedBackMapper;
	}

	@Autowired
	public void setFeedBackMapper(FeedbackMapper feedBackMapper) {
		this.feedBackMapper = feedBackMapper;
	}

	@Override
	public int addFeedBack(Feedback feedback) {
		int count;
		if(feedback.getCreatedDate()==null||feedback.equals("")){
			feedback.setCreatedDate(new Date());
		}
		try {
			feedBackMapper.updateByPrimaryKeySelective(feedback);
			count = feedBackMapper.insertSelective(feedback);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("添加失败");
		}
		return count;

	}
}
