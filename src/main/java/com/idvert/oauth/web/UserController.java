package com.idvert.oauth.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.idvert.oauth.pojo.User;
import com.idvert.oauth.service.IUserService;

/**
	author : lijiqun
	version : 1.0
	date : 2015年9月18日
**/
@Controller
@RequestMapping(value="user")
public class UserController {
    
    @Autowired
    IUserService userService;
    
    @RequestMapping("register")
    public String registerUser(){
        return "user/register";
    }
    
    @RequestMapping("addUser")
    public String addUser(User user){
        userService.addUser(user);
        return "login/clientLogin";
    }
    
    
}
