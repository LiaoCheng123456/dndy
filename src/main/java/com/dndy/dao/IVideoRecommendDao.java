package com.dndy.dao;

import com.dndy.model.MVideoRecommend;

import java.util.List;

/**
 * 视频推荐dao
 */
public interface IVideoRecommendDao {
    /**
     * 添加视频推荐
     */
    Integer addVideoRecommend(MVideoRecommend pd);

    /**
     * 编辑视频推荐
     */
    Integer modifyVideoRecommend(MVideoRecommend pd);

    /**
     * 查询单个视频推荐
     */
    MVideoRecommend getVideoRecommend(MVideoRecommend pd);

    /**
     * 查询多个视频推荐
     */
    List<MVideoRecommend> getVideoRecommendList(MVideoRecommend pd);
}
