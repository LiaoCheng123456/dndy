package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IVideoRecommendDao;
import com.dndy.model.MVideoRecommend;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "VideoRecommendImpl")
public class VideoRecommendImpl extends BaseDao implements IVideoRecommendDao {

    @Override
    public Integer addVideoRecommend(MVideoRecommend videoRecommend) {
        return sqlSessionTemplate.insert("VideoRecommendMapper.addVideoRecommend", videoRecommend);
    }

    @Override
    public Integer modifyVideoRecommend(MVideoRecommend videoRecommend) {
        return sqlSessionTemplate.update("VideoRecommendMapper.modifyVideoRecommend", videoRecommend);
    }

    @Override
    public MVideoRecommend getVideoRecommend(MVideoRecommend videoRecommend) {
        return sqlSessionTemplate.selectOne("VideoRecommendMapper.getVideoRecommend", videoRecommend);
    }

    @Override
    public List<MVideoRecommend> getVideoRecommendList(MVideoRecommend videoRecommend) {
        return sqlSessionTemplate.selectList("VideoRecommendMapper.getVideoRecommendList", videoRecommend);
    }
}
