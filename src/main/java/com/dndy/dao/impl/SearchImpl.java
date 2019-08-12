package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.ISearchDao;
import com.dndy.model.PageData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "searchImpl")
public class SearchImpl extends BaseDao implements ISearchDao {

    @Override
    public Integer addSearch(PageData Search) {
        return sqlSessionTemplate.insert("SearchMapper.addSearch", Search);
    }

    @Override
    public Integer modifySearch(PageData Search) {
        return sqlSessionTemplate.update("SearchMapper.modifySearch", Search);
    }

    @Override
    public PageData getSearch(PageData Search) {
        return sqlSessionTemplate.selectOne("SearchMapper.getSearch", Search);
    }

    @Override
    public PageData deleteSearch(PageData Search) {
        return sqlSessionTemplate.selectOne("SearchMapper.deleteSearch", Search);
    }

    @Override
    public List<PageData> getSearchList(PageData Search) {
        return sqlSessionTemplate.selectList("SearchMapper.getSearchList", Search);
    }
}
