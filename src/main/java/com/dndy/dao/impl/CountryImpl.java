package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.ICountryDao;
import com.dndy.model.PageData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "countryImpl")
public class CountryImpl extends BaseDao implements ICountryDao {

    @Override
    public Integer addCountry(PageData country) {
        return sqlSessionTemplate.insert("CountryMapper.addCountry", country);
    }

    @Override
    public Integer modifyCountry(PageData country) {
        return sqlSessionTemplate.update("CountryMapper.modifyCountry", country);
    }

    @Override
    public PageData getCountry(PageData country) {
        return sqlSessionTemplate.selectOne("CountryMapper.getCountry", country);
    }

    @Override
    public List<PageData> getCountryList(PageData country) {
        return sqlSessionTemplate.selectOne("CountryMapper.getCountryList", country);
    }
}
