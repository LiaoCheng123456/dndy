package com.dndy.dao;

import com.dndy.model.PageData;

import java.util.List;

/**
 * 视频信息dao
 */
public interface IVideoDao {
    /**
     * 添加视频
     */
    Integer addVideo(PageData pd);

    /**
     * 编辑视频
     */
    Integer modifyVideo(PageData pd);

    /**
     * 查询单个视频
     */
    PageData getVideo(PageData pd);

    /**
     * 查询多个视频
     */
    List<PageData> getVideoList(PageData pd);

    List<PageData> getVideoListByClickNumberDesc(PageData pd);
}
