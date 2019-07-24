package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.ITypeDao;
import com.dndy.model.MType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "typeImpl")
public class TypeImpl extends BaseDao implements ITypeDao {

    @Override
    public Integer addType(MType pd) {
        return null;
    }

    @Override
    public Integer modifyType(MType pd) {
        return null;
    }

    @Override
    public MType getType(MType pd) {
        return null;
    }

    @Override
    public List<MType> getTypeList(MType pd) {
        return null;
    }
}
