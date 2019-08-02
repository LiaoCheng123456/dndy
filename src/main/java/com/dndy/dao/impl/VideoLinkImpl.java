package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IVideoLinkDao;
import com.dndy.model.PageData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "videoLinkImpl")
public class VideoLinkImpl extends BaseDao implements IVideoLinkDao {

    @Override
    public Integer addVideoLink(PageData videoLink) {
        return sqlSessionTemplate.insert("VideoLinkMapper.addVideoLink", videoLink);
    }

    @Override
    public Integer modifyVideoLink(PageData videoLink) {
        return sqlSessionTemplate.update("VideoLinkMapper.modifyVideoLink", videoLink);
    }

    @Override
    public PageData getVideoLink(PageData videoLink) {
        return sqlSessionTemplate.selectOne("VideoLinkMapper.getVideoLink", videoLink);
    }

    @Override
    public List<PageData> getVideoLinkList(PageData videoLink) {
        return sqlSessionTemplate.selectList("VideoLinkMapper.getVideoLinkList", videoLink);
    }

    @Override
    public Integer deleteVideoLink(PageData pd) {
        return sqlSessionTemplate.delete("VideoLinkMapper.deleteVideoLink", pd);
    }
}
