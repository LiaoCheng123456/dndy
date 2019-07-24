package com.sdm.model;

import java.io.Serializable;

/**
 * ip
 */
public class MIP implements Serializable {

    private static final long serialVersionUID = 6357869213649815390L;
    // ip
    private Long id;

    // 访问ip
    private String ip;

    // 访问时间
    private Long time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
