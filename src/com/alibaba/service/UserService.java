package com.alibaba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.dao.UserD;
import com.alibaba.model.User;

@Service("userService")
public class UserService {

	@Autowired
	private UserD userDao;
	
	@Cacheable(value="accountCache")
	public User findUserByName(String username) {
		User user = userDao.findUserByName(username);
		System.out.println("get DB!");
		return user;
	}

}
