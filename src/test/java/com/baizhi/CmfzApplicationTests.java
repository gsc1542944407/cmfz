package com.baizhi;

import com.baizhi.dao.AdminDao;
import io.goeasy.GoEasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzApplicationTests {

    @Autowired
    private AdminDao adminDao;

    @Test
    public void test1() {
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-f4868785bc6e4b3d91186bf55e673660");
        goEasy.publish("my_channel", "Hello, GoEasy!");
    }
}
