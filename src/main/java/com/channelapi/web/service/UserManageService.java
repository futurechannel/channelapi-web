package com.channelapi.web.service;

import com.channelapi.web.entity.User;

import java.util.List;
import java.util.Map;

public interface UserManageService {

    User checkUser(String username, String password);
    List<Map<String, Object>> queryMenuByRole(int roleId);
}
