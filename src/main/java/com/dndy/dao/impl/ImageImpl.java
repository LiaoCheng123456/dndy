package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IImageDao;
import com.dndy.model.PageData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "imageImpl")
public class ImageImpl extends BaseDao implements IImageDao {

    @Override
    public Integer addImage(PageData image) {
        return sqlSessionTemplate.insert("ImageMapper.addImage", image);
    }

    @Override
    public Integer modifyImage(PageData image) {
        return sqlSessionTemplate.update("ImageMapper.modifyImage", image);
    }

    @Override
    public PageData getImage(PageData image) {
        return sqlSessionTemplate.selectOne("ImageMapper.getImage", image);
    }

    @Override
    public List<PageData> getImageList(PageData image) {
        return sqlSessionTemplate.selectList("ImageMapper.getImageList", image);
    }
}
