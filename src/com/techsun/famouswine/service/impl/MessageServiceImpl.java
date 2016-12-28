package com.techsun.famouswine.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.MessageMapper;
import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.domain.ConversationUser;
import com.techsun.famouswine.domain.Message;
import com.techsun.famouswine.domain.MessageType;
import com.techsun.famouswine.domain.MessageUser;
import com.techsun.famouswine.service.MessageService;

@Service("MessageService")
public class MessageServiceImpl implements MessageService {
		
	private MessageMapper messageMapper;

	public MessageMapper getMessageMapper() {
		return messageMapper;
	}
	@Autowired
	public void setMessageMapper(MessageMapper messageMapper) {
		this.messageMapper = messageMapper;
	}
	  
	
	
	
	@Override
	public int addMessage(Message message) {
		int count=0;
		
		try {
		count= messageMapper.insertSelective(message);
		     } catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("增加信息失败");
		}
		return count;
	}
	
	@Override
	public List<Message> getMessageByToUserId(MessageType messageType) {
		
	  List<Message> message =new ArrayList<Message>();
		try{
			message= messageMapper.selectMessageByToUserId(messageType);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取消息失败！");
		}
		return message;
	         }
	@Override
	public List<MessageUser> getMessageUser(int toUserId) {

		List<MessageUser> message =new ArrayList<MessageUser>();
			try{
				message= messageMapper.selectMessageUser(toUserId);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationContextException("获取消息失败！");
			}
			return message;
	}
	@Override
	public List<MessageUser> getMessageUserOnConversation(int myUserId, int friendUserId) {

		ConversationUser param = new ConversationUser();
		param.setMyUserId(myUserId);
		param.setFriendUserId(friendUserId);
		
		  List<MessageUser> message =new ArrayList<MessageUser>();
			try{
				message= messageMapper.selectMessageUserOnConversation(param);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationContextException("获取消息失败！");
			}
			return message;
	}
	
	@Override
	public List<Message> getSystemMessage(int userId) {
		List<Message> message =new ArrayList<Message>();
		try{
			message= messageMapper.selectSystemMessage(userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取消息失败！");
		}
		return message;
	}
	@Override
	public List<MessageUser> getSystemMessageByType(int userId, int type) {
		CommonParam param = new CommonParam();
		param.setUserId(userId);
		param.setType(type);
		
		  List<MessageUser> message =new ArrayList<MessageUser>();
			try{
				message= messageMapper.selectSystemMessageByType(param);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationContextException("获取消息失败！");
			}
			return message;
	}

}
