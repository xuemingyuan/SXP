package com.alibaba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.model.Login;

@Controller
@SessionAttributes("login")
public class LoginController
{
    
    public LoginController()
    {
    }
    
    
    @RequestMapping(value="login",method=RequestMethod.POST)
    public String login(Login login,BindingResult result)
    {
        System.out.println(login.getUserName());
        return "userlist";
    }
    
}
