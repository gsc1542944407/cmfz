package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.Map;

public interface BannerService {
    //查询
    public Map<String, Object> getAll(Integer page, Integer rows);

    //添加
    public Map<String, Object> add(Banner banner);

    //修改图片路径
    public void update(Banner banner);

    //修改状态
    public void updateStatus(Banner banner);

    //删除
    public void del(String[] id);
}
