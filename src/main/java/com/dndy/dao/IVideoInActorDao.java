package com.dndy.dao;

import com.dndy.model.MVideoInActor;

import java.util.List;

/**
 * 视频和演员dao
 */
public interface IVideoInActorDao {
    /**
     * 添加视频和演员
     */
    Integer addVideoInActor(MVideoInActor pd);

    /**
     * 编辑视频和演员
     */
    Integer modifyVideoInActor(MVideoInActor pd);

    /**
     * 查询单个视频和演员
     */
    MVideoInActor getVideoInActor(MVideoInActor pd);

    /**
     * 查询多个视频和演员
     */
    List<MVideoInActor> getVideoInActorList(MVideoInActor pd);
}
