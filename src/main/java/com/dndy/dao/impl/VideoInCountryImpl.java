package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IVideoInCountryDao;
import com.dndy.model.MVideoInCountry;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "videoInCountryImpl")
public class VideoInCountryImpl extends BaseDao implements IVideoInCountryDao {

    @Override
    public Integer addVideoInCountry(MVideoInCountry pd) {
        return null;
    }

    @Override
    public Integer modifyVideoInCountry(MVideoInCountry pd) {
        return null;
    }

    @Override
    public MVideoInCountry getVideoInCountry(MVideoInCountry pd) {
        return null;
    }

    @Override
    public List<MVideoInCountry> getVideoInCountryList(MVideoInCountry pd) {
        return null;
    }
}
