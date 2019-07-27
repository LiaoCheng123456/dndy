package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IRoleDao;
import com.dndy.model.PageData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "roleDaoImpl")
public class RoleDaoImpl extends BaseDao implements IRoleDao {

    @Override
    public Integer addRole(PageData pd) {
        return sqlSessionTemplate.insert("RoleMapper.addRole", pd);
    }

    @Override
    public Integer modifyRole(PageData pd) {
        return sqlSessionTemplate.update("RoleMapper.modifyRole", pd);
    }

    @Override
    public PageData getRole(PageData pd) {
        return sqlSessionTemplate.selectOne("RoleMapper.getRole", pd);
    }

    @Override
    public List<PageData> getRoleList(PageData pd) {
        return sqlSessionTemplate.selectList("RoleMapper.getRoleList", pd);
    }

    @Override
    public Integer deleteRole(PageData pd) {
        return sqlSessionTemplate.delete("RoleMapper.deleteRole", pd);
    }
}
