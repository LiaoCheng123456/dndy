package com.sdm.model;

import java.io.Serializable;

/**
 * 点击
 */
public class MClick implements Serializable {

    private static final long serialVersionUID = 6357869213649815390L;

    // id
    private Long id;

    // 点击时间
    private Long clickTime;

    // 视频id
    private Long videoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClickTime() {
        return clickTime;
    }

    public void setClickTime(Long clickTime) {
        this.clickTime = clickTime;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }
}
