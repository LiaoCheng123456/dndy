package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IVideoLinkDao;
import com.dndy.model.MVideoLink;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "videoLinkImpl")
public class VideoLinkImpl extends BaseDao implements IVideoLinkDao {

    @Override
    public Integer addVideoLink(MVideoLink videoLink) {
        return sqlSessionTemplate.insert("VideoLinkMapper.addVideoLink", videoLink);
    }

    @Override
    public Integer modifyVideoLink(MVideoLink videoLink) {
        return sqlSessionTemplate.update("VideoLinkMapper.modifyVideoLink", videoLink);
    }

    @Override
    public MVideoLink getVideoLink(MVideoLink videoLink) {
        return sqlSessionTemplate.selectOne("VideoLinkMapper.getVideoLink", videoLink);
    }

    @Override
    public List<MVideoLink> getVideoLinkList(MVideoLink videoLink) {
        return sqlSessionTemplate.selectList("VideoLinkMapper.getVideoLinkList", videoLink);
    }
}
