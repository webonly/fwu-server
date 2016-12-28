package com.techsun.famouswine.service;

import java.util.List;

import com.techsun.famouswine.domain.Message;
import com.techsun.famouswine.domain.MessageType;
import com.techsun.famouswine.domain.MessageUser;

public interface MessageService {
	int addMessage( Message message);
	
	List<Message> getMessageByToUserId(MessageType messageType );
	
	List<MessageUser> getMessageUser(int toId);
	
	List<MessageUser> getMessageUserOnConversation(int myId, int friendUserId);
	
	List<Message> getSystemMessage(int userId);
	
	List<MessageUser> getSystemMessageByType(int userId, int type);
}
