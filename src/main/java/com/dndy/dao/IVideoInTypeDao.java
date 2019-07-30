package com.dndy.dao;

import com.dndy.model.PageData;

import java.util.List;

/**
 * 视频类型dao
 */
public interface IVideoInTypeDao {
    /**
     * 添加视频类型
     */
    Integer addVideoInType(PageData pd);

    /**
     * 编辑视频类型
     */
    Integer modifyVideoInType(PageData pd);

    /**
     * 查询单个视频类型
     */
    PageData getVideoInType(PageData pd);

    /**
     * 查询多个视频类型
     */
    List<PageData> getVideoInTypeList(PageData pd);
}
