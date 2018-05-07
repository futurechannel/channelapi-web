package com.channelapi.web.service.impl;

import com.channelapi.web.dao.UserManageDao;
import com.channelapi.web.entity.User;
import com.channelapi.web.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManageServiceImpl implements UserManageService {

    @Autowired
    private UserManageDao userManageDao;

    @Override
    public User checkUser(String username, String password) {
        return userManageDao.getUserLogin(username, password);
    }
}
