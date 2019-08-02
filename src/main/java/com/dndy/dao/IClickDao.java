package com.dndy.dao;

import com.dndy.model.PageData;

import java.util.List;

/**
 * 视频点击dao
 */
public interface IClickDao {
    /**
     * 添加视频点击
     */
    Integer addClick(PageData pd);

    /**
     * 编辑视频点击
     */
    Integer modifyClick(PageData pd);

    /**
     * 查询单个视频点击
     */
    PageData getClick(PageData pd);

    /**
     * 查询多个视频点击
     */
    List<PageData> getClickList(PageData pd);
}
