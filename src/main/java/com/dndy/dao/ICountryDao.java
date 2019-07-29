package com.dndy.dao;

import com.dndy.model.PageData;

import java.util.List;

/**
 * 国家dao
 */
public interface ICountryDao {
    /**
     * 添加国家
     */
    Integer addCountry(PageData pd);

    /**
     * 编辑国家
     */
    Integer modifyCountry(PageData pd);

    /**
     * 查询单个国家
     */
    PageData getCountry(PageData pd);

    /**
     * 查询多个国家
     */
    List<PageData> getCountryList(PageData pd);
}
