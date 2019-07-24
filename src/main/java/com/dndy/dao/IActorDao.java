package com.dndy.dao;

import com.dndy.model.MActor;

import java.util.List;

/**
 * 演员dao
 */
public interface IActorDao {
    /**
     * 添加演员
     */
    Integer addActor(MActor pd);

    /**
     * 编辑演员
     */
    Integer modifyActor(MActor pd);

    /**
     * 查询单个演员
     */
    MActor getActor(MActor pd);

    /**
     * 查询多个演员
     */
    List<MActor> getActorList(MActor pd);
}
