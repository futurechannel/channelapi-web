package com.channelapi.web.service;

import com.channelapi.web.entity.User;

public interface UserManageService {

    User checkUser(String username, String password);
}
