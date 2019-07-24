package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IVideoInActorDao;
import com.dndy.model.MVideoInActor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "videoInActorImpl")
public class VideoInActorImpl extends BaseDao implements IVideoInActorDao {

    @Override
    public Integer addVideoInActor(MVideoInActor pd) {
        return null;
    }

    @Override
    public Integer modifyVideoInActor(MVideoInActor pd) {
        return null;
    }

    @Override
    public MVideoInActor getVideoInActor(MVideoInActor pd) {
        return null;
    }

    @Override
    public List<MVideoInActor> getVideoInActorList(MVideoInActor pd) {
        return null;
    }
}
