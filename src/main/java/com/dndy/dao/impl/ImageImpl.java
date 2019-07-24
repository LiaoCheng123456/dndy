package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IImageDao;
import com.dndy.model.MImage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "imageImpl")
public class ImageImpl extends BaseDao implements IImageDao {

    @Override
    public Integer addImage(MImage image) {
        return sqlSessionTemplate.insert("ImageMapper.addImage", image);
    }

    @Override
    public Integer modifyImage(MImage image) {
        return sqlSessionTemplate.update("ImageMapper.modifyImage", image);
    }

    @Override
    public MImage getImage(MImage image) {
        return sqlSessionTemplate.selectOne("ImageMapper.getImage", image);
    }

    @Override
    public List<MImage> getImageList(MImage image) {
        return sqlSessionTemplate.selectList("ImageMapper.getImageList", image);
    }
}
