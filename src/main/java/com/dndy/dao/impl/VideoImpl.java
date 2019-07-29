package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IVideoDao;
import com.dndy.model.PageData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "videoImpl")
public class VideoImpl extends BaseDao implements IVideoDao {

    @Override
    public Integer addVideo(PageData video) {
        return sqlSessionTemplate.insert("VideoMapper.addVideo", video);
    }

    @Override
    public Integer modifyVideo(PageData video) {
        return sqlSessionTemplate.update("VideoMapper.modifyVideo", video);
    }

    @Override
    public PageData getVideo(PageData video) {
        return sqlSessionTemplate.selectOne("VideoMapper.getVideo", video);
    }

    @Override
    public List<PageData> getVideoList(PageData video) {
        return sqlSessionTemplate.selectList("VideoMapper.getVideoList", video);
    }
}
