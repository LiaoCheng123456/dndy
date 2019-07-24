package com.dndy.dao;

import com.dndy.model.MIP;

import java.util.List;

/**
 * IPdao
 */
public interface IIPDao {
    /**
     * 添加IP
     */
    Integer addIP(MIP pd);

    /**
     * 编辑IP
     */
    Integer modifyIP(MIP pd);

    /**
     * 查询单个IP
     */
    MIP getIP(MIP pd);

    /**
     * 查询多个IP
     */
    List<MIP> getIPList(MIP pd);
}
