package com.dndy.dao;

import com.dndy.model.MImage;

import java.util.List;

/**
 * 图片dao
 */
public interface IImageDao {
    /**
     * 添加图片
     */
    Integer addImage(MImage pd);

    /**
     * 编辑图片
     */
    Integer modifyImage(MImage pd);

    /**
     * 查询单个图片
     */
    MImage getImage(MImage pd);

    /**
     * 查询多个图片
     */
    List<MImage> getImageList(MImage pd);
}
