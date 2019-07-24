package com.dndy.dao;

import com.dndy.model.MVideo;

import java.util.List;

/**
 * 视频信息dao
 */
public interface IVideoDao {
    /**
     * 添加视频
     */
    Integer addVideo(MVideo pd);

    /**
     * 编辑视频
     */
    Integer modifyVideo(MVideo pd);

    /**
     * 查询单个视频
     */
    MVideo getVideo(MVideo pd);

    /**
     * 查询多个视频
     */
    List<MVideo> getVideoList(MVideo pd);
}
