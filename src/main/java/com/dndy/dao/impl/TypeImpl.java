package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.ITypeDao;
import com.dndy.model.MType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "typeImpl")
public class TypeImpl extends BaseDao implements ITypeDao {

    @Override
    public Integer addType(MType type) {
        return sqlSessionTemplate.insert("TypeMapper.addType", type);
    }

    @Override
    public Integer modifyType(MType type) {
        return sqlSessionTemplate.update("TypeMapper.modifyType", type);
    }

    @Override
    public MType getType(MType type) {
        return sqlSessionTemplate.selectOne("TypeMapper.getType", type);
    }

    @Override
    public List<MType> getTypeList(MType type) {
        return sqlSessionTemplate.selectList("TypeMapper.getTypeList", type);
    }
}
