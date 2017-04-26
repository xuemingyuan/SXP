package com.alibaba.service;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springMybatis-servlet.xml")
public class UserServiceTest 
{

	@Resource
	private UserService userService;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindUserByName() {
		long startTime = System.currentTimeMillis();
		userService.findUserByName("a");
		System.out.println(System.currentTimeMillis()-startTime);
		
		long startTime2 = System.currentTimeMillis();
		userService.findUserByName("a");
		System.out.println(System.currentTimeMillis()-startTime2);
	}

}
