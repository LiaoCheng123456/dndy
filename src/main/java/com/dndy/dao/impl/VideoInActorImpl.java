package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IVideoInActorDao;
import com.dndy.model.MVideoInActor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "videoInActorImpl")
public class VideoInActorImpl extends BaseDao implements IVideoInActorDao {

    @Override
    public Integer addVideoInActor(MVideoInActor videoInActor) {
        return sqlSessionTemplate.insert("VideoInActorMapper.addVideoInActor", videoInActor);
    }

    @Override
    public Integer modifyVideoInActor(MVideoInActor videoInActor) {
        return sqlSessionTemplate.update("VideoInActorMapper.modifyVideoInActor", videoInActor);
    }

    @Override
    public MVideoInActor getVideoInActor(MVideoInActor videoInActor) {
        return sqlSessionTemplate.selectOne("VideoInActorMapper.getVideoInActor", videoInActor);
    }

    @Override
    public List<MVideoInActor> getVideoInActorList(MVideoInActor videoInActor) {
        return sqlSessionTemplate.selectList("VideoInActorMapper.getVideoInActorList", videoInActor);
    }
}
