package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IActorDao;
import com.dndy.model.MActor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "actorImpl")
public class ActorImpl extends BaseDao implements IActorDao {

    @Override
    public Integer addActor(MActor actor) {
        return sqlSessionTemplate.insert("ActorMapper.addActor", actor);
    }

    @Override
    public Integer modifyActor(MActor actor) {
        return sqlSessionTemplate.update("ActorMapper.modifyActor", actor);
    }

    @Override
    public MActor getActor(MActor actor) {
        return sqlSessionTemplate.selectOne("ActorMapper.getActor", actor);
    }

    @Override
    public List<MActor> getActorList(MActor actor) {
        return sqlSessionTemplate.selectList("ActorMapper.getActorList", actor);
    }
}
