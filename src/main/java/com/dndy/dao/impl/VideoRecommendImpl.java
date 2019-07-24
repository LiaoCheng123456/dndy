package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IVideoRecommendDao;
import com.dndy.model.MVideoRecommend;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "VideoRecommendImpl")
public class VideoRecommendImpl extends BaseDao implements IVideoRecommendDao {

    @Override
    public Integer addVideoRecommend(MVideoRecommend pd) {
        return null;
    }

    @Override
    public Integer modifyVideoRecommend(MVideoRecommend pd) {
        return null;
    }

    @Override
    public MVideoRecommend getVideoRecommend(MVideoRecommend pd) {
        return null;
    }

    @Override
    public List<MVideoRecommend> getVideoRecommendList(MVideoRecommend pd) {
        return null;
    }
}
