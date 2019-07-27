package com.dndy.dao;

import com.dndy.model.PageData;

import java.util.List;

/**
 * 权限信息dao
 */
public interface IPermissionDao {
    /**
     * 添加权限
     */
    Integer addPermission(PageData pd);

    /**
     * 编辑权限
     */
    Integer modifyPermission(PageData pd);

    /**
     * 查询单个权限
     */
    PageData getPermission(PageData pd);

    /**
     * 添加权限
     */
    List<PageData> getPermissionList(PageData pd);

    /**
     * 删除权限
     */
    Integer deletePermission(PageData pd);
}
