package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IClickDao;
import com.dndy.model.PageData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "clickImpl")
public class ClickImpl extends BaseDao implements IClickDao {

    @Override
    public Integer addClick(PageData click) {
        return sqlSessionTemplate.insert("ClickMapper.addClick", click);
    }

    @Override
    public Integer modifyClick(PageData click) {
        return sqlSessionTemplate.update("ClickMapper.modifyClick", click);
    }

    @Override
    public PageData getClick(PageData click) {
        return sqlSessionTemplate.selectOne("ClickMapper.getClick", click);
    }

    @Override
    public List<PageData> getClickList(PageData click) {
        return sqlSessionTemplate.selectList("ClickMapper.getClickList", click);
    }
}
