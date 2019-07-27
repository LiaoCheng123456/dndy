package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IRoleInPermissionDao;
import com.dndy.model.PageData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "roleInPermissionDaoImpl")
public class RoleInPermissionDaoImpl extends BaseDao implements IRoleInPermissionDao {

    @Override
    public Integer addRoleInPermission(PageData pd) {
        return sqlSessionTemplate.insert("RoleInPermissionMapper.addRoleInPermission", pd);
    }

    @Override
    public List<PageData> getRoleInPermissionList(PageData pd) {
        return sqlSessionTemplate.selectList("RoleInPermissionMapper.getRoleInPermissionList", pd);
    }

    @Override
    public Integer deleteRoleInPermission(PageData pd) {
        return sqlSessionTemplate.delete("RoleInPermissionMapper.deleteRoleInPermission", pd);
    }
}
