package com.dndy.model;

import java.io.Serializable;

/**
 * 演员
 */
public class MActor implements Serializable {

    private static final long serialVersionUID = 6357869213649815390L;

    // id
    private Long id;

    // 名字
    private String name;

    // 英文名
    private String nameEng;

    // 性别 男 女
    private String sex;

    // 国家
    private Long countryId;

    private Long addTime;

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }
}
