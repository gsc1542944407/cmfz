package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao {
    //
    public List<Album> getAll(@Param("begin") Integer begin, @Param("rows") Integer rows);

    //查询总条数
    public Integer getCount();

    //添加
    public void add(Album album);

    public void updateCover(@Param("id") String id, @Param("cover") String cover);

    public void update(Album album);

}
