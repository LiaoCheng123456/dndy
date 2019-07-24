package com.dndy.dao;

import com.dndy.model.MClick;

import java.util.List;

/**
 * 视频点击dao
 */
public interface IClickDao {
    /**
     * 添加视频点击
     */
    Integer addClick(MClick pd);

    /**
     * 编辑视频点击
     */
    Integer modifyClick(MClick pd);

    /**
     * 查询单个视频点击
     */
    MClick getClick(MClick pd);

    /**
     * 查询多个视频点击
     */
    List<MClick> getClickList(MClick pd);
}
