package com.channelapi.web.dao;

import com.channelapi.web.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserManageDao {

    User getUserLogin(@Param("username") String username, @Param("password") String password);
}
