package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IVideoRecommendDao;
import com.dndy.model.PageData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "VideoRecommendImpl")
public class VideoRecommendImpl extends BaseDao implements IVideoRecommendDao {

    @Override
    public Integer addVideoRecommend(PageData videoRecommend) {
        return sqlSessionTemplate.insert("VideoRecommendMapper.addVideoRecommend", videoRecommend);
    }

    @Override
    public Integer modifyVideoRecommend(PageData videoRecommend) {
        return sqlSessionTemplate.update("VideoRecommendMapper.modifyVideoRecommend", videoRecommend);
    }

    @Override
    public PageData getVideoRecommend(PageData videoRecommend) {
        return sqlSessionTemplate.selectOne("VideoRecommendMapper.getVideoRecommend", videoRecommend);
    }

    @Override
    public List<PageData> getVideoRecommendList(PageData videoRecommend) {
        return sqlSessionTemplate.selectList("VideoRecommendMapper.getVideoRecommendList", videoRecommend);
    }

    @Override
    public Integer deleteVideoRecommend(PageData videoRecommend) {
        return sqlSessionTemplate.selectOne("VideoRecommendMapper.deleteVideoRecommend", videoRecommend);
    }
}
