package com.alibaba.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.model.User;
import com.alibaba.service.UserService;
import com.alibaba.util.RequestUtil;

/** 
 * @author tfj 
 * 2014-7-26 
 */
@Controller
public class SystemController
{
    private final Logger log = LoggerFactory.getLogger(SystemController.class);
    
    @Resource
    private UserService userService;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home()
    {
        log.info("返回首页！");
        return "index";
    }
    
    @RequestMapping(value = "/test/hello", method = RequestMethod.GET)
    public String testHello()
    {
        log.info("执行了testHello方法！");
        return "testHello";
    }
    
    @RequestMapping(value = "/login")
    public String testLogin(HttpServletRequest request, @RequestParam String username, @RequestParam String password)
    {
        log.info("执行了testLogin方法！");
        User user = userService.findUserByName(username);
        if (user != null)
        {
            if (user.getPassword().equals(DigestUtils.md5Hex(password)))
            {
                request.getSession().setAttribute("userId", user.getId());
                request.getSession().setAttribute("user", username);
                return "redirect:" + RequestUtil.retrieveSavedRequest();//跳转至访问页面  
            }
            else
            {
                log.info("密码错误");
                request.getSession().setAttribute("message", "用户名密码错误，请重新登录");
                return "login";
            }
        }
        else
        {
            log.info("用户名不存在");
            request.getSession().setAttribute("message", "用户名不存在，请重新登录");
            return "login";
        }
    }
}