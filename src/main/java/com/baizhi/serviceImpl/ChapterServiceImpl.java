package com.baizhi.serviceImpl;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> getAll(Integer page, Integer rows, String albumId) {
        Map<String, Object> map = new HashMap<>();
        Integer begin = (page - 1) * rows;
        List<Chapter> all = chapterDao.getAll(begin, rows, albumId);
        Integer records = chapterDao.getCount(albumId);
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("rows", all);
        map.put("total", total);
        map.put("records", records);
        map.put("page", page);
        return map;
    }

    @Override
    public String add(Chapter chapter) {
        chapter.setId(UUID.randomUUID().toString());
        chapter.setCreateDate(new Date());
        String id = chapter.getId();

        chapterDao.add(chapter);

        return id;
    }

    @Override
    public void update(Chapter chapter) {
        chapterDao.update(chapter);
    }
}
