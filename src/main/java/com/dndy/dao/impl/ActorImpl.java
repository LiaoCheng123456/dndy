package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IActorDao;
import com.dndy.model.PageData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "actorImpl")
public class ActorImpl extends BaseDao implements IActorDao {

    @Override
    public Integer addActor(PageData actor) {
        return sqlSessionTemplate.insert("ActorMapper.addActor", actor);
    }

    @Override
    public Integer modifyActor(PageData actor) {
        return sqlSessionTemplate.update("ActorMapper.modifyActor", actor);
    }

    @Override
    public PageData getActor(PageData actor) {
        return sqlSessionTemplate.selectOne("ActorMapper.getActor", actor);
    }

    @Override
    public List<PageData> getActorList(PageData actor) {
        return sqlSessionTemplate.selectList("ActorMapper.getActorList", actor);
    }

    @Override
    public Integer deleteActor(PageData actor) {
        return sqlSessionTemplate.delete("ActorMapper.deleteActor", actor);
    }
}
