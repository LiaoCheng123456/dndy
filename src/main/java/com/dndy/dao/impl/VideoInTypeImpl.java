package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IVideoInTypeDao;
import com.dndy.model.PageData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "videoInTypeImpl")
public class VideoInTypeImpl extends BaseDao implements IVideoInTypeDao {

    @Override
    public Integer addVideoInType(PageData VideoInType) {
        return sqlSessionTemplate.insert("VideoInTypeMapper.addVideoInType", VideoInType);
    }

    @Override
    public Integer modifyVideoInType(PageData VideoInType) {
        return sqlSessionTemplate.update("VideoInTypeMapper.modifyVideoInType", VideoInType);
    }

    @Override
    public PageData getVideoInType(PageData VideoInType) {
        return sqlSessionTemplate.selectOne("VideoInTypeMapper.getVideoInType", VideoInType);
    }

    @Override
    public List<PageData> getVideoInTypeList(PageData VideoInType) {
        return sqlSessionTemplate.selectList("VideoInTypeMapper.getVideoInTypeList", VideoInType);
    }

    @Override
    public Integer deleteVideoInType(PageData VideoInType) {
        return sqlSessionTemplate.insert("VideoInTypeMapper.deleteVideoInType", VideoInType);
    }
}
