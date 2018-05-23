package com.channelapi.web.dao;

import com.channelapi.web.entity.RoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMenuDao {
    List<RoleMenu> queryRoleMenuByRole(@Param("roleId") int roleId);
}
