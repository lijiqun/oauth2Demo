package com.idvert.oauth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idvert.oauth.dao.IUserDao;
import com.idvert.oauth.pojo.User;
import com.idvert.oauth.service.IUserService;

/**
	author : lijiqun
	version : 1.0
	date : 2015年9月18日
**/
@Service
public class UserServiceImpl implements IUserService {
    
    @Autowired
    IUserDao userDao;
    
    @Override
    public void addUser(User user) {
        this.userDao.createFavUser(user);
    }

}
