package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IImageDao;
import com.dndy.model.MImage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "imageImpl")
public class ImageImpl extends BaseDao implements IImageDao {

    @Override
    public Integer addImage(MImage pd) {
        return null;
    }

    @Override
    public Integer modifyImage(MImage pd) {
        return null;
    }

    @Override
    public MImage getImage(MImage pd) {
        return null;
    }

    @Override
    public List<MImage> getImageList(MImage pd) {
        return null;
    }
}
