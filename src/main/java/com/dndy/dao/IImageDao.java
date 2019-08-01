package com.dndy.dao;

import com.dndy.model.PageData;

import java.util.List;

/**
 * 图片dao
 */
public interface IImageDao {
    /**
     * 添加图片
     */
    Integer addImage(PageData pd);

    /**
     * 编辑图片
     */
    Integer modifyImage(PageData pd);

    /**
     * 查询单个图片
     */
    PageData getImage(PageData pd);

    /**
     * 查询多个图片
     */
    List<PageData> getImageList(PageData pd);

    /**
     * 删除单张图片
     */
    Integer deleteImage(PageData pd);
}
