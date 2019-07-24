package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IClickDao;
import com.dndy.model.MClick;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "clickImpl")
public class ClickImpl extends BaseDao implements IClickDao {

    @Override
    public Integer addClick(MClick click) {
        return sqlSessionTemplate.insert("ClickMapper.addClick", click);
    }

    @Override
    public Integer modifyClick(MClick click) {
        return sqlSessionTemplate.update("ClickMapper.modifyClick", click);
    }

    @Override
    public MClick getClick(MClick click) {
        return sqlSessionTemplate.selectOne("ActorMapper.getClick", click);
    }

    @Override
    public List<MClick> getClickList(MClick click) {
        return sqlSessionTemplate.selectList("ActorMapper.getClickList", click);
    }
}
