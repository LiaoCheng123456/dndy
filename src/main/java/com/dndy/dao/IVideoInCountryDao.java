package com.dndy.dao;

import com.dndy.model.PageData;

import java.util.List;

/**
 * 视频和国家dao
 */
public interface IVideoInCountryDao {
    /**
     * 添加视频和国家
     */
    Integer addVideoInCountry(PageData pd);

    /**
     * 编辑视频和国家
     */
    Integer modifyVideoInCountry(PageData pd);

    /**
     * 查询单个视频和国家
     */
    PageData getVideoInCountry(PageData pd);

    /**
     * 查询多个视频和国家
     */
    List<PageData> getVideoInCountryList(PageData pd);
}
