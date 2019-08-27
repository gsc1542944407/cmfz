package com.baizhi.serviceImpl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.DTO.UserDTO;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> getAll(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        Integer begin = (page - 1) * rows;
        List<User> list = userDao.getAll(begin, rows);
        Integer count = userDao.getCount();
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;
        map.put("rows", list);   //rows   数据集合
        map.put("total", total); //total  总页数
        map.put("records", count);   //records 总条数
        map.put("page", page);        //page   page
        return map;

    }

    @Override
    public List<User> queryAll(HttpSession session, HttpServletResponse response) throws IOException {
        String realPath = session.getServletContext().getRealPath("/img/");
        List<User> users = userDao.queryAll();
        for (User user : users) {
            String headPic = user.getHeadPic();
            user.setHeadPic(realPath + headPic);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户表", "用户"),
                User.class, users);
        try {
            String encode = URLEncoder.encode("用户表.xls", "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + encode);

            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            workbook.close();
        }
        return null;
    }

    @Override
    public List<User> users() {
        List<User> users = userDao.queryAll();
        return users;

    }

    @Override
    public List<UserDTO> queryUserByProvince() {
        List<UserDTO> userDTOS = userDao.queryUserByProvince();
        return userDTOS;
    }

    @Override
    public Integer getCountByDate(Integer day) {
        Integer countByDate = userDao.getCountByDate(day);
        return countByDate;
    }
}
