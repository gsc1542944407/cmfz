package com.baizhi.serviceImpl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;

    @Override
    public Map<String, Object> getAll(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        Integer begin = (page - 1) * rows;
        List<Album> list = albumDao.getAll(begin, rows);
        Integer count = albumDao.getCount();
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;
        map.put("rows", list);   //rows   数据集合
        map.put("total", total); //total  总页数
        map.put("records", count);   //records 总条数
        map.put("page", page);        //page   page
        return map;
    }

    @Override
    public Map<String, Object> add(Album album) {
        Map<String, Object> map = new HashMap<>();
        album.setId(UUID.randomUUID().toString());
        album.setScore(0.0);
        album.setCount(70);
        album.setPublishDate(new Date());
        albumDao.add(album);
        String id = album.getId();
        map.put("id", id);
        return map;
    }

    @Override
    public void updateCover(String id, String cover) {
        albumDao.updateCover(id, cover);
    }

    @Override
    public void update(Album album) {
        albumDao.update(album);
    }

}
