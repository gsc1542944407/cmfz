package com.baizhi.service;

import com.baizhi.DTO.UserDTO;
import com.baizhi.entity.User;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UserService {
    //查所有
    public Map<String, Object> getAll(Integer page, Integer rows);

    //
    public List<User> queryAll(HttpSession session, HttpServletResponse response) throws IOException;
    //

    public List<User> users();

    public List<UserDTO> queryUserByProvince();

    public Integer getCountByDate(Integer day);

}
