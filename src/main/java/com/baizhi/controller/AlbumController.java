package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
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
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/getAll")
    public Map<String, Object> getAll(Integer page, Integer rows) {
        Map<String, Object> map = albumService.getAll(page, rows);
        return map;

    }

    @RequestMapping("/edit")
    public Map<String, Object> edit(String oper, Album album) {
        if ("add".equals(oper)) {
            Map<String, Object> map = albumService.add(album);
            return map;
        }
        if ("edit".equals(oper)) {
            albumService.update(album);
        }

        return null;
    }

    @RequestMapping("/upload")
    public void upload(String albumId, MultipartFile cover, HttpSession session) throws IOException {
        String realPath = session.getServletContext().getRealPath("/img");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filename = cover.getOriginalFilename();
        String newFileName = new Date().getTime() + "_" + filename;
        cover.transferTo(new File(realPath, newFileName));

        albumService.updateCover(albumId, newFileName);
    }
}
