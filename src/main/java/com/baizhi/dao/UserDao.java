package com.baizhi.dao;

import com.baizhi.DTO.UserDTO;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    //查所有
    public List<User> getAll(@Param("begin") Integer begin, @Param("rows") Integer rows);

    //查询总条数
    public Integer getCount();

    //
    public List<User> queryAll();

    public Integer getCountByDate(Integer day);

    public List<UserDTO> queryUserByProvince();

}
