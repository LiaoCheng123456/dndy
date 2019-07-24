package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.ICountryDao;
import com.dndy.model.MCountry;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "countryImpl")
public class CountryImpl extends BaseDao implements ICountryDao {

    @Override
    public Integer addCountry(MCountry pd) {
        return null;
    }

    @Override
    public Integer modifyCountry(MCountry pd) {
        return null;
    }

    @Override
    public MCountry getCountry(MCountry pd) {
        return null;
    }

    @Override
    public List<MCountry> getCountryList(MCountry pd) {
        return null;
    }
}
