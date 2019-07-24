package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.ICountryDao;
import com.dndy.model.MCountry;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "countryImpl")
public class CountryImpl extends BaseDao implements ICountryDao {

    @Override
    public Integer addCountry(MCountry country) {
        return sqlSessionTemplate.insert("CountryMapper.addCountry", country);
    }

    @Override
    public Integer modifyCountry(MCountry country) {
        return sqlSessionTemplate.update("CountryMapper.modifyCountry", country);
    }

    @Override
    public MCountry getCountry(MCountry country) {
        return sqlSessionTemplate.selectOne("CountryMapper.getCountry", country);
    }

    @Override
    public List<MCountry> getCountryList(MCountry country) {
        return sqlSessionTemplate.selectOne("CountryMapper.getCountryList", country);
    }
}
