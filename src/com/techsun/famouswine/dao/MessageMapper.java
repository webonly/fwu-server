package com.techsun.famouswine.dao;

import java.util.List;

import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.domain.ConversationUser;
import com.techsun.famouswine.domain.Message;
import com.techsun.famouswine.domain.MessageType;
import com.techsun.famouswine.domain.MessageUser;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer messageId);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer messageId);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
    List<Message>  selectMessageByToUserId(MessageType messageType);
    List<MessageUser>  selectMessageUser(Integer toUserId);
    List<MessageUser>  selectMessageUserOnConversation(ConversationUser param);
    List<MessageUser>  selectSystemMessageByType(CommonParam param);
    List<Message>  selectSystemMessage(Integer userId);
}