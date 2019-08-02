package com.dndy.dao;

import com.dndy.model.PageData;

import java.util.List;

/**
 * 视频链接dao
 */
public interface IVideoLinkDao {
    /**
     * 添加视频链接
     */
    Integer addVideoLink(PageData pd);

    /**
     * 编辑视频链接
     */
    Integer modifyVideoLink(PageData pd);

    /**
     * 查询单个视频链接
     */
    PageData getVideoLink(PageData pd);

    /**
     * 查询多个视频链接
     */
    List<PageData> getVideoLinkList(PageData pd);

    /**
     * 删除链接
     */
    Integer deleteVideoLink(PageData pd);
}
