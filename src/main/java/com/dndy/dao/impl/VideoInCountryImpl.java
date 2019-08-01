package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IVideoInCountryDao;
import com.dndy.model.PageData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "videoInCountryImpl")
public class VideoInCountryImpl extends BaseDao implements IVideoInCountryDao {

    @Override
    public Integer addVideoInCountry(PageData videoInCountry) {
        return sqlSessionTemplate.insert("VideoInCountryMapper.addVideoInCountry", videoInCountry);
    }

    @Override
    public Integer modifyVideoInCountry(PageData videoInCountry) {
        return sqlSessionTemplate.update("VideoInCountryMapper.modifyVideoInCountry", videoInCountry);
    }

    @Override
    public PageData getVideoInCountry(PageData videoInCountry) {
        return sqlSessionTemplate.selectOne("VideoInCountryMapper.getVideoInCountry", videoInCountry);
    }

    @Override
    public List<PageData> getVideoInCountryList(PageData videoInCountry) {
        return sqlSessionTemplate.selectList("VideoInCountryMapper.getVideoInCountryList", videoInCountry);
    }

    @Override
    public Integer deleteVideoInCountry(PageData videoInCountry) {
        return sqlSessionTemplate.delete("VideoInCountryMapper.deleteVideoInCountry", videoInCountry);
    }
}
