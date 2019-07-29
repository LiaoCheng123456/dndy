package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.ITypeDao;
import com.dndy.model.PageData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "typeImpl")
public class TypeImpl extends BaseDao implements ITypeDao {

    @Override
    public Integer addType(PageData type) {
        return sqlSessionTemplate.insert("TypeMapper.addType", type);
    }

    @Override
    public Integer modifyType(PageData type) {
        return sqlSessionTemplate.update("TypeMapper.modifyType", type);
    }

    @Override
    public PageData getType(PageData type) {
        return sqlSessionTemplate.selectOne("TypeMapper.getType", type);
    }

    @Override
    public List<PageData> getTypeList(PageData type) {
        return sqlSessionTemplate.selectList("TypeMapper.getTypeList", type);
    }
}
