package com.dndy.dao;

import com.dndy.model.PageData;

import java.util.List;

/**
 * 角色和权限关联信息dao
 */
public interface IRoleInPermissionDao {
    /**
     * 添加角色和权限关联
     */
    Integer addRoleInPermission(PageData pd);

    /**
     * 添加角色和权限关联
     */
    List<PageData> getRoleInPermissionList(PageData pd);

    /**
     * 删除角色和权限关联
     */
    Integer deleteRoleInPermission(PageData pd);
}
