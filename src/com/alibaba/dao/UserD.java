package com.alibaba.dao;

import org.apache.ibatis.annotations.Select;

import com.alibaba.model.User;
import com.alibaba.util.Constants;


public interface UserD {

	@Select(Constants.SQL_TEST)
	public User findUserByName(String username);
	
}
