package com.alibaba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dao.UserSpringJdbcDao;
import com.alibaba.model.User;

@Service("userSpringJdbcService")
public class UserSpringJdbcService
{
    
    @Autowired
    private UserSpringJdbcDao userSpringJdbcDao;
    
    public User findUserByName(String username)
    {
        return userSpringJdbcDao.findUserByName(username);
    }
    
    public User findUserByName3(String username)
    {
        return userSpringJdbcDao.findUserByName3(username);
    }
}
