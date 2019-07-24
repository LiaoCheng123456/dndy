package com.dndy.dao;

import com.dndy.model.MCountry;

import java.util.List;

/**
 * 国家dao
 */
public interface ICountryDao {
    /**
     * 添加国家
     */
    Integer addCountry(MCountry pd);

    /**
     * 编辑国家
     */
    Integer modifyCountry(MCountry pd);

    /**
     * 查询单个国家
     */
    MCountry getCountry(MCountry pd);

    /**
     * 查询多个国家
     */
    List<MCountry> getCountryList(MCountry pd);
}
