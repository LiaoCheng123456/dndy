package com.dndy.dao;

import com.dndy.model.MVideoLink;

import java.util.List;

/**
 * 视频链接dao
 */
public interface IVideoLinkDao {
    /**
     * 添加视频链接
     */
    Integer addVideoLink(MVideoLink pd);

    /**
     * 编辑视频链接
     */
    Integer modifyVideoLink(MVideoLink pd);

    /**
     * 查询单个视频链接
     */
    MVideoLink getVideoLink(MVideoLink pd);

    /**
     * 查询多个视频链接
     */
    List<MVideoLink> getVideoLinkList(MVideoLink pd);
}
