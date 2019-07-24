package com.sdm.model;

import java.io.Serializable;

/**
 * 类型
 */
public class MType implements Serializable {

    private static final long serialVersionUID = 6357869213649815390L;
    // id
    private Long id;

    // 类型名
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
