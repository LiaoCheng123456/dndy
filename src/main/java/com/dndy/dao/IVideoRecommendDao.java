package com.dndy.dao;

import com.dndy.model.PageData;

import java.util.List;

/**
 * 视频推荐dao
 */
public interface IVideoRecommendDao {
    /**
     * 添加视频推荐
     */
    Integer addVideoRecommend(PageData pd);

    /**
     * 编辑视频推荐
     */
    Integer modifyVideoRecommend(PageData pd);

    /**
     * 查询单个视频推荐
     */
    PageData getVideoRecommend(PageData pd);

    /**
     * 查询多个视频推荐
     */
    List<PageData> getVideoRecommendList(PageData pd);

    /**
     * 删除数据
     */
    Integer deleteVideoRecommend(PageData pd);
}
