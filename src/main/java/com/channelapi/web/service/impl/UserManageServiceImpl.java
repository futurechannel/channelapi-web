package com.channelapi.web.service.impl;

import com.channelapi.web.dao.RoleMenuDao;
import com.channelapi.web.dao.UserManageDao;
import com.channelapi.web.entity.RoleMenu;
import com.channelapi.web.entity.User;
import com.channelapi.web.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserManageServiceImpl implements UserManageService {

    @Autowired
    private UserManageDao userManageDao;

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    public User checkUser(String username, String password) {
        return userManageDao.getUserLogin(username, password);
    }

    @Override
    public List<Map<String, Object>> queryMenuByRole(int roleId) {
        List<Map<String, Object>> menus = new ArrayList<>();
        List<RoleMenu> roleMenus = roleMenuDao.queryRoleMenuByRole(roleId);

        for (RoleMenu roleMenu: roleMenus){
            Map<String, Object> map = new HashMap<>();
            map.put("id", roleMenu.getMenuID());
            map.put("text", roleMenu.getMenuName());
            map.put("iconCls", roleMenu.getMenuIcon());

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("url", roleMenu.getMenuUrl());
            map.put("attributes", attributes);
            menus.add(map);
        }
        return menus;
    }
}
