package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IVideoInCountryDao;
import com.dndy.model.MVideoInCountry;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "videoInCountryImpl")
public class VideoInCountryImpl extends BaseDao implements IVideoInCountryDao {

    @Override
    public Integer addVideoInCountry(MVideoInCountry videoInCountry) {
        return sqlSessionTemplate.insert("VideoInCountryMapper.addVideoInCountry", videoInCountry);
    }

    @Override
    public Integer modifyVideoInCountry(MVideoInCountry videoInCountry) {
        return sqlSessionTemplate.update("VideoInCountryMapper.modifyVideoInCountry", videoInCountry);
    }

    @Override
    public MVideoInCountry getVideoInCountry(MVideoInCountry videoInCountry) {
        return sqlSessionTemplate.selectOne("VideoInCountryMapper.getVideoInCountry", videoInCountry);
    }

    @Override
    public List<MVideoInCountry> getVideoInCountryList(MVideoInCountry videoInCountry) {
        return sqlSessionTemplate.selectList("VideoInCountryMapper.getVideoInCountryList", videoInCountry);
    }
}
