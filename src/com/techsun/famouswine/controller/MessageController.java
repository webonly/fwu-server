package com.techsun.famouswine.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techsun.famouswine.domain.Message;
import com.techsun.famouswine.domain.MessageType;
import com.techsun.famouswine.domain.MessageUser;
import com.techsun.famouswine.service.MessageService;
import com.techsun.famouswine.util.ResultErrorInfo;
import com.techsun.famouswine.util.ResultSuccessInfo;

@Controller
public class MessageController {
	private MessageService messageService;

	public MessageService getMessageService() {
		return messageService;
	}
	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	
	
	
	
	@RequestMapping(value = "/sendMessage")
	public @ResponseBody
	Object sendMessage(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		ResultErrorInfo reInfo = new ResultErrorInfo();
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		response.addHeader("Access-Control-Allow-Origin","*");
		String fromId = request.getParameter("fromId");
		String toId = request.getParameter("toId");
		String type = request.getParameter("type");
		String content = request.getParameter("content");
		
		
		if (fromId == null || fromId.equals("")) {
			reInfo.setErrorMessage("发送用户ID不能为空！");
			reInfo.setErrorCode("10002");
			return reInfo;
		}
		if (toId == null || toId.equals("")) {
			reInfo.setErrorMessage("接收用户ID不能为空！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		if (type == null || type.equals("")) {
			reInfo.setErrorMessage("消息类型不能为空！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		
		if (content == null || content.equals("")) {
			reInfo.setErrorMessage("消息内容不能为空！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		
		
	  Message message= new Message();
	 
	  message.setFromId(Integer.parseInt(fromId));
	  message.setToId(Integer.parseInt(toId));	  
	  message.setType(Integer.parseInt(type));
	  message.setContent(content);
	  
	  int count=0;
	  try {
		count=messageService.addMessage(message);
	} catch (Exception e) {
		reInfo.setErrorMessage("消息发送失败！");
		reInfo.setErrorCode("10000");
		return reInfo;
	}
	    rsInfo.setErrorMessage("消息发送成功！");
		rsInfo.setResult(count);
	return rsInfo;
		
	}
	
	@RequestMapping(value = "/getMessage")//通过type的不同取到不同的消息
	public @ResponseBody
	Object getMessage(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		ResultErrorInfo reInfo = new ResultErrorInfo();
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		response.addHeader("Access-Control-Allow-Origin","*");
		String toId = request.getParameter("toId");
		String type = request.getParameter("type");
		
		
		if (toId == null || toId.equals("")) {
			reInfo.setErrorMessage("接收用户ID不能为空！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		if (type == null || type.equals("")) {
			reInfo.setErrorMessage("消息类型不能为空！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		
		
		MessageType messageType= new MessageType();
		messageType.setToUserId(Integer.parseInt(toId));
		messageType.setType(Integer.parseInt(type));
		
		
		List<Message> message= new ArrayList<Message>();
      try {
		  message=messageService.getMessageByToUserId(messageType);
	} catch (Exception e) {
		reInfo.setErrorMessage("得到消息失败！");
		reInfo.setErrorCode("10000");
		return reInfo;
	}
	  
	    if (message!=null) {
	    	rsInfo.setErrorMessage("消息发送成功！");
			rsInfo.setResult(message);
		}
	    return rsInfo;	
	}
	
	
	@RequestMapping(value = "/getMessageUser")//通过type的不同渠道不同的消息
	public @ResponseBody
	Object getMessageUser(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		ResultErrorInfo reInfo = new ResultErrorInfo();
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		response.addHeader("Access-Control-Allow-Origin","*");
		
		String toUserId = request.getParameter("toUserId");
				
		if (toUserId == null || toUserId.equals("")) {
			reInfo.setErrorMessage("接收用户ID不能为空！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		
		List<MessageUser> message= new ArrayList<MessageUser>();
      try {
		  message=messageService.getMessageUser(Integer.parseInt(toUserId));
		  		  
	} catch (Exception e) {
		reInfo.setErrorMessage("得到消息失败！");
		reInfo.setErrorCode("10000");
		return reInfo;
	}
	  
	    if (message!=null) {
	    	rsInfo.setErrorMessage("消息发送成功！");
			rsInfo.setResult(message);
		}
	    return rsInfo;	
	}
	
	@RequestMapping(value = "/getMessageUserOnConversation")//通过type的不同渠道不同的消息
	public @ResponseBody
	Object getMessageUserOnConversation(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		ResultErrorInfo reInfo = new ResultErrorInfo();
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		response.addHeader("Access-Control-Allow-Origin","*");
		
		String myUserId = request.getParameter("myUserId");
		String friendUserId = request.getParameter("friendUserId");
				
		if (myUserId == null || myUserId.equals("")) {
			reInfo.setErrorMessage("我的用户ID不能为空！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		
		if (friendUserId == null || friendUserId.equals("")) {
			reInfo.setErrorMessage("好友用户ID不能为空！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		
		List<MessageUser> message= new ArrayList<MessageUser>();
      try {
		  message=messageService.getMessageUserOnConversation(Integer.parseInt(myUserId),Integer.parseInt(friendUserId));
		  		  
	} catch (Exception e) {
		reInfo.setErrorMessage("得到消息失败！");
		reInfo.setErrorCode("10000");
		return reInfo;
	}
	  
	    if (message!=null) {
	    	rsInfo.setErrorMessage("消息发送成功！");
			rsInfo.setResult(message);
		}
	    return rsInfo;	
	}
	
	
	@RequestMapping(value = "/getSystemMessageByType")//通过type的不同渠道不同的消息
	public @ResponseBody
	Object getSystemMessageByType(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		ResultErrorInfo reInfo = new ResultErrorInfo();
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		response.addHeader("Access-Control-Allow-Origin","*");
		
		String userId = request.getParameter("userId");
		String type = request.getParameter("type");
				
		if (userId == null || userId.equals("")) {
			reInfo.setErrorMessage("我的用户ID不能为空！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		
		if (type == null || type.equals("")) {
			reInfo.setErrorMessage("系统消息类型不能为空！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		
		List<MessageUser> message= new ArrayList<MessageUser>();
      try {
		  message=messageService.getSystemMessageByType(Integer.parseInt(userId),Integer.parseInt(type));
		  		  
	} catch (Exception e) {
		reInfo.setErrorMessage("得到消息失败！");
		reInfo.setErrorCode("10000");
		return reInfo;
	}
	  
	    if (message!=null) {
	    	rsInfo.setErrorMessage("消息发送成功！");
			rsInfo.setResult(message);
		}
	    return rsInfo;	
	}
	
	@RequestMapping(value = "/getSystemMessage")//通过type的不同渠道不同的消息
	public @ResponseBody
	Object getSystemMessage(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		ResultErrorInfo reInfo = new ResultErrorInfo();
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		response.addHeader("Access-Control-Allow-Origin","*");
		
		String userId = request.getParameter("userId");
				
		if (userId == null || userId.equals("")) {
			reInfo.setErrorMessage("我的用户ID不能为空！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}

		
		List<Message> message= new ArrayList<Message>();
      try {
		  message=messageService.getSystemMessage(Integer.parseInt(userId));
		  		  
	} catch (Exception e) {
		reInfo.setErrorMessage("得到消息失败！");
		reInfo.setErrorCode("10000");
		return reInfo;
	}
	  
	    if (message!=null) {
	    	rsInfo.setErrorMessage("消息发送成功！");
			rsInfo.setResult(message);
		}
	    return rsInfo;	
	}
}
