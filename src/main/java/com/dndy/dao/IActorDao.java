package com.dndy.dao;

import com.dndy.model.PageData;

import java.util.List;

/**
 * 演员dao
 */
public interface IActorDao {
    /**
     * 添加演员
     */
    Integer addActor(PageData pd);

    /**
     * 编辑演员
     */
    Integer modifyActor(PageData pd);

    /**
     * 查询单个演员
     */
    PageData getActor(PageData pd);

    /**
     * 查询多个演员
     */
    List<PageData> getActorList(PageData pd);

    /**
     * 删除演员
     */
    Integer deleteActor(PageData pd);
}
