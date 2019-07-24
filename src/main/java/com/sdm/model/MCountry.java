package com.sdm.model;

import java.io.Serializable;

/**
 * 国家
 */
public class MCountry implements Serializable {

    private static final long serialVersionUID = 6357869213649815390L;
    // id
    private Long id;

    // 名字
    private String name;

    // 英文名
    private String nameEng;

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

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }
}
