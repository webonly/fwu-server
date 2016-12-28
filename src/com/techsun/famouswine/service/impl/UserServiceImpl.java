package com.techsun.famouswine.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.UserMapper;
import com.techsun.famouswine.domain.User;
import com.techsun.famouswine.service.UserService;


@Service("UserService")
public class UserServiceImpl implements UserService{
    
	private UserMapper userMapper;
         
	public UserMapper getUserMapper() {
			
		return userMapper;
		}
	@Autowired
	public void setUserMapper(UserMapper userMapper) {
			this.userMapper = userMapper;
		}
	@Override
	public User getUserByUserName(String userName) {
		 User user;
		try {
			  user = userMapper.selectByUserName(userName);
			  
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("通过用户名得到用户错误");
		}
		return user;
	}
	@Override
	public User getUserByUserId(Integer userId) {
		 User user;
			try {
			  user = userMapper.selectByPrimaryKey(userId);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationContextException("通过用户Id得到用户信息错误");
			}
			return user;
	}
	@Override
	public int addUser(User user)  {
		int count = 0;
		if(user.getIntegral() == null){
			user.setIntegral(0);			
		}
		
		try{
			if (user.getIntegral()<=5) {
				user.setLevel("LV1");
			}else if (user.getIntegral()<=291838) {
				user.setLevel("LV20");
			}
			userMapper.updateByPrimaryKeySelective(user);
			count = userMapper.insertSelective(user);
			if(count<0){
				throw new Exception("增加用户失败1");
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("增加用户失败2");
		}
		return count;
	   
		
	}
	
	@Override
	public int modifyUser(User user) {
		int count = 0;
		try{
			if (user.getIntegral()<=5) {
				user.setLevel("LV1");
			}else if (user.getIntegral()<=291838) {
				user.setLevel("LV20");
			}
			
			count = userMapper.updateByPrimaryKeySelective(user);
			if(count<0){
				throw new Exception("修改用户失败!");
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("修改用户失败！");
		}
		return count;
	}

	
	
	
	
	
	
}
