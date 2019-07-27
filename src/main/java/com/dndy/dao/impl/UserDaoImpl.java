package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IUserDao;
import com.dndy.model.PageData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "userDaoImpl")
public class UserDaoImpl extends BaseDao implements IUserDao {

    @Override
    public Integer addUser(PageData pd) {
        return sqlSessionTemplate.insert("UserMapper.addUser", pd);
    }

    @Override
    public Integer modifyUser(PageData pd) {
        return sqlSessionTemplate.update("UserMapper.modifyUser", pd);
    }

    @Override
    public PageData getUser(PageData pd) {
        return sqlSessionTemplate.selectOne("UserMapper.getUser", pd);
    }

    @Override
    public List<PageData> getUserList(PageData pd) {
        return sqlSessionTemplate.selectList("UserMapper.getUserList", pd);
    }
}
