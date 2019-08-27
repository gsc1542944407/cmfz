package com.baizhi.service;


import com.baizhi.entity.Article;

import java.util.Map;

public interface ArticleService {
    //查询
    public Map<String, Object> getAll(Integer page, Integer rows);

    //
    public String add(Article article);

    //
    public void update(Article article);

}
