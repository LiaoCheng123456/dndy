package com.dndy.dao;

import com.dndy.model.MVideoInCountry;

import java.util.List;

/**
 * 视频和演员dao
 */
public interface IVideoInCountryDao {
    /**
     * 添加视频和演员
     */
    Integer addVideoInCountry(MVideoInCountry pd);

    /**
     * 编辑视频和演员
     */
    Integer modifyVideoInCountry(MVideoInCountry pd);

    /**
     * 查询单个视频和演员
     */
    MVideoInCountry getVideoInCountry(MVideoInCountry pd);

    /**
     * 查询多个视频和演员
     */
    List<MVideoInCountry> getVideoInCountryList(MVideoInCountry pd);
}
