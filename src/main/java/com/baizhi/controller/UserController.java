package com.baizhi.controller;


import com.baizhi.DTO.UserDTO;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.goeasy.GoEasy;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getAll")
    public Map<String, Object> getAll(Integer page, Integer rows) {

        Map<String, Object> map = userService.getAll(page, rows);

        return map;

    }

    @RequestMapping("/out")
    public void getAll(HttpSession session, HttpServletResponse response) throws IOException {
        List<User> users = userService.queryAll(session, response);

        System.out.println("导出完毕");
    }

    @RequestMapping("upload")//修改图片路径
    public void upload(String userId, MultipartFile imgPath, HttpSession session) {
        //获取图片上传的路径
        System.out.println(userId);
        String realPath = session.getServletContext().getRealPath("/img");
        //
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filename = imgPath.getOriginalFilename();
        String newFileName = new Date().getTime() + "_" + filename;
        try {
            imgPath.transferTo(new File(realPath, newFileName));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @RequestMapping("/user")
    public void queryUserByProvince() {
        List<UserDTO> userDTOS = userService.queryUserByProvince();
        for (UserDTO userDTO : userDTOS) {
            System.out.println(userDTO);
        }
        /*JSONArray array = new JSONArray();
        array.addAll(userDTOS);

        String s = array.toJSONString();*/
        ObjectMapper objectMapper = new ObjectMapper();
        String s = null;
        try {
            s = objectMapper.writeValueAsString(userDTOS);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(s);
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-f4868785bc6e4b3d91186bf55e673660");
        goEasy.publish("my_channel", s);

    }

    @RequestMapping("/count")
    public void getCountByDate() {
        List<Integer> list = new ArrayList<>();
        Integer integer = null;
        for (int i = 1; i < 8; i++) {
            integer = userService.getCountByDate(i);
            list.add(integer);

        }
        JSONArray array = new JSONArray();
        array.addAll(list);

        String s = array.toJSONString();
        System.out.println(s);

        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-f4868785bc6e4b3d91186bf55e673660");
        goEasy.publish("my_channel", s);

    }

}

