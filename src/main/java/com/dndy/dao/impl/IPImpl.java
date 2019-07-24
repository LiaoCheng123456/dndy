package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IIPDao;
import com.dndy.model.MIP;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "IPImpl")
public class IPImpl extends BaseDao implements IIPDao {

    @Override
    public Integer addIP(MIP ip) {
        return sqlSessionTemplate.insert("IPMapper.addIP", ip);
    }

    @Override
    public Integer modifyIP(MIP ip) {
        return sqlSessionTemplate.update("IPMapper.modifyIP", ip);
    }

    @Override
    public MIP getIP(MIP ip) {
        return sqlSessionTemplate.selectOne("IPMapper.getIP", ip);
    }

    @Override
    public List<MIP> getIPList(MIP ip) {
        return sqlSessionTemplate.selectOne("IPMapper.getIPList", ip);
    }
}
