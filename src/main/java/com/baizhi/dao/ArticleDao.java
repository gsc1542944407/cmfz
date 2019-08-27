package com.baizhi.dao;


import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {
    //
    public List<Article> getAll(@Param("begin") Integer begin, @Param("rows") Integer rows);

    //查询总条数
    public Integer getCount();

    //添加
    public void add(Article article);

    //修改
    public void update(Article article);

}
