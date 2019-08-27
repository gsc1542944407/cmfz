package com.baizhi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import io.goeasy.GoEasy;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class testAdmin {
    @Autowired
    private UserDao userDao;

    @Test
    public void test1() {
        List<User> all = userDao.getAll(0, 5);
        for (User user : all) {
            System.out.println(user);
             System.out.println(user); 
            System.out.println(user);
             System.out.println(user);
        }

    }

    @Test
    public void test2() {
        List<User> all = userDao.getAll(0, 5);

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户表", "用户"),
                User.class, all);
        try {
            workbook.write(new FileOutputStream(new File("D:/esay.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-f4868785bc6e4b3d91186bf55e673660");
        goEasy.publish("my_channel", "Hello, GoEasy!");
    }
}
