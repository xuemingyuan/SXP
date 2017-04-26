package com.alibaba.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springMybatis-servlet.xml")
public class UserSpringJdbcServiceTest {

	@Resource
	private UserSpringJdbcService userSpringJdbcService;
	
	@Test
	public void test() {
		User user =userSpringJdbcService.findUserByName("a");
		System.out.println(user.toString());
	}

}
