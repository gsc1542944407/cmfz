package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.Map;

public interface ChapterService {
    //查询
    public Map<String, Object> getAll(Integer page, Integer rows, String albumId);

    public String add(Chapter chapter);

    public void update(Chapter chapter);
}
