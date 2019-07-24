package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IVideoDao;
import com.dndy.model.MVideo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "videoImpl")
public class VideoImpl extends BaseDao implements IVideoDao {

    @Override
    public Integer addVideo(MVideo video) {
        return sqlSessionTemplate.insert("VideoMapper.addVideo", video);
    }

    @Override
    public Integer modifyVideo(MVideo video) {
        return sqlSessionTemplate.update("VideoMapper.modifyVideo", video);
    }

    @Override
    public MVideo getVideo(MVideo video) {
        return sqlSessionTemplate.selectOne("VideoMapper.getVideo", video);
    }

    @Override
    public List<MVideo> getVideoList(MVideo video) {
        return sqlSessionTemplate.selectList("VideoMapper.getVideoList", video);
    }
}
