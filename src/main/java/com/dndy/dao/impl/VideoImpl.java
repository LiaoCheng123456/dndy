package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IVideoDao;
import com.dndy.model.MVideo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "videoImpl")
public class VideoImpl extends BaseDao implements IVideoDao {

    @Override
    public Integer addVideo(MVideo pd) {
        return null;
    }

    @Override
    public Integer modifyVideo(MVideo pd) {
        return null;
    }

    @Override
    public MVideo getVideo(MVideo pd) {
        return null;
    }

    @Override
    public List<MVideo> getVideoList(MVideo pd) {
        return null;
    }
}
