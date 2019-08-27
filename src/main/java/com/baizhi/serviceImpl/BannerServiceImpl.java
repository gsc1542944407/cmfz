package com.baizhi.serviceImpl;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;

    @Override//查询
    public Map<String, Object> getAll(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        Integer begin = (page - 1) * rows;
        List<Banner> list = bannerDao.getAll(begin, rows);
        Integer count = bannerDao.getCount();
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;
        map.put("rows", list);   //rows   数据集合
        map.put("total", total); //total  总页数
        map.put("records", count);   //records 总条数
        map.put("page", page);        //page   page
        return map;
    }

    @Override//添加
    public Map<String, Object> add(Banner banner) {
        Map<String, Object> map = new HashMap<>();
        banner.setId(UUID.randomUUID().toString().replace("-", ""));
        banner.setCreateDate(new Date());
        bannerDao.add(banner);
        String id = banner.getId();
        map.put("id", id);
        return map;
    }

    @Override//修改图片路径
    public void update(Banner banner) {
        bannerDao.update(banner);
    }

    @Override//修改状态
    public void updateStatus(Banner banner) {
        bannerDao.updateStatus(banner);
    }

    @Override//删除
    public void del(String[] id) {
        bannerDao.del(id);
    }
}
