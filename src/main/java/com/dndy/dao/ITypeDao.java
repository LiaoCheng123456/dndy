package com.dndy.dao;

import com.dndy.model.PageData;

import java.util.List;

/**
 * 视频类型dao
 */
public interface ITypeDao {
    /**
     * 添加视频类型
     */
    Integer addType(PageData pd);

    /**
     * 编辑视频类型
     */
    Integer modifyType(PageData pd);

    /**
     * 查询单个视频类型
     */
    PageData getType(PageData pd);

    /**
     * 查询多个视频类型
     */
    List<PageData> getTypeList(PageData pd);
}
