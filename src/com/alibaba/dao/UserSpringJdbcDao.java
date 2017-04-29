package com.alibaba.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alibaba.model.User;
import com.alibaba.util.Constants;

import sqlmanager.SQLResource;

@Repository("userSpringJdbcDao")
public class UserSpringJdbcDao
{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private SQLResource sqlResource;
    
    public User findUserByName(String username)
    {
        
        List<User> userList = jdbcTemplate.query(Constants.SQL_QUERY_DEAL_BY_ID.replace("#{username}", "?"),
            new Object[] {username},
            new BeanPropertyRowMapper<User>(User.class));
        
        return userList.get(0);
        
    }
    
    public User findUserByName2(String username)
    {
        List<Map<String, Object>> rsList =
            jdbcTemplate.queryForList(Constants.SQL_QUERY_DEAL_BY_ID.replace("#{username}", "?"), username);
        for (Map<String, Object> map : rsList)
        {
            User user = new User();
            user.setId(String.valueOf(map.get("ID")));
            user.setUsername(String.valueOf(map.get("USERNAME")));
            user.setPassword(String.valueOf(map.get("PASSWORD")));
            return user;
        }
        return null;
    }
    
    public User findUserByName3(String username)
    {
        List<Map<String, Object>> rsList 
            = jdbcTemplate.queryForList(
                sqlResource.getProperties()
                .getProperty(Constants.SQL_QUERY_DEAL_BY_ID),
                username);
        for (Map<String, Object> map : rsList)
        {
            User user = new User();
            user.setId(String.valueOf(map.get("ID")));
            user.setUsername(String.valueOf(map.get("USERNAME")));
            user.setPassword(String.valueOf(map.get("PASSWORD")));
            return user;
        }
        return null;
    }
}
