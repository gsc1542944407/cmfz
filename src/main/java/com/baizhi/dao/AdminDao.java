package com.baizhi.dao;

import com.baizhi.entity.Admin;

public interface AdminDao {
    //查询
    public Admin queryAdminByUsername(String username);
}
