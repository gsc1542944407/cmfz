package com.baizhi.serviceImpl;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> login(String username, String password, String enCode, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        String code = (String) session.getAttribute("code");
        if (enCode.equals(code)) {
            Admin admin = adminDao.queryAdminByUsername(username);
            if (admin != null) {
                if (password.equals(admin.getPassword())) {
                    map.put("msg", "ok");
                    map.put("admin", admin);
                    return map;
                } else {
                    map.put("msg", "密码错误");
                    return map;
                }
            } else {
                map.put("msg", "用户不存在");
                return map;
            }
        } else {
            map.put("msg", "验证码错误");
            return map;
        }

    }
}
