package com.baizhi.dao;


import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDao {
    //分页查询
    public List<Chapter> getAll(@Param("begin") Integer begin, @Param("rows") Integer rows, @Param("albumId") String albumId);

    public Integer getCount(String albumId);

    public void add(Chapter chapter);

    public void update(Chapter chapter);
}
