package com.dndy.dao;

import com.dndy.model.MType;

import java.util.List;

/**
 * 视频类型dao
 */
public interface ITypeDao {
    /**
     * 添加视频类型
     */
    Integer addType(MType pd);

    /**
     * 编辑视频类型
     */
    Integer modifyType(MType pd);

    /**
     * 查询单个视频类型
     */
    MType getType(MType pd);

    /**
     * 查询多个视频类型
     */
    List<MType> getTypeList(MType pd);
}
