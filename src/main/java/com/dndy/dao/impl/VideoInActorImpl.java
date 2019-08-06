package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IVideoInActorDao;
import com.dndy.model.PageData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "videoInActorImpl")
public class VideoInActorImpl extends BaseDao implements IVideoInActorDao {

    @Override
    public Integer addVideoInActor(PageData videoInActor) {
        return sqlSessionTemplate.insert("VideoInActorMapper.addVideoInActor", videoInActor);
    }

    @Override
    public Integer modifyVideoInActor(PageData videoInActor) {
        return sqlSessionTemplate.update("VideoInActorMapper.modifyVideoInActor", videoInActor);
    }

    @Override
    public PageData getVideoInActor(PageData videoInActor) {
        return sqlSessionTemplate.selectOne("VideoInActorMapper.getVideoInActor", videoInActor);
    }

    @Override
    public List<PageData> getVideoInActorList(PageData videoInActor) {
        return sqlSessionTemplate.selectList("VideoInActorMapper.getVideoInActorList", videoInActor);
    }

    @Override
    public Integer deleteActor(PageData videoInActor) {
        return sqlSessionTemplate.selectOne("VideoInActorMapper.deleteActor", videoInActor);
    }
}
