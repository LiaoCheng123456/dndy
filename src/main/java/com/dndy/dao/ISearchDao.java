package com.dndy.dao;

import com.dndy.model.PageData;

import java.util.List;

/**
 * 搜索关键字dao
 */
public interface ISearchDao {
    /**
     * 添加搜索关键字
     */
    Integer addSearch(PageData pd);

    /**
     * 编辑搜索关键字
     */
    Integer modifySearch(PageData pd);

    /**
     * 查询单个搜索关键字
     */
    PageData getSearch(PageData pd);

    /**
     * 删除单个搜索关键字
     */
    PageData deleteSearch(PageData pd);

    /**
     * 查询多个搜索关键字
     */
    List<PageData> getSearchList(PageData pd);
}
