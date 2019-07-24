package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IIPDao;
import com.dndy.model.MIP;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "IPImpl")
public class IPImpl extends BaseDao implements IIPDao {

    @Override
    public Integer addIP(MIP pd) {
        return null;
    }

    @Override
    public Integer modifyIP(MIP pd) {
        return null;
    }

    @Override
    public MIP getIP(MIP pd) {
        return null;
    }

    @Override
    public List<MIP> getIPList(MIP pd) {
        return null;
    }
}
