package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IActorDao;
import com.dndy.model.MActor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "actorImpl")
public class ActorImpl extends BaseDao implements IActorDao {

    @Override
    public Integer addActor(MActor pd) {
        return null;
    }

    @Override
    public Integer modifyActor(MActor pd) {
        return null;
    }

    @Override
    public MActor getActor(MActor pd) {
        return null;
    }

    @Override
    public List<MActor> getActorList(MActor pd) {
        return null;
    }
}
