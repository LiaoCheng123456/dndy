package com.dndy.dao;

import com.dndy.model.PageData;

import java.util.List;

/**
 * 视频和演员dao
 */
public interface IVideoInActorDao {
    /**
     * 添加视频和演员
     */
    Integer addVideoInActor(PageData pd);

    /**
     * 编辑视频和演员
     */
    Integer modifyVideoInActor(PageData pd);

    /**
     * 查询单个视频和演员
     */
    PageData getVideoInActor(PageData pd);

    /**
     * 查询多个视频和演员
     */
    List<PageData> getVideoInActorList(PageData pd);

    /**
     * 删除演员
     */
    Integer deleteActor(PageData delActor);
}
