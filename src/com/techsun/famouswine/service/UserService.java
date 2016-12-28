package com.techsun.famouswine.service;

import com.techsun.famouswine.domain.User;



public interface UserService {
   
	User getUserByUserName(String userName);
	
	User getUserByUserId(Integer userId);
	
	int addUser(User user);
	
	int modifyUser( User user);    
}
