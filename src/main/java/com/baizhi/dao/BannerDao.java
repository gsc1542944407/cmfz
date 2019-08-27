package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDao {
    //分页查询
    public List<Banner> getAll(@Param("begin") Integer begin, @Param("rows") Integer rows);

    //查询总条数
    public Integer getCount();

    //添加
    public void add(Banner banner);

    //修改图片路径
    public void update(Banner banner);

    //修改状态
    public void updateStatus(Banner banner);

    //删除 基本没有 修改状态即可
    public void del(String[] id);
}
