package com.alibaba.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.alibaba.model.User;
import com.alibaba.util.Constants;

@Repository("userDao")
public interface UserD {

	@Select(Constants.SQL_QUERY_DEAL_BY_ID)
	public User findUserByName(String username);
	
}
