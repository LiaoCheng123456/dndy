package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IClickDao;
import com.dndy.model.MClick;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "clickImpl")
public class ClickImpl extends BaseDao implements IClickDao {

    @Override
    public Integer addClick(MClick pd) {
        return null;
    }

    @Override
    public Integer modifyClick(MClick pd) {
        return null;
    }

    @Override
    public MClick getClick(MClick pd) {
        return null;
    }

    @Override
    public List<MClick> getClickList(MClick pd) {
        return null;
    }
}
