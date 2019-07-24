package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IVideoLinkDao;
import com.dndy.model.MVideoLink;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "videoLinkImpl")
public class VideoLinkImpl extends BaseDao implements IVideoLinkDao {

    @Override
    public Integer addVideoLink(MVideoLink pd) {
        return null;
    }

    @Override
    public Integer modifyVideoLink(MVideoLink pd) {
        return null;
    }

    @Override
    public MVideoLink getVideoLink(MVideoLink pd) {
        return null;
    }

    @Override
    public List<MVideoLink> getVideoLinkList(MVideoLink pd) {
        return null;
    }
}
