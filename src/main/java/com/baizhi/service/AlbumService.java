package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.Map;

public interface AlbumService {
    //查询
    public Map<String, Object> getAll(Integer page, Integer rows);

    //添加
    public Map<String, Object> add(Album album);

    //
    public void updateCover(String id, String cover);

    public void update(Album album);
}
