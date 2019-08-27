package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;


@RestController
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("/getAll")//展示查询
    public Map<String, Object> getAll(Integer page, Integer rows) {
        Map<String, Object> map = bannerService.getAll(page, rows);
        return map;
    }

    @RequestMapping("edit")//操作方法
    public Map<String, Object> edit(String oper, Banner banner, String[] id) {
        //添加数据
        if ("add".equals(oper)) {
            Map<String, Object> map = bannerService.add(banner);
            return map;
        }
        //修改状态
        if ("edit".equals(oper)) {
            System.out.println(banner);
            bannerService.updateStatus(banner);
        }
        //删除操作  基本不用 可隐藏
        if ("del".equals(oper)) {
            bannerService.del(id);
        }
        return null;
    }

    @RequestMapping("upload")//修改图片路径
    public void upload(String bannerId, MultipartFile imgPath, HttpSession session) {
        //获取图片上传的路径
        System.out.println(bannerId);
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
        Banner banner = new Banner();
        banner.setId(bannerId);
        banner.setImgPath(newFileName);
        bannerService.update(banner);


    }


}
