package com.dndy.dao;

import com.dndy.model.PageData;

import java.util.List;

/**
 * 角色信息dao
 */
public interface IRoleDao {
    /**
     * 添加角色
     */
    Integer addRole(PageData pd);

    /**
     * 编辑角色
     */
    Integer modifyRole(PageData pd);

    /**
     * 查询单个角色
     */
    PageData getRole(PageData pd);

    /**
     * 添加角色
     */
    List<PageData> getRoleList(PageData pd);

    /**
     * 删除角色
     */
    Integer deleteRole(PageData pd);
}
