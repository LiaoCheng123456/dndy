package com.dndy.dao.impl;

import com.dndy.dao.BaseDao;
import com.dndy.dao.IPermissionDao;
import com.dndy.model.PageData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "permissionDaoImpl")
public class PermissionDaoImpl extends BaseDao implements IPermissionDao {

    @Override
    public Integer addPermission(PageData pd) {
        return sqlSessionTemplate.insert("PermissionMapper.addPermission", pd);
    }

    @Override
    public Integer modifyPermission(PageData pd) {
        return sqlSessionTemplate.update("PermissionMapper.modifyPermission", pd);
    }

    @Override
    public PageData getPermission(PageData pd) {
        return sqlSessionTemplate.selectOne("PermissionMapper.getPermission", pd);
    }

    @Override
    public List<PageData> getPermissionList(PageData pd) {
        return sqlSessionTemplate.selectList("PermissionMapper.getPermissionList", pd);
    }

    @Override
    public Integer deletePermission(PageData pd) {
        return sqlSessionTemplate.delete("PermissionMapper.deletePermission", pd);
    }
}
