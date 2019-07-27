package com.dndy.dao;

import com.dndy.model.PageData;

import java.util.List;

/**
 * 用户信息dao
 */
public interface IUserDao{
    /**
     * 添加用户
     */
    Integer addUser(PageData pd);

    /**
     * 编辑用户
     */
    Integer modifyUser(PageData pd);

    /**
     * 查询单个用户
     */
    PageData getUser(PageData pd);

    /**
     * 添加用户
     */
    List<PageData> getUserList(PageData pd);
}
